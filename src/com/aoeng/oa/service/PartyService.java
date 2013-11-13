/**
 * 
 */
package com.aoeng.oa.service;

import com.aoeng.oa.model.Company;
import com.aoeng.oa.model.Party;
import com.aoeng.oa.vo.PagerVo;

/**
 * Oct 30, 2013 4:56:53 PM
 * 
 */
public interface PartyService
{

	public void addParty(Party party);

	void updateParty(Party party);

	PagerVo findAllPartyPaging(String partyName);

	/**
	 * @return
	 * 
	 */
	public Company findCurrentCompany();

	/**
	 * @param model
	 */
	public void saveOrUpdateCompany(Company model);

	/**
	 * @param i
	 * @return
	 */
	public Party findPartyById(int i);

	/**
	 * @param i
	 */
	public void delParty(int i);

	/**
	 * @param parentId
	 * @param sSearch
	 * @return
	 */
	public PagerVo findPersons(int parentId, String sSearch);

}
