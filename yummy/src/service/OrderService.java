package service;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dao.ConsumerDAO;
import dao.DishOrderDAO;
import dao.ProviderDAO;
import eo.DishOrderState;
import eo.Site;
import po.ConsumerAddressPO;
import po.ConsumerPO;
import po.DishOrderPO;
import po.OrderMultipleDishPO;
import po.OrderSingleDishPO;
import po.ProviderDiscountPO;
import po.ProviderMultipleDishPO;
import po.ProviderPO;
import po.ProviderSingleDishPO;
import vo.AccountTransferVO;
import vo.DishOrderVO;
import vo.OrderMultipleDishVO;
import vo.OrderSingleDishVO;

public class OrderService {

	DishOrderDAO orderDAO=new DishOrderDAO();
	OrderStatisticsService statisticsService=new OrderStatisticsService();
	AccountService accountService=new AccountService();
	ProviderDAO providerDAO=new ProviderDAO();
	ConsumerService consumerService=new ConsumerService();
	ConsumerDAO consumerDAO=new ConsumerDAO();
	GeoMapService mapService=new GeoMapService();
	ManagerService managerService=new ManagerService();
	
	public Double getCancelMoneyScale() {
		return 0.8;
	}
	
	public void create(DishOrderVO order) {
		if(!checkDiscount(order)) {
			System.err.println("不满足打折条件");
			return;
		}
		if(!checkDistance(order)) {
			System.err.println("距离不符合条件");
			return;
		}
		if(!checkLimit(order)) {
			System.err.println("所剩菜品不足");
			return;
		}
		DishOrderPO orderPO=orderDAO.createOrder(order);
		orderDAO.save(orderPO);
		int oid=orderPO.getOid();
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				DishOrderPO orderPO=orderDAO.query("where oid="+oid).get(0);
				DishOrderState state=orderPO.getOrderState();
				if(state.equals(DishOrderState.Created)) {
					OrderService orderService=new OrderService();
					orderService.cancel(String.valueOf(oid));
				}
			}
		}, 120000);
	};
	
	public void pay(String oid,String password) {
		AccountTransferVO transfer=statisticsService.getTransfer(oid);
		transfer.setAid_to(managerService.getAccountId());
		if(accountService.checkAuthority(transfer.getAid_from(), password)) {
			accountService.transfer(transfer);
		}else {
			System.err.println("银行账户密码错误");
			return;
		}
		
		DishOrderPO orderPO=orderDAO.query("where oid="+oid).get(0);
		orderPO.setOrderState(DishOrderState.Paid);
		orderDAO.save(orderPO);
	};
	
	public void cancel(String oid) {
		DishOrderPO orderPO=orderDAO.query("where oid="+oid).get(0);
		if(orderPO.getOrderState().equals(DishOrderState.Created)) {
			orderPO.setOrderState(DishOrderState.Canceled);
			orderDAO.save(orderPO);
		}else if(orderPO.getOrderState().equals(DishOrderState.Paid)) {
			AccountTransferVO transfer=statisticsService.getTransfer(oid);
			transfer.setAid_to(transfer.getAid_from());
			transfer.setAid_from(managerService.getAccountId());
			transfer.setMoney(transfer.getMoney()*this.getCancelMoneyScale());
			accountService.transfer(transfer);
			orderPO.setOrderState(DishOrderState.Canceled);
			orderDAO.save(orderPO);
		}else {
			System.err.println("这不是能取消的订单");
			return;
		}
	};
	
	public void receive(String oid) {
		DishOrderPO orderPO=orderDAO.query("where oid="+oid).get(0);
		if(!orderPO.getOrderState().equals(DishOrderState.Paid)) {
			System.err.println("这不是能签收的订单");
			return;
		}
		AccountTransferVO transfer=statisticsService.getTransfer(oid);
		transfer.setAid_from(managerService.getAccountId());
		accountService.transfer(transfer);
		orderPO.setOrderState(DishOrderState.Received);
		orderDAO.save(orderPO);
		
		ProviderPO provider=providerDAO.query("where pid="+orderPO.getProvider().getPid()).get(0);
		List<OrderMultipleDishPO> orderMultipleList=orderPO.getOrderMultipleDishList();
		for(OrderMultipleDishPO orderMultiplePO:orderMultipleList) {
			ProviderMultipleDishPO providerMultiplePO=orderMultiplePO.getProviderMultipleDish();
			providerMultiplePO.setLimitNum(providerMultiplePO.getLimitNum()-orderMultiplePO.getNum());
			providerDAO.save(provider.getPid(), providerMultiplePO);
		}
		List<OrderSingleDishPO> orderSingleList=orderPO.getOrderSingleDishList();
		for(OrderSingleDishPO orderSinglePO:orderSingleList) {
			ProviderSingleDishPO providerSinglePO=orderSinglePO.getProviderSingleDish();
			providerSinglePO.setLimitNum(providerSinglePO.getLimitNum()-orderSinglePO.getNum());
			providerDAO.save(provider.getPid(), providerSinglePO);
		}
		
		consumerService.upgrade(String.valueOf(orderPO.getConsumer().getCid()), statisticsService.calculateMoney(orderPO));
	};
	
	public Boolean checkDiscount(DishOrderVO order) {
		String pid=order.getPid();
		String did=order.getDid();
		ProviderDiscountPO discountPO = providerDAO.getDiscount(Integer.valueOf(pid),Integer.valueOf(did));
		if(discountPO==null) {
			return true;
		}
		Date begin=discountPO.getDateBegin();
		Date end=discountPO.getDateEnd();
		Date now=new Date();
		if(now.after(end)||now.before(begin)) {
			System.err.println("当前时间不符合条件");
			return false;
		}
		return true;
	}
	
	public Boolean checkDistance(DishOrderVO order) {
		String pid=order.getPid();
		String cid=order.getCid();
		String caid=order.getCaid();
		ProviderPO providerPO=providerDAO.query("where pid="+pid).get(0);
		ConsumerAddressPO consumerAddressPO=consumerDAO.getAddress(Integer.valueOf(cid),Integer.valueOf(caid));
		ConsumerPO consumerPO=consumerDAO.query("where cid="+cid).get(0);
		Site pa=providerPO.getSite();
		Site ca = consumerAddressPO.getSite();
		for(ConsumerAddressPO addressPO:consumerPO.getConsumerAddressList()) {
			if(String.valueOf(addressPO.getCaid()).equals(caid)){
				ca=addressPO.getSite();
			}
		}
		return mapService.isReachable(pa, ca);
	}
	
	public Boolean checkLimit(DishOrderVO order) {
		String pid=order.getPid();
		ProviderPO provider=providerDAO.query("where pid="+pid).get(0);
		List<ProviderMultipleDishPO> multiplePOList=provider.getProviderMultipleDishList();
		List<OrderMultipleDishVO>multipleVOList=order.getOrderMultipleDishList();
		for(OrderMultipleDishVO multipleVO:multipleVOList) {
			for(ProviderMultipleDishPO multiplePO:multiplePOList) {
				if(multiplePO.getMid()==Integer.valueOf(multipleVO.getMid())) {
					if(multiplePO.getLimitNum()<multipleVO.getNum()) {
						return false;
					}
				}
			}
		}
		List<ProviderSingleDishPO> singlePOList=provider.getProviderSingleDishList();
		List<OrderSingleDishVO>singleVOList=order.getOrderSingleDishList();
		for(OrderSingleDishVO singleVO:singleVOList) {
			for(ProviderSingleDishPO singlePO:singlePOList) {
				if(singlePO.getSid()==Integer.valueOf(singleVO.getSid())) {
					if(singlePO.getLimitNum()<singleVO.getNum()) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
