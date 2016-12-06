<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@include file="../head.jspf" %>
	
	 <link rel="stylesheet" href="<%=basePath%>css/Rebate/Rebate_saveInput.css" type="text/css" />
	

	 <script language="JavaScript" >
	 $(function(){
			 
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
					    url:'Rebate/Rebate-save.action',    
					    
					    success:function(){    
							//关闭当前窗体
							parent.$("#win").window("close");
							//重载dg
							parent.$("#dg").datagrid("reload");				           
					    }    
					});  
				}
			});
			$("#selectSender").combobox({
				url:'<%=basePath%>admin/Sender/Sender-listAll.action',
				valueField:'sdName',
				textField:'sdName',
				panelHeight:200,
				panelWidth:120,
				width:120,
				onSelect: function(rec){   
	             $('#sdtel').textbox('setValue', rec.phone);
	            
	       		 } 
			});
			$
			//批次选择框
			$("#ssb").combobox({
				url:'<%=basePath%>admin/Bitch/Bitch-listAll.action',
				editable:false,
				valueField:'bitch',
				textField:'bitch',
				panelHeight:'auto',
				panelWidth:120,
				width:120,
				onSelect: function(rec){   
	             $('#ssbid').textbox('setValue', rec.id);
	       		 } 
			}); 
			
			
			
		})
	 </script>	

  </head>
  
  <body>
	<div id="container">
	
	
	
			
			<form id="ff" method="post">	
				<div id="retab" >				
				
							<div class="hang">
									<div class="flabel">企业名称</div>
									<div class="fdata"><input type="text" name="company" class="easyui-textbox"style="width:150px;height:20px"/></div>
							</div>
							<div class="hang">
									<div class="flabel">海关编码</div>
									<div class="fdata"><input type="text" name="companyCode" class="easyui-textbox"style="width:150px;height:20px"/></div>
							</div>
							<div class="hang">
							<div class="flabel">合同号</div>
							<div class="fdata"><input type="text" name="contract"  class="easyui-textbox"style="width:120px;height:20px"/></div>		
							</div>
							<div class="hang">
							<div class="flabel">贸易方式</div>
							<div class="fdata">
								<select name="tradeType" class="easyui-combobox" style="width:120px;height:20px">
									<option selected value="一般贸易" >一般贸易</option>
									<option value='来料加工' >来料加工</option>
								</select>
							</div>
							</div>
							<div class="hang">
									<div class="flabel">货  源  地</div>
									<div class="fdata"><input type="text" name="source" class="easyui-textbox"style="width:120px;height:20px"/></div>		
							</div>
				
				
						<div class="hang">
								<div class="flabel">发货人</div>
								<div class="fdata"><input id="selectSender" name="custId"/></div>
						</div>									
						<div class="hang">
								<div class="flabel">电话</div>
								<div class="fdata"><input id="sdtel" type="text" name="telphone"  class="easyui-textbox" style="width:120px;height:20px"/></div>		
						</div>
				
				
					<div class="hang">
							<div class="flabel">包数</div>
							<div class="fdata"><input type="text" name="packages"  class="easyui-textbox" style="width:120px;height:20px"/></div>		
					</div>
					<div class="hang">
							<div class="flabel">毛重</div>
							<div class="fdata"><input type="text" name="grossWeight"  class="easyui-textbox" style="width:120px;height:20px"/></div>		
					</div>
					<div class="hang">
							<div class="flabel">批次</div>
							<div class="fdata"><input id="ssb" name="bitch"/></div>	
					</div>					
				
				
						<div class="hang">
								<div class="flabel">接单日期</div>
								<div class="fdata"><input type="text" name="sddate"  class="easyui-datebox"/></div>		
						</div>			
							
				
			
					<div class="hang">
		    			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		    			<input type="hidden" name="id"/>
		    			<input id="ssbid" type="hidden" class="easyui-textbox"name="bitchId"/>
		    		</div>
	    		</div>
				</form>
			
		</div>
		
		
	
	
</body>
</html>
