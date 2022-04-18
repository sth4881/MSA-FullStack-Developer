<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}" />
<c:set var="source" value="${app}/resources/img/attach.png" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
	<c:set var="articleDTO" value="${articleDTO}" />
	<form method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>글 번호</th>
				<td>
					${vno}<input type="hidden" id="ano" required="required" value="${articleDTO.ano}" />
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					${articleDTO.author}<input type="hidden" id="author" value="${articleDTO.author}" required="required" />
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" id="title" value="${articleDTO.title}" autofocus="autofocus" required="required"/>
			    </td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" id="password" required="required" /><br>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea id="content" rows="5" cols="40"required="required">
						${articleDTO.content}
				   	</textarea>
				</td>
			</tr>
		</table>
		<hr>
	
		<div id="attachResult">
			<c:forEach var="attachDTO" items="${attachList}">
				<c:choose>
					<c:when test="${attachDTO.ftype eq 1}">
						<!-- 첨부파일이 이미지인 경우 썸네일 표시
						<a href="javascript:;" data-fpath="${attachDTO.fpath}" data-uuid="${attachDTO.uuid}" data-fname="${attachDTO.fname}" data-ftype="${attachDTO.ftype}">
							<img src="<c:url value='update/display?fname=${attachDTO.fpath}/s_${attachDTO.uuid}_${attachDTO.fname}'/>" />
						</a>
						-->
						<img src="<c:url value='display?fname=${attachDTO.fpath}/s_${attachDTO.uuid}_${attachDTO.fname}'/>" id="${attachDTO.fname}"/>
						<button type="button" class="btn btn-danger btn-circle" data-name="${attachDTO.fname}"></button>
						<input type="hidden" data-uuid="${attachDTO.uuid}" data-fname="${attachDTO.fname}" data-fpath="${attachDTO.fpath}" data-ftype="${attachDTO.ftype}"/>
					</c:when>
					<c:otherwise>
						<!-- 첨부파일이 이미지가 아닌 경우 attach.png 표시
						<a href="<c:url value='download?fname=${attachDTO.fpath}/${attachDTO.uuid}_${attachDTO.fname}'/>">
							<img src="${app}/resources/img/attach.png" style="width : 100px" />
						</a>
						-->
						<img src="${app}/resources/img/attach.png" id="${attachDTO.fname}" style="width : 100px" />
						<button type="button" class="btn btn-danger btn-circle" data-name="${attachDTO.fname}"></button>
						<input type="hidden" data-uuid="${attachDTO.uuid}" data-fname="${attachDTO.fname}" data-fpath="${attachDTO.fpath}" data-ftype="${attachDTO.ftype}"/>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<hr>
		<input type="file" id="attach" name="첨부파일" style="" multiple />
		<button type="button" id="modify">수정</button>
		<button type="button" id="back" onClick="window.location.href='../?vno=${vno}'">취소</button>
	</form>
</body>
<script type="text/javascript" src="<c:url value='/webjars/jquery/3.6.0/dist/jquery.js' />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 파일 종류 및 크기 제한
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880; // 5MB
		function checkExtension(fileName, fileSize) {
			if(fileSize >= maxSize) {
				alert("파일 크기 초과");
				return false;
			}
			if(regex.test(fileName)) {
				alert("해당 종류의 파일은 업로드할 수 없습니다.")
				return false;
			}
			return true;
		}
		
		// 파일을 추가할 때마다 업로드 및 미리보기 업데이트
		$("#attach").on("change", function(e) {
			var files = e.target.files;
			var formData = new FormData();
			for(var i=0; i<files.length; i++) {
				if(!checkExtension(files[i].name, files[i].size)) return false;
				formData.append("attach", files[i]);
			}
	
			$.ajax({
				url : 'uploadAttach',
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				success:function(result) {
					if(result==1) console.log("파일 업로드 성공");
				}
			});
			
			var fileArr = Array.from(e.target.files);
			fileArr.forEach((file, index) => {
				var reader = new FileReader();
				reader.onload = event => {
					// 선택한 첨부파일을 생성하기 위한 img 태그 추가
					var img = document.createElement("img");
					// 첨부파일 타입이 이미지 포맷이라면
					if(file.type=="image/jpeg" || file.type=="image/png" || file.type=="image/gif") {
						img.setAttribute("src", event.target.result);
						img.setAttribute("width", 100);
						img.setAttribute("height", 100);
						img.setAttribute("id", file.name);
					}
					// 첨부파일 타입이 이미지 포맷이 아니라면
					else {
						img.setAttribute("src", "${source}");
						img.setAttribute("width", 150);
						img.setAttribute("height", 100);
						img.setAttribute("id", file.name);
					}
					
					// 첨부파일을 삭제하기 위한 'button' 요소 생성
					var cancelBtn = document.createElement("button");
					cancelBtn.setAttribute("type", "button");
					cancelBtn.setAttribute("class", "btn btn-danger btn-circle");
					cancelBtn.setAttribute("data-name", file.name);
					
					// attachResult에 자식 요소로 첨부파일 및 버튼 추가
					document.getElementById("attachResult").appendChild(img);
					document.getElementById("attachResult").appendChild(cancelBtn);
				}
				reader.readAsDataURL(file);
			});
		});
		
		// 'X' 버튼을 누르면 원본 파일, 표시 이미지, 버튼 삭제
		$("#attachResult").on("click", "button", function(e) {
			var fileName = $(this).data('name');
			$("button[data-name='"+fileName+"']").remove();
			document.getElementById(fileName).remove();
			console.log("화면에서 삭제");
		});
		
		// '수정' 버튼을 누르면 formData를 통해서 데이터 전송
		$("#modify").on("click", function(e) {
			var formData = new FormData();
			formData.append("ano", $("#ano").val());
			formData.append("title", $("#title").val());
			formData.append("content", $("#content").val());
			formData.append("password", $("#password").val());
			
			var files = $("#attachResult").children('input');
			for(var i=0; i<files.length; i++) {
				console.log(files[i]);
			}
			
			$.ajax({
				url : '',
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				success:function(result) {
					if(result == "ERROR") {
						alert("게시물 수정 오류");
					} else {
						alert("게시물 수정 완료");
						window.location.href = "../?vno="+result;
					}
				}
			});
		});
		
		// '취소' 버튼을 누르면 temp에 저장된 데이터 전부 삭제하고 뒤로가기
		$("#back").on("click", function(e) {
			$.ajax({
				url : 'deleteAttachAll',
				type : 'POST',
				success:function(result) {
					if(result==1) {
						window.location.href="../?vno=${vno}";
					}
				}
			});
		});
	});
</script>
</html>