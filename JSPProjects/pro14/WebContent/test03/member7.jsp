<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, sec01.ex01.*"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	List<MemberBean> list = new ArrayList<>();
	list.add(new MemberBean("son", "1234", "손흥민", "son@naver.com"));
	list.add(new MemberBean("kii", "1234", "기성용", "kii@naver.com"));
	list.add(new MemberBean("park", "1234", "박지성", "park@naver.com"));
%>

<c:set var="list" value="<%= list %>" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원정보 출력창</title>
</head>
<body>
	<table border="1" align="center">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="5%"><b>이름</b></td>
			<td width="5%"><b>이메일</b></td>
		</tr>
		<c:forEach var="i" begin="0" end="2" step="1">
			<tr align="center">
				<td>${list[i].id}</td>
				<td>${list[i].pwd}</td>
				<td>${list[i].name}</td>
				<td>${list[i].email}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>