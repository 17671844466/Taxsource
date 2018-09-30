package com.zhidisoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.User;

import com.zhidisoft.dao.UserNameDao;
import com.zhidisoft.entity.Tb_user;
import com.zhidisoft.util.MD5DB;

import net.sf.json.JSONObject;

@WebServlet("/loginServlet.do")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	// 账号密码判断
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String cook = req.getParameter("cookie");
		//System.out.println(cook);
		password = MD5DB.getMD5(password);
		//System.err.println("密码"+password);
		//String cook = req.getParameter("cookie");
		List userList = UserNameDao.getUser(username);

		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		

		if (null != userList && userList.size() <= 0) {
			//System.out.println("你搞我，用户名都不存在");
			json.put("success", false);
			json.put("msg", "用户名不存在");
			
		} else {
			//System.out.println("你搞我，用户名存在");
			for (int i = 0; i < userList.size(); i++) {

				Tb_user object = (Tb_user) userList.get(i);
				if (password.equals(object.getPassword())) {
					//cookie
					if ("on".equals(cook)) {
						Cookie cookie = new Cookie("username", username);
						cookie.setMaxAge(60 * 60 * 24 * 7);
						resp.addCookie(cookie);
					}else {
						Cookie cookie = new Cookie("username", "");
				  		cookie.setMaxAge(0);
				  		resp.addCookie(cookie);
					}

					HttpSession session = req.getSession();
					session.setAttribute("user", object);
					json.put("success", true);
					json.put("msg", "");
				
					
					
				} else {
					// 重定向到login.jsp页面
					json.put("success", false);
					json.put("msg", "账号或密码错误");
					
				}
			}

		}
		out.print(json);
		out.flush();
		out.close();
		

	}
}