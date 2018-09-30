<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>修改纳税人</title>
    <link href="static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link href="static/css/edit.css" rel="stylesheet">

  </head>
  <body>
     <div class="container">
        <div class="content">
            <div title="纳税人信息" data-options="closable:false" class="basic-info">
                <div class="column"><span class="current">修改纳税人信息</span></div>
                <form id="editForm">
                <input name="id" id="id" type="hidden" ">
                <table class="kv-table">              	
                    <tbody>                 
                    <tr>
                        <td class="kv-label">纳税人识别号</td>
                        <td class="kv-content"><input type="text" id="payerCode" name="payerCode" readonly placeholder="纳税人识别号" ></td>
                        <td class="kv-label">纳税人名称</td>
                        <td class="kv-content"><input type="text" id="payerName" name="payerName" class="easyui-validatebox" data-options="required:true" placeholder="纳税人名称" ></td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营地址</td>
                        <td class="kv-content"><input type="text" id="bizAddress" name="bizAddress" class="easyui-validatebox" data-options="required:true" placeholder="生产经营地址" ></td>
                        <td class="kv-label">生产经营地电话</td>
                        <td class="kv-content"><input type="text" id="bizAddressPhone"  name="bizAddressPhone"  placeholder="生产经营地电话" ></td>
                    </tr>
                    <tr>
                        <td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select id="taxOrganId" name="taxOrganId" class="easyui-validatebox" data-options="required:true" > 
                            <option value="-1"  >请选择所属税务机关</option>
                                                                        
                            </select>
                        </td>
                        <td class="kv-label">行业</td>
                        <td class="kv-content">
                             <select id="instruyId" name="instruyId" class="easyui-validatebox" data-options="required:true" >  
                            <option value="-1" >请选择行业</option>
                                                              
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营范围</td>
                        <td class="kv-content">
                            <input type="text" name="bizScope" id="bizScope"  placeholder="生产经营范围">
                        </td>
                        <td class="kv-label">票种核定</td>
                        <td class="kv-content">
                            <select name="invoiceType" id="invoiceType">
                                <option value="-1" >请选择发票种类</option>
                                <option value="1" >增值税普通发票</option>
                                <option value="2" >增值税专用发票</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">法人代表人</td>
                        <td class="kv-content">
                            <input type="text" name="legalPerson" id="legalPerson"  placeholder="法人姓名">
                        </td>
                        <td class="kv-label">法人身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="legalIdCard" id="legalIdCard"  placeholder="法人代表身份证号码">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">主管财务</td>
                        <td class="kv-content">
                            <input type="text" name="finaceName" id="finaceName"  placeholder="主管财务">
                        </td>
                        <td class="kv-label">财务身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="finaceIdCard" id="finaceIdCard" value="" placeholder="财务身份证号">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">办税人员</td>
                        <td class="kv-content">
                           <label id="taxerName"></label>
                        </td>
                        <td class="kv-label" >录入日期</td>
                        <td class="kv-content" id="recordDate"></td>
                    </tr>
                    </tbody>                                
                </table>
                </form>
            </div>
            <div class="btn-selection">
                <a href="javascript:void(0);" class="easyui-linkbutton save-btn" id="editPayer" data-options="selected:true">修改</a>
                <a href="javascript:void(0);" class="easyui-linkbutton reset-btn" id="reset" data-options="selected:true">重置</a>
            </div>
        </div>
    </div>
  </body>
  <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
 <script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/js/calendar.js"></script>
<script type="text/javascript">
   
    $(function () {
    
 
			$.ajax({
				url : "getindustryServlet.do",
				type : "post",
				data : {},
				async : false,
				datetype : "json",
				success : function(data, status) {
					//console.log(data);
					if (data) {
						var str = "";
						$.each(data.data, function(index, obj) {
							str += "<option value='"+obj.id+"'>"
									+ obj.industryName + "</option>";
						});
						$("#instruyId").append(str)

						//alert("成功 ....🤗"+data);

					}
				},
				error : function(data, status) {
					alert("失败....🤗");
				}
			});
    	
    	

			$.ajax({
				url : "getOrganServlet.do",
				type : "post",
				data : {},
				async : false,
				datetype : "json",
				success : function(data, status) {
					//console.log(data);
					if (data) {
						var str = "";
						$.each(data.data, function(index, obj) {
							str += "<option value='"+obj.id+"'>"
									+ obj.organName + "</option>";
									//alert(obj.organName);
						});
						$("#taxOrganId").append(str)
					}
				},
				error : function(data, status) {
					alert("失败....🤗");
				}
			});
		
    	
    	
    	var id = ${param.id}; 
    		
    	 $.ajax({
			url : "editTaxPayer.do",
			type : "post",
			data : {
				"id":id				
			},
			async : false,
			datetype : "json",
			success : function(data, status) {
				//console.log(data);
				 if (data) {									
					 $.each(data.data, function(index, obj) {
							//alert(obj.legalIdCard)
							$("#payerCode").val(obj.payerCode);
							$("#payerName").val(obj.payerName);
							$("#bizAddress").val(obj.bizAddress);
							$("#bizAddressPhone").val(obj.bizAddressPhone);
							$("#bizScope").val(obj.bizScope);
							
							var inId = obj.userId;
							$("#invoiceType [value="+inId+"]").attr("selected","selected");
						
							$("#legalPerson").val(obj.legalPerson);
							$("#legalIdCard").val(obj.legalIdCard);							
							$("#finaceName").val(obj.finaceName);
							$("#finaceIdCard").val(obj.finaceIdCard);
							$("#taxerName").html(obj.taxerName);
							$("#recordDate").html(obj.recordDate);
							var ind = obj.industryId;
							var tax = obj.taxOrganId;
							$("#instruyId [value="+ind+"]").attr("selected","selected");
							$("#taxOrganId [value="+tax+"]").attr("selected","selected");
						})
				} 
			},
			error : function(data, status) {
				alert("失败....🤗");
			}
		}); 
    	 $("#editPayer").on("click",function(){
    		 up();
 		}) 
    	 function up(){
    		 var id = ${param.id}; 
    		 var payerName = $("#payerName").val();
        	 var bizAddress = $("#bizAddress").val();
        	 var bizAddressPhone = $("#bizAddressPhone").val();
        	 //alert(bizAddressPhone);
        	 var bizScope = $("#bizScope").val();
        	 var legalPerson = $("#legalPerson").val();
        	 var legalIdCard = $("#legalIdCard").val();
        	 var finaceName = $("#finaceName").val();
        	 var finaceIdCard = $("#finaceIdCard").val();
        	 var instruyId = $("#instruyId").val();
        	 var taxOrganId = $("#taxOrganId").val();
    
        	 //alert(taxOrganId);
        	 $.ajax({
     			url : "editTaxPayer2.do",
     			type : "post",
     			data : {
     				"id":id,
     				"payerName":payerName,
     				"bizAddress":bizAddress,
     				"bizAddressPhone":bizAddressPhone,
     				"bizScope":bizScope,
     				"legalPerson":legalPerson,
     				"legalIdCard":legalIdCard,
     				"finaceName":finaceName,
     				"finaceIdCard":finaceIdCard,
     				"instruyId":instruyId,
     				"taxOrganId":taxOrganId
     			},
     			async : false,
     			datetype : "json",
     			success : function(data, status) {
     				//console.log(data);
     				 if (data) {									
     					alert(data.data);
    					parent.$("#topWindow").window('close');
     				} 
     			},
     			error : function(data, status) {
     				alert("失败....🤗");
     			}
     		}); 
    	 }
    	 	
	});
</script>
</html>
