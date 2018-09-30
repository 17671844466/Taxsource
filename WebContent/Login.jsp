<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>河南省金税三期税源信息管理系统</title>
<link href="static/css/base.css" rel="stylesheet">
<link href="static/css/login/login.css" rel="stylesheet">
<link href="static/easyui/uimaker/easyui.css" rel="stylesheet">

<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
</head>
<body>
	<div class="login-hd">
		<div class="left-bg"></div>
		<div class="right-bg"></div>
		<div class="hd-inner">
			<span class="sys-name">xx省国税税源信息管理系统</span>
		</div>
	</div>
	<div class="login-bd">
		<div class="bd-inner">
			<div class="inner-wrap">
				<div class="lg-zone">
					<div class="lg-box">
						<div class="lg-label">
							<h4>用户登录</h4>
						</div>
						<div id="alert-error" class="alert alert-error alert-hidden">
							<i class="iconfont">&#xe62e;</i> <span id="login-info">请输入用户名</span>
						</div>
						<form>
							<div class="lg-username input-item clearfix">
								<i class="iconfont">&#xe60d;</i> <input id="username"
									value="<%%>" type="text" placeholder="系统账号">
							</div>
							<div class="lg-password input-item clearfix">
								<i class="iconfont">&#xe634;</i> <input id="password"
									type="password" placeholder="系统密码">
							</div>
							<div class="lg-check clearfix">
								<div class="input-item">
									<i class="iconfont">&#xe633;</i> <input id="captchaValue"
										type="text" placeholder="验证码">
								</div>
								<img id="captchaimg" src="CaptchaServlet" height="41px"
									width="114px">
							</div>
							<div class="tips clearfix">
								<label><input id="cookie" type="checkbox"
									checked="checked">记住用户名</label> <a href="javascript:;"
									class="forget-pwd">忘记密码？</a>
							</div>
							<div class="enter">
								<a href="javascript:void(0);" class="supplier" id="login">登录</a>
								<a href="Login.jsp "
									class="purchaser">重置</a>
							</div>
						</form>
					</div>
				</div>
				<div class="lg-poster"></div>
			</div>
		</div>
	</div>
	<div class="login-ft">
		<div class="ft-inner">
			<div class="about-us">
				<a href="javascript:;">关于我们</a> <a href="javascript:;">法律声明</a> <a
					href="javascript:;">服务条款</a> <a href="javascript:;">联系方式</a>
			</div>
			<div class="address">地址：湖北省洪山区光谷国际广场B座24楼&nbsp;邮编：430070&nbsp;&nbsp;Copyright&nbsp;©&nbsp;2015&nbsp;-&nbsp;2017&nbsp;专注系统集成&nbsp;版权所有</div>
			<div class="other-info">建议使用IE8及以上版本浏览器&nbsp;京ICP备&nbsp;17003078号&nbsp;E-mail：service@zhidisoft.com</div>
		</div>
	</div>
	<div id="dialog" style="padding: 20px; line-height: 1.5em;"></div>
</body>
<script type="text/javascript">
	$(function() {
		$("#alert-error").hide();
		/* 刷新验证码 */
		$("#captchaimg").click(function() {
			$("#captchaimg").attr('src', 'CaptchaServlet');
		});
		// 登录验证  
		$("#login").click(function() {

			// 做表单输入校验  
			var username = $("#username"); //账号
			var password = $("#password"); //密码
			var captchaValue = $("#captchaValue"); //验证码
			var msg = "";
			if ($.trim(username.val()) == "") {
				msg = "用户名不能为空！";

			} else if (!/[0-9a-zA-Z]{4,20}$/.test($.trim(username.val()))) {
				msg = "用户名格式不正确！";

			} else if ($.trim(password.val()) == "") {
				msg = "密码不能为空！";

			} else if (!/^(\w){6,20}$/.test($.trim(password.val()))) {
				msg = "密码格式不正确！";

			} else if ($.trim(captchaValue.val()) == "") {
				msg = "验证码不能为空！";

			} else if (!/^[0-9a-zA-Z]{4}$/.test($.trim(captchaValue.val()))) {
				msg = "验证码格式不正确！";

			}
			if (msg != "") {
				$("#alert-error").show();
				$("#login-info").text(msg);
				$("#captchaimg").attr('src', 'CaptchaServlet');
			} else {
				$("#alert-error").hide();
				login();
			}

		});

		// 为document绑定onkeydown事件监听是否按了回车键  
		$(document).keydown(function(event) {
			if (event.keyCode === 13) { // 按了回车键  
				$("#login").trigger("click");
			}
		});

		//把字符串分割成几个段，然后遍历整个数组。
		//alert( document.cookie.split("=")[0]);
		var cook = document.cookie.split("=")[1];
		//放入cookie值
		$("#username").val(cook);

		//登录验证
		function login() {
			var username = $("#username").val(); //账号
			var password = $("#password").val(); //密码
			var captchaValue = $("#captchaValue").val(); //验证码	

			//cookie选中返回true，否则为false
			var check = $("#cookie").is(":checked");
			//alert(check);
			if (check == false) {
				var loginCookie = "";
			} else {
				var loginCookie = $("#cookie").val(); //记住密码
			}

			$.post("CaptchaServlet.do", {
				"captcha" : captchaValue
			}, function(data) {
				if (data.success) {
					$("#login-info").text(data.msg)
					//loginUp();

					$.post("loginServlet.do", {
						"username" : username,
						"password" : password,
						"cookie" : loginCookie
					}, function(data) {
						if (data.success) {
							document.location.href = "manage/jsp/index.jsp"
						} else {
							$("#alert-error").show();
							$("#login-info").text("账号或密码错误");
							$("#captchaimg").attr('src', 'CaptchaServlet');
						}
					}, "json")

				} else {
					$("#alert-error").show();
					$("#login-info").text(data.msg);
					$("#captchaimg").attr('src', 'CaptchaServlet');
					//alert(data.msg);
				}
			}, "json");
		}

	});
</script>
</html>


