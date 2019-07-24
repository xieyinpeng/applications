package po;

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
import javax.persistence.SequenceGenerator;

import eo.Site;

@Entity(name="consumerAddress")
public class ConsumerAddressPO {

	@Id
	@Column(length=7)
	@SequenceGenerator(name = "caidGenerator",initialValue=1000000,allocationSize=1)
	@GeneratedValue(generator="caidGenerator") 
	int caid;
	//@ManyToOne(fetch = FetchType.EAGER)
	//ConsumerPO consumer;
	@Enumerated(EnumType.STRING)
	Site site;
	
	
	public int getCaid() {
		return caid;
	}
	public void setCaid(int caid) {
		this.caid = caid;
	}
	/*
	public ConsumerPO getConsumer() {
		return consumer;
	}
	public void setConsumer(ConsumerPO consumer) {
		this.consumer = consumer;
	}
	*/
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}

	
	
}

/*
create table consumerAddress(
cid varchar(7),
address enum(????),
foreign key (cid) references account(cid)
);
*/