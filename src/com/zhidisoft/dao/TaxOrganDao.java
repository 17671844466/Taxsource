package com.zhidisoft.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.entity.Tb_tax_organ;

public class TaxOrganDao {

	private static TaxOrganDao instance;
	private TaxOrganDao(){}
	public static TaxOrganDao getinstance(){
		if(instance==null){
			instance = new TaxOrganDao();
		}
		return instance;
	}
	
	/**
	 * 获取所有的部门
	 * @return 部门对象集
	 */
	public static List<Tb_tax_organ> getOrgans(){
		//获得结果集不一定有数据，需要判断结果中是否数据
		List<Map<String, String>> result = DBUtil.query("select * from tb_tax_organ ");
		List<Tb_tax_organ> organs = new ArrayList<Tb_tax_organ>();
		if(result!=null&&result.size()>0){
			for(Map<String, String> map:result){
				Tb_tax_organ organ = new Tb_tax_organ();
				try {
					BeanUtils.populate(organ, map);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				organs.add(organ);
			}	
		}
		return organs;
	}
	/**
	 * 根据id获取部门
	 * @param organId 部门id
	 * @return 部门对象
	 */
	public static Tb_tax_organ getOrgan(Integer organId) {
		Map<String, String> map = DBUtil.queryRow("select * from tb_tax_organ where id =?", organId);
		Tb_tax_organ organ = new Tb_tax_organ();
		if(map!=null&&map.size()>0){		
			try {
				BeanUtils.populate(organ, map);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return organ;
	}	
	/**
	 * 添加部门
	 * @param organ 根据表单创建的部门对象
	 * @return 是否添加成功
	 */
	public static boolean addOrgan(Tb_tax_organ organ){
		int rows = DBUtil.add(organ, "tb_tax_organ");
		return rows==1;
	}
	
}
