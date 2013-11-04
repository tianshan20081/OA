/**
 * 
 */
package test.aoeng.oa;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;

/**
 * Oct 31, 2013 1:20:48 PM
 *
 */
public class TextJson extends TestCase{

	public void test01() throws Exception{
		Simple simple = new Simple("12","zhangsan");
		
		
		System.out.println(JSONMapper.toJSON(simple));
		System.out.println(JSONMapper.toJSON(simple).render(true));
	}
	public void test02() throws MapperException{
		Map map = new HashMap();
		map.put("id", "123456");
		map.put("name", "123456");
		
		System.out.println(JSONMapper.toJSON(map).render(true));
	}
	
	public void test03() throws Exception{
		Node node = new Node("首信易支付",1,"www.baidu.com");
		node.addChildNode(new Node("财务部",2,"www.beijing.com.cn"));
		node.addChildNode(new Node("市场部", 3,"www.payeasenet.cn"));
		
		System.out.println(JSONMapper.toJSON(node).render(true));
		
	}
}
