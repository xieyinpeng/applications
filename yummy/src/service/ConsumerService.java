package service;

import dao.ConsumerDAO;
import eo.ConsumerState;
import po.ConsumerPO;
import vo.ConsumerForPresentVO;
import vo.ConsumerVO;

public class ConsumerService {

	private ConsumerDAO consumerDAO = new ConsumerDAO();
	private SecurityService securityService=new SecurityService();
	
	public void register(ConsumerVO consumer,String code) {
		String email=consumer.getEmail();
		EmailService service=EmailService.getInstance();
		String code_true=service.getCode(email);
		if(!code.equals(code_true)) {
			System.err.println("验证码错误");
			return;
		}
		String passwordInDB=securityService.md5Encrypt(consumer.getPassword());
		consumer.setPassword(passwordInDB);
		ConsumerPO consumerPO=consumerDAO.createConsumerPO(consumer);
		consumerDAO.save(consumerPO);
	};
	
	public void update(ConsumerVO consumer,String cid) {
		String passwordInDB=securityService.md5Encrypt(consumer.getPassword());
		consumer.setPassword(passwordInDB);
		ConsumerPO consumerPO=consumerDAO.createConsumerPO(consumer);
		consumerPO.setCid(Integer.valueOf(cid));
		consumerDAO.save(consumerPO);
	};
	
	public void logoff(String cid) {
		ConsumerPO consumerPO=consumerDAO.query("where cid="+cid).get(0);
		consumerPO.setConsumerState(ConsumerState.LogOffed);
		consumerDAO.save(consumerPO);
	};
	
	public void upgrade(String cid,Double money) {
		int up=(int)money.intValue()/5;
		ConsumerPO consumerPO=consumerDAO.query("where cid="+cid).get(0);
		int level=consumerPO.getLevel();
		level+=up;
		consumerPO.setLevel(level);
		consumerDAO.save(consumerPO);
	}
	
	public Boolean checkAuthority(String cid,String password) {
		ConsumerPO consumerPO=consumerDAO.query("where cid="+cid).get(0);
		String passwordInDB=consumerPO.getPassword();
		Boolean passwordIsRight=securityService.md5Verify(password, passwordInDB);
		Boolean stateIsRight=consumerPO.getConsumerState().equals(ConsumerState.Registered);
		if(passwordIsRight&&stateIsRight) {
			return true;
		}else {
			System.err.println("密码错误");
			return false;
		}
	};

	
}
