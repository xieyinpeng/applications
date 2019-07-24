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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="providerSingleDish")
public class ProviderSingleDishPO {

	String summary;
	Double price;
	Double limitNum;
	@Id
	@Column(length=7)
	@SequenceGenerator(name = "sidGenerator",initialValue=1000000,allocationSize=1)
	@GeneratedValue(generator="sidGenerator") 
	int sid;
	/*
	@ManyToOne(fetch = FetchType.EAGER)
	ProviderPO provider;
	*/
	@Temporal(TemporalType.TIMESTAMP)
	Date dateBegin;
	@Temporal(TemporalType.TIMESTAMP)
	Date dateEnd;
	
	
	public void setSid(int sid) {
		this.sid = sid;
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
	public int getSid() {
		return sid;
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
	
	
}


/*
create table providerMultipleDish(
summary varchar(20),
price decimal(20,2),
limit decimal(20,2),
sid varchar(7) primary key auto_increment,
pid varchar(7),
dateBegin timestamp,
dateEnd timestamp,
foreign key(pid) references provider(pid)
)auto_increment=1000000;
*/