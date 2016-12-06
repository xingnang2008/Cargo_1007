<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 <%@include file="/admin/head.jspf" %>
 <script type="text/javascript">
		$(function(){
			$('#dd1').datebox({    
   				 required:true   
			}); 
			$('#dd2').datebox({    
   				 required:true   
			}); 
		})
	
	
	</script>
  </head>
  
  <body>
 <p align="center"> 查询核销记录： </p><br>
 	<form action="Rebate-list.action" method="post">
	 	<label>起始日期</label>  <input id="dd1" type="text" name="stdate" />
	 	</br>
		<label>结束日期</label>  <input id="dd2" type="text" name="eddate" />
		</br>
		<label>客  户  名</label>  <input class="easyui-textbox" name="custName"></br> 
		<label>厂  家  名</label>  <input class="easyui-textbox" name="company.company"></br>
		<label>合  同  号</label>  <input class="easyui-textbox" name="contract"></br>
		<label>批  次  号</label>  <input class="easyui-textbox" name="bitch.bitch"></br>
 		<input type="submit" value="查询" /> 	
 	
 	</form>
 
  </body>
</html>
