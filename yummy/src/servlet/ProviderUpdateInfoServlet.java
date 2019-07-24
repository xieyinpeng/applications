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
import service.ProviderStatisticsService;
import vo.ProviderForPresentVO;
import vo.ProviderVO;


@WebServlet("/provider/updateinfo")
public class ProviderUpdateInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid=(String)request.getSession().getAttribute("pid");
		ProviderStatisticsService service=new ProviderStatisticsService();
		ProviderForPresentVO provider=service.get(pid);
		request.setAttribute("type", provider.getAid());
		request.setAttribute("pid", provider.getPid());
		request.setAttribute("site", provider.getSite());
		request.setAttribute("type", provider.getType());
		request.setAttribute("aid", provider.getAid());
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/provider/updateinfo.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid=(String)request.getSession().getAttribute("pid");
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
		service.update(provider, pid);
		
		response.sendRedirect(request.getContextPath()+"/provider/index");
	}

}
