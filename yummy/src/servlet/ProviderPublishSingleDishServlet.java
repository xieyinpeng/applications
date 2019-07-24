package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProviderService;
import vo.ProviderMultipleDishVO;
import vo.ProviderSingleDishVO;


@WebServlet("/provider/publish/singledish")
public class ProviderPublishSingleDishServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/provider/publish/singledish.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid=(String)request.getSession().getAttribute("pid");
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
		Date dateBegin = null;
		Date dateEnd = null;
		try {
			dateBegin = format.parse(request.getParameter("dateBegin"));
			dateEnd=format.parse(request.getParameter("dateEnd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Double limit=Double.valueOf(request.getParameter("limit"));
		Double price=Double.valueOf(request.getParameter("price"));
		String summary=request.getParameter("summary");
		
		ProviderSingleDishVO single=new ProviderSingleDishVO();
		single.setDateBegin(dateBegin);
		single.setDateEnd(dateEnd);
		single.setLimit(limit);
		single.setPrice(price);
		single.setSummary(summary);
		
		ProviderService service=new ProviderService();
		service.publishSingleDish(single, pid);
		
		response.sendRedirect(request.getContextPath()+"/provider/publish/index");
	}

}
