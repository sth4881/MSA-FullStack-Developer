<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="bean" class="sec01.ex02.MemberBean" />
<jsp:setProperty name="bean" property="*" />

<jsp:useBean id="addr" class="sec01.ex02.Address" />
<jsp:setProperty name="addr" property="city" value="서울" />
<jsp:setProperty name="addr" property="zipCode" value="07654" />

<%
	bean.setAddr(addr);
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
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="5%"><b>이름</b></td>
			<td width="5%"><b>이메일</b></td>
			<td width="5%"><b>도시</b></td>
			<td width="5%"><b>우편번호</b></td>
		</tr>
		<tr align="center">
			<td>${bean.id}</td>
			<td>${bean.pwd}</td>
			<td>${bean.name}</td>
			<td>${bean.email}</td>
			<td><%= bean.getAddr().getCity() %></td>
			<td><%= bean.getAddr().getZipCode() %></td>
		</tr>
		<tr align="center">
			<td>${bean.id}</td>
			<td>${bean.pwd}</td>
			<td>${bean.name}</td>
			<td>${bean.email}</td>
			<td>${bean.addr.city}</td>
			<td>${bean.addr.zipCode}</td>
		</tr>
	</table>
</body>
</html>