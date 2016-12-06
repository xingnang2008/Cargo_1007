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
				waybill: $('#waybill').val(),
				custId: $('#custId').val(),
				sender: $('#sender').val(),
				raterName: $('#rater').val(),
				stdate: $('#stdate').datebox('getValue'), 
				enddate: $('#enddate').datebox('getValue')	
			});
		});	
		
		$('#dg').datagrid({    
				//请求的url地址
			    url:'Waybill-find.action',
			    queryParams: {
					bitch: 'bitch',
					waybill: 'waybill',
					custId: 'custId',
					sender:'sender',
					raterName: 'rater',
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
				//显示分页条				
				
				pagination:true,
				//排序
				sortName:'id',
				sortOrder:'desc',
				remoteSort:false,
				//同列属性，冰结在最左侧
				pageSize: 300,//每页显示的记录条数，默认为10  
	       		pageList: [300,500],//可以设置每页记录条数的列表  
	       		rowStyler: function(index,row){
					if (row.editable>0){
						return 'background-color:#6293BB;color:#fff;';
					}
					
				},
				frozenColumns:[[
					{field:'z',checkbox:true},
					{field:'id',title:'编号',width:50},
					{field:'waybill',title:'运单号',align:'center',width:200}
					
				]],
				toolbar: [
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
									$.post("Waybill-deleteByIds.action",{ids:ids},function(result){
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
												msg:'删除失败,必须先删除此单下的货物明细！',
												timeout:2000,
												showType:'slide'
											
											});
										}
									
									
									},"text");
								
								}
							});

						}

						}
					},
					'-',
					{
						iconCls: 'icon-reload',
						text:'刷新',
						handler: function(){$("#dg").datagrid("reload");}
					},{
						iconCls: 'icon-more',
						text:'导入结算数据',
						handler: function(){				
						
						//1.弹出导入数据页面
						$("#win").window({
							title:'结算数据',
							width:'500',
							height:'450',
							content:'<iframe title="导入数据" src="Waybill_UploadOutCheckfile.jsp" frameborder="0" width="100%" height="100%"/>'
						});
														
						}	
						
					},{
						iconCls: 'icon-more',
						text:'生成利润数据',
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
										//1.弹出导出数据页面
										var url =""																		
									}
							}
					},{
						iconCls: 'icon-redo',
						text:'锁定运单',
						handler: function(){
						var rows =$("#dg").datagrid("getSelections");
						
						var editable = $('#editable').combobox('getValue');
						console.info(editable);
						if(editable ==""){
							$.messager.show({
								title:'选择锁定或解锁运单',
								msg:'请选择 “锁定”或“解锁”运单',
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
									var ids ="";
									for(var i=0;i<rows.length;i++){
										ids += rows[i].id+",";
									}
									//拼接id的值
									ids = ids.substring(0,ids.lastIndexOf(","));
									
									//发送ajax请求
									$.post("Waybill-updateLockOnWaybills.action",{ids:ids,editId:editable},function(result){
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
					   
					}],
				
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
		                    {field:'bitch',title:'批次',align:'center',width:100},
		                   
		                    {field:'sender',title:'发货人',align:'center',width:80},
		                    
					        {field:'pics',title:'包数',align:'right',width:50},
					        {field:'weight',title:'重量',align:'right',width:80},
					        {field:'volumu',title:'体积',align:'right',width:70},
					        {field:'goods',title:'品名',align:'center',width:100},
					        {field:'quantity',title:'数量',align:'center',width:80},
					        
					        {field:'custId',title:'客户号',align:'center',width:80},					    
					        {field:'custName',title:'联系人',align:'center',width:100},
					        {field:'custTel',title:'联系电话',align:'center',width:150},
					        {field:'custEmail',title:'邮箱',align:'center',width:100},
					        {field:'custAdd',title:'地址',align:'center',width:80},
					        
					     
					        {field:'sdTel',title:'发货人电话',align:'right',width:100},
					        {field:'isRebate',title:'是否核销',align:'right',width:50},
					        {field:'rebateId',title:'核销编号',align:'right',width:50},					       
					        
					        {field:'advancedZ',title:'垫付￥',align:'right',width:80},			        
					        {field:'advancedU',title:'垫付$',align:'right',width:80},
					        {field:'packfeeZ',title:'包费￥',align:'right',width:80},
					        {field:'packfeeU',title:'包费$',align:'right',width:80},
					        {field:'noaccPackfee',title:'未加包费',align:'right',width:80},
					        {field:'noaccAdvance',title:'未加运费',align:'right',width:80},
					        {field:'worth',title:'货值',align:'right',width:80},
					        {field:'worthRate',title:'保率',align:'right',width:80},
					        {field:'insurance',title:'保费',align:'right',width:80},
					        {field:'indemnity',title:'索赔',align:'right',width:80}, 
					        {field:'price',title:'单价',align:'right',width:80},
					        {field:'sumbill',title:'运费',align:'right',width:80},
					        {field:'payMethod',title:'付款方式',align:'right',width:80},
					        
					        {field:'procurator',title:'代理人',align:'center',width:80},
					        {field:'raterName',title:'经办人',align:'center',width:80},
					        {field:'cod',title:'代收款',align:'right',width:80},
					        {field:'raterRate',title:'折扣',align:'right',width:80},
					        {field:'discount',title:'代理费',align:'right',width:80},

					        {field:'total',title:'应收金额',align:'right',width:80},
					        {field:'actualSum',title:'实收金额',align:'right',width:80},
					        {field:'exchangeRate',title:'汇率',align:'right',width:50},
					        
					        {field:'outPrice',title:'结算价',align:'right',width:80},
					        {field:'outWorth',title:'结算货值',align:'right',width:80},
					        {field:'outWorthrate',title:'结算保率',align:'right',width:80},
					        {field:'outInsurance',title:'结算保费',align:'right',width:80},					        
					        {field:'outSum',title:'结算金额',align:'right',width:80},      
					        
					        {field:'statusId',title:'状态号',
					        	 formatter:function(value,row,index){ 
					        	switch(value){
					        		case 1: return "新建";
					        		break;
					        		case 2: return "已配仓";
					        		break;
					        		case 3: return "已装运";
					        		break;
					        		case 4: return "已清关";
					        		break;
					        		case 5: return "已到达";
					        		break;
					        		case 6: return "已分货";
					        		break;
					        		case 7: return "已存";
					        		break;
					        		case 8: return "问题处理中";
					        		break;
					        		case 9: return "丢失";
					        		break;
					        	}
	                        } ,align:'center',width:80},	
	                        
					       
					       
					     
	                        {field:'lineId',title:'运输线路',align:'center',width:80},
					        {field:'transType',title:'运输类型',align:'center',width:80},
					        {field:'destName',title:'目的地',align:'center',width:80},
					        {field:'destTel',title:'分货电话',align:'center',width:80},
					        {field:'descrip',title:'操作说明',align:'left',width:100},				        
					        {field:'remarks',title:'分货备注',align:'left',width:100},
					        {field:'editable',title:'状态',
					        	 formatter:function(value,row,index){ 
					        	switch(value){
					        		case 0: return "可编辑";
					        		break;
					        		case 1: return "锁定";
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
			panelHeight:'auto',
			panelWidth:100,
			width:100,
			onSelect: function(rec){    
				//$('#bitch').combobox("clear");
	            $('#bitch').combobox({
	            	disabled:false,
            		url:'<%=basePath%>admin/Bitch/Bitch-listByLine.action?lineId='+rec.lineId,
		            valueField:'bitch',
					textField:'bitch',
					panelHeight:'auto',
					panelWidth:100,
					width:100  
	            }).combobox("clear");;
        	}  
			
		});
		$("#bitch").combobox({
			disabled:true,
			valueField:'bitch',
			textField:'bitch',
			panelHeight:'auto',
			panelWidth:100,
			width:100
		}); 
		$("#editable").combobox({

			valueField: 'value',
			textField: 'label',
			data: [{
				label: '--',
				value: ''
			},{
				label: '锁定',
				value: '1'
			},{
				label: '解锁',
				value: '0'
			}] 

		}); 


		
	}) 
		
	
	
	</script>

  </head>
  
  
<body>   
<div id="container">
	
		<div id="searchDiv">
			<div class="line">
				<div class="label">线路</div>
				<div class="hang"><input type="text" id="lineId"  name="lineId" style="width:120px" /></div>
							
				<div class="label">批次</div>
				<div class="hang"><input type="text" id="bitch" class="easyui-combobox"  name="bitch" style="width:120px" /></div>
				
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
			
				<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</div>
		
		
		</div>
	
	<div id="tableDG"><table id="dg"></table></div>
<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,maximized:true,modal:true">   
        
</div>  
 
</div>
</body>  


</html>
