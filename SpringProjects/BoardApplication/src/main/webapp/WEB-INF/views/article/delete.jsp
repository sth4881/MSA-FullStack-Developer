<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시물 삭제하기</title>
</head>
<body>
	<form method="post">
		<table>
			<tr>
				<th>글번호</th>
				<td>${vno}</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="password" required="required" autofocus="autofocus" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="삭제" />
					<input type="button" value="취소" onClick="window.location.href='../?vno=${vno}'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>