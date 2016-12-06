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
					    url:'<%=basePath%>admin/Disburse/Disburse-save.action',    
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
				panelHeight:'auto',
				panelWidth:100,
				width:100,
				onSelect: function(rec){    
			    $('#bitch').combobox("clear").combobox("reload",
					            '<%=basePath%>admin/Bitch/Bitch-listByLine.action?lineId='+rec.lineId);
        	}  
			}); 
			//批次选择 
			$("#bitch").combobox({
				required:true,
				valueField:'bitch',
				textField:'bitch',
				panelHeight:200,
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
    			<label for="lineId">线&nbsp;&nbsp;路：</label>
    			<input id="lineId" type="text" name="lineId" />
    			</div>
    			<div>
    			<label for="bitch">批&nbsp;&nbsp;次：</label>
    			<input  id="bitch" type="text" name="bitch" />
    			</div>
    			<div>
    			<label for="payFor">项&nbsp;&nbsp;目：</label>
    			<input class="easyui-textbox" type="text" name="payFor" />
    			</div>
    			<div>
    			<label for="payDate">付款日期：</label>
    			<input class="easyui-datebox" type="text" name="payDate" />
    			</div>
    			
    			<div>
    			<label for="payMethod">付款方式：</label>
						<select id="payMethod"  class="easyui-combobox"
							style="width: 120px; panelHeight: 200px;">
							<option value="0">莫斯科付</option>
							<option value="1">北京转账</option>
							<option value="2">其他</option>
						</select>
					</div>
    			<div>
    			<label for="fee">金&nbsp;&nbsp;额：</label>
    			<input  class="easyui-numberbox validatebox" required="required"   precision="2"  type="text" name="fee" />
    			</div>
    			<div>     			
    		
    			<label for="remarks">备&nbsp;&nbsp;注：</label>
    			<input class="easyui-textbox" type="text" name="remarks" />
    			<div>
    			
			        	<input type="hidden"   value="0"  name="sortId" />
    					<!-- 这里默认为0 是用来付款里分类为：付清关费 -->
    			</div>  
    		
    		<hr/>  	
    		<div>
    			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    			
    		</div>
    	
    	</div>	
    	
    	
   	
    </form>
  </body>
</html>
