<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@include file="../head.jspf" %>
	 
	<link rel="stylesheet" href="<%=basePath%>css/Waybill/Waybill_list1.css" type="text/css" />	
	 
	
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
			    url:'Waybill-findWaybillFee.action',
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
				onClickRow:totalTarget,
				onSelectAll:totalTarget,
				onCheck:totalTarget,
				onUncheck:totalTarget,

				
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
				
				toolbar: [{	
					text:"收款日期：<input type='text' id='rDate' />"
				},{
					iconCls: 'icon-edit',
					text:'收款',
					handler: function(){
					var rows =$("#dg").datagrid("getSelections");
					var receiptDate = $('#rDate').combobox('getValue');
					
					if(receiptDate ==""){
						$.messager.show({
							title:'选择收款日期',
							msg:'请选择收款日期',
							timeout:2000,
							showType:'slide'
						});
					}else{
					if(rows.length ==0){
						$.messager.show({
							title:'选择行',
							msg:'至少要选中一行，进行锁定操作。',
							timeout:2000,
							showType:'slide'
						});

					}else{
								//获取被选中的记录，后从记录中获取相应的id
						//获取被选中的记录，后从记录中获取相应的id
						var waybills ="";
						for(var i=0;i<rows.length;i++){
							waybills += rows[i].waybill+",";
						}
						//拼接id的值
						waybills = waybills.substring(0,waybills.lastIndexOf(","));
						
						//发送ajax请求
						$.post("<%=basePath%>admin/Receipt/Receipt-updateWaybillsReceipt.action",{waybills:waybills,receiptDate:receiptDate},function(result){

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
					text:"状态：<input type='text' id='status' />"   
						   
				},{
					iconCls: 'icon-add',
					text:'更新状态',
					handler: function(){
					var rows =$("#dg").datagrid("getSelections");
					
					var statusId = $('#status').combobox('getValue');
					console.info(statusId);
					if(statusId ==""){
						$.messager.show({
							title:'选择运单状态',
							msg:'请选择运单状态',
							timeout:2000,
							showType:'slide'
						});
					}else{
					if(rows.length ==0){
						$.messager.show({
							title:'选择行',
							msg:'至少要选中一行，进行锁定操作。',
							timeout:2000,
							showType:'slide'
						});

					}else{
								var waybills ="";
								for(var i=0;i<rows.length;i++){
									waybills += rows[i].waybill+",";
								}
								//拼接id的值
								waybills = waybills.substring(0,waybills.lastIndexOf(","));
								
								//发送ajax请求
								$.post("Waybill-updateWaybillStatusByWaybills.action",{waybills:waybills,sid:statusId},function(result){
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
				}
				
				],
			    columns:[[ 
					         {field:'sddate',title:'发货日期', 
						        formatter:function(value,row,index){
						        	if(value!=""){
		                        	var unixTimestamp = new Date(value);  
		                        	return unixTimestamp.toLocaleDateString();
						        	}else{
										return "";
						        	}  
		                        } , align:'right',width:80},
		                        	
		                   
		                    {field:'pics',title:'包数',align:'center',
			                    styler: function(value,row,index){
	        					return 'font-weight: bold;';
	        				}, width:50},
		                    
		                    {field:'arrPics',title:'到货包数',align:'center',
		                    	styler: function(value,row,index){
		                    		if(value== row.pics){
		        						return 'font-weight: bold;color:#006633';
		                    		}else{
		                    			return 'font-weight: bold;color:#FF0000';
		                    		}
		        				}, width:50},
		        				
		        				{field:'unArrPics',title:'未到包数',align:'center',
				                    styler: function(value,row,index){
		        					if(value != 0){
		        						return 'font-weight: bold;color:#FF0000';
		        					}
		        				}, width:50},
					       
		        			{field:'total',title:'运费',align:'right',width:60},
							{field:'actualSum',title:'实收金额',align:'right',width:60},
							{field:'indemnify',title:'丢失赔偿',align:'right',width:60},							
							{field:'delayIndem',title:'晚到赔偿',align:'right',width:60},
							{field:'arrear',title:'应收合计',align:'right',width:60},
							{field:'sender',title:'发货人',align:'center',width:80},			       
					        {field:'orderNo',title:'货源地',align:'center',width:80},
					        {field:'outIndemnify',title:'外－丢赔偿',align:'right',width:70},
							{field:'outDelayIndemnity',title:'外－晚赔偿',align:'right',width:70},
					        {field:'lineId',title:'运输线路',align:'center',width:80},
			                {field:'bitch',title:'批次',align:'center',width:180},
			                {field:'destName',title:'目的地',align:'center',width:100},		                    
		                    
		                    {field:'transType',title:'运输类型',align:'center',width:80},
					        
					        {field:'procurator',title:'代理人',align:'center',width:80},
					        {field:'raterName',title:'经办人',align:'center',width:80},
					    
					        {field:'weight',title:'重量',align:'right',width:80},
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
					        } ,align:'center',width:80}        
					       
				]]    
			},'json');
		
		


		
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
				label: '已分货，欠款',
				value: '3'
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
	}); 
		 function totalTarget(){
		    	//计算函数
		    	 var picTotal = 0 ;//计算应收合计的总和
		    	    		    	    
		    	    var rows = $('#dg').datagrid('getSelections')//获取当前的数据行
		    	     
		    	    for (var i = 0; i < rows.length; i++) {
		    	    	picTotal += rows[i]['arrear'];
		    	    	    	    	
		    	    }
		    	   
		    	   $("#sum").text(picTotal.toFixed(0));
		    	
		    	   
		    	  
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
				
				<div class="label">代理人</div>
				<div class="hang"><input type="text"  id="procurator" class="easyui-textbox" name="procurator"  style="width:100px"/></div>
				
				<div class="label">起始日期</div>
				<div class="hang"><input type="text" id="stdate" class="easyui-datebox" name="stdate" style="width:120px"/></div>
				<div class="label">截止日期</div>
				<div class="hang"><input type="text" id="enddate" class="easyui-datebox" name="enddate" style="width:120px" /></div>
				<div class="label">货物状态</div>
				<div class="hang"><select id="statusId" name="statusId" class="easyui-combobox"  style="width:120px;panelHeight:100px;">   
						   	  <option value="">全部</option>
						   	   <option value="1">新建</option>  
						   	  <option value="2">已配仓</option>  	
						  	  <option value="3">已分货，欠款</option>   
							  <option value="4">已到货</option>	
						  	<option value="5">存货</option>
						  	<option value="6">已结束</option>
						  	<option value="7">灭失，已赔偿"</option>
						  	<option value="8">问题处理中"</option>
						  			    
						</select> </div>
				<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				<a id="btnReset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">清空</a>
			</div>
		
		
		</div>
	
	
	<div id="tableDG"><table id="dg"></table></div>
	<div class="bottom">
		<div class="bt1">
			
			<div class="label">应收款</div><div class="hang"><p id="sum"></p></div>
			
			
			</div>
		</div>
				
	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,maximized:true,modal:true">   
        
	</div>  
 
</div>
</body>  


</html>
