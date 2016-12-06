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
				category: $('#category').combobox('getValue'),
				products: $('#products').val(), 
				transType: $('#transType').val()			
			});
		});	
		$('#btnReset').click(function(){
			$('#category').combobox('setValue',""),
			$('#products').textbox("clear"),
			$('#transType').textbox("clear")
			
		});	
		$('#dg').datagrid({    
				//请求的url地址
			    url:'HuiQuote/HuiQuote-find.action', 
			    queryParams :{
		    		category:'category',
		    		products:'products',
		    		transType:'transType'
				    },
			   loadMsg:'请等待...',
				
				fit:true,
				striped:true,//隔行换色——斑马线				
				nowrap:false, //数据同行显示
				//自动适应列，如设为true则不会出现水平滚动条，在演示冻结列此参数不要设置
				fitColumns:true,
				//单行选择，全选功能失效
				singleSelect:false,
				
				//排序
				sortName:'id',
				sortOrder:'desc',
				remoteSort:false,
				//同列属性，冰结在最左侧
				rowStyler: function(index,row){
						if (row.isUse ==0){
							return 'background-color:#CFCFCF;color:#CD3700;';
						}
						
					},
				frozenColumns:[[
					{field:'z',checkbox:true},
					{field:'id',title:'编号',width:50}
					
				]],
				
				
			    columns:[[ 			      
					        {field:'category',title:'分类 категория',width:150},
					        {field:'products',title:'品名наименование',width:150,align:'left'} ,	
					        {field:'price',title:'报价Цена',width:80,align:'center'} ,
					        {field:'worthRate',title:'保率ставка страхования',width:80,align:'center'} ,
					        {field:'yiBao',title:'义保номинальная страховка',width:50,align:'center'} ,
					        {field:'days',title:'承诺运期срок доставки',width:80,align:'center'} ,
					        {field:'outDays',title:'灭失期срок потери',width:80,align:'center'} ,
					        {field:'transType',title:'运输方式 вид отправки',width:100,align:'center'} ,
					        {field:'isUse',title:'是否可收', 
						        formatter:function(value,row,index){ 
					        	switch(value){
				        		case 0: return "不收нельзя";
				        		break;
				        		case 1: return "可收можно";
				        		break;
				        		case 2: return "待定уточним";
				        		break;		
				        						        		
				        	}
		                    } , align:'right',width:80},
					        	
					        {field:'remarks',title:'备注дополнение',align:'left',width:150}
				]]    
			});
		
		
		
	});
	
	
	</script>

  </head>
  
  
<body>   
<div id="container">
	<div id="searchDiv">
			
				<div class="label">分类</div>
				<div class="hang">
							<select id="category" name="category" class="easyui-combobox" name="dept" style="width:120px">   
   								<option value="">全部 /ALL</option> 
   								<option value="普通服装 Одежды обычные">普通服装 Одежды обычные</option>  
   								<option value="杂货  Промтовары">杂货  Промтовары</option>  
   								<option value="鞋 Обуви">鞋 Обуви</option>  
   								<option value="小百货 Промтовары">小百货 Промтовары</option>  
   								<option value="大百货  Промтовары">大百货  Промтовары</option>  
   								<option value="皮衣、皮毛一体 Кожаная одежда / Куртка из натуральной кожи с подкладкой натурального меха">皮衣、皮毛一体 Кожаная одежда / Куртка из натуральной кожи с подкладкой натурального меха</option>  
   								<option value="手机 Мобильные телефоны">手机 Мобильные телефоны</option>  
   								 <option value="电子产品 Электронная продукция">电子产品 Электронная продукция</option>  
   								 <option value="带电池、带磁 с батареем, с магнитом">带电池、带磁 с батареем, с магнитом</option>  
   								 <option value="一类裘皮 Шуба (норка)">一类裘皮 Шуба (норка)</option>  
   								 <option value="二类裘皮 Шуба (лиса / кролик рекс / енот / хорёк )">二类裘皮 Шуба (лиса / кролик рекс / енот / хорёк )</option>  
   								 <option value="羊绒、羊反穿 Цигейка">羊绒、羊反穿 Цигейка</option>  
   								 <option value="箱包 Чемоданы">箱包 Чемоданы</option>  
   								 <option value="眼镜 Очки">眼镜 Очки</option>
   								 <option value="食品 Пищевые продукты">食品 Пищевые продукты</option>
   								 <option value="药品 Лекарство">药品 Лекарство</option>
   								 <option value="保健品  Продукты для поддержания здоровья">保健品  Продукты для поддержания здоровья</option>
   								 <option value="液体 Жидкость">液体 Жидкость</option>
   								 <option value="粉沫状物品 Продукция в порошковом виде">粉沫状物品 Продукция в порошковом виде</option>
							    
							</select>  

					</div>		
				<div class="label">品名</div>
				<div class="hang"><input type="text" id="products"  class="easyui-textbox"  name="products" style="width:120px" /></div>
				
				<div class="label">运输方式</div>
				<div class="hang"><input type="text"  id="transType"  class="easyui-textbox" name="transType" style="width:100px" /></div>
				<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				<a id="btnReset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">清空</a>
				
			
					
		
		</div>
		<hr/>
<div id="tableDG"><table id="dg"></table></div>

<div id="win" data-options="collapsible:false,minimizable:false,maxmizable:true,modal:true">   
        
</div>  
 </div>
</body>  


</html>
