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


@WebServlet("/consumer/order/checkall/money")
public class ConsumerOrderCheckAllMoneyServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderStatisticsService service=new OrderStatisticsService();
		String cid=(String)request.getSession().getAttribute("cid");
		List<OrderForPresentVO> orderList=service.getListByMoneyForConsumer(cid);
		request.setAttribute("orderList", orderList);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/consumer/order/checkall/present.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
