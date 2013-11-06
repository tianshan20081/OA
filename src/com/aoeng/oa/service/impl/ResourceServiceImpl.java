/**
 * 
 */
package com.aoeng.oa.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Service;

import com.aoeng.oa.annotations.Oper;
import com.aoeng.oa.annotations.Res;
import com.aoeng.oa.dao.ResourceDao;
import com.aoeng.oa.model.ActionMethodOper;
import com.aoeng.oa.model.ActionResource;

/**
 * Nov 6, 20132:02:13 PM
 * 
 */
@Service("resourceService")
public class ResourceServiceImpl implements com.aoeng.oa.service.ResourceService {
	Logger logger = Logger.getLogger(ResourceServiceImpl.class.getName());
	@Resource
	private ResourceDao resourceDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.ResourceService#rebuildActionResource()
	 */
	@Override
	public void rebuildActionResource() {
		// TODO Auto-generated method stub
		System.out.println("rebuildActionResource");
		try {
			// 扫描某个包 把其中 Action 类扫描出来
			String pathPattern = "com/aoeng/oa/web/**/*Action.class";

			// 创建 Spring 的路径模式解释器
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

			// 把路径下所有符合路径模式的类的元数据封装成 Resource 类
			org.springframework.core.io.Resource[] res = resolver.getResources(pathPattern);

			if (res != null) {
				// 创建元数据读取对象工厂
				MetadataReaderFactory metaFactory = new CachingMetadataReaderFactory();
				// 扫描每个 Resource
				for (org.springframework.core.io.Resource re : res) {
					// 得到响应的 MetadataReader
					MetadataReader metaReader = metaFactory.getMetadataReader(re);
					// 提取类信息，保存 ActionResource 对象
					saveActionResource(metaReader, metaFactory, resolver);
				}

			}
			//建立父子关系
			List<ActionResource> resources = resourceDao.findAll(ActionResource.class);
			for (ActionResource ar : resources) {
				//根据 parentSn 判断是否存在 父资源
				String parentSn = ar.getParentSn();
				if (!StringUtils.isEmpty(parentSn)) {
					ActionResource parent = resourceDao.findActionResourceBySn(parentSn);
					if (parent != null) {
						ar.setParent(parent);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param metaReader
	 * @param metaFactory
	 * @param resolver
	 */
	private void saveActionResource(MetadataReader metaReader, MetadataReaderFactory metaFactory,
			ResourcePatternResolver resolver) {
		// TODO Auto-generated method stub
		try {
			// 得到类的元数据
			ClassMetadata classMetadata = metaReader.getClassMetadata();
			// 得到注解的元数据
			AnnotationMetadata annoMetadata = metaReader.getAnnotationMetadata();

			// 判断是否定义了 @Res 注解
			if (annoMetadata.hasAnnotation(Res.class.getName())) {
				System.out.println("扫描到类【" + classMetadata.getClassName() + "】包含有@Res 注解！");
				/***************** 提取资源信息 ******************************************************/
				// 取出@Res 注解的属性
				Map resAttr = annoMetadata.getAnnotationAttributes(Res.class.getName());
				// 获取 @Res 注解中 name 属性
				String name = (String) resAttr.get("name");
				// 获取 @Res 注解中 sn 属性
				String sn = (String) resAttr.get("sn");
				// 获取 @Res 注解中 orderNumber 属性
				int orderNumber = (Integer) resAttr.get("orderNumber");
				// 获取 @Res 注解中 parentSn 属性
				String parentSn = (String) resAttr.get("parentSn");
				// 获取 @Res 所在类的类名
				String className = classMetadata.getClassName();
				/******************* 根据这些信息创建 ActionResource 对象 *****************************************/
				ActionResource ar = resourceDao.findActionResourceBySn(sn);

				if (ar == null) {
					ar = new ActionResource();
				}
				ar.addClassName(className);
				ar.setClassName(className);
				ar.setOrderNumber(orderNumber);
				ar.setSn(sn);
				ar.setParentSn(parentSn);
				ar.setName(name);

				System.out.println("扫描到资源【" + sn + "(" + name + ")" + "】;");

				// 搜索本类型下面定义 @Oper 的方法及其父类下面定义了 @Oper 的方法
				searchOperAnnotations(ar, metaReader, metaFactory, resolver);

				resourceDao.save(ar);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param ar
	 * @param metaReader
	 * @param metaFactory
	 * @param resolver
	 * @throws Exception
	 */
	private void searchOperAnnotations(ActionResource ar, MetadataReader metaReader, MetadataReaderFactory metaFactory,
			ResourcePatternResolver resolver) throws Exception {
		// TODO Auto-generated method stub
		// 得到注解的元数据
		AnnotationMetadata annotationMetadata = metaReader.getAnnotationMetadata();
		// 扫描这个类下面配置的@Oper 的方法
		Set<MethodMetadata> mms = annotationMetadata.getAnnotatedMethods(Oper.class.getName());

		if (mms != null) {
			for (MethodMetadata m : mms) {
				Map<String, Object> operAttr = m.getAnnotationAttributes(Oper.class.getName());
				String methodName = m.getMethodName();
				String operName = (String) operAttr.get("name");

				if (StringUtils.isEmpty(operName)) {
					operName = getDefaultOperName(methodName);
				}

				String operSn = (String) operAttr.get("sn");
				if (StringUtils.isEmpty(operSn)) {
					operSn = getDefaultOperSn(methodName);
				}

				int operIndex = (Integer) operAttr.get("index");
				if (operIndex == -1) {
					operIndex = getDefaultOperIndex(methodName);
				}

				ar.addActionMethodOper(methodName, operName, operSn, operIndex);
				// resourceDao.save(ar);

				System.out.println("扫描到操作【" + operSn + "(" + operName + ")】【" + operIndex + "】:" + methodName);

				// 如果有父类，而且不是 java.lang.Object ,则继续搜索这个父类当中是否还包含 @Oper 注解的方法
				if (metaReader.getClassMetadata().hasSuperClass()
						&& !metaReader.getClassMetadata().getSuperClassName().equals(Object.class.getName())) {
					// 得到父类得名称
					String superClassName = metaReader.getClassMetadata().getSuperClassName();
					// 构造父类资源路径
					String superClassPath = superClassName.replace(".", "/") + ".class";
					org.springframework.core.io.Resource superClassResource = resolver.getResource(superClassPath);
					searchOperAnnotations(ar, metaFactory.getMetadataReader(superClassResource), metaFactory, resolver);

				}

			}
		}
	}

	/**
	 * @param methodName
	 * @return
	 */
	private int getDefaultOperIndex(String methodName) {
		// TODO Auto-generated method stub
		if (methodName.startsWith("add")) {
			return 0;
		} else if (methodName.startsWith("update")) {
			return 1;
		} else if (methodName.startsWith("del")) {
			return 2;
		}
		return 3;
	}

	/**
	 * @param methodName
	 * @return
	 */
	private String getDefaultOperSn(String methodName) {
		// TODO Auto-generated method stub
		if (methodName.startsWith("add")) {
			return "CREATE";
		} else if (methodName.startsWith("update")) {
			return "UPDATE";
		} else if (methodName.startsWith("del")) {
			return "DELETE";
		}
		return "READ";
	}

	/**
	 * @param methodName
	 * @return
	 */
	private String getDefaultOperName(String methodName) {
		// TODO Auto-generated method stub
		if (methodName.startsWith("add")) {
			return "添加";
		} else if (methodName.startsWith("update")) {
			return "更新";
		} else if (methodName.startsWith("del")) {
			return "删除";
		}
		return "查询";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.ResourceService#findAllTopActionResources()
	 */
	@Override
	public List<ActionResource> findAllTopActionResources() {
		// TODO Auto-generated method stub
		return resourceDao.findAllTopActionResources();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.ResourceService#addActionResource(com.aoeng.oa.model.ActionResource)
	 */
	@Override
	public void addActionResource(ActionResource actionResource) {
		// TODO Auto-generated method stub
		resourceDao.save(actionResource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.ResourceService#findActionResourceById(int)
	 */
	@Override
	public ActionResource findActionResourceById(int sn) {
		// TODO Auto-generated method stub
		return resourceDao.findById(ActionResource.class, sn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.ResourceService#updateActionResource(com.aoeng.oa.model.ActionResource)
	 */
	@Override
	public void updateActionResource(ActionResource actionResource) {
		// TODO Auto-generated method stub
		resourceDao.update(actionResource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.ResourceService#delActionResource(int)
	 */
	@Override
	public void delActionResource(int sn) {
		// TODO Auto-generated method stub
		resourceDao.del(resourceDao.findById(ActionResource.class, sn));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.ResourceService#addActionResourceOper(int, com.aoeng.oa.model.ActionMethodOper)
	 */
	@Override
	public void addActionResourceOper(int sn, ActionMethodOper oper) {
		// TODO Auto-generated method stub
		ActionResource ar = resourceDao.findById(ActionResource.class, sn);
		ar.addActionMethodOper(oper.getMethodName(), oper.getOperName(), oper.getOperSn(), oper.getOperIndex());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.ResourceService#delActionResourceOper(int, java.lang.String)
	 */
	@Override
	public void delActionResourceOper(int sn, String operSn) {
		// TODO Auto-generated method stub
		ActionResource ar = resourceDao.findById(ActionResource.class, sn);
		ar.getOpers().remove(operSn);
	}

}
