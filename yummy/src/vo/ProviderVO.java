package vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import eo.ProviderState;
import eo.ProviderType;
import eo.Site;
import po.AccountPO;
import po.ProviderDiscountPO;
import po.ProviderMultipleDishPO;
import po.ProviderSingleDishPO;

public class ProviderVO {

	Site site;
	ProviderType type;
	String password;
	String aid;
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public ProviderType getType() {
		return type;
	}
	public void setType(ProviderType type) {
		this.type = type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}

	
	
}
