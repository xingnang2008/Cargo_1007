<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="../head.jspf"%>
		<link rel="stylesheet"
			href="<%=basePath%>css/Indemnify/Indemnify-saveInput.css"
			type="text/css" />
		<title>My JSP 'Rebategoods_saveInput.jsp' starting page</title>

		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
		$(function(){
			//窗体弹出时默认禁用验证
			$("#ff").form("disableValidation");
			
			$("#btnSave").click(function(){
				//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){
					//提交数据
					$('#ff').form('submit', {    
					    url:'<%=basePath%>admin/Indemnify/Indemnify-save.action',    
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
				editable:true,
				valueField:'lineId',
				textField:'lineId',
				panelHeight:'auto',
				panelWidth:120,
				width:120,
				onSelect: function(rec){    
					$('#bitch').combobox("clear").combobox("reload",
					 		 			'<%=basePath%>admin/Bitch/Bitch-listByLine.action?lineId='+rec.lineId); 
			 			
	        	}  
				
			});
			$("#bitch").combobox({
				editable:false,
				valueField:'bitch',
				textField:'bitch',
				panelHeight:200,
				panelWidth:120,
				width:120,
				onSelect: function(rec){    
				$('#waybill').combobox("clear").combobox("reload",
				 		 			'<%=basePath%>admin/Waybill/Waybill-listByBitch.action?bitch='+rec.bitch); 
		 			
        	}  
			}); 
			$("#waybill").combobox({
				editable:false,
				valueField:'waybill',
				textField:'waybill',
				panelHeight:200,
				panelWidth:120,
				width:120,
				onSelect: function(rec){    
				var p = rec.price+rec.raterRate;
				 $('#sender').textbox('setValue', rec.sender);
	             $('#custId').textbox('setValue', rec.custId);
	             $('#custName').textbox('setValue', rec.custName);
	             $('#rater').textbox('setValue', rec.raterName); 
		 		 $('#weight').textbox('setValue', rec.weight);
		 		$('#goods').textbox('setValue', rec.goods);
		 		$('#worth').textbox('setValue', rec.worth);
		 		$('#mark').textbox('setValue', rec.mark);
		 		$("#price").textbox("setValue",p);
		 		$("#procurator").textbox("setValue",rec.procurator);
		 		$("#outWorth").textbox("setValue",rec.outWorth);
		 		$("#outPrice").textbox("setValue",rec.outPrice);
		 		
		 		
        	}  
			});
			//更改重量时计算运费 及折扣	和结算金额		
			$("#indemWeight").numberbox({
				onChange:function(newValue,oldValue){ 
				var weight = $("#weight").textbox("getValue");
				var worth = $("#worth").textbox("getValue");
				var price = $("#price").textbox("getValue");
				var yiBao = $("#yiWuBao").numberbox("getValue");
				
				rate = newValue/weight;
				indWorth =worth==0?yiBao*newValue:rate*worth;
				reBill = newValue*price;
				sum = indWorth+reBill;
				
					$("#indemWorth").numberbox("setValue",indWorth);
					$("#returnBill").numberbox("setValue",reBill);
					$("#indemnity").numberbox("setValue",sum);

					
				}
			});	
			//更改重量时计算运费 及折扣	和结算金额		
			$("#yiWuBao").numberbox({
				onChange:function(newValue,oldValue){ 
				var weight = $("#weight").textbox("getValue");
				var worth = $("#worth").textbox("getValue");
				var price = $("#price").textbox("getValue");
				var yiBao = $("#yiWuBao").numberbox("getValue");
				var indWeight = $("#indemWeight").textbox("getValue");
				
				rate = indWeight/weight;
				indWorth =worth==0?indWeight*newValue:rate*worth;
				reBill = indWeight*price;
				sum = indWorth+reBill;
				
					$("#indemWorth").numberbox("setValue",indWorth);
					$("#returnBill").numberbox("setValue",reBill);
					$("#indemnity").numberbox("setValue",sum);
				
				}
			});	
			//更改对外丢失 重量时计算 对外  运费 及折扣	和结算金额		
			$("#outIndemWeight").numberbox({
				onChange:function(newValue,oldValue){ 
				var weight = $("#weight").textbox("getValue");
				var worth = $("#outWorth").textbox("getValue");
				var price = $("#outPrice").textbox("getValue");
				var yiBao = $("#outYiWuBao").numberbox("getValue");
				
				rate = newValue/weight;
				indWorth =worth==0?yiBao*newValue:rate*worth;
				reBill = newValue*price;
				sum = indWorth+reBill;
				
					$("#outIndemWorth").numberbox("setValue",indWorth);
					$("#outReturnBill").numberbox("setValue",reBill);
					$("#outIndemnity").numberbox("setValue",sum);

					
				}
			});	
			//更改对<外> 义保时计算运费 及折扣	和结算金额		
			$("#outYiWuBao").numberbox({
				onChange:function(newValue,oldValue){ 
				var weight = $("#weight").textbox("getValue");
				var worth = $("#outWorth").textbox("getValue");
				var price = $("#outPrice").textbox("getValue");
				
				var outIndemWeight = $("#outIndemWeight").textbox("getValue");
				
				rate = outIndemWeight/weight;
				outIndemWorth =worth==0?outIndemWorth*newValue:rate*worth;
				reBill = outIndemWeight*price;
				sum = outIndemWeight+reBill;
				
					$("#outIndemWorth").numberbox("setValue",outIndemWorth);
					$("#outReturnBill").numberbox("setValue",reBill);
					$("#outIndemnity").numberbox("setValue",sum);
				
				}
			});	
		});
	
	
	</script>

	</head>

	<body>
		<form id="ff" method="post">

			<div id="container">
				<font color="red"><h2>
						赔偿记录
					</h2>
				</font>
				<div id="top">
					<a id="btnSave" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'">保存</a>

				</div>
				<div id="baseInfo">
					<div class="hang">
						<div class="clabel">
							线&nbsp;&nbsp;路：
						</div>
						<div class="xiang">
							<input type="text" id="lineId" name="lineId" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							批&nbsp;&nbsp;次：
						</div>
						<div class="xiang">
							<input type="text" id="bitch" name="bitch" />
						</div>
					</div>

					<div class="hang">
						<div class="clabel">
							运 单 号：
						</div>
						<div class="xiang">
							<input type="text" id="waybill" name="waybill" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							发 货 人：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								id="sender" name="sender" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							客 户 号：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								id="custId" name="custId" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							收 货 人：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								id="custName" name="custName" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							经 办 人：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								id="rater" name="rater" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							代 理 人：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								id="procurator" name="procurator" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							重&nbsp;&nbsp;量：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								id="weight" name="weight" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							单&nbsp;&nbsp;价：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								id="price" name="price" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							品&nbsp;&nbsp;类：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								id="mark" name="mark" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							品&nbsp;&nbsp;名：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								id="goods" name="goods" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							货&nbsp;&nbsp;值：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								id="worth" name="worth" />
						</div>

					</div>
				</div>
				<div id="feeInfo">

					<div class="hang">
						<div class="clabel">
							义保标准：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-numberbox validatebox"
								required="true" value="10" id="yiWuBao" name="yiWuBao" />
						</div>

					</div>

					<div class="hang">
						<div class="clabel">
							丢失重量：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-numberbox validatebox" precision="2"
								required="true" id="indemWeight" name="indemWeight" />
						</div>

					</div>

					<div class="hang">
						<div class="clabel">
							丢失货值：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-numberbox validatebox"
								required="true" id="indemWorth" name="indemWorth" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							返还运费：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-numberbox validatebox"
								required="true" id="returnBill" name="returnBill" />
						</div>

					</div>

					<div class="hang">
						<div class="clabel">
							金&nbsp;&nbsp;额：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-numberbox validatebox"
								required="true" id="indemnity" name="indemnity" />
						</div>

					</div>
					
					<div class="hang">
						<div class="clabel">
							赔偿日期：
						</div>
						<div class="xiang">
							<input id="sddate" type="text" class="easyui-datebox" name="indemDate"/>
						</div>

					</div>
					
					<div class="hang">
						<div class="clabel">
							赔偿状态：
						</div>
						<div class="xiang">
							
								<select name="status" class="easyui-combobox" style="width: 100px;">
								<option value="0" selected="true">未赔偿	</option>
								<option value="1">已赔付	</option>
								
							</select>
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							赔偿方式：
						</div>
						<div class="xiang">

							<select name="payMethod" class="easyui-combobox" style="width: 100px; panelHeight: 80px;">
								<option value="0">运费中减</option>
								<option value="1">现&nbsp;&nbsp;金</option>
								<option value="2">转&nbsp;&nbsp;账</option>
								<option value="3">其&nbsp;&nbsp;他</option>
							</select>
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							原&nbsp;&nbsp;因：
						</div>
						<div class="xiang2">
							<input type="text" class="easyui-textbox" multiline="true"
								style="width: 260px; height: 60px" name="reason" />
						</div>
					</div>

					<div class="hang">
						<div class="clabel">
							备&nbsp;&nbsp;注：
						</div>
						<div class="xiang2">
							<input type="text" class="easyui-textbox" multiline="true"
								style="width: 260px; height: 60px" name="remarks" />
						</div>

					</div>
				</div>
				<div id="outInfo">
					<div class="hang">
							<div class="clabel">
								外&nbsp;货&nbsp;值：
							</div>
							<div class="xiang">
								<input type="text" class="easyui-textbox" readonly="true"
									id="outWorth" name="outWorth" />
							</div>
	
					</div>
					<div class="hang">
						<div class="clabel">
							外-单价：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								required="true" id="outPrice" name="outPrice" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							外-义保：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-numberbox validatebox"
								required="true" value="10" id="outYiWuBao" name="outYiWuBao" />
						</div>

					</div>
					
					<div class="hang">
						<div class="clabel">
							外-丢重量：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-numberbox validatebox" precision="2"
								required="true" id="outIndemWeight" name="outIndemWeight" />
						</div>

					</div>

					<div class="hang">
						<div class="clabel">
							外-丢货值：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-numberbox validatebox"
								required="true" id="outIndemWorth" name="outIndemWorth" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							外-返运费：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-numberbox validatebox"
								required="true" id="outReturnBill" name="outReturnBill" />
						</div>

					</div>

					<div class="hang">
						<div class="clabel">
							外-金&nbsp;额：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-numberbox validatebox"
								required="true" id="outIndemnity" name="outIndemnity" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							外-赔方式：
						</div>
						<div class="xiang">

							<select name="outStatus" class="easyui-combobox" style="width: 100px; panelHeight: 80px;">
								<option value="0">未赔偿	</option>
								<option value="1">已赔偿	</option>
								
							</select>
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							外-赔日期：
						</div>
						<div class="xiang">
							<input id="sddate" type="text" class="easyui-datebox" name="outIndemDate"/>
						</div>

					</div>



				</div>
				<input type="hidden" value="0" name="approval" />





			</div>
		</form>

	</body>
</html>