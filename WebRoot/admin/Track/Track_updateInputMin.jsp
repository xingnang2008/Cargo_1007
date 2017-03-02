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
		
		var rows = parent.$("#dgTrack").datagrid("getSelections");
		$("#ff").form('load', {
			id:rows[0].id,
			arriveDate:rows[0].arriveDate,
			waybill:rows[0].waybill,
			custId:rows[0].custId,
			custName:rows[0].custName,
			rater:rows[0].rater,
			sender:rows[0].sender,
			lineId:rows[0].lineId,
			days:rows[0].days,
			pics:rows[0].pics,
			inDate:rows[0].inDate,
			wstatus:rows[0].wstatus,
			approval:rows[0].approval,
			bitch:rows[0].bitch,
			calBy:rows[0].calBy,			
			delayDate:rows[0].delayDate,
			delayIndemnity:rows[0].delayIndemnity,
			delayRate:rows[0].delayRate,
			delayVol:rows[0].delayVol,
			delayWeight:rows[0].delayWeight,			
			model:rows[0].model,						
			sddate:rows[0].sddate,			
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
						parent.$("#dgTrack").datagrid("reload");
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
								id="sddate" required="true" name="sddate" />
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
				
				<div id="outInfo">
					
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
					<input type="hidden" name="custId" />				
					<input type="hidden" name="custName" />
					<input type="hidden" name="rater" />
					<input type="hidden" name="sender" />
					<input type="hidden" name="lineId" />
					<input type="hidden" name="bitch" />
					<input type="hidden" name="calBy" />
					<input type="hidden" name="delayDate" />
					<input type="hidden" name="delayIndemnity" />
					<input type="hidden" name="delayRate" />
					<input type="hidden" name="delayVol" />
					<input type="hidden" name="delayWeight" />
					<input type="hidden" name="model" />
			</div>
		</form>



	</body>
</html>
