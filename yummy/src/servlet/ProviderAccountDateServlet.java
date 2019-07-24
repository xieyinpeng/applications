package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AccountStatisticsService;
import service.ConsumerStatisticsService;
import service.ProviderStatisticsService;
import vo.AccountBalanceByDateVO;
import vo.AccountTransferForPresentVO;


@WebServlet("/provider/account/date")
public class ProviderAccountDateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid=(String)request.getSession().getAttribute("pid");
		ProviderStatisticsService providerService=new ProviderStatisticsService();
		String aid=providerService.get(pid).getAid();
		
		AccountStatisticsService service=new AccountStatisticsService();
		List<AccountTransferForPresentVO> transferList=service.getListByDate(aid);
		request.setAttribute("transferList", transferList);
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/provider/account/present.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
