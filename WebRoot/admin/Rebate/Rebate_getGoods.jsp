<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Rebate_getGoods.jsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
   <s:property value="model.id"/>|<s:property value="model.company.company"/>|<s:property value="model.company.companyCode"/>|
  <s:property value="model.contract"/>|<s:property value="model.custName"/>|<s:property value="model.grossWeight"/>|
   
   </br>
    <s:iterator value="goodsList" var="g">
    	<s:property value="#g.id"/>|
    	<s:property value="#g.goods"/>|
    	<s:property value="#g.hsCode"/>|
    	<s:property value="#g.material"/>|
    	<s:property value="#g.quantity"/>|
    	<s:property value="#g.price"/>|
    	<s:property value="#g.amount"/>|
    	<s:property value="#g.netWeight"/>|
    	<s:property value="#g.remarks"/>|
    	
    
    </s:iterator>
  </body>
</html>
