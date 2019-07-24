package po;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="accounttransfer")
public class AccountTransferPO {
	@Id
	@Column(length=7)
	@SequenceGenerator(name = "tidGenerator",initialValue=1000000,allocationSize=1)
	@GeneratedValue(generator="tidGenerator") 
	int tid;
	@ManyToOne(fetch = FetchType.EAGER)
	AccountPO account_from;
	@ManyToOne(fetch = FetchType.EAGER)
	AccountPO account_to;
	Double money;
	@Temporal(TemporalType.TIMESTAMP)
	Date date;
	
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public AccountPO getAccount_from() {
		return account_from;
	}
	public void setAccount_from(AccountPO account_from) {
		this.account_from = account_from;
	}
	public AccountPO getAccount_to() {
		return account_to;
	}
	public void setAccount_to(AccountPO account_to) {
		this.account_to = account_to;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
/*
create table accountTransfer(
tid varchar(7) primary key auto_increment,
aid_from varchar(7),
aid_to varchar(7),
money decimal(20,2),
date timestamp,
foreign key(aid_from) references acount(aid),
foreign key(aid_to) references acount(aid),
)auto_increment=1000000;
*/