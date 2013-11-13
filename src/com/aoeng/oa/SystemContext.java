/**
 * 
 */
package com.aoeng.oa;

/**
 * Oct 30, 2013 6:08:24 PM
 * 
 */
public class SystemContext
{
	private static ThreadLocal offSet = new ThreadLocal();
	private static ThreadLocal pageSize = new ThreadLocal();

	/**
	 * @return the offSet
	 */
	public static int getOffSet() {
		Integer _offSet = (Integer) offSet.get();
		if (null == _offSet) {
			return 0;
		}
		return _offSet;
	}

	/**
	 * @param offSet
	 *            the offSet to set
	 */
	public static void setOffSet(int _offSet) {
		offSet.set(_offSet);
	}

	/**
	 * @return the pageSize
	 */
	public static int getPageSize() {
		Integer _pageSize = (Integer) pageSize.get();
		if (null == _pageSize) {
			return 0;
		}
		return _pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public static void setPageSize(int _pageSize) {
		pageSize.set(_pageSize);
	}

	public static void removeOffSet() {
		offSet.remove();
	}

	public static void removePageSize() {
		pageSize.remove();
	}

}
