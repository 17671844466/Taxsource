package com.zhidisoft.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.entity.User;



public class UserDao {
/**
 * 根据username获取用户对象
 * @param username 指定username
 * @return 用户对象
 */
	public static User getUser(String username){
		Map<String, String> map = new HashMap<String, String>();
		
		List<Map<String, String>> list = DBUtil.query("select * from tb_user where username = ?", username);
		if(list!=null&&list.size()==1){
			map = list.get(0);
		}
		User user = new User();
		try {
				BeanUtils.populate(user,map);			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
/**
 * 
 * @param id
 * @return
 */
	public static User getUserById(Integer id){
		Map<String, String> result = DBUtil.queryRow("select * from tb_user where id = ?", id);
		User user = new User();
		try {
			if(!result.isEmpty()&&result.size()>0){
				BeanUtils.populate(user, result);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}	
	
}
