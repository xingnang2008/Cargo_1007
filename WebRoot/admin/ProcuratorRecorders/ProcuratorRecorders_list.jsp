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
				custId:$('#procurator').val(),				
				stdate: $('#stdate').datebox('getValue'), 
				enddate: $('#enddate').datebox('getValue')	
			});
		});	
		$('#btnReset').click(function(){
			
			$('#sender').textbox("clear"),
			$('#procurator').textbox("clear"),				
			$('#stdate').datebox('setValue',""), 
			$('#enddate').datebox('setValue',"")	
		
		});	


		
		$('#dg').datagrid({    
				//请求的url地址
			    url:'ProcuratorRecorders/ProcuratorRecorders-find.action', 
			    queryParams :{
		    		sender:'sender',
		    		custId:'procurator',
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
				
				frozenColumns:[[
					{field:'z',checkbox:true},
					{field:'id',title:'编号',width:50}
					
				]],
				toolbar: [{
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
					        {field:'procurator',title:'代理人',align:'center',width:80},
					        {field:'waybill',title:'票号',align:'center',width:100},					       
					        {field:'pics',title:'包数',align:'center',width:60},					       
					        {field:'fee',title:'代理费',align:'right',width:80},					        
					        {field:'rdate',title:'结款日期', 
							    formatter:function(value,row,index){
							    	if(value!=""){
							    	var unixTimestamp = new Date(value);  
							    	return unixTimestamp.toLocaleDateString();
							    	}else{
										return "";
							    	}  
							    } , align:'right',width:80}
				]]    
			});
		$("#rDate").datebox(); //收款日期
		
		
	});
	function totalTarget(){
    	//计算函数
    	 var aTotal = 0//计算总和
    	    pics =0;
    	    
    	    var rows = $('#dg').datagrid('getSelections')//获取当前的数据行
    	    
    	   
    	    
    	    for (var i = 0; i < rows.length; i++) {
    	    	pics += rows[i]['pics'];
    	    	aTotal += rows[i]['fee'];
    	    	
    	    	   	    	
    	    }
    	    //新增  显示统计信息
    	   $("#pic").text(pics);
    	   $("#Total").text(aTotal);
    	  
    	  
      }
	
	</script>

  </head>
  
  
<body>  
<div id="container">
	<div id="searchDiv">
			
			<div class="line">
				
				<div class="label">发货人</div>
				<div class="hang"><input type="text"  id="sender" class="easyui-textbox" name="procurator"  style="width:100px"/></div>
				<div class="label">代理人</div>
				<div class="hang"><input type="text"  id="procurator" class="easyui-textbox" name="procurator"  style="width:100px"/></div>
				
				<div class="label">起始日期</div>
				<div class="hang"><input type="text" id="stdate" class="easyui-datebox" name="stdate" style="width:120px"/></div>
				<div class="label">截止日期</div>
				<div class="hang"><input type="text" id="enddate" class="easyui-datebox" name="enddate" style="width:120px" /></div>
				
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
