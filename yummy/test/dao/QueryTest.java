package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import service.ProviderStatisticsService;
import vo.ProviderSingleDishForPresentVO;

class QueryTest {

	@Test
	void testProvider() {
		ProviderDAO dao=new ProviderDAO();
		int pid=dao.query("where account_aid="+1).get(0).getPid(); 
	}
	
	@Test
	void testOrder() {
		DishOrderDAO dao=new DishOrderDAO();
		dao.query("").get(0); 
	}
	
	@Test
	void testProviderSingle(){
		ProviderStatisticsService service=new ProviderStatisticsService();
		List<ProviderSingleDishForPresentVO> list=service.getSingleDishList();
		int a=1+1;
	}

}
