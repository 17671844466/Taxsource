package com.zhidisoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.entity.Tb_user;
import com.zhidisoft.util.MD5DB;
import com.zhidisoft.util.MySqlDB;

import javafx.scene.control.Alert;
import net.sf.json.JSONObject;

@WebServlet("/CaptchaServlet.do")
public class ValiDateServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	// 验证码
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		String captcha = req.getParameter("captcha");
		String validateCaptcha = req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString();
		if (captcha.equals(validateCaptcha)) {
			json.put("success", true);
			json.put("msg", "");
			
		} else {
			json.put("success", false);
			json.put("msg", "验证码错误");
		}
		out.print(json);
		out.flush();
		out.close();
	}
	
	
	
}
