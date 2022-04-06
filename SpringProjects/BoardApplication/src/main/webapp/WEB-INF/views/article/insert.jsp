<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>새 게시물 쓰기</title>
</head>
<body>
	<form method="post">
		<table>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" autofocus="autofocus" required="required" /></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="author" required="required" /></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="password" required="required" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
		   		<textarea name="content" rows="5" cols="40" required="required"></textarea>
		   	</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="등록" /></td>
		</tr>
		</table>
	</form>
</body>
</html>