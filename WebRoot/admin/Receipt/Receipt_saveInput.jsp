<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="../head.jspf" %>
     <link rel="stylesheet" href="<%=basePath%>css/Track/Track_updateInput.css" type="text/css" />
    <title>My JSP 'Rebategoods_saveInput.jsp' starting page</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
		$(function(){
			//窗体弹出时默认禁用验证
			$("#ff").form("disableValidation");
			
			$("#btnSave").click(function(){
				//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){
					//提交数据
					$('#ff').form('submit', {    
					    url:'<%=basePath%>admin/Receipt/Receipt-save.action',    
					    success:function(){    
							//关闭当前窗体
							parent.$("#win").window("close");
							//重载dg
							parent.$("#dg").datagrid("reload");				           
					    }    
					});  
					
				}
			});
			//线路选择框
			$("#lineId").combobox({
				url:'<%=basePath%>admin/Line/Line-listAll.action',
				editable:true,
				valueField:'lineId',
				textField:'lineId',
				panelHeight:'auto',
				panelWidth:120,
				width:120,
				onSelect: function(rec){    
					$('#bitch').combobox("clear").combobox("reload",
					 		 			'<%=basePath%>admin/Bitch/Bitch-listByLine.action?lineId='+rec.lineId); 
			 			
	        	}  
				
			});
			$("#bitch").combobox({
				editable:false,
				valueField:'bitch',
				textField:'bitch',
				panelHeight:200,
				panelWidth:120,
				width:120,
				onSelect: function(rec){    
				$('#waybill').combobox("clear").combobox("reload",
				 		 			'<%=basePath%>admin/Waybill/Waybill-listByBitch.action?bitch='+rec.bitch); 
		 			
        	}  
			}); 
			$("#waybill").combobox({
				editable:false,
				valueField:'waybill',
				textField:'waybill',
				panelHeight:200,
				panelWidth:120,
				width:120,
				onSelect: function(rec){    
				 $('#sender').textbox('setValue', rec.sender);
	             $('#custId').textbox('setValue', rec.custId);
	             $('#custName').textbox('setValue', rec.custName);
	             $('#rater').textbox('setValue', rec.raterName); 
		 			
        	}  
			});
		});
	
	
	</script>

  </head>
  
  <body>
   <form id="ff" method="post">
	   
	    <div id="container">
	    <font color="green"><h2>收款记录</h2></font>
	    <div id="top">	
			<a id="btnSave" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
			
		</div>
			 <div class="hang">
		  	<div class="clabel">线&nbsp;&nbsp;路：</div><div class="xiang"><input type="text" id="lineId" name="lineId"/></div>  
		  </div>
		   <div class="hang">
		  	<div class="clabel">批&nbsp;&nbsp;次：</div><div class="xiang"><input type="text"  id="bitch" name="bitch"/></div>  
		  </div>
		  
			
		
		  <div class="hang">
		  	<div class="clabel">运  单  号：</div><div class="xiang"><input type="text"  id="waybill" name="waybill"/></div>  
		  </div>
		   <div class="hang">
		  	<div class="clabel">发  货  人：</div><div class="xiang"><input type="text" class="easyui-textbox"  readonly="true" id="sender" name="sender"/></div>  
		  </div>
		   <div class="hang">
		  	<div class="clabel">客  户  号：</div><div class="xiang"><input type="text" class="easyui-textbox"  readonly="true" id="custId" name="custId"/></div>  
		  </div>
		   <div class="hang">
		  	<div class="clabel">收  货  人：</div><div class="xiang"><input type="text" class="easyui-textbox"  readonly="true" id="custName" name="custName"/></div>  
		  </div>
		   <div class="hang">
		  	<div class="clabel">经  办  人：</div><div class="xiang"><input type="text" class="easyui-textbox" readonly="true"  id="rater" name="rater"/></div>  
		  </div>
		 
		   <div class="hang">
		  	<div class="clabel">收款日期：</div><div class="xiang"><input type="text" class="easyui-datebox validatebox"  required="true"  name="rdate"/></div>  
		  
		  </div>
		   <div class="hang">
		  	<div class="clabel">金&nbsp;&nbsp;额：</div><div class="xiang"><input type="text" class="easyui-numberbox validatebox"  required="true"  name="fee"/></div>  
		  
		  </div>
		  
		   <div class="hang">
		 	 <div class="clabel">付款方式：</div><div class="xiang">
		 	 
		 	 		<select name="payMethod" class="easyui-combobox"  style="width:100px;panelHeight:80px;">   
						     <option value="0">到付</option>  	
						    <option value="1">正付</option>
						       
						</select></div>  
		  
		  </div>
		   <div class="hang">
		  	<div class="clabel">备&nbsp;&nbsp;注：</div><div class="xiang"><input type="text" class="easyui-textbox" multiline="true" style="width:300px;height:60px" name="remarks" /></div>  
		  
		  </div>
		   
		  
		
		   
		   
		   
	</div>
	</form>

  </body>
</html>