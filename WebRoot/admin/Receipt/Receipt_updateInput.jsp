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
			var rows =parent.$("#dg").datagrid("getSelections");
			$("#ff").form('load',{
				id:rows[0].id,	
				waybill:rows[0].waybill,							
				lineId:rows[0].lineId,
				bitch:rows[0].bitch,				
				sender:rows[0].sender,
				custId:rows[0].custId,
				custName:rows[0].custName,
				rater:rows[0].rater,

				rdate:rows[0].rdate,
				fee:rows[0].fee,				
				remarks:rows[0].remarks,				
				payMethod:rows[0].payMethod
				
				
			
				
				//更新form中的数据
			});	
			//窗体弹出时默认禁用验证
			$("#ff").form("disableValidation");
			
			$("#btnSave").click(function(){
				//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){
					//提交数据
					$('#ff').form('submit', {    
					    url:'<%=basePath%>admin/Receipt/Receipt-update.action',    
					    success:function(){    
							//关闭当前窗体
							parent.$("#win").window("close");
							//重载dg
							parent.$("#dg").datagrid("reload");				           
					    }    
					});  
					
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
		  	<div class="clabel">运  单  号：</div><div class="xiang"><input type="text" class="easyui-textbox " readonly="true"  id="waybill" name="waybill"/></div>  
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
		   
		   <input type="hidden" name="id"/>
		 	<input type="hidden" name="bitch"/>
		   <input type="hidden" name="lineId"/>
		   <input type="hidden" name="sender"/>
		   <input type="hidden" name="custId"/>
		   <input type="hidden" name="custName"/>
		   <input type="hidden" name="rater"/>
		   
		   
		   
	</div>
	</form>

  </body>
</html>