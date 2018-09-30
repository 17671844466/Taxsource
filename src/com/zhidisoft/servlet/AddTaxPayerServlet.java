/*package com.zhidisoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhidisoft.dao.TaxPayerDao;
import com.zhidisoft.entity.Tb_taxPayer;
import com.zhidisoft.entity.User;

import net.sf.json.JSONObject;

@WebServlet("/editTaxPayerServlet.do")
public class AddTaxPayerServlet extends HttpServlet {

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
		HttpSession session = req.getSession();
		// 建立对象，获取和设置参数
		Tb_taxPayer payer = new Tb_taxPayer();
		String id = req.getParameter("id");
		payer.setId(Integer.parseInt(id));
		String payerCode = req.getParameter("payerCode");
		payer.setPayerCode(payerCode);
		String payerName = req.getParameter("payerName");
		payer.setPayerName(payerName);
		String bizAddress = req.getParameter("bizAddress");
		payer.setBizAddress(bizAddress);
		String taxOrganId = req.getParameter("taxOrganId");
		if ("-1".equals(taxOrganId)) {
			payer.setTaxOrganId((Integer) null);
		} else {
			payer.setTaxOrganId(Integer.parseInt(taxOrganId));
		}
		String industryId = req.getParameter("industryId");
		if ("-1".equals(industryId)) {
			payer.setIndustryId(0);
		} else {
			payer.setIndustryId(Integer.parseInt(industryId));
		}
		String bizScope = req.getParameter("bizScope");
		payer.setBizScope(bizScope);
		String invoiceType = req.getParameter("invoiceType");
		payer.setInvoiceType(invoiceType);
		String legalPerson = req.getParameter("legalPerson");
		payer.setLegalPerson(legalPerson);
		String legalIdCard = req.getParameter("legalIdCard");
		payer.setLegalIdCard(legalIdCard);
		String finaceName = req.getParameter("finaceName");
		payer.setFinaceName(finaceName);
		String finaceIdCard = req.getParameter("finaceIdCard");
		payer.setFinaceIdCard(finaceIdCard);
		String taxerName = req.getParameter("taxerName");
		payer.setTaxerName(taxerName);
		String bizAddressPhone = req.getParameter("bizAddressPhone");
		payer.setBizAddressPhone(bizAddressPhone);
		Date date = new Date();
		//payer.setRecordDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
		User user = (User) session.getAttribute("user");
		payer.setUserId(user.getId());
		// 保存
		boolean b = TaxPayerDao.updatePayer(payer, Integer.parseInt(id));
		if (b) {
			json.put("success", true);
			json.put("msg", "保存成功");
		} else {
			json.put("success", false);
			json.put("msg", "保存失败");
		}
		out.print(json);
		out.flush();
		out.close();
	}
}
*/