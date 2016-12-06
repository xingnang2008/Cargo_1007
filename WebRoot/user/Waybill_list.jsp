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
				bitch: $('#bitch').combobox('getValue'),
				lineId:$('#lineId').combobox('getValue'),
				waybill: $('#waybill').val(),
				custId: $('#custId').val(),
				sender: $('#sender').val(),
				raterName: $('#rater').val(),
				procurator: $('#procurator').val(),
				stdate: $('#stdate').datebox('getValue'), 
				enddate: $('#enddate').datebox('getValue')	
			});
		});	
		$('#btnReset').click(function(){
			
			$('#bitch').combobox('setValue',""),
			$('#lineId').combobox('setValue',""),				
			$('#waybill').textbox("clear"),
			$('#custId').textbox("clear"),
			$('#sender').textbox("clear"),
			$('#rater').textbox("clear"),
			$('#procurator').textbox("clear"),				
			$('#statusId').combobox('setValue',""),
			$('#stdate').datebox('setValue',""), 
			$('#enddate').datebox('setValue',"")	
		
	});	
		$('#dg').datagrid({    
				//请求的url地址
			    url:'<%=basePath%>admin/Waybill/Waybill-find.action',
			    queryParams: {
					bitch: 'bitch',
					lineId:'lineId',
					waybill: 'waybill',
					custId: 'custId',
					sender:'sender',
					raterName: 'rater',
					procurator:'procurator',
					stdate: 'stdate', 
					enddate: 'enddate'					
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
	       		rowStyler: function(index,row){
					if (row.editable>0){
						return 'background-color:#6293BB;color:#fff;';
					}
					
				},
				       		
				frozenColumns:[[
					{field:'z',checkbox:true},
					
					{field:'waybill',title:'运单号',align:'center',width:200}
				]],
				toolbar: [			{
					iconCls: 'icon-edit',
					text:'查看记录',
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
							width:'900',
							height:'650',
							content:'<iframe title="" src="Waybill_items.jsp" frameborder="0" width="100%" height="100%"/>'
						});
				}}},
				'-',
				{
					iconCls: 'icon-reload',
					text:'刷新',
					handler: function(){$("#dg").datagrid("reload");}
				},
				{
					iconCls: 'icon-edit',
					text:'打印运单',
					handler: function(){
					var rows =$("#dg").datagrid("getSelections");
					if(rows.length !=1){
						$.messager.show({
							title:'错误提示',
							msg:'一次只能生成一份运单，只能选取一个运单号。',
							timeout:2000,
							showType:'slide'
						});
					}else{
						//1.完成弹出更新页面
						$("#win").window({
							title:'运单打印',
							width:'100%',
							height:'100%',
							content:'<iframe title="" src="<%=basePath%>admin/Waybill/Waybill_print.jsp" frameborder="0" width="100%" height="100%"/>'
						});
				}}},
				{
					iconCls: 'icon-edit',
					text:'生成运单图片',
					handler: function(){
					var rows =$("#dg").datagrid("getSelections");
					if(rows.length !=1){
						$.messager.show({
							title:'错误提示',
							msg:'一次只能生成一份运单，只能选取一个运单号。',
							timeout:2000,
							showType:'slide'
						});
					}else{
						//1.完成弹出更新页面
						$("#win").window({
							title:'运单图片',
							width:'100%',
							height:'100%',
							content:'<iframe title="" src="<%=basePath%>admin/Waybill/Waybill_print2b.jsp" frameborder="0" width="100%" height="100%"/>'
						});
				}}},],
				
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
		                    {field:'lineId',title:'运输线路',align:'center',width:80},
			                {field:'bitch',title:'批次',align:'center',width:100},
		                    {field:'orderNo',title:'货源地',align:'center',width:80},
		                    {field:'sender',title:'发货人',align:'center',width:80},
		                    {field:'transType',title:'运输类型',align:'center',width:80},
					        {field:'destName',title:'目的地',align:'center',width:80},
					        {field:'pics',title:'包数',align:'right',width:50},
					        {field:'weight',title:'重量',align:'right',width:80},
					        {field:'volumu',title:'体积',align:'right',width:70},
					        {field:'goods',title:'品名',align:'center',width:100},
					        {field:'quantity',title:'数量',align:'center',width:80},
					        {field:'price',title:'单价',align:'right',width:80},
					        {field:'raterRate',title:'折扣',align:'right',width:80},
					        {field:'total',title:'应收金额',align:'right',width:80},
					        {field:'payMethod',title:'付款方式',align:'right',width:80},
					       					       
					        
					        {field:'advancedZ',title:'垫付￥',align:'right',width:80},			        
					        {field:'advancedU',title:'垫付$',align:'right',width:80},
					        {field:'packfeeZ',title:'包费￥',align:'right',width:80},
					        {field:'packfeeU',title:'包费$',align:'right',width:80},
					        {field:'noaccPackfee',title:'未加包费',align:'right',width:80},
					        {field:'noaccAdvance',title:'未加运费',align:'right',width:80},
					        {field:'worth',title:'货值',align:'right',width:80},
					        {field:'worthRate',title:'保率',align:'right',width:80},
					        {field:'insurance',title:'保费',align:'right',width:80},
					        {field:'indemnity',title:'索赔',align:'right',width:80}, 
					        {field:'sumbill',title:'运费',align:'right',width:80},
					        
					        {field:'procurator',title:'代理人',align:'center',width:80},
					        {field:'raterName',title:'经办人',align:'center',width:80},
					        {field:'cod',title:'代收款',align:'right',width:80},
					        {field:'raterRate',title:'折扣',align:'right',width:80},
					        {field:'discount',title:'代理费',align:'right',width:80},

					       
					        {field:'actualSum',title:'实收金额',align:'right',width:80},
					        {field:'exchangeRate',title:'汇率',align:'right',width:50},
					        {field:'custId',title:'客户号',align:'center',width:80},					    
					        {field:'custName',title:'联系人',align:'center',width:100},
					        {field:'custTel',title:'联系电话',align:'center',width:150},
					        {field:'custEmail',title:'邮箱',align:'center',width:100},
					        {field:'custAdd',title:'地址',align:'center',width:80},
					        
					     
					            
					        
					        {field:'statusId',title:'状态号',
					        	 formatter:function(value,row,index){ 
					        	switch(value){
					        		case 1: return "新建";
					        		break;
					        		case 2: return "已配仓";
					        		break;
					        		case 3: return "已分货，欠款";
					        		break;	
					        		case 4: return "已到货";
					        		break;					        		
					        		case 5: return "已到达，存货";
					        		break;
					        		case 6: return "已结束";
					        		break;
					        		case 7: return "灭失";
					        		break;
					        		case 8: return "问题处理中";
					        		break;
					        		
					        	}
	                        } ,align:'center',width:100},	
	                        
					       
					        {field:'destTel',title:'分货电话',align:'center',width:80},
					        {field:'descrip',title:'操作说明',align:'left',width:100},				        
					        {field:'remarks',title:'分货备注',align:'left',width:100},
					        {field:'editable',title:'状态',
					        	 formatter:function(value,row,index){ 
					        	switch(value){
					        		case 0: return "可编辑";
					        		break;
					        		case 1: return "锁定";
					        		break;
					        	}
	                        } ,align:'center',width:80}
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
		$("#bitch").combobox({
			
			valueField:'bitch',
			textField:'bitch',
			panelHeight:200,
			panelWidth:120,
			width:120
		}); 
		$("#editable").combobox({

			valueField: 'value',
			textField: 'label',
			panelHeight:'auto',
			panelWidth:100,
			width:100,
			data: [{
				label: '   --',
				value: ''
			},{
				label: '   锁定',
				value: '1'
			},{
				label: '   解锁',
				value: '0'
			}] 

		});
		
	}); 
		 function totalTarget(){
		    	//计算函数
		    	 var picTotal = 0//计算pic的总和
		    	    ,weightTotal=0//统计weight的总和
		    	    ,volTotal=0,//统计vol的总和
		    	    amout= 0,
		    	    sum =0,//统计应收的总和
		    	    outsum =0;//统计结算的总和
		    	    
		    	    var rows = $('#dg').datagrid('getSelections')//获取当前的数据行
		    	    
		    	   
		    	    
		    	    for (var i = 0; i < rows.length; i++) {
		    	    	picTotal += rows[i]['pics'];
		    	    	weightTotal += rows[i]['weight'];
		    	    	volTotal+= rows[i]['volumu'];
		    	    	amout+= rows[i]['quantity'];
						sum += rows[i]['total'];
						outsum += rows[i]['outSum'];
		    	    	
		    	    }
		    	    //新增  显示统计信息
		    	   $("#pic").text(picTotal);
		    	   $("#weight").text(weightTotal.toFixed(1));
		    	   $("#volum").text(volTotal.toFixed(2));
		    	   $("#amout").text(amout);
		    	   $("#sum").text(sum.toFixed(0));
		    	   $("#outsum").text(outsum.toFixed(0));
		    	   
		    	  
		      }
	
	
	</script>

  </head>
  
  
