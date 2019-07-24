package service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eo.ProviderType;
import eo.Site;
import vo.ProviderVO;

class ProviderTest {

	@Test
	void testRegister() {
		String aid="1";
		String password="xyp2016";
		Site site=Site.B;
		ProviderType type=ProviderType.A;
		
		ProviderVO provider=new ProviderVO();
		provider.setAid(aid);
		provider.setPassword(password);
		provider.setSite(site);
		provider.setType(type);
		
		ProviderService service=new ProviderService();
		service.register(provider);
	}
	
	@Test
	void testupdate() {
		String aid="1";
		String password="xyp2016";
		Site site=Site.C;
		ProviderType type=ProviderType.B;
		
		ProviderVO provider=new ProviderVO();
		provider.setAid(aid);
		provider.setPassword(password);
		provider.setSite(site);
		provider.setType(type);
		
		ProviderService service=new ProviderService();
		service.update(provider, "31");
	}
	
	@Test
	void testApprove() {
		String pid;
		ProviderService service=new ProviderService();
		service.approve("31");
	}
	
	@Test
	void testDisapprove() {
		String pid;
		ProviderService service=new ProviderService();
		service.disapprove("31");
	}
	

}
