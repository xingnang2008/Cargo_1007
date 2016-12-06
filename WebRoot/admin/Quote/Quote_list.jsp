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
				product: $('#product').val(), 
				hsCodeRu: $('#hsCodeRu').val()			
			});
		});	
		$('#btnReset').click(function(){
			
		
			$('#product').textbox("clear"),
			$('#hsCodeRu').textbox("clear")
			
		});	
		$('#dg').datagrid({    
				//请求的url地址
			    url:'Quote-find.action', 
			    queryParams: {
					product: 'product', 
					hsCodeRu: 'hsCodeRu'									
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
				//显示分页条		pagination:true,		
				
				//排序
				sortName:'id',
				sortOrder:'desc',
				remoteSort:false,
				//同列属性，冰结在最左侧
				//pageSize: 50,//每页显示的记录条数，默认为10         		pageList: [50,100,200,300],//可以设置每页记录条数的列表  
				rowStyler: function(index,row){
					if (row.isUse ==0){
						return 'background-color:#CFCFCF;color:#CD3700;';
					}
					
				},
				frozenColumns:[[
				
					{field:'z',checkbox:true},
					{field:'id',title:'编号',width:50}
					
				]],
				
				
			    columns:[[ 
							
					        {field:'product',title:'наименование品名',align:'center',width:450},
					        {field:'hsCodeRu',title:'Код товара海关编码',align:'center',width:200},					       
					        {field:'metarial',title:'материал材质',align:'center',width:200},
					        {field:'price',title:' $/кг报价',align:'center',width:120},		
					        {field:'transType',title:'运输方式',align:'center',width:100},
					       		        
					        {field:'isUse',title:'是否可收', 
						        formatter:function(value,row,index){ 
					        	switch(value){
				        		case 0: return "不收";
				        		break;
				        		case 1: return "可收";
				        		break;
				        		case 2: return "待定";
				        		break;		
				        						        		
				        	}
		                    } , align:'right',width:80},		                   
					        {field:'remarks',title:'дополнение备注',align:'right',width:150}
				]]    
			});	
		//分类选择框
		$("#categroy").combobox({
			url:'<%=basePath%>admin/Category/Category-listAll.action',
			editable:true,
			valueField:'category',
			textField:'category',
			panelHeight:'auto',
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
			
			<div class="label">品名</div>
			<div class="hang"><input type="text" id="product" class="easyui-textbox" name="product" style="width:120px"/></div>
			<div class="label">HS编码</div>
			<div class="hang"><input type="text" id=hsCodeRu class="easyui-textbox" name="hsCodeRu" style="width:120px" /></div>
			
			</div>
			<div>
			<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			<a id="btnReset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">重置</a>
			</div>
		</div>
	<hr/>
	<div id="tableDG"><table id="dg"></table></div>
	
	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true">   
	        
	</div>  
 </div>
</body>  


</html>
