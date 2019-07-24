package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrderService;


@WebServlet("/consumer/order/checknow/cancel")
public class ConsumerOrderCheckNowCancelServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/consumer/order/checknow/cancel.jsp");
		dispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid=request.getParameter("oid");
		OrderService service=new OrderService();
		service.cancel(oid);
		response.sendRedirect(request.getContextPath()+"/consumer/order/checknow/index");
	}

}
