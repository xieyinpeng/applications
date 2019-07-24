package dao;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import eo.ConsumerState;
import eo.DishOrderState;
import eo.ProviderDiscountType;
import eo.ProviderState;
import eo.ProviderType;
import eo.Site;
import po.AccountPO;
import po.AccountTransferPO;
import po.ConsumerAddressPO;
import po.ConsumerPO;
import po.DishOrderPO;
import po.OrderMultipleDishPO;
import po.OrderSingleDishPO;
import po.ProviderDiscountPO;
import po.ProviderMultipleDishPO;
import po.ProviderPO;
import po.ProviderSingleDishPO;

class SaveTest {

	@Test
	void testAccount() {
		AccountPO account=new AccountPO();
		account.setBalance(1.0);
		account.setPassword("1");
		
		AccountDAO dao=new AccountDAO();
		dao.save(account);
	}
	
	@Test
	void testTransfer() {
		AccountTransferDAO dao=new AccountTransferDAO();
		AccountTransferPO transfer=new AccountTransferPO();
		transfer.setMoney(1.0);

		AccountPO account1=new AccountPO();
		AccountPO account2=new AccountPO();
		account1.setAid(1);
		account2.setAid(1);
		transfer.setAccount_from(account1);
		transfer.setAccount_to(account2);
		transfer.setDate(new Timestamp(2000,1,1,1,1,1,1));
		
		dao.save(transfer);
	}
	
	@Test
	void testConsumer() {
		ConsumerPO consumer=new ConsumerPO();
		consumer.setEmail("1");
		consumer.setLevel(1);
		consumer.setName("1");
		consumer.setPassword("1");
		consumer.setPhoneNumber("1");
		
		AccountPO account=new AccountPO();
		account.setAid(1);
		consumer.setAccount(account);
		
		List<ConsumerAddressPO> addressList=new ArrayList<ConsumerAddressPO>();
		ConsumerAddressPO address=new ConsumerAddressPO();
		address.setSite(Site.A);
		addressList.add(address);
		consumer.setConsumerAddressList(addressList);

		consumer.setConsumerState(ConsumerState.Registered);
		
		ConsumerDAO dao=new ConsumerDAO();
		dao.save(consumer);
		
	}
	
	@Test
	void testOrder() {
		DishOrderPO order=new DishOrderPO();
		order.setDate(new Date(111));
		order.setOrderState(DishOrderState.Created);
		
		ConsumerPO consumer=new ConsumerPO();
		consumer.setCid(6);
		order.setConsumer(consumer);
		
		ConsumerAddressPO address=new ConsumerAddressPO();
		address.setCaid(7);
		order.setConsumerAddress(address);

		List<OrderMultipleDishPO> orderMultipleList=new ArrayList<OrderMultipleDishPO>();
		OrderMultipleDishPO orderMultiple=new OrderMultipleDishPO();
		orderMultiple.setNum(1.0);
		ProviderMultipleDishPO multiple=new ProviderMultipleDishPO();
		multiple.setMid(11);
		orderMultiple.setProviderMultipleDish(multiple);
		orderMultipleList.add(orderMultiple);
		order.setOrderMultipleDishList(orderMultipleList);
		
		List<OrderSingleDishPO> orderSingleList=new ArrayList<OrderSingleDishPO>();
		OrderSingleDishPO orderSingle=new OrderSingleDishPO();
		orderSingle.setNum(1.0);
		ProviderSingleDishPO single=new ProviderSingleDishPO();
		single.setSid(10);
		orderSingle.setProviderSingleDish(single);
		orderSingleList.add(orderSingle);
		order.setOrderSingleDishList(orderSingleList);

		ProviderPO provider=new ProviderPO();
		provider.setPid(8);
		order.setProvider(provider);
		
		ProviderDiscountPO discount=new ProviderDiscountPO();
		discount.setDid(9);
		order.setProviderDiscount(discount);
		
		DishOrderDAO dao=new DishOrderDAO();
		dao.save(order);
	}
	
	@Test
	void testProvider() {
		ProviderPO provider=new ProviderPO();
		provider.setPassword("1");
		provider.setSite(Site.A);
		provider.setState(ProviderState.Registered);
		provider.setType(ProviderType.A);
		
		AccountPO account=new AccountPO();
		account.setAid(1);
		provider.setAccount(account);

		List<ProviderDiscountPO> discountList=new ArrayList<ProviderDiscountPO>();
		ProviderDiscountPO discount=new ProviderDiscountPO();
		discount.setDateBegin(new Date(1));
		discount.setDateEnd(new Date(1111111111));
		discount.setLevelMax(100);
		discount.setLevelMin(0);
		discount.setMoneyMax(100.0);
		discount.setMoneyMin(0.0);
		discount.setScale(0.8);
		discount.setSummary("1");
		discount.setType(ProviderDiscountType.Total);
		discountList.add(discount);
		provider.setProviderDiscountList(discountList);
		
		List<ProviderSingleDishPO> singleList=new ArrayList<ProviderSingleDishPO>();
		ProviderSingleDishPO single=new ProviderSingleDishPO();
		single.setDateBegin(new Date(1));
		single.setDateEnd(new Date(11111111));
		single.setLimitNum(100.0);
		single.setPrice(1.0);
		single.setSummary("1");
		singleList.add(single);
		provider.setProviderSingleDishList(singleList);
		
		ProviderDAO dao=new ProviderDAO();
		dao.save(provider);
		
		List<ProviderMultipleDishPO> multipleList=new ArrayList<ProviderMultipleDishPO>();
		ProviderMultipleDishPO multiple=new ProviderMultipleDishPO();
		multiple.setDateBegin(new Date(1));
		multiple.setDateEnd(new Date(11111111));
		multiple.setLimitNum(100.0);
		multiple.setPrice(1.0);
		multiple.setSummary("1");
		multiple.setProviderSingleDishList(singleList);
		multipleList.add(multiple);
		provider.setProviderMultipleDishList(multipleList);
		
		dao.save(provider);

	}

}
