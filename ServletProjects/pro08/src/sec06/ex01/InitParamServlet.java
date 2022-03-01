package sec06.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitParamServlet
 */
@WebServlet(
		urlPatterns = { 
				"/sInit1", 
				"/sInit2"
		}, 
		initParams = { 
				@WebInitParam(name = "email", value = "admin@web.com"), 
				@WebInitParam(name = "tel", value = "010-1111-2222")
		})
public class InitParamServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<table><tr>");
		out.print("<td>email : </td><td>" + email + "</td></tr>");
		out.print("<tr><td>tel : </td><td>" + tel + "</td>");
		out.print("</tr></table></body></html>");
	}
}