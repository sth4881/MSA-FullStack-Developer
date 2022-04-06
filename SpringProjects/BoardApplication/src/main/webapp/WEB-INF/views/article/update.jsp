<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="dto" value="${boardDTO}" />
	<form method="post">
		<table>
			<caption>게시물 수정</caption>
			<tr>
				<th>글 번호</th>
				<td>
					${vno}<input type="hidden" name="no" required="required" value="${dto.no}" />
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title" value="${dto.title}" autofocus="autofocus" required="required"/>
			    </td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" value="${dto.name}" required="required"/></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="password" required="required" /><br>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="5" cols="40"required="required">
						${dto.content}
				   	</textarea>
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