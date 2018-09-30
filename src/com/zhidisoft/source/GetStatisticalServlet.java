package com.zhidisoft.source;

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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/getStatisticalServlet.do")
public class GetStatisticalServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置响应字符编码，类型，创建json对象
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		JSONObject json = new JSONObject();
		// 获取参数 pageNum, pageSize,totalPages
		int pageNum = 1;
		if (req.getParameter("page") != null
				&& !req.getParameter("page").isEmpty()) {
			pageNum = Integer.parseInt(req.getParameter("page"));
		}
		int pageSize = 10;
		if (req.getParameter("size") != null
				&& !req.getParameter("size").isEmpty()) {
			pageSize = Integer.parseInt(req.getParameter("size"));
		}
		int totalRows = TaxPayerDao.getTotalRows();
		String payerName = req.getParameter("payerName");
		String payerCode = req.getParameter("payerCode");
		// 传递参数
		json.put("total", totalRows);
		List<Map<String, String>> result = TaxPayerDao.getStatistical(pageNum,
				pageSize, payerCode, payerName);
		JSONArray jsonArray = new JSONArray();
		for (Map<String, String> map : result) {
			jsonArray.add(map);
		}
		json.put("rows", jsonArray);
		out.print(json);
		out.flush();
		out.close();
	}
}
