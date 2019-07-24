package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eo.DishOrderState;
import po.ConsumerAddressPO;
import po.ConsumerPO;
import po.DishOrderPO;
import po.OrderMultipleDishPO;
import po.OrderSingleDishPO;
import po.ProviderDiscountPO;
import po.ProviderMultipleDishPO;
import po.ProviderPO;
import po.ProviderSingleDishPO;
import vo.DishOrderVO;
import vo.OrderForPresentVO;
import vo.OrderMultipleDishVO;
import vo.OrderSingleDishVO;

public class DishOrderDAO extends BaseDAO<DishOrderPO>{
	//????
	public DishOrderPO createOrder(DishOrderVO order) {
		ConsumerPO consumer=new ConsumerPO();
		consumer.setCid(Integer.valueOf(order.getCid()));
		ConsumerAddressPO address=new ConsumerAddressPO();
		address.setCaid(Integer.valueOf(order.getCaid()));
		Date date=new Date();
		List<OrderMultipleDishPO> orderMultiplePOList=new ArrayList<OrderMultipleDishPO>();
		List<OrderMultipleDishVO> orderMultipleList=order.getOrderMultipleDishList(); 
		for(OrderMultipleDishVO orderMultiple:orderMultipleList) {
			OrderMultipleDishPO orderMultiplePO=new OrderMultipleDishPO();
			ProviderMultipleDishPO providerMultiplePO=new ProviderMultipleDishPO();
			providerMultiplePO.setMid(Integer.valueOf(orderMultiple.getMid()));
			orderMultiplePO.setProviderMultipleDish(providerMultiplePO);
			orderMultiplePO.setNum(orderMultiple.getNum());
			orderMultiplePOList.add(orderMultiplePO);
		}
		List<OrderSingleDishPO> orderSinglePOList=new ArrayList<OrderSingleDishPO>();
		List<OrderSingleDishVO> orderSingleList=order.getOrderSingleDishList(); 
		for(OrderSingleDishVO orderSingle:orderSingleList) {
			OrderSingleDishPO orderSinglePO=new OrderSingleDishPO();
			ProviderSingleDishPO providerSinglePO=new ProviderSingleDishPO();
			providerSinglePO.setSid(Integer.valueOf(orderSingle.getSid()));
			orderSinglePO.setProviderSingleDish(providerSinglePO);
			orderSinglePO.setNum(orderSingle.getNum());
			orderSinglePOList.add(orderSinglePO);
		}
		DishOrderState orderState=DishOrderState.Created;
		ProviderPO provider=new ProviderPO();
		provider.setPid(Integer.valueOf(order.getPid()));
		ProviderDiscountPO discount=new ProviderDiscountPO();
		discount.setDid(Integer.valueOf(order.getDid()));
		
		DishOrderPO orderPO=new DishOrderPO();
		orderPO.setConsumer(consumer);
		orderPO.setConsumerAddress(address);
		orderPO.setDate(date);
		orderPO.setOrderMultipleDishList(orderMultiplePOList);
		orderPO.setOrderSingleDishList(orderSinglePOList);
		orderPO.setOrderState(orderState);
		orderPO.setProvider(provider);
		orderPO.setProviderDiscount(discount);
		return orderPO;
	}
}
