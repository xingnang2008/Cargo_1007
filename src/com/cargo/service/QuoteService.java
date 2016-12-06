package com.cargo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import com.cargo.dao.QuoteDao;
import com.cargo.model.Bitch;
import com.cargo.model.Customer;
import com.cargo.model.Dest;
import com.cargo.model.Line;
import com.cargo.model.Procurator;
import com.cargo.model.Quote;
import com.cargo.model.Rater;
import com.cargo.model.Sender;
import com.cargo.model.Waybill;

@Component("quoteService")
public class QuoteService {
	
	
	private QuoteDao quoteDao;
	
	public QuoteDao getQuoteDao() {
		return quoteDao;
	}
	@Resource
	public void setQuoteDao(QuoteDao quoteDao) {
		this.quoteDao = quoteDao;
	}
	

	public void save(Quote bitch){
		this.quoteDao.save(bitch);
	}
	
	public void delete(Quote bitch){
		this.quoteDao.delete(bitch);
	}
	public void update(Quote bitch){
		this.quoteDao.update(bitch);
	}
	public List<Quote> findAll(){
		return this.quoteDao.findAll();
	}
	public void deleteByIds(String ids){
		this.quoteDao.deleteByIds(ids);
	}
	public List listByIds(String ids){
		return this.quoteDao.lsitByIds(ids);
	}
	public Map find(String product,String hsCodeRu){
		return this.quoteDao.find(product, hsCodeRu);
	}
	public Quote findByBH(String bh){
		return this.quoteDao.findByBH(bh);
	}
	//报价资料的excel检查
	public String checkExcel(File excelFile,String excelFileFileName) throws Exception{
		String returnMark="";
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);
		if(!ros.getCell(0).getStringCellValue().equals("NO")	
				||!ros.getCell(1).getStringCellValue().equals("наименование品名")
				||!ros.getCell(2).getStringCellValue().equals("материал材质")
				
				||!ros.getCell(3).getStringCellValue().equals("Код товара海关编码")
				||!ros.getCell(4).getStringCellValue().equals("Цена Суньфуньхе - Хабаровск, $/кг报价")
				||!ros.getCell(6).getStringCellValue().equals("дополнение备注")
				
		){
			
			returnMark="格式不对，请下载模板表格";
			
		}else if(sheet.getLastRowNum()<=0){
			returnMark="这是一个空的模板";
		}else{
			Map mapWaybill = new HashMap();
			int j =0;
			for(int i=2;i<=sheet.getLastRowNum();i++){
				ros = sheet.getRow(i);
				if(null==ros.getCell(0)){
					returnMark+=""+(i+1)+"行，第1列<NO>不能为空！ ";
					continue;
				}else if(null==ros.getCell(1)){
					returnMark+=""+(i+1)+"行，第2列<品名>不能为空！ ";
					continue;
				}			
							
				else if(ros.getCell(4).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第5列<报价>不是数值类型！";
					continue;
				}
				
				if(mapWaybill.get(this.getValue(ros.getCell(0)))==null){
					mapWaybill.put(this.getValue(ros.getCell(0)), this.getValue(ros.getCell(0)));
				}else{
					returnMark+="\n 第"+(i+1)+"行编号有重复，编号是： "+this.getValue(ros.getCell(0));
				}
			}
		}
		System.out.println(returnMark);
		return returnMark;
	}
	
	public String updateInputExcel(File excelFile,String excelFileName) throws FileNotFoundException {	
		String errorString="";		
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(2);		
		for(int i=2;i<=sheet.getLastRowNum();i++){
			ros=sheet.getRow(i);
			Quote quote = this.findByBH(this.getValue(ros.getCell(0)));
			if(quote != null){				
				quote.setProduct(getValue(ros.getCell(1)));
				quote.setMetarial(getValue(ros.getCell(2)));				
				quote.setHsCodeRu(getValue(ros.getCell(3)));
				quote.setPrice(Double.parseDouble(getValue((ros.getCell(4)))));
				quote.setTransType(getValue(ros.getCell(5)));
				quote.setRemarks(getValue(ros.getCell(6)));
				quote.setIsUse(Integer.parseInt(getValue(ros.getCell(7))));
				quote.setInspection(true);				
				update(quote);
			}else{
				quote = new Quote();
								
				quote.setBh(this.getValue(ros.getCell(0)));
				quote.setProduct(getValue(ros.getCell(1)));
				quote.setMetarial(getValue(ros.getCell(2)));				
				quote.setHsCodeRu(getValue(ros.getCell(3)));
				quote.setPrice(Double.parseDouble(getValue((ros.getCell(4)))));
				quote.setTransType(getValue(ros.getCell(5)));
				quote.setRemarks(getValue(ros.getCell(6)));
				quote.setIsUse(Integer.parseInt(getValue(ros.getCell(7))));
				quote.setInspection(true);
								
				save(quote);
				
			}
			}
		return errorString;
	}
	public InputStream getDownReport(){
		List<Quote> list = this.quoteDao.findAll();
		String sheetName="报价表";
		String titleName="258 КАРГО 货物报价表   北京Пекин — 莫斯科Москва";
		
		InputStream is = null;
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet(sheetName);
		//--页边距设置
		HSSFPrintSetup ps = (HSSFPrintSetup) sheet.getPrintSetup();
        ps.setLandscape(true); //打印方向，true:横向，false:纵向
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
        sheet.setMargin(HSSFSheet.BottomMargin, (double)0.5); //页边距（下）
        sheet.setMargin(HSSFSheet.LeftMargin, (double)0.1); //页边距（左）
        sheet.setMargin(HSSFSheet.RightMargin, (double)0.1); //页边距（右）
        sheet.setMargin(HSSFSheet.TopMargin, (double)0.5); //页边距（上）
        sheet.setHorizontallyCenter(true); //设置打印页面为水平居中
        //sheet.setVerticallyCenter(true); //设置打印页面为垂直居中
      //--列宽设置
        sheet.setColumnWidth(0, 2900);
		sheet.setColumnWidth(1, 20000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 4500);
		sheet.setColumnWidth(6, 7000);
		sheet.setColumnWidth(7, 2200);
		
		
		

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
		
		HSSFCellStyle titleStyle = (HSSFCellStyle) book.createCellStyle();// 创建标题样式  
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中  
		HSSFFont titleFont = (HSSFFont) book.createFont(); //创建字体样式  		
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
		titleFont.setFontName("Times New Roman");  //设置字体类型  
		titleFont.setFontHeightInPoints((short) 14);    //设置字体大小  
		titleStyle.setFont(titleFont);    //为标题样式设置字体样式  
		
		HSSFCellStyle cellStyle = (HSSFCellStyle) book.createCellStyle();// 创建单元格样式  
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
		
		
		HSSFCellStyle dateCellStyle = (HSSFCellStyle) book.createCellStyle();// 创建<日期>单元格样式  
		dateCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
		dateCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中 
		dateCellStyle.setWrapText(true);		
		dateCellStyle.setFont(cellFont); 
		dateCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
		dateCellStyle.setDataFormat(df);
		
		
		Row row = sheet.createRow(0);
		
		
		HSSFCell cell =(HSSFCell) row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
		
		row.getCell(0).setCellValue(titleName);		
		row.getCell(0).setCellStyle(titleStyle);
		
		row = sheet.createRow(1);
		row.setHeight((short) 600);//目的是想把行高设置成400px
		cell = (HSSFCell) row.createCell(0);
		cell.setCellValue("NO");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(1);
		cell.setCellValue("наименование品名");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(2);
		cell.setCellValue("материал材质");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(3);
		cell.setCellValue("Код товара海关编码");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(4);
		cell.setCellValue("Цена Суньфуньхе - Хабаровск, $/кг报价");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(5);
		cell.setCellValue("运输方式");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(6);
		cell.setCellValue("дополнение备注");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(7);
		cell.setCellValue("是否可用（0不可收，1可收，2待定）");
		cell.setCellStyle(headerStyle);
		
			
		
		
			int i=2;
			
			for(int j=0;j<list.size();j++){
				Quote q = list.get(j);				
				
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue(q.getBh());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(q.getProduct());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(q.getMetarial());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(q.getHsCodeRu());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(q.getPrice());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue(q.getTransType());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue(q.getRemarks());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue(q.getIsUse());
				cell.setCellStyle(cellStyle);
				
				i++;
			}
						
		File file = new File("运单.xls");
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
            	DecimalFormat df = new DecimalFormat("0.00");
            	DecimalFormat dfz = new DecimalFormat("0");
                BigDecimal big=new BigDecimal(cell.getNumericCellValue()); 
                value = df.format(big);
                
                //解决1234.0  去掉后面的.0  
                if(null!=value&&!"".equals(value.trim())){ 
                     String[] item = value.split("[.]"); 
                    
                     if(1<item.length&&"00".equals(item[1])){  
                         value=dfz.format(big);  
                         
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
