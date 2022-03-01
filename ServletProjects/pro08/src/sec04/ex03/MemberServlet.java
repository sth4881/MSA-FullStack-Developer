package sec04.ex03;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8");
	      response.setContentType("text/html;charset=utf-8");
	      
	      MemberDAO dao = new MemberDAO();
	      String command = request.getParameter("command");
	      if(command!=null && command.equals("addMember")) {
			 String id = request.getParameter("id");
			 String pwd = request.getParameter("pwd");
			 String name = request.getParameter("name");
			 String email = request.getParameter("email");
			 
			 MemberVO vo = new MemberVO();
			 vo.setId(id);
			 vo.setPwd(pwd);
			 vo.setName(name);
			 vo.setEmail(email);
		     dao.addMember(vo);
	      } else if(command!=null && command.equals("delMember") ) {
	    	  String id = request.getParameter("id");
	    	  dao.delMember(id);
	      }
	      
	      request.setAttribute("memberList", dao.listMembers());
	      RequestDispatcher dispatch = request.getRequestDispatcher("viewMembers");
	      dispatch.forward(request, response);
	}
}
