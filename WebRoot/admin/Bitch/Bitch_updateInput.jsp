<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
   
    
	<%@include file="../head.jspf" %>
	 <link rel="stylesheet" href="<%=basePath%>css/Bitch/Bitch_updateInput.css" type="text/css" />
	 <script language="JavaScript" >
	 $(function(){
			var rows =parent.$("#dg").datagrid("getSelections");
			$("#ff").form('load',{
				id:rows[0].id,				
				bitch:rows[0].bitch,
				info:rows[0].info,
				lineId:rows[0].lineId,
				fee:rows[0].fee,
				sdDate:rows[0].sdDate,
				arrDate:rows[0].arrDate,
				changeRate:rows[0].changeRate,
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
					    url:'<%=basePath%>admin/Bitch/Bitch-update.action',    
					    
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
				width:100
			}); 	
			
		})
	 </script>	

  </head>
  
  <body>
  <div><H3>请在更改【批次名】前，确认此批次下没有运单</H3></div> <br> <Hr/><br>
  
	<div >
    	<form id="ff" method="post">
    		<div>
    			<label for="bitch">批&nbsp;&nbsp;次：</label>
    			<input class="easyui-textbox" data-options="required:true"  type="text" name="bitch" />
    		</div>
    	
    		<div>
    			<label for="info">批次信息：</label>
    			<input class="easyui-textbox" type="text" name="info" />
    		</div>
    		<hr/>
    		<div>
    			<label for="lineId">线&nbsp;&nbsp;路：</label>
    			<input id='lineId' type="text" name="lineId" />
    			</div>
    		<div>
    			<label for="fee">费&nbsp;&nbsp;用：</label>
    			<input class="easyui-numberbox" type="text" name="fee" />
    			</div>
    		<div>
       			<label for="sdDate">发出日期：</label>
    			<input class="easyui-datebox" type="text" name="sdDate" />
    			</div>
    		<div>
      			<label for="arrDate">到达日期：</label>
    			<input class="easyui-datebox" type="text" name="arrDate" />
    		</div>    		
    		<div>
    			<label for="changeRate">汇&nbsp;&nbsp;率：</label>
    			<input class="easyui-numberbox" data-options="required:true"  precision="2"  type="text" name="changeRate" />
    		</div>
    		<div>
    			<label for="remarks">备&nbsp;&nbsp;注：</label>
    			<input class="easyui-textbox" type="text" name="remarks" />
    			
    		
    		</div>  
    		<br>
    		<hr/>  	
    		<div>
    			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    			<input type="hidden" name="id"/>
    		</div>
    	
    	</form>
    </div>
</body>
</html>
