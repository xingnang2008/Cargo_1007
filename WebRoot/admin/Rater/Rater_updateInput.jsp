<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
   
    
	<%@include file="../head.jspf" %>
	 <link rel="stylesheet" href="<%=basePath%>css/Rebate/Rebate_updateInput.css" type="text/css" />
	 <script language="JavaScript" >
	 $(function(){
			var rows =parent.$("#DgRater").datagrid("getSelections");
			$("#ff").form('load',{
				id:rows[0].id,				
				raterName:rows[0].raterName,
				phone:rows[0].phone,
				card:rows[0].card,
				cardName:rows[0].cardName,
				bank:rows[0].bank,
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
					    url:'<%=basePath%>admin/Rater/Rater-update.action',    
					    
					    success:function(){    
							//关闭当前窗体
							parent.$("#win").window("close");
							//重载dg
							parent.$("#DgRater").datagrid("reload");				           
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
    			<label for="raterName">经  办  人：</label>
    			<input id="raterName"  class="easyui-textbox" name="raterName" />
    			</div>
    		<div>
    			<label for="phone">联系电话：</label>
    			<input class="easyui-textbox" type="text" name="phone" />
    		</div>
    		<div>
    			<label for="cardName">银行户名：</label>
    			<input class="easyui-textbox" type="text" name="cardName" />
    		</div>
    		<div>
    			<label for="bank">开  户  行：</label>
    			<input class="easyui-textbox" type="text" name="bank" />
    		</div>
    		<div>
    			<label for="card">卡&nbsp;&nbsp;号：</label>
    			<input class="easyui-textbox" type="text" name="card" />
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
