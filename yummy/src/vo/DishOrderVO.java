package vo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import eo.DishOrderState;
import po.ConsumerAddressPO;
import po.ConsumerPO;
import po.OrderMultipleDishPO;
import po.OrderSingleDishPO;
import po.ProviderDiscountPO;
import po.ProviderPO;

public class DishOrderVO {


	String cid;
	List<OrderSingleDishVO> OrderSingleDishList;
	List<OrderMultipleDishVO> OrderMultipleDishList;
	String did;//discount
	String pid;//provider
	String caid;//consumeraddress
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public List<OrderSingleDishVO> getOrderSingleDishList() {
		return OrderSingleDishList;
	}
	public void setOrderSingleDishList(List<OrderSingleDishVO> orderSingleDishList) {
		OrderSingleDishList = orderSingleDishList;
	}
	public List<OrderMultipleDishVO> getOrderMultipleDishList() {
		return OrderMultipleDishList;
	}
	public void setOrderMultipleDishList(List<OrderMultipleDishVO> orderMultipleDishList) {
		OrderMultipleDishList = orderMultipleDishList;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCaid() {
		return caid;
	}
	public void setCaid(String caid) {
		this.caid = caid;
	}
	
	
	
	
	
}
