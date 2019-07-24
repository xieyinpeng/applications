package po;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import eo.DishOrderState;

@Entity(name="dishorder")
public class DishOrderPO {

	@Id
	@Column(length=7)
	@SequenceGenerator(name = "oidGenerator",initialValue=1000000,allocationSize=1)
	@GeneratedValue(generator="oidGenerator") 
	int oid;
	@ManyToOne(fetch = FetchType.EAGER)
	ConsumerPO consumer;
	@Temporal(TemporalType.TIMESTAMP)
	Date date;
	@Enumerated(EnumType.STRING)
	DishOrderState orderState;
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT) 
	List<OrderSingleDishPO> OrderSingleDishList;
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT) 
	List<OrderMultipleDishPO> OrderMultipleDishList;
	@ManyToOne(fetch = FetchType.EAGER)
	ProviderDiscountPO providerDiscount;
	@ManyToOne(fetch = FetchType.EAGER)
	ProviderPO provider;
	@ManyToOne(fetch = FetchType.EAGER)
	ConsumerAddressPO consumerAddress;
	
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getOid() {
		return oid;
	}
	public ConsumerPO getConsumer() {
		return consumer;
	}
	public void setConsumer(ConsumerPO consumer) {
		this.consumer = consumer;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public DishOrderState getOrderState() {
		return orderState;
	}
	public void setOrderState(DishOrderState orderState) {
		this.orderState = orderState;
	}
	public List<OrderSingleDishPO> getOrderSingleDishList() {
		return OrderSingleDishList;
	}
	public void setOrderSingleDishList(List<OrderSingleDishPO> orderSingleDishList) {
		OrderSingleDishList = orderSingleDishList;
	}
	public List<OrderMultipleDishPO> getOrderMultipleDishList() {
		return OrderMultipleDishList;
	}
	public void setOrderMultipleDishList(List<OrderMultipleDishPO> orderMultipleDishList) {
		OrderMultipleDishList = orderMultipleDishList;
	}
	public ProviderDiscountPO getProviderDiscount() {
		return providerDiscount;
	}
	public void setProviderDiscount(ProviderDiscountPO providerDiscount) {
		this.providerDiscount = providerDiscount;
	}
	public ProviderPO getProvider() {
		return provider;
	}
	public void setProvider(ProviderPO provider) {
		this.provider = provider;
	}
	public ConsumerAddressPO getConsumerAddress() {
		return consumerAddress;
	}
	public void setConsumerAddress(ConsumerAddressPO consumerAddress) {
		this.consumerAddress = consumerAddress;
	}
	
	
	
}

/*
create table order(
oid varchar(7) primary key auto_increment,
cid varchar(7),
date timestamp,
orderState enum(????),
did varchar(7),
pid varchar(7),
aid varchar(7),
foreign key(cid) references consumer(cid),
foreign key(did) references providerDiscount(did),
foreign key(pid) references provider(pid),
foreign key(aid) references consumerAddress(aid)
)auto_increment=1000000;
*/