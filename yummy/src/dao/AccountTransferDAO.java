package dao;

import java.util.Date;

import po.AccountPO;
import po.AccountTransferPO;
import vo.AccountTransferVO;

public class AccountTransferDAO extends BaseDAO<AccountTransferPO>{

	public AccountTransferPO createTransfer(AccountTransferVO transfer) {
		AccountPO account_from=new AccountPO();
		account_from.setAid(Integer.valueOf(transfer.getAid_from()));
		AccountPO account_to=new AccountPO();
		account_to.setAid(Integer.valueOf(transfer.getAid_to()));
		Date date=new Date();
		Double money=transfer.getMoney();
		AccountTransferPO transferPO=new AccountTransferPO();
		transferPO.setAccount_from(account_from);
		transferPO.setAccount_to(account_to);
		transferPO.setDate(date);
		transferPO.setMoney(money);
		return transferPO;
	}
}