<body>   
<div id="container">
	
		<div id="searchDiv">
			<div class="line">
				<div class="label">线路</div>
				<div class="hang"><input type="text" id="lineId"  name="lineId" style="width:120px" /></div>
							
				<div class="label">批次</div>
				<div class="hang"><input type="text" id="bitch"  name="bitch" style="width:120px" /></div>
				
				<div class="label">客户号</div>
				<div class="hang"><input type="text"  id="custId"  class="easyui-textbox" name="custId" style="width:100px" /></div>
				
				<div class="label">运单号</div>
				<div class="hang"><input type="text" id="waybill" class="easyui-textbox" name="waybill" style="width:120px" /></div>
				
				<div class="label">发货人</div>
				<div class="hang"><input type="text"  id="sender" class="easyui-textbox" name="sender"  style="width:100px"/></div>
				
				<div class="label">经办人</div>
				<div class="hang"><input type="text"  id="rater" class="easyui-textbox" name="raterName"  style="width:100px"/></div>
			
			</div>
			<div class="line">
				
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
	
	
	<div id="tableDG"><table id="dg"></table></div>
	<div class="bottom">
		<div class="bt1">
			<div class="label">包数</div><div class="hang"><p id="pic"></p></div>
			<div class="label">重量</div><div class="hang"><p id="weight"></p>	</div>
			<div class="label">体积</div><div class="hang"><p id="volum"></p></div>
			<div class="label">数量</div><div class="hang"><p id="amout"></p></div>
			<div class="label">应收款</div><div class="hang"><p id="sum"></p></div>
			<div class="label">结算</div><div class="hang"><p id="outsum"></p></div>
			
			</div>
		</div>
		
	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,maximized:true,modal:true">   
        
	</div>  
 
</div>
</body>  


</html>
