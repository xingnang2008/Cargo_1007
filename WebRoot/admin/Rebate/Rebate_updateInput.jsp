<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
   
    
	<%@include file="../head.jspf" %>
	 <link rel="stylesheet" href="<%=basePath%>css/Rebate/Rebate_updateInput.css" type="text/css" />
	 <script language="JavaScript" >
	 $(function(){
			var rows =parent.$("#dg").datagrid("getSelections");
			$("#ff").form('load',{
				id:rows[0].id,				
				company:rows[0].company,
				companyCode:rows[0].companyCode,
				contract:rows[0].contract,
				source:rows[0].source,
				tradeType:rows[0].tradeType,
				custId:rows[0].custId,
				
				telphone:rows[0].telphone,
				bitch:rows[0].bitch,
				remarks:rows[0].remarks,
				packages:rows[0].packages,
				grossWeight:rows[0].grossWeight,
				sddate:rows[0].sddate,
				bcdate:rows[0].bcdate,
				infeeDate:rows[0].infeeDate,
				payMethod:rows[0].payMethod,
				inFee:rows[0].inFee
				//更新form中的数据
			});	
			$("input[name=company]").validatebox({
				
				required:true,
				missingMessage:'请输公司名称'
			});
			$('#dd').datebox({    
				 required:true   
			}); 
			//窗体弹出时默认禁用验证
			$("#ff").form("disableValidation");
			
			$("#btn").click(function(){
				//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){
					//提交数据
					//提交数据
					$('#ff').form('submit', {    
					    url:'Rebate/Rebate-update.action',    
					    
					    success:function(){    
							//关闭当前窗体
							parent.$("#win").window("close");
							//重载dg
							parent.$("#dg").datagrid("reload");				           
					    }    
					});  
				}
			});
			$("#ssi").combobox({
				url:'<%=basePath%>admin/Sender/Sender-listAll.action',
				editable:false,
				valueField:'sdName',
				textField:'sdName',
				panelHeight:'auto',
				panelWidth:100,
				width:100,
				onSelect: function(rec){   
	             $('#sst').textbox('setValue', rec.telphone);
	            
	       		 } 
			});
			
			//批次选择框
			$("#ssb").combobox({
				url:'<%=basePath%>admin/Bitch/Bitch-listAll.action',
				editable:false,
				valueField:'bitch',
				textField:'bitch',
				panelHeight:'auto',
				panelWidth:100,
				width:100,
				onSelect: function(rec){   
	             $('#ssbid').textbox('setValue', rec.id);
	       		 } 
			}); 
			$("#sst").textbox();
			
			$('#gtab').datagrid({    
				//请求的url地址
				
			    url:'<%=basePath%>admin/Rebategoods/Rebategoods-listByRebateId?rebateId='+rows[0].id,			    
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
				//同列属性，冰结在最左侧
				pageSize: 10,//每页显示的记录条数，默认为10  
	       		pageList: [5,10,15,20],//可以设置每页记录条数的列表  
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
							width:500,
							height:450,
							content:'<iframe src="../Rebategoods/Rebategoods-saveInput.action" frameborder="0" width="100%" height="100%"> </iframe>'
						});
					
						}
					},
					{
						iconCls: 'icon-edit',
						text:'编辑',
						handler: function(){
						var rows =$("#gtab").datagrid("getSelections");
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
								width:'80%',
								height:'100%',
								content:'<iframe title="更新记录" src="../Rebategoods/Rebategoods-updateInput.action" frameborder="0" width="100%" height="100%"/>'
							});
					}}},
					{
						iconCls: 'icon-remove',
						text:'删除',
						handler: function(){
						var rows =$("#gtab").datagrid("getSelections");
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
									$.post("../Rebategoods/Rebategoods-deleteByIds.action",{ids:ids},function(result){
										if(result =="true"){

											$.messager.show({
												title:'删除成功',
												msg:'删除成功',
												timeout:2000,
												showType:'slide'
											
											});
											//取消选中所有行
											$("#gtab").datagrid("uncheckAll");
											//重新刷新页面
											$("#gtab").datagrid("reload");
											
										}else{
											$.messager.show({
												title:'删除错误',
												msg:'删除失败',
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
						handler: function(){$("#gtab").datagrid("reload");}
					}],
				
			    columns:[[ 
												      
					      
					        {field:'goods',title:'商品名称'},
					        {field:'hsCode',title:'商品编码',align:'right'} ,
					        {field:'quantity',title:'件数',align:'right'},
					        {field:'netWeight',title:'净重',align:'right'},
					        {field:'price',title:'单价',align:'right'},
					        {field:'amount',title:'金额',align:'right'},			        
					        {field:'material',title:'材质',align:'right'},					       
					        {field:'remarks',title:'备注',align:'right'}
				]]    
			});
			$('#wtab').datagrid({    
				//请求的url地址
			    url:'<%=basePath%>admin/Waybill/Waybill-findByRebateId.action?rebateId='+rows[0].id,
			    
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
				pageSize: 20,//每页显示的记录条数，默认为10  
	       		pageList: [20,50,100,150,200],//可以设置每页记录条数的列表  
	       		
				frozenColumns:[[
					{field:'z',checkbox:true},
					{field:'id',title:'编号',width:50}
					
				]],
				
				
			    columns:[[ 
												      
					        {field:'sddate',title:'发货日期', 
						        formatter:function(value,row,index){  
		                        	var unixTimestamp = new Date(value);  
		                        	return unixTimestamp.toLocaleDateString();  
		                        } , align:'right'},
		                    {field:'waybill',title:'运单号',align:'center'},
					        {field:'bitch',title:'批次'},
					        {field:'pics',title:'包数',align:'right'},
					        {field:'weight',title:'重量',align:'right'},
					        {field:'volumu',title:'体积',align:'right'},
					        {field:'goods',title:'品名',align:'center'},
					        {field:'quantity',title:'数量',align:'center'},
					        {field:'custId',title:'唛头',align:'center'},					    
					        {field:'custName',title:'联系人',align:'center'},
					        {field:'custTel',title:'联系电话',align:'center'},
					        {field:'sender',title:'发货人',align:'right'},
					        {field:'rebateId',title:'核销编号',align:'right'},					       
					        {field:'raterName',title:'经办人',align:'center'},
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
		})
	 </script>	

  </head>
  
  <body>
	<div id="container">
	
	<div id="middle">
		<div id="lside">
			<div>	
			<form id="ff" method="post">	
				<div id="retab" >
				
				
				<div class="hang">
						<div class="flabel">企业名称</div>
						<div class="fdata"><input type="text" name="company" class="longtd"/></div>
				</div>
				<div class="hang">
						<div class="flabel">海关编码</div>
						<div class="fdata"><input type="text" name="companyCode" class="longtd"/></div>
				</div>
				<div class="hang">
						<div class="flabel">合同号</div>
						<div class="fdata"><input type="text" name="contract"  class="midtd"/></div>		
				</div>
				<div class="hang">
						<div class="flabel">贸易方式</div>
						<div class="fdata">
							<select name="tradeType" >
								<option selected value="一般贸易" >一般贸易</option>
								<option value='来料加工' >来料加工</option>
							</select>
						</div>
				</div>
				<div class="hang">
						<div class="flabel">境内货源地</div>
						<div class="fdata"><input type="text" name="source" class="midtd"/></div>		
				</div>
				<div class="hang">
						<div class="flabel">发货人</div>
						<div class="fdata"><input id="ssi" name="custId"/></div>
				</div>	
					
				<div class="hang">
						<div class="flabel">电话</div>
						<div class="fdata"><input id="sst" type="text" name="telphone"  class="midtd"/></div>		
				</div>
				<div class="hang">
						<div class="flabel">包数</div>
						<div class="fdata"><input type="text" name="packages"  class="shorttd"/></div>		
				</div>
				<div class="hang">
						<div class="flabel">毛重</div>
						<div class="fdata"><input type="text" name="grossWeight"  class="midtd"/></div>		
				</div>
				
				<div class="hang">
						<div class="flabel">批次</div>
						<div class="fdata"><input id="ssb" name="bitch"/></div>	
				</div>					
				<div class="hang">
						<div class="flabel">接单日期</div>
						<div class="fdata"><input type="text" name="sddate"  class="easyui-datebox"/></div>		
				</div>			
				<div class="hang">
						<div class="flabel">回单日期</div>
						<div class="fdata"><input type="text" name="bcdate"  class="easyui-datebox"/></div>		
				</div>
				<div class="hang">
						<div class="flabel">收款日期</div>
						<div class="fdata"><input type="text" name="infeeDate"  class="easyui-datebox"/></div>		
				</div>			
				<div class="hang">
						<div class="flabel">收款金额</div>
						<div class="fdata"><input type="text" name="inFee"  class="midtd"/></div>		
				</div>		
				<div class="hang">
						<div class="flabel">付款方式</div>
						<div class="fdata">
						<select name="payMethod">
						  <option value ="0">现金</option>
						  <option value ="1">汇款</option>
						  <option value="2">其他</option>
						</select>							
				</div>	</div>
				<div class="hang">
	    			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
	    			<input type="hidden" name="id"/>
	    		</div>
	    		</div>
				</form>
			</div>
		</div>
		<div id="rside">
			<div id="rsidetop">
				<table id="gtab" >						
				</table>
			</div>
			<div id="rsidefooter">
				<table id="wtab" >
				<th>
					核销运单明细
				</th>
			</table>
		
		</div>
		
		</div>
		<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true" ></div> 
	</div>
	</div>
</body>
</html>
