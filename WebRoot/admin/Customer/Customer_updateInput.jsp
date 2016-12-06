<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
   
    
	<%@include file="../head.jspf" %>
	 <link rel="stylesheet" href="<%=basePath%>css/Rebate/Rebate_updateInput.css" type="text/css" />
	 <script language="JavaScript" >
	 $(function(){
			var rows =parent.$("#DgCustomer").datagrid("getSelections");
			$("#ff").form('load',{
				id:rows[0].id,				
				custId:rows[0].custId,
				name:rows[0].name,
				telphone:rows[0].telphone,
				city:rows[0].city,
				address:rows[0].address,
				email:rows[0].email,
				remarks:rows[0].remarks
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
					    url:'<%=basePath%>admin/Customer/Customer-update.action',    
					    
					    success:function(){    
							//关闭当前窗体
							parent.$("#win").window("close");
							//重载dg
							parent.$("#DgCustomer").datagrid("reload");				           
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
    			<label for="custId">客户编号：</label>
    			<input class="easyui-textbox" type="text" name="custId" />
    		</div>
    	<hr/>
    		<div>
    			<label for="name">收货人名：</label>
    			<input class="easyui-textbox" type="text" name="name" />
    		</div>
    		
    		<div>
    			<label for="telphone">联系电话：</label>
    			<input class="easyui-textbox" type="text" name="telphone" />
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
    			<input type="hidden" name="id"/>
    		</div>
    	
    	</form>
    </div>
</body>
</html>
