/**
 * 
 */
package com.aoeng.oa.service;

import java.util.List;

import com.aoeng.oa.model.ActionMethodOper;
import com.aoeng.oa.model.ActionResource;

/**
 * Nov 6, 20132:01:13 PM
 * 
 */
public interface ResourceService {
	/**
	 * 重建 ActionResource 资源对象
	 */
	public void rebuildActionResource();

	/**
	 * 查询ActionResource 资源管理的顶级 资源项目
	 * 
	 * @return
	 */
	public List<ActionResource> findAllTopActionResources();

	/**
	 * 添加 ActionResource 对象
	 * 
	 * @param model
	 */
	public void addActionResource(ActionResource model);

	/**
	 * 通过 id 查找 ActionResource 对象
	 * 
	 * @param id
	 * @return
	 */
	public ActionResource findActionResourceById(int id);

	/**
	 * 更新 ActionResource
	 * 
	 * @param model
	 */
	public void updateActionResource(ActionResource model);

	/**
	 * 根据 id 删除 ActionResource 对象
	 * 
	 * @param id
	 */
	public void delActionResource(int id);

	/**
	 * 给 标识为 id 的ActionResource 添加 操作
	 * 
	 * @param id
	 * @param oper
	 */
	public void addActionResourceOper(int id, ActionMethodOper oper);

	/**
	 * 删除标识为 id 对象 ActionResource 上面的操作
	 * 
	 * @param id
	 * @param operSn
	 */
	public void delActionResourceOper(int id, String operSn);

}
