package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ConsumerService;


@WebServlet("/consumer/logoff")
public class ConsumerLogoffServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/consumer/logoff.jsp");
		dispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String cid=(String)session.getAttribute("cid");
		ConsumerService service=new ConsumerService();
		service.logoff(cid);
		session.setAttribute("cid", null);
		response.sendRedirect(request.getContextPath()+"/consumer/index");
	}

}
