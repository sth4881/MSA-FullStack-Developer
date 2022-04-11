<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<table>
		<tr>
			<td colspan="2">
				<tiles:insertAttribute name="header" />
			</td>
		</tr>
		<tr>
			<td>
				<tiles:insertAttribute name="body" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<tiles:insertAttribute name="footer" />
			</td>
		</tr>
	</table>
</body>
</html>