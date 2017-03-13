<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@include file="../head.jspf" %>
	<link rel="stylesheet" href="<%=basePath%>css/Track/Track_list.css" type="text/css" />	
	<script type="text/javascript">
	$(function(){
		$('#btnSearch').click(function(){
			$('#dg').datagrid('load',{
				bitch: $('#bitch').combobox('getValue'),
				lineId:$('#lineId').combobox('getValue'),
				waybill: $('#waybill').val(),
				custId:$('#custId').val(),				
				sender: $('#sender').val(),
				rater: $('#rater').val(),				
				stdate: $('#stdate').datebox('getValue'), 
				enddate: $('#enddate').datebox('getValue')	
			});
		});	
		$('#dg').datagrid({    
				//请求的url地址
			    url:'Track-find.action', 
			    queryParams: {
					bitch: 'bitch',
					lineId:'lineId',
					waybill:'waybill',					
					custId: 'custId',
					sender:'sender',
					rater: 'rater',					
					stdate: 'stdate', 
					enddate: 'enddate'					
				},
			   loadMsg:'请等待...',
				//隔行换色——斑马线
				fit:true,
				striped:true,
				//数据同行显示
				nowrap:true,
				//自动适应列，如设为true则不会出现水平滚动条，在演示冻结列此参数不要设置
				fitColumns:false,
				//单行选择，全选功能失效
				singleSelect:false,
							
				onLoadSuccess:totalTarget,
				onClickRow:totalTarget,
				onSelectAll:totalTarget,
				onCheck:totalTarget,
				onUncheck:totalTarget,				
				//同列属性，冰结在最左侧totalTarget
				//rowStyler: function(index,row){
				//	if( row[1].wstatus!=0){
				//		return 'color:#ff0000;';
				//	}
					
				//},
				rowStyler: function(index,row){
					if (row.approval>0){
						return 'background-color:#6293BB;color:#fff;';
					}
					
				},
				frozenColumns:[[
					{field:'z',checkbox:true},
					{field:'id',title:'序号' ,align:'center',width:50},
					{field:'waybill',title:'运单号' ,align:'center',width:150},
					
				]],
				toolbar: [{
					iconCls: 'icon-edit',
					text:'编辑',
					handler: function(){
					var rows =$("#dg").datagrid("getSelections");
					if(rows.length !=1){
						$.messager.show({
							title:'错误提示',
							msg:'一次只能更新一条记录',
							timeout:2000,
							showType:'slide'
						});
					}else{
						//1.完成弹出更新页面
						$("#win").window({
							title:'更新记录',
							width:'100%',
							height:'100%',
							content:'<iframe title="" src="Track_updateInput.jsp" frameborder="0" width="100%" height="100%"/>'
						});
				}}}
					,{	
						text:"赔偿方式：<input type='text' id='model' />"   
					   
					},{	
						text:"赔偿系数：<input type='text' class='easyui-textbox' id='delayRate'/>"
					},{	
						text:"承诺运期：<input type='text' class='easyui-textbox' id='inDate' />"
					},{	
						text:"外配日期：<input type='text' class='easyui-datebox' id='outSdDate'/>"
					},{	
						text:"外承诺运期：<input type='text' class='easyui-textbox' id='outInDate'/>"
					},{	
						text:"外赔系数：<input type='text' class='easyui-textbox' id='outDelayRate'/>"
					},{
						iconCls: 'icon-redo',
						text:'生成晚到赔偿',
						handler: function(){
						var rows =$("#dg").datagrid("getSelections");
						var dRate = $("#delayRate").val();
						var md = $('#model').combobox('getValue');
						var indate =$("#inDate").val();
						var outsd =$("#outSdDate").combobox('getValue');
						var outind =$("#outInDate").val();
						var outdr =$("#outDelayRate").val();
						
						if(md ==""){
							$.messager.show({
								title:'选择输入赔偿系数',
								msg:'计算晚到赔偿，必须要填写赔偿系数',
								timeout:2000,
								showType:'slide'
							});
						}else{
						if(rows.length ==0){
							$.messager.show({
								title:'选择行',
								msg:'至少要选中一行，进行赔偿操作。',
								timeout:2000,
								showType:'slide'
							});

						}else{
									//获取被选中的记录，后从记录中获取相应的id
									var ids ="";
									for(var i=0;i<rows.length;i++){
										ids += rows[i].id+",";
									}
									//拼接id的值
									ids = ids.substring(0,ids.lastIndexOf(","));
									
									//发送ajax请求
									$.post("<%=basePath%>admin/Track/Track-createTrRecord.action",{ids:ids,md:md,drate:dRate,indate:indate,outsddate:outsd,outindate:outind,outdelayrate:outdr},function(result){
										if(result =="true"){

											//取消选中所有行
											$("#dg").datagrid("uncheckAll");
											//重新刷新页面
											$("#dg").datagrid("reload"); 
										}else if(result =="exsit"){
											$.messager.show({
												title:'赔偿记录已存在。',
												msg:'此运单的晚到赔偿记录已经存在。',
												timeout:2000,
												showType:'slide'
											});
										}						
									
									},"text");
								
								

						}

						}
		 			}
					},{
						iconCls: 'icon-redo',
						text:'审核赔偿',
						handler: function(){
						var rows =$("#dg").datagrid("getSelections");
						
						var editable = $('#editable').combobox('getValue');
						console.info(editable);
						if(editable ==""){
							$.messager.show({
								title:'选择审核或取消审核',
								msg:'请选择 “审核”或“取消审核”赔偿',
								timeout:2000,
								showType:'slide'
							});
						}else{
						if(rows.length ==0){
							$.messager.show({
								title:'选择行',
								msg:'至少要选中一行，进行审核操作。',
								timeout:2000,
								showType:'slide'
							});

						}else{
									//获取被选中的记录，后从记录中获取相应的id
									var ids ="";
									for(var i=0;i<rows.length;i++){
										ids += rows[i].id+",";
									}
									//拼接id的值
									ids = ids.substring(0,ids.lastIndexOf(","));
									
									//发送ajax请求
									$.post("Track-updateAppOn.action",{ids:ids,editId:editable},function(result){
										if(result =="true"){

											//取消选中所有行
											$("#dg").datagrid("uncheckAll");
											//重新刷新页面
											$("#dg").datagrid("reload");
										}						
									
									},"text");

						}

						}
		 			}
					},{	
						text:"<input type='text' id='editable' />"   
					   
					},{
						iconCls: 'icon-redo',
						text:'导出记录',
						handler: function(){
							var rows =$("#dg").datagrid("getSelections");
								if(rows.length ==0){
									$.messager.show({
										title:'选择行',
										msg:'至少要选中一行，进行导出操作。',
										timeout:2000,
										showType:'slide'
									});

							}else{
								//获取被选中的记录，后从记录中获取相应的id
								var ids ="";
								for(var i=0;i<rows.length;i++){
									ids += rows[i].id+",";
								}
								//拼接id的值
								ids = ids.substring(0,ids.lastIndexOf(","));
								//1.弹出导出数据页面downloadReport
								var surl='<iframe src="Track_download.jsp?ids='+ids+'" frameborder="0" width="100%" height="100%"/>';
								console.info(surl);
								//1.弹出导出数据页面
								$("#win").window({
									title:'货物明细',
									width:'500',
									height:'450',
									content:surl
								});				
							}
						}
						
						}],
				
			    columns:[[ 		
							
								 {field:'model',title:'赔偿方式',align:'center',formatter:function(value,row,index){ 
								   	switch(value){
								   		case 0: return "按天计算";
								   		break;
								   		case 1: return "重新定价";
								   		break;						        		
								   	}
								   },width:60},
								   {field:'delayWeight',title:'晚到重量',align:'center',width:60},
								   {field:'inDate',title:'承诺运期',align:'center',width:60},
								   {field:'delayDate',title:'晚到天数',align:'center',width:60},	
								   {field:'delayIndemnity',title:'晚到赔偿',align:'center',width:60},	
								   {field:'approval',title:'审核',align:'center',formatter:function(value,row,index){ 
								   	switch(value){
								   		case 0: return "未审";
								   		break;
								   		case 1: return "已审核";
								   		break;						        		
								   	}
								   },width:60},	
								{field:'lineId',title:'线路',align:'center',width:100},
								{field:'bitch',title:'批次',align:'center',width:150},
								{field:'sender',title:'发货人',align:'center',width:60},
								{field:'custId',title:'客户号',align:'center',width:60},
								{field:'custName',title:'收货人',align:'center',width:120},
								{field:'rater',title:'经办人',align:'center',width:60},
								
								{field:'pics',title:'到货件数',align:'center',width:60},
								{field:'days',title:'运期',align:'center',width:60},
								{field:'wstatus',title:'完整状态',
									formatter:function(value,row,index){ 
							   	switch(value){
							   		case 0: return "完好";
							   		break;
							   		case 1: return "有破损或丢失";
							   		break;						        		
							   	}
							   },align:'center',width:80},
							   {field:'sddate',title:'发货日期',align:'center',
									 formatter:function(value,row,index){
									 if(value != null){
										var unixTimestamp = new Date(value);  
								   	return unixTimestamp.toLocaleDateString();
									 }else{
										 return "-";
									 }
									}  ,width:100},	
								{field:'arriveDate',title:'到货日期',align:'center',
									 formatter:function(value,row,index){
									 if(value != null){
										var unixTimestamp = new Date(value);  
			                        	return unixTimestamp.toLocaleDateString();
									 }else{
										 return "-";
									 }
									}  ,width:100},	

							         
					        {field:'remarks',title:'备注',align:'center',width:200}
				]]    
			});
		//线路选择框
		$("#lineId").combobox({
			url:'<%=basePath%>admin/Line/Line-listAll.action',
			editable:true,
			valueField:'lineId',
			textField:'lineId',
			panelHeight:300,
			panelWidth:120,
			width:120,
			onSelect: function(rec){    
				$('#bitch').combobox("clear").combobox("reload",
				 		 			'<%=basePath%>admin/Bitch/Bitch-listByLine.action?lineId='+rec.lineId); 
        	}  
			
		});
		$("#bitch").combobox({
			
			valueField:'bitch',
			textField:'bitch',
			panelHeight:300,
			panelWidth:120,
			width:120
		}); 
		$("#delayRate").textbox({
			width:60
		});
		$("#inDate").textbox({
			width:60
		});
		$("#outInDate").textbox({
			width:60
		});
		$("#outDelayRate").textbox({
			width:60
		});
		
		$('#outSdDate').datebox();
		
		$("#model").combobox({
			valueField: 'value',
			textField: 'label',
			panelHeight:'auto',
			panelWidth:100,
			width:100,
			data: [{
				label: '按天计算',
				value: '0'
			},{
				label: '重新定价',
				value: '1'
			}] 

		});
		$("#editable").combobox({

			valueField: 'value',
			textField: 'label',
			panelHeight:'auto',
			panelWidth:100,
			width:100,
			data: [{
				label: '   --',
				value: ''
			},{
				label: '   审核',
				value: '1'
			},{
				label: '   取消审核',
				value: '0'
			}] 

		});
		
	});
	function totalTarget(){
    	//计算函数
    	 var picTotal = 0,delay=0;//计算pic的总和
    	   
    	    
    	    var rows = $('#dg').datagrid('getSelections');//获取当前的数据行
    	    for (var i = 0; i < rows.length; i++) {
    	    	picTotal += rows[i]['pics'];
    	    	delay += rows[i]['delayIndemnity'];
    	    }
    	    //新增  显示统计信息
    	   $("#pic").text(picTotal);
    	   $("#delayFee").text(Math.round(delay));
      }
	
	</script>

  </head>
  
  
