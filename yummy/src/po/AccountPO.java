package po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="account")
public class AccountPO {

	@Id
	@Column(length=7)
	@SequenceGenerator(name = "aidGenerator",initialValue=1000000,allocationSize=1)
	@GeneratedValue(generator="aidGenerator") 
	int aid;
	String password;
	Double balance;
	
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	
}

/*
create table account(
aid varchar(7) primary key auto_increment,
password varchar(7),
balance decimal(20,2)
)auto_increment=1000000;
*/