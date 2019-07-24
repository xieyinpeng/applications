package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ConsumerService;
import service.SecurityService;
import vo.VerifyImageVO;

@WebServlet("/consumer/login")
public class ConsumerLoginServlet extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SecurityService service=new SecurityService();
		VerifyImageVO verifyImage=service.getVerifyImage(request.getServletContext().getRealPath(""));
		request.setAttribute("verifyImageUrl", verifyImage.getImageUrl());
		request.getSession().setAttribute("verifyCode", verifyImage.getCode());
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/consumer/login.jsp");
		dispatcher.include(request, response);	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConsumerService service=new ConsumerService();
		String verifyCode_true=(String)request.getSession().getAttribute("verifyCode");
		String verifyCode=request.getParameter("verifyCode");
		String cid=request.getParameter("cid");
		String password=request.getParameter("password");
		if(!(verifyCode_true!=null&&verifyCode_true.equals(verifyCode))) {
			response.getWriter().write("wrong code!!");
			return;
		}
		if(service.checkAuthority(cid,password)) {
			request.getSession().setAttribute("cid", cid);
			response.sendRedirect(request.getContextPath()+"/consumer/index");
		}else {
			response.sendRedirect(request.getContextPath()+"/consumer/login");
		}
		
		
	}

}
