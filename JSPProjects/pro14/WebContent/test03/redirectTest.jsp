<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>c:redirect 테스트</title>
</head>
<body>
	<c:redirect url="/test01/member1.jsp">
		<c:param name="id" value="hong" />
		<c:param name="pwd" value="1243" />
		<c:param name="name" value="홍길동" />
		<c:param name="email" value="hong@naver.com" />
	</c:redirect>
</body>
</html>