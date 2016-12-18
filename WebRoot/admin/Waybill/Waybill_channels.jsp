<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@include file="../head.jspf" %>
	<link rel="stylesheet" href="<%=basePath%>css/Waybill/Waybill_channels.css" type="text/css" />	
	<script type="text/javascript">
	$(function(){
	$('#btnSearch').click(function(){
		$("#ff").form("enableValidation");
		//如果验证成功，则提交数据
		if($("#ff").form("validate")){
		
			$('#source').datagrid('load',{
				bitch: $('#bitch').combobox('getValue'),
				waybill: $('#waybill').val(),
				custId: $('#custId').val(),
				sender: $('#sender').val(),
				statusId:'2'	
			});
			$('#target').datagrid('load',{
				bitch: '',
				waybill: $('#waybill').val(),
				custId: $('#custId').val(),
				sender: $('#sender').val(),
				statusId:'2'	
			});
		}
	});	
	$('#btnAdd').click(function(){
		$("#ff").form("enableValidation");
		//如果验证成功，则提交数据
		if($("#ff").form("validate")){
			var rows =$("#source").datagrid("getSelections");
			if(rows.length ==0){
				$.messager.show({
					title:'选择行',
					msg:'至少要选中一行，进行操作。',
					timeout:2000,
					showType:'slide'
				});

			}else{
				var lineid = $('#lineId').combobox('getValue');				
				var bth =$('#bitch').combobox('getValue');
				
				$('#lineId').combobox('setValue','');
				$('#bitch').combobox('setValue','');
				
				var ids ="";
				for(var i=0;i<rows.length;i++){
					ids += rows[i].id+",";
				}
				
				//拼接id的值
				ids = ids.substring(0,ids.lastIndexOf(","));
				$('#ff').form('submit', {    
				    url:'Waybill/Waybill-updateBitch.action?ids='+ids,
				    success:function(){    
						//重载dg
						$('#lineId').combobox('setValue',lineid),
						$('#bitch').combobox('setValue',bth),
				    
						//重载dg
						$("#source").datagrid("reload",{
							bitch: $('#bitch').combobox('getValue'),
							waybill: $('#waybill').val(),
							custId: $('#custId').val(),
							sender: $('#sender').val(),
							statusId:'2'	
						});	
						$("#target").datagrid("reload");			           
				    }    
			})
			}
		}
	});

	$('#btnBack').click(function(){
		$("#ff").form("enableValidation");
		//如果验证成功，则提交数据
		if($("#ff").form("validate")){
			var rows =$("#target").datagrid("getSelections");
			if(rows.length ==0){
				$.messager.show({
					title:'选择行',
					msg:'至少要选中一行，进行操作。',
					timeout:2000,
					showType:'slide'
				});

			}else{
				var ids ="";
				for(var i=0;i<rows.length;i++){
					ids += rows[i].id+",";
				}				
				//拼接id的值
				ids = ids.substring(0,ids.lastIndexOf(","));
				$('#ff').form('submit', {    
				    url:'Waybill/Waybill-updateBitch.action?ids='+ids,
				    success:function(){    
						//重载dg
						$("#source").datagrid("reload");	
						$("#target").datagrid("reload");			           
				    }    
			})
			}
		}
	});		
	$('#source').datagrid({    
			//请求的url地址
		    url:'Waybill-findByStatusId.action',
		    queryParams: {
					bitch: 'bitch',
					waybill:'waybill',
					sender:'sender',
					statusId:'2'				
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
			
			
			onLoadSuccess:totalSelect,
			
		
       		//同列属性，冰结在最左侧
			frozenColumns:[[
				{field:'z',checkbox:true},
				{field:'id',title:'编号',width:50}
				
			]],			
		    columns:[[ 
											      
					{field:'sddate',title:'发货日期', 
					    formatter:function(value,row,index){  
					  	var unixTimestamp = new Date(value);  
					  	return unixTimestamp.toLocaleDateString();  
					  } , align:'right',width:80},
					{field:'custId',title:'客户号',align:'center',width:80},
					{field:'sender',title:'发货人',align:'right',width:80},
					{field:'waybill',title:'运单号',align:'center',width:120},
					{field:'transType',title:'运输类型',align:'center',width:100},
					
					{field:'pics',title:'包数',align:'right',width:40},
			        {field:'weight',title:'重量',align:'right',width:60},
			        {field:'volumu',title:'体积',align:'right',width:60},
			        {field:'goods',title:'品名',align:'center',width:100},
			        {field:'quantity',title:'数量',align:'center',width:60},
			        
			        {field:'lineId',title:'运输线路',align:'center',width:80},
			        {field:'destName',title:'目的地',align:'center',width:80},
			        {field:'bitch',title:'批次',align:'center',width:80},		    
			        {field:'isRebate',title:'是否核销',
			        	 formatter:function(value,row,index){ 
			        	if(value=="0"){
							return "-";
			        	}else if(value=="1"){
			        		return "核销";
			        	}
			        },align:'right',width:60},
					{field:'rebateId',title:'核销编号',align:'right',width:60},					       
					 
					
					{field:'statusId',title:'状态号',
						 formatter:function(value,row,index){ 
						if(value=="1"){
							return "新建";
						}else if(value=="2"){
							return "已配仓";
						}else if(value=="3"){
							return "已装运";
						}else if(value=="6"){
							return "绿色通道";
						}
					} ,align:'center',width:60},					        
					{field:'remarks',title:'备注',align:'left',width:100}
			]]    
		},'json');
	//线路选择框
	$("#lineId").combobox({
		url:'<%=basePath%>admin/Line/Line-listAll.action',
		editable:true,
		valueField:'lineId',
		textField:'lineId',
		panelHeight:300,
		panelWidth:120,
		width:120,
		onSelect: function(rec){    
			$('#bitch').combobox("clear").combobox("reload",
			 		 			'<%=basePath%>admin/Bitch/Bitch-listByLine.action?lineId='+rec.lineId); 
	 			
    	}  
		
	});
	$("#bitch").combobox({
		
		valueField:'bitch',
		textField:'bitch',
		panelHeight:300,
		panelWidth:120,
		width:120
	}); 
	$('#target').datagrid({    
		//请求的url地址
	    url:'Waybill-findByStatusId.action',
	    queryParams: {
				bitch: '',
				waybill:'waybill',
				sender:'sender',
				statusId:'2'					
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
		
		onLoadSuccess:totalVal,
		
		
   		//同列属性，冰结在最左侧
		frozenColumns:[[
			{field:'z',checkbox:true},
			{field:'id',title:'编号',width:50}
			
		]],		
	    columns:[[ 
										      
			        {field:'sddate',title:'发货日期', 
				        formatter:function(value,row,index){  
                      	var unixTimestamp = new Date(value);  
                      	return unixTimestamp.toLocaleDateString();  
                      } , align:'right',width:80},
                  {field:'custId',title:'客户号',align:'center',width:80},
                  {field:'sender',title:'发货人',align:'right',width:80},
                  {field:'waybill',title:'运单号',align:'center',width:120},
                  {field:'transType',title:'运输类型',align:'center',width:100},
                  
                  {field:'pics',title:'包数',align:'right',width:40},
			        {field:'weight',title:'重量',align:'right',width:60},
			        {field:'volumu',title:'体积',align:'right',width:60},
			        {field:'goods',title:'品名',align:'center',width:100},
			        {field:'quantity',title:'数量',align:'center',width:60},
			        
			        {field:'lineId',title:'运输线路',align:'center',width:80},
			        {field:'destName',title:'目的地',align:'center',width:80},
			        {field:'bitch',title:'批次',align:'center',width:80},		    
			        {field:'isRebate',title:'是否核销',
			        	 formatter:function(value,row,index){ 
			        	if(value=="0"){
							return "-";
			        	}else if(value=="1"){
			        		return "核销";
			        	}
			        },align:'right',width:60},
			        {field:'rebateId',title:'核销编号',align:'right',width:60},					       
			         
			        
			        {field:'statusId',title:'状态号',
			        	 formatter:function(value,row,index){ 
			        	if(value=="1"){
							return "新建";
			        	}else if(value=="2"){
			        		return "已配仓";
			        	}else if(value=="3"){
			        		return "已装运";
			        	}else if(value=="6"){
			        		return "绿色通道";
			        	}
                  } ,align:'center',width:60},					        
			        {field:'remarks',title:'备注',align:'left',width:100}
		]]    
	},'json'); 
	
	
});
	
      function totalVal(){
    	//计算函数
    	    var rows = $('#target').datagrid('getRows')//获取当前的数据行
    	    
    	    var picTotal = 0//计算pic的总和
    	    ,weightTotal=0//统计weight的总和
    	    ,volTotal=0;//统计vol的总和
    	    
    	    for (var i = 0; i < rows.length; i++) {
    	    	picTotal += rows[i]['pics'];
    	    	weightTotal += rows[i]['weight'];
    	    	volTotal+= rows[i]['volumu'];
    	    }
    	    //新增  显示统计信息
    	   $("#pic").text(picTotal);
    	   $("#weight").text(weightTotal.toFixed(1));
    	   $("#volum").text(volTotal.toFixed(2));
    	  
      }
      function totalSelect(){
      	//计算函数
      	    var rows = $('#source').datagrid('getRows')//获取当前的数据行
      	    
      	    var picTotal = 0//计算pic的总和
      	    ,weightTotal=0//统计weight的总和
      	    ,volTotal=0;//统计vol的总和
      	    
      	    for (var i = 0; i < rows.length; i++) {
      	    	picTotal += rows[i]['pics'];
      	    	weightTotal += rows[i]['weight'];
      	    	volTotal+= rows[i]['volumu'];
      	    }
      	    //新增一行显示统计信息
      	   $("#pic1").text(picTotal);
      	   $("#weight1").text(weightTotal.toFixed(1));
      	   $("#volum1").text(volTotal.toFixed(2));
      	  
        }
	


</script>

</head>


  
  <body>
  <div id="container">
    <div id="searchDiv">
			<div><form id="ff" method="post">
			<div class="label">线路</div>
			<div class="hang"><input type="text" id="lineId"  name="lineId" style="width:120px" /></div>
				
			<div class="label">批次</div>
			<div class="hang"><input type="text" id="bitch" class="easyui-textbox" name="bitch" style="width:120px" /></div>
			
			<div class="label">客户号</div>
			<div class="hang"><input type="text"  id="custId"  class="easyui-textbox" name="custId" style="width:100px" /></div>
			
			
			<div class="label">运单号</div>
			<div class="hang"><input type="text" id="waybill" class="easyui-textbox" name="waybill" style="width:120px" /></div>
			
			
			<div class="label">发货人</div>
			<div class="hang"><input type="text"  id="sender" class="easyui-textbox" name="sender"  style="width:100px"/></div>
			
			
			</form>
			</div>
			
			<div class="hang">
			<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</div>
			
		</div>
	
	<div id="main">
	<div id="tableSource"><table id="source"></table></div>
	<div id="middleDiv">
		<div id="bottons">
			<a id="btnAdd" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-none'">调到 >绿色通道    </a>
			<a id="btnBack" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-none'">调到  >所选批次    </a>
		</div>
		<div id="botto">
			<div id="bt1">
				<div class="blabel">件数</div><div class="bhang">
				<p id="pic1"></p></div>
				<div class="blabel">重量</div><div class="bhang">
				<p id="weight1"></p>
				</div>
				<div class="blabel">体积</div><div class="bhang">
				<p id="volum1"></p>
				</div>
			</div>
			<div id="bt2">
				<div class="blabel">件数</div><div class="bhang">
				<p id="pic"></p></div>
				<div class="blabel">重量</div><div class="bhang">
				<p id="weight"></p>
				</div>
				<div class="blabel">体积</div><div class="bhang">
				<p id="volum"></p>
				</div>
			</div>
		</div>
	</div>
	
	<div id="tableTarget"><table id="target"></table></div>
	
	
	</div>
	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true">  </div>
	</div>
	
  </body>
</html>
