package vo;

import javax.persistence.ManyToOne;

import po.DishOrderPO;
import po.ProviderMultipleDishPO;

public class OrderMultipleDishVO {

	String mid;
	Double num;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	
	
}