<body>   
<div id="container">
	
		<div id="searchDiv">
			<div class="line">
				<div class="label">线路</div>
				<div class="hang"><input type="text" id="lineId"  name="lineId" style="width:120px" /></div>
							
				<div class="label">批次</div>
				<div class="hang"><input type="text" id="bitch"  name="bitch" style="width:120px" /></div>
				
				<div class="label">客户号</div>
				<div class="hang"><input type="text"  id="custId"  class="easyui-textbox" name="custId" style="width:100px" /></div>
				
				<div class="label">运单号</div>
				<div class="hang"><input type="text" id="waybill" class="easyui-textbox" name="waybill" style="width:120px" /></div>
				
				<div class="label">发货人</div>
				<div class="hang"><input type="text"  id="sender" class="easyui-textbox" name="sender"  style="width:100px"/></div>
				
				<div class="label">经办人</div>
				<div class="hang"><input type="text"  id="rater" class="easyui-textbox" name="raterName"  style="width:100px"/></div>
			
			</div>
			<div class="line">								
				<div class="label">起始日期</div>
				<div class="hang"><input type="text" id="stdate" class="easyui-datebox" name="stdate" style="width:120px"/></div>
				<div class="label">截止日期</div>
				<div class="hang"><input type="text" id="enddate" class="easyui-datebox" name="enddate" style="width:120px" /></div>
			
				<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</div>	
		
		</div>
	<div id="tableDG"><table id="dg"></table></div>
	<div id="bottom">
		<div class="bt1">
			<div class="label">到货包数:</div><div class="hang"><p id="pic"></p></div>
			<div class="label">晚到赔偿:</div><div class="hang"><p id="delayFee"></p></div>	
		</div>
	</div>
	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true">	        
	</div>  
 </div> 
</body>  


</html>
