package service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import po.ProviderDiscountPO;
import vo.ProviderDiscountForPresentVO;

class ProviderStatisticsTest {

	@Test
	void testDiscount() {
		
		List<ProviderDiscountForPresentVO> discountList=new ProviderStatisticsService().getDiscountList();
		int a=0;
	}

}
