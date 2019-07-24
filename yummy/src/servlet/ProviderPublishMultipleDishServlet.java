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


@WebServlet("/provider/publish/multipledish")
public class ProviderPublishMultipleDishServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/provider/publish/multipledish.jsp");
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
		String[] sidArray=request.getParameterValues("sid");
		List<String> sidList=new ArrayList<String>();
		for(String sid:sidArray) {
			sidList.add(sid);
		}
		
		ProviderMultipleDishVO multiple=new ProviderMultipleDishVO();
		multiple.setDateBegin(dateBegin);
		multiple.setDateEnd(dateEnd);
		multiple.setLimit(limit);
		multiple.setPrice(price);
		multiple.setSummary(summary);
		multiple.setSidList(sidList);
		
		ProviderService service=new ProviderService();
		service.publishMultipleDish(multiple, pid);
		
		response.sendRedirect(request.getContextPath()+"/provider/publish/index");
	}

}
