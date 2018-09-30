package com.zhidisoft.source;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zhidisoft.dao.IndustryDao;
import com.zhidisoft.dao.TaxOrganDao;
import com.zhidisoft.dao.TaxPayer;
import com.zhidisoft.dao.TaxPayerDao;
import com.zhidisoft.dao.TaxSourceDao;
import com.zhidisoft.dao.TaxerDao;
import com.zhidisoft.entity.TaxSource;
import com.zhidisoft.entity.Tb_TaxSource;



@WebServlet("/toAddTaskServlet.do")
public class ToAddTaskServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String payerCode = req.getParameter("payerCode");
		TaxPayer payer = null;
		if(StringUtils.isNotEmpty(payerCode)){
			payer = TaxPayerDao.getPayerByCode(payerCode);
		} 		
		if(StringUtils.isNotEmpty(id)){
			payer = TaxPayerDao.getPayer(id);			
			Tb_TaxSource task = TaxSourceDao.getTask(Integer.parseInt(id));
			req.setAttribute("task", task);
		}
		if(payer != null) {
		req.setAttribute("payer", payer);
			if(payer.getTaxOrganId() != null)
				req.setAttribute("organ", TaxOrganDao.getOrgan(payer.getTaxOrganId()));
			if(payer.getIndustryId() != null)
				req.setAttribute("industry",
						IndustryDao.getIndustry(payer.getIndustryId()));
		}
		req.setAttribute("taxers", TaxerDao.getTaxers());//所有人
		req.setAttribute("organs", TaxOrganDao.getOrgans());//所有下达部门
		req.getRequestDispatcher("/manage/jsp/addTask.jsp").forward(req, resp);
	}
}
