package vo;

import java.util.Date;

import eo.ProviderDiscountType;

public class ProviderDiscountForPresentVO {
	
	String did;
	String pid;
	String summary;
	Double moneyMin;
	Double moneyMax;
	Date dateBegin;
	Date dateEnd;
	int levelMin;
	int levelMax;
	ProviderDiscountType type;
	Double scale;
	
	
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public Double getMoneyMin() {
		return moneyMin;
	}
	public void setMoneyMin(Double moneyMin) {
		this.moneyMin = moneyMin;
	}
	public Double getMoneyMax() {
		return moneyMax;
	}
	public void setMoneyMax(Double moneyMax) {
		this.moneyMax = moneyMax;
	}
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public int getLevelMin() {
		return levelMin;
	}
	public void setLevelMin(int levelMin) {
		this.levelMin = levelMin;
	}
	public int getLevelMax() {
		return levelMax;
	}
	public void setLevelMax(int levelMax) {
		this.levelMax = levelMax;
	}
	public ProviderDiscountType getType() {
		return type;
	}
	public void setType(ProviderDiscountType type) {
		this.type = type;
	}
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	
	
	
	
}
