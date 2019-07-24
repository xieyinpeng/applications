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
import service.ManagerService;
import vo.AccountBalanceByDateVO;

@WebServlet("/manager/account/present")
public class ManagerAccountPresentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerService managerService=new ManagerService();
		AccountStatisticsService accountService=new AccountStatisticsService();
		String aid=managerService.getAccountId();
		List<AccountBalanceByDateVO> balanceList=accountService.getAccountBalanceByDateList(aid);
		request.setAttribute("balanceList", balanceList);
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/manager/account/present.jsp");
		dispatcher.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
