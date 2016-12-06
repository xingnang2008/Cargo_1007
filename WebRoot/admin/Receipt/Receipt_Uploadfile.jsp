<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
  
    	<%@include file="../head.jspf" %>
    <title>My JSP 'uploadfile.jsp' starting page</title>
  

  </head>
  
  <body>
 <h2>收款信息导入：</h2>
	<form action="Receipt-inputFromExcel.action" method="post" enctype='multipart/form-data'>
	<div>
	<input id="inbox" class="easyui-filebox" name="excelFile" buttonText="选取文件" data-options="prompt:'请选择上传的文件...'" style="width:300px;height:30px">
	</div>
	<div>
	<input type="submit" />
	</div>	
	</form>
	
  </body>
</html>
