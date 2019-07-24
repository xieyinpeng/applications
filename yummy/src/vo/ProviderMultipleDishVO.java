package vo;

import java.util.Date;
import java.util.List;

public class ProviderMultipleDishVO {

	String summary;
	Double price;
	Double limit;
	Date dateBegin;
	Date dateEnd;
	List<String> sidList;
	
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
	public Double getLimit() {
		return limit;
	}
	public void setLimit(Double limit) {
		this.limit = limit;
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
	public List<String> getSidList() {
		return sidList;
	}
	public void setSidList(List<String> sidList) {
		this.sidList = sidList;
	}
	
	
	
	
}
