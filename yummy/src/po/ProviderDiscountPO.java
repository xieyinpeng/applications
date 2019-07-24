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

import eo.ProviderDiscountType;

@Entity(name="providerDiscount")
public class ProviderDiscountPO {
/*
	@ManyToOne(fetch = FetchType.EAGER)
	ProviderPO provider;
	*/
	@Id
	@Column(length=7)
	@SequenceGenerator(name = "didGenerator",initialValue=1000000,allocationSize=1)
	@GeneratedValue(generator="didGenerator") 
	int did;
	Double moneyMin;
	Double moneyMax;
	int levelMin;
	int levelMax;
	@Enumerated(EnumType.STRING)
	ProviderDiscountType type;
	Double scale;
	String summary;
	@Temporal(TemporalType.TIMESTAMP)
	Date dateBegin;
	@Temporal(TemporalType.TIMESTAMP)
	Date dateEnd;
	
	
	public void setDid(int did) {
		this.did = did;
	}
	/*
	public ProviderPO getProvider() {
		return provider;
	}
	public void setProvider(ProviderPO provider) {
		this.provider = provider;
	}
	*/
	public int getDid() {
		return did;
	}
	public Double getMoneyMin() {
		return moneyMin;
	}
	public void setMoneyMin(Double moneyMin) {
		this.moneyMin = moneyMin;
	}
	public Double getMoneyMax() {
		return moneyMax;
	}
	public void setMoneyMax(Double moneyMax) {
		this.moneyMax = moneyMax;
	}
	public int getLevelMin() {
		return levelMin;
	}
	public void setLevelMin(int levelMin) {
		this.levelMin = levelMin;
	}
	public int getLevelMax() {
		return levelMax;
	}
	public void setLevelMax(int levelMax) {
		this.levelMax = levelMax;
	}
	public ProviderDiscountType getType() {
		return type;
	}
	public void setType(ProviderDiscountType type) {
		this.type = type;
	}
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
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
create table providerDiscount(
pid varchar(7),
did varchar(7) primary key auto_increment,
billMin decimal(20,2),
billMax decimal(20,2),
levelMin int,
levelMax int,
type enum(?????),
discountScale decimal(5,5);
summary varchar(20),
dateBegin timestamp,
dateEnd timestamp,
foreign key(pid) references provider(pid)
)auto_increment=1000000;
*/