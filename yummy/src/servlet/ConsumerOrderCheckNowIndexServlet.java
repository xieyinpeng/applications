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


@WebServlet("/consumer/order/checknow/index")
public class ConsumerOrderCheckNowIndexServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid=(String)request.getSession().getAttribute("cid");
		OrderStatisticsService service=new OrderStatisticsService();
		List<OrderForPresentVO> orderList=service.getCurrentListForConsumer(cid);
		request.setAttribute("orderList", orderList);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/consumer/order/checknow/index.jsp");
		dispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
