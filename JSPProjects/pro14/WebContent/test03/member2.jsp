<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, sec01.ex01.*"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="list" class="java.util.ArrayList" />
<jsp:useBean id="map" class="java.util.HashMap" />
<%
	list.add(new MemberBean("son", "1234", "손흥민", "son@naver.com"));
	list.add(new MemberBean("ki", "1233", "기성용", "ki@naver.com"));
	map.put("members", list);
	
	map.put("id", "park2");
	map.put("pwd", "1233");
	map.put("name", "박지성");
	map.put("email", "park@naver.com");
%>
<c:set var="members" value="${map.list}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 출력창</title>
</head>
<body>
	<table border="1" align="center">
		<tr align="center" bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
			<td width="20%"><b>비밀번호</b></td>
			<td width="20%"><b>이름</b></td>
			<td width="20%"><b>이메일</b></td>
		</tr>
		<!-- 박지성으로 저장한 친구들 출력 -->
		<tr align="center">
			<td>${map.id}</td>
			<td>${map.pwd}</td>
			<td>${map.name}</td>
			<td>${map.email}</td>
		</tr>
		<tr align="center">
			<td>${list[0].id}</td>
			<td>${list[0].pwd}</td>
			<td>${list[0].name}</td>
			<td>${list[0].email}</td>
		</tr>
		<tr align="center">
			<td>${list[1].id}</td>
			<td>${list[1].pwd}</td>
			<td>${list[1].name}</td>
			<td>${list[1].email}</td>
		</tr>
	</table>
</body>
</html>