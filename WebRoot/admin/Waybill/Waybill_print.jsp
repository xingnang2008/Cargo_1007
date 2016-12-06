<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <%@include file="../head.jspf" %>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/Waybill/Waybill_print.css">
	
	<script language="javascript" src="<%=basePath%>script/jqprint/jquery-1.4.4.min.js"></script> 
	<script language="javascript" src="<%=basePath%>script/jqprint/jquery.jqprint-0.3.js"></script>
	 <script language="JavaScript" >
	 $(document).ready(function(){
		 var rows =parent.$("#dg").datagrid("getSelections");
		$("#waybill").text(rows[0].waybill);
		$("#transType").text(rows[0].transType);
		$("#orderNo").text(rows[0].orderNo);
		$("#sender").text(rows[0].sender);
		$("#destName").text(rows[0].destName);
		$("#custId").text(rows[0].custId);
		$("#custName").text(rows[0].custName);
		$("#custTel").text(rows[0].custTel);
		$("#destTel").text(rows[0].destTel);
		$("#pics").text(rows[0].pics);
		$("#weight").text(rows[0].weight);
		$("#volumu").text(rows[0].volumu);
		$("#goods").text(rows[0].goods);
		$("#quantity").text(rows[0].quantity);
		$("#total").text(rows[0].total);
		$("#price").text(rows[0].price+rows[0].raterRate);
		$("#worth").text(rows[0].worth);
		$("#worthRate").text(rows[0].worthRate);
		$("#insurance").text(rows[0].insurance);
		$("#indemnity").text(rows[0].indemnity);
		var unixTimestamp = new Date(rows[0].sddate);  
		$("#sddate").text(unixTimestamp.toLocaleDateString());
		
		var advZ = rows[0].advancedZ;
		var advU = rows[0].advancedU;
		var pkfZ = rows[0].packfeeZ;
		var pkfU = rows[0].packfeeU;
		var exchangeRate =rows[0].exchangeRate;
		var advanced=0;
		var packfee=0;
		if(advZ==0){advanced = advU }else{advanced=Math.round(advZ/exchangeRate)+advU}
		if(pkfZ==0){packfee = pkfU}else{packfee=Math.round(pkfZ/exchangeRate)+pkfU}
		
		$("#advanced").text(advanced);
		$("#packfee").text(packfee);
		



		
		});
	 function  a(){
	        $(".container").jqprint();
	    }
		
	    
	</script>
  </head>
  
  <body>   
    <input name="print" id="print" type="button"  value=" 打印 " class="btn" onclick='a()'/> 
     
    <div class="container">
    	<div class="top">
	    	<div class="t-kuaif">
	    		<div><span class="ruFont">Маркировка</span>(货 号):</div>
	    		</div>
	    		<div class="t-kuais">
	    		<div><span class="billFont"><b> <p id="waybill"></p></b></span></div>
	    	</div>
	    	<div class="t-kuaif">
	    		<div><span class="ruFont">вид перевозки</span>(运输方式)：</div>
	    		</div>
	    		<div class="t-kuais">
	    		<div><span class="billFont"> <b><p id="transType"></p> </b></span></div>
	    		<div></div>
	    	</div>
	    	
    	</div>
    	<div class="conInfo">
    		<div class="conInfoLeft">
    			<div class="senderDiv">
    			<div class="hang1">
			    		<div class=h1><span class="ruFont">Станция отправления</span>始发地:</div>
			    		<div class=h1xiang> <p id="orderNo"></p></div>
		    		</div>
		    	
	    			<div class="hang1">
		    			<div class=h1><span class="ruFont">Отправитель </span>发货人:</div>
		    			<div class=h1xiang> <p id="sender"></p></div>
	    			</div>
	    			
	    			
	    			
    			</div>
    			<div class="consigneeDiv">
	    				<div class="hang1">
				    		<div class=h1>
					    		<span class="ruFont">Станция назначения</span>目的地:</div>
					    		<div class=h1xiang><p id="destName"></p></div>
			    			</div>
			    		<div class="hang1">
			    			<div class=h1>
				    			<span class="ruFont">NO. Получателя </span>收货人编号:</div>
				    			<div class=h1xiang><p id="custId"></p></div>
		    			</div>
		    			<div class="hang1">
			    			<div class=h2>
			    			<span class="ruFont">Получатель</span>收货人:</div>
			    			<div class=h2xiang><span class="custFont"><p id="custName"></p></span></div>
		    			</div>
		    			<div class="hang1">
					    	<div class=h2>
					    	<span class="ruFont">Тел.:</span>收货电话:</div>
					    	<div class=h2xiang><p id="custTel"></p></div>
				    	</div>
    			</div>
    		</div>
    		<div class="conInfoRight">
    			<div class="logo">
    				<center><img id="logo" src="<%=basePath%>resources/image/logo.jpg" /></center>
    			</div>
    			<div class="hang1">
    				<center><span class="billFont">中集信达<br/>国际物流有限公司</span></center>
    			</div>
    			<div class="hang1">
    			<center><span class="redFont">258库房</span></center>
    				
    			</div>
    			
    			<div>
    				<div class="hang2">
    				<left><span class="dataFont">北京总部</span></left>
    				</div>
    				<div>
	    				<div class="h3">
	    				<span class="dataFont">联系电话：</span>
	    					
		    			</div>
		    			<div class="h3xiang">
		    			<center><span class="dataFont">010-85094073</span></center>
	    					
		    			</div>
		    			<div class="h3">
		    			<span class="dataFont">传真：</span>
		    			</div>
		    			<div class="h3xiang">
		    			<center><span class="dataFont">010-85690720</span></center>
	    					
		    			</div>
    				</div>
    			</div>
    		</div>
    		
    	
    	</div>
    	<div class="moscInfo">
    		<div class="h4">
    			<p><span class="ruFont">Контакт в России：</span></br>驻俄办事处电话：</p>
    		</div>
    		<div class="h4xiang">
    			<p id="destTel"><span class="dataFont"></p>
    		
    		</div>
    	</div>
    	
    	
    	<div class="itemInfo">
    		<div class=itemLeft>
	    		<div class="itemTitle">
			    	<div class="h5">
				    	<center><span class="ruFont">К-во Мест</span></br>(包数)</center>
				    	</div>
				    	<div class="h5">
				    	<center>	<span class="ruFont">Вес</span></br>(重量kg)</center>
				    	</div>
			    	<div class="h5">
				    	<center>	<span class="ruFont">Объём</span></br>(体积m³)</center>
				    	</div>
			    	<div class="h6">
				    	<center>	<span class="ruFont">Наименования</span></br>(品名)</center>
				    	</div>
				    	<div class="h5">
				    	<center>	<span class="ruFont">К-во штук </span></br>(小件数)</center>
				    	</div>
			    </div>
			    <div class="items">
			    	<div class="h7"><b><p id="pics"></p></b></div>
			    	<div class="h7"><b><p id="weight"></p></b></div>
			    	<div class="h7"><b><p id="volumu"></p></b></div>
			    	<div class="h8"><b><p id="goods"></p></b></div>
			    	<div class="h7"><b><p id="quantity"></p></b></div>
			    	
			    </div>
			     <div class="itemTotal">
			    	<div class="h9"><span class="ruFont">Цена за 1кг</span></br>运价</div>
			    	<div class="h9"> <b><p id="price"></p></b></div>
			    	<div class="h10"><span class="ruFont">Итого</span><br>总运费$</div>
			    	<div class="h11"><b><p id="total"></p></b> </div>
			    </div>
			    
			    
		    </div>
		    <div class="itemRight">
		    	<div class="hang3">
		    		<center>款项$</center>
		    	</div>
		    	<div >
		    		<div class="h12">
			    		<div class="h12g">
						<span class="ruFont">Аванс</span></br>(国内运费$)
				    	</div>
				    	<div class="h12g">
				    		<span class="ruFont">Упаковка</span></br>(包装费$)
				    	</div>
				    	<div class="h12g">
				    		<span class="ruFont">стоимость товара</span></br>(货值$)
				    	</div>
				    	<div class="h12g">
				    		<span class="ruFont">страховой взнос</span></br>（保价费率%）
				    	</div>
				    	<div class="h12g">
				    		<span class="ruFont">Страховка</span></br>(保险费$)
				    	</div>
				    	<div class="h12g">
				    		<span class="ruFont">Компенсация</span></br>(减赔偿$)
				    	</div>
		    		</div>
		    		<div class="h13" >
		    			<div class="h13x">
							<b><p id="advanced"></p></b>
				    	</div>
				    	<div class="h13x">
				    		<b><p id="packfee"></p></b>
				    	</div>
				    	<div class="h13x">
				    		<b><p id="worth"></p></b>
				    	</div>
				    	<div class="h13x">
				    		<b><p id="worthRate"></p></b>
				    	</div>
				    	<div class="h13x">
				    		<b><p id="insurance"></p></b>
				    	</div>
				    	<div class="h13x">
				    		<b><p id="indemnity"></p></b>
				    	</div>
		    		</div>
		    	</div>
		    </div>
		    
	    </div>
	    <div class="bottom">
	    	<div class="remarkInfo">
				
					<h5>
					<span class="ruFont">Утери груза</span>丢货: </br>
					<span class="ruFont">В случаях утери грузов , подписанный акт выдачи товара от нашего представителя в Москве является свидетельством для компенсации. </span>
					</br></br>
					
					</br> 如有丢货请及时与我莫办联系确认，我公司以莫办签单为准。
					</h5>
				
			</div>
			<div class="dateInfo">
					<span id="signdate">日期：<p id="sddate"></p></sapn>
			</div>
			
	    
	    </div>
	    
		    	
    	
    	
    	</div>
    
   
  </body>
</html>
