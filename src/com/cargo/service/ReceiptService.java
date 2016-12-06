package com.cargo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cargo.dao.IndemnifyDao;
import com.cargo.dao.ReceiptDao;
import com.cargo.dao.TrackDao;
import com.cargo.dao.WaybillDao;
import com.cargo.model.Receipt;
import com.cargo.model.Track;
import com.cargo.model.Waybill;
import com.cargo.model.dto.RceiptByCustAndDate;

@Component
public class ReceiptService {
	private ReceiptDao receiptDao;
	private WaybillDao waybillDao;
	private IndemnifyDao indemnifyDao;
	private TrackDao trackDao;
	
	public WaybillDao getWaybillDao() {
		return waybillDao;
	}
	@Resource
	public void setWaybillDao(WaybillDao waybillDao) {
		this.waybillDao = waybillDao;
	}
	public ReceiptDao getReceiptDao() {
		return receiptDao;
	}
	@Resource
	public void setReceiptDao(ReceiptDao receiptDao) {
		this.receiptDao = receiptDao;
	}
	
	public IndemnifyDao getIndemnifyDao() {
		return indemnifyDao;
	}
	@Resource
	public void setIndemnifyDao(IndemnifyDao indemnifyDao) {
		this.indemnifyDao = indemnifyDao;
	}
	
	public TrackDao getTrackDao() {
		return trackDao;
	}
	@Resource
	public void setTrackDao(TrackDao trackDao) {
		this.trackDao = trackDao;
	}
	public void save(Receipt receipt){
		this.receiptDao.save(receipt);
		Waybill wb = waybillDao.findByWaybill(receipt.getWaybill());
		
		wb.setActualSum(receiptDao.countFeeByWaybill(receipt.getWaybill()).intValue());
			
		waybillDao.update(wb);
		
		
	}
	public void update(Receipt receipt){
		
		this.receiptDao.update(receipt);
		Waybill wb = waybillDao.findByWaybill(receipt.getWaybill());
		if(this.receiptDao.isexsitTrackByWaybill(receipt.getWaybill())){
			wb.setActualSum(receiptDao.countFeeByWaybill(receipt.getWaybill()).intValue());
		}else wb.setActualSum(0);
	
		waybillDao.update(wb);
	}
	public void delete(Receipt receipt){
		this.receiptDao.delete(receipt);
	}
	public void deleteByIds(String ids,String waybills) {
		this.receiptDao.deleteByIds(ids);
		String[] waybillss = waybills.split(",");
		for(int i=0;i<waybillss.length;i++){
			System.out.println("Indemnify Service: waybill.i :"+waybillss[i]);  //输出语句
			Waybill wayb  = waybillDao.findByWaybill(waybillss[i]);
			wayb.setActualSum(receiptDao.countFeeByWaybill(waybillss[i]).intValue());
			waybillDao.update(wayb);
		}
		
	}
	public Integer countFeeByWaybill(String waybill) {
		// TODO Auto-generated method stub
		return this.receiptDao.countFeeByWaybill(waybill);
	}
	public Receipt findById(java.lang.Integer id) {
		return this.receiptDao.findById(id);
	}
	public List findAll() {
		return this.receiptDao.findAll();
	}
	public Map findByCustAndMethod(String custName,String sender,String custId,Integer medthod,Date stdate,Date enddate){
		return this.receiptDao.findByCustAndMethod(custName, sender, custId, medthod, stdate, enddate);
	}	
	public Map find(String custId,String bitch,String waybill,String sender,String rater,String lineId,Date stdate,Date enddate){
		return this.receiptDao.find(custId, bitch, waybill, sender, rater, lineId, stdate, enddate);
	}
	//按日期 汇总收款
	public List<RceiptByCustAndDate> listByCustIdAndDate(){
		return receiptDao.listByCustIdAndDate();
	}
	//按客户号查询  按日期 汇总收款
	public List<RceiptByCustAndDate> listByCustIdAndDate(String sender){
		return receiptDao.listByCustIdAndDate(sender);
	}
	//实际批量收款操作
	public void updateWaybillsReceipt(String waybills , Date date){
		String[] waybillss = waybills.split(",");
		Waybill wayb = null;
		
		for(int i=0;i<waybillss.length;i++){
			System.out.println(waybillss[i]);  //--------------------
			wayb = waybillDao.findByWaybill(waybillss[i]);
			Integer indemFee =indemnifyDao.countFeeByWaybill(waybillss[i])!=null?indemnifyDao.countFeeByWaybill(waybillss[i]).intValue():0;
			Integer recFee =	this.receiptDao.countFeeByWaybill(waybillss[i])!=null?this.receiptDao.countFeeByWaybill(waybillss[i]):0;	
			Integer delayIndem = this.trackDao.countDelayIndemByWaybill(waybillss[i])!=null?this.trackDao.countDelayIndemByWaybill(waybillss[i]):0;	
			System.out.println("indemFee: "+indemFee);//--------------------
			System.out.println("recFee: "+recFee);//--------------------
			System.out.println("getTotal: "+wayb.getTotal());//--------------------
			Integer dd = wayb.getTotal()-indemFee-recFee-delayIndem;
		
			
			
			
			Receipt receipt = new Receipt();
				receipt.setBitch(wayb.getBitch());
				receipt.setCustId(wayb.getCustId());
				receipt.setCustName(wayb.getCustName());
				receipt.setFee(dd);
				receipt.setLineId(wayb.getLineId());
				receipt.setPayMethod(0);//默认是到付。
				receipt.setRater(wayb.getRaterName());
				receipt.setRdate(date);
				receipt.setRemarks("");
				receipt.setSender(wayb.getSender());
				receipt.setWaybill(wayb.getWaybill());
				receipt.setProcurator(wayb.getProcurator());
				save(receipt);
				
				wayb.setActualSum(receiptDao.countFeeByWaybill(waybillss[i]).intValue());
				waybillDao.update(wayb);
			}
		
	}
	

