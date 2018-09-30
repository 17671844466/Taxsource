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
import com.zhidisoft.entity.TaxSource;
import com.zhidisoft.entity.Tb_TaxSource;



@WebServlet("/getTaskInfoServlet.do")
public class GetTaskInfoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取task
		String id = req.getParameter("id");
		//System.out.println("哈哈哈哈哈哈哈哈 ");
		Tb_TaxSource task = TaxSourceDao.getTask(Integer.parseInt(id));
		TaxPayer payer = TaxPayerDao.getPayer(task.getPayerId().toString());
		req.setAttribute("task", task);
		req.setAttribute("payer", payer);
		req.setAttribute("organ", TaxOrganDao.getOrgan(payer.getTaxOrganId()));
		req.setAttribute("industry",
				IndustryDao.getIndustry(payer.getIndustryId()));
		req.setAttribute("subOrgan", TaxOrganDao.getOrgan(task.getSubOrganId()));
		req.setAttribute("user", UserDao.getUserById(payer.getUserId()));
		req.setAttribute("executeTaxer",
				TaxerDao.getTaxerById(task.getExecuteId()));
		req.setAttribute("approverTaxer",
				TaxerDao.getTaxerById(task.getApproverId()));
		req.setAttribute("taskUser",
				UserDao.getUserById(task.getRecordUserId()));
		req.getRequestDispatcher("/manage/jsp/taskInfo.jsp").forward(req, resp);
	}
}
