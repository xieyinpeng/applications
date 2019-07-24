package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ConsumerStatisticsService;
import service.OrderService;
import service.ProviderStatisticsService;
import vo.ConsumerAddressForPresentVO;
import vo.DishOrderVO;
import vo.OrderMultipleDishVO;
import vo.OrderSingleDishVO;
import vo.ProviderDiscountForPresentVO;
import vo.ProviderMultipleDishForPresentVO;
import vo.ProviderSingleDishForPresentVO;


@WebServlet("/consumer/order/make")
public class ConsumerOrderMakeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProviderStatisticsService providerService=new ProviderStatisticsService();
		List<ProviderSingleDishForPresentVO> singleList=providerService.getSingleDishList();
		request.setAttribute("singleDishList", singleList);
		List<ProviderMultipleDishForPresentVO> multipleList=providerService.getMultipleDishList();
		request.setAttribute("multipleDishList", multipleList);
		List<ProviderDiscountForPresentVO> discountList=providerService.getDiscountList();
		request.setAttribute("discountList", discountList);
		ConsumerStatisticsService consumerService=new ConsumerStatisticsService();
		String cid=(String)request.getSession().getAttribute("cid");
		List<ConsumerAddressForPresentVO> addressList=consumerService.getAddressList(cid);
		request.setAttribute("addressList", addressList);
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/consumer/order/make.jsp");
		dispatcher.include(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String caid=request.getParameter("caid");
		String pid=request.getParameter("pid");
		String did=request.getParameter("did");
		String cid=(String)request.getSession().getAttribute("cid");
		List<OrderSingleDishVO> singleList=new ArrayList<OrderSingleDishVO>();
		String[] sidList=request.getParameterValues("sid");
		if(sidList==null) {
			sidList=new String[0];
		}
		for(String sid:sidList) {
			Boolean isExist=false;
			for(int i=0;i<singleList.size();i++) {
				OrderSingleDishVO single=singleList.get(i);
				if(single.getSid().equals(sid)) {
					single.setNum(single.getNum()+1.0);
					singleList.set(i, single);
					isExist=true;
					break;
				}
			}
			if(!isExist) {
				OrderSingleDishVO single=new OrderSingleDishVO();
				single.setSid(sid);
				single.setNum(1.0);
				singleList.add(single);
			}
		}
		List<OrderMultipleDishVO> multipleList=new ArrayList<OrderMultipleDishVO>();
		String[] midList=request.getParameterValues("mid");
		if(midList==null) {
			midList=new String[0];
		}
		for(String mid:midList) {
			Boolean isExist=false;
			for(int i=0;i<multipleList.size();i++) {
				OrderMultipleDishVO multiple=multipleList.get(i);
				if(multiple.getMid().equals(mid)) {
					multiple.setNum(multiple.getNum()+1.0);
					multipleList.set(i, multiple);
					isExist=true;
					break;
				}
			}
			if(!isExist) {
				OrderMultipleDishVO multiple=new OrderMultipleDishVO();
				multiple.setMid(mid);
				multiple.setNum(1.0);
				multipleList.add(multiple);
			}
		}
		DishOrderVO order=new DishOrderVO();
		order.setCaid(caid);
		order.setCid(cid);
		order.setDid(did);
		order.setOrderMultipleDishList(multipleList);
		order.setOrderSingleDishList(singleList);
		order.setPid(pid);
		OrderService service=new OrderService();
		service.create(order);		
		
		response.sendRedirect(request.getContextPath()+"/consumer/order/index");
	}

}
