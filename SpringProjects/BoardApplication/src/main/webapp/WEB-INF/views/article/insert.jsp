<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}" />
<c:set var="source" value="${app}/resources/img/attach.png" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>새 게시물 쓰기</title>
	<style>
		
	</style>
</head>
<body>
	<form method='POST' enctype="multipart/form-data">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" id="title" autofocus="autofocus" required="required" /></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" id="author" required="required" /></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="password" required="required" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
			   		<textarea id="content" rows="5" cols="40" required="required"></textarea>
			   	</td>
			</tr>
			<tr>
			   	<th>첨부파일</th>
				<td><input type="file" id="attach" multiple /></td>
			</tr>
		</table>
		
		<div id="preview">

		</div>
		
		<button type="button" id="register">등록</button>
		<input type="button" value="취소" onClick="window.location.href='./'" />
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
		
		// 파일 내용이 변경될 때마다 썸네일 추가
		$("#attach").on("change", function(e) {
			var arr = Array.from(e.target.files);
			arr.forEach((file, index) => {
				var reader = new FileReader();
				reader.onload = event => {
					var img = document.createElement("img");
					// 첨부파일 타입이 이미지 포맷이라면
					if(file.type=="image/jpeg" || file.type=="image/png" || file.type=="image/gif") {
						img.setAttribute("src", event.target.result);

					}
					// 첨부파일 타입이 이미지 포맷이 아니라면
					else {
						img.setAttribute("src", "${source}");
					}
					// 미리보기용 이미지 크기 줄이기
					img.setAttribute("width", 100);
					img.setAttribute("height", 100);
					document.getElementById("preview").appendChild(img);
				}
				reader.readAsDataURL(file);
			});
		});
		
		// 파일 내용이 변경될 때마다 썸네일 삭제
		
		
		// 파일 업로드 -> 첨부파일은 내용이 변경될 때마다 업로드하도록 변경할 것
		// input 내용은 지금처럼 '등록' 버튼을 누를 때 전송하는 방식으로 처리할 것
		$("#register").on("click", function(e) {
			var formData = new FormData();
			formData.append("title", $("#title").val());
			formData.append("author", $("#author").val());
			formData.append("password", $("#password").val());
			formData.append("content", $("#content").val());
			
			// '등록' 버튼을 눌렀을 때 남아있는 파일들을 서버로 업로드하도록 구현할 것
			var attach = $("#attach");
			var files = attach[0].files;
			for(var i=0; i<files.length; i++) {
				if(!checkExtension(files[i].name, files[i].size)) return false;
				formData.append("attach", files[i]);
			}

			$.ajax({
				url : '',
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				success:function(result) {
					if(result==1) {
						alert("게시물 작성 완료");
						window.location.href = "../1/";
					}
				}
			});
		});
	});
</script>
</html>