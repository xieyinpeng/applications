package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.AccountDAO;
import eo.ProviderDiscountType;
import po.AccountPO;
import po.ProviderDiscountPO;
import utils.HibernateUtil;

public class SystemService {

	AccountDAO accountDAO=new AccountDAO();
	SecurityService securityService=new SecurityService();
	
	public void init1() {
		
		AccountPO consumerAccount=new AccountPO();
		consumerAccount.setBalance(1000000.0);
		consumerAccount.setPassword(securityService.md5Encrypt("xyp2016"));
		
		AccountPO providerAccount=new AccountPO();
		providerAccount.setBalance(1000000.0);
		providerAccount.setPassword(securityService.md5Encrypt("xyp2016"));
		
		
		AccountPO managerAccount=new AccountPO();
		managerAccount.setBalance(1000000.0);
		managerAccount.setPassword(securityService.md5Encrypt("xyp2016"));
		
		accountDAO.save(consumerAccount);
		accountDAO.save(providerAccount);
		accountDAO.save(managerAccount);
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		ProviderDiscountPO discount=new ProviderDiscountPO();
		discount.setDateBegin(new Date(0));
		try {
			discount.setDateEnd(new SimpleDateFormat("yyyy-mm-dd").parse("9999-12-31"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		discount.setLevelMax(Integer.MAX_VALUE);
		discount.setLevelMin(0);
		discount.setMoneyMax(Double.MAX_VALUE);
		discount.setMoneyMin(0.0);
		discount.setScale(1.0);
		discount.setSummary("no discount");
		discount.setType(ProviderDiscountType.Total);
		try {
			session.saveOrUpdate(discount);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public void init2() {
		
	}
}
