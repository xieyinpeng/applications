package vo;

import eo.ProviderType;
import eo.Site;

public class ProviderSingleDishForPresentVO {

	String sid;
	String summary;
	Double price;
	String pid;
	ProviderType type;
	Site site;
	Double limit;
	
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public ProviderType getType() {
		return type;
	}
	public void setType(ProviderType type) {
		this.type = type;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public Double getLimit() {
		return limit;
	}
	public void setLimit(Double limit) {
		this.limit = limit;
	}


}
