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
				id:rows[0].id,				
				name:rows[0].name,
				username:rows[0].username,				
				enabled:rows[0].enabled,				
				//更新form中的数据
			});	
			
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
					    url:'<%=basePath%>security/User/User-update.action',    
					    
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
    			<label for="username">用&nbsp;&nbsp;&nbsp;户：</label>
    			<input id="username" class="easyui-textbox" type="text" name="username" />
    			</div>
    		<div>
    			<label for="name">姓&nbsp;&nbsp;&nbsp;名：</label>
    			<input id='name' class="easyui-textbox" type="text" name="name" />
    			</div>
    		
    		
    		<div>
    			<label for="enabled">状&nbsp;&nbsp;&nbsp;态：</label>
    			
				<input name="enabled" type="radio" value="<%=true%>" />可用 
				<input name="enabled" type="radio" value="<%=false%>" />不可用 
    			</div>
    		
    		<div>
    		 
    		<hr/>  	
    		<div>
    			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">更新</a>
    			<input type="hidden" name="id"/>
    		</div>
    	
    	</div>	
    	
    	
   	
    </form>
  </body>
</html>
