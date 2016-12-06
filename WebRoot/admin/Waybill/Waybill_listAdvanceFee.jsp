<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@include file="../head.jspf" %>
	<link rel="stylesheet"
			href="<%=basePath%>css/Waybill/Waybill_listRaterFee.css" type="text/css" />
	<script type="text/javascript">
	
	$(function(){
		$('#btnSearch').click(function(){
			$('#dg').datagrid('load',{
				
				sender:$('#sender').val(),
				custId:$('#custId').val(),
				advanceStauts:$('#advanceStauts').combobox('getValue'),
				stdate: $('#stdate').datebox('getValue'), 
				enddate: $('#enddate').datebox('getValue')	
			});
		});	
		$('#btnReset').click(function(){
			
			$('#sender').textbox("clear"),
			$('#custId').textbox("clear"),				
			$('#advanceStauts').combobox('advanceStauts',1),
			$('#stdate').datebox('setValue',""), 
			$('#enddate').datebox('setValue',"")	
		
		});	


		
		$('#dg').datagrid({    
				//请求的url地址
			    url:'Waybill/Waybill-listAdvance.action', 
			    queryParams :{
		    		sender:'sender',
		    		custId:'custId',
		    		advanceStauts:'advanceStauts',
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
				fitColumns:true,
				//单行选择，全选功能失效
				singleSelect:false,
				onLoadSuccess:totalTarget,
				onClickRow:totalTarget,
				onSelectAll:totalTarget,
				onCheck:totalTarget,
				onUncheck:totalTarget,		
				sortName:'id',
				sortOrder:'desc',
				remoteSort:false,
				rowStyler: function(index,row){
						if (row.advanceStauts>1){
							return 'background-color:#336666;color:#fff;';
						}
						
				},
				frozenColumns:[[
					{field:'z',checkbox:true},
					{field:'id',title:'编号',width:50}
					
				]],
				toolbar: [{	
					text:"付款日期：<input type='text' id='rDate' />"
				},{
					iconCls: 'icon-redo',
					text:'已结运单',
					handler: function(){
						var rows =$("#dg").datagrid("getSelections");
						var rDate = $('#rDate').combobox('getValue');
						if(rDate ==""){
							$.messager.show({
								title:'选择付款日期',
								msg:'请选择付款日期',
								timeout:2000,
								showType:'slide'
							});
						}else{
								if(rows.length ==0){
									$.messager.show({
										title:'选择行',
										msg:'至少要选中一行，进行已结操作。',
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
											$.post("Waybill-updateAdvanceStatus.action",{ids:ids,advanceStatusId:2,advanceDate:rDate},function(result){
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
					iconCls: 'icon-redo',
					text:'返回未结',
					handler: function(){
					var rows =$("#dg").datagrid("getSelections");
					
				
					if(rows.length ==0){
						$.messager.show({
							title:'选择行',
							msg:'至少要选中一行，进行操作。',
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
								$.post("Waybill-updateAdvanceStatus.action",{ids:ids,advanceStatusId:1},function(result){
									if(result =="true"){

										//取消选中所有行
										$("#dg").datagrid("uncheckAll");
										//重新刷新页面
										$("#dg").datagrid("reload");
									}						
								
								},"text");

					

					}
	 			}
	 			},{
						iconCls: 'icon-more',
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
									var surl='<iframe src="Waybill_downloadAdvance.jsp?ids='+ids+'" frameborder="0" width="100%" height="100%"/>';
									
									
									//1.弹出导出数据页面
									$("#win").window({
										title:'代理明细',
										width:'100%',
										height:'100%',
										content:surl
									});		
							
						}
						}
					},
					'-',
					{
						iconCls: 'icon-reload',
						text:'刷新',
						handler: function(){$("#dg").datagrid("reload");}
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
					        {field:'sender',title:'发货人',width:80},
					        {field:'custId',title:'客户号',align:'center',width:80},
					        {field:'waybill',title:'票号',align:'center',width:100},
					        {field:'bitch',title:'批次',align:'center',width:150},
					        {field:'pics',title:'包数',align:'center',width:60},
					        {field:'weight',title:'重量',align:'center',width:60},
					        {field:'volumu',title:'体积',align:'center',width:60},
					        {field:'goods',title:'品名',align:'center',width:100},
					        {field:'quantity',title:'数量',align:'center',width:60},
					        {field:'advancedZ',title:'垫付款',align:'right',width:80},
					        {field:'noaccAdvance',title:'未加垫付',align:'right',width:80},
					       
					        
					       
					        
					        
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
					        		case 5: return "已到达，存货";
					        		break;
					        		case 6: return "已结束";
					        		break;
					        		case 7: return "灭失";
					        		break;
					        		case 8: return "问题处理中";
					        		break;
					        		
					        	}
	                        } ,align:'center',width:100},					        
					        {field:'advanceStauts',title:'出单状态',
					        	 formatter:function(value,row,index){ 
					        	switch(value){
					        		case 1: return "未结";
					        		break;
					        		case 2: return "已结";
					        		break;					        		
					        		
					        	}
	                        } ,align:'right'}
				]]    
			});
		$("#rDate").datebox(); //收款日期
		
		
	});
	function totalTarget(){
    	//计算函数
    	 var aTotal = 0//计算总和
    	    ,noTotal=0,//统计未加的总和
    	    pics =0,
    	   	total =0;
    	    
    	    var rows = $('#dg').datagrid('getSelections')//获取当前的数据行
    	    
    	   
    	    
    	    for (var i = 0; i < rows.length; i++) {
    	    	pics += rows[i]['pics'];
    	    	aTotal += rows[i]['advancedZ'];
    	    	noTotal += rows[i]['noaccAdvance'];
    	    	   	    	
    	    }
    	    //新增  显示统计信息
    	   $("#pic").text(pics);
    	   $("#Total").text(aTotal+noTotal);
    	  
    	   
    	  
      }
	
	</script>

  </head>
  
  
<body>  
<div id="container">
	<div id="searchDiv">
			
			<div class="line">
				
				<div class="label">发货人</div>
				<div class="hang"><input type="text"  id="sender" class="easyui-textbox" name="procurator"  style="width:100px"/></div>
				<div class="label">客户号</div>
				<div class="hang"><input type="text"  id="custId" class="easyui-textbox" name="procurator"  style="width:100px"/></div>
				
				<div class="label">起始日期</div>
				<div class="hang"><input type="text" id="stdate" class="easyui-datebox" name="stdate" style="width:120px"/></div>
				<div class="label">截止日期</div>
				<div class="hang"><input type="text" id="enddate" class="easyui-datebox" name="enddate" style="width:120px" /></div>
				<div class="hang">
						<select id="advanceStauts"  class="easyui-combobox"
							style="width: 120px; panelHeight: 200px;">
							<option value="1">未出单</option>
							<option value="2">已出单</option>
						</select>
					</div>
				<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				<a id="btnReset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">清空</a>
			</div>
		
		
		</div>
		<div id="tableDG">
					<table id="dg"></table>
		</div>
		<div class="bottom">
		<div class="bt1">
			<div class="label">包数： </div><div class="hang"><p id="pic"></p></div>
			<div class="label">合计： </div><div class="hang"><p id="Total"></p>	</div>
			</div>
		</div>
		<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true">   
		        
		</div>  
 </div> 
</body>  


</html>
