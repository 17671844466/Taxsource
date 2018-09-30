package com.zhidisoft.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.entity.Tb_industry;



public class IndustryDao {

	private static IndustryDao instance;
	private IndustryDao(){}
	public static IndustryDao getinstance(){
		if(instance==null){
			instance = new IndustryDao();
		}
		return instance;
	}
	/**
	 * ��ȡ���е���ҵ
	 * @return ���е���ҵ����
	 */
	public static List<Tb_industry> getIndustrys(){
		List<Tb_industry> industrys = new ArrayList<Tb_industry>();
		List<Map<String, String>> result = DBUtil.query("select * from tb_industry");
		if(result!=null&&result.size()>0){
			for(Map<String, String> map:result){
				Tb_industry industry = new Tb_industry();
				try {
					BeanUtils.populate(industry, map);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				industrys.add(industry);
			}	
		}
		return industrys;
	}
	/**
	 * ����id��ȡ��ҵ����
	 * @param industryId ��ҵid
	 * @return ��ҵ����
	 */
	public static Object getIndustry(Integer industryId) {	
		Map<String, String> map = DBUtil.queryRow("select * from tb_industry where id =?", industryId);
		Tb_industry industry = new Tb_industry();
		if(map!=null&&map.size()>0){		
			try {
				BeanUtils.populate(industry, map);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return industry;
	}

}
