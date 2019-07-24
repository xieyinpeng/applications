package dao;

import java.util.ArrayList;
import java.util.List;

import eo.ProviderState;
import eo.ProviderType;
import eo.Site;
import po.AccountPO;
import po.ProviderDiscountPO;
import po.ProviderForUpdatePO;
import po.ProviderMultipleDishPO;
import po.ProviderPO;
import po.ProviderSingleDishPO;
import vo.ProviderDiscountVO;
import vo.ProviderMultipleDishVO;
import vo.ProviderSingleDishVO;
import vo.ProviderVO;

public class ProviderDAO extends BaseDAO<ProviderPO>{
	
	
	public ProviderPO createProvider(ProviderVO provider) {
		AccountPO account=new AccountPO();
		account.setAid(Integer.valueOf(provider.getAid()));
		String password=provider.getPassword();
		List<ProviderDiscountPO> discountPOList=new ArrayList<ProviderDiscountPO>();
		List<ProviderMultipleDishPO> multiplePOList=new ArrayList<ProviderMultipleDishPO>();
		List<ProviderSingleDishPO> singlePOList=new ArrayList<ProviderSingleDishPO>();
		
		Site site=provider.getSite();
		ProviderState state=ProviderState.Registered;
		ProviderType type=provider.getType();
		
		ProviderPO providerPO=new ProviderPO();
		providerPO.setAccount(account);
		providerPO.setPassword(password);
		providerPO.setProviderDiscountList(discountPOList);
		providerPO.setProviderMultipleDishList(multiplePOList);
		providerPO.setProviderSingleDishList(singlePOList);
		providerPO.setSite(site);
		providerPO.setState(state);
		providerPO.setType(type);
		return providerPO;
	}

	public ProviderPO createProvider(ProviderForUpdatePO provider) {
		ProviderPO providerPO=query("where pid="+provider.getPid()).get(0);
		providerPO.setAccount(provider.getAccount());
		providerPO.setPassword(provider.getPassword());
		providerPO.setSite(provider.getSite());
		providerPO.setState(provider.getState());
		providerPO.setType(provider.getType());
		return providerPO;
	}
	
	public ProviderSingleDishPO createSingleDish(ProviderSingleDishVO single) {
		ProviderSingleDishPO singlePO=new ProviderSingleDishPO();
		singlePO.setDateBegin(single.getDateBegin());
		singlePO.setDateEnd(single.getDateEnd());
		singlePO.setLimitNum(single.getLimit());
		singlePO.setPrice(single.getPrice());
		singlePO.setSummary(single.getSummary());
		return singlePO;
	};
	
	public ProviderMultipleDishPO createMultipleDish(ProviderMultipleDishVO multiple) {
		ProviderMultipleDishPO multiplePO=new ProviderMultipleDishPO();
		multiplePO.setDateBegin(multiple.getDateBegin());
		multiplePO.setDateEnd(multiple.getDateEnd());
		multiplePO.setLimitNum(multiple.getLimit());
		multiplePO.setPrice(multiple.getPrice());
		multiplePO.setSummary(multiple.getSummary());
		List<ProviderSingleDishPO> singlePOList=new ArrayList<ProviderSingleDishPO>();
		List<String> sidList=multiple.getSidList();
		for(String sid:sidList) {
			ProviderSingleDishPO singlePO=new ProviderSingleDishPO();
			singlePO.setSid(Integer.valueOf(sid));
			singlePOList.add(singlePO);
		}
		multiplePO.setProviderSingleDishList(singlePOList);
		return multiplePO;
	};
	
	public ProviderDiscountPO createDiscount(ProviderDiscountVO discount) {
		ProviderDiscountPO discountPO=new ProviderDiscountPO();
		discountPO.setDateBegin(discount.getDateBegin());
		discountPO.setDateEnd(discount.getDateEnd());
		discountPO.setLevelMax(discount.getLevelMax());
		discountPO.setLevelMin(discount.getLevelMin());
		discountPO.setMoneyMax(discount.getMoneyMax());
		discountPO.setMoneyMin(discount.getMoneyMin());
		discountPO.setScale(discount.getScale());
		discountPO.setSummary(discount.getSummary());
		discountPO.setType(discount.getType());
		return discountPO;
	};
	
	public ProviderDiscountPO getDiscount(int pid,int did) {
		ProviderPO providerPO=query("where pid="+pid).get(0);
		List<ProviderDiscountPO> discountPOList=providerPO.getProviderDiscountList();
		ProviderDiscountPO discountPOTarget = null;
		for(ProviderDiscountPO discountPO:discountPOList) {
			if(discountPO.getDid()==did) {
				discountPOTarget=discountPO;
				break;
			}
		}
		System.out.print("no this discount");
		return discountPOTarget;
	}
	
	public void save(int pid,ProviderMultipleDishPO target) {
		ProviderPO providerPO=query("where pid="+pid).get(0);
		List<ProviderMultipleDishPO> multipleList=providerPO.getProviderMultipleDishList();
		for(ProviderMultipleDishPO multiple:multipleList) {
			if(multiple.getMid()==target.getMid()) {
				multipleList.remove(multiple);
				multipleList.add(target);
				break;
			}
		}
		providerPO.setProviderMultipleDishList(multipleList);
		save(providerPO);
	}
	
	public void save(int pid,ProviderSingleDishPO target) {
		ProviderPO providerPO=query("where pid="+pid).get(0);
		List<ProviderSingleDishPO> singleList=providerPO.getProviderSingleDishList();
		for(ProviderSingleDishPO single:singleList) {
			if(single.getSid()==target.getSid()) {
				singleList.remove(single);
				singleList.add(target);
				break;
			}
		}
		providerPO.setProviderSingleDishList(singleList);
		save(providerPO);
	}
	
}
