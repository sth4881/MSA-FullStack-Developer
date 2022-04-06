<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<caption>게시물 상세보기</caption>
		<tr>
			<th>번호</th>
			<td>${virtual}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${boardDTO.name}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${boardDTO.title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${boardDTO.content}</td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td><fmt:formatDate value="${boardDTO.regdate}" type="both" /></td>
		</tr>		<tr>
			<th>조회수</th>
			<td>${boardDTO.readcount}</td>
		</tr>
	</table><br>
	<a href="../">목록</a> |
	<a href="update">수정</a> |
	<a href="delete">삭제</a>
</body>
</html>