package po;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name="providerMultipleDish")
public class ProviderMultipleDishPO {

	String summary;
	Double price;
	Double limitNum;
	@Id
	@Column(length=7)
	@SequenceGenerator(name = "midGenerator",initialValue=1000000,allocationSize=1)
	@GeneratedValue(generator="midGenerator") 
	int mid;
	/*
	@ManyToOne(fetch = FetchType.EAGER)
	ProviderPO provider;
	*/
	@Temporal(TemporalType.TIMESTAMP)
	Date dateBegin;
	@Temporal(TemporalType.TIMESTAMP)
	Date dateEnd;
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT) 
	List<ProviderSingleDishPO> providerSingleDishList;
	
	
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(Double limitNum) {
		this.limitNum = limitNum;
	}
	public int getMid() {
		return mid;
	}
	/*
	public ProviderPO getProvider() {
		return provider;
	}
	public void setProvider(ProviderPO provider) {
		this.provider = provider;
	}
	*/
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public List<ProviderSingleDishPO> getProviderSingleDishList() {
		return providerSingleDishList;
	}
	public void setProviderSingleDishList(List<ProviderSingleDishPO> providerSingleDishList) {
		this.providerSingleDishList = providerSingleDishList;
	}
}

/*
create table providerMultipleDish(
summary varchar(20),
price decimal(20,2),
limit decimal(20,2),
mid varchar(7) primary key auto_increment,
pid varchar(7),
dateBegin timestamp,
dateEnd timestamp,
foreign key(pid) references provider(pid)
)auto_increment=1000000;
*/