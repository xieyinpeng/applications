package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eo.ProviderType;
import eo.Site;
import service.ProviderService;
import vo.ProviderVO;

@WebServlet("/provider/register")
public class ProviderRegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/provider/register.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid=request.getParameter("aid");
		Site site=Site.valueOf(request.getParameter("site"));
		String password=request.getParameter("password");
		ProviderType type=ProviderType.valueOf(request.getParameter("type"));
		
		ProviderVO provider=new ProviderVO();
		provider.setAid(aid);
		provider.setPassword(password);
		provider.setSite(site);
		provider.setType(type);
		
		ProviderService service=new ProviderService();
		service.register(provider);
		
		response.sendRedirect(request.getContextPath()+"/provider/index");
	}

}
