package vo;

import javax.persistence.ManyToOne;

import po.DishOrderPO;
import po.ProviderSingleDishPO;

public class OrderSingleDishVO {


	String sid;
	Double num;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	
	
}
