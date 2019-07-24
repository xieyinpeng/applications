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
import vo.AccountTransferForPresentVO;


@WebServlet("/consumer/account/date")
public class ConsumerAccountDateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid=(String)request.getSession().getAttribute("cid");
		ConsumerStatisticsService consumerService=new ConsumerStatisticsService();
		String aid=consumerService.get(cid).getAid();
		
		AccountStatisticsService service=new AccountStatisticsService();
		List<AccountTransferForPresentVO> transferList=service.getListByDate(aid);
		request.setAttribute("transferList", transferList);
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/consumer/account/present.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
