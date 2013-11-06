/**
 * 
 */
package com.aoeng.oa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来定义资源的注解 Nov 6, 201311:07:51 AM
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
// 运行时，可以进行反射，来获得注解的资源的信息
@Target(ElementType.TYPE)
// 只能定义在类前面
public @interface Res {
	/**
	 * 资源名称，必须定义 eg:组织机构管理
	 * 
	 * @return
	 */
	String name();

	/**
	 * 资源的唯一标识
	 * 
	 * @return
	 */
	String sn();

	/**
	 * 资源的排序号，
	 * 
	 * @return
	 */
	int orderNumber();

	/**
	 * 父资源的唯一标识
	 * 
	 * @return
	 */
	String parentSn();

}
