<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="sec01.ex01.*"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%
	request.setCharacterEncoding("utf-8");
	request.setAttribute("member", new MemberBean("lee", "1234", "이순신", "lee@naver.com"));
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>forward2</title>
</head>
<body>
	<jsp:forward page="member2.jsp" />
</body>
</html>