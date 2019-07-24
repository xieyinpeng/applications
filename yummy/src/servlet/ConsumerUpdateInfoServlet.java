package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eo.Site;
import service.ConsumerService;
import service.ConsumerStatisticsService;
import vo.ConsumerAddressVO;
import vo.ConsumerForPresentVO;
import vo.ConsumerVO;


@WebServlet("/consumer/updateinfo")
public class ConsumerUpdateInfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid=(String)request.getSession().getAttribute("cid");
		ConsumerStatisticsService service=new ConsumerStatisticsService();
		ConsumerForPresentVO consumer=service.get(cid);
		request.setAttribute("consumer", consumer);
		request.setAttribute("cid", cid);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/consumer/updateinfo.jsp");
		dispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid=(String)request.getSession().getAttribute("cid");
		String aid=request.getParameter("aid");
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String phoneNumber=request.getParameter("phoneNumber");
		String[] siteList=request.getParameterValues("site");
		List<ConsumerAddressVO> addressList=new ArrayList<ConsumerAddressVO>();
		for(String site:siteList) {
			ConsumerAddressVO address=new ConsumerAddressVO();
			address.setSite(Site.valueOf(site));
			addressList.add(address);
		}
		
		ConsumerVO consumer=new ConsumerVO();
		consumer.setAid(aid);
		consumer.setEmail(email);
		consumer.setName(name);
		consumer.setPassword(password);
		consumer.setPhoneNumber(phoneNumber);
		consumer.setConsumerAddressList(addressList);
		
		ConsumerService service=new ConsumerService();
		service.update(consumer, cid);
		response.sendRedirect(request.getContextPath()+"/consumer/index");
	}

}
