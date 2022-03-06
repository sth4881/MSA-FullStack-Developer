<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, sec01.ex01.*"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>

<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="bean1" class="sec01.ex01.MemberBean" />
<jsp:setProperty name="bean1" property="*" />

<jsp:useBean id="list" class="java.util.ArrayList" />

<%
	MemberBean bean2 = new MemberBean("son", "1234", "손흥민", "son@test.com");
	list.add(bean1);
	list.add(bean2);
%>

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