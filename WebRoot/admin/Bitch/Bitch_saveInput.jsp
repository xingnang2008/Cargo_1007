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
					    url:'<%=basePath%>admin/Bitch/Bitch-save.action',    
					    success:function(){    
							//关闭当前窗体
							parent.$("#win").window("close");
							//重载dg
							parent.$("#dg").datagrid("reload");				           
					    }    
					});  
					
				}
			});

			//线路选择框
			$("#lineId").combobox({
				url:'<%=basePath%>admin/Line/Line-listAll.action',
				editable:false,
				required:true,
				valueField:'lineId',
				textField:'lineId',
				panelHeight:300,
				panelWidth:100,
				width:100
			}); 
		})
	
	
	</script>

  </head>
  
  <body>
  <form id="ff" method="post">
   	 <div >
    	
    		<div>
    			<label for="bitch">批  次  号：</label>
    			<input class="easyui-textbox"  data-options="required:true" type="text" name="bitch" />
    		</div>
    	
    		<div>
    			<label for="sdDate">发出日期：</label>
    			<input class="easyui-datebox" type="text" name="sdDate" />
    		</div>
    		<hr/>
    		<div>
    			<label for="lineId">线&nbsp;&nbsp;路：</label>
    			<input id="lineId" type="text" name="lineId" />
    			</div>
    		<div>
    			<label for="info">批次信息：</label>
    			<input class="easyui-textbox" type="text" name="info" />
    			</div>
    		<div>
       			<label for="fee">支&nbsp;&nbsp;出：</label>
    			<input class="easyui-numberbox" type="text" name="fee" />
    			</div>
    		<div>
    			<label for="arrDate">到达日期：</label>
    			<input class="easyui-datebox" type="text"  name="arrDate" />
    		</div>
    		<div>
    			<label for="changeRate">汇&nbsp;&nbsp;率：</label>
    			<input class="easyui-numberbox" data-options="required:true" precision="2"  type="text" name="changeRate" />
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
