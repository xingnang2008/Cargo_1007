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
			$("#ame").textbox({
				required:true
			});
			$("#btn").click(function(){
				//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){
					//提交数据
					$('#ff').form('submit', {    
					    url:'<%=basePath%>admin/Sender/Sender-save.action',    
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
    			<label for="sdName">发  货  人：</label>
    			<input id="sdName"  class="easyui-textbox" name="sdName" />
    			</div>
    		<div>
    			<label for="phone">联系电话：</label>
    			<input class="easyui-textbox" type="text" name="phone" />
    		</div>
    		<div>
    			<label for="email">邮&nbsp;&nbsp;箱：</label>
    			<input class="easyui-textbox" type="text" name="email" />
    		</div>
    		<div>
    			<label for="qq">Q&nbsp;&nbsp;Q：</label>
    			<input class="easyui-textbox" type="text" name="qq" />
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
