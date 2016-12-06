<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="../head.jspf"%>

		<link rel="stylesheet"
			href="<%=basePath%>css/Waybill/Waybill_track.css" type="text/css" />


		<script type="text/javascript">
	$(function(){
		$('#btnSearch').click(function(){
			$('#dg').datagrid('load',{
				bitch: $('#bitch').combobox('getValue'),
				lineId:$('#lineId').combobox('getValue'),
				waybill: $('#waybill').val(),
				custId: $('#custId').val(),
				sender: $('#sender').val(),
				raterName: $('#rater').val(),
				procurator: $('#procurator').val(),
				statusId:$('#statusId').combobox('getValue'),
				stdate: $('#stdate').datebox('getValue'), 
				enddate: $('#enddate').datebox('getValue')	
			});
		});	
		$('#btnReset').click(function(){
			
			$('#bitch').combobox('setValue',""),
			$('#lineId').combobox('setValue',""),				
			$('#waybill').textbox("clear"),
			$('#custId').textbox("clear"),
			$('#sender').textbox("clear"),
			$('#rater').textbox("clear"),
			$('#procurator').textbox("clear"),				
			$('#statusId').combobox('setValue',""),
			$('#stdate').datebox('setValue',""), 
			$('#enddate').datebox('setValue',"")	
		
		});	
		$('#dg').datagrid({    
				//请求的url地址
			    url:'<%=basePath%>admin/Waybill/Waybill-findUnArrivalTrack.action',
			    queryParams: {
					bitch: 'bitch',
					lineId:'lineId',
					waybill: 'waybill',
					custId: 'custId',
					sender:'sender',
					raterName: 'rater',
					procurator:'procurator',
					statusId:'statusId',
					stdate: 'stdate', 
					enddate: 'enddate'					
				},
			    loadMsg:'数据加载中，请等待...',
				//隔行换色——斑马线
				fit:true,
				striped:true,
				//数据同行显示
				nowrap:true,
				//自动适应列，如设为true则不会出现水平滚动条，在演示冻结列此参数不要设置
				fitColumns:false,
				//单行选择，全选功能失效
				singleSelect:false,
				//显示分页条				
				
				//pagination:true,
				//排序
				//sortName:'id',
				//sortOrder:'desc',
				//remoteSort:false,
				onLoadSuccess:totalTarget,
				onClickRow: function(rowIndex, rowData){
					
					$('#dgTrack').datagrid('load',{
						waybill: rowData.waybill,		
												
					});
					totalTarget();
				},
				onSelectAll:totalTarget,
				onCheck: function(rowIndex, rowData){
					
					$('#dgTrack').datagrid('load',{
						waybill: rowData.waybill,		
												
					});
					totalTarget();
				},
				onUncheck:totalTarget,
				onDblClickRow: function(rowIndex, rowData){
					
					$('#dgTrack').datagrid('load',{
						waybill: rowData.waybill,		
												
					});
					totalTarget();
				},
				
				//同列属性，冰结在最左侧
				//pageSize: 300,//每页显示的记录条数，默认为10  
	       		//pageList: [300,500],//可以设置每页记录条数的列表  
	       		rowStyler: function(index,row){
					
					switch(row.statusId){
	        		
	        		case 3: return 'background-color:#6293BB;color:#ff0000;';
	        		break;					        		
	        		case 5: return 'background-color:#6293BB;color:#0000FF;';
	        		break;
	        		case 6: return 'background-color:#6293BB;color:#003300;';
	        		break;
	        		case 7: return 'background-color:#666;color:#003300;';
	        		break;
	        		case 8: return 'color:#ff0000;';
	        		break;
	        		
	        	}
				},
				       		
				frozenColumns:[[
					{field:'z',checkbox:true},		
							
					{field:'waybill',title:'运单号',align:'center',width:200}
				]],
				
			    columns:[[ 
					         {field:'sddate',title:'发货日期', 
						        formatter:function(value,row,index){
						        	if(value!=""){
		                        	var unixTimestamp = new Date(value);  
		                        	return unixTimestamp.toLocaleDateString();
						        	}else{
										return "";
						        	}  
		                        } , align:'center',width:70},
		                        {field:'statusId',title:'状态号',
						        	 formatter:function(value,row,index){ 
						        	switch(value){
						        		case 1: return "新建";
						        		break;
						        		case 2: return "已配仓";
						        		break;
						        		case 3: return "已分货，欠款";
						        		break;		
						        		case 4: return "已到货";
						        		break;			        		
						        		case 5: return "存货";
						        		break;
						        		case 6: return "已结束";
						        		break;
						        		case 7: return "灭失，已赔偿";
						        		break;
						        		case 8: return "问题处理中";
						        		break;
						        		
						        	}
		                        } ,align:'center',width:80},
		                        {field:'pics',title:'包数',align:'center',
				                    styler: function(value,row,index){
		        					return 'font-weight: bold;';
		        				}, width:70},
			                    
			                    {field:'arrPics',title:'到货包数',align:'center',
			                    	styler: function(value,row,index){
			                    		if(value== row.pics){
			        						return 'font-weight: bold;color:#006633';
			                    		}else{
			                    			return 'font-weight: bold;color:#FF0000';
			                    		}
			        				}, width:70},
			        				
			        				{field:'unArrPics',title:'未到包数',align:'center',
					                    styler: function(value,row,index){
			        					if(value != 0){
			        						return 'font-weight: bold;color:#FF0000';
			        					}
			        				}, width:70},
						       
						        {field:'total',title:'应收金额',align:'right',width:70},
						        {field:'actualSum',title:'实收金额',align:'right',width:70},	
		                    {field:'lineId',title:'运输线路',align:'center',width:80},
			                {field:'bitch',title:'批次',align:'center',width:180},
			                {field:'destName',title:'目的地',align:'center',width:100},		                    
		                    {field:'sender',title:'发货人',align:'center',width:80},
		                    {field:'transType',title:'运输类型',align:'center',width:80},
		                    
					        
					      
					       				       
					        {field:'orderNo',title:'货源地',align:'center',width:80},
					       
					        
					        {field:'procurator',title:'代理人',align:'center',width:80},
					        {field:'raterName',title:'经办人',align:'center',width:80},
					    
					        {field:'weight',title:'重量',align:'right',width:80},
					        {field:'volumu',title:'体积',align:'right',width:70},
					        {field:'goods',title:'品名',align:'center',width:100},
					        {field:'quantity',title:'数量',align:'center',width:80},
					     		        
					        {field:'remarks',title:'分货备注',align:'left',width:100}
				]]    
			},'json');
		$('#dgTrack').datagrid({    
			//请求的url地址
		    url:'<%=basePath%>admin/Track/Track-find.action', 
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
			frozenColumns:[[
				{field:'z',checkbox:true},
				{field:'id',title:'序号' ,align:'center',width:50},
				{field:'waybill',title:'运单号' ,align:'center',width:150}
				
			]],
			
			
		    columns:[[ 							
						{field:'arriveDate',title:'到货日期',align:'center',
							 formatter:function(value,row,index){
							 if(value != null){
								var unixTimestamp = new Date(value);  
	                        	return unixTimestamp.toLocaleDateString();
							 }else{
								 return "-";
							 }
							}  ,width:100},	
							{field:'pics',title:'到货件数',align:'center',width:60},
							{field:'wstatus',title:'完整状态',
								formatter:function(value,row,index){ 
						   		switch(value){
							   		case 0: return "完好";
							   		break;
							   		case 1: return "有破损或丢失";
							   		break;						        		
						   		}
						   },align:'center',width:80},
						   {field:'days',title:'运期',align:'center',width:60},
							{field:'custId',title:'客户号',align:'center',width:60},
							{field:'custName',title:'收货人',align:'center',width:120},
							
							
							
							
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
					         
				        {field:'remarks',title:'备注',align:'center',width:200}
			]]    
		});
		


		
		//线路选择框
		$("#lineId").combobox({
			url:'<%=basePath%>admin/Line/Line-listAll.action',
			editable:true,
			valueField:'lineId',
			textField:'lineId',
			panelHeight:200,
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
			panelHeight:200,
			panelWidth:120,
			width:120
		}); 
		$("#status").combobox({
			valueField: 'value',
			textField: 'label',
			panelHeight:'auto',
			panelWidth:150,
			width:150,
			data: [{
				label: '   --',
				value: ''
			},{
				label: '已到货',
				value: '4'
			},{
				label: '存货',
				value: '5'
			},{
				label: '已结束',
				value: '6'
			}
			,{
				label: '灭失，已赔偿',
				value: '7'
			},{
				label: '问题处理中',
				value: '8'
			}] 

		});
		$("#tDate").datebox(); //到货日期
		
		$("#rDate").datebox(); //收款日期
		$("#model").combobox({
			valueField: 'value',
			textField: 'label',
			panelHeight:'auto',
			panelWidth:150,
			width:150,
			data: [{
				label: '按天计算',
				value: '0'
			},{
				label: '重新定价',
				value: '1'
			}] 

		});
	}); 
		 function totalTarget(){
		    	//计算函数
		    	 var picTotal = 0//计算pic的总和
		    	    ,weightTotal=0//统计weight的总和
		    	    ,volTotal=0,//统计vol的总和
		    	    arrPics= 0,
		    	    sum =0,//统计应收的总和
		    	    arrSum =0;//统计结算的总和
		    	    
		    	    var rows = $('#dg').datagrid('getSelections')//获取当前的数据行
		    	    
		    	   
		    	    
		    	    for (var i = 0; i < rows.length; i++) {
		    	    	picTotal += rows[i]['pics'];
		    	    	weightTotal += rows[i]['weight'];
		    	    	volTotal+= rows[i]['volumu'];
		    	    	arrPics+= rows[i]['arrPics'];
						sum += rows[i]['total'];
						arrSum += rows[i]['actualSum'];
		    	    	
		    	    }
		    	    //新增  显示统计信息
		    	   $("#pic").text(picTotal);
		    	   $("#weight").text(weightTotal.toFixed(1));
		    	   $("#volum").text(volTotal.toFixed(2));
		    	   $("#arrPics").text(arrPics);
		    	   $("#sum").text(sum.toFixed(0));
		    	   $("#arrSum").text(arrSum.toFixed(0));
		    	   
		    	  
		      }
	
	
	</script>

	</head>


	<body>
		<div id="container">

			<div id="searchDiv">
				<div class="line">
					<div class="label">
						线路
					</div>
					<div class="hang">
						<input type="text" id="lineId" name="lineId" style="width: 120px" />
					</div>

					<div class="label">
						批次
					</div>
					<div class="hang">
						<input type="text" id="bitch" name="bitch" style="width: 120px" />
					</div>

					<div class="label">
						客户号
					</div>
					<div class="hang">
						<input type="text" id="custId" class="easyui-textbox"
							name="custId" style="width: 100px" />
					</div>

					<div class="label">
						运单号
					</div>
					<div class="hang">
						<input type="text" id="waybill" class="easyui-textbox"
							name="waybill" style="width: 120px" />
					</div>

					<div class="label">
						发货人
					</div>
					<div class="hang">
						<input type="text" id="sender" class="easyui-textbox"
							name="sender" style="width: 100px" />
					</div>

					<div class="label">
						经办人
					</div>
					<div class="hang">
						<input type="text" id="rater" class="easyui-textbox"
							name="raterName" style="width: 100px" />
					</div>

				</div>
				<div class="line">

					<div class="label">
						代理人
					</div>
					<div class="hang">
						<input type="text" id="procurator" class="easyui-textbox"
							name="procurator" style="width: 100px" />
					</div>

					<div class="label">
						起始日期
					</div>
					<div class="hang">
						<input type="text" id="stdate" class="easyui-datebox"
							name="stdate" style="width: 120px" />
					</div>
					<div class="label">
						截止日期
					</div>
					<div class="hang">
						<input type="text" id="enddate" class="easyui-datebox"
							name="enddate" style="width: 120px" />
					</div>
					<div class="label">
						货物状态
					</div>
					<div class="hang">
						<select id="statusId"  class="easyui-combobox"
							style="width: 120px; panelHeight: 200px;">
							<option value="">全部</option>
							<option value="1">新建</option>
							<option value="2">已配仓</option>
							<option value="3">已分货，欠款</option>
							<option value="4">已到货</option>
							<option value="5">存货</option>
							<option value="6">已结束</option>
							<option value="7">灭失，已赔偿</option>
							<option value="8">问题处理中</option>

						</select>
					</div>
					<a id="btnSearch" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a>
					<a id="btnReset" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-clear'">清空</a>
				</div>


			</div>

			<div id="tableHH">
				<div id="tableDG">
					<table id="dg"></table>
				</div>
				
				<div id="tableXX">
					<table id="dgTrack"></table>
				</div>
				
			</div>
			<div id="bottom">
					<div class="bt1">
						<div class="label">
							包数
						</div>
						<div class="hang">
							<p id="pic"></p>
						</div>
						<div class="label">
							重量
						</div>
						<div class="hang">
							<p id="weight"></p>
						</div>
						<div class="label">
							体积
						</div>
						<div class="hang">
							<p id="volum"></p>
						</div>
						<div class="label">
							到货包数
						</div>
						<div class="hang">
							<p id="arrPics"></p>
						</div>
						<div class="label">
							应收款
						</div>
						<div class="hang">
							<p id="sum"></p>
						</div>
						<div class="label">
							已收款
						</div>
						<div class="hang">
							<p id="arrSum"></p>
						</div>

					</div>
				</div>
			<div id="win"
				data-options="collapsible:false,minimizable:false,maxmizable:true,maximized:true,modal:true">

			</div>

		</div>
	</body>


</html>
