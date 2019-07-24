package dao;

import java.util.ArrayList;
import java.util.List;

import eo.ConsumerState;
import eo.Site;
import po.AccountPO;
import po.ConsumerAddressPO;
import po.ConsumerPO;
import vo.ConsumerAddressVO;
import vo.ConsumerVO;

public class ConsumerDAO extends BaseDAO<ConsumerPO>{
	
	public ConsumerPO createConsumerPO(ConsumerVO consumer) {
		AccountPO account=new AccountPO();
		account.setAid(Integer.valueOf(consumer.getAid()));
		List<ConsumerAddressPO> addressPOList=new ArrayList<ConsumerAddressPO>();
		List<ConsumerAddressVO> addressList=consumer.getConsumerAddressList();
		for(ConsumerAddressVO address:addressList) {
			ConsumerAddressPO addressPO=new ConsumerAddressPO();
			addressPO.setSite(address.getSite());
			addressPOList.add(addressPO);
		}
		ConsumerState consumerState=ConsumerState.Registered;
		String email=consumer.getEmail();
		int level=1;
		String name=consumer.getName();
		String password=consumer.getPassword();
		String phoneNumber=consumer.getPhoneNumber();
		
		ConsumerPO consumerPO=new ConsumerPO();
		consumerPO.setAccount(account);
		consumerPO.setConsumerAddressList(addressPOList);
		consumerPO.setConsumerState(consumerState);
		consumerPO.setEmail(email);
		consumerPO.setLevel(level);
		consumerPO.setName(name);
		consumerPO.setPassword(password);
		consumerPO.setPhoneNumber(phoneNumber);
		return consumerPO;
	}
	
	public ConsumerAddressPO getAddress(int cid,int caid) {
		ConsumerPO consumerPO=this.query("where cid="+cid).get(0);
		ConsumerAddressPO target=null;
		for(ConsumerAddressPO addressPO:consumerPO.getConsumerAddressList()) {
			if(addressPO.getCaid()==caid){
				target=addressPO;
				break;
			}
		}
		return target;
	}
}
