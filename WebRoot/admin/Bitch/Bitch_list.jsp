<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@include file="../head.jspf" %>
	 <link rel="stylesheet" href="<%=basePath%>css/Bitch/Bitch_list.css" type="text/css" />
	<script type="text/javascript">
	$(function(){
		$('#btnSearch').click(function(){
			$('#dg').datagrid('load',{
				lineId: $('#lineId').combobox('getValue'),			
				stdate: $('#stdate').datebox('getValue'), 
				enddate: $('#enddate').datebox('getValue')	
			});
		});	
		
		$('#dg').datagrid({    
				//请求的url地址
			    url:'Bitch/Bitch-listByLineId.action', 
			    queryParams: {
					lineId: 'lineId',
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
				pageSize: 50,//每页显示的记录条数，默认为10  
	       		pageList: [50,100,200,300],//可以设置每页记录条数的列表  
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
							content:'<iframe src="Bitch_saveInput.jsp" frameborder="0" width="100%" height="100%"> </iframe>'
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
								width:'80%',
								height:'90%',
								content:'<iframe title="" src="Bitch_updateInput.jsp" frameborder="0" width="100%" height="100%"/>'
							});
					}}},
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
									$.post("Bitch/Bitch-deleteByIds.action",{ids:ids},function(result){
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
												msg:'删除失败,必须先删除此批次下的运单明细！',
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
												      
					        {field:'sdDate',title:'发出日期', 
						        formatter:function(value,row,index){  
		                        	var unixTimestamp = new Date(value);  
		                        	return unixTimestamp.toLocaleDateString();  
		                        } , align:'center',width:80},
					   		        
					        {field:'bitch',title:'运输批次',align:'left',width:150},
					        {field:'lineId',title:'线路',align:'left',width:100},
					        {field:'info',title:'批次信息',align:'left',width:120},
					        {field:'fee',title:'支出费用',align:'right',width:80},
					        {field:'changeRate',title:'汇率',align:'right',width:80},					        
					        {field:'arrDate',title:'到达日期', 
						        formatter:function(value,row,index){ 
						        if(value==null){
						        	return "-";
						        }else{ 
		                    	var unixTimestamp = new Date(value);  
		                    	return unixTimestamp.toLocaleDateString();
						        }  
		                    } , align:'right',width:80},		                   
					        {field:'remarks',title:'备注',align:'right',width:100}
				]]    
			});	
		//线路选择框
		$("#lineId").combobox({
			url:'<%=basePath%>admin/Line/Line-listAll.action',
			editable:true,
			valueField:'lineId',
			textField:'lineId',
			panelHeight:300,
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
			<div id="label">线路</div>
			<div id="hang"><input type="text"  id="lineId"  class="easyui-textbox" name="lineId" style="width:100px" /></div>		
			
			<div id="label">起始日期</div>
			<div id="hang"><input type="text" id="stdate" class="easyui-datebox" name="stdate" style="width:120px"/></div>
			<div id="label">截止日期</div>
			<div id="hang"><input type="text" id="enddate" class="easyui-datebox" name="enddate" style="width:120px" /></div>
			</div>
			<div>
			<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			<a id="btnReset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reset'">重置</a>
			</div>
		</div>
	<hr/>
	<div id="tableDG"><table id="dg"></table></div>
	
	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true">   
	        
	</div>  
 </div>
</body>  


</html>
