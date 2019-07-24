package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrderStatisticsService;
import vo.OrderForPresentVO;


@WebServlet("/provider/order/checkall/consumer")
public class ProviderOrderCheckAllConsumerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid=(String)request.getSession().getAttribute("pid");
		OrderStatisticsService service=new OrderStatisticsService();
		List<OrderForPresentVO> orderList=service.getListByConsumerForProvider(pid);
		request.setAttribute("orderList", orderList);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/provider/order/checkall/present.jsp");
		dispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
