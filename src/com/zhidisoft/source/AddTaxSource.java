package com.zhidisoft.source;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.entity.Tb_taxPayer;
import com.zhidisoft.util.JdbcDB;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns="/addTaxSource.do")
public class AddTaxSource extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		
		String payerCode = req.getParameter("payerCode");
		
		List<Tb_taxPayer> list = JdbcDB.excuQuery(Tb_taxPayer.class, "select * from tb_tax_payer where payerCode= " +payerCode);
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		json.put("data", list);
		//System.out.println(json);
		out.write(json.toString());
		out.flush();
		out.close();
	}
}
