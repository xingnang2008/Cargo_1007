<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@include file="../head.jspf" %>
	
	<script type="text/javascript">
	$(function(){
		$('#dg').datagrid({    
				//请求的url地址
			    url:'Rebate/Rebate-listCustId.action', 
			    queryParams :{custId:''},
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
				onLoadSuccess: compute,
				//同列属性，冰结在最左侧
				pageSize: 10,//每页显示的记录条数，默认为10  
	       		pageList: [5,10,15,20,50,100,150,200],//可以设置每页记录条数的列表  
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
							content:'<iframe src="Rebate_saveInput.jsp" frameborder="0" width="100%" height="100%"> </iframe>'
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
								content:'<iframe title="" src="Rebate_updateInput.jsp" frameborder="0" width="100%" height="100%"/>'
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
						text:'导入数据',
						handler: function(){				
						
						//1.弹出导入数据页面
						$("#win").window({
							title:'货物明细',
							width:'500',
							height:'450',
							content:'<iframe title="导入数据" src="Rebate_Uploadfile.jsp" frameborder="0" width="100%" height="100%"/>'
						});
														
						}	
						
					},{
						iconCls: 'icon-more',
						text:'导出选中数据',
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
									
										var surl='<iframe src="Rebate_downloadExcel.jsp?ids='+ids+'" frameborder="0" width="100%" height="100%"/>';
										console.info(surl);
										//1.弹出导出数据页面
										$("#win").window({
											title:'货物明细',
											width:'500',
											height:'450',
											content:surl
										});																	
									}
							}
					},{	
						text:"<input type='text' id='ss' />"
				}],
				
			    columns:[[ 
												      
					        {field:'sddate',title:'接收日期', 
						        formatter:function(value,row,index){
						        	if(value!=""){
		                        	var unixTimestamp = new Date(value);  
		                        	return unixTimestamp.toLocaleDateString();
						        	}else{
										return "";
						        	}  
		                        } , align:'right'},
					        {field:'company',title:'公司名称'},
					        {field:'companyCode',title:'海关编码',align:'right'} ,
					        {field:'contract',title:'合同号',align:'right'},
					        {field:'tradeType',title:'贸易类型',align:'right'},
					        {field:'source',title:'货源地',align:'right'},					       		        
					        {field:'bitch',title:'运输批次',align:'right'},
					        {field:'custId',title:'客户号',align:'right'},
					        {field:'telphone',title:'联系电话',align:'right'},
					        {field:'packages',title:'包数',align:'right'},
					        {field:'grossWeight',title:'毛重',align:'right'},
					        {field:'inFee',title:'收款',align:'right'},
					        {field:'payMethod',title:'付款方式',
					        	formatter:function(value,row,index){ 
						        if(value==1){
						        	return "汇款";
						        }else if(value==0){ 		                    	
		                    		return "现金";
						        }else if(value==2){
									return "其他";
						        }    
		                    } ,

						        align:'right'},
					        {field:'infeeDate',title:'收款日期', 
						        formatter:function(value,row,index){ 
						        if(value==null){
						        	return "-";
						        }else{ 
		                    	var unixTimestamp = new Date(value);  
		                    	return unixTimestamp.toLocaleDateString();
						        }  
		                    } , align:'right'},
		                    {field:'outFee',title:'外付款',align:'right'},
					        {field:'outfeeDate',title:'外付日期', 
		                    	 formatter:function(value,row,index){ 
						        if(value==null){
						        	return "-";
						        }else{ 
		                    	var unixTimestamp = new Date(value);  
		                    	return unixTimestamp.toLocaleDateString();
						        }  
		                    } , align:'right'},
					        {field:'yldate',title:'预录日期', 
		                    	 formatter:function(value,row,index){ 
						        if(value==null){
						        	return "-";
						        }else{ 
		                    	var unixTimestamp = new Date(value);  
		                    	return unixTimestamp.toLocaleDateString();
						        }   
		                    } , align:'right'},
					        {field:'yuludan',title:'预录单号',align:'right'},
					        {field:'bcdate',title:'回单日期', 
					        	 formatter:function(value,row,index){ 
						        if(value==null){
						        	return "-";
						        }else{ 
		                    	var unixTimestamp = new Date(value);  
		                    	return unixTimestamp.toLocaleDateString();
						        }    
		                    } , align:'right'},
					        {field:'remarks',title:'备注',align:'right'}
				]]    
			});
		//设置查询控件  
		$('#ss').searchbox({
			searcher:function(value,name){
				$('#dg').datagrid('load',{custId:value});
			},
			prompt:'客户'

		});
		
		
	});
	function compute() {//计算函数
        var rows = $('#dg').datagrid('getRows')//获取当前的数据行
        
        var ptotal = 0//计算listprice的总和
        ,utotal=0;//统计unitcost的总和
        for (var i = 0; i < rows.length; i++) {
            ptotal += rows[i]['packages'];
            utotal += rows[i]['inFee'];
            
        }
        //新增一行显示统计信息
        $('#dg').datagrid('appendRow', {id: '统计',sddate:'', packages: ptotal, inFee: utotal});
      
    }
	
	</script>

  </head>
  
  
<body>   

<table id="dg"></table>

<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true">   
        
</div>  
 
</body>  


</html>
