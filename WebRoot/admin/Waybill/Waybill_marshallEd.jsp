<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@include file="../head.jspf" %>
	<link rel="stylesheet" href="<%=basePath%>css/Waybill/Waybill_marshallEd.css" type="text/css" />	
	<script type="text/javascript">
	$(function(){
	$('#btnSearch').click(function(){
		$('#source').datagrid('load',{
			bitch: $('#bitch').combobox('getValue'),
			waybill: $('#waybill').val(),
			sender: $('#sender').val(),
			raterName:$('#rater').val(),
			
			statusId:'2'	
		});
		$('#target').datagrid('load',{
			bitch: $('#bitch').combobox('getValue'),
			waybill: $('#waybill').val(),
			sender: $('#sender').val(),
			raterName:$('#rater').val(),
			
			statusId:'3'	
		});
	});	
	
	$('#source').datagrid({    
			//请求的url地址
		    url:'Waybill-findByStatusId.action',
		    queryParams: {
				bitch:'bitch',
				waybill:'waybill',
				custId:'custId',
				sender:'sender',
				raterName:'rater',
				
				statusId:'2'				
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
			//排序
			sortName:'id',
			sortOrder:'desc',
			remoteSort:false,
			onLoadSuccess:totalSource,
			onClickRow:totalSource,
			onSelectAll:totalSource,
			onCheck:totalSource,
			onUncheck:totalSource,
		
			pageSize: 300,//每页显示的记录条数，默认为10  
			pageList: [300,500],//可以设置每页记录条数的列表  
       		//同列属性，冰结在最左侧
			frozenColumns:[[
				{field:'z',checkbox:true},
				{field:'id',title:'编号',width:50}
				
			]],
			toolbar: [
			 			{
							iconCls: 'icon-redo',
							text:'装机',
							handler: function(){
							var rows =$("#source").datagrid("getSelections");
							var cangid = $("#cangdanId").val();
							console.info(cangid);
							if(cangid ==""){
								$.messager.show({
									title:'选择输入仓单号',
									msg:'进行配仓操作，必须要填写配仓单号',
									timeout:2000,
									showType:'slide'
								});
							}else{
							if(rows.length ==0){
								$.messager.show({
									title:'选择行',
									msg:'至少要选中一行，进行配仓操作。',
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
										$.post("Waybill-updateTrackOnSendByIds.action",{ids:ids,sid:'3',cangid:cangid},function(result){
											if(result =="true"){

												//取消选中所有行
												$("#source").datagrid("uncheckAll");
												//重新刷新页面
												$("#source").datagrid("reload");
												$("#target").datagrid("reload");
											}						
										
										},"text");
									
									

							}

							}
			 			}
						},{	
							text:"装机单号：<input type='text' class='easyui-textbox' id='cangdanId' />"
						}],
		    columns:[[ 
											      
		              {field:'sddate',title:'发货日期', 
					        formatter:function(value,row,index){  
	                        	var unixTimestamp = new Date(value);  
	                        	return unixTimestamp.toLocaleDateString();  
	                        } , align:'right'},
	                    {field:'custId',title:'唛头',align:'center'},
	                    {field:'sender',title:'发货人',align:'right'},
	                    {field:'waybill',title:'运单号',align:'center'},
	                    {field:'transType',title:'运输类型',align:'center'},
	                    
	                    {field:'joinBill',title:'合包号',
		                    formatter:function(value,row,index){  
	                    	if(value==null) 
	                    	return "-";  
	                    } ,align:'center'},
	                    {field:'pics',title:'包数',align:'right'},
				        {field:'weight',title:'重量',align:'right'},
				        {field:'volumu',title:'体积',align:'right'},
				        {field:'goods',title:'品名',align:'center'},
				        {field:'quantity',title:'数量',align:'center'},
				        
				        {field:'lineId',title:'运输线路',align:'center'},
				        {field:'destName',title:'目的地',align:'center'},
				        {field:'bitch',title:'批次'},		    
				        {field:'isRebate',title:'是否核销',align:'right'},
				        {field:'rebateId',title:'核销编号',align:'right'},					       
				         
				        
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
	                    } ,align:'center'},					        
				        {field:'remarks',title:'备注',align:'left'}
			]]    
		},'json');
	//线路选择框
	$("#lineId").combobox({
		url:'<%=basePath%>admin/Line/Line-listAll.action',
		editable:true,
		valueField:'lineId',
		textField:'lineId',
		panelHeight:200,
		panelWidth:120,
		width:120,
		onSelect: function(rec){    
			$('#bitch').combobox("clear").combobox("reload",
			 		 			'<%=basePath%>admin/Bitch/Bitch-listByLine.action?lineId='+rec.lineId); 
	 			
    	}  
		
	});
	//批次选择框
	$("#bitch").combobox({
			valueField:'bitch',
			textField:'bitch',
			panelHeight:200,
			panelWidth:120,
			width:120
		}); 
	$('#target').datagrid({    
		//请求的url地址
	    url:'Waybill-findByStatusId.action',
	    queryParams: {
			bitch: 'bitch',
			custId: 'custId',
			sender:'sender',
			raterName:'rater',
			orderNo:'orderNo',
			statusId:'3'					
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
		//排序
		sortName:'id',
		sortOrder:'desc',
		remoteSort:false,
		onLoadSuccess:totalTarget,
		onClickRow:totalTarget,
		onSelectAll:totalTarget,
		onCheck:totalTarget,
		onUncheck:totalTarget,
		
		pageSize: 300,//每页显示的记录条数，默认为10  
		pageList: [300,500],//可以设置每页记录条数的列表  
   		//同列属性，冰结在最左侧
		frozenColumns:[[
			{field:'z',checkbox:true},
			{field:'id',title:'编号',width:50}
			
		]],toolbar: [
			 			{
							iconCls: 'icon-undo',
							text:'调回',
							handler: function(){
							var rows =$("#target").datagrid("getSelections");
							if(rows.length ==0){
								$.messager.show({
									title:'选择行',
									msg:'至少要选中一行，进行配仓操作。',
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
										$.post("Waybill-updateTrackOnSendByIds.action",{ids:ids,sid:'2',cangid:''},function(result){
											if(result =="true"){

												
												//取消选中所有行
												$("#source").datagrid("uncheckAll");
												//重新刷新页面
												$("#source").datagrid("reload");
												$("#target").datagrid("reload");
												
											}						
										
										},"text");
									
									

							}

							}
						}],		
	    columns:[[ 
	              {field:'sddate',title:'发货日期', 
				        formatter:function(value,row,index){  
                      	var unixTimestamp = new Date(value);  
                      	return unixTimestamp.toLocaleDateString();  
                      } , align:'right'},
                  {field:'custId',title:'唛头',align:'center'},
                  {field:'sender',title:'发货人',align:'right'},
                  {field:'waybill',title:'运单号',align:'center'},
                  {field:'transType',title:'运输类型',align:'center'},
                  
                  {field:'joinBill',title:'合包号',
	                    formatter:function(value,row,index){  
                  	if(value==null) 
                  	return "-";  
                  } ,align:'center'},
                  {field:'pics',title:'包数',align:'right'},
			        {field:'weight',title:'重量',align:'right'},
			        {field:'volumu',title:'体积',align:'right'},
			        {field:'goods',title:'品名',align:'center'},
			        {field:'quantity',title:'数量',align:'center'},
			        
			        {field:'lineId',title:'运输线路',align:'center'},
			        {field:'destName',title:'目的地',align:'center'},
			        {field:'bitch',title:'批次'},		    
			        {field:'isRebate',title:'是否核销',align:'right'},
			        {field:'rebateId',title:'核销编号',align:'right'},					       
			         
			        
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
                  } ,align:'center'},					        
			        {field:'remarks',title:'备注',align:'left'}
		]]    
	},'json'); 
	
	
});
	
	function totalTarget(){
    	//计算函数
    	   
    	     $("#pic").text("0");
       	   $("#weight").text("0.0");
       	   $("#volum").text("0.00");
    	    var picTotal = 0//计算pic的总和
    	    ,weightTotal=0//统计weight的总和
    	    ,volTotal=0;//统计vol的总和
    	    var rows = $('#target').datagrid('getSelections')//获取当前的数据行
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
      function totalSource(){
      	//计算函数
      	    
      	    
      	   $("#pic1").text("0");
         	   $("#weight1").text("0.0");
         	   $("#volum1").text("0.00");
        	   var picTotal = 0,weightTotal=0,volTotal=0;//统计vol的总和
        	   var rows = $('#source').datagrid('getSelections')//获取当前的选 中的数据行
        	 
        	    for (var i = 0; i < rows.length; i++) {
        	    	picTotal += rows[i]['pics'];
        	    	weightTotal += rows[i]['weight'];
        	    	volTotal += rows[i]['volumu'];
        	    
        	   }
        	    //显示统计信息
        	   $("#pic1").text(picTotal);
        	   $("#weight1").text(weightTotal.toFixed(1));
        	   $("#volum1").text(volTotal.toFixed(2));
        	  
        }
	


</script>

</head>


  
  <body>
  <div class="container">
  <h2>起运管理</h2>
    <div id="searchDiv">
			<div>
			
			<div class="label">线路</div>
			<div class="hang"><input type="text" id="lineId"  name="lineId" style="width:120px" /></div>
			
			<div class="label">批次</div>
			<div class="hang"><input type="text" id="bitch" class="easyui-textbox" name="bitch" style="width:120px" /></div>
			
					
			<div class="label">运单号</div>
			<div class="hang"><input type="text" id="waybill" class="easyui-textbox" name="waybill" style="width:120px" /></div>
			
			
			<div class="label">发货人</div>
			<div class="hang"><input type="text"  id="sender" class="easyui-textbox" name="sender"  style="width:100px"/></div>
			
			<div class="label">经办人</div>
			<div class="hang"><input type="text"  id="rater" class="easyui-textbox" name="raterName"  style="width:100px"/></div>
			</div>
			<div>
			
			</div>
			<div class="hang">
			<a class="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</div>
			
		</div>
	
	
	<div id="tableSource"><table id="source"></table></div>
	
	<div id="tableTarget"><table id="target"></table></div>
	
	<div class="bottom">
		<div class="bt1">
			<div id="label">件数</div><div id="hang">
			<p id="pic1"></p></div>
			<div id="label">重量</div><div id="hang">
			<p id="weight1"></p>
			</div>
			<div id="label">体积</div><div id="hang">
			<p id="volum1"></p>
			</div>
		</div>
		<div class="bt2">
		<div id="label">件数</div><div id="hang">
			<p id="pic"></p></div>
			<div id="label">重量</div><div id="hang">
			<p id="weight"></p>
			</div>
			<div id="label">体积</div><div id="hang">
			<p id="volum"></p>
			</div>
		</div>
		</div>
	</div>
  </body>
</html>
