package service;

import java.util.ArrayList;
import java.util.List;

import dao.AccountDAO;
import dao.AccountTransferDAO;
import po.AccountPO;
import po.AccountTransferPO;
import vo.AccountBalanceByDateVO;
import vo.AccountTransferForPresentVO;
import vo.AccountTransferVO;

public class AccountStatisticsService {

	private AccountDAO accountDAO=new AccountDAO();
	private AccountTransferDAO transferDAO=new AccountTransferDAO();
	
	public List<AccountTransferForPresentVO> getListByDate(String aid){
		List<AccountTransferPO> transferPOList=transferDAO.query("where account_from.aid="+aid+
				" or account_to.aid="+aid+" order by date");
		List<AccountTransferForPresentVO> transferList=new ArrayList<AccountTransferForPresentVO>();
		for(AccountTransferPO transferPO:transferPOList) {
			AccountTransferForPresentVO transfer=PO2PresentVO(transferPO);
			transferList.add(transfer);
		}
		
		return transferList;
	}
	
	public List<AccountBalanceByDateVO> getAccountBalanceByDateList(String aid){
		List<AccountTransferPO> transferPOList=transferDAO.query("where account_from.aid="+aid+
				" or account_to.aid="+aid+" order by date desc");
		List<AccountBalanceByDateVO> recordList=new ArrayList<AccountBalanceByDateVO>();
		AccountPO account=accountDAO.query("where aid="+aid).get(0);
		double balance=account.getBalance();
		int aidInDB=Integer.valueOf(aid);
		for(AccountTransferPO transferPO:transferPOList) {
			AccountBalanceByDateVO record=new AccountBalanceByDateVO();
			record.setBalance(balance);
			record.setDate(transferPO.getDate());
			recordList.add(record);
			if(transferPO.getAccount_from().getAid()==aidInDB) {
				balance+=transferPO.getMoney();
			}else if(transferPO.getAccount_to().getAid()==aidInDB) {
				balance-=transferPO.getMoney();
			}
		}
		return recordList;
	}
	
	public AccountTransferVO createTransfer(String aid_from,String aid_to,Double money) {
		AccountTransferVO transfer=new AccountTransferVO();
		transfer.setAid_from(aid_from);
		transfer.setAid_to(aid_to);
		transfer.setMoney(money);
		return transfer;
		
	}

	
	private AccountTransferForPresentVO PO2PresentVO(AccountTransferPO transferPO) {
		AccountTransferForPresentVO transfer=new AccountTransferForPresentVO();
		transfer.setAid_from(String.valueOf(transferPO.getAccount_from().getAid()));
		transfer.setAid_to(String.valueOf(transferPO.getAccount_to().getAid()));
		transfer.setDate(transferPO.getDate());
		transfer.setMoney(transferPO.getMoney());
		transfer.setTid(String.valueOf(transferPO.getTid()));
		return transfer;
	}
}
