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
	
	<div class="attachResult">
		<c:forEach var="attachDTO" items="${attachList}">
			<c:choose>
				<c:when test="${attachDTO.ftype eq 1}">
					<!-- 첨부파일이 이미지인 경우 썸네일 표시 -->
					<a href="javascript:;" data-fpath="${attachDTO.fpath}" data-uuid="${attachDTO.uuid}" data-fname="${attachDTO.fname}" data-ftype="${attachDTO.ftype}">
						<img src="<c:url value='display?fname=${attachDTO.fpath}/s_${attachDTO.uuid}_${attachDTO.fname}'/>" />
					</a>
				</c:when>
				<c:otherwise>
					<a href="<c:url value='download?fname=${attachDTO.fpath}/${attachDTO.uuid}_${attachDTO.fname}'/>">
						<!-- 첨부파일이 이미지가 아닌 경우 attach.png 표시 -->
						<img src="${app}/resources/img/attach.png" style="width : 100px" />
					</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	
	<div class="bigPictureWrapper">
		<div class="bigPicture">
		
		</div>
	</div>
	
	<a href="${vno}/update">게시글 수정</a> |
	<a href="${vno}/delete">게시글 삭제</a> |
	<a href="../">목록</a>
</body>
<script type="text/javascript" src="<c:url value="/webjars/jquery/3.6.0/dist/jquery.js" />"></script>
<script type="text/javascript">
	// 썸네일을 클릭하면 원본 이미지를 보여주는 메소드
	function showBigImage(fileCallPath) {
		$(".bigPictureWrapper").css("display", "flex").show();
		$(".bigPicture")
			.html("<img src='display?fname=" + encodeURI(fileCallPath) + "'>")
			.animate({width:'100%', height:'100%'}, 1000);
	}
	
	$(document).ready(function() {
		// 썸네일을 클릭하면 원본 이미지 표시
		$(".attachResult").on("click", "a", function(e) {
			var path = decodeURIComponent($(this).data("fpath")+"/" + $(this).data("uuid") + "_" + $(this).data("fname"));
			if($(this).data("ftype")==1) {
				showBigImage(path.replace(new RegExp(/\\/g), "/"));
			}
		});
		
		// 원본 이미지를 클릭하면 이미지 소멸
		$(".bigPictureWrapper").on("click", function(e) {
			$(".bigPicture").animate({width : '0%', height : '0%'}, 1000);
			setTimeout(() => { $(this).hide(); }, 1000);
		});
	});
</script>
</html>