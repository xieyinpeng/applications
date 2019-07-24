package service;


public class ManagerService {
	
	private static String  mid="1000000";
	private static String password="xyp2016";
	private static String aid="1000002";
	private static Double transferScale=0.8;
	
	
	public Boolean checkAuthority(String mid,String password) {
		if(mid.equals(this.mid)&&password.equals(this.password)) {
			return true;
		}
		return false;
	};
	
	public String getAccountId() {
		return aid;
	}
	
	public Double getTransferScale() {
		return transferScale;
	}
	
}
