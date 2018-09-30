<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<base href="<%=basePath%>">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>添加纳税人</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css"
	href="static/easyui/uimaker/easyui.css">
<link rel="stylesheet" type="text/css"
	href="static/easyui/uimaker/icon.css">
<link rel="stylesheet" type="text/css" href="static/css/edit.css">
</head>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/js/calendar.js"></script>
<script type="text/javascript"></script>
<body>
	<div id="cc" class="container">
		<div class="content">
			<div title="纳税人信息" data-options="closable:false" class="basic-info">
				<div class="column">
					<span class="current">添加纳税人信息</span>
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">纳税人识别号</td>
							<td class="kv-content"><input type="text" id="payerCode"
								placeholder="纳税人识别号"></td>
							<td class="kv-label">纳税人名称</td>
							<td class="kv-content"><input type="text" id="payerName"
								placeholder="纳税人名称"></td>
						</tr>
						<tr>
							<td class="kv-label">生产经营地址</td>
							<td class="kv-content"><input type="text" id="bizAddress"
								placeholder="生产经营地址"></td>
							<td class="kv-label">经营地电话</td>
							<td class="kv-content"><input type="text" id="taxerMobile"
								placeholder="生产经营地电话"></td>
						</tr>
						<tr>
							<td class="kv-label">所属税务机关</td>
							<td class="kv-content">
							<select id="taxOrganId">
									<option value="-1"  id="taxOrgan">请选择所属税务机关</option>
							</select></td>
							<td class="kv-label">行业</td>
							<td class="kv-content">
							<select id="instruyId">
									<option value="-1" id="instruy">请选择纳税人行业</option>
							</select></td>
						</tr>
						<tr>
							<td class="kv-label">生产经营范围</td>
							<td class="kv-content"><input type="text" id="bizScope"
								placeholder="生产经营范围"></td>
							<td class="kv-label">票种核定</td>
							<td class="kv-content"><select id="invoiceType">
									<option value="-1">请选择发票种类</option>
									<option value="1">增值税普通发票</option>
									<option value="2">增值税专用发票</option>
							</select></td>
						</tr>
						<tr>
							<td class="kv-label">法人代表人</td>
							<td class="kv-content"><input type="text" id="legalPerson"
								placeholder="法人姓名"></td>
							<td class="kv-label">法人身份证号</td>
							<td class="kv-content"><input type="text" id="legalIdCard"
								placeholder="法人代表身份证号码"></td>
						</tr>
						<tr>
							<td class="kv-label">主管财务</td>
							<td class="kv-content"><input type="text" id="finaceName"
								placeholder="主管财务"></td>
							<td class="kv-label">财务身份证号</td>
							<td class="kv-content"><input type="text" id="finaceIdCaed"
								placeholder="财务身份证号"></td>
						</tr>
						<tr>
							<td class="kv-label">办税专员</td>
							<td class="kv-content"><select id="userId">
									<option value="-1">请选择办税专员</option>
									<option value="1">张三</option>
									<option value="2">李四</option>
							</select></td>
							<td class="kv-label">录入日期</td>
							<td class="kv-content" id="recordDate">2017-01-20</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn-selection">
				<a href="javascript:void(0);" class="easyui-linkbutton save-btn"
					data-options="selected:true" id="BaoCun">保存</a> <a
					href="javascript:void(0);" class="easyui-linkbutton reset-btn"
					data-options="selected:true" id="ChongZhi">重置</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		Organ();
		instruy();
		//ZengJia();
		//ajax请求获取外键值
		function Organ() {

			$.ajax({
				url : "getOrganServlet.do",
				type : "post",
				data : {},
				async : true,
				datetype : "json",
				success : function(data, status) {
					//console.log(data);
					if (data) {
						var str = "";
						$.each(data.data, function(index, obj) {
							str += "<option value='"+obj.id+"'>"
									+ obj.organName + "</option>";
									//alert(obj.id)
						})
						$("#taxOrganId").append(str)
					}
				},
				error : function(data, status) {
					alert("失败....🤗");
				}
			});
		}
		function instruy() {

			$.ajax({
				url : "getindustryServlet.do",
				type : "post",
				data : {},
				async : true,
				datetype : "json",
				success : function(data, status) {
					//console.log(data);
					if (data) {
						var str = "";
						$.each(data.data, function(index, obj) {
							str += "<option value='"+obj.id+"'>"
									+ obj.industryName + "</option>";
						})
						$("#instruyId").append(str)

						//alert("成功 ....🤗"+data);

					}
				},
				error : function(data, status) {
					alert("失败....🤗");
				}
			});
		}
		$("#BaoCun").click(function() {

			ZengJia();
		});
		$("#ChongZhi").click(function() {

		 //alert(231)
		 $('#cc').form('clear');  
			
		});
		function ZengJia() {
			var payerCode = $("#payerCode").val();
			var payerName = $("#payerName").val();
			var bizAddress = $("#bizAddress").val();
			var taxerMobile = $("#taxerMobile").val();
			var taxOrganId = $("#taxOrganId").val();
			//alert(taxOrganId)
			var instruyId = $("#instruyId").val();
			//alert(instruyId)
			var bizScope = $("#bizScope").val();
			var invoiceType = $("#invoiceType").val();
			if(invoiceType == 1){
				var invoiceType = "增值税普通发票";
			}else if(invoiceType == 2){
				var invoiceType = "增值税专用发票";
			}
			var legalPerson = $("#legalPerson").val();
			var legalIdCard = $("#legalIdCard").val();
			var finaceName = $("#finaceName").val();
			var finaceIdCaed = $("#finaceIdCaed").val();
			var userId = $("#userId").val();
			if(userId == 1){
				var taxerName = "张三";
			}else if(userId == 2){
				var taxerName = "李四";
			}
			//var finaceIdCaed = $("#finaceIdCaed").val();
			//alert(userId);
			$.ajax({
				url : "addTax.do",
				type : "post",
				data : {
					"taxerName" : taxerName,
					"payerCode" : payerCode,					
					"payerName" : payerName,
					"bizAddress" : bizAddress,
					"taxerMobile" : taxerMobile,
					"taxOrganId" : taxOrganId,
					"instruyId" : instruyId,
					"bizScope" : bizScope,
					"invoiceType" : invoiceType,
					"legalPerson" : legalPerson,
					"legalIdCard" : legalIdCard,
					"finaceName" : finaceName,
					"finaceIdCaed" : finaceIdCaed,
					"userId" : userId,
				},
				async : true,
				datetype : "json",
				success : function(data, status) {
					//console.log(data);
					if (data) {
						alert(data.data);
						parent.$("#topWindow").window('close');
					}
					;
				},
				error : function(data, status) {
					alert("失败....🤗");
				}
			});
		}

	})
</script>
</html>