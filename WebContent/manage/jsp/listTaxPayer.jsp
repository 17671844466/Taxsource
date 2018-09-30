<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>">
<head>

<link rel="stylesheet" type="text/css" href="static/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="static/js/easyui/themes/icon.css">
<script type="text/javascript" src="static/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function(){
		getData();
		$("#searchBtn").click(function(){
			getSerch();
		});
		
		$("#updateBtn").click(function(){
			
			openTopWindow(
			{
				width:820,
				height:500,
				title:"添加纳税信息",
				url:"manage/jsp/addTaxpayer.jsp"
			}		
			);
		});
	});

	function getData(){
		$("#tb").datagrid({
			url:'getTaxPayer.do',
			method:'post',
			columns:[[
			          {field:'payer1',title:'是否选中',width:100,checkbox:true},
			          {field:'payerCode',title:'纳税人识别号',width:100},
			          {field:'payerName',title:'纳税人名称',width:100,sortable:true},
			          {field:'taxOrganId',title:'所属税务机关',width:100},
			          {field:'bizScope',title:'所属行业',width:100},
			          {field:'legalPerson',title:'法人代表',width:100},
			          {field:'legalIdCard',title:'法人身份证号码',width:100},
			          {field:'finaceName',title:'主管财务',width:100},
			          {field:'finaceIdCard',title:'财务身份证号码',width:100},
			          {field:'taxerName',title:'办税人员',width:100},
			          {field:'recordDate',title:'录入日期',width:100},
			          {field:'operation',title:'操作',width:100},
			        ]],
			//singleSelect:true,
			rownumbers:true,
			pagination:true,
			pageList:[10,20,30,40],
			pageSize:10,
			fitColumns:true,
			//pagePosition:'top',
			toolbar:'#tb1',
			
		});
	}
	
	function getSerch(){
		 var payerCode = $("#payerCode").val();
		 var payerName = $("#payerName").val();
		$("#tb").datagrid('load',{
			"payerCode" : payerCode,
			"payerName" : payerName
		})
	}
	
	/**
     *打开在父窗口中打开window
     */
    function openTopWindow(options){
        options = !options ? {} :options;
        options.width = !options.width ? 500 : options.width;
        options.height = !options.height ? 400 : options.height;
        options.url = !options.url ? "404.html" : options.url;
        options.title = !options.title ? "" : options.title;

        parent.$("#wd").window({
            title : options.title,
            width: options.width,
            height: options.height,
            content : "<iframe scrolling='no' frameborder='0' border='0' height='100%' width='100%' src='"+options.url+"'></iframe>",
            modal:true,
            resizable:false,
            collapsible:false,
            minimizable:false
        });
    }
	
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div id="wd"></div>
	 <div id="tb1" style="padding:0 30px;">
	        纳税人识别号: <input id = "payerCode" class="easyui-textbox" name="payerCode" type="text" style="width:166px;height:35px;line-height:35px;"/>
	        纳税人姓名: <input id = "payerName" class="easyui-textbox"  name="payerName" type="text"  style="width:166px;height:35px;line-height:35px;"/>
        <a href="javascript:void(0);" id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
         <a href="javascript:void(0);" id="updateBtn" class="easyui-linkbutton" iconCls="icon-update">添加纳税信息</a>
      </div>
	<table id = "tb"></table>
	
	
</body>
</html>