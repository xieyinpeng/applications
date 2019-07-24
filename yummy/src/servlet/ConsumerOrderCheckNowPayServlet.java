package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DishOrderDAO;
import po.DishOrderPO;
import service.AccountService;
import service.OrderService;
import service.OrderStatisticsService;
import vo.AccountTransferVO;


@WebServlet("/consumer/order/checknow/pay")
public class ConsumerOrderCheckNowPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/consumer/order/checknow/pay.jsp");
		dispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid=request.getParameter("oid");
		String password=request.getParameter("password");
		
		OrderService service=new OrderService();
		service.pay(oid,password);
		
		response.sendRedirect(request.getContextPath()+"/consumer/order/checknow/index");
	}

}
