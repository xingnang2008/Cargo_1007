package com.cargo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cargo.dao.RebateDao;
import com.cargo.model.Company;
import com.cargo.model.Customer;
import com.cargo.model.Rebate;
import com.cargo.model.Rebategoods;
import com.cargo.model.Waybill;

@Component
public class RebateService {
		@Resource
		private RebateDao rebateDao;
		@Resource
		private CompanyService companyService;
		@Resource
		private CustomerService customerService;
		@Resource
		private RebategoodsService rebategoodsService;
		@Resource
		private WaybillService waybillService;
		
		

		public void save(Rebate rebate){
			this.rebateDao.save(rebate);
		}
		
		public void delete(Rebate rebate){
			this.rebateDao.delete(rebate);
		}
		public void update(Rebate rebate){
			this.rebateDao.update(rebate);
		}
		public List<Rebate> findAll(){
			return this.rebateDao.findAll();
			
		}
		public Rebate findById(java.lang.Integer id) {
			return this.rebateDao.findById(id);
		}
		public void deleteByIds(String ids){
			this.rebateDao.deleteByIds(ids);
		}
		public List listByIds(String ids){
			return this.rebateDao.listByIds(ids);
		}
		
		public List<Rebate> listByCustId(String cust_id,Integer page,Integer rows){
			return(this.rebateDao.listCustId(cust_id, page, rows));
		}
		public Long getCountByCustId(String cust_id){
			return(this.rebateDao.getCountByCustId(cust_id));
		}
		public Map<String,Object>find(String custName,String company,String bitch,Date beginTime,Date endTime,Integer page,Integer rows){
			return this.rebateDao.find(custName, company, bitch, beginTime, endTime, page, rows);
		}

		public CompanyService getCompanyService() {
			return companyService;
		}

		public void setCompanyService(CompanyService companyService) {
			this.companyService = companyService;
		}
		

		public RebateDao getRebateDao() {
			return rebateDao;
		}

		public void setRebateDao(RebateDao rebateDao) {
			this.rebateDao = rebateDao;
		}

		public CustomerService getCustomerService() {
			return customerService;
		}

		public void setCustomerService(CustomerService customerService) {
			this.customerService = customerService;
		}

		public RebategoodsService getRebategoodsService() {
			return rebategoodsService;
		}

		public void setRebategoodsService(RebategoodsService rebategoodsService) {
			this.rebategoodsService = rebategoodsService;
		}

		public WaybillService getWaybillService() {
			return waybillService;
		}

		public void setWaybillService(WaybillService waybillService) {
			this.waybillService = waybillService;
		}

