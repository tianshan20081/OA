/**
 * 
 */
package test.aoeng.oa;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aoeng.oa.model.Company;
import com.aoeng.oa.model.Department;
import com.aoeng.oa.model.Party;
import com.aoeng.oa.model.Person;
import com.aoeng.oa.model.Position;
import com.aoeng.oa.service.PartyService;
import com.aoeng.oa.vo.PagerVo;

import junit.framework.TestCase;

/**
 * Oct 30, 2013 5:01:56 PM
 *
 */
public class Test extends TestCase {
	
	
	
	
	public void test1() throws Exception {
		long start = System.currentTimeMillis();
		BeanFactory factory = new ClassPathXmlApplicationContext("app*.xml");
		PartyService partyService = (PartyService) factory.getBean("partyService");
		
		Party company = new Company();
		company.setName("易智付科技");
		company.setDescription("专业支付公司");
		partyService.addParty(company);
		for (int j = 0; j < 100; j++) {
			Party dp = new Person();
			dp.setName("公司员工"+j);
			dp.setDescription("公司员工"+j);
			dp.setParent(company);
			partyService.addParty(dp);
		}
		
		
		
		Party deParty = new Department();
		deParty.setName("财务部");
		deParty.setDescription("财务部门");
		deParty.setParent(company);
		Party deParty1 = new Department();
		deParty1.setName("结算部");
		deParty1.setDescription("结算部门");
		deParty1.setParent(company);
		Party deParty2 = new Department();
		deParty2.setName("销售部");
		deParty2.setDescription("销售部门");
		deParty2.setParent(company);
		
		partyService.addParty(deParty);
		partyService.addParty(deParty1);
		partyService.addParty(deParty2);
		
		for (int i = 0; i < 20; i++) {
			Party d  = null ;
			d = new Department();
			d.setName("部门"+i);
			d.setDescription("部门描述"+i);
			d.setParent(company);
			partyService.addParty(d);
			for (int j = 0; j < 50; j++) {
				Party dp = new Person();
				dp.setName("部门员工"+j);
				dp.setDescription("部门员工"+j);
				dp.setParent(d);
				partyService.addParty(dp);
			}
			
		}
		
		System.out.println(System.currentTimeMillis()-start);
		
	}
	public void testPagers() throws Exception {
		BeanFactory factory = new ClassPathXmlApplicationContext("app*.xml");
		PartyService partyService = (PartyService) factory.getBean("partyService");
		PagerVo pagerVo = partyService.findAllPartyPaging("部");
		
		
		System.out.println(pagerVo.toString());
	}

}
