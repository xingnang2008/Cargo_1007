<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@include file="../head.jspf" %>
	<script type="text/javascript">
	$(function(){
		$("#btnCreate").click(function(){
			//开启验证
			$("#ff").form("enableValidation");
			//如果验证成功，则提交数据
			if($("#ff").form("validate")){
				//提交数据				
				$('#ff').form('submit', {    
				    url:'Waybill/Waybill-downloadAllBitchReport',
				    onSubmit:function(){    
						$.messager.show({
						title:'请求已发送',
						msg:'请求已发送，请稍等。',
						timeout:2000,
						showType:'slide'
					});	
			   		 },    
				    success:function(){    
						$.messager.show({
							title:'请求已成功',
							msg:'请求已成功。',
							timeout:2000,
							showType:'slide'
						});		           
			    	}        
			    })    
				} 
		
		});
		
	})

	</script>
  </head>
  
  <body>
 	<div class="container">
	<div>
    <div> <h2>导出批次明细表</h2></div>
    <div id="searchDiv">
			<div>
			<form id="ff"  method="post">
			
			<div class="label">起始日期</div>
				<div class="hang"><input type="text" id="stdate" class="easyui-datebox" name="stdate" style="width:120px"/></div>
				<div class="label">截止日期</div>
				<div class="hang"><input type="text" id="enddate" class="easyui-datebox" name="enddate" style="width:120px" /></div>
								
			
			</form>
			</div>
		
			<div id="hang">
			<a id="btnCreate" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">生成报表</a>
			</div>
			
		</div>
	</div>		
	
	</div>
  </body>
</html>
