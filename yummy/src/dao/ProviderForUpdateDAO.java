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

public class ProviderForUpdateDAO extends BaseDAO<ProviderForUpdatePO> {

	ProviderDAO providerDAO=new ProviderDAO();
	public ProviderForUpdatePO createProviderForUpdate(ProviderVO provider) {
		ProviderForUpdatePO providerPO=new ProviderForUpdatePO();
		
		Site site=provider.getSite();
		ProviderState state=ProviderState.Updated;
		AccountPO account=new AccountPO();
		account.setAid(Integer.valueOf(provider.getAid()));
		
		providerPO.setAccount(account);
		providerPO.setPassword(provider.getPassword());
		providerPO.setSite(provider.getSite());
		providerPO.setState(state);
		providerPO.setType(provider.getType());
		return providerPO;
	}
	
}
