<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
   
    
	<%@include file="../head.jspf" %>
	 <link rel="stylesheet" href="<%=basePath%>css/Rebate/Rebate_updateInput.css" type="text/css" />
	 <script language="JavaScript" >
	 $(function(){
			var rows =parent.$("#dg").datagrid("getSelections");
			$("#ff").form('load',{
				custId:rows[0][3].customer.custId,
				senderId:rows[0][3].sender.sdName,
				raterId:rows[0][3].rater.raterName
				
				//更新form中的数据
			});	
			console.info(rows[0][3].customer.custId);
			//窗体弹出时默认禁用验证
			$("#ff").form("disableValidation");
			
			$("#btn").click(function(){
				//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){
					//提交数据
					//提交数据
					$('#ff').form('submit', {    
					    url:'<%=basePath%>admin/CustomerRelation/CustomerRelation-update.action',    
					    
					    success:function(){    
							//关闭当前窗体
							parent.$("#win").window("close");
							//重载dg
							parent.$("#dg").datagrid("reload");				           
					    }    
					});  
				}
			});
			//经办人选择框
			$("#raterId").combobox({
				url:'<%=basePath%>admin/Rater/Rater-listAll.action',
				valueField:'id',
				textField:'raterName',
				panelHeight:'auto',
				panelWidth:200,
				width:200,
				onSelect: function(rec){   
	              $('#raterPhone').textbox('setValue', rec.phone);
				}
			});
			//发货人选择框
			$("#senderId").combobox({
				url:'<%=basePath%>admin/Sender/Sender-listAll.action',
				valueField:'id',
				textField:'sdName',
				panelHeight:'auto',
				panelWidth:200,
				width:200,
				onSelect: function(rec){   
	              $('#sdName').textbox('setValue', rec.sender.sdName);
	               $('#phone').textbox('setValue',rec.sender.phone);
				}
			});
			//客户选择框
			$("#custId").combobox({
				url:'<%=basePath%>admin/Customer/Customer-listAll.action',
				valueField:'id',
				textField:'custId',
				panelHeight:'auto',
				panelWidth:200,
				width:200,
				onSelect: function(rec){   
	              $('#custName').textbox('setValue', rec.customer.name);
	               $('#custTel').textbox('setValue',rec.customer.telphone);
				}
			});
			
		})
	 </script>	

  </head>
  
  <body>
	<form id="ff" method="post">
   	 <div >
    	
    		
    		<div style="margin:10px">
    			<label for="customer">客户号：</label>
    			<input id="custId" type="text" class="easyui-combobox"  name="custId"style="width:300px"/>
    			<input id="custName" type="text" class="easyui-textbox"  style="width:300px"/>
    			<input id="custTel" type="text" class="easyui-textbox"  style="width:300px"/>
    		</div>
    		<div style="margin:10px">
    			<label for="sender">发货人：</label>
    			<input id="senderId"  class="easyui-combobox" type="text" name="senderId" style="width:300px"/>
    			<input id="sdName type="text" class="easyui-textbox"  style="width:300px"/>
    			<input id="phone" type="text" class="easyui-textbox"  style="width:300px"/>
    		</div>
    		<div style="margin:10px">
    			<label for="rater">经办人：</label>
    			<input id="raterId" class="easyui-combobox" type="text" name="raterId" style="width:300px"/>
    			<input id="raterPhone" type="text" class="easyui-textbox"  style="width:300px"/>
    		</div>
    		<div style="margin:10px">
    			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    			
    		</div>
    	
    	</div>	
    	
    	
   	
    </form>
   
</body>
</html>
