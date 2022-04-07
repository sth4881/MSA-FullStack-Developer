<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>새 게시물 쓰기</title>
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
				<td><input type="file" id="attach" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="register">등록</button>
				</td>
			</tr>
		</table>
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
		
		// 파일 업로드
		$("#register").on("click", function(e) {
			var formData = new FormData();
			formData.append("title", $("#title").val());
			formData.append("author", $("#author").val());
			formData.append("password", $("#password").val());
			formData.append("content", $("#content").val());
			
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