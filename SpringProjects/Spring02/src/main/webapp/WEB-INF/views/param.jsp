<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Action 값을 주지 않은 경우, 호출했던 URL이 다시 호출된다. -->
	<form method="post">
		난 누구지? 내 이름을 불러봐~!<br>
		<input type="text" name="myname" autofocus="autofocus" /><br>
		나이 : <input type="number" name="age">
		<button type="submit">say</button><br>
		<input type="radio" name="hobby" value="잠자기">잠자기<br>
		<input type="radio" name="hobby" value="축구">축구<br>
		<input type="radio" name="hobby" value="농구">농구<br>
	</form>
</body>
</html>