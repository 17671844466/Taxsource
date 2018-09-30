package com.zhidisoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.TaxPayerDao;
import com.zhidisoft.entity.Tb_industry;
import com.zhidisoft.entity.Tb_taxPayer;
import com.zhidisoft.entity.Tb_tax_organ;
import com.zhidisoft.util.JdbcDB;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns = "/getindustryServlet.do")
public class GetIndustryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		
		List<Tb_industry> list = JdbcDB.excuQuery(Tb_industry.class, "select * from tb_industry");
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();	
		
		json.put("data", list);

		out.write(json.toString());
		out.flush();
		out.close();
		//System.out.println(json);
	}
}
