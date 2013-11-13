/**
 * 
 */
package com.aoeng.oa.dao.impl;

import org.springframework.stereotype.Repository;

import com.aoeng.oa.dao.PartyDao;
import com.aoeng.oa.model.Company;
import com.aoeng.oa.vo.PagerVo;

/**
 * Oct 30, 2013 4:30:58 PM
 * 
 */
@Repository("partyDao")
public class PartyDaoImpl extends BaseDaoImpl implements PartyDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.PartyDao#findAllPartyPaging(java.lang.String, int, int)
	 */
	@Override
	public PagerVo findAllPartyPaging(String partyName) {
		// TODO Auto-generated method stub
		String hql = "select p from Party p where p.name like ?";
		return findPaging(hql, "%" + partyName + "%");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.PartyDao#findCurrentCompany()
	 */
	@Override
	public Company findCurrentCompany() {
		// TODO Auto-generated method stub

		String hql = "select c from Company c where c.parent is null ";
		// 组织机构列表中不包含 人员 。应当在 Party.hbm.xml 中配置 no_contain_person
		getSession().enableFilter("no_contain_person");

		return (Company) getSession().createQuery(hql).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.PartyDao#saveOrUpdate(com.aoeng.oa.model.Company)
	 */
	@Override
	public void saveOrUpdate(Company model) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.PartyDao#findPersonsById(int, java.lang.String)
	 */
	@Override
	public PagerVo findPersons(int parentId, String sSearch) {
		// TODO Auto-generated method stub
		// 根据parent id 查询
		String hql = "select p.id , p.name,p.sex,p.phone from Person p where p.parent.id = " + parentId;
		if (parentId == 0) {
			hql = "select p.id , p.name,p.sex,p.phone from Person p where  1= 1 ";
		}
		if (null != sSearch && !"".equals(sSearch)) {
			hql += " and ( p.name like '%" + sSearch + "%' or p.sex like '%" + sSearch + "%')";
		}
		return findPaging(hql);
	}

}
