<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@include file="../head.jspf" %>
	 <link rel="stylesheet" href="<%=basePath%>css/Waybill/Waybill_saveInput.css" type="text/css" />
	 <script language="JavaScript" >
	 
	 $(function(){
		 $('#editable').attr('readonly',true);
			var rows =parent.$("#dg").datagrid("getSelections");
			$("#ff").form('load',{
				id:rows[0].id,				
				sddate:rows[0].sddate,
				lineId:rows[0].lineId,
				transType:rows[0].transType,
				bitch:rows[0].bitch,
				destName:rows[0].destName,
				orderNo:rows[0].orderNo,
				waybill:rows[0].waybill,
				statusId:rows[0].statusId,
				sender:rows[0].sender,
				sdTel:rows[0].sdTel,
				custId:rows[0].custId,
				custName:rows[0].custName,
				custTel:rows[0].custTel,
				custEmail:rows[0].custEmail,
				custAdd:rows[0].custAdd,
				remarks:rows[0].remarks,
				pics:rows[0].pics,
				weight:rows[0].weight,
				volumu:rows[0].volumu,
				goods:rows[0].goods,
				quantity:rows[0].quantity,
				isRebate:rows[0].isRebate,
				rebateId:rows[0].rebateId,
				advancedZ:rows[0].advancedZ,
				advancedU:rows[0].advancedU,
				packfeeZ:rows[0].packfeeZ,
				packfeeU:rows[0].packfeeU,
				worth:rows[0].worth,
				worthRate:rows[0].worthRate,
				insurance:rows[0].insurance,
				price:rows[0].price,
				sumbill:rows[0].sumbill,
				payMethod:rows[0].payMethod,
				raterName:rows[0].raterName,
				cod:rows[0].cod,
				raterRate:rows[0].raterRate,
				discount:rows[0].discount,
				total:rows[0].total,
				actualSum:rows[0].actualSum,
				outWorth:rows[0].outWorth,
				outWorthrate:rows[0].outWorthrate,
				outInsurance:rows[0].outInsurance,
				outPrice:rows[0].outPrice,
				outSum:rows[0].outSum,
				procurator:rows[0].procurator,
				mark:rows[0].mark,
				noaccAdvance:rows[0].noaccAdvance,
				noaccPackfee:rows[0].noaccPackfee,
				exchangeRate:rows[0].exchangeRate,
				descrip:rows[0].descrip,
				editable:rows[0].editable,
				sdId:rows[0].sdId,
				arrPics:rows[0].arrPics,
				indemnity:rows[0].indemnity,
				cangId:rows[0].cangId,
				advanceStauts:rows[0].advanceStauts,
				raterStauts:rows[0].raterStauts
				
				//更新form中的数据
			});	
			
			
			//窗体弹出时默认禁用验证
			$("#ff").form("disableValidation");
			
			
			$("#btnSave").click(function(){
				 
					  
						//关闭当前窗体
						parent.$("#win").window("close");
						//重载dg
						parent.$("#dg").datagrid("reload");				           
				  
				
			});
			//发货人选择框
			$("#selectSender").combobox({
				url:'<%=basePath%>admin/Sender/Sender-listAll.action',
				
				valueField:'id',
				textField:'sdName',
				panelHeight:200,
				panelWidth:100,
				width:100,
				
				onSelect: function(rec){ 
							 $('#sdTel').textbox('setValue', rec.phone);
							 $('#sender').textbox('setValue', rec.sdName);
				               $('#custNm').textbox('clear');
						       $('#custTl').textbox('clear');
						       $('#custEmail').textbox('clear');
						       $('#custAdd').textbox('clear');
						     //重置收货人
				             	$('#selectCustId').combobox("clear").combobox("reload",
				    	             '<%=basePath%>admin/CustomerRelation/CustomerRelation-listCustBySenderId.action?senderId='+rec.id); 
				           //重置经办人
				 			$("#selectRater").combobox("clear").combobox("reload",
				 		 			'<%=basePath%>admin/CustomerRelation/CustomerRelation-listRaterBySenderId.action?senderId='+rec.id); 
				 			 //重置代理人
				 			$("#selectProcurator").combobox("clear").combobox("reload",
				 		 			'<%=basePath%>admin/CustomerRelation/CustomerRelation-listProcuratorBySenderId.action?senderId='+rec.id); 
				 			 
				      }    
		        
			});  
			$("#selectCustId").combobox({
				url:'<%=basePath%>admin/Customer/Customer-listAll.action',
            	valueField:'custId',
				textField:'custId',
				panelHeight:200,
				panelWidth:100,
				width:100,
				onSelect: function(recc){ 							
	             $('#custNm').textbox('setValue', recc.name);
	             $('#custTl').textbox('setValue', recc.telphone);
	             $('#custEmail').textbox('setValue', recc.email);
	             $('#custAdd').textbox('setValue', recc.address); 
				} 
			});
			
			//代理人选择框
			$("#selectProcurator").combobox({
				url:'<%=basePath%>admin/Procurator/Procurator-listAll.action',
				editable:false,
				valueField:'name',
 				textField:'name',				
				panelHeight:200,
				panelWidth:100,
				width:100
			});
			//经办人选择框
			$("#selectRater").combobox({
				url:'<%=basePath%>admin/Rater/Rater-listAll.action',
				editable:false,
				valueField:'raterName',
 				textField:'raterName',				
				panelHeight:200,
				panelWidth:100,
				width:100
			});
			//运输方式选择框
			$("#selectLineId").combobox({
				url:'<%=basePath%>admin/Line/Line-listAll.action',
				editable:true,
				required:true,
				valueField:'lineId',
				textField:'lineId',
				panelHeight:'auto',
				panelWidth:100,
				width:100,
				onSelect: function(rec){ 
					$('#transType').textbox('setValue', rec.transType);   
		            $('#selcetBitch').combobox("clear").combobox("reload",
				            '<%=basePath%>admin/Bitch/Bitch-listByLine.action?lineId='+rec.lineId);
	        	}  
			});
			//批次选择 
			$("#selcetBitch").combobox({
				url:'<%=basePath%>admin/Bitch/Bitch-listAll.action',
				editable:false,	
				required:true,
				valueField:'bitch',
				textField:'bitch',
				panelHeight:'auto',
				panelWidth:100,
				width:100
			}); 
			//目的地选择框
			$("#selcetDest").combobox({
				url:'<%=basePath%>admin/Dest/Dest-listAll.action',
				editable:false,
				valueField:'destName',
				textField:'destName',
				panelHeight:'auto',
				panelWidth:100,
				width:100
					
				
			});
			//品类选择框
			$("#selcetMark").combobox({
				url:'<%=basePath%>admin/Mark/Mark-listAll.action',
				editable:false,
				valueField:'type',
				textField:'remarks',
				panelHeight:'auto',
				panelWidth:100,
				width:100
				
			});

			//更改单价时计算运费
			$("#price").numberbox({
				onChange:function(newValue,oldValue){ 
				var weight = $("#weight").numberbox("getValue");
				if(weight!=0 && newValue !=0){
					total= weight*newValue;
					$("#sumbill").numberbox("setValue",total);
				}
				}
			});	
			//更改重量时计算运费 及折扣	和结算金额		
			$("#weight").numberbox({
				onChange:function(newValue,oldValue){ 
				var price = $("#price").numberbox("getValue");
				var raterRate = $("#raterRate").numberbox("getValue");
				var outPrice = $("#outPrice").numberbox("getValue");
				var outInsurance =$("#outInsurance").numberbox("getValue");
					tot = raterRate*newValue;
					total= price*newValue;
					outSum = outPrice*newValue+outInsurance;
					$("#sumbill").numberbox("setValue",total);
					$("#discount").numberbox("setValue",tot);
					$("#outSum").numberbox("setValue",outSum);
				}
			});	
			//计算保险费
			$("#worth").numberbox({
				onChange:function(newValue,oldValue){ 
				var worthRate = $("#worthRate").numberbox("getValue");
				if(worthRate!=0 && newValue !=0){
					total= worthRate*newValue;
					$("#insurance").numberbox("setValue",total);
				}
				}
			});				
			$("#worthRate").numberbox({
				onChange:function(newValue,oldValue){ 
				var worth = $("#worth").numberbox("getValue");
				if(worth!=0 && newValue !=0){
					total= worth*newValue;
					$("#insurance").numberbox("setValue",total);
				}
				}
			});	
			//计算对外保险费
			$("#outWorth").numberbox({
				onChange:function(newValue,oldValue){ 
				var worthRate = $("#outWorthrate").numberbox("getValue");
				if(worthRate!=0 && newValue !=0){
					total= worthRate*newValue;
					$("#outInsurance").numberbox("setValue",total);
				}
				}
			});				
			$("#outWorthrate").numberbox({
				onChange:function(newValue,oldValue){ 
				var worth = $("#outWorth").numberbox("getValue");
				if(worth!=0 && newValue !=0){
					total= worth*newValue;
					$("#outInsurance").numberbox("setValue",total);
				}
				}
			});
			//更改折扣率时计算代理费
			$("#raterRate").numberbox({
				onChange:function(newValue,oldValue){ 
				var weight = $("#weight").numberbox("getValue");				
					total= weight*newValue;
					$("#discount").numberbox("setValue",total);				
				}
			});	
			//计算总应收款
			$("#discount").numberbox({
				onChange:calcMoney 				
			});	
			$("#insurance").numberbox({
				onChange:calcMoney 				
			});
			$("#sumbill").numberbox({
				onChange:calcMoney 				
			});
			$("#cod").numberbox({
				onChange:calcMoney 				
			});
			$("#packfeeZ").numberbox({
				onChange:calcMoney 				
			});
			$("#packfeeU").numberbox({
				onChange:calcMoney 				
			});
			$("#advancedU").numberbox({
				onChange:calcMoney 				
			});
			$("#advancedZ").numberbox({
				onChange:calcMoney 				
			});
			$("#exchangeRate").numberbox({
				onChange:calcMoney 				
			});
			//计算外配结算价
			$("#outPrice").numberbox({
				onChange:calcOutMoney
			});
			$("#outInsurance").numberbox({
				onChange:calcOutMoney
			});
		});  
	 function calcMoney(){
			var discount =$("#discount").numberbox("getValue");
			var insurance =$("#insurance").numberbox("getValue");
			var sumbill =$("#sumbill").numberbox("getValue");
			var cod =$("#cod").numberbox("getValue");
			var packfeeZ = $("#packfeeZ").numberbox("getValue");
			var advancedZ =$("#advancedZ").numberbox("getValue");
			var packfeeU = $("#packfeeU").numberbox("getValue");
			var advancedU =$("#advancedU").numberbox("getValue");
			var exchangeRate=$("#exchangeRate").numberbox("getValue");
			var pfz=0;
			var adz=0;
			if(packfeeZ==0){pfz = 0}else{pfz=packfeeZ/exchangeRate}
			if(advancedZ==0){adz = 0}else{adz=advancedZ/exchangeRate}
			var shu = parseFloat(discount)+parseFloat(insurance)+parseFloat(sumbill)+parseFloat(pfz)+parseFloat(adz);
			var total= parseInt(Math.round(shu))+parseInt(cod)+parseInt(packfeeU)+parseInt(advancedU);
			
			$("#total").numberbox("setValue", total);
		 }	
	 function calcOutMoney(){				
			var outInsurance =$("#outInsurance").numberbox("getValue");				
			var outPrice =$("#outPrice").numberbox("getValue");
			var weight = $("#weight").numberbox("getValue");
			var outBill = outPrice * weight;
			console.info(outBill);
			console.info(Math.round(outBill));
			var outSum= parseInt(Math.round(outBill))+parseInt(outInsurance);
			
			$("#outSum").numberbox("setValue", outSum);
			console.info(outSum);
		 }	
	 $.extend($.fn.validatebox.defaults.rules, {
		    equals: {
				validator: function(value){
					return value == 0;
				},
				
				message: '此运单已经被<锁定>,无法修改。'
		    }
		});
	 </script>	
  </head>
  
  <body>
    <form id="ff" method="post">
    <div class="container">
		<div id="top">	
			<a id="btnSave" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">关闭</a>
			
		</div>
	
  	<div class="main">
      <div class="kuai">
	      <div class="hang">
		      	<div class="xiang">
			        <div class="xianglabel">运输方式</div>
			        <div class="xiangshort"><input id="selectLineId" name="lineId" /> </div>       
		        </div>
		        <div class="xiang">
			        <div class="xianglabel">批次</div>
			        <div class="xiangshort"><input id="selcetBitch" name="bitch" /></div>
		        </div>
		        <div class="xiang">
			        <div class="xianglabel">运输类型</div>
			        <div class="xiangshort"><input id="transType" class="easyui-textbox" type="text" name="transType" /></div>
		        </div>
		        <div class="xiang">
			        <div class="xianglabel">发货日期</div>
			        <div class="xiangshort"><input id="sddate" type="text" class="easyui-datebox" name="sddate"/></div>
		        </div>
	      </div>
      </div>
      <div class="kuai">
       <div class="hang">
	      <div class="xiang">
		        <div class="xianglabel">运单号</div>
		        <div class="xiangshort"><input id="waybill" name="waybill" class="easyui-validatebox textbox" style="width:180px;height:30px;" /> 
		        </div></div>
		    <div class="xiang">    
		        <div class="xianglabel">货源地</div>
		      <div class="xiangshort"><select name="orderNo" class="easyui-combobox"  style="width:100px;panelHeight:100px;">   
						     <option value="北京">北京</option>  	
						     <option value="广州">广州</option>   
						     <option value="义乌">义乌</option>
						  				    
						</select> </div>
		     </div>
		     <div class="xiang">
		        <div class="xianglabel">发货人</div>
		        <div class="xiangshort"><input id="selectSender" name="sdId">
		        </div>
		    </div>
		    <div class="xiang">
              <div class="xianglabel">经办人</div>
               <div class="xiangshort"><input id="selectRater" name="raterName" />
             
              </div></div>
		  </div>
		</div>
		<div class="kuai">     
		   <div class="hang">
		   		 <div class="xiang">
			        <div class="xianglabel">客户号</div>
			        <div class="xiangshort"><input id="selectCustId" name="custId" /></div>
			        </div>
		         <div class="xiang">
			        <div class="xianglabel">目的地</div>
			        <div class="xiangshort"><input id="selcetDest" name="destName" /></div>
		        </div>
			         <div class="xiang">
			        <div class="xianglabel"></div>
			        <div class="xiangshort"></div>
			        </div>
			      <div class="xiang">
			        <div class="xianglabel"></div>
			        <div class="xiangshort"></div>
			        </div>
			     </div>
		    <div class="hang">  
		      	
			         <div class="xiang">
			        <div class="xianglabel">客户名</div>
			        <div class="xiangshort"><input id="custNm" class="easyui-textbox"  name="custName"  /></div>
			        </div>
		         <div class="xiang">
			        <div class="xianglabel">联系电话</div>
			        <div class="xiangshort"><input id="custTl" class="easyui-textbox" name="custTel" /> </div>
			        </div>
			     
		         <div class="xiang">
		         	<div class="xianglabel">邮箱</div>
		        	<div class="xiangshort"><input id="custEmail"  class="easyui-textbox"  name="custEmail" /></div>
		        	</div>
		    
		   		<div class="xiang">
		      		<div class="xianglabel">地址</div>
		      		<div class="xiangshort"><input id="custAdd"  class="easyui-textbox"  name="custAdd" /></div>
		      	</div>
		    
		     </div> 	
	      	</div>
       
      <div class="kuai">   
	      <div class="hang">
	      	<div class="xiang">
		        <div class="xianglabel">包数</div>
		        <div class="xiangshort"><input type="text"  class="easyui-numberbox validatebox" required="true"   value="0"  style="width:80px" name="pics" /></div>
		     </div>
		    <div class="xiang">
		        <div class="xianglabel">重量</div>
		        <div class="xiangshort"><input type="text" id="weight"  precision="1" class="easyui-numberbox validatebox" required="true"   value="0"   style="width:100px" name="weight" /></div>
		     </div>
		    <div class="xiang">    
		        <div class="xianglabel">体积</div>
		        <div class="xiangshort"><input type="text"  class="easyui-numberbox validatebox" required="true"  precision="2" value="0"  style="width:100px" name="volumu" /></div>
		     </div>
		      <div class="xiang">
			        <div class="xianglabel">品类</div>
			        <div class="xiangshort"><input id="selcetMark" name="mark" /></div>
			        </div>
	      </div>
	      <div class="hang">
		      <div class="xiang">
			        <div class="xianglabel">品名</div>
			        <div class="xiangshort"><input type="text" class="easyui-textbox"  style="width:180px" name="goods" /></div>
		        </div>

			        
			    <div class="xiang">
			        <div class="xianglabel">数量</div>
		        	<div class="xiangshort"><input type="text" class="easyui-numberbox  validatebox" required="true"  value="0"  style="width:100px" name="quantity" /></div>
		        </div>
				<div class="xiang">
			        <div class="xianglabel">核销</div>
			        <div class="xiangshort">
			        <input type="radio" name="isRebate" value="0" checked="checked" />不核销 
					<input type="radio" name="isRebate" value="1" />核销 </div>
			   </div>

			        
			    <div class="xiang">
			        <div class="xianglabel">核销编号</div>
		        	<div class="xiangshort"><input type="text" class="easyui-textbox validatebox" required="true" value="0"  style="width:100px" name="rebateId" /></div>
		        </div>

	      </div>	      
      </div>
     
	      <div class="kuai"> 
		      <div class="hang">
			      <div class="xiang">
			        <div class="xianglabel">包费￥</div>
			        <div class="xiangshort"><input type="text" id="packfeeZ" class="easyui-numberbox validatebox" required="true"   value="0"  style="width:100px" name="packfeeZ" /></div></div>
			      <div class="xiang">
			        <div class="xianglabel">垫付款￥</div>
			        <div class="xiangshort"><input type="text" id="advancedZ" class="easyui-numberbox validatebox" required="true"   value="0"  style="width:100px" name="advancedZ" /></div></div>
			         <div class="xiang">
			        <div class="xianglabel">汇率</div>
			        <div class="xiangshort"><input type="text" id="exchangeRate" class="easyui-numberbox validatebox" required="true"  precision="2" value="6.6"  style="width:100px" name="exchangeRate" /></div>
			        </div>
			         <div class="xiang">
			        <div class="xianglabel"></div>
			        <div class="xiangshort"></div>
			        </div>
		      </div>
		      <div class="hang">
		      		<div class="xiang">
			        <div class="xianglabel">未加包费</div>
			        <div class="xiangshort"><input type="text" id="nopackfee" class="easyui-numberbox validatebox" required="true"   value="0"  style="width:100px" name="noaccPackfee" /></div>
			        </div>
			         <div class="xiang">
			        <div class="xianglabel">未加运费</div>
			        <div class="xiangshort"><input type="text" id="noadvanced" class="easyui-numberbox validatebox" required="true"   value="0"  style="width:100px" name="noaccAdvance" /></div>
			        </div>
			      <div class="xiang">
			        <div class="xianglabel">包费$</div>
			        <div class="xiangshort"><input type="text" id="packfeeU" class="easyui-numberbox validatebox" required="true"   value="0"  style="width:100px" name="packfeeU" /></div></div>
			      <div class="xiang">
			        <div class="xianglabel">垫付款$</div>
			        <div class="xiangshort"><input type="text" id="advancedU" class="easyui-numberbox validatebox" required="true"   value="0"  style="width:100px" name="advancedU" /></div></div>
			     
		      </div>
		      <div class="hang">
			      <div class="xiang">
			        <div class="xianglabel">货值</div>
			        <div class="xiangshort"><input type="text" id="worth" class="easyui-numberbox validatebox" required="true"   value="0"  style="width:100px" name="worth" /></div></div>
			      <div class="xiang">
			        <div class="xianglabel">保率</div>
			        <div class="xiangshort"><input type="text"  id="worthRate" class="easyui-numberbox validatebox" required="required"   precision="3"  value="0.01"   style="width:100px" name="worthRate" /></div></div>
			      <div class="xiang">
			        <div class="xianglabel">保险费</div>
			        <div class="xiangshort"><input type="text"  id="insurance"  class="easyui-numberbox validatebox" required="required"   value="0"  style="width:100px" name="insurance" /></div></div>
			        <div class="xiang">
			        <div class="xianglabel"></div>
			        <div class="xiangshort"></div>
			        </div>
		      </div>
		  </div>
		  <div class="kuai">
		   <div class="hang">
		      <div class="xiang">
			        <div class="xianglabel">单价</div>
			        <div><input type="text" id="price"  class="easyui-numberbox validatebox" required="required"   precision="2"  value="0"  style="width:100px" name="price" /></div>
		        </div>
		      <div class="xiang">
			        <div class="xianglabel">运费</div>
			        <div class="xiangshort"><input type="text" id="sumbill" class="easyui-numberbox validatebox" required="required"  precision="1"  value="0"  style="width:100px" name="sumbill" /></div>
		      </div>
		         <div class="xiang">
			        <div class="xianglabel">索赔</div>
			        <div class="xiangshort"><input type="text"  class="easyui-numberbox validatebox" required="required"  value="0"  style="width:100px" name="indemnity" /></div>
			        </div>
			    <div class="xiang">
			        <div class="xianglabel">可索赔</div>
			        <div class="xiangshort"><input type="text" readonly="true" id="showIndemnity"  /></div>
			        </div>  
	    	</div>
	    	</div>
	    <div id="raterDiv"  class="kuai"> 
          
            <div  class="hang">
            <div class="xiang">
              <div class="xianglabel">代理人</div>
              <div class="xiangshort"><input id="selectProcurator" name="procurator" />
              </div></div>
           <div class="xiang">
              <div class="xianglabel">折扣率</div>
              <div class="xiangshort"><input type="text" id="raterRate"  class="easyui-numberbox validatebox" required="required"  precision="2"  value="0" style="width:60px" name="raterRate" /></div></div>
            <div class="xiang">
              <div class="xianglabel">代收款</div>
              <div class="xiangshort"><input type="text" class="easyui-numberbox validatebox" required="required"   id="cod"  value="0"  style="width:100px" name="cod" /></div></div>
            <div class="xiang">
              <div class="xianglabel">代理费</div>
              <div class="xiangshort"><input type="text" id="discount" class="easyui-numberbox validatebox" required="required"  precision="2" value="0"  style="width:100px" name="discount" /></div>
            </div>
           </div>
          
        </div>     
	    <div class="kuai">
		   <div class="hang">
		      <div class="xiang">
			        <div class="xianglabel">应收款</div>
			        <div><input type="text"  class="easyui-numberbox validatebox" required="required"   id="total"  value="0" style="width:100px" name="total" /></div>
		        </div>
		      <div class="xiang">
			        <div class="xianglabel">实收款</div>
			        <div class="xiangshort"><input type="text"  class="easyui-numberbox validatebox" required="required"   value="0"  style="width:100px" name="actualSum" /></div>
		      </div>
		      <div class="xiang">
			        <div class="xianglabel">付款方式</div>
			        <div class="xiangshort"><select name="payMethod" class="easyui-combobox"  style="width:100px;panelHeight:100px;">   
						     <option value="到付">到付</option>  	
						    <option value="正付">正付</option>   
						  <option value="已付款">已付款</option>				    
						</select> </div>
			        </div>
			    <div class="xiang">
			        <div class="xianglabel">到货包数</div>
			        <div class="xiangshort"><input type="text"  name="arrPics" class="easyui-numberbox" readonly="true"  style="width:100px" value="0" /></div>
			        </div>
	    	</div>
	    	 <div class="hang">
			      <div style="width:500px;float:left;">
				        <div class="xianglabel">备注</div>
				        <div style="width:400px;float:left"><input type="text"  class="easyui-textbox" multiline="true" value="" style="width:390px;height:40px" name="remarks" /></div>
			        </div>
			      <div style="width:500px;float:left;">
				        <div class="xianglabel">说明</div>
				        <div style="width:400px;float:left"><input type="text"  class="easyui-textbox" multiline="true" value="" style="width:390px;height:40px" name="descrip" /></div>
			        </div>
			 </div>
	    </div>
	
	    	
	   			 <div> 
			        <!-- 隐藏的项目 -->
			        <input type="hidden"    id="sender" class="easyui-textbox"  name="sender" />
			        <input type="hidden" name="id"/>			      
			        <input type="hidden" name="statusId" />
			        <input type="hidden" name="cangId" />
			        <input type="hidden" id="raterStauts" name="raterStauts" />	
				 	<input type="hidden" id="advanceStauts" name="advanceStauts" />	
			     </div>
  		</div>
  		
    	<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true" ></div> 
      </div>   
   


    </form> 
    
  </body>
</html>
