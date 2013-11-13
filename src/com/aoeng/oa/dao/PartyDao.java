/**
 * 
 */
package com.aoeng.oa.dao;

import com.aoeng.oa.model.Company;
import com.aoeng.oa.vo.PagerVo;

/**
 * Oct 30, 2013 4:26:38 PM
 * 
 */
public interface PartyDao extends BaseDao
{

	/**
	 * @param partyName
	 * @param offSet
	 * @param pageSize
	 * @return
	 */
	PagerVo findAllPartyPaging(String partyName);

	/**
	 * @return
	 * 
	 */
	Company findCurrentCompany();

	/**
	 * @param model
	 */
	void saveOrUpdate(Company model);

	/**
	 * @param parentId
	 * @param sSearch
	 * @return
	 */
	PagerVo findPersons(int parentId, String sSearch);

}
