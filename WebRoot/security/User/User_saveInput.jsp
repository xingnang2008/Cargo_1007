<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <%@include file="../head.jspf" %>
    <title>用户新建</title>
    
	

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
					    url:'<%=basePath%>security/User/User-save',    
					    success:function(){    
							//关闭当前窗体
							$.messager.show({
								title:'提示',
								msg:'新建 用户记录成功',
								timeout:2000,
								showType:'slide'
							});
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
    			<label for="username">用&nbsp;&nbsp;户：</label>
    			<input id="username" class="easyui-textbox" type="text" name="username" />
    			</div>
    		<div>
    			<label for="name">姓&nbsp;&nbsp;名：</label>
    			<input id='name' class="easyui-textbox" type="text" name="name" />
    			</div>
    		<div>
    			<label for="password">密&nbsp;&nbsp;码：</label>
    			<input id='password' class="easyui-textbox" type="text" name="password" />
    			</div>
    		
    		<div>
    			<label for="enabled">状&nbsp;&nbsp;态：</label>
    			
				<input name="enabled" type="radio" value="<%=true%>" />可用 
				<input name="enabled" type="radio" value="<%=false%>" />不可用 
    			</div>
    		
    		<div>
    			<label for="roles_id">权&nbsp;&nbsp;限：</label>
    			
				<input name="role_id" type="radio" value="1" />用户 
				<input name="role_id" type="radio" value="2" />管理员 
				<input name="role_id" type="radio" value="3" />财务 
				
    			</div>
    		<div> 
    		<hr/>  	
    		<div>
    			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    			
    		</div>
    	
    	</div>	
    	
    	
   	
    </form>
  </body>
</html>
