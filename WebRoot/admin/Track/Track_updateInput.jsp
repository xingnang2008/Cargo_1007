<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="../head.jspf"%>
		 <link rel="stylesheet" href="<%=basePath%>css/Track/Track-updateInput.css" type="text/css" />
		<script language="JavaScript">
	$(function() {
		$('#waybill').attr('readonly', true);
		$('#approval').attr('readonly', true);
		
		var rows = parent.$("#dg").datagrid("getSelections");
		$("#ff").form('load', {
			id:rows[0].id,
			arriveDate:rows[0].arriveDate,
			waybill:rows[0].waybill,
			sddate:rows[0].sddate,
			lineId:rows[0].lineId,
			bitch:rows[0].bitch,
			sender:rows[0].sender,
			custId:rows[0].custId,
			custName:rows[0].custName,
			rater:rows[0].rater,
			destName:rows[0].destName,
			days:rows[0].days,
			pics:rows[0].pics,
			wstatus:rows[0].wstatus,

			model:rows[0].model,
			delayWeight:rows[0].delayWeight,
			inDate:rows[0].inDate,
			delayDate:rows[0].delayDate,
			delayRate:rows[0].delayRate,
			delayIndemnity:rows[0].delayIndemnity,
			status:rows[0].status,
			indemDate:rows[0].indemDate,
			mothed:rows[0].mothed,

			approval:rows[0].approval,
			appDate:rows[0].appDate,
			outSdDate:rows[0].outSdDate,
			outInDate:rows[0].outInDate,
			outDelayDate:rows[0].outDelayDate,
			outDelayRate:rows[0].outDelayRate,
			outIndemnity:rows[0].outIndemnity,
			remarks:rows[0].remarks

		//更新form中的数据
				});

		//窗体弹出时默认禁用验证
		$("#ff").form("disableValidation");

		$("#btnSave").click(function(){
			//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据

				if($("#ff").form("validate")){
					//提交数据
					//提交数据
					$('#ff').form('submit',{
						url:'Track/Track-update.action',

						success:function(){
							//关闭当前窗体
						parent.$("#win").window("close");
						//重载dg
						parent.$("#dg").datagrid("reload");
					}
					});
				}
			});

		$("#delayRate").numberbox({
			onChange:calcOutMoney
		});
		$("#delayWeight").numberbox({
			onChange:calcOutMoney
		});
		$("#delayDate").numberbox({
			onChange:calcOutMoney
		});
		$("#model").combobox({
			onChange:calcOutMoney
		});

	});
	function calcOutMoney(){
		var delayRate = $("#delayRate").numberbox("getValue");
		var delayWeight = $("#delayWeight").numberbox("getValue");
		var delayDate = $("#delayDate").numberbox("getValue");
		var model = $("#model").combobox("getValue");
		var delayIndemnity = 0;
		if (model == 1) {
			delayIndemnity = delayRate * delayWeight;

		} else if (model == 0) {

			delayIndemnity = delayWeight * delayRate * delayDate;
		}

		$("#delayIndemnity").numberbox("setValue", delayIndemnity);

	}

	$.extend($.fn.validatebox.defaults.rules, {
		equals : {
			validator : function(value) {
				return value == 0;
			},

			message : '此单已经被<审核>,无法修改。'
		}
	});
