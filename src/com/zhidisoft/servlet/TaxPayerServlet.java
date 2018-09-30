package com.zhidisoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.TaxPayerDao;
import com.zhidisoft.entity.Tb_taxPayer;
import com.zhidisoft.util.JdbcDB;
import com.zhidisoft.util.MySqlDB;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns = "/getTaxPayer.do")
public class TaxPayerServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");

		int pageCount = 1 ;
		int pageNum = 10 ;
		//获取 当前页 和 当前页面显示个数
		String pageCountStr = req.getParameter("page");
		String pageNumStr = req.getParameter("rows");

		
		/*System.out.println(pageCountStr);
		System.out.println(pageNumStr);*/
		
		
		if(null != pageCountStr && !"".equals(pageCountStr)){
			pageCount = Integer.parseInt(pageCountStr);
		}
		
		if(null != pageNumStr && !"".equals(pageNumStr)){
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		
		//获取 查询条件
		String payerCode = req.getParameter("payerCode");//纳税人识别号
		String payerName = req.getParameter("payerName");
		/*System.out.println(payerCode);
		System.out.println(payerName);*/
		//sql 拼接
		String sql = "select * from tb_tax_payer where 1=1 ";
		//select * from tb_tax_payer left join tb_industry on tb_tax_payer.id=tb_industry.id where 1=1 		
		
		if(null != payerCode && !"".equals(payerCode)){
			sql += " and payerCode like '%"+payerCode+"%'";
		}
		if(null != payerName && !"".equals(payerName)){
			sql += " and payerName like '%"+payerName+"%' ";
		}
		
		String sql2 = sql.replace("*", " count(id) count_id "); 
		List<Map<String, Object>> list1 = JdbcDB.doExcuQuery(sql2);
		String page = list1.get(0).get("count_id").toString();
		//System.out.println(sql2);
	
		
		sql += " limit " + (pageCount -1)*pageNum +" , " + pageNum;
		
		List<Tb_taxPayer> list = MySqlDB.chaSql1(Tb_taxPayer.class, sql);
		
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		json.put("rows", list);
		json.put("total", page);
		//System.out.println(json);


		out.write(json.toString());
		out.flush();
		out.close();
	}
}
