<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		<table>
			<caption>게시물 삭제</caption>
			<tr>
				<th>글번호</th>
				<td><input type="hidden" name="no" value="${no}" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="password" required="required" autofocus="autofocus" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="완료" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>