package service;

import java.util.ArrayList;
import java.util.List;

import dao.ConsumerDAO;
import eo.Site;
import po.ConsumerAddressPO;
import po.ConsumerPO;
import vo.ConsumerAddressForPresentVO;
import vo.ConsumerForPresentVO;
import vo.ConsumerNumByLevelVO;

public class ConsumerStatisticsService {

	private ConsumerDAO consumerDAO = new ConsumerDAO();
	
	public List<ConsumerForPresentVO> getList(){
		List<ConsumerPO> consumerPOList=consumerDAO.query("order by cid");
		List<ConsumerForPresentVO> consumerList=new ArrayList<ConsumerForPresentVO>();
		for(ConsumerPO consumerPO:consumerPOList) {
			ConsumerForPresentVO consumer=PO2PresentVO(consumerPO);
			consumerList.add(consumer);
		}
		
		return consumerList;
	}
	
	public List<ConsumerAddressForPresentVO> getAddressList(String cid){
		ConsumerPO consumerPO=consumerDAO.query("where cid="+cid).get(0);
		List<ConsumerAddressPO> addressPOList=consumerPO.getConsumerAddressList();
		List<ConsumerAddressForPresentVO> addressList=new ArrayList<ConsumerAddressForPresentVO>();
		for(ConsumerAddressPO addressPO:addressPOList) {
			ConsumerAddressForPresentVO address=new ConsumerAddressForPresentVO();
			address.setSite(addressPO.getSite());
			address.setCaid(String.valueOf(addressPO.getCaid()));
			addressList.add(address);
		}
		return addressList;
	}
	
	public ConsumerForPresentVO get(String cid) {
		ConsumerPO consumerPO=consumerDAO.query("where cid="+cid).get(0);
		return PO2PresentVO(consumerPO);
	}
	
	public List<ConsumerNumByLevelVO> getConsumerNumByLevelList(){
		List<ConsumerPO> consumerPOList=consumerDAO.query("");
		List<ConsumerNumByLevelVO> recordList=new ArrayList<ConsumerNumByLevelVO>();
		for(ConsumerPO consumerPO:consumerPOList) {
			int level=consumerPO.getLevel();
			Boolean isExist=false;
			for(ConsumerNumByLevelVO record:recordList) {
				if(level==record.getLevel()) {
					record.setNum(record.getNum()+1);
					isExist=true;
					break;
				}
			}
			if(!isExist) {
				ConsumerNumByLevelVO record=new ConsumerNumByLevelVO();
				record.setLevel(consumerPO.getLevel());
				record.setNum(1);
				recordList.add(record);
			}
		}
		return recordList;
	}
	
	private ConsumerForPresentVO PO2PresentVO(ConsumerPO consumerPO) {
		List<Site> siteList=new ArrayList<Site>();
		List<ConsumerAddressPO> addressPOList=consumerPO.getConsumerAddressList();
		for(ConsumerAddressPO addressPO:addressPOList) {
			siteList.add(addressPO.getSite());
		}
		
		ConsumerForPresentVO consumer=new ConsumerForPresentVO();
		consumer.setAid(String.valueOf(consumerPO.getAccount().getAid()));
		consumer.setCid(String.valueOf(consumerPO.getCid()));
		consumer.setEmail(consumerPO.getEmail());
		consumer.setName(consumerPO.getName());
		consumer.setPassword(consumerPO.getPassword());
		consumer.setPhoneNumber(consumerPO.getPhoneNumber());
		consumer.setSiteList(siteList);
		
		return consumer;
	}
	
}
