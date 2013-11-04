/**
 * 
 */
package com.aoeng.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.From;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aoeng.oa.SystemContext;
import com.aoeng.oa.dao.BaseDao;
import com.aoeng.oa.vo.PagerVo;

/**
 * Oct 30, 2013 4:31:27 PM
 *
 */
public class BaseDaoImpl implements BaseDao{
	@Resource
	private SessionFactory sessionFactory ;
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public void save(Object obj){
		getSession().save(obj);
	}
	/* (non-Javadoc)
	 * @see com.aoeng.oa.dao.BaseDao#update(java.lang.Object)
	 */
	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		getSession().update(obj);
	}
	/* (non-Javadoc)
	 * @see com.aoeng.oa.dao.BaseDao#del(java.lang.Object)
	 */
	@Override
	public void del(Object obj) {
		// TODO Auto-generated method stub
		getSession().delete(obj);
	}
	/* (non-Javadoc)
	 * @see com.aoeng.oa.dao.BaseDao#findById(int)
	 */
	@Override
	public <T> T findById(Class<T> entityClass,int id) {
		// TODO Auto-generated method stub
		
		return (T) getSession().load(entityClass, id);
	}
	/* (non-Javadoc)
	 * @see com.aoeng.oa.dao.BaseDao#findAll()
	 */
	@Override
	public <T> List<T> findAll(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(entityClass).list();
	}
	/* (non-Javadoc)
	 * @see com.aoeng.oa.dao.BaseDao#findPaging(java.lang.String, java.lang.Object[])
	 */
	/* (non-Javadoc)
	 * @see com.aoeng.oa.dao.BaseDao#findPaging(java.lang.String, int, int, java.lang.Object[])
	 */
	//分頁
	@Override
	public PagerVo findPaging(String hql, int offSet, int pageSize, Object... params) {
		// TODO Auto-generated method stub
		//查詢總記錄數
		String countHql = getCountHql(hql);
		Query query = getSession().createQuery(countHql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			
		}
		long total = (Long) query.uniqueResult();
		//查询数据集
		query = getSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult(offSet);
		query.setMaxResults(pageSize);
		List datas = query.list();
		
		return new PagerVo(datas, total);
	}
	private String getCountHql(String hql){
		int index = hql.indexOf("from");
		if (index == -1) {
			throw new RuntimeException("There a error in your hql ");
		}
		return "select count(*) "+ hql.substring(index);
	}
	/* (non-Javadoc)
	 * @see com.aoeng.oa.dao.BaseDao#findPaging(java.lang.String, java.lang.Object[])
	 */
	@Override
	public PagerVo findPaging(String hql, Object... params) {
		// TODO Auto-generated method stub
		
		return findPaging(hql, SystemContext.getOffSet(), SystemContext.getPageSize(), params);
	}
}
