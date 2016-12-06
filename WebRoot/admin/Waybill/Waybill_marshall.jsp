<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@include file="../head.jspf" %>
	<link rel="stylesheet" href="<%=basePath%>css/Waybill/Waybill_marshall.css" type="text/css" />	
	<script type="text/javascript">
	$(function(){
	$('#btnSearch').click(function(){
		$('#source').datagrid('load',{
			bitch: $('#bitch').combobox('getValue'),			
			sender: $('#sender').val(),
			sddate: $('#sddate').datebox('getValue'),
			statusId:'1'
		});
		$('#target').datagrid('load',{
			bitch: $('#bitch').combobox('getValue'),			
			sender: $('#sender').val(),
			sddate: $('#sddate').datebox('getValue'),
			statusId:'2'
		});
		$('#custTotal').datagrid('load',{
			bitch: $('#bitch').combobox('getValue'),
			statusId:$('#statusID').combobox('getValue')
		
		});
	});	
	//----------------按客户和发货日期分类 信息
	$('#source').datagrid({    
			//请求的url地址
		    url:'Waybill-findBySenderAndSddate.action',
		    queryParams: {
				bitch:'bitch',				
				sender:'sender',
				sddate:'sddate',
				statusId:'1'
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
			
			onLoadSuccess:totalSource,
			onSelectAll:totalSource,
			onCheck:totalSource,
			onUncheck:totalSource,
			onClickRow:totalSource,
			
			rowStyler: function(index,row){
				if (row.isRebate!=0){
					return 'color:#0000FF;';
				}
				
			},
					
       		//同列属性，冰结在最左侧
			frozenColumns:[[
				{field:'z',checkbox:true},
				{field:'id',title:'编号',width:50}
				
			]],
			toolbar: [
			 			{
							iconCls: 'icon-redo',
							text:'配仓',
							handler: function(){
							var rows =$("#source").datagrid("getSelections");
							var cangid = $("#cangdanId").val();
							console.info(cangid);
							if(cangid ==""){
								$.messager.show({
									title:'选择输入配仓单号',
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
										$.post("Waybill-updateTrackOnSendByIds.action",{ids:ids,sid:'2',cangid:cangid},function(result){
											if(result =="true"){

												//取消选中所有行
												$("#source").datagrid("uncheckAll");
												//重新刷新页面
												$("#source").datagrid("reload");
												$("#target").datagrid("reload");
												$('#custTotal').datagrid("reload");   
											}						
										
										},"text");
									
									

							}

							}
			 			}
						},{	
							text:"配仓单号：<input type='text' class='easyui-textbox' id='cangdanId' />"
						}
						],
		    columns:[[ 
											      
				     {field:'sddate',title:'发货日期', 
				        formatter:function(value,row,index){  
                      	var unixTimestamp = new Date(value);  
                      	return unixTimestamp.toLocaleDateString();  
                      } , align:'right',width:80},
                  {field:'custId',title:'客户号',align:'center',width:70},
                  {field:'sender',title:'发货人',align:'right',width:70},
                  {field:'waybill',title:'运单号',align:'center',width:120},
                  {field:'transType',title:'运输类型',align:'center',width:90},
                 
                  {field:'pics',title:'包数',align:'right',width:50},
			        {field:'weight',title:'重量',align:'right',width:50},
			        {field:'volumu',title:'体积',align:'right',width:50},
			        {field:'goods',title:'品名',align:'center',width:80},
			        {field:'quantity',title:'数量',align:'center',width:50},
			        
			        {field:'lineId',title:'运输线路',align:'center',width:80},
			        {field:'destName',title:'目的地',align:'center',width:80},
			        {field:'bitch',title:'批次',width:100},		    
			        {field:'isRebate',title:'是否核销',align:'right',width:50},
			        {field:'rebateId',title:'核销编号',align:'right',width:50},					       
			         
			        
			        {field:'statusId',title:'状态号',
			        	 formatter:function(value,row,index){ 
			        	if(value=="1"){
							return "新建";
			        	}else if(value=="2"){
			        		return "已配仓";
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
	//通过 状态改变 reload 客户分类统计   
	$('#statusID').combobox({
		onSelect: function(){
		$('#custTotal').datagrid('load',{
			bitch: $('#bitch').combobox('getValue'),
			statusId:$('#statusID').combobox('getValue')
		
		});
		}
	});

	
	//----------------客户分类统计表
	$('#custTotal').datagrid({    
		//请求的url地址
	    url:'Waybill-listByCustGroup.action',
	    queryParams: {
			bitch: 'bitch',
			statusId:'statusId'							
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
		
		onLoadSuccess:totalSender,
		onSelectAll:totalSender,
		onCheck:totalSender,
		onUncheck:totalSender,
		onClickRow:totalSender,
		
		onDblClickRow: function(rowIndex, rowData){
			
			$('#source').datagrid('load',{
				bitch: $('#bitch').combobox('getValue'),			
				sender: rowData.sender,
				sddate: rowData.sddate,
				statusId:1,
				
			});
		
		},
			
		 
   		//同列属性，冰结在最左侧
		frozenColumns:[[
			{field:'z',checkbox:true},
			{field:'sender',title:'发货人' , align:'center',width:70}
			
		]],		
	    columns:[[ 
					           
                    {field:'pics',title:'包数',align:'center',width:30},
			        {field:'weight',title:'重量',align:'right',width:40},
			        {field:'volumu',title:'体积',align:'right',width:40},
			        {field:'density',title:'密度',align:'right',width:40},
			        {field:'sddate',title:'发货日期',
			        	formatter:function(value,row,index){
			        	if(value!=""){
                    	var unixTimestamp = new Date(value);  
                    	return unixTimestamp.toLocaleDateString();
			        	}else{
							return "";
			        	}  
                    } ,  align:'right',width:70}
		]]    
	},'json'); 

	//已经配仓货
	$('#target').datagrid({    
		//请求的url地址	   
		    url:'Waybill-findBySenderAndSddate.action',
		    queryParams: {
				bitch:'bitch',				
				sender:'sender',
				sddate:'sddate',
				statusId:'2'
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
	
		
		onLoadSuccess:totalTarget,
		onClickRow:totalTarget,
		onSelectAll:totalTarget,
		onCheck:totalTarget,
		onUncheck:totalTarget,
		rowStyler: function(index,row){
				if (row.isRebate!=0){
					return 'color:#0000FF;';
				}
				
			},
		
	
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
										$.post("Waybill-updateTrackOnSendByIds.action",{ids:ids,sid:'1',cangid:''},function(result){
											if(result =="true"){
												
												//取消选中所有行
												$("#source").datagrid("uncheckAll");
												//重新刷新页面
												$("#source").datagrid("reload");
												$("#target").datagrid("reload");
												$('#custTotal').datagrid("reload"); 
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
                      } , align:'right',width:80},
                  {field:'custId',title:'客户号',align:'center',width:70},
                  {field:'sender',title:'发货人',align:'right',width:70},
                  {field:'waybill',title:'运单号',align:'center',width:120},
                  {field:'transType',title:'运输类型',align:'center',width:90},
                 
                  {field:'pics',title:'包数',align:'right',width:50},
			        {field:'weight',title:'重量',align:'right',width:50},
			        {field:'volumu',title:'体积',align:'right',width:50},
			        {field:'goods',title:'品名',align:'center',width:80},
			        {field:'quantity',title:'数量',align:'center',width:50},
			        
			        {field:'lineId',title:'运输线路',align:'center',width:80},
			        {field:'destName',title:'目的地',align:'center',width:80},
			        {field:'bitch',title:'批次',width:100},		    
			        {field:'isRebate',title:'是否核销',align:'right',width:50},
			        {field:'rebateId',title:'核销编号',align:'right',width:50},					       
			         
			        
			        {field:'statusId',title:'状态号',
			        	 formatter:function(value,row,index){ 
			        	if(value=="1"){
							return "新建";
			        	}else if(value=="2"){
			        		return "已配仓";
			        	}
                  } ,align:'center',width:60},					        
			        {field:'remarks',title:'备注',align:'left',width:100}
		]]    
	},'json'); 
	

	
});
		
      function totalSource(){
    	//计算函数
    	   
       	  $("#pic1").text("0");
      	   $("#weight1").text("0.0");
      	   $("#volum1").text("0.00");
      		$("#density1").text("0");
    	   
    	    var picTotal1 = 0,weightTotal1=0,volTotal1=0;//统计vol的总和
       	   var rows = $('#source').datagrid('getSelections')//获取当前的选 中的数据行
    	   
    	    for (var i = 0; i < rows.length; i++) {
        	    
        	    	picTotal1 += rows[i]['pics'];
	    	    	weightTotal1 += rows[i]['weight'];
	    	    	volTotal1 += rows[i]['volumu'];
    	    }
			
      	    
       	  //显示未装车信息
       	  var dy1 = volTotal1==0?"-":weightTotal1/volTotal1;
      	   $("#pic1").text(picTotal1);
      	   $("#weight1").text(weightTotal1.toFixed(1));
      	   $("#volum1").text(volTotal1.toFixed(2));
      	   
      		$("#density1").text(dy1.toFixed(0));
    	  
      }
      //更新已配仓的数据
      function totalTarget(){
      	//计算函数
      	   
      	     $("#pic").text("0");
         	   $("#weight").text("0.0");
         	   $("#volum").text("0.00");
         	$("#density").text("0");

         	
      	    var picTotal = 0//计算pic的总和
      	    ,weightTotal=0//统计weight的总和
      	    ,volTotal=0;//统计vol的总和
      	   
         	 var rows = $('#target').datagrid('getSelections')//获取当前的选 中的数据行
         	 
      	   
      	    for (var i = 0; i < rows.length; i++) {
          	    
          	    	picTotal += rows[i]['pics'];
  	    	    	weightTotal += rows[i]['weight'];
  	    	    	volTotal += rows[i]['volumu'];
          	        
      	    }		
        		
      	    //已经装车信息
      	    var dy = volTotal==0?"-":weightTotal/volTotal;
        		
      	   $("#pic").text(picTotal);
      	   $("#weight").text(weightTotal.toFixed(1));
      	   $("#volum").text(volTotal.toFixed(2));
      	   $("#density").text(dy.toFixed(0));
        }
      function totalSender(){
      	//计算函数
      	   
      	     $("#picSender").text("0");
         	   $("#weightSender").text("0.0");
         	   $("#volumSender").text("0.00");
         	  $("#densitySender").text("0");
      	    var picTotal = 0//计算pic的总和
      	    ,weightTotal=0//统计weight的总和
      	    ,volTotal=0;//统计vol的总和
      	    var rows = $('#custTotal').datagrid('getSelections')//获取当前的数据行
      	    for (var i = 0; i < rows.length; i++) {
      	    	picTotal += rows[i]['pics'];
      	    	weightTotal += rows[i]['weight'];
      	    	volTotal+= rows[i]['volumu'];
      	    }
      	    //新增  显示统计信息
      	    var dy = volTotal==0?"-":weightTotal/volTotal;
      	   $("#picSender").text(picTotal);
      	   $("#weightSender").text(weightTotal.toFixed(1));
      	   $("#volumSender").text(volTotal.toFixed(2));
      	 $("#densitySender").text(dy.toFixed(0));
        }
	


</script>

</head>


  
  <body>
  <div id="container">

    
    <div id="searchDiv">
			<div>
			<div class="label">线路</div>
			<div class="hang"><input type="text" id="lineId"  name="lineId" style="width:120px" /></div>
			<div class="label">批次</div>
			<div class="hang"><input type="text" id="bitch" class="easyui-textbox" name="bitch" style="width:120px" /></div>
			
			<div class="label">发货人</div>
			<div class="hang"><input type="text"  id="sender" class="easyui-textbox" name="sender"  style="width:100px"/></div>
			
			<div class="label">日期</div>
			<div class="hang"><input type="text"  id="sddate" class="easyui-datebox" name="sddate"  style="width:100px"/></div>
			</div>
			
			<div class="hang">
			<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</div>
			
		</div>
	
	<div id='main'>
	<div id='left'>
			<div id="tableSource"><table id="source"></table></div>
			<div id="tableTarget"><table id="target"></table></div>
			<div class="botto">
				<div class="bt1">
					<div class="label">件数</div><div class="bhang">
					<p id="pic1"></p></div>
					<div class="label">重量</div><div class="bhang">
					<p id="weight1"></p>
					</div>
					<div class="label">体积</div><div class="bhang">
					<p id="volum1"></p>
					</div>
					<div class="label">密度</div><div class="bhang">
					<p id="density1"></p>
					</div>
				</div>
				<div class="bt2">
				<div class="label">件数</div><div class="bhang">
					<p id="pic"></p></div>
					<div class="label">重量</div><div class="bhang">
					<p id="weight"></p>
					</div>
					<div class="label">体积</div><div class="bhang">
					<p id="volum"></p>
					</div>
					<div class="label">密度</div><div class="bhang">
					<p id="density"></p>
					</div>
				</div>
			</div>
	</div>
	<div id='right'>
			<div > <select id="statusID" name='statusId' class="easyui-combobox"  style="width:100px;panelHeight:100px;">   
						     <option value="1">未配仓</option>  	
						    <option value="2">已配仓</option> 
						    <option value="4">已到货</option>
						    <option value="6">已结束</option>  
						</select> 
			</div>
		<div id="tableTotal"><table id="custTotal"></table></div>
		<div class="bt3">
					<div class="blabel">件数</div><div class="sbbhang">
					
					<p id="picSender"></p></div>
					
					<div class="blabel">重量</div><div class="bhang">
					
					<p id="weightSender"></p>
					
					</div>
					<div class="blabel">体积</div><div class="bbhang">
					
					<p id="volumSender"></p>
					
					</div>
					<div class="blabel">密度</div><div class="sbbhang">
					<p id="densitySender"></p>
					</div>
		</div>
	</div>
	
	</div>
	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true">  </div>
	</div>
	
  </body>
</html>
