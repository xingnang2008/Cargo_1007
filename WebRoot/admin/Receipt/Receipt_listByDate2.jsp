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
				sender: $('#sender').val()
				
			});
		});	
		$('#btnReset').click(function(){
			$('#sender').textbox("clear")	
		
	});	
		$('#dg').datagrid({    
				//请求的url地址
			    url:'Receipt-listBySenderAndDate2.action',
			    queryParams: {					
					sender:'sender'	
				},
			    loadMsg:'数据加载中，请等待...',
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
				
				//pagination:true,
				//排序
				//sortName:'id',
				//sortOrder:'desc',
				//remoteSort:false,
				onLoadSuccess:totalTarget,
				onClickRow:totalTarget,
				onSelectAll:totalTarget,
				onCheck:totalTarget,
				onUncheck:totalTarget,

				
				//同列属性，冰结在最左侧
				//pageSize: 300,//每页显示的记录条数，默认为10  
	       		//pageList: [300,500],//可以设置每页记录条数的列表  
	       		
				       		
				frozenColumns:[[
					{field:'z',checkbox:true}					
				]],
				toolbar: [{
					iconCls: 'icon-edit',
					text:'导出欠款表',
					handler: function(){
						//发送ajax请求
						$.post("<%=basePath%>admin/Waybill/Waybill-downloadArrearagesReport.action",function(result){

									if(result =="true"){
										//取消选中所有行
										$("#dg").datagrid("uncheckAll");
										//重新刷新页面
										$("#dg").datagrid("reload");
									}						
								
								},"text");
					
	 			}
				}],
				
			    columns:[[  {field:'sender',title:'发货人',align:'center',width:80},
		                    {field:'custId',title:'客户号',align:'center',width:80},					    
					        {field:'rdate',title:'日期', 
					              formatter:function(value,row,index){
		            	  		if(value!=""){
	                        	var unixTimestamp = new Date(value);  
	                        	return unixTimestamp.toLocaleDateString();
					        	}else{
									return "";
					        	}  
	                        }, align:'center',width:100},		                   
					        {field:'fee',title:'收款金额',align:'center',width:120}
				]]    
			},'json');
		
		
	}); 
		 function totalTarget(){
		    	//计算函数
		    	 var totalFee = 0;//统计欠款的总和
		    	    
		    	    var rows = $('#dg').datagrid('getSelections')//获取当前的数据行
		    	    for (var i = 0; i < rows.length; i++) {
		    	    	totalFee += rows[i]['fee'];
		    	    }
		    	    //新增  显示统计信息
		    	 
		    	   $("#sum").text(totalFee.toFixed(0));
		    	
		      }
	
	
	</script>

  </head>
  
  
<body>   
<div id="container">
	
		<div id="searchDiv">
			<div class="line">
				
				
				<div class="label">发货人</div>
				<div class="hang"><input type="text"  id="sender" class="easyui-textbox" name="sender"  style="width:100px"/></div>
			
				<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				<a id="btnReset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">清空</a>
			</div>
		
		
		</div>
	
	
	<div id="tableDG"><table id="dg"></table></div>
	<div class="bottom">
		<div class="bt1">			
			<div class="label">收款金额</div><div class="hang"><p id="sum"></p></div>
						
			</div>
		</div>
		
	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,maximized:true,modal:true">   
        
	</div>  
 
</div>
</body>  


</html>
