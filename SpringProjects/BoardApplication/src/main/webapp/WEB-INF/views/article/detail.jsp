<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${dto.title} :: 게시물 상세보기</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>번호</th>
			<td>${vno}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.author}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${dto.content}</td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td><fmt:formatDate value="${dto.createdAt}" type="both" /></td>
		</tr>
		<tr>
			<th>수정일자</th>
			<td><fmt:formatDate value="${dto.updatedAt}" type="both" /></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${dto.viewCount}</td>
		</tr>
	</table><br>
	<a href="../">목록</a> |
	<a href="update">수정</a> |
	<a href="../../${page}/${dto.ano}/delete">삭제</a>
</body>
</html>