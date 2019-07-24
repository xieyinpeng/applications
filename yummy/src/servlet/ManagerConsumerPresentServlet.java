package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ConsumerStatisticsService;
import service.ProviderStatisticsService;
import vo.ConsumerNumByLevelVO;
import vo.ProviderNumByTypeVO;


@WebServlet("/manager/consumer/present")
public class ManagerConsumerPresentServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConsumerStatisticsService service=new ConsumerStatisticsService();
		List<ConsumerNumByLevelVO> numList=service.getConsumerNumByLevelList();
		request.setAttribute("numList", numList);
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/manager/consumer/present.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
