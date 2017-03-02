<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>运输管理系统0.1</title>
	<%@include file="head.jspf" %>

	<script type="text/javascript">
	$(function(){
		$("a[title]").click(function(){
			var text = $(this).text();
			var href = $(this).attr("title");			
			if($("#tt").tabs("exists",text)){    <!--判断是否存在tabs-->				
				$("#tt").tabs('select',text);
			}else{
				//3:如果没有创建一个
				$("#tt").tabs("add",{
					title:text,
					closable:true,
					//href:'send_category_query.action'            //默认通过url加载远程页面仅仅是body部分
					content:'<iframe src='+href+' frameborder="0" width="100%" height="100%"> </iframe>'
					
				});
			}
		})
	});
	
	</script>
	<style type="text/css">
		body{
			text-align:center;
		}
		#menu {
			margin:auto; 
			width:200;
		}
		#menu ul{
			padding:0;   
			margin:auto; 
			list-style-position:inside;
		}
		#menu ul li{
			margin:auto;
			border-bottom:1px solid #fff;
			text-aglin:center;  
		}
		#menu ul li a{
			display:block;		
			background-color:#008792;
			color:#fff;
			padding:5px;   
			text-decoration:none;  
		}
		#menu ul li a:hover{
			background-color:#00a6ac;
		}
	</style>

  </head>
  
  
   <body class="easyui-layout">   
   <!-- 
    <div data-options="region:'north',title:'North Title',split:true" style="height:50px;">
    	<a href="<%=basePath%>j_spring_security_logout">退出</a>
    </div>    
    -->
    <div data-options="region:'west',title:'菜单',split:true" style="width:200px;">
    <div id="menu" class="easyui-accordion" data-options="fit:true">   <!--适应最外层-->
    		<sec:authorize access="hasRole('ROLE_USER')"> 
    			<div title="运单管理">
				<ul>
					<li><a href="#" title="../user/Waybill_list.jsp">运单查询</a></li>
					<li><a href="#" title="../user/Waybill_track.jsp">货物跟踪</a></li>
					<li><a href="#" title="../user/Track_list.jsp">到货明细</a></li>
				</ul>
				</div>
    		</sec:authorize>
    
    			<sec:authorize access="hasAnyRole('ROLE_SUPER','ROLE_ADMIN','ROLE_CAIWU')"> 
    			
    			<div title="运单管理">
				<ul>
					<li><a href="#" title="Waybill/Waybill_list.jsp">运单明细</a></li>
					<sec:authorize access="hasAnyRole('ROLE_SUPER','ROLE_ADMIN')"> 
					<li><a href="#" title="Waybill/Waybill_accountInput.jsp">运单结算</a></li>	
					</sec:authorize>				
					<li><a href="#" title="Waybill/Waybill_downloadBitchReport.jsp">批次报表</a></li>
					<li><a href="#" title="Mark/Mark_list.jsp">商品类别</a></li>
				</ul>
				</div>
				<div title="配仓操作">
				<ul>
					<li><a href="#" title="Waybill/Waybill_marshall.jsp">配仓管理</a></li>					
					<li><a href="#" title="Waybill/Waybill_downloadExcelCangdan.jsp">数据生成</a></li>
					<li><a href="#" title="Waybill/Waybill_channels.jsp#">绿色通道</a></li>	
				</ul>
				</div> 
    
				<div title="核销管理">
				<ul>
					<li><a href='#' title="Rebate/Rebate_list.jsp">核销登记</a></li>
				</ul>
				</div>
				<div title="运输管理">
				<ul>
					<li><a href="#" title="Bitch/Bitch_list.jsp">批次管理</a></li>					
					<li><a href="#" title="Line/Line_list.jsp">线路管理</a></li>
					<li><a href="#" title="Dest/Dest_list.jsp">目的地管理</a></li>
				</ul>
				</div>
				<div title="客户管理">
				<ul>
					<li><a href="#" title="Customer/Customer_list.jsp">客户管理</a></li>
					<li><a href="#" title="Rater/Rater_list.jsp">经办人管理</a></li>
					<li><a href="#" title="Sender/Sender_list.jsp">发货人管理</a></li>
					<li><a href="#" title="Procurator/Procurator_list.jsp">代理人管理</a></li>
					<li><a href="#" title="CustomerRelation/CustomerRelation_list.jsp">发货关系管理</a></li>			
					<li><a href="#" title="Rebatecustomer/Rebatecustomer_list.jsp">核销客户管理</a></li>
				</ul>
				</div>
				<div title="跟踪管理">
				<ul>
					<li><a href="#" title="Waybill/Waybill_track.jsp">货物跟踪</a></li>
					<li><a href="#" title="Indemnify/Indemnify_list.jsp">赔偿管理</a></li>
					<li><a href="#" title="Track/Track_applist.jsp">晚到赔偿</a></li>
					<li><a href="#" title="Track/Track_applistOut.jsp">外-晚到赔偿</a></li>
				</ul>
				</div>
				<div title="外结费用">
				<ul>
					<li><a href="#" title="Disburse/Disburse_list.jsp">外付款</a></li>	
					<li><a href="#" title="Disburse/Disburse_listFee.jsp">外付款对账</a></li>	
				</ul>
				</div>
				<div title="财务管理">
				<ul>
					
					<li><a href="#" title="Waybill/Waybill_fee.jsp">运单收支</a></li>
					<li><a href="#" title="Receipt/Receipt_list.jsp">收款管理</a></li>
					<li><a href="#" title="Receipt/Receipt_listGroup.jsp">收款明细</a></li>
					<li><a href="#" title="Receipt/Receipt_listByDate2.jsp">客户收款查询</a></li>
					<li><a href="#" title="Receipt/Receipt_listByDate.jsp">客户收款全部</a></li>
					<li><a href="#" title="Waybill/Waybill_listRaterFee.jsp">代理费</a></li>
					<li><a href="#" title="ProcuratorRecorders/ProcuratorRecorders_list.jsp">已结代理费</a></li>
					<li><a href="#" title="Waybill/Waybill_listAdvanceFee.jsp">垫付款</a></li>						
					<li><a href="#" title="AdvanceRecorders/AdvanceRecorders_list.jsp">已结垫付款</a></li>
					<li><a href="#" title="Waybill/Waybill_arrearages.jsp">欠款总计</a></li>
				
				</ul>
				</div>
				<div title="财务报表">
				<ul>
					<li><a href="#" title="Waybill/Waybill_raterReport.jsp">财务报表</a></li>
					<li><a href="#" title="Bitch/Bitch_totalList.jsp">批次毛利</a></li>	
					<li><a href="#" title="Waybill/Waybill_advancedReport.jsp">垫付款报表</a></li>
					<li><a href="#" title="Waybill/Waybill_allBitchReport.jsp">批次统计报表</a></li>
				
				</ul>
				</div>
    			</sec:authorize>
				<div title="东线报价管理">
				<ul>
					<li><a href="#" title="Quote/Quote_list.jsp">东线报价查询</a></li>
					<sec:authorize access="hasRole('ROLE_SUPER')">					
					<li><a href="#" title="Quote/Quote_uploadfile.jsp">东线报价更新</a></li>
					</sec:authorize>
					
				</ul>
				</div>
				<div title="西线报价管理">
				<ul>
					<li><a href="#" title="HuiQuote/HuiQuote_list.jsp">西线报价查询</a></li>
					<sec:authorize access="hasRole('ROLE_SUPER')">	
					<li><a href="#" title="HuiQuote/HuiQuote_uploadfile.jsp">西线报价更新</a></li>					
					</sec:authorize>
					
				</ul>
				</div>
				<sec:authorize access="hasRole('ROLE_SUPER')">
				<div title="用户管理">
				<ul>
					<li><a href="#" title="../security/User/User_list.jsp">用户管理</a></li>
				</ul>
				
				</div>
				</sec:authorize>
				<div title="退出">
				<ul>
					<li><a href="<%=basePath%>j_spring_security_logout">退出</a></li>
				</ul>
				</div>
		</div></div>   
    <div data-options="region:'center',title:'工作区'" style="padding:5px;background:#eee;">
	    <div id="tt" class="easyui-tabs" data-options="fit:true">
	    	 
	    </div>
    </div>   
</body>  


</html>
