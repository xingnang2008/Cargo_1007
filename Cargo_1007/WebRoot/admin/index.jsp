<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
        
    <div data-options="region:'west',title:'菜单',split:true" style="width:200px;">
    <div id="menu" class="easyui-accordion" data-options="fit:true">   <!--适应最外层-->
    			<div title="运单管理">
				<ul>
					<li><a href="#" title="Waybill/Waybill_list.jsp">运单明细</a></li>
					<li><a href="#" title="Waybill/Waybill_accountInput.jsp">运单结算</a></li>
					<li><a href="#" title="Waybill/Waybill_downloadBitchReport.jsp">批次报表</a></li>
					<li><a href="#" title="Mark/Mark_list.jsp">商品类别</a></li>
				</ul>
				</div>
				<div title="配仓操作">
				<ul>
					<li><a href="#" title="Waybill/Waybill_marshall.jsp">配仓管理</a></li>					
					<li><a href="#" title="Waybill/Waybill_marshallEd.jsp">装机管理</a></li>
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
				
				<div title="财务管理">
				<ul>
					<li><a href="#" title="CashContent.jsp">款项记录</a></li>
					
					<li></li>
				</ul>
				</div>
				<div title="退出">
				<ul>
					<li><a href="#" title="/j_spring_security_logout">退出</a></li>
					
					<li></li>
				</ul>
				</div>
		</div></div>   
    <div data-options="region:'center',title:'工作区'" style="padding:5px;background:#eee;">
	    <div id="tt" class="easyui-tabs" data-options="fit:true">
	    	 
	    </div>
    </div>   
</body>  


</html>
