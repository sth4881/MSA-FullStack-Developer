<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, sec01.ex01.*"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%
	request.setCharacterEncoding("utf-8");
	List<MemberBean> list = new ArrayList<>();
	list.add(new MemberBean("lee", "1123", "이순신", "lee@naver.com"));
	list.add(new MemberBean("son", "1234", "손흥민", "son@naver.com"));
	request.setAttribute("members", list);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>forward3</title>
</head>
<body>
	<jsp:forward page="member3.jsp" />
</body>
</html>