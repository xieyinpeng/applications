package po;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import eo.ConsumerState;
import eo.Site;

@Entity(name="consumer")
public class ConsumerPO {

	@Id
	@Column(length=7)
	@SequenceGenerator(name = "cidGenerator",initialValue=1000000,allocationSize=1)
	@GeneratedValue(generator="cidGenerator")  
	int cid;
	String password;
	String name;
	String phoneNumber;
	String email;
	int level;
	@OneToOne(fetch = FetchType.EAGER)
	AccountPO account;
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT) 
	List<ConsumerAddressPO> consumerAddressList;
	@Enumerated(EnumType.STRING)
	ConsumerState consumerState;
	
	
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getCid() {
		return cid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public AccountPO getAccount() {
		return account;
	}
	public void setAccount(AccountPO account) {
		this.account = account;
	}
	public List<ConsumerAddressPO> getConsumerAddressList() {
		return consumerAddressList;
	}
	public void setConsumerAddressList(List<ConsumerAddressPO> consumerAddressList) {
		this.consumerAddressList = consumerAddressList;
	}
	public ConsumerState getConsumerState() {
		return consumerState;
	}
	public void setConsumerState(ConsumerState consumerState) {
		this.consumerState = consumerState;
	}

	
	
}

/*
create table consumer(
cid varchar(7) primary key auto_increment,
password varchar(20),
name varchar(20),
phoneNumber varchar(20),
email varchar(20),
level int,
aid varchar(7),
state enum(?????),
foreign key (aid) references account(aid)
)auto_increment=1000000;
*/