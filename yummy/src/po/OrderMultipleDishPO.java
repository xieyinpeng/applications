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

@Entity(name="orderMultipleDish")
public class OrderMultipleDishPO {

	@Id
	@Column(length=7)
	@SequenceGenerator(name = "omidGenerator",initialValue=1000000,allocationSize=1)
	@GeneratedValue(generator="omidGenerator")  
	int omid;
	@ManyToOne(fetch = FetchType.EAGER)
	ProviderMultipleDishPO providerMultipleDish;
	Double num;
	/*
	@ManyToOne(fetch = FetchType.EAGER)
	DishOrderPO dishOrder;
	*/
	
	public void setOmid(int omid) {
		this.omid = omid;
	}
	public int getOmid() {
		return omid;
	}
	public ProviderMultipleDishPO getProviderMultipleDish() {
		return providerMultipleDish;
	}
	public void setProviderMultipleDish(ProviderMultipleDishPO providerMultipleDish) {
		this.providerMultipleDish = providerMultipleDish;
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
create table orderMultipleDish(
mid varchar(7),
num decimal(20,2),
foreign key(mid) references providerMultipleDish(mid),
foreign key(oid) references order(oid)
);
*/