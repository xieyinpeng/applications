package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProviderStatisticsService;
import vo.ProviderNumByTypeVO;


@WebServlet("/manager/provider/present")
public class ManagerProviderPresentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProviderStatisticsService service=new ProviderStatisticsService();
		List<ProviderNumByTypeVO> numList=service.getProviderNumByTypeList();
		request.setAttribute("numList", numList);
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/jsp/manager/provider/present.jsp");
		dispatcher.include(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
