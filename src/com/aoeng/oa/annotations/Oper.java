/**
 * 
 */
package com.aoeng.oa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作的注解 Nov 6, 201311:16:14 AM
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
// 运行时，可以进行反射，来获得注解的资源的信息
@Target(ElementType.METHOD)
// 只能定义在方法的前面
public @interface Oper {
	/**
	 * 操作的名称，如果不定义，自动根据方法的命名赋一个值 eg:add开头的方法自动添加一个名字 如 添加 update 更新 del 删除 其他 查询
	 * 
	 * @return
	 */
	String name() default "";

	/**
	 * 操作的唯一标识，如果不定义，自动根据方法名称赋予一个默认值，规则如下 eg:add CREATE ; update UPDATE ; del DELETE; 其他方法 READ
	 * 
	 * @return
	 */
	String sn() default "";

	/**
	 * 操作对应的索引，如果不定义，自动根据方法名赋予一个默认值，规则如下 eg:add开头的方法 自动给一个索引值 0; update 1 ； del 2 ; 其他 3 .
	 * 
	 * @return
	 */
	int index() default -1;
}
