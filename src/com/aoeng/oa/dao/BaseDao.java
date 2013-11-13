/**
 * 
 */
package com.aoeng.oa.dao;

import java.util.List;

import com.aoeng.oa.vo.PagerVo;

/**
 * Oct 30, 2013 4:38:16 PM
 * 
 */
public interface BaseDao
{
	void save(Object obj);

	void update(Object obj);

	void del(Object obj);

	<T> T findById(Class<T> entityClass, int id);

	<T> List<T> findAll(Class<T> entityClass);

	/**
	 * 通用分页查询
	 * 
	 * @param hql
	 * @param size
	 * @param pageSize
	 * @param params
	 * @return
	 */
	PagerVo findPaging(String hql, int size, int pageSize, Object... params);

	PagerVo findPaging(String hql, Object... params);

}
