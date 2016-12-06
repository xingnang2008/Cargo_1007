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
				custName:$('#custName').val(),	
				sender: $('#sender').val(),
				custId:$('#custId').val(),	
				payMethod:$('#payMethod').combobox('getValue'),
				stdate: $('#stdate').datebox('getValue'), 
				enddate: $('#enddate').datebox('getValue')	
			});
		});	
		$('#dg').datagrid({    
				//请求的url地址
			    url:'Receipt-findByCustAndMethod.action', 
			    queryParams: {
		    		custName:'custName',
		    		sender:'sender',
		    		custId: 'custId',
					payMethod:'payMethod',					
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
							
				onLoadSuccess:totalTarget,
				onClickRow:totalTarget,
				onSelectAll:totalTarget,
				onCheck:totalTarget,
				onUncheck:totalTarget,				
				//同列属性，冰结在最左侧totalTarget
				
				frozenColumns:[[
					{field:'z',checkbox:true},
					
					
				]],
				toolbar: [{
					iconCls: 'icon-add',
					text:'新建',
					handler: function(){
						$("#win").window({
							title:'新建记录',
							width:'100%',
							height:'100%',
							content:'<iframe src="Receipt_saveInput.jsp" frameborder="0" width="100%" height="100%"> </iframe>'
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
								content:'<iframe title="" src="Receipt_updateInput.jsp" frameborder="0" width="100%" height="100%"/>'
							});
					}}},{
						iconCls: 'icon-more',
						text:'导入收款信息',
						handler: function(){				
						
						//1.弹出导入数据页面
						$("#win").window({
							title:'收款信息',
							width:'100%',
							height:'100%',
							content:'<iframe title="导入数据" src="Receipt_Uploadfile.jsp" frameborder="0" width="100%" height="100%"/>'
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
									var waybills ="";
									for(var i=0;i<rows.length;i++){
										ids += rows[i].id+",";
										waybills += rows[i].waybill+",";
									}
									//拼接id的值
									ids = ids.substring(0,ids.lastIndexOf(","));
									waybills = waybills.substring(0,waybills.lastIndexOf(","));
									
									//发送ajax请求
									$.post("Receipt-deleteByIds.action",{ids:ids,waybills,waybills},function(result){
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
					}],
				
			    columns:[[ 		
							{field:'rdate',title:'收款日期',align:'center',
								 formatter:function(value,row,index){
									   	if(value!=null){
										   	var unixTimestamp = new Date(value);  
										   	return unixTimestamp.toLocaleDateString();
									   	}else{
												return "-";
									   	}
								}  ,width:100},	
								
								{field:'fee',title:'收款金额',align:'center',width:60},
								{field:'method',title:'付款方式',formatter:function(value,row,index){ 
						        	switch(value){
					        		case 0: return "到付";
					        		break;
					        		case 1: return "正付";
					        		break;
					        		
					        		
					        	}
	                        } ,styler: function(value,row,index){
	                    		if(value== 0){
	        						return 'font-weight: bold;color:#006633';
	                    		}else{
	                    			return 'font-weight: bold;color:#FF0000';
	                    		}
	        				},align:'center',width:100},
					        {field:'sender',title:'发货人',align:'center',width:100},
					        
					        {field:'custId',title:'客户号',align:'center',width:100},
					        {field:'custName',title:'收货人',align:'center',width:100},
					        
				        	
				]]    
			});
		
		
		
	});
	function totalTarget(){
    	//计算函数
    	 var picTotal = 0;//计算pic的总和
    	   
    	    
    	    var rows = $('#dg').datagrid('getSelections');//获取当前的数据行
    	    
    	   
    	    
    	    for (var i = 0; i < rows.length; i++) {
    	    	picTotal += rows[i]['fee'];
    	    	
    	    	
    	    }
    	    //新增  显示统计信息
    	   $("#fee").text(picTotal);
    	  
      }
	
	</script>

  </head>
  
  
<body>   
<div id="container">
	
		<div id="searchDiv">
			<div class="line">
				
				
				<div class="label">客户号</div>
				<div class="hang"><input type="text"  id="custId"  class="easyui-textbox" name="custId" style="width:100px" /></div>
				
			
				<div class="label">发货人</div>
				<div class="hang"><input type="text"  id="sender" class="easyui-textbox" name="sender"  style="width:100px"/></div>
				
				<div class="label">收货人</div>
				<div class="hang"><input type="text"  id="custName" class="easyui-textbox" name="raterName"  style="width:100px"/></div>
				<div class="label">付款方式</div>
				<div class="hang">
				<select name="payMethod" id="payMethod" class="easyui-combobox"  style="width:100px;panelHeight:100px;">   
						      <option value="">-</option> 
						     <option value="0">到付</option>  	
						    <option value="1">正付</option>   
						</select> 
				</div>
			
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
			<div class="label">金额合计:</div><div class="hang"><p id="fee"></p></div>	
		</div>
	</div>
	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true">	        
	</div>  
 </div> 
</body>  


</html>
