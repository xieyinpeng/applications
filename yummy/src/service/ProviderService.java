package service;

import java.util.List;

import dao.ProviderDAO;
import dao.ProviderForUpdateDAO;
import eo.ProviderState;
import po.ProviderDiscountPO;
import po.ProviderForUpdatePO;
import po.ProviderMultipleDishPO;
import po.ProviderPO;
import po.ProviderSingleDishPO;
import vo.ProviderDiscountVO;
import vo.ProviderMultipleDishVO;
import vo.ProviderSingleDishVO;
import vo.ProviderVO;

public class ProviderService {

	private SecurityService securityService=new SecurityService();
	private ProviderDAO providerDAO = new ProviderDAO();
	private ProviderForUpdateDAO providerForUpdateDAO = new ProviderForUpdateDAO();
	
	public void register(ProviderVO provider) {
		String passwordInDB=securityService.md5Encrypt(provider.getPassword());
		provider.setPassword(passwordInDB);
		ProviderPO providerPO=providerDAO.createProvider(provider);
		providerDAO.save(providerPO);
	};
	
	public void update(ProviderVO provider,String pid) {
		String passwordInDB=securityService.md5Encrypt(provider.getPassword());
		provider.setPassword(passwordInDB);
		ProviderForUpdatePO providerPO=providerForUpdateDAO.createProviderForUpdate(provider);
		providerPO.setPid(Integer.valueOf(pid));
		providerForUpdateDAO.save(providerPO);
	};
	
	public void publishSingleDish(ProviderSingleDishVO single,String pid) {
		ProviderSingleDishPO singlePO=providerDAO.createSingleDish(single);
		ProviderPO providerPO=providerDAO.query("where pid="+pid).get(0);
		List<ProviderSingleDishPO> singleList=providerPO.getProviderSingleDishList();
		singleList.add(singlePO);
		providerPO.setProviderSingleDishList(singleList);
		providerDAO.save(providerPO);		
	};
	
	public void publishMultipleDish(ProviderMultipleDishVO multiple,String pid) {
		ProviderMultipleDishPO multiplePO=providerDAO.createMultipleDish(multiple);
		ProviderPO providerPO=providerDAO.query("where pid="+pid).get(0);
		List<ProviderMultipleDishPO> multipleList=providerPO.getProviderMultipleDishList();
		multipleList.add(multiplePO);
		providerPO.setProviderMultipleDishList(multipleList);
		providerDAO.save(providerPO);	
	};
	
	public void publishDiscount(ProviderDiscountVO discount,String pid) {
		ProviderDiscountPO discountPO=providerDAO.createDiscount(discount);
		ProviderPO providerPO=providerDAO.query("where pid="+pid).get(0);
		List<ProviderDiscountPO> discountList=providerPO.getProviderDiscountList();
		discountList.add(discountPO);
		providerPO.setProviderDiscountList(discountList);
		providerDAO.save(providerPO);	
	};
	
	public Boolean checkAuthority(String pid,String password) {
		ProviderPO provider=providerDAO.query("where pid="+pid).get(0);
		String passwordInDB=provider.getPassword();
		Boolean passwordIsRight=securityService.md5Verify(password, passwordInDB);
		Boolean stateIsRight=provider.getState().equals(ProviderState.Authorized);
		if(passwordIsRight&&stateIsRight) {
			return true;
		}else {
			System.err.println("密码错误");
			return false;
		}
	};
	
	public void approve(String pid) {
		Boolean isUpdate=(providerForUpdateDAO.query("where pid="+pid).size()==1);
		if(!isUpdate) {
			approveRegister(pid);
		}
		if(isUpdate) {
			approveUpdate(pid);
		}
	}
	
	public void disapprove(String pid) {
		Boolean isUpdate=(providerForUpdateDAO.query("where pid="+pid).size()==1);
		if(!isUpdate) {
			disapproveRegister(pid);
		}
		if(isUpdate) {
			disapproveUpdate(pid);
		}
	}
	
	private void approveRegister(String pid) {
		ProviderPO provider=providerDAO.query("where pid="+pid).get(0);
		provider.setState(ProviderState.Authorized);
		providerDAO.save(provider);
	};
	
	private void approveUpdate(String pid) {
		ProviderForUpdatePO providerForPresent=providerForUpdateDAO.query("where pid="+pid).get(0);
		providerForPresent.setState(ProviderState.Authorized);
		ProviderPO provider=providerDAO.createProvider(providerForPresent);
		providerForUpdateDAO.delete("where pid="+pid);
		providerDAO.save(provider);
	};
	
	private void disapproveRegister(String pid) {
		ProviderPO provider=providerDAO.query("where pid="+pid).get(0);
		provider.setState(ProviderState.Logoffed);
		providerDAO.save(provider);
	};
	
	private void disapproveUpdate(String pid) {
		providerForUpdateDAO.delete("where pid="+pid);
	};
	
}
