<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@include file="../head.jspf" %>
	
	<script type="text/javascript">
	$(function(){
		$('#dg').datagrid({    
				//请求的url地址
			    url:'User-find.action', 			   
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
				//排序
				sortName:'id',
				sortOrder:'desc',
				remoteSort:false,
				//同列属性，冰结在最左侧
				pageSize: 10,//每页显示的记录条数，默认为10  
	       		pageList: [10,15,20],//可以设置每页记录条数的列表  
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
							content:'<iframe src="User_saveInput.jsp" frameborder="0" width="100%" height="100%"> </iframe>'
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
								width:'1025',
								height:'645',
								content:'<iframe title="" src="User_updateInput.jsp" frameborder="0" width="100%" height="100%"/>'
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
									$.post("User-deleteByIds.action",{ids:ids},function(result){
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
					},
					'-',
					{
						iconCls: 'icon-reload',
						text:'刷新',
						handler: function(){$("#dg").datagrid("reload");}
					},{	
						text:"<input type='text' id='roles' />"
				},{
					iconCls: 'icon-redo',
					text:'更新权限',
					handler: function(){
					var rows =$("#dg").datagrid("getSelections");
					
					var roles = $('#roles').combobox('getValue');
					
					if(roles ==""){
						$.messager.show({
							title:'选择列的权限',
							msg:'请选择 “审核”或“取消审核”赔偿',
							timeout:2000,
							showType:'slide'
						});
					}else{
					if(rows.length ==0){
						$.messager.show({
							title:'选择行',
							msg:'至少要选中一个用户，进行更新操作。',
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
								$.post("User-updateRole.action",{ids:ids,rid:roles},function(result){
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
				},'-',{	
					text:"<input id='psw'  class='easyui-textbox' type='password' />"
				},{
					iconCls: 'icon-redo',
					text:'更改密码',
					handler: function(){
					var rows =$("#dg").datagrid("getSelections");					
					var pswd = $('#psw').val();
					if(pswd.length < 5){
						$.messager.show({
							title:'密码位数不够',
							msg:'密码位数不能少于5位，请重新设定！',
							timeout:2000,
							showType:'slide'
						});
					}else{
						if(rows.length ==0){
							$.messager.show({
								title:'选择行',
								msg:'至少要选中一个用户，进行更新操作。',
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
									$.post("User-refreshUser.action",{ids:ids,passwd:pswd},function(result){
										if(result =="true"){
											
											//取消选中所有行
											$("#dg").datagrid("uncheckAll");
											//重新刷新页面
											$("#dg").datagrid("reload");
											$("#psw").textbox("setValue", "");
										}						
									
									},"text");
	
						}
	 				}
				}
				}],
				
			    columns:[[ 		
							{field:'username',title:'用户名',align:'center',width:100}, 	      
					        {field:'name',title:'姓名',width:100},
					        {field:'roles',title:'权限',
						        formatter:function(value,row,index){
						        	var temp='';
						        	if(Array.isArray(value)){
										
										for(i=0;i<value.length;i++){
											temp += value[i].remark+',';
										}
						        	}
						        	temp = temp.substring(0,temp.lastIndexOf(","));
						        	return temp;
		                        },width:100},
		                    {field:'enabled',title:'是否可用',
		                        	formatter:function(value,row,index){
		                        	switch(value){
						        		case false: return "不可用";
						        		break;
						        		case true: return "可用";
						        		break;
						        	}
						        	
		                        },width:80},
					        {field:'password',title:'密码',width:200}
					        
					        
				]]    
			});

		$("#roles").combobox({
			  url:"<%=basePath%>security/Role/Role-listAll.action",
			  textField:"remark",
			  valueField:"id",
			  multiple:false,
			  editable:false,
			  panelWidth:100,
			  width:200
			  
			 });
		$('#psw').textbox({    
			width: 100
		         
		})

				
		
	});
	
	
	</script>

  </head>
  
  
<body>   

<table id="dg"></table>
<table id="dgRole"></table>
<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true">   
        
</div>  
 
</body>  


</html>
