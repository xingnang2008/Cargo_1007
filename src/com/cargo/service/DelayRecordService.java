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

import com.cargo.dao.DelayRecordDao;
import com.cargo.dao.TrackDao;
import com.cargo.dao.WaybillDao;
import com.cargo.model.DelayRecord;
import com.cargo.model.Track;
import com.cargo.model.Waybill;

@Component
public class DelayRecordService {
	private DelayRecordDao delayRecordDao;
	private WaybillDao waybillDao;
	private TrackDao trackDao;
	
	public DelayRecordDao getDelayRecordDao() {
		return delayRecordDao;
	}

	@Resource
	public void setDelayRecordDao(DelayRecordDao delayRecordDao) {
		this.delayRecordDao = delayRecordDao;
	}

	public WaybillDao getWaybillDao() {
		return waybillDao;
	}
	@Resource
	public void setWaybillDao(WaybillDao waybillDao) {
		this.waybillDao = waybillDao;
	}

	public TrackDao getTrackDao() {
		return trackDao;
	}
	@Resource
	public void setTrackDao(TrackDao trackDao) {
		this.trackDao = trackDao;
	}

	public void save(DelayRecord delayRecord){
		delayRecordDao.save(delayRecord);
	}
	public void delete(DelayRecord delayRecord){
		this.delayRecordDao.delete(delayRecord);
	}
	public void update(DelayRecord delayRecord){
		delayRecordDao.update(delayRecord);
		Waybill waybill = waybillDao.findByWaybill(delayRecord.getWaybill());
		//waybill.setIndemnity(countFeeByWaybill(delayRecord.getWaybill()).intValue());
		waybillDao.update(waybill);
		
	}
	public List<DelayRecord> findAll(){
		return this.delayRecordDao.findAll();
	}
	public void updateApprovalDelayRecord(String ids,Integer status){
		this.delayRecordDao.approvalDelayRecord(ids, status);
		String[] idss = ids.split(",");
		for(int i=0;i<idss.length;i++){
			DelayRecord ind = this.delayRecordDao.findById(Integer.parseInt(idss[i]));
			Waybill wayb  = waybillDao.findByWaybill(ind.getWaybill());
			if(status==1){
				//wayb.setIndemnity(ind.getFee());
			}else if(status==0){
				//wayb.setIndemnity(0);
			}
		}
	}
	public void deleteByIds(String ids,String waybills) {
		delayRecordDao.deleteByIds(ids);
		String[] waybillss = waybills.split(",");
		for(int i=0;i<waybillss.length;i++){
			System.out.println("DelayRecord Service: waybill.i :"+waybillss[i]);  //输出语句
			Waybill wayb  = waybillDao.findByWaybill(waybillss[i]);
			//wayb.setIndemnity(countFeeByWaybill(waybillss[i]).intValue());
			waybillDao.update(wayb);
		}
		
	}
	public Map find(String custId,String bitch,String waybill,String sender,String rater,String lineId,Date stdate,Date enddate){
		return this.delayRecordDao.find(custId, bitch, waybill, sender, rater, lineId, stdate, enddate);
	}
	public Long countFeeByWaybill(String waybill){
		return this.delayRecordDao.countFeeByWaybill(waybill);
	}
	//实现在到货页面直接生成晚到赔偿记录
	public void updateCreatRecord(String ids,Integer md, Double drate,Integer inDate){
		String[] idss = ids.split(",");
		Waybill wayb = null;
		Track track = null;
		for(int i=0;i<idss.length;i++){
			System.out.println("Track Service: waybill.i :"+idss[i]);  //输出语句
			
			track = trackDao.findById(Integer.parseInt(idss[i]));
			wayb = waybillDao.findByWaybill(track.getWaybill());
			DelayRecord dr = new DelayRecord();
			
			dr.setWaybill(track.getWaybill());
			dr.setApproval(0); //未审核状态
			dr.setArrdate(track.getArriveDate());
			dr.setSddate(track.getSddate());
			dr.setArrPics(track.getPics());
			
			dr.setBitch(track.getBitch());
			dr.setCustId(track.getCustId());
			dr.setCustName(track.getCustName());
			dr.setLineId(track.getLineId());
			dr.setRater(track.getRater());
			dr.setSender(track.getSender());
		
			
			dr.setModel(md);
			dr.setDelayRate(drate);
			//平均得出到货重量
			Double dWeight = wayb.getWeight()*(track.getPics().doubleValue()/wayb.getPics().doubleValue());
			System.out.print("总包："+wayb.getPics()+"到货包数："+track.getPics());
			
			System.out.print("平均重量："+dWeight);//测试输出
			
			//区分算法分别计算赔偿金额
			Double indemnity =0.0;
			int delayDate =0;
			java.text.DecimalFormat   df1   =new   java.text.DecimalFormat("#.0"); 
			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
			
			if(md==1){
				indemnity = dWeight*drate;
			}else if(md==0){
				delayDate = track.getDays()-inDate;
				indemnity = dWeight*drate*delayDate;
			}
			Double idy = Double.parseDouble(df.format(indemnity));
			Double weight = Double.parseDouble(df1.format(dWeight));
			dr.setInDate(inDate);
			dr.setTransDays(track.getDays());
			dr.setDelayDate(delayDate);
			dr.setDelayWeight(weight);
			dr.setDelayIndemnity(idy);
			dr.setRemarks("");
			save(dr);
		
		}
		
	}
	//实现 “赔偿资料”的excel导入
	public String checkExcel(File excelFile,String excelFileFileName) throws Exception{
		String returnMark="";
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);
		if(!ros.getCell(0).getStringCellValue().equals("赔偿日期")				
				||!ros.getCell(1).getStringCellValue().equals("票号")
				||!ros.getCell(2).getStringCellValue().equals("金额")				
				||!ros.getCell(3).getStringCellValue().equals("赔偿方式")	
				||!ros.getCell(4).getStringCellValue().equals("赔偿原由")
				||!ros.getCell(5).getStringCellValue().equals("备注")
			
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
					returnMark+=""+(i+1)+"行，<赔偿原由>不是数值类型！\n |";
					continue;
				}else if(ros.getCell(4).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<赔偿方式>不是数值类型！\n |";
					continue;
				}
				else if(delayRecordDao.isExsit(this.getValue(ros.getCell(1)), Integer.parseInt(this.getValue(ros.getCell(4))))){
					returnMark+=""+(i+1)+"行，票号："+this.getValue(ros.getCell(1))+"已经做过此“赔偿原由”的赔偿 。\n  |";
					continue;
				}
				
				
			}
		}
		System.out.println(returnMark);
		return returnMark;
	}
	//晚到赔偿的导入
	public String saveInputExcel(File excelFile,String excelFileName) throws FileNotFoundException {	
		String errorString="";
		Date inDate = new Date();		
		
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);		
		for(int i=2;i<=sheet.getLastRowNum();i++){
			ros=sheet.getRow(i);
			DelayRecord delayRecord = new DelayRecord();
		
			inDate=HSSFDateUtil.getJavaDate(ros.getCell(0).getNumericCellValue());
			
				Waybill waybill = waybillDao.findByWaybill(this.getValue(ros.getCell(1)));
				delayRecord.setWaybill(waybill.getWaybill());
				
				delayRecord.setBitch(waybill.getBitch());
				delayRecord.setCustId(waybill.getCustId());
				delayRecord.setCustName(waybill.getCustName());
				delayRecord.setLineId(waybill.getLineId());
				delayRecord.setRater(waybill.getRaterName());
				delayRecord.setSender(waybill.getSender());
				
			//	delayRecord.setArrdate(arrdate);
			//	delayRecord.setIndate(inDate);
			//	delayRecord.setFee(Integer.parseInt(this.getValue(ros.getCell(2))));
			//	delayRecord.setPayMethod(Integer.parseInt(this.getValue(ros.getCell(3))));
			//	delayRecord.setReason(Integer.parseInt(this.getValue(ros.getCell(4))));
				delayRecord.setRemarks(this.getValue(ros.getCell(5)));		
				
				delayRecord.setApproval(0); //默认未审核				
				save(delayRecord);
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
