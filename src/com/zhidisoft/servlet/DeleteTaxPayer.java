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

import com.zhidisoft.entity.Tb_taxPayer;
import com.zhidisoft.util.JdbcDB;
import com.zhidisoft.util.MySqlDB;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns="/deleteTaxPayer.do")
public class DeleteTaxPayer extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		
		String id1 = req.getParameter("id");
		int id = Integer.parseInt(id1);
		// 执行删除操作
		Tb_taxPayer tb_taxPayer = new Tb_taxPayer();
		tb_taxPayer.setId(id);
		JdbcDB.delete(tb_taxPayer);
		
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		List<Map<String, Object>> list = MySqlDB.JieGuoJi();
		for (int i = 0; i < list.size(); i++) {
			String oldid = list.get(i).get("id").toString();
			if (!oldid.equals(id1)) {
				json.put("success", true);
				json.put("msg", "删除成功");
			}
		}

		out.write(json.toString());
		out.flush();
		out.close();
	}
}
