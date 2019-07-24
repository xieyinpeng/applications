package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eo.ProviderDiscountType;
import service.ProviderService;
import sun.misc.FormattedFloatingDecimal.Form;
import vo.ProviderDiscountVO;


@WebServlet("/provider/publish/discount")
public class ProviderPublishDiscountServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/provider/publish/discount.jsp");
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
		int levelMax=Integer.valueOf(request.getParameter("levelMax"));
		int levelMin=Integer.valueOf(request.getParameter("levelMin"));
		Double moneyMax=Double.valueOf(request.getParameter("moneyMax"));
		Double moneyMin=Double.valueOf(request.getParameter("moneyMin"));
		Double scale=Double.valueOf(request.getParameter("scale"));
		String summary=request.getParameter("summary");
		ProviderDiscountType type=ProviderDiscountType.valueOf(request.getParameter("type"));
		
		ProviderDiscountVO discount=new ProviderDiscountVO();
		discount.setDateBegin(dateBegin);
		discount.setDateEnd(dateEnd);
		discount.setLevelMax(levelMax);
		discount.setLevelMin(levelMin);
		discount.setMoneyMax(moneyMax);
		discount.setMoneyMin(moneyMin);
		discount.setScale(scale);
		discount.setSummary(summary);
		discount.setType(type);
		
		ProviderService service=new ProviderService();
		service.publishDiscount(discount, pid);
		response.sendRedirect(request.getContextPath()+"/provider/publish/index");
	}

}
