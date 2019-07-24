package service;

import java.util.ArrayList;
import java.util.List;

import dao.DishOrderDAO;
import po.DishOrderPO;
import po.OrderMultipleDishPO;
import po.OrderSingleDishPO;
import vo.AccountTransferVO;
import vo.OrderForPresentVO;

public class OrderStatisticsService {

	DishOrderDAO orderDAO=new DishOrderDAO();
	AccountStatisticsService accountService=new AccountStatisticsService();
	
	public List<OrderForPresentVO> getListByConsumerForProvider(String pid) {
		List<DishOrderPO> orderPOList=orderDAO.query("where provider.pid="+pid+" order by consumer.cid");
		return PO2PresentVO(orderPOList);
	};
	
	public List<OrderForPresentVO> getListByDateForProvider(String pid) {
		List<DishOrderPO> orderPOList=orderDAO.query("where provider.pid="+pid+" order by date");
		return PO2PresentVO(orderPOList);
	};
	
	public List<OrderForPresentVO> getListByMoneyForProvider(String pid) {
		List<DishOrderPO> orderPOList=orderDAO.query("where provider.pid="+pid);
		return sortByMoney(PO2PresentVO(orderPOList));
	};

	public List<OrderForPresentVO> getCurrentListForProvider(String pid){
		List<DishOrderPO> orderPOList=orderDAO.query("where provider.pid="+pid+" and (orderState='Paid' or orderState='Created')");
		return PO2PresentVO(orderPOList);
	}
	
	
	public List<OrderForPresentVO> getListByDateForConsumer(String cid) {
		List<DishOrderPO> orderPOList=orderDAO.query("where consumer.cid="+cid+" order by date");
		return PO2PresentVO(orderPOList);
	};
	
	public List<OrderForPresentVO> getListByMoneyForConsumer(String cid) {
		List<DishOrderPO> orderPOList=orderDAO.query("where consumer.cid="+cid);
		return sortByMoney(PO2PresentVO(orderPOList));
	};
	
	public List<OrderForPresentVO> getListByProviderForConsumer(String cid) {
		List<DishOrderPO> orderPOList=orderDAO.query("where consumer.cid="+cid+" order by provider.pid");
		return PO2PresentVO(orderPOList);
	};
	
	public List<OrderForPresentVO> getCurrentListForConsumer(String cid){
		List<DishOrderPO> orderPOList=orderDAO.query("where consumer.cid="+cid+" and (orderState='Paid' or orderState='Created')");
		return PO2PresentVO(orderPOList);
	}
	
	
	public AccountTransferVO getTransfer(String oid) {
		DishOrderDAO dao=new DishOrderDAO();
		DishOrderPO orderPO=dao.query("where oid="+oid).get(0);
		
		String aid_from=String.valueOf(orderPO.getConsumer().getAccount().getAid());
		String aid_to=String.valueOf(orderPO.getProvider().getAccount().getAid());
		Double money=calculateMoney(orderPO);
		
		AccountTransferVO transfer=accountService.createTransfer(aid_from, aid_to, money);
		return transfer;
	}
	
	public Double calculateMoney(DishOrderPO orderPO) {
		Double money=0.0;
		List<OrderSingleDishPO> orderSingleList=orderPO.getOrderSingleDishList();
		for(OrderSingleDishPO orderSingle:orderSingleList) {
			money+=orderSingle.getNum()*orderSingle.getProviderSingleDish().getPrice();
		}
		List<OrderMultipleDishPO> orderMultipleList=orderPO.getOrderMultipleDishList();
		for(OrderMultipleDishPO orderMultiple:orderMultipleList) {
			money+=orderMultiple.getNum()*orderMultiple.getProviderMultipleDish().getPrice();
		}
		if(orderPO.getProviderDiscount()!=null) {
			money*=orderPO.getProviderDiscount().getScale();
		}
		
		return money;
	}
	
	private OrderForPresentVO PO2PresentVO(DishOrderPO orderPO) {
		OrderForPresentVO order=new OrderForPresentVO();
		
		List<String> summaryList=new ArrayList<String>();
		List<OrderMultipleDishPO> multipleList=orderPO.getOrderMultipleDishList();
		for(OrderMultipleDishPO multiple:multipleList) {
			String summary="";
			summary+=multiple.getProviderMultipleDish().getSummary();
			summary+="X"+multiple.getNum();
			summaryList.add(summary);
		}
		List<OrderSingleDishPO>singleList=orderPO.getOrderSingleDishList();
		for(OrderSingleDishPO single:singleList) {
			String summary="";
			summary+=single.getProviderSingleDish().getSummary();
			summary+="X"+single.getNum();
			summaryList.add(summary);
		}
		
		double money=calculateMoney(orderPO);
		
		order.setAddress(orderPO.getConsumerAddress().getSite().toString());
		order.setCid(String.valueOf(orderPO.getConsumer().getCid()));
		order.setDate(orderPO.getDate());
		order.setOid(String.valueOf(orderPO.getOid()));
		order.setPid(String.valueOf(orderPO.getProvider().getPid()));
		order.setState(orderPO.getOrderState());
		order.setDiscount(orderPO.getProviderDiscount().getSummary());
		order.setOrderDishSummaryList(summaryList);
		order.setMoney(money);
		return order;
	}

	private List<OrderForPresentVO> PO2PresentVO(List<DishOrderPO> orderPOList){
		List<OrderForPresentVO> orderList=new ArrayList<OrderForPresentVO>();
		for(DishOrderPO orderPO:orderPOList) {		
			orderList.add(PO2PresentVO(orderPO));
		}
		return orderList;
	}
	
	private List<OrderForPresentVO> sortByMoney(List<OrderForPresentVO> orderList){
		List<OrderForPresentVO> newList=new ArrayList<OrderForPresentVO>();
		int size=orderList.size();
		for(int i=0;i<size;i++) {
			OrderForPresentVO order=orderList.get(0);
			for(int i2=1;i2<orderList.size();i2++) {
				if(orderList.get(i2).getMoney()<order.getMoney()) {
					order=orderList.get(i2);
				}
			}
			orderList.remove(order);
			newList.add(order);
		}
		return newList;
	}
	
	
}
