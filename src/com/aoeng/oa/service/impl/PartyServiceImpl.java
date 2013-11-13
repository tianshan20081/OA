/**
 * 
 */
package com.aoeng.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aoeng.oa.dao.PartyDao;
import com.aoeng.oa.model.Company;
import com.aoeng.oa.model.Party;
import com.aoeng.oa.service.PartyService;
import com.aoeng.oa.vo.PagerVo;

/**
 * Oct 30, 2013 4:57:46 PM
 * 
 */
@Service("partyService")
public class PartyServiceImpl implements PartyService
{
	@Resource
	private PartyDao partyDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.PartyService#addParty(com.aoeng.oa.model.Party)
	 */
	@Override
	public void addParty(Party party) {
		// TODO Auto-generated method stub
		partyDao.save(party);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.PartyService#updateParty(com.aoeng.oa.model.Party)
	 */
	@Override
	public void updateParty(Party party) {
		// TODO Auto-generated method stub
		partyDao.update(party);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.PartyService#findAllPaging(java.lang.String, int, int)
	 */
	@Override
	public PagerVo findAllPartyPaging(String partyName) {
		// TODO Auto-generated method stub

		return partyDao.findAllPartyPaging(partyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.PartyService#findCurrentCompany()
	 */
	@Override
	public Company findCurrentCompany() {
		// TODO Auto-generated method stub
		return partyDao.findCurrentCompany();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.PartyService#saveOrUpdateCompany(com.aoeng.oa.model.Company)
	 */
	@Override
	public void saveOrUpdateCompany(Company model) {
		// TODO Auto-generated method stub
		partyDao.saveOrUpdate(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.PartyService#findPartyById()
	 */
	@Override
	public Party findPartyById(int id) {
		// TODO Auto-generated method stub

		return partyDao.findById(Party.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.PartyService#delParty(int)
	 */
	@Override
	public void delParty(int id) {
		// TODO Auto-generated method stub
		Party p = partyDao.findById(Party.class, id);
		if (p.getChildren().size() > 0) {
			throw new RuntimeException("存在子Party 无法删除");
		}
		partyDao.del(p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.PartyService#findPersons(int, java.lang.String)
	 */
	@Override
	public PagerVo findPersons(int parentId, String sSearch) {
		// TODO Auto-generated method stub

		return partyDao.findPersons(parentId, sSearch);
	}

}
