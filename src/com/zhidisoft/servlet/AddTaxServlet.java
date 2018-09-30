package com.zhidisoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.entity.Tb_taxPayer;
import com.zhidisoft.util.JdbcDB;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns = "/addTax.do")
public class AddTaxServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		String payerCode = req.getParameter("payerCode");
		String payerName = req.getParameter("payerName");
		String bizAddress = req.getParameter("bizAddress");
		String taxerMobile = req.getParameter("taxerMobile");
		String taxOrgan = req.getParameter("taxOrganId");
		int taxOrganId = Integer.parseInt(taxOrgan);
		String instruy = req.getParameter("instruyId");
		int instruyId = Integer.parseInt(instruy);
		String bizScope = req.getParameter("bizScope");
		String invoiceType = req.getParameter("invoiceType");
		String taxerName = req.getParameter("taxerName");
		String legalIdCard = req.getParameter("legalIdCard");
		String legalPerson = req.getParameter("legalPerson");
		String finaceName = req.getParameter("finaceName");
		String finaceIdCaed = req.getParameter("finaceIdCaed");
		String user = req.getParameter("userId");
		int userId = Integer.parseInt(user);
		Date recordDate = new Date();
		//System.out.println(recordDate);
		Tb_taxPayer payer = new Tb_taxPayer();
		payer.setPayerCode(payerCode);
		payer.setPayerName(payerName);
		payer.setBizAddress(bizAddress);
		payer.setTaxerMobile(taxerMobile);
		payer.setTaxOrganId(taxOrganId);
		payer.setIndustryId(instruyId);
		payer.setBizScope(bizScope);
		payer.setInvoiceType(invoiceType);
		payer.setLegalIdCard(legalIdCard);
		payer.setLegalPerson(legalPerson);
		payer.setFinaceIdCard(finaceIdCaed);
		payer.setFinaceName(finaceName);
		payer.setUserId(userId);
		payer.setRecordDate(recordDate);
		payer.setTaxerName(taxerName);
		//System.out.println(payer);
		
		
		
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();	
		long l = JdbcDB.save(payer);
		if (l>0) {
			json.put("data", "增加成功");
			
		}else {
			json.put("data", "增加失败");
		}

		out.write(json.toString());
		out.flush();
		out.close();
		//System.out.println(json);
	}
}
