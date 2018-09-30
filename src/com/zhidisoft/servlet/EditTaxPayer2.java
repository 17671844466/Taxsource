package com.zhidisoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
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

@WebServlet(urlPatterns = "/editTaxPayer2.do")
public class EditTaxPayer2 extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");

		String id1 = req.getParameter("id");
		int id = Integer.parseInt(id1);
		String payerName = req.getParameter("payerName");
		String bizAddress = req.getParameter("bizAddress");
		String bizAddressPhone = req.getParameter("bizAddressPhone");
		//System.out.println(bizAddressPhone);
		String bizScope = req.getParameter("bizScope");
		String legalPerson = req.getParameter("legalPerson");
		String legalIdCard = req.getParameter("legalIdCard");
		String finaceName = req.getParameter("finaceName");
		String finaceIdCard = req.getParameter("finaceIdCard");

		String instruy = req.getParameter("instruyId");
		int instruyId = Integer.parseInt(instruy);
		String taxOrgan = req.getParameter("taxOrganId");
		int taxOrganId = Integer.parseInt(taxOrgan);

		List list = JdbcDB.excuQuery(Tb_taxPayer.class, "SELECT * FROM tb_tax_payer where id = " + id);

		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();

		Tb_taxPayer payer = (Tb_taxPayer) list.get(0);
		payer.setBizAddress(bizAddressPhone);
		payer.setBizAddress(bizAddress);
		payer.setBizScope(bizScope);
		payer.setPayerName(payerName);
		payer.setLegalPerson(legalPerson);
		payer.setLegalIdCard(legalIdCard);
		payer.setFinaceName(finaceName);
		payer.setId(id);
		payer.setFinaceIdCard(finaceIdCard);
		payer.setIndustryId(instruyId);
		payer.setTaxOrganId(taxOrganId);
		payer.setBizAddressPhone(bizAddressPhone);
		long l = JdbcDB.update(payer);
		// System.out.println(l);

		if (l != 0) {
			json.put("data", "修改成功");

		} else {
			json.put("data", "修改失败");
		}
		// System.out.println(json);
		out.write(json.toString());
		out.flush();
		out.close();

	}
}
