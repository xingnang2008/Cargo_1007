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
			    url:'Indemnify/Indemnify-find.action', 
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
				rowStyler: function(index,row){
					if (row.approval>0){
						return 'background-color:#6293BB;color:#fff;';
					}
					
				},			
				onLoadSuccess:totalTarget,
				onClickRow:totalTarget,
				onSelectAll:totalTarget,
				onCheck:totalTarget,
				onUncheck:totalTarget,	
				//显示分页条				
				
				frozenColumns:[[
					{field:'z',checkbox:true},
					{field:'id',title:'编号',width:50}
					
				]],
				toolbar: [{
					iconCls: 'icon-add',
					text:'新建',
					handler: function(){
						$("#win").window({
							title:'新建记录',
							width:'100%',
							height:'100%',
							content:'<iframe src="Indemnify_saveInput.jsp" frameborder="0" width="100%" height="100%"> </iframe>'
						});
					
						}
					},
					{
						iconCls: 'icon-edit',
						text:'编辑记录',
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
								content:'<iframe title="" src="Indemnify_updateInput.jsp" frameborder="0" width="100%" height="100%"/>'
							});
					}}},{
						iconCls: 'icon-more',
						text:'导入赔偿信息',
						handler: function(){				
						
						//1.弹出导入数据页面
						$("#win").window({
							title:'赔偿信息',
							width:'100%',
							height:'100%',
							content:'<iframe title="导入数据" src="Indemnify_Uploadfile.jsp" frameborder="0" width="100%" height="100%"/>'
						});
														
						}	
						
					},
					'-',
					{
						iconCls: 'icon-reload',
						text:'刷新',
						handler: function(){$("#dg").datagrid("reload");}
					},
					{
						iconCls: 'icon-remove',
						text:'删除记录',
						handler: function(){
						var rows =$("#dg").datagrid("getSelections");
						if(rows.length ==0){
							$.messager.show({
								title:'选择行',
								msg:'至少要选中一行，进行删除操作。',
								timeout:2000,
								showType:'slide'
							});

						}else{
							//提示是否删除，如果确认，执行删除
							$.messager.confirm("删除确认对话框","是否要删除选中的记录",function(r){
								if(r){
									//获取被选中的记录，后从记录中获取相应的id
									var ids ="";
									
									for(var i=0;i<rows.length;i++){
										ids += rows[i].id+",";
										
									}
									//拼接id的值
									ids = ids.substring(0,ids.lastIndexOf(","));
									
									//发送ajax请求
									$.post("Indemnify-deleteByIds.action",{ids:ids},function(result){
										if(result =="true"){
											$.messager.show({
												title:'删除成功',
												msg:'删除成功',
												timeout:2000,
												showType:'slide'
											
											});
											//取消选中所有行
											$("#dg").datagrid("uncheckAll");
											//重新刷新页面
											$("#dg").datagrid("reload");
											
										}else{
											$.messager.show({
												title:'删除错误',
												msg:'删除失败！',
												timeout:2000,
												showType:'slide'
											});
										}
									},"text");
								}
							});
						}
						}
					},{
						iconCls: 'icon-redo',
						text:'审核赔偿',
						handler: function(){
						var rows =$("#dg").datagrid("getSelections");
						
						var editable = $('#approval').combobox('getValue');
						console.info(editable);
						if(editable ==""){
							$.messager.show({
								title:'选择审核或取消审核赔偿',
								msg:'请选择 “审核”或“取消审核”',
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
									$.post("Indemnify-approvalIndemnify.action",{ids:ids,approvalId:editable},function(result){
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
						text:"<input type='text' id='approval' />"   
					   
					},{
						iconCls: 'icon-more',
						text:'导出赔偿表',
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
										var waybills ="";
										for(var i=0;i<rows.length;i++){
											waybills += rows[i].waybill+",";
										}
										//拼接id的值
										waybills = waybills.substring(0,waybills.lastIndexOf(","));
										//1.弹出导出数据页面downloadReport
										var surl='<iframe src="Indemnify_download.jsp?waybills='+waybills+'" frameborder="0" width="100%" height="100%"/>';
										console.info(surl);
										//1.弹出导出数据页面
										$("#win").window({
											title:'导出明细',
											width:'60%',
											height:'60%',
											content:surl
										});																		
									}
							}
					}],
				
			    columns:[[ 	
						{field:'waybill',title:'运单号',width:120,align:'center'},
						{field:'lineId',title:'线路',align:'center',width:100},
						{field:'bitch',title:'批次',align:'center',width:180},
						{field:'sender',title:'发货人',align:'center',width:90},		      
			              {field:'indemDate',title:'赔偿日期',align:'center',
				               formatter:function(value,row,index){
								 if(value != null){
										var unixTimestamp = new Date(value);  
			                        	return unixTimestamp.toLocaleDateString();
									 }else{
										 return "-";
									 }
									}  ,width:90},	
			              {field:'payMethod',title:'赔付方式',
										 formatter:function(value,row,index){
										switch(value){
						        		case 0: return "运费中减";
						        		break;
						        		case 1: return "现金";
						        		break;
						        		case 2: return "转账";
						        		break;
						        		case 3: return "其他";
						        		break;
									    }
								}  ,width:90},
						 {field:'weight',title:'重量',align:'right',width:80},
			            {field:'worth',title:'货值',align:'right',width:80},
			            {field:'yiWuBao',title:'义保',align:'right',width:80},
			           
			            {field:'indemWeight',title:'丢失重量',align:'right',
		                    styler: function(value,row,index){
        					return 'font-weight: bold;';
        				}, width:80},
						{field:'indemWorth',title:'赔偿货值',align:'right',width:80},
						{field:'price',title:'运价',align:'right',width:80},
						{field:'returnBill',title:'退运费',align:'right',width:80},						  
			            {field:'indemnity',title:'赔偿金额',align:'right', 
				            styler: function(value,row,index){
        					return 'font-weight: bold;color:#FF0000';
        				}, width:80},
			           

			            


			            
			            {field:'reason',title:'赔偿原因',width:180},
						{field:'status',title:'赔偿状态',
							 formatter:function(value,row,index){
							switch(value){
			        		case 0: return "未赔付";
			        		break;
			        		case 1: return "已经赔付";
			        		break;
			        		
						    }
							}  ,width:100},
						{field:'outWorth',title:'外货值',align:'right',width:80},
						{field:'outPrice',title:'外单价',align:'right',width:80},	
						{field:'outYiWuBao',title:'外义保',align:'right',width:80},
						{field:'outIndemWeight',title:'外丢重量',align:'right',width:80},

						{field:'outIndemWorth',title:'外丢货值',align:'right',width:80},
						{field:'outReturnBill',title:'外返运费',align:'right',width:80},
													
						{field:'outIndemnity',title:'外赔偿金额',align:'right', 
				            styler: function(value,row,index){
        					return 'font-weight: bold;color:#006633';
        				}, width:80},
						{field:'outStatus',title:'外赔偿状态',
								 formatter:function(value,row,index){
								switch(value){
				        		case 0: return "未赔付";
				        		break;
				        		case 1: return "已经赔付";
				        		break;
				        		
							    }
						}  ,width:100},
						 {field:'outIndemDate',title:'外赔偿日期',align:'center',
				               formatter:function(value,row,index){
								 if(value != null){
										var unixTimestamp = new Date(value);  
			                        	return unixTimestamp.toLocaleDateString();
									 }else{
										 return "-";
									 }
									}  ,width:100},	
						{field:'approval',title:'审核状态',
							 formatter:function(value,row,index){
							switch(value){
			        		case 0: return "未审核";
			        		break;
			        		case 1: return "已审核";
			        		break;
			        		
						    }
					}  ,width:100}, 
			              
							{field:'custId',title:'客户号',align:'center',width:80},
							{field:'custName',title:'收货人',align:'center',width:120},
							{field:'rater',title:'经办人',align:'center',width:100},
										       				        
					        {field:'remarks',title:'备注',align:'left',width:150}
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
			panelHeight:200,
			panelWidth:120,
			width:120
		}); 
		$("#approval").combobox({

			valueField: 'value',
			textField: 'label',
			panelHeight:'auto',
			panelWidth:100,
			width:100,
			data: [{
				label: '   --',
				value: ''
			},{
				label: '   审核 ',
				value: '1'
			},{
				label: ' 取消审核',
				value: '0'
			}] 

		});
		
		
	});
	function totalTarget(){
    	//计算函数
    	 var picTotal = 0;//计算pic的总和
    	   
    	    
    	    var rows = $('#dg').datagrid('getSelections');//获取当前的数据行
    	    for (var i = 0; i < rows.length; i++) {
    	    	picTotal += rows[i]['indemnity'];
    	    }
    	    //新增  显示统计信息
    	   $("#pic").text(picTotal);
    	  
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
			<div class="label">赔偿金额:</div><div class="hang"><p id="pic"></p></div>	
		</div>
	</div>
	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true">	        
	</div>  
 </div> 
 
</body>  


</html>
