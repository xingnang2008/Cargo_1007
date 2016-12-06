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
				    url:'Waybill/Waybill-downloadCaiReport',
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
		//线路选择框
		$("#lineId").combobox({
			url:'<%=basePath%>admin/Line/Line-listAll.action',
			editable:true,
			valueField:'lineId',
			textField:'lineId',
			panelHeight:200,
			panelWidth:120,
			width:120,
			onSelect: function(rec){    
				$('#bitch').combobox("clear").combobox("reload",
				 		 			'<%=basePath%>admin/Bitch/Bitch-listByLine.action?lineId='+rec.lineId); 
		 			
	    	}  
			
		});
		$("#bitch").combobox({
			
			valueField:'bitch',
			textField:'bitch',
			panelHeight:200,
			panelWidth:120,
			width:120
		}); 
	})

	</script>
  </head>
  
  <body>
 	<div class="container">

    <div> <h2>导出明细</h2></div>
    <div id="searchDiv">
			<div>
			<form id="ff"  method="post">
			<div class="label">线路</div>
			<div class="hang"><input type="text" id="lineId"  name="lineId" style="width:120px" /></div>
			
			<div id="label">批次</div>
			<div id="hang"><input type="text" id="bitch" class="easyui-combobox" name="bitch" style="width:200px" /></div>
			
			<div id="label">表单类型</div>
			<div id="hang">
				<select id="statusId" class="easyui-combobox" name="reportType"  data-options="panelHeight:'auto'" style="width:200px;">   
				    <option value="1">莫办报表</option>   
				    <option value="2">财务报表</option> 				    
				    <option value="3">总报表</option>
				    <option value="4">财务总报表</option> 
				    <option value="5">结算报表</option>  
				</select>
			 </div>							
			
			</form>
			</div>
		
			<div id="hang">
			<a id="btnCreate" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">生成报表</a>
			</div>
			
		</div>
	
	
	
	</div>
  </body>
</html>