		//实现核销单据的excel导入
		public String checkExcel(File excelFile,String excelFileFileName) throws Exception{
			String returnMark="";
			Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileFileName);
			Sheet sheet = book.getSheetAt(0);
			Row ros = sheet.getRow(0);
			if(!ros.getCell(0).getStringCellValue().equals("日期")
					||!ros.getCell(1).getStringCellValue().equals("单位名称")
					||!ros.getCell(2).getStringCellValue().equals("海关编码")
					||!ros.getCell(3).getStringCellValue().equals("合同号")
					||!ros.getCell(4).getStringCellValue().equals("贸易类型")
					||!ros.getCell(5).getStringCellValue().equals("货源地")
					||!ros.getCell(6).getStringCellValue().equals("毛重")
					||!ros.getCell(7).getStringCellValue().equals("金额")
			){
				
				returnMark="格式不对，请下载模板表格";
				
			}else if(sheet.getLastRowNum()<=0){
				returnMark="这是一个空的模板";
			}else{
				for(int i=1;i<=sheet.getLastRowNum();i++){
					ros = sheet.getRow(i);
					if(null==ros.getCell(0)){
						returnMark+=""+(i+1)+"行，第1列<日期>不能为空！";
						continue;
					}else if(null ==ros.getCell(1)){
						returnMark+=""+(i+1)+"行，第2列<单位名称>不能为空！";
						continue;
					}else if(ros.getCell(6).getCellType()!= Cell.CELL_TYPE_NUMERIC){
						returnMark+=""+(i+1)+"行，第6列<毛重>不是数值类型！";
						continue;
					}else if(ros.getCell(7).getCellType()!= Cell.CELL_TYPE_NUMERIC){
						returnMark+=""+(i+1)+"行，第7列<金额>不是数值类型！";
						continue;
					}else if(ros.getCell(8).getCellType()!= Cell.CELL_TYPE_NUMERIC){
						returnMark+=""+(i+1)+"行，第8列<包数>不是数值类型！";
						continue;
					}
				}
			}
			System.out.println(returnMark);
			return returnMark;
			
			
		}
		public String saveInputExcel(File excelFile,String excelFileName) throws FileNotFoundException {	
			Date date = new Date();
			Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileName);
			Sheet sheet = book.getSheetAt(0);
			Row ros = sheet.getRow(1);
			for(int i=1;i<=sheet.getLastRowNum();i++){
				ros=sheet.getRow(i);
				Rebate rebate = new Rebate();
				
				Company company=null;
				Customer customer = null;
				date=HSSFDateUtil.getJavaDate(ros.getCell(0).getNumericCellValue());
				
				if(this.companyService.isCompanyExist(this.getValue(ros.getCell(1))).size()>0){
					company = (Company)this.companyService.isCompanyExist(this.getValue(ros.getCell(1))).get(0);
				}else{
				company = new Company();
				company.setCompany(this.getValue(ros.getCell(1)));
				company.setCompanyCode(this.getValue(ros.getCell(2)));
				this.companyService.save(company);
				}
				if(this.customerService.isCustomerExist(this.getValue(ros.getCell(9))).size()>0){
					
					customer = (Customer)this.customerService.findByName(this.getValue(ros.getCell(9)));
				}else{
					customer = new Customer();
					customer.setName(this.getValue(ros.getCell(9)));
					customer.setCustId(this.getValue(ros.getCell(8)));
					customer.setTelphone(this.getValue(ros.getCell(10)));
				this.customerService.save(customer);
				}
				rebate.setSddate(date);
				rebate.setCompany(company.getCompany());
				rebate.setCompanyId(company.getId());
				rebate.setCompanyCode(company.getCompanyCode());
				rebate.setContract(this.getValue(ros.getCell(3)));
				rebate.setTradeType(this.getValue(ros.getCell(4)));
				rebate.setSource(this.getValue(ros.getCell(5)));
				rebate.setGrossWeight(Double.parseDouble(this.getValue(ros.getCell(6))));
				rebate.setPackages(Integer.parseInt(this.getValue(ros.getCell(7))));
				rebate.setCustId(customer.getCustId());
				rebate.setTelphone(customer.getTelphone());
							
				this.save(rebate);
			}
			
			return "";
		}
		public Workbook createWorkbook(InputStream is,String excelFileName){
			try {
				if(excelFileName.toLowerCase().endsWith("xls")){
					return new HSSFWorkbook(is);
				}
				if(excelFileName.toLowerCase().endsWith(".xlsx")){
					return new XSSFWorkbook(is);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		//导出Excel
		
		public InputStream getInputStream(List<Rebate> list){
			InputStream is = null;
			Workbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet("导出核销信息");
			sheet.setColumnWidth(1, 2500);
			sheet.setColumnWidth(2, 7500);
			sheet.setColumnWidth(3, 3000);
			sheet.setColumnWidth(4, 5000);
			sheet.setColumnWidth(13, 7000);
			sheet.setColumnWidth(16, 3000);
			sheet.setColumnWidth(17, 3000);
			sheet.setColumnWidth(18, 7000);		
			//--样式设置
			HSSFCellStyle headerStyle = (HSSFCellStyle) book.createCellStyle();// 创建标题样式  
			headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
			headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中  
			headerStyle.setWrapText(true);
			HSSFFont headerFont = (HSSFFont) book.createFont(); //创建字体样式  
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
			headerFont.setFontName("Times New Roman");  //设置字体类型  
			headerFont.setFontHeightInPoints((short) 10);    //设置字体大小  
			headerStyle.setFont(headerFont);    //为标题样式设置字体样式  
			headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			
			HSSFCellStyle cellStyle = (HSSFCellStyle) book.createCellStyle();// 创建标题样式  
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中 
			cellStyle.setWrapText(true);
			HSSFFont cellFont = (HSSFFont) book.createFont(); //创建字体样式 	
			cellFont.setFontName("Times New Roman");  //设置字体类型  
			cellFont.setFontHeightInPoints((short) 10);    //设置字体大小  
			cellStyle.setFont(cellFont); 
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			
			Row row = sheet.createRow(0);
			
			HSSFCell cell =(HSSFCell) row.createCell(0);
			cell.setCellValue("编号");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(1);
			cell.setCellValue("日期");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(2);
			cell.setCellValue("单位名称");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(3);
			cell.setCellValue("合同号");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(4);
			cell.setCellValue("品名");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(5);
			cell.setCellValue("数量");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(6);
			cell.setCellValue("净重");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(7);
			cell.setCellValue("单价");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(8);
			cell.setCellValue("金额");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(9);
			cell.setCellValue("贸易类型");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(10);
			cell.setCellValue("包数");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(11);
			cell.setCellValue("毛重");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(12);
			cell.setCellValue("材质");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(13);
			cell.setCellValue("票号");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(14);
			cell.setCellValue("来源");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(15);
			cell.setCellValue("货源地");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(16);
			cell.setCellValue("海关编码");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(17);
			cell.setCellValue("商品编码");
			cell.setCellStyle(headerStyle);
			cell =(HSSFCell) row.createCell(18);
			cell.setCellValue("联系人");
			cell.setCellStyle(headerStyle);	
			
			
			
				int i=1;
				for(int j=0;j<list.size();j++){
					Rebate rebate = list.get(j);				
					
					String bills="";
					
					List<Waybill> waybills =waybillService.findByRebateId(rebate.getId());
					System.out.println("waybills:"+waybills.size());
					if(waybills.size()>=1){
						for(Waybill w : waybills){
							bills+=w.getWaybill();
							bills+="\n";
						}
						bills =bills.substring(0, bills.lastIndexOf("\n"));
					}
					
					
					row = sheet.createRow(i);
					cell =(HSSFCell) row.createCell(0);
					cell.setCellValue(rebate.getId());
					cell.setCellStyle(cellStyle);	
					cell =(HSSFCell) row.createCell(1);
					cell.setCellValue(rebate.getSddate().toString());
					cell.setCellStyle(cellStyle);	
					cell =(HSSFCell) row.createCell(2);
					cell.setCellValue(rebate.getCompany());
					cell.setCellStyle(cellStyle);	
					cell =(HSSFCell) row.createCell(3);
					cell.setCellValue(rebate.getContract());
					cell.setCellStyle(cellStyle);	
					
					cell =(HSSFCell) row.createCell(9);
					cell.setCellValue(rebate.getTradeType());
					cell.setCellStyle(cellStyle);	
					cell =(HSSFCell) row.createCell(10);
					cell.setCellValue(rebate.getPackages());
					cell.setCellStyle(cellStyle);	
					cell =(HSSFCell) row.createCell(11);
					cell.setCellValue(rebate.getGrossWeight());
					cell.setCellStyle(cellStyle);	
						
					cell =(HSSFCell) row.createCell(13);
					cell.setCellValue(bills);
					cell.setCellStyle(cellStyle);	
				
					cell =(HSSFCell) row.createCell(15);
					cell.setCellValue(rebate.getSource());
					cell.setCellStyle(cellStyle);	
					cell =(HSSFCell) row.createCell(16);
					cell.setCellValue(rebate.getCompanyCode().toString());
					cell.setCellStyle(cellStyle);	
					
					cell =(HSSFCell) row.createCell(18);
					cell.setCellValue(rebate.getCustId()+" "+rebate.getTelphone());
					cell.setCellStyle(cellStyle);					
				
					
					List<Rebategoods> goodsList = rebategoodsService.listByRebateId(rebate.getId());
					int gSize = goodsList.size();
					if(gSize>=1){
						for(int m=0;m<goodsList.size();m++){
							Rebategoods rebategoods = goodsList.get(m);
							
							cell =(HSSFCell) row.createCell(4);
							cell.setCellValue(rebategoods.getGoods());
							cell.setCellStyle(cellStyle);
							cell =(HSSFCell) row.createCell(5);
							cell.setCellValue(rebategoods.getQuantity());
							cell.setCellStyle(cellStyle);
							cell =(HSSFCell) row.createCell(6);
							cell.setCellValue(rebategoods.getNetWeight());
							cell.setCellStyle(cellStyle);
							cell =(HSSFCell) row.createCell(7);
							cell.setCellValue(rebategoods.getPrice());
							cell.setCellStyle(cellStyle);
							cell =(HSSFCell) row.createCell(8);
							cell.setCellValue(rebategoods.getAmount());
							cell.setCellStyle(cellStyle);
							cell =(HSSFCell) row.createCell(12);
							cell.setCellValue(rebategoods.getMaterial());
							cell.setCellStyle(cellStyle);
							cell =(HSSFCell) row.createCell(17);
							cell.setCellValue(rebategoods.getHsCode().toString());
							cell.setCellStyle(cellStyle);
						
						
							i++;
							if(m<goodsList.size()){
								row = sheet.createRow(i);
							}
							
						}
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 1, 1));
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 3, 3));
						
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 9, 9));
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 10, 10));
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 11, 11));
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 13, 13));
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 14, 14));
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 15, 15));
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 16, 16));
						sheet.addMergedRegion(new CellRangeAddress(i-gSize, i-1, 18, 18));
					}else{
						i++;
					}
					
				}
			
			File file = new File("核销单.xls");
			OutputStream os;
			try {
					os = new FileOutputStream(file);
					book.write(os);
					os.close();
					is = new FileInputStream(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return is;
		}
		
		//解决excel类型问题，获得数值  
	    public  String getValue(Cell cell) {  
	        String value = "";  
	        if(null==cell){  
	            return value;  
	        }  
	        switch (cell.getCellType()) {  
	        //数值型  
	        case Cell.CELL_TYPE_NUMERIC:  
	            if (HSSFDateUtil.isCellDateFormatted(cell)) {  
	                //如果是date类型则 ，获取该cell的date值  
	                Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());  
	                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	                value = format.format(date);;  
	            }else {// 纯数字  
	                BigDecimal big=new BigDecimal(cell.getNumericCellValue());  
	                value = big.toString();  
	                //解决1234.0  去掉后面的.0  
	                if(null!=value&&!"".equals(value.trim())){  
	                     String[] item = value.split("[.]");  
	                     if(1<item.length&&"0".equals(item[1])){  
	                         value=item[0];  
	                     }  
	                }  
	            }  
	            break;  
	            //字符串类型   
	        case Cell.CELL_TYPE_STRING:  
	            value = cell.getStringCellValue().toString();  
	            break;  
	        // 公式类型  
	        case Cell.CELL_TYPE_FORMULA:  
	            //读公式计算值  
	            value = String.valueOf(cell.getNumericCellValue());  
	            if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串  
	                value = cell.getStringCellValue().toString();  
	            }  
	            break;  
	        // 布尔类型  
	        case Cell.CELL_TYPE_BOOLEAN:  
	            value = " "+ cell.getBooleanCellValue();  
	            break;  
	        // 空值  
	        case Cell.CELL_TYPE_BLANK:   
	            value = "";  
	            break;  
	        // 故障  
	        case Cell.CELL_TYPE_ERROR:   
	            value = "";  
	            break;  
	        default:  
	            value = cell.getStringCellValue().toString();  
	    }  
		    if("null".endsWith(value.trim())){  
		        value="";  
		    }  
	    	return value;  
	    }  
		
}
