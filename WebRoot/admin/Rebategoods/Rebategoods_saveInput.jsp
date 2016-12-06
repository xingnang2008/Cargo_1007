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
					    url:'<%=basePath%>admin/Rebategoods/Rebategoods-save.action',    
					    success:function(){    
							//关闭当前窗体
							parent.$("#win").window("close");
							//重载dg
							parent.$("#gtab").datagrid("reload");				           
					    }    
					});  
					
				}
			});
		})
	
	
	</script>

  </head>
  
  <body>
    <div >
    	<form id="ff" method="post">
    		<div>
    			<label for="rebateId">核销编号：</label>
    			<input class="easyui-numberbox" type="text" name="rebateId" />
    		</div>
    	
    		<div>
    			<label for="goods">商品名称：</label>
    			<input class="easyui-textbox" type="text" name="goods" />
    		</div>
    		<hr/>
    		<div>
    			<label for="hsCode">H S 编码：</label>
    			<input class="easyui-textbox" type="text" name="hsCode" />
    			</div>
    		<div>
    			<label for="quantity">数&nbsp;&nbsp;量：</label>
    			<input class="easyui-numberbox" type="text" name="quantity" />
    			</div>
    		<div>
       			<label for="netWeight">净&nbsp;&nbsp;重：</label>
    			<input class="easyui-numberbox" type="text" name="netWeight" />
    			</div>
    		<div>
      			<label for="price">单&nbsp;&nbsp;价：</label>
    			<input class="easyui-numberbox" type="text" name="price" />
    			</div>
    		<div>
      			<label for="amount">金&nbsp;&nbsp;额：</label>
    			<input class="easyui-numberbox" type="text" name="amount" />
    			       			
    		</div>
    	   		<hr/>
    		<div>
    			<label for="material">材&nbsp;&nbsp;质：</label>
    			<input class="easyui-textbox" type="text" name="material" />
    			</div>
    		
    		<div>
    			<label for="remarks">备&nbsp;&nbsp;注：</label>
    			<input class="easyui-textbox" type="text" name="remarks" />
    			
    		
    		</div>  
    		
    		<hr/>  	
    		<div>
    			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    			
    		</div>
    	
    		
    	
    	</form>
    </div>	
  </body>
</html>
