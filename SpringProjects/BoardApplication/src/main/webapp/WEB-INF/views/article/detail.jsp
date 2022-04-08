<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.Date"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="app" value="${pageContext.request.contextPath}" />
<c:set var="now" value="<%= new Date() %>" />
<fmt:formatDate value="${now}" pattern="YYYY-MM-dd" var="today" />
<fmt:formatDate value="${articleDTO.createdAt}" pattern="YYYY-MM-dd" var="created" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${articleDTO.title} :: 게시물 상세보기</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${vno}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${articleDTO.author}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${articleDTO.title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${articleDTO.content}</td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td>
				<c:choose>
					<c:when test="${today eq created}">
						<fmt:formatDate value="${articleDTO.createdAt}" type="time" />
					</c:when>
					<c:otherwise>
						<fmt:formatDate value="${articleDTO.createdAt}" type="date" pattern="YYYY-MM-dd" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${articleDTO.viewCount}</td>
		</tr>
	</table>
	
	<div id="attachResult">
		<c:forEach var="attachDTO" items="${attachList}">
			<c:choose>
				<c:when test="${attachDTO.ftype eq 1}">
					<img src="<c:url value='display?fname=${attachDTO.fpath}/s_${attachDTO.fname}'/>" />
				</c:when>
			</c:choose>
		</c:forEach>
	</div>
	
	<a href="../">목록</a> |
	<a href="${vno}/update">수정</a> |
	<a href="${vno}/delete">삭제</a>
</body>
</html>