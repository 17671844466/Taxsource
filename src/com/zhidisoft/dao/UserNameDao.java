package com.zhidisoft.dao;

import java.util.List;
import java.util.Map;

import org.apache.catalina.User;


import com.zhidisoft.entity.Tb_user;
import com.zhidisoft.util.JdbcDB;
import com.zhidisoft.util.MySqlDB;



public class UserNameDao {
/**
 * 根据username获取用户对象
 * @return 用户对象
 */
	public static List getUser(String username){
		
		List<Tb_user> list = JdbcDB.excuQuery(Tb_user.class, "select * from tb_user where username = '"+ username+"'");
		return  list;
		
	}
		public static List getWord(String password){
		
			List<Tb_user> list = JdbcDB.excuQuery(Tb_user.class, "select * from tb_user where password = "+ password);
			return  list;
		
	}
	
}






















/**
 * @param  更改密码
 * @param oldPassword 旧密码
 * @param newPassword 新的密码
 * @return 是否修改成功
 
	public static boolean update(String username,String oldPassword,String newPassword){
		String uuid =null;
		String value = null;
	//通过username 拿到user对象属性
		List< Map<String, String>> result= DBUtil.query("select * from tb_user where username = ?", username);
		if(result!=null&&result.size()>0){
			//如果查询到结果 ，获取salt，password
			uuid = result.get(0).get("salt");
			value = result.get(0).get("password");
		}
		//密码uuid必须不能都是数字，加密
		String value1 = EncryptUtil.encryptMD5(oldPassword+uuid);
		//判断用户密码是否正确
		if(!value1.equals(value)){
			return false;
		}
		//对新密码加密
		String password = EncryptUtil.encryptMD5(newPassword+uuid);
		int row = DBUtil.update("update tb_user set password=? where username =?", password,username);
		return row==1;
	}
}
	*/