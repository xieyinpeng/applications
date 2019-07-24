package service;

import java.util.ArrayList;
import java.util.List;

import dao.ProviderDAO;
import dao.ProviderForUpdateDAO;
import eo.ProviderType;
import po.ProviderDiscountPO;
import po.ProviderForUpdatePO;
import po.ProviderMultipleDishPO;
import po.ProviderPO;
import po.ProviderSingleDishPO;
import vo.ProviderDiscountForPresentVO;
import vo.ProviderForPresentVO;
import vo.ProviderMultipleDishForPresentVO;
import vo.ProviderNumByTypeVO;
import vo.ProviderSingleDishForPresentVO;

public class ProviderStatisticsService {

	private ProviderDAO providerDAO = new ProviderDAO();
	private ProviderForUpdateDAO providerForUpdateDAO=new ProviderForUpdateDAO();
	
	public List<ProviderForPresentVO> getList(){
		List<ProviderPO> providerPOList=providerDAO.query("");
		return PO2PresentVO(providerPOList);
	}
	
	public List<ProviderMultipleDishForPresentVO> getMultipleDishList(){
		List<ProviderPO> providerPOList=providerDAO.query("");
		List<ProviderMultipleDishForPresentVO> multipleList=new ArrayList<ProviderMultipleDishForPresentVO>();
		for(ProviderPO providerPO:providerPOList) {
			List<ProviderMultipleDishPO> multiplePOList=providerPO.getProviderMultipleDishList();
			for(ProviderMultipleDishPO multiplePO:multiplePOList) {
				ProviderMultipleDishForPresentVO multiple=new ProviderMultipleDishForPresentVO();
				String detail="";
				List<ProviderSingleDishPO> singlePOList=multiplePO.getProviderSingleDishList();
				for(ProviderSingleDishPO singlePO:singlePOList) {
					detail+=singlePO.getSummary();
				}
				multiple.setDetail(detail);
				multiple.setMid(String.valueOf(multiplePO.getMid()));
				multiple.setPid(String.valueOf(providerPO.getPid()));
				multiple.setPrice(multiplePO.getPrice());
				multiple.setSite(providerPO.getSite());
				multiple.setSummary(multiplePO.getSummary());
				multiple.setType(providerPO.getType());
				multiple.setLimit(multiple.getLimit());
				multipleList.add(multiple);
			}
		}
		return multipleList;
	}
	
	public List<ProviderSingleDishForPresentVO> getSingleDishList(){
		List<ProviderPO> providerPOList=providerDAO.query("");
		List<ProviderSingleDishForPresentVO> singleList=new ArrayList<ProviderSingleDishForPresentVO>();
		for(ProviderPO providerPO:providerPOList) {
			List<ProviderSingleDishPO> singlePOList=providerPO.getProviderSingleDishList();
			for(ProviderSingleDishPO singlePO:singlePOList) {
				ProviderSingleDishForPresentVO single=new ProviderSingleDishForPresentVO();
				single.setSid(String.valueOf(singlePO.getSid()));
				single.setPid(String.valueOf(providerPO.getPid()));
				single.setPrice(singlePO.getPrice());
				single.setSite(providerPO.getSite());
				single.setSummary(singlePO.getSummary());
				single.setType(providerPO.getType());
				singleList.add(single);
			}
		}
		return singleList;
	}
	
	public List<ProviderDiscountForPresentVO> getDiscountList(){
		List<ProviderPO> providerPOList=providerDAO.query("");
		List<ProviderDiscountForPresentVO> discountList=new ArrayList<ProviderDiscountForPresentVO>();
		for(ProviderPO providerPO:providerPOList) {
			List<ProviderDiscountPO> discountPOList=providerPO.getProviderDiscountList();
			for(ProviderDiscountPO discountPO:discountPOList) {
				ProviderDiscountForPresentVO discount=new ProviderDiscountForPresentVO();
				discount.setDateBegin(discountPO.getDateBegin());
				discount.setDateEnd(discountPO.getDateEnd());
				discount.setLevelMax(discountPO.getLevelMax());
				discount.setLevelMin(discountPO.getLevelMin());
				discount.setMoneyMax(discountPO.getMoneyMax());
				discount.setMoneyMin(discountPO.getMoneyMin());
				discount.setScale(discountPO.getScale());
				discount.setSummary(discountPO.getSummary());
				discount.setType(discountPO.getType());
				discount.setDid(String.valueOf(discountPO.getDid()));
				discount.setPid(String.valueOf(providerPO.getPid()));
				discountList.add(discount);
			}
		}
		return discountList;
	}
	
