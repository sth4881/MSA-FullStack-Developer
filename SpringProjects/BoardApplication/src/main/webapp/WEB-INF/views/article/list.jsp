<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시물 목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<td colspan="4">전체 페이지 수 : ${pageCount}</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${list}" var="dto" varStatus="status">
			<tr>
				<td>${articleCount - status.index - ((page-1)*articlePerPage)}</td>
				<td><a href="../${page}/${dto.ano}/?vno=${articleCount - status.index - ((page-1)*articlePerPage)}">${dto.title}</a></td>
				<td>${dto.author}</td>
				<td>${dto.viewCount}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4">
				<!-- 시작 페이지가 1이 아닌 경우 'Prev' 출력 -->
				<c:if test="${startPage != 1}">
					[<a href="../${startPage-1}/">Prev</a>]
				</c:if>
				
				<!-- 페이지 블록 출력 -->
				<c:forEach begin="${startPage}" end="${endPage}" var="p">
					<c:if test="${p == page}">${p}</c:if>
					<c:if test="${p != page}">
						<a href="../${p}/">${p}</a>
					</c:if>
				</c:forEach>
				
				<!-- 마지막 페이지가 총 페이지의 개수가 아닌 경우 'Next' 출력 -->
				<c:if test="${endPage != pageCount}">
					[<a href="../${endPage+1}/">Next</a>]
				</c:if>
			</td>
	</table>
	<a href="insert">글쓰기</a>
</body>
</html>