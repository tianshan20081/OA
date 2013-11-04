/**
 * 
 */
package com.aoeng.oa.utils;

import org.apache.struts2.ServletActionContext;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;

/**
 * Oct 31, 2013 3:12:53 PM
 * 
 */
public class JsonUtils {

	public static void toJson(Object obj) {
		try {
			String jsonStr = JSONMapper.toJSON(obj).render(false);
			System.out.println(jsonStr);
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(jsonStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
