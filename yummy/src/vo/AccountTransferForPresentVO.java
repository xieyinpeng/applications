package vo;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import po.AccountPO;

public class AccountTransferForPresentVO {
	String tid;
	String aid_from;
	String aid_to;
	Double money;
	Date date;
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getAid_from() {
		return aid_from;
	}
	public void setAid_from(String aid_from) {
		this.aid_from = aid_from;
	}
	public String getAid_to() {
		return aid_to;
	}
	public void setAid_to(String aid_to) {
		this.aid_to = aid_to;
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