</script>
</head>
<body>
		<form id="ff" method="post">
				
			<div id="container">
				<font color="blue"><h2>晚到赔偿</h2></font>
				<hr/>
				<div id="top">
					
					<div class="xiang">审核状态:<input class="easyui-validatebox textbox" required="required"				validType="equals" name="approval" id="approval"
							style="width: 80px; height: 20px;">
					</div>
					<a id="btnSave" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'">保存</a>

				</div>
				<div id="baseInfo">
					<div class="hang">
						<div class="clabel">
							运 单 号：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox validatebox"
								required="true" readonly="true" id="waybill" name="waybill" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							发货日期：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-datebox" readonly="true"
								id="sddate" name="sddate" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							到货日期：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-datebox" readonly="true"
								id="arrdate" required="true" name="arriveDate" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							运期：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" readonly="true"
								id="days" name="days" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							到货件数：
						</div>
						<div class="xiang">
							<input id="pics" type="text" class="easyui-numberbox validatebox"
								required="true" name="pics" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							完好状态：
						</div>
						<div class="xiang">

							<select name="wstatus" class="easyui-combobox"
								style="width: 100px; panelHeight: 80px;">
								<option value="0">完好</option>
								<option value="1">有破损丢失</option>
							</select>
						</div>

					</div>

					<div class="hang">
						<div class="clabel">
							承诺运期：
						</div>
						<div class="xiang">
							<input id="inDate" type="text"
								class="easyui-numberbox validatebox" required="true"
								name="inDate" />
						</div>

					</div>
				</div>
				<div id="feeInfo">
					<div class="hang">
						<div class="clabel">
							晚到天数：
						</div>
						<div class="xiang">
							<input id="delayDate" type="text"
								class="easyui-numberbox validatebox" required="true"
								name="delayDate" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							赔偿方式：
						</div>
						<div class="xiang">
							<select id="model" name="model" class="easyui-combobox"
								style="width: 100px; panelHeight: 80px;">
								<option value="0">按天计算</option>
								<option value="1">重新定价</option>
							</select>
						</div>



					</div>
					<div class="hang">
						<div class="clabel">
							晚到重量：
						</div>
						<div class="xiang">
							<input id="delayWeight" type="text"
								class="easyui-numberbox validatebox" required="true"
								name="delayWeight" />
						</div>

					</div>

					<div class="hang">
						<div class="clabel">
							赔偿系数：
						</div>
						<div class="xiang">
							<input id="delayRate" type="text"
								class="easyui-numberbox validatebox" precision="2"
								required="true" name="delayRate" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							赔偿金额：
						</div>
						<div class="xiang">
							<input id="delayIndemnity" type="text"
								class="easyui-numberbox validatebox" required="true"
								name="delayIndemnity" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							赔偿日期：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-datebox" 
								id="indemDate" required="true" name="indemDate" />
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							赔偿方式：
						</div>
						<div class="xiang">
							<select id="mothed" name="mothed" class="easyui-combobox"
								style="width: 100px; panelHeight: 80px;">
								<option value="0">减运费</option>
								<option value="1">国内付	</option>
							</select>
						</div>

					</div>
					<div class="hang">
						<div class="clabel">
							赔偿状态：
						</div>
						<div class="xiang">

							<select id="status" name="status" class="easyui-combobox"
								style="width: 100px; panelHeight: 80px;">
								<option value="0">未赔付</option>
								<option value="1">已赔付</option>
							</select>
						</div>

					</div>
				</div>
				<div id="outInfo">
					<div class="hang">
						<div class="clabel">
							外配日期：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-datebox" id="outSdDate"
								required="true" name="outSdDate" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							外承运期：
						</div>
						<div class="xiang">
							<input id="outInDate" type="text"
								class="easyui-numberbox validatebox" required="true"
								name="outInDate" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							外晚天数：
						</div>
						<div class="xiang">
							<input id="outDelayDate" type="text"
								class="easyui-numberbox validatebox" required="true"
								name="outDelayDate" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							外赔单价：
						</div>
						<div class="xiang">
							<input id="outDelayRate" type="text"
								class="easyui-numberbox validatebox" required="true"
								precision="2" name="outDelayRate" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							外赔金额：
						</div>
						<div class="xiang">
							<input id="outIndemnity" type="text"
								class="easyui-numberbox validatebox" required="true"
								name="outIndemnity" />
						</div>
					</div>
					<div class="hang">
						<div class="clabel">
							备&nbsp;&nbsp;注：
						</div>
						<div class="xiang">
							<input type="text" class="easyui-textbox" multiline="true"
								style="width: 300px; height: 60px" name="remarks" />
						</div>
				</div>
				</div>
				
				
					<input type="hidden" name="id" />
					<input type="hidden" name="bitch" />
					<input type="hidden" name="lineId" />
					<input type="hidden" name="sender" />
					<input type="hidden" name="custId" />
					<input type="hidden" name="custName" />
					<input type="hidden" name="rater" />
				

			</div>
		</form>



	</body>
</html>
