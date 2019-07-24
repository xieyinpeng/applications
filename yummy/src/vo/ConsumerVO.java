package vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import eo.ConsumerState;
import po.AccountPO;
import po.ConsumerAddressPO;

public class ConsumerVO {

	String password;
	String name;
	String phoneNumber;
	String email;
	String aid;
	List<ConsumerAddressVO> consumerAddressList;
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
	
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public List<ConsumerAddressVO> getConsumerAddressList() {
		return consumerAddressList;
	}
	public void setConsumerAddressList(List<ConsumerAddressVO> consumerAddressList) {
		this.consumerAddressList = consumerAddressList;
	}
	
	
}
