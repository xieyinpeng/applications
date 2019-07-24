package po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="orderSingleDish")
public class OrderSingleDishPO {

	@Id
	@Column(length=7)
	@SequenceGenerator(name = "osidGenerator",initialValue=1000000,allocationSize=1)
	@GeneratedValue(generator="osidGenerator") 
	int osid;
	@ManyToOne(fetch = FetchType.EAGER)
	ProviderSingleDishPO providerSingleDish;
	Double num;
	/*
	@ManyToOne(fetch = FetchType.EAGER)
	DishOrderPO dishOrder;
	*/
	
	public void setOsid(int osid) {
		this.osid = osid;
	}
	public int getOsid() {
		return osid;
	}
	public ProviderSingleDishPO getProviderSingleDish() {
		return providerSingleDish;
	}
	public void setProviderSingleDish(ProviderSingleDishPO providerSingleDish) {
		this.providerSingleDish = providerSingleDish;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	/*
	public DishOrderPO getDishOrder() {
		return dishOrder;
	}
	public void setDishOrder(DishOrderPO dishOrder) {
		this.dishOrder = dishOrder;
	}
	*/

	
	
}

/*
create table orderSingleDish(
sid varchar(7),
num decimal(20,2),
oid varchar(7),
foreign key(sid) references providerSingleDish(sid),
foreign key(oid) references order(oid)
);
*/