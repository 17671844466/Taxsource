package com.zhidisoft.source;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.IndustryDao;
import com.zhidisoft.dao.TaxOrganDao;
import com.zhidisoft.dao.TaxPayer;
import com.zhidisoft.dao.TaxPayerDao;
import com.zhidisoft.dao.TaxSourceDao;
import com.zhidisoft.dao.TaxerDao;
import com.zhidisoft.dao.UserDao;
import com.zhidisoft.entity.Tb_TaxSource;
import com.zhidisoft.entity.Tb_taxPayer;



@WebServlet("/toEditTaskServlet.do")
public class ToEditServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		//System.out.println("扫一扫一生一世");
		Tb_TaxSource task = TaxSourceDao.getTask(Integer.parseInt(id));
		TaxPayer payer = TaxPayerDao.getPayer(task.getPayerId().toString());
		req.setAttribute("task", task);
		req.setAttribute("payer", payer);
		req.setAttribute("organ", TaxOrganDao.getOrgan(payer.getTaxOrganId()));
		req.setAttribute("industry",
				IndustryDao.getIndustry(payer.getIndustryId()));
		req.setAttribute("subOrgan", TaxOrganDao.getOrgan(task.getSubOrganId()));//下达部门
		req.setAttribute("user", UserDao.getUserById(payer.getUserId()));
		req.setAttribute("executeTaxer",
				TaxerDao.getTaxerById(task.getExecuteId()));	//执行人
		req.setAttribute("approverTaxer",
				TaxerDao.getTaxerById(task.getApproverId()));//批准人
//		req.setAttribute("taskUser",
//				UserDao.getUserById(task.getRecordUserId()));
		req.setAttribute("taxers", TaxerDao.getTaxers());//所有人
		req.setAttribute("organs", TaxOrganDao.getOrgans());//所有下达部门
		req.getRequestDispatcher("/manage/jsp/editTask.jsp").forward(req, resp);
	}
}
