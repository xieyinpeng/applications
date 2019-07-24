package vo;

public class AccountTransferVO {
	String aid_from;
	String aid_to;
	Double money;
	
	public AccountTransferVO() {}
	
	public AccountTransferVO(AccountTransferVO transfer) {
		this.setAid_from(transfer.getAid_from());
		this.setAid_to(transfer.getAid_to());
		this.setMoney(transfer.getMoney());
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
	
	
}
