/**
 * 
 */
package test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aoeng.oa.service.InitService;

import junit.framework.TestCase;

/**
 * Nov 1, 2013 4:23:19 PM
 *
 */
public class TestInitService extends TestCase{

	public void test(){
		BeanFactory factory = new ClassPathXmlApplicationContext("app*.xml");
		InitService service = (InitService) factory.getBean("initService");
		service.addInitData();
	}
}
