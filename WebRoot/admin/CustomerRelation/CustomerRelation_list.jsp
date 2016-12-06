<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@include file="../head.jspf" %>
	<link rel="stylesheet" href="<%=basePath%>css/CustomerRelation/CustomerRelation_list.css" type="text/css" />	
	<script type="text/javascript">
	$(function(){
		$('#btnSearch').click(function(){
			$('#dg').datagrid('load',{
				custId: $('#custId').combobox('getValue'),
				senderId: $('#senderId').combobox('getValue'),
				procuratorId: $('#procuratorId').combobox('getValue'),
				raterId: $('#raterId').combobox('getValue')
				
			});
		});	
		$('#dg').datagrid({    
				//请求的url地址
			    url:'CustomerRelation/CustomerRelation-find.action', 
			    queryParams: {
					custId:'custId',					
					senderId:'senderId',
					procuratorId:'procuratorId',
					raterId:'raterId'
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
				//显示分页条				
				pagination:true,
				
				//同列属性，冰结在最左侧
				pageSize: 100,//每页显示的记录条数，默认为10  
	       		pageList: [100,200,300],//可以设置每页记录条数的列表  
				frozenColumns:[[
					{field:'z',checkbox:true},
					{field:'id',title:'编号',
			        	formatter:function(value,row,index){ 
			       		return row[4].id;
			        },align:'center'}
					
				]],
				toolbar: [{
					iconCls: 'icon-add',
					text:'新建',
					handler: function(){
						$("#win").window({
							title:'新建记录',
							width:'650',
							height:'80%',
							content:'<iframe src="CustomerRelation_saveInput.jsp" frameborder="0" width="100%" height="100%"> </iframe>'
						});
					
						}
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
										ids += rows[i][4].id+",";
									}
									//拼接id的值
									ids = ids.substring(0,ids.lastIndexOf(","));
									
									//发送ajax请求
									$.post("CustomerRelation-deleteByIds.action",{ids:ids},function(result){
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
					}],
				
			    columns:[[ 			      
					        {field:'customer.custId',title:'客户编号',
					        	formatter:function(value,row,index){ 
						       		return row[4].customer.custId;
						        },align:'center'},
					        {field:'customer.name',title:'收货人名',
					        	formatter:function(value,row,index){ 
						       		return row[4].customer.name;
						        },align:'center'},
					        {field:'customer.telphone',title:'电话',
					        	formatter:function(value,row,index){ 
						       		return row[4].customer.telphone;
						        },align:'center'},
					       
					        {field:'sender.sdName',title:'发货人',
					        	formatter:function(value,row,index){ 
					       		return row[4].sender.sdName;
					        },align:'center'},
					        {field:'sender.phone',title:'发货人电话',
					        	formatter:function(value,row,index){ 
					       		return row[4].sender.phone;
					        },align:'center'},
					        {field:'rater.raterName',title:'经办人',
					        	formatter:function(value,row,index){ 
					       		return row[4].rater.raterName;
					        },align:'center'},	
					        {field:'rater.phone',title:'经办人电话',
					        	formatter:function(value,row,index){ 
					       		return row[4].rater.phone;
					        },align:'center'},
					        {field:'procurator.name',title:'代理人',
					        	formatter:function(value,row,index){ 
					       		return row[4].procurator.name;
					        },align:'center'},	
					        {field:'procurator.telphone',title:'代理人电话',
					        	formatter:function(value,row,index){ 
					       		return row[4].procurator.telphone;
					        },align:'center'}
					             
					        
					        
				]]    
			});
		
		//经办人选择框
		$("#raterId").combobox({
			url:'<%=basePath%>admin/Rater/Rater-listAll.action',
			valueField:'id',
			textField:'raterName',				
			panelHeight:200,
			panelWidth:100,
			width:100
		});
		//客户选择框
		$("#custId").combobox({
			url:'<%=basePath%>admin/Customer/Customer-listAll.action',
			valueField:'id',
			textField:'custId',				
			panelHeight:200,
			panelWidth:100,
			width:100
		});
		//发货人选择框
		$("#senderId").combobox({
			url:'<%=basePath%>admin/Sender/Sender-listAll.action',
			valueField:'id',
			textField:'sdName',				
			panelHeight:200,
			panelWidth:100,
			width:100
		});
		//代理人选择框
		$("#procuratorId").combobox({
			url:'<%=basePath%>admin/Procurator/Procurator-listAll.action',
			valueField:'id',
			textField:'name',				
			panelHeight:200,
			panelWidth:100,
			width:100
		});
	});
	
	
	</script>

  </head>
  
  
<body> 
<div id="container">  
		<div id="searchDiv">
			<div>
			<div id="label">客户号</div>
			<div id="hang"><input type="text"  id="custId"   name="custId" style="width:100px" /></div>
			<div id="label">发货人</div>
			<div id="hang"><input type="text"  id="senderId" name="senderId"  style="width:100px"/></div>
			
			<div id="label">经办人</div>
			<div id="hang"><input type="text"  id="raterId" name="raterId"  style="width:100px"/></div>
			<div id="label">代理人</div>
			<div id="hang"><input type="text"  id="procuratorId" name="procuratorId"  style="width:100px"/></div>
			</div>
			<div>
			<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</div>
		</div>
	<div id="tableDG"><table id="dg"></table></div>
	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,maximized:true,modal:true">   
        
</div>  
 
</div>
        

 
</body>  


</html>
