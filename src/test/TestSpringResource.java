/**
 * 
 */
package test;

import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import com.aoeng.oa.annotations.Oper;
import com.aoeng.oa.annotations.Res;

/**
 * 测试 Spring 的反射类 Nov 6, 20131:29:54 PM
 * 
 */
public class TestSpringResource extends TestCase {
	public void test1() throws Exception {
		//获得资源的解释路径，返回资源对象
		String path = "com/aoeng/oa/web/**/*Action.class";
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] res = resolver.getResources(path);
		
		//要读取资源对象，必须使用 Spring 提供的 MetadataReader 对象
		MetadataReaderFactory metaFactory = new CachingMetadataReaderFactory();
		MetadataReader metaReader = metaFactory.getMetadataReader(res[2]);
		//得到类的元数据  注：得到元数据
		ClassMetadata classMetadata = metaReader.getClassMetadata();
		//得到注解的元数据
		AnnotationMetadata annoMetadata = metaReader.getAnnotationMetadata();
		
		System.out.println(classMetadata.getClassName());
		Map resAttr  = annoMetadata.getAnnotationAttributes(Res.class.getName());
		System.out.println(resAttr.get("name"));
		
		//得到注解的方法元数据
		Set<MethodMetadata> amd= annoMetadata.getAnnotatedMethods(Oper.class.getName());
		for (MethodMetadata m : amd) {
			System.out.println(m.getMethodName());
			System.out.println(m.getAnnotationAttributes(Oper.class.getName()).get("name"));
		}
		
	}

}
