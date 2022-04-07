<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.Date"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="now" value="<%= new Date() %>" />
<fmt:formatDate value="${now}" pattern="YYYY-MM-dd" var="today" />
<fmt:formatDate value="${dto.createdAt}" pattern="YYYY-MM-dd" var="created" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${dto.title} :: 게시물 상세보기</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>글번호</th>
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
			<td>
				<c:choose>
					<c:when test="${today eq created}">
						<fmt:formatDate value="${dto.createdAt}" type="time" />
					</c:when>
					<c:otherwise>
						<fmt:formatDate value="${dto.createdAt}" type="date" pattern="YYYY-MM-dd" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${dto.viewCount}</td>
		</tr>
	</table>
	
	<div id="attachResult">
		<ul>
			
		</ul>
	</div>
	
	<c:forEach items="${list}" var="dto" varStatus="status">
		<img src="" />
	</c:forEach>

	<br>
	<a href="../">목록</a> |
	<a href="${vno}/update">수정</a> |
	<a href="${vno}/delete">삭제</a>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		var ano = '<c:out value="${dto.ano}"/>';
		$.getJSON("getAttachList", {ano : ano}, function(attachResultArr) {
			$(attachResultArr).each(function(i, attach) {
				if(attach.image==0) {
					var fileCallPath = attach.uploadPath + encodeURIComponent(attach.fileName);
					
				}
			})
		});
	});
</script>
</html>