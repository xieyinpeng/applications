package service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import vo.DishOrderVO;
import vo.OrderMultipleDishVO;
import vo.OrderSingleDishVO;

class OrderTest {

	@Test
	void testMake() {
		String caid;
		String cid;
		String did;
		String pid;
		List<OrderMultipleDishVO> multipleList=new ArrayList<OrderMultipleDishVO>();
		OrderMultipleDishVO multiple=new OrderMultipleDishVO();
		multiple.setMid("11");
		multiple.setNum(2.0);
		multipleList.add(multiple);
		List<OrderSingleDishVO> singleList=new ArrayList<OrderSingleDishVO>();
		OrderSingleDishVO single=new OrderSingleDishVO();
		single.setNum(3.0);
		single.setSid("10");
		singleList.add(single);
		
		DishOrderVO order=new DishOrderVO();
		order.setCaid("29");
		order.setCid("27");
		order.setDid("9");
		order.setOrderMultipleDishList(multipleList);
		order.setOrderSingleDishList(singleList);
		order.setPid("8");
		
		OrderService service=new OrderService();
		service.create(order);
		
	}
	
	@Test
	void testChange() {
		String oid="32";
		String password="xyp2016";
		
		OrderService service=new OrderService();
		service.cancel(oid);
		service.pay(oid,password);
		service.receive(oid);
	}
	
	
}
