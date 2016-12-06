<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="../head.jspf" %>
    
    <title>My JSP 'Rebategoods_saveInput.jsp' starting page</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
		$(function(){
			//窗体弹出时默认禁用验证
			$("#ff").form("disableValidation");
			
			$("#btn").click(function(){
				//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){
					//提交数据
					$('#ff').form('submit', {    
					    url:'<%=basePath%>admin/CustomerRelation/CustomerRelation-save.action',    
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
			$("#rater").combobox({
				url:'<%=basePath%>admin/Rater/Rater-listAll.action',
				
				valueField:'id',
				textField:'raterName',
				panelHeight:200,
				panelWidth:200,
				width:200,
				onSelect: function(rec){   
	              $('#raterPhone').textbox('setValue', rec.phone);
				}
			});
			//发货人选择框
			$("#sender").combobox({
				url:'<%=basePath%>admin/Sender/Sender-listAll.action',
				
				valueField:'id',
				textField:'sdName',
				panelHeight:200,
				panelWidth:200,
				width:200,
				onSelect: function(rec){   
	              $('#sdName').textbox('setValue', rec.sdName);
	               $('#phone').textbox('setValue',rec.phone);
				}
			});
			//客户选择框
			$("#customer").combobox({
				url:'<%=basePath%>admin/Customer/Customer-listAll.action',
				
				valueField:'id',
				textField:'custId',
				panelHeight:200,
				panelWidth:200,
				width:200,
				onSelect: function(rec){   
	              $('#custName').textbox('setValue', rec.name);
	               $('#custTel').textbox('setValue',rec.telphone);
				}
			});
			//代理人选择框
			$("#procurator").combobox({
				url:'<%=basePath%>admin/Procurator/Procurator-listAll.action',
				valueField:'id',
				textField:'name',				
				panelHeight:200,
				panelWidth:100,
				width:100,
				onSelect: function(rec){   
	              $('#procuratorPhone').textbox('setValue', rec.telphone);
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
    			<input id="customer" type="text" class="easyui-combobox"  name="custId"style="width:300px"/>
    			<input id="custName" type="text" class="easyui-textbox"  style="width:300px"/>
    			<input id="custTel" type="text" class="easyui-textbox"  style="width:300px"/>
    		</div>
    		<div style="margin:10px">
    			<label for="sender">发货人：</label>
    			<input id="sender"  class="easyui-combobox" type="text" name="senderId" style="width:300px"/>
    			<input id="sdName type="text" class="easyui-textbox"  style="width:300px"/>
    			<input id="phone" type="text" class="easyui-textbox"  style="width:300px"/>
    		</div>
    		<div style="margin:10px">
    			<label for="rater">经办人：</label>
    			<input id="rater" class="easyui-combobox" type="text" name="raterId" style="width:300px"/>
    			<input id="raterPhone" type="text" class="easyui-textbox"  style="width:300px"/>
    		</div>
    		<div style="margin:10px">
    			<label for="procurator">代理人：</label>
    			<input id="procurator" class="easyui-combobox" type="text" name="procuratorId" style="width:300px"/>
    			<input id="procuratorPhone" type="text" class="easyui-textbox"  style="width:300px"/>
    		</div>
    		<div style="margin:10px">
    			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    			
    		</div>
    	
    	</div>	
    	
    	
   	
    </form>
  </body>
</html>
