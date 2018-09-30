package com.zhidisoft.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.entity.Tb_taxPayer;


public class TaxPayerDao {
	private static TaxPayerDao instance;
	private TaxPayerDao(){}
	public static TaxPayerDao getinstance(){
		if(instance==null){
			instance = new TaxPayerDao();
		}
		return instance;
	}
	/**
	 * ��ȡ���е���˰�˶���
	 * @param pageNum ҳ
	 * @param pageSize ҳ���¼��
	 * @return ���еļ�¼����
	 */
	public static List<Tb_taxPayer> getPayers(int pageNum,int pageSize){
		List<Tb_taxPayer> payers = new ArrayList<Tb_taxPayer>();		
		List<Map<String, String>> list = DBUtil.query("select * from tb_tax_payer limit ?,?", (pageNum-1)*pageSize,pageSize);
		for(Map<String, String> m:list){
			Tb_taxPayer payer = new Tb_taxPayer();
			try {
				BeanUtils.populate(payer, m);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			payers.add(payer);
		}
		return payers;
	}
	
	
	/**
	 * ���ز�ѯ��˰�˵Ľ����
	 * @param pageNum ҳ
	 * @param pageSize ҳ���¼��
	 * @param payerCode ��˰�˱��
	 * @param payerName ��˰������
	 * @return ��ѯ�Ľ��
	 */
	public static List<Map<String, String>> getSearchResult(int pageNum,int pageSize,String payerCode,String payerName){	
		boolean checkCode = payerCode!=null&&payerCode.toString().length()>0;
		boolean checkName = payerName!=null&&payerName.toString().length()>0;
		String sql = "select * from tb_tax_payer p JOIN tb_industry i join tb_tax_organ o join tb_user u on p.taxOrganId=o.id and p.industryId=i.id and p.userId=u.id where removeState=0 ";
		if(checkCode){
			sql=sql+"and p.payerCode = "+payerCode;
		}
		if(checkName){
			sql=sql+" and payerName like '%"+payerName+"%' ";
		}
		sql=sql+" limit ?,?";		
		List<Map<String, String>> list = DBUtil.query(sql, (pageNum-1)*pageSize,pageSize);
		return list;
	}
	
	/**
	 * ��ȡ���еļ�¼����
	 * @return ��¼����
	 */
	public static int getTotalRows(){
		return Integer.parseInt(DBUtil.query("select count(*) c from tb_tax_payer p JOIN tb_industry i join tb_tax_organ o join tb_user u on p.taxOrganId=o.id and p.industryId=i.id and p.userId=u.id ").get(0).get("c"));
	}
	/**
	 * ��ȡ��ѯ�ļ�¼��
	 * @param payerCode ��ѯ��˰�˱��
	 * @param payerName ��ѯ����˰������
	 * @return ��ѯ����˰�˾ּ�¼��
	 */
	public static int getSearchRows(String payerCode,String payerName){
		boolean checkCode = payerCode!=null&&payerCode.toString().length()>0;
		boolean checkName = payerName!=null&&payerName.toString().length()>0;
		String sql = "select * from tb_tax_payer p JOIN tb_industry i join tb_tax_organ o join tb_user u on p.taxOrganId=o.id and p.industryId=i.id and p.userId=u.id where removeState=0 ";
		if(checkCode){
			sql=sql+"and p.payerCode = "+payerCode;
		}
		if(checkName){
			sql=sql+" and payerName like '%"+payerName+"%' ";
		}		
		List<Map<String, String>> list = DBUtil.query(sql);
		return list.size();
	}
	/**
	 * �����˰��
	 * @param payer ��˰�˶���
	 * @return ����Ƿ�ɹ�
	 */
	public static boolean addPayer(Tb_taxPayer payer){
		int row = DBUtil.add(payer,"tb_tax_payer");
		return row==1;
	}
	/**
	 * ɾ����˰��
	 * @param id ִ����˰��id
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public static boolean deletePayer(String id) {	
		int rows = DBUtil.update("update tb_tax_payer set removeState=1 where id = ?" ,id);
		return rows==1;
	}
	
	/**
	 * ������˰��
	 * @param payer ���������������˰�˶���
	 * @param id ִ��id
	 * @return �Ƿ���³ɹ�
	 */
	public static boolean updatePayer(Tb_taxPayer payer,Integer id){
		int rows = DBUtil.edit(payer, "tb_tax_payer", id);
		return rows==1;
	}
	/**
	 * ��ȡָ��id��˰�˶���
	 * @param id ָ��id
	 * @return ��˰�˶���
	 */
	public static TaxPayer getPayer(String id){
		Map<String ,String> row = new HashMap<String, String>();
		if(id!=null&&id.length()>0){
			row = DBUtil.queryRow("select * from tb_tax_payer where id = ?", Integer.parseInt(id));
		}		
		TaxPayer payer = new TaxPayer(); 
		try {
			BeanUtils.populate(payer, row);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return payer;
	}
	/**
	 * ͨ����˰��ʶ��Ż�ȡ��˰�˶���
	 * @param payerCode ��˰��ʶ���
	 * @return ��˰�˶���
	 */
	public static TaxPayer getPayerByCode(String payerCode){
		Map<String ,String> row = new HashMap<String, String>();
		if(payerCode!=null&&payerCode.length()>0){
			List<Map<String, String>> list = DBUtil.query("select * from tb_tax_payer where payerCode = ?", payerCode);
			if(list!=null&&list.size()==1){
				row = list.get(0);
			}
		}
		TaxPayer payer = new TaxPayer(); 
		try {
			BeanUtils.populate(payer, row);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return payer;
	}
	/**
	 * ��ȡδ���������˰�˶���
	 * @param pageNum ҳ
	 * @param pageSize ҳ���¼��
	 * @param payerCode ��˰�˱���
	 * @param payerName ��˰������
	 * @return
	 */
	public static List<Map<String, String>> getStatistical(int pageNum,int pageSize,String payerCode,String payerName){	
		boolean checkCode = payerCode!=null&&payerCode.toString().length()>0;
		boolean checkName = payerName!=null&&payerName.toString().length()>0;
		String sql = "select * from tb_tax_payer p LEFT join tb_tax_source s on p.id=s.payerId JOIN tb_industry i join tb_tax_organ o join tb_user u on p.taxOrganId=o.id and p.industryId=i.id and p.userId=u.id  where s.id is null and p.removeState=0 ";
		if(checkCode){
			sql=sql+"and p.payerCode = "+payerCode;
		}
		if(checkName){
			sql=sql+" and payerName like '%"+payerName+"%' ";
		}
		sql=sql+" limit ?,?";	
		List<Map<String, String>> list = DBUtil.query(sql, (pageNum-1)*pageSize,pageSize);
		return list;
	}
}
