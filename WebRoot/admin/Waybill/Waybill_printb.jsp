<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <%@include file="../head.jspf" %>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/Waybill/Waybill_printb.css">
	
	<script language="javascript" src="<%=basePath%>script/jqprint/jquery-1.4.4.min.js"></script> 
	<script language="javascript" src="<%=basePath%>script/jqprint/jquery.jqprint-0.3.js"></script>
	 <script language="JavaScript" >
	 $(document).ready(function(){
		 var rows =parent.$("#dg").datagrid("getSelections");
		$("#waybill").text(rows[0].waybill);
		$("#transType").text(rows[0].transType);
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
		$("#price").text(rows[0].price);
		$("#orderNo").text(rows[0].orderNo);
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
		if(advZ==0){advanced = advU }else{advanced=parseInt(advZ/exchangeRate)+advU}
		if(pkfZ==0){packfee = pkfU}else{packfee=parseInt(pkfZ/exchangeRate)+pkfU}
		
		$("#advanced").text(advanced);
		$("#packfee").text(packfee);
		



		
		});
	 function  a(){
	        $("#container").jqprint();
	    }
		
	    
	</script>
  </head>
  
  <body>   
    <input name="print" id="print" type="button"  value=" Print " class="btn" onclick='a()'/> 
     
    <div id="container">
    	<div id="top">
    			<div class="logo">
    				<center><img id="logo" src="<%=basePath%>image/logo.png" /></center>
    			</div>
    			<div class="title">
    				<center><span class="titleFont">北  京  中  集  信  达  国  际  物  流  有  限  公  司</span></center>
    			</div>
    			<div class="title1">
    				<center><span class="redFont">КАРГО 258</span></center>
    			</div>
	    
	    	
    	</div>
    	<div id="main">
    		<div id="conInfo1">
    			<div id="senderDiv">
    				<div class="hang1">
			    		<div class="label1"><span class="ruFont">Станция отправления</span>始发地:</div>
			    		<div class="h1xiang1"> <p ><span class="ruFont">Пекин </span>(北京)</p></div>
		    		</div>
		    	
	    			<div class="hang1">
		    			<div class="label1"><span class="ruFont">NO. Отправителя</span> 发货人编号:</div>
		    			<div class=h1xiang1> <p id=""></p></div>
	    			</div>
	    			<div class="hang1">
		    			<div class="label2"><span class="ruFont">Отправитель </span>发货人:</div>
		    			<div class="h1xiang2"> <p id="sender"></p></div>
	    			</div>
	    			<div class="hang1">
		    			<div class="label3"><span class="ruFont">Тел.:</span></div>
		    			<div class="h1xiang3"> <p id=""></p></div>
	    			</div>
	    			<div class="hang1">
		    			<div  class="label3"><span class="ruFont">факс:</span></div>
		    			<div class="h1xiang3"> <p id=""></p></div>
	    			</div>
	    			<div class="hang1">
		    			<div  class="label3">e-mail:</div>
		    			<div class="h1xiang3"> <p id=""></p></div>
	    			</div>
	    			
    			</div>
    			<div id="billInfo">
	    			<div class="rhang1">
	    				<div class ="rhlabel1"><span class="ruFont">Дата</span>(发货日期):</div>
	    				<div class ="rhxiang1"><p id="sddate"></p></div>
	    			</div>
	    			<div class="rhang2">
	    				<div class ="rhlabel2"><span class="ruFont">вид перевозки</span></br>(运输方式)：</div>
	    				<div class ="rhxiang2"><p id="transType"></p></div>
	    			</div>
	    			<div class="rhang2">
	    				<div class ="rhlabel2"><span class="ruFont">Маркировка</span></br>(货 号):</div>
	    				<div class ="rhxiang2"><p id="waybill"></p></div>
	    			</div>
    			</div>
    			
    			
    			
    			
    		</div>
    		<div id="conInfo2">
    			<div id="consigneeDiv">
	    				<div class="hang1">
				    		<div class="label1"><span class="ruFont">Станция назначения</span>目的地:</div>
					    		
			    			</div>
			    			<div class="hang1">
				    		<div class="label1"></div>
					    		<div class="h1xiang1"><p id="destName"></p></div>
			    			</div>
			    		<div class="hang1">
			    			<div class="label1">
				    			<span class="ruFont">NO. Получателя </span>收货人编号:</div>
				    			<div class="h1xiang1"><p id="custId"></p></div>
		    			</div>
		    			<div class="hang1">
			    			<div class="label2">
			    			<span class="ruFont">Получатель</span></br>收货人:</div>
			    			<div class="h1xiang2"><p id="custName"></p></div>
		    			</div>
		    			<div class="hang1">
					    	<div class="label3">
					    	<span class="ruFont">Тел.:</span></div>
					    	<div class="h1xiang3"><p id="custTel"></p></div>
				    	</div>
				    	<div class="hang1">
		    			<div  class="label3">e-mail:</div>
		    			<div class="h1xiang3"> <p id="custEmail"></p></div>
	    			</div>
    			</div>
    			
    			<div id="moscInfo">
    				<div class="rhang3">
	    				<div class ="rhlabel2"><span class="ruFont">Контакт в России：</span></br>驻俄办事处电话：</div>
	    				<div class ="rhxiang2"><p id="destTel"></p></div>
	    			</div>
	    			
	    			<div class="rhang31">
	    				<div class ="rhlabel3" >
							<h5>
							<span class="ruFont">Утери груза</span>丢货: </br>
							<span class="ruFont">В случаях утери грузов , подписанный акт выдачи товара от нашего представителя в Москве является свидетельством для компенсации. </span>
							</br> 如有丢货请及时与我莫办联系确认，我公司以莫办签单为准。
							</h5>
						</div>
	    			</div>
    			</div>
    		</div>
    		
    			<div id="conInfo3">
	    			<div class="goodsinfo">
	    				<center><span class="ruFont">Наименования</span></br>(品名)</center>
	    			</div>
	    			<div class="goodsinfo">
	    				<center><span class="ruFont">Материал</span></br>（材质）</center>
	    			</div>    			
		    		<div class="jzc">
		    			<center>	<span class="ruFont">К-во штук </span></br>(小件数)</center>
		    		</div>
		    		<div class="jzc">
		    			<center><span class="ruFont">К-во Мест</span></br>(包数)</center>
		    		</div>
		    		<div class="jzc">
		    			<center>	<span class="ruFont">Объём</span></br>(体积m³)</center>
		    		</div>
		    		<div class="jzc">
		    			<center>	<span class="ruFont">Вес</span></br>(重量kg)</center>
		    		</div>
		    		<div class="jzc">
		    		<center>	<span class="ruFont">Цена за 1кг</span></br>运价</center>
		    		</div>    		
    			</div>
    			<div class="rhang4">
	    			<div class="goodsinfo1"></div>
	    			<div class="goodsinfo1"></div>    			
		    		<div class="jzc1"></div>
		    		<div class="jzc1"></div>
		    		<div class="jzc1"></div>
		    		<div class="jzc1"></div>
		    		<div class="jzc1"></div>    		
    			</div>
    			<div class="rhang5">
	    			<div class="goodsinfo">
	    			<center><span class="ruFont">Аванс</span></br>(国内运费$)</center>
	    			</div>
	    			<div class="goodsinfo">
	    			<center>
	    			<span class="ruFont">Упаковка</span></br>(包装费$)
	    			</center>
	    			</div>
	    			<div class="items">
	    			<center>
	    			<span class="ruFont">стоимость товара</span></br>(货值$)
	    			</center>
	    			</div>
	    			<div class="items">
	    			<center>
	    			<span class="ruFont">страховой взнос</span></br>（保价费率%）
	    			</center>
	    			</div>
	    			<div class="jzc">
	    			<center>
	    				<span class="ruFont">Страховка</span></br>(保险费$)
	    			</center>
	    			</div>
	    			<div class="jzc">
	    			<center>
	    				<span class="ruFont">Компенсация</span></br>(减赔偿$)
	    			</center>
	    			</div>   		
    			</div>
    			<div class="rhang5">
	    			<div class="goodsinfo">
	    			<center>
	    			
	    			</center>
	    			</div>
	    			<div class="goodsinfo">
	    			<center>
	    			
	    			</center>
	    			</div>
	    			<div class="items">
	    			<center>
	    			
	    			</center>
	    			</div>
	    			<div class="items">
	    			<center>
	    			
	    			</center>
	    			</div>
	    			<div class="jzc">
	    			<center>
	    			
	    			</center>
	    			</div>
	    			<div class="jzc">
	    			<center>
	    			
	    			</center>
	    			</div>     		
    			</div>
    			<div class="rhang6">
    				<div class="totalInfo">
		    			<center>
		    			<span class="ruFont">Итого</span><br>总运费$
		    			</center>
	    			</div>
	    			<div class="totalInfo">
		    			<center>
		    			
		    			</center>
	    			</div>
    				<div class="remarks">
		    			<center>
		    			<span class="ruFont">Дополнение</br>(减赔偿说明)</br>Дополнение(备注)</span>
		    			</center>
	    			</div>
    				<div class="remarks1">
		    			<center>
		    			
		    			</center>
	    			</div>
    			</div>
    			<div class="rhang7">
    				<div class="call">
		    			<p vertical-align:left>
		    			
		    			</p>
	    			</div>
	    			<div class="exchangeRate">
		    			<div class="labelexchange">
							<p vertical-align:right>
								<span class="ruFont">Курс</span> $ (汇率):
							</p>
						</div>
		    			<div class="exchange">
		    					<p id="exchangeRate"></p>
		    			</div>
	    			</div>
    			</div>
    			<div class="rhang8">
    				<p vertical-align:right>
								    库房地址:  北京朝阳区京沈高速豆庄出口郎辛庄 联系人13522154999
							</p>
    			</div>
    			<div class="rhang9">
    				<div class="bottomleft">
		    			<p vertical-align:left>
		    			
		    			</p>
	    			</div>
	    			<div class="exchangeRate">
		    			<div class="labelexchange">
							<p vertical-align:right>
								
							</p>
						</div>
		    			<div class="exchange">
		    					<p id="exchangeRate"></p>
		    			</div>
	    			</div>
    			</div>
    	</div>
    </div>
   
   
  </body>
</html>