	public List<ProviderForPresentVO> getUpdatedList(){
		List<ProviderForUpdatePO> providerPOList=providerForUpdateDAO.query("where state='Updated'");
		return UpdatePO2PresentVO(providerPOList);
	}
	
	public List<ProviderForPresentVO> getRegisteredList(){
		List<ProviderPO> providerPOList=providerDAO.query("where state='Registered'");
		return PO2PresentVO(providerPOList);
	}
	
	public ProviderForPresentVO get(String pid) {
		ProviderPO provider=providerDAO.query("where pid="+pid).get(0);
		return PO2PresentVO(provider);
	}
	
	public List<ProviderNumByTypeVO> getProviderNumByTypeList(){
		List<ProviderPO> providerPOList=providerDAO.query("");
		List<ProviderNumByTypeVO> recordList=new ArrayList<ProviderNumByTypeVO>();
		for(ProviderPO providerPO:providerPOList) {
			ProviderType type=providerPO.getType();
			Boolean isExist=false;
			for(ProviderNumByTypeVO record:recordList) {
				if(type==record.getType()) {
					record.setNum(record.getNum()+1);
					isExist=true;
					break;
				}
			}
			if(!isExist) {
				ProviderNumByTypeVO record=new ProviderNumByTypeVO();
				record.setType(providerPO.getType());
				record.setNum(1);
				recordList.add(record);
			}
		}
		return recordList;
	}
	
	public List<ProviderForPresentVO> getProviderRequestList(){
		List<ProviderPO> providerPOList = providerDAO.query("where state=='Registered'");
		List<ProviderForUpdatePO> providerForUpdateList=providerForUpdateDAO.query("where state='updated'");
		List<ProviderForPresentVO> requestList=new ArrayList<ProviderForPresentVO>();
		requestList.addAll(PO2PresentVO(providerPOList));
		requestList.addAll(UpdatePO2PresentVO(providerForUpdateList));
		return requestList;
	};
	
	private ProviderForPresentVO PO2PresentVO(ProviderPO providerPO) {
		ProviderForPresentVO provider=new ProviderForPresentVO();
		provider.setAid(String.valueOf(providerPO.getAccount().getAid()));
		provider.setPassword(providerPO.getPassword());
		provider.setPid(String.valueOf(providerPO.getPid()));
		provider.setSite(providerPO.getSite());
		provider.setState(providerPO.getState());
		provider.setType(providerPO.getType());
		return provider;
	}
	
	private List<ProviderForPresentVO> PO2PresentVO(List<ProviderPO> providerPOList){
		List<ProviderForPresentVO> providerList=new ArrayList<ProviderForPresentVO>();
		for(ProviderPO providerPO:providerPOList) {
			providerList.add(PO2PresentVO(providerPO));
		}
		return providerList;
	}
	private ProviderForPresentVO UpdatePO2PresentVO(ProviderForUpdatePO providerPO) {
		ProviderForPresentVO provider=new ProviderForPresentVO();
		provider.setAid(String.valueOf(providerPO.getAccount().getAid()));
		provider.setPassword(providerPO.getPassword());
		provider.setPid(String.valueOf(providerPO.getPid()));
		provider.setSite(providerPO.getSite());
		provider.setState(providerPO.getState());
		provider.setType(providerPO.getType());
		return provider;
	}
	
	private List<ProviderForPresentVO> UpdatePO2PresentVO(List<ProviderForUpdatePO> providerPOList){
		List<ProviderForPresentVO> providerList=new ArrayList<ProviderForPresentVO>();
		for(ProviderForUpdatePO providerPO:providerPOList) {
			providerList.add(UpdatePO2PresentVO(providerPO));
		}
		return providerList;
	}
	
	
}
