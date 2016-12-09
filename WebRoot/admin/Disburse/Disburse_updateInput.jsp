<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
   
    
	<%@include file="../head.jspf" %>
	 <script language="JavaScript" >
	 $(function(){
			var rows =parent.$("#dg").datagrid("getSelections");
			$("#ff").form('load',{
				id:rows[0].id,				
				bitch:rows[0].bitch,
				lineId:rows[0].lineId,
				payFor:rows[0].payFor,	
				payDate:rows[0].payDate,
				payMethod:rows[0].payMethod,	
				sortId:rows[0].sortId,
				fee:rows[0].fee,		
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
					    url:'<%=basePath%>admin/Disburse/Disburse-update.action',    
					    
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
				panelWidth:150,
				width:150,
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
				panelHeight:300,
				panelWidth:200,
				width:200
			    
        	  
			}); 
		})
	 </script>	

  </head>
  
  <body>
	<div >
    	<form id="ff" method="post">
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
						<select id="payMethod" name="payMethod" class="easyui-combobox"
							style="width: 120px; panelHeight: 200px;">
							<option value="0">莫斯科付</option>
							<option value="1">国内转账</option>
							<option value="2">国内现金</option>
							<option value="3">其他</option>
						</select>
					</div>
    			<div>
	    			<label for="fee">金&nbsp;&nbsp;额：</label>
	    			<input  class="easyui-numberbox validatebox" required="required"   precision="2"  type="text" name="fee" />
    			</div>
    			<div>     			
	    			<label for="remarks">备&nbsp;&nbsp;注：</label>
	    			<input class="easyui-textbox" type="text" name="remarks" />
    			</div>
    			<div>
    			
			        	<input type="hidden" name="sortId" />
			        	<input type="hidden" name="id"/>
    					<!-- 这里默认为0 是用来付款里分类为：付清关费 -->
    			</div>  
    		<hr/>  	
    		<div>
    			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    			
    		</div>
    	
    	</form>
    </div>
</body>
</html>
