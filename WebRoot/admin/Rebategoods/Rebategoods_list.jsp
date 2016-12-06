<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
   
    
	<%@include file="../head.jspf" %>
	 <link rel="stylesheet" href="<%=basePath%>css/Rebate/Rebate_updateInput.css" type="text/css" />
	 <script language="JavaScript" >
	 $(function(){
			
			$('#gtab').datagrid({    
				//请求的url地址
				
			    url:'<%=basePath%>admin/Rebategoods/Rebategoods-listByRebateId.action',			    
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
				pageSize: 10,//每页显示的记录条数，默认为10  
	       		pageList: [5,10,15,20],//可以设置每页记录条数的列表  
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
							width:500,
							height:450,
							content:'<iframe src="../Rebategoods/Rebategoods-saveInput.action" frameborder="0" width="100%" height="100%"> </iframe>'
						});
					
						}
					},
					{
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
								width:'80%',
								height:'100%',
								content:'<iframe title="" src="Rebate_updateInput.jsp" frameborder="0" width="100%" height="100%"/>'
							});
					}}},
					{
						iconCls: 'icon-remove',
						text:'删除',
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
									$.post("Rebate-deleteByIds.action",{ids:ids},function(result){
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
												msg:'删除失败',
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
					},'-',
					{
						iconCls: 'icon-more',
						text:'货物明细',
						handler: function(){
						var rows =$("#dg").datagrid("getSelections");
						if(rows.length !=1){
							$.messager.show({
								title:'错误提示',
								msg:'一次只能查看一条记录',
								timeout:2000,
								showType:'slide'
							});
						}else{
						
						//1.完成弹出货物明细页面
						$("#win").window({
							title:'货物明细',
							width:'500',
							height:'450',
							content:'<iframe title="" src="Rebate_details.jsp" frameborder="0" width="100%" height="100%"/>'
						});
														
						}	
						}
					}],
				
			    columns:[[ 
												      
					      
					        {field:'goods',title:'商品名称'},
					        {field:'hsCode',title:'商品编码',align:'right'} ,
					        {field:'quantity',title:'件数',align:'right'},
					        {field:'netWeight',title:'净重',align:'right'},
					        {field:'price',title:'单价',align:'right'},
					        {field:'amount',title:'金额',align:'right'},			        
					        {field:'material',title:'材质',align:'right'},					       
					        {field:'remarks',title:'备注',align:'right'}
				]]    
			});
			console.debug("rid:"+rows[0].id);
		})
	 </script>	

  </head>
  
  <body>
	<div id="container">
	
	<div id="middle">
		
		<div id="rside">
			<div id="rsidetop">
				<table id="gtab" >						
				</table>
			</div>
			
		</div>
		
		</div>
		<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true" ></div> 
	</div>
	</div>
</body>
</html>
