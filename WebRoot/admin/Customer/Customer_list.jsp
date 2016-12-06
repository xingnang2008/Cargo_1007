<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@include file="../head.jspf" %>
	<link rel="stylesheet" href="<%=basePath%>css/Customer/Customer_list.css" type="text/css" />	
	
	<script type="text/javascript">
	$(function(){
		$('#btnSearch').click(function(){
			$('#DgCustomer').datagrid('load',{
				custId: $('#custId').val(),
				name: $('#name').val(),
				telphone: $('#telphone').val()
			});
		});
		$('#DgCustomer').datagrid({    
				//请求的url地址
			    url:'Customer/Customer-find.action', 
			    queryParams :{
					custId:'',
					name:'',
					telphone:''
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
				singleSelect:true,
				//显示分页条				
				onSelect: function(rowIndex,rowData){
						var pid = rowData.id;
						
						
						$('#DgRelation').datagrid('reload',{
							custId:pid,					
							senderId:null,
							procuratorId:null,
							raterId:null							
						});
						$("#DgSender").datagrid("uncheckAll");
						$("#DgRater").datagrid("uncheckAll");
						$("#DgProcurator").datagrid("uncheckAll");
					},
				frozenColumns:[[
					{field:'z',checkbox:true},
					{field:'id',title:'编号',width:50}
				]],
				toolbar: [{
					iconCls: 'icon-add',
					text:'新建收货人',
					handler: function(){
						$("#win").window({
							title:'新建记录',
							width:'650',
							height:'80%',
							content:'<iframe src="Customer_saveInput.jsp" frameborder="0" width="100%" height="100%"> </iframe>'
						});
					
						}
					},
					{
						iconCls: 'icon-edit',
						text:'编辑记录',
						handler: function(){
						var rows =$("#DgCustomer").datagrid("getSelections");
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
								width:'1025',
								height:'645',
								content:'<iframe title="" src="Customer_updateInput.jsp" frameborder="0" width="100%" height="100%"/>'
							});
					}}},
					{
						iconCls: 'icon-remove',
						text:'删除记录',
						handler: function(){
						var rows =$("#DgCustomer").datagrid("getSelections");
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
									$.post("Customer-deleteByIds.action",{ids:ids},function(result){
										if(result =="true"){
											$.messager.show({
												title:'删除成功',
												msg:'删除成功',
												timeout:2000,
												showType:'slide'
											
											});
											//取消选中所有行
											$("#DgCustomer").datagrid("uncheckAll");
											//重新刷新页面
											$("#DgCustomer").datagrid("reload");
											
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
					}
					

					],
			    columns:[[ 			      
					        {field:'custId',title:'客户编号',width:80},
					        {field:'name',title:'收货人名',align:'center',width:200},
					        {field:'telphone',title:'电话',align:'center',width:200},
					        {field:'email',title:'邮箱',align:'center',width:150},
					        {field:'city',title:'城市',align:'center',width:120},
					        {field:'address',title:'地址',align:'center',width:120},		        
					        {field:'remarks',title:'备注',align:'right',width:100}
				]]    
			});

		//发货人关系
		
		$('#DgRelation').datagrid({    
			//请求的url地址
		    url:'<%=basePath%>admin/CustomerRelation/CustomerRelation-find.action', 
		    queryParams: {
			    custId:'custId',
				sender:'sender',
				rater:'rater',
				procurator:'procurator'
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
						
			
			frozenColumns:[[
				{field:'z',checkbox:true},
				{field:'customer.custId',title:'客户编号',
					formatter:function(value,row,index){ 
				   		return row[4].customer.custId;
				    },align:'center',width:60}
				
			]],
			toolbar: [{
				iconCls: 'icon-add',
				text:'新建',
				handler: function(){
					$("#win").window({
						title:'新建记录',
						width:'80%',
						height:'80%',
						content:'<iframe src="<%=basePath%>admin/CustomerRelation/CustomerRelation_saveInput.jsp" frameborder="0" width="100%" height="100%"> </iframe>'
					});
				
					}
				},
				{
					iconCls: 'icon-remove',
					text:'删除记录',
					handler: function(){
					var rows =$("#DgRelation").datagrid("getSelections");
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
									ids += rows[i][4].id+",";
								}
								//拼接id的值
								ids = ids.substring(0,ids.lastIndexOf(","));
								
								//发送ajax请求
								$.post("<%=basePath%>admin/CustomerRelation/CustomerRelation-deleteByIds.action",{ids:ids},function(result){
									if(result =="true"){
										$.messager.show({
											title:'删除成功',
											msg:'删除成功',
											timeout:2000,
											showType:'slide'
										
										});
										//取消选中所有行
										$("#DgRelation").datagrid("uncheckAll");
										//重新刷新页面
										$("#DgRelation").datagrid("reload");
										
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
					handler: function(){$("#DgRelation").datagrid("reload");}
				}],
			
		    columns:[[ 			      
								             			        
						
						{field:'customer.name',title:'收货人名',
							formatter:function(value,row,index){ 
						   		return row[4].customer.name;
						    },align:'center',width:100},
						{field:'customer.telphone',title:'电话',
							formatter:function(value,row,index){ 
						   		return row[4].customer.telphone;
						    },align:'center',width:100},
						
						{field:'sender.sdName',title:'发货人',
							formatter:function(value,row,index){ 
								return row[4].sender.sdName;
						},align:'center',width:70},
						{field:'sender.phone',title:'发货人电话',
							formatter:function(value,row,index){ 
								return row[4].sender.phone;
						},align:'center',width:100},
						{field:'rater.raterName',title:'经办人',
							formatter:function(value,row,index){ 
								return row[4].rater.raterName;
						},align:'center',width:70},	
						{field:'rater.phone',title:'经办人电话',
							formatter:function(value,row,index){ 
								return row[4].rater.phone;
						},align:'center',width:100},
						{field:'procurator.name',title:'代理人',
							formatter:function(value,row,index){ 
								return row[4].procurator.name;
						},align:'center',width:70}
				        
				       
				        
			]]    
		});
		$('#DgSender').datagrid({    
			//请求的url地址
		    url:'<%=basePath%>admin/Sender/Sender-listBySender.action', 
		    queryParams :{sdName:''},
		   loadMsg:'请等待...',
			//隔行换色——斑马线
			fit:true,
			striped:true,
			//数据同行显示
			nowrap:true,
			//自动适应列，如设为true则不会出现水平滚动条，在演示冻结列此参数不要设置
			fitColumns:false,
			//单行选择，全选功能失效
			singleSelect:true,
			//显示分页条				
			pagination:true,
			onSelect: function(rowIndex,rowData){
		    	var pid = rowData.id;
		    	console.info(pid);
				$('#DgRelation').datagrid('reload',{
					custId:null,					
					senderId:pid,
					procuratorId:null,
					raterId:null
				});
				$("#DgCustomer").datagrid("uncheckAll");
				$("#DgRater").datagrid("uncheckAll");
				$("#DgProcurator").datagrid("uncheckAll");
			},
			//同列属性，冰结在最左侧
			pageSize: 200,//每页显示的记录条数，默认为10  
       		pageList: [200,500,1000],//可以设置每页记录条数的列表  
			frozenColumns:[[
				{field:'z',checkbox:true},
				{field:'id',title:'编号',width:50}
				
			]],
			toolbar: [{
				iconCls: 'icon-add',
				text:'新建',
				handler: function(){
					$("#win").window({
						title:'新建',
						width:'650',
						height:'80%',
						content:'<iframe src="<%=basePath%>admin/Sender/Sender_saveInput.jsp" frameborder="0" width="100%" height="100%"> </iframe>'
					});
				
					}
				},
				{
					iconCls: 'icon-edit',
					text:'编辑',
					handler: function(){
					var rows =$("#DgSender").datagrid("getSelections");
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
							title:'更新',
							width:'1025',
							height:'645',
							content:'<iframe title="" src="<%=basePath%>admin/Sender/Sender_updateInput.jsp" frameborder="0" width="100%" height="100%"/>'
						});
				}}},
				{
					iconCls: 'icon-remove',
					text:'删除',
					handler: function(){
					var rows =$("#DgSender").datagrid("getSelections");
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
								$.post("<%=basePath%>admin/Sender/Sender-deleteByIds.action",{ids:ids},function(result){
									if(result =="true"){
										$.messager.show({
											title:'删除成功',
											msg:'删除成功',
											timeout:2000,
											showType:'slide'
										
										});
										//取消选中所有行
										$("#DgSender").datagrid("uncheckAll");
										//重新刷新页面
										$("#DgSender").datagrid("reload");
										
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
				{	
					text:"<input type='text' id='ss' />"
			}],
			
		    columns:[[ 			      
				        {field:'sdName',title:'发货人',width:80},
				        {field:'phone',title:'电话',align:'center',width:90},
				        {field:'email',title:'邮箱',align:'center',width:60},
				        {field:'qq',title:'QQ',align:'center',width:50},
				        {field:'remarks',title:'备注',align:'right',width:50}
			]]    
		});
	//设置分页控件  
	$('#ss').searchbox({
		searcher:function(value,name){
			$('#DgSender').datagrid('load',{sdName:value});
		},
		prompt:'发货人'

	});
//代理人
			$('#DgProcurator').datagrid({    
				//请求的url地址
			    url:'<%=basePath%>admin/Procurator/Procurator-listByProcurator.action', 
			    queryParams :{name:''},
			   loadMsg:'请等待...',
				//隔行换色——斑马线
				fit:true,
				striped:true,
				//数据同行显示
				nowrap:true,
				//自动适应列，如设为true则不会出现水平滚动条，在演示冻结列此参数不要设置
				fitColumns:false,
				//单行选择，全选功能失效
				singleSelect:true,
				//显示分页条				
				pagination:true,
				onSelect: function(rowIndex,rowData){
					var pid = rowData.id;
					$('#DgRelation').datagrid('load',{
						custId:null,					
						senderId:null,
						procuratorId:pid,
						raterId:null
					});
					$("#DgCustomer").datagrid("uncheckAll");
					$("#DgRater").datagrid("uncheckAll");
					$("#DgSender").datagrid("uncheckAll");
					
				},
				//同列属性，冰结在最左侧
				pageSize: 200,//每页显示的记录条数，默认为10  
		   		pageList: [200,500],//可以设置每页记录条数的列表  
				frozenColumns:[[
					{field:'z',checkbox:true},
					{field:'id',title:'编号',width:50}
					
				]],
				toolbar: [{
					iconCls: 'icon-add',
					text:'新建',
					handler: function(){
						$("#win").window({
							title:'新建',
							width:'650',
							height:'80%',
							content:'<iframe src="<%=basePath%>admin/Procurator/Procurator_saveInput.jsp" frameborder="0" width="100%" height="100%"> </iframe>'
						});
					
						}
					},
					{
						iconCls: 'icon-edit',
						text:'编辑',
						handler: function(){
						var rows =$("#DgProcurator").datagrid("getSelections");
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
								width:'1025',
								height:'645',
								content:'<iframe title="" src="<%=basePath%>admin/Procurator/Procurator_updateInput.jsp" frameborder="0" width="100%" height="100%"/>'
							});
					}}},
					{
						iconCls: 'icon-remove',
						text:'删除',
						handler: function(){
						var rows =$("#DgProcurator").datagrid("getSelections");
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
									$.post("<%=basePath%>admin/Procurator/Procurator-deleteByIds.action",{ids:ids},function(result){
										if(result =="true"){
											$.messager.show({
												title:'删除成功',
												msg:'删除成功',
												timeout:2000,
												showType:'slide'
											
											});
											//取消选中所有行
											$("#DgProcurator").datagrid("uncheckAll");
											//重新刷新页面
											$("#DgProcurator").datagrid("reload");
											
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
					
					{	
						text:"<input type='text' id='ssp' />"
				}],
				
			    columns:[[ 			      
					        {field:'name',title:'代理人',width:80},
					        {field:'phone',title:'电话',align:'center',width:100},
					        {field:'cardName',title:'户名',align:'center',width:60},
					        {field:'card',title:'卡号',align:'center',width:100},
					        {field:'bank',title:'银行',align:'center',width:100},					        
					        {field:'remarks',title:'备注',align:'right',width:100}
				]]    
			});
		//设置分页控件  
		$('#ssp').searchbox({
			searcher:function(value,name){
				$('#DgProcurator').datagrid('load',{name:value});
			},
			prompt:'代理人'
		
		});

		//经办人
			$('#DgRater').datagrid({    
				//请求的url地址
			    url:'<%=basePath%>admin/Rater/Rater-listByRaterName.action', 
			    queryParams :{raterName:''},
			   loadMsg:'请等待...',
				//隔行换色——斑马线
				fit:true,
				striped:true,
				//数据同行显示
				nowrap:true,
				//自动适应列，如设为true则不会出现水平滚动条，在演示冻结列此参数不要设置
				fitColumns:false,
				//单行选择，全选功能失效
				singleSelect:true,
				//显示分页条				
				pagination:true,
				onSelect: function(rowIndex,rowData){
			    	var pid = rowData.id;
					ppid = pid[0];
					$('#DgRelation').datagrid('load',{
						custId:null,					
						senderId:null,
						procuratorId:null,
						raterId:pid
					});
					$("#DgCustomer").datagrid("uncheckAll");
					$("#DgProcurator").datagrid("uncheckAll");
					$("#DgSender").datagrid("uncheckAll");
				},
				//同列属性，冰结在最左侧
				pageSize: 100,//每页显示的记录条数，默认为10  
					pageList: [100,150,200],//可以设置每页记录条数的列表  
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
							width:'650',
							height:'80%',
							content:'<iframe src="<%=basePath%>admin/Rater/Rater_saveInput.jsp" frameborder="0" width="100%" height="100%"> </iframe>'
						});
					
						}
					},
					{
						iconCls: 'icon-edit',
						text:'编辑',
						handler: function(){
						var rows =$("#DgRater").datagrid("getSelections");
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
								width:'1025',
								height:'645',
								content:'<iframe title="" src="<%=basePath%>admin/Rater/Rater_updateInput.jsp" frameborder="0" width="100%" height="100%"/>'
							});
					}}},
					{
						iconCls: 'icon-remove',
						text:'删除',
						handler: function(){
						var rows =$("#DgRater").datagrid("getSelections");
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
									$.post("<%=basePath%>admin/Rater/Rater-deleteByIds.action",{ids:ids},function(result){
										if(result =="true"){
											$.messager.show({
												title:'删除成功',
												msg:'删除成功',
												timeout:2000,
												showType:'slide'
											
											});
											//取消选中所有行
											$("#DgRater").datagrid("uncheckAll");
											//重新刷新页面
											$("#DgRater").datagrid("reload");
											
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
						text:"<input type='text' id='ssr' />"
				}],
				
			    columns:[[ 			      
					        {field:'raterName',title:'经办人',width:80},
					        {field:'phone',title:'电话',align:'center',width:100},
					        {field:'cardName',title:'户名',align:'center',width:60},
					        {field:'card',title:'卡号',align:'center',width:100},
					        {field:'bank',title:'银行',align:'center',width:100},					        
					        {field:'remarks',title:'备注',align:'right',width:100}
				]]    
			});
			//设置分页控件  
			$('#ssr').searchbox({
			searcher:function(value,name){
				$('#DgRater').datagrid('load',{raterName:value});
			},
			prompt:'经办人'
			
			});
	});
	
	
	</script>

  </head>
  
  
<body>   
<div id="container">
	<div id="cust">
			<div id="searchDiv">
						<div>
						<div class="label">客户号</div>
						<div class="hang"><input type="text"  id="custId"  class="easyui-textbox" name="custId" style="width:100px" /></div>
						
						<div class="label">收货人</div>
						<div class="hang"><input type="text"  id="name" class="easyui-textbox" name="name"  style="width:100px"/></div>
						
						<div class="label">电话</div>
						<div class="hang"><input type="text"  id="telphone" class="easyui-textbox" name="telphone"  style="width:100px"/></div>
						</div>			
						<div>
						<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						</div>
					</div>
		<div>			
			<div id="tableDG">
					<table id="DgCustomer"></table>
			</div>
			<div id="procuratorDiv">
					<table id="DgProcurator"></table>
			</div>
		</div>	
	</div>
	<hr>
	<div id="bott">
		<div id="relation">
			<table id="DgRelation"></table>	
		</div>
		<div id="senderDiv">
					<table id="DgSender"></table>
			</div>
		<div id="raterDiv">
					<table id="DgRater"></table>
			</div>	
		
	
	</div>
	</div>
<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true"></div>   
        
  
 </div>
</body>  


</html>