	//实现到货资料的excel导入
	public String checkExcel(File excelFile,String excelFileFileName) throws Exception{
		String returnMark="";
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);
		if(!ros.getCell(0).getStringCellValue().equals("收款日期")				
				||!ros.getCell(1).getStringCellValue().equals("票号")
				||!ros.getCell(2).getStringCellValue().equals("金额")
				||!ros.getCell(3).getStringCellValue().equals("收款方式")				
				||!ros.getCell(4).getStringCellValue().equals("备注")
				
		){
			
			returnMark="格式不对，请下载模板表格";
			
		}else if(sheet.getLastRowNum()<=0){
			returnMark="这是一个空的模板";
		}else{
			int j =0;
			for(int i=2;i<=sheet.getLastRowNum();i++){
				ros = sheet.getRow(i);
				Waybill waybill = waybillDao.findByWaybill(this.getValue(ros.getCell(1)));
				if(null==ros.getCell(0)){
					returnMark+=""+(i+1)+"行，第1列<日期>不能为空！ \n |";
					continue;
				}else if(null ==ros.getCell(1)){
					returnMark+=""+(i+1)+"行，第2列<票号>不能为空！\n |";
					continue;
				}
				else if(!waybillDao.isBillExsit(this.getValue(ros.getCell(1)))){					
					returnMark+=""+(i+1)+"行，第2列<票号>在数据库不存在 ！\n |";
					continue;
				}
				else if(ros.getCell(2).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<金额>不是数值类型！\n |";
					continue;
				}else if(ros.getCell(3).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<付款方式>不是数值类型！0：到付，1：正付 \n |";
					continue;
				}
				
			}
		}
		System.out.println(returnMark);
		return returnMark;
	}
	
	
	//到货资料的导入
	public String saveInputExcel(File excelFile,String excelFileName) throws FileNotFoundException {	
		String errorString="";
		Date rDate = new Date();		
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);		
		for(int i=2;i<=sheet.getLastRowNum();i++){
			ros=sheet.getRow(i);
			Receipt receipt = new Receipt();		
			rDate=HSSFDateUtil.getJavaDate(ros.getCell(0).getNumericCellValue());			
			
				Waybill waybill = waybillDao.findByWaybill(this.getValue(ros.getCell(1)));
				receipt.setWaybill(waybill.getWaybill());
				receipt.setRdate(rDate);
				
				receipt.setBitch(waybill.getBitch());
				receipt.setCustId(waybill.getCustId());
				receipt.setCustName(waybill.getCustName());
				receipt.setLineId(waybill.getLineId());
				receipt.setRater(waybill.getRaterName());
				receipt.setSender(waybill.getSender());
				
				receipt.setFee(Integer.parseInt(this.getValue(ros.getCell(2))));
				receipt.setPayMethod(Integer.parseInt(this.getValue(ros.getCell(3))));
				
				receipt.setRemarks(this.getValue(ros.getCell(4)));
				save(receipt);
			}
		return errorString;
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
}
