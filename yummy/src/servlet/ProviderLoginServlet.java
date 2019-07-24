package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ManagerService;
import service.ProviderService;


@WebServlet("/provider/login")
public class ProviderLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/provider/login.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid=request.getParameter("pid");
		String password=request.getParameter("password");
		ProviderService service=new ProviderService();
		if(service.checkAuthority(pid, password)) {
			request.getSession().setAttribute("pid", pid);
		}
		response.sendRedirect(request.getContextPath()+"/provider/index");
	}

}
