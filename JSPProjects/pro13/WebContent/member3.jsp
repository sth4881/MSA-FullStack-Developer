<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, sec01.ex01.*"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="memberBean" class="sec01.ex01.MemberBean" scope="page" />
<jsp:setProperty name="memberBean" property="id" value='<%= request.getParameter("id") %>' />
<jsp:setProperty name="memberBean" property="pwd" value='<%= request.getParameter("pwd") %>' />
<jsp:setProperty name="memberBean" property="name" value='<%= request.getParameter("name") %>' />
<jsp:setProperty name="memberBean" property="email" value='<%= request.getParameter("email") %>' />

<%
	MemberDAO dao = new MemberDAO();
	dao.addMember(memberBean);
	
	List list = dao.listMembers();
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원목록창</title>
</head>
<body>
	<table align="center" width="100%">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%" >아이디</td>
	      	<td width="7%">비밀번호</td>
	      	<td width="5%" >이름</td>
	      	<td width="11%" >이메일</td>
      	</tr>
    	<%
    		if(list.size()==0) {
    	%>
    	<tr>
    		<td colspan="5">
		        <p align="center">
		        	<b><span style="font-size:9pt;">등록된 회원이 없습니다. </span></b>
     			</p>
	    	</td>
	    </tr>
    	<%
    		} else {
    			for(int i=0; i<list.size(); i++) {
    				MemberBean bean = (MemberBean)list.get(i);
    	%>
    	<tr align="center">
	       <td><%= bean.getId() %></td>
	       <td><%= bean.getPwd() %></td>
	       <td><%= bean.getName() %></td>
	       <td><%= bean.getEmail() %></td>
	   </tr>
	   <%
    			}
    		}
	   %>
	</table>
</body>
</html>