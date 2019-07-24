package vo;

import eo.ProviderState;
import eo.ProviderType;
import eo.Site;


public class ProviderForPresentVO {

	String pid;
	Site site;
	ProviderType type;
	String password;
	String aid;
	ProviderState state;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
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
	public ProviderState getState() {
		return state;
	}
	public void setState(ProviderState state) {
		this.state = state;
	}
	
	
}
