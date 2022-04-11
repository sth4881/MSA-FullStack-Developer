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
	<style>
		.attachResult {
			width : 100%;
			background-color : gray;
		}
		.attachResult ul {
			display-flex;
			flex-flow : row;
			justify-content : center;
			align-items : center;
		}
		.attachResult ul li {
			list-style
		}
		.attachResult ul li img {
			width : 20px;
		}
	</style>
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
	
	<c:forEach var="attachDTO" items="${attachList}">
		<c:choose>
			<c:when test="${attachDTO.ftype eq 1}">
				<!-- 첨부파일이 이미지인 경우 썸네일 표시 -->
				<img src="<c:url value='display?fname=${attachDTO.fpath}/s_${attachDTO.uuid}_${attachDTO.fname}'/>" />
			</c:when>
			<c:otherwise>
				<a href="<c:url value='download?fname=${attachDTO.fpath}/${attachDTO.uuid}_${attachDTO.fname}'/>">
					<!-- 첨부파일이 이미지가 아닌 경우 attach.png 표시 -->
					<img src="${app}/resources/img/attach.png" style="width : 100px" />
                   </a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<div id="attachResult">
		
	</div>
	
	<a href="${vno}/update">게시글 수정</a> |
	<a href="${vno}/delete">게시글 삭제</a> |
	<a href="../">목록</a>
</body>
<script>
	function showImage(fileCallPath) {
		$(".bigPictureWrapper").css("display", "flex").show();
		$(".bigPicture")
			.html("<img src='display?fname=" + encodeURI(fileCallPath) + "'>")
			.animate({width:'100%', height:'100%'}, 1000);
	}
	$(document).ready(function() {
		// 파일 업로드 전 미리보기
		function showUploadedImage(input) {
			var str = "";
			$(input).each(function(i, obj) {
				if(!obj.image) {
					var fileCallPath = obj.uploadPath + encodeURIComponent(obj.fileName);
					var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
					str += "<li><div><a href='${app}/download?fileName=" + fileCallPath + "'>" 
						+ "<img src='./resources/img/attach.png'>" + obj.fileName + "</a>"
						+ "<span data-file=\'" + fileCallPath + "\' data-type='file' class='span_del'> x </span><div></li>";
				} else {
					//str += "<li>" + obj.fileName + "</li>";
					var fileCallPath = obj.uploadPath + "/s_" + encodeURIComponent(obj.fileName);
					var originPath = obj.uploadPath + "\\" + obj.fileName;
					originPath = originPath.replace(new RegExp(/\\/g), "/");
					//str += "<li><img src='${app}/display?fileName=" +  encodedFileName + "'><li>";
					str += "<li><a href=\"javascript:showImage(\'" + originPath + "\')\">"
						+ "<img src='${app}/display?fileName=" + fileCallPath + "'></a>"
						+ "<span data-file=\'" + fileCallPath + "\' data-type='image' class='span_del'> x </span></li>";
				}
			});
			uploadResult.append(str);
		}
</script>
</html>