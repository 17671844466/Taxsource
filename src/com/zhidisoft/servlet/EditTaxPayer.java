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
import com.zhidisoft.entity.Tb_taxPayer;
import com.zhidisoft.util.JdbcDB;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns = "/editTaxPayer.do")
public class EditTaxPayer extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		
		String id1 = req.getParameter("id");
		int id = Integer.parseInt(id1);
		List<Tb_taxPayer> list = JdbcDB.excuQuery(Tb_taxPayer.class, "SELECT * FROM tb_tax_payer where id = "+id);
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		json.put("data", list);
		//System.out.println(json);
		out.write(json.toString());
		out.flush();
		out.close();

		
	}
}
