package service;

import dao.AccountDAO;
import dao.AccountTransferDAO;
import po.AccountPO;
import vo.AccountTransferVO;

public class AccountService {

	private SecurityService securityService=new SecurityService();
	private AccountTransferDAO transferDAO=new AccountTransferDAO();
	private AccountDAO accountDAO=new AccountDAO();

	
	public void transfer(AccountTransferVO transfer) {
		AccountPO account_from=accountDAO.query("where aid="+transfer.getAid_from()).get(0);
		account_from.setBalance(account_from.getBalance()-transfer.getMoney());
		accountDAO.save(account_from);
		
		AccountPO account_to=accountDAO.query("where aid="+transfer.getAid_to()).get(0);
		account_to.setBalance(account_to.getBalance()+transfer.getMoney());
		accountDAO.save(account_to);
		
		transferDAO.save(transferDAO.createTransfer(transfer));
	};
	
	public Boolean checkAuthority(String aid,String password) {
		String passwordInDB = accountDAO.query("where aid="+aid).get(0).getPassword();
		return securityService.md5Verify(password, passwordInDB);
	};
}
