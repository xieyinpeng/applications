package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ManagerService;
import service.ProviderService;
import service.ProviderStatisticsService;
import vo.ProviderForPresentVO;


@WebServlet("/manager/provider/audit")
public class ManagerProviderAuditServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProviderStatisticsService service=new ProviderStatisticsService();
		List<ProviderForPresentVO> updatedList=service.getUpdatedList();
		List<ProviderForPresentVO> registeredList=service.getRegisteredList();
		request.setAttribute("updatedList", updatedList);
		request.setAttribute("registeredList", registeredList);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/manager/provider/audit.jsp");
		dispatcher.include(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid=request.getParameter("pid");
		Boolean isApproved=Boolean.valueOf(request.getParameter("isApproved"));
		ProviderService service=new ProviderService();
		if(isApproved) {
			service.approve(pid);
		}else {
			service.disapprove(pid);
		}
		response.sendRedirect(request.getContextPath()+"/manager/provider/index");
	}

}
