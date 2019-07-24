package vo;

import java.util.Date;
import java.util.List;

import eo.DishOrderState;


public class OrderForPresentVO {
	
	String oid;
	String cid;
	String pid;
	DishOrderState state;
	Date date;
	String address;
	String discount;
	List<String> orderDishSummaryList;
	Double money;
	
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public DishOrderState getState() {
		return state;
	}
	public void setState(DishOrderState state) {
		this.state = state;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public List<String> getOrderDishSummaryList() {
		return orderDishSummaryList;
	}
	public void setOrderDishSummaryList(List<String> orderDishSummaryList) {
		this.orderDishSummaryList = orderDishSummaryList;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
	
	
	
}
