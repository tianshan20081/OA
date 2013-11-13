/**
 * 
 */
package com.aoeng.oa.service.impl;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

import com.aoeng.oa.service.InitService;
import com.aoeng.oa.service.ResourceService;

/**
 * Nov 1, 2013 3:20:02 PM
 * 
 */
@Service("initService")
public class InitServiceImpl implements InitService, BeanFactoryAware
{
	private String path;
	private BeanFactory beanFactory;

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans.factory.BeanFactory)
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		this.beanFactory = beanFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.InitService#addInitData()
	 */
	@Override
	public void addInitData() {
		// TODO Auto-generated method stub
		try {
			// 获取得到当前类路径下的文件
			Document doc = new SAXReader().read(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(path));
			// 取得根元素
			Element root = doc.getRootElement();
			// 得到包名
			String pkg = root.valueOf("@package");
			// 得到根源素下的 entity 集合
			List<Element> entities = root.selectNodes("entity");
			for (Iterator<Element> iter = entities.iterator(); iter.hasNext();) {
				Element e = (Element) iter.next();
				addEntity(e, pkg, null, null);
			}

			// 重建所有的 ActionResource 资源
			ResourceService resourceService = (ResourceService) beanFactory.getBean("resourceService");
			resourceService.rebuildActionResource();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param e
	 * @param pkg
	 * @param object
	 * @param object2
	 */
	private void addEntity(Element e, String pkg, Object parent, String callString) {
		// TODO Auto-generated method stub
		try {
			// 處理當前的 element 对象
			// 1.要创建一个什么类型的对象
			// 要创建类的全包名
			String className = pkg + "." + e.attributeValue("class");
			// 根据类名创建实体对象
			Object entity = Class.forName(className).newInstance();

			// 给实体对象当中的属性赋值
			Iterator iter = e.attributeIterator();
			while (iter.hasNext()) {
				Attribute attr = (Attribute) iter.next();
				String proName = attr.getName();
				// 判断除了 class 和 call 属性的其他属性赋值
				if (!"class".equals(proName) && !"call".equals(proName)) {
					String proValue = attr.getValue();
					BeanUtils.copyProperty(entity, proName, proValue);
				}

			}
			// 给 entity 的父属性赋值
			BeanUtils.copyProperty(entity, "parent", parent);
			// 2. 存储对象（调用那一个 Service 的那一个方法）

			String call = e.attributeValue("call");
			if (call != null) {
				callString = call;
			}

			if (callString == null) {
				throw new RuntimeException("无法创建实体对象，调用方法未知");
			}

			// 3.调用响应的方法进行存储
			String[] mesg = callString.split("\\.");
			String serviceName = mesg[0];
			String methodName = mesg[1];
			// 调用 service 对象
			Object serviceObj = beanFactory.getBean(serviceName);

			// 调用响应的方法 得到要调用的 service 对象上的方法的反射类
			// Method method = serviceObj.getClass().getMethod(methodName, entity.getClass());
			Method[] methods = serviceObj.getClass().getMethods();
			for (Method m : methods) {
				if (methodName.equals(m.getName())) {
					// 调用这个方法
					m.invoke(serviceObj, entity);

				}
			}

			// 4.考虑 Element 下面有没有子元素
			List<Element> subElements = e.elements("entity");
			for (Iterator itr = subElements.iterator(); itr.hasNext();) {
				Element subElement = (Element) itr.next();
				addEntity(subElement, pkg, entity, callString);
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
