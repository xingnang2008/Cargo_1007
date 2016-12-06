<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
  
    	<%@include file="../head.jspf" %>
    <title>My JSP 'uploadfile.jsp' starting page</title>
   

  </head>
  
  <body>
 
	<form action="Quote-updateByExcel.action" method="post" enctype='multipart/form-data'>
	<div>
	<input class="easyui-filebox" name="excelFile" buttonText="选取文件" data-options="prompt:'请选择上传的文件...'" style="width:300px">
	</div>
	<div>
	<input type="submit" />
	</div>	
	</form>
	
	<a href="<%=basePath%>admin/Quote/Quote-downloadReport">导出</a>
  </body>
</html>
