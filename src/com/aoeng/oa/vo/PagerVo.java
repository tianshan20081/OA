/**
 * 
 */
package com.aoeng.oa.vo;

import java.util.List;


/**
 * Oct 30, 2013 5:15:48 PM
 *
 */
public class PagerVo {
	private List datas ;
	private long total ;
	
	
	/**
	 * 
	 */
	public PagerVo() {
		super();
	}
	/**
	 * @param datas
	 * @param total
	 */
	public PagerVo(List datas, long total) {
		super();
		this.datas = datas;
		this.total = total;
	}
	/**
	 * @return the datas
	 */
	public List getDatas() {
		return datas;
	}
	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List datas) {
		this.datas = datas;
	}
	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagerVo [datas=" + datas.size() + ", total=" + total + "]";
	}
	
	

}
