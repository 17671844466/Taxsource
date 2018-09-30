package com.zhidisoft.source;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.TaxPayerDao;
import com.zhidisoft.dao.TaxSourceDao;
import com.zhidisoft.entity.Tb_taxPayer;

import com.zhidisoft.util.FormatDate;
import com.zhidisoft.util.JdbcDB;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@WebServlet(urlPatterns = "/Survey.do")
public class SurveyServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");

		// 设置响应参数，创建json对象
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		JSONObject json = new JSONObject();
		int pageNum = 1;
		if (req.getParameter("page") != null
				&& !req.getParameter("page").isEmpty()) {
			pageNum = Integer.parseInt(req.getParameter("page"));
		}
		int pageSize = 10;
		if (req.getParameter("rows") != null
				&& !req.getParameter("rows").isEmpty()) {
			pageSize = Integer.parseInt(req.getParameter("rows"));
		}

		Map<String, String> params = new HashMap<String, String>();
		// 获取查询参数
		String payerCode = req.getParameter("payerCode");
		params.put("payerCode", payerCode);
		String payerName = req.getParameter("payerName");
		params.put("payerName", payerName);
		String subOrganId = req.getParameter("subOrganId");
		if ("-1".equals(subOrganId)) {
			params.put("subOrganId", null);
		} else {
			params.put("subOrganId", subOrganId);
		}
		String startDate = req.getParameter("startDate");
		params.put("startDate", startDate);
		String endDate = req.getParameter("endDate");
		params.put("endDate", endDate);
		String industryId = req.getParameter("industryId");
		if ("-1".equals(industryId)) {
			params.put("industryId", null);
		} else {
			params.put("industryId", industryId);
		}
		List<Map<String, String>> result = TaxSourceDao.getSearchTasks(pageNum,
				pageSize, params);
		JSONArray jsonArray = new JSONArray();
		// 获得查询map集合，计算时间差（可以再sql语句中直接获取）
		for (Map<String, String> map : result) {
			String timeOut = FormatDate.getTimeOut(map.get("executeTime"),
					map.get("recordTaskDate"));
			map.put("timeout", timeOut);
			jsonArray.add(map);
		}
		int totalRows = TaxSourceDao.getSearchRows();
		json.put("total", totalRows);
		json.put("rows", jsonArray);
		out.print(json);
		out.flush();
		out.close();
	
	}
} 
