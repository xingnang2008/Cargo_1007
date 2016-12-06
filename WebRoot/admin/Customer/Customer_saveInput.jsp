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
			$("#lineId").textbox({
				required:true
				});
			$("#btn").click(function(){
				//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){
					//提交数据
					$('#ff').form('submit', {    
					    url:'<%=basePath%>admin/Customer/Customer-save.action',    
					    success:function(){    
							//关闭当前窗体
							parent.$("#win").window("close");
							//重载dg
							parent.$("#dg").datagrid("reload");				           
					    }    
					});  
					
				}
			});

			
		})
	
	
	</script>

  </head>
  
  <body>
  <form id="ff" method="post">
   	 <div >
    	
    		
    		<div>
    			<label for="custId">客户编号：</label>
    			<input id="lineId" type="text" class="easyui-textbox"  name="custId" />
    		</div>
    		<div>
    			<label for="name">收货人名：</label>
    			<input class="easyui-textbox" type="text" name="name" style="width:300px"/>
    		</div>
    		<div>     			
    			<label for="telphone">电&nbsp;&nbsp;话：</label>
    			<input class="easyui-textbox" type="text" name="telphone" style="width:300px" />
    		</div> 
    		<div>     			
    			<label for="city">城&nbsp;&nbsp;市：</label>
    			<input class="easyui-textbox" type="text" name="city" />
    		</div>
    		<div>     			
    			<label for="address">地&nbsp;&nbsp;址：</label>
    			<input class="easyui-textbox" type="text" name="address" />
    		</div> 
    		<div>     			
    			<label for="email">邮&nbsp;&nbsp;箱：</label>
    			<input class="easyui-textbox" type="text" name="email" />
    		</div> 	
    		<div>     			
    			<label for="remarks">备&nbsp;&nbsp;注：</label>
    			<input class="easyui-textbox" type="text" name="remarks" />
    		</div>  
    		
    		<hr/>  	
    		<div>
    			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    			
    		</div>
    	
    	</div>	
    	
    	
   	
    </form>
  </body>
</html>
