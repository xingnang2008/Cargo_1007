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
import java.util.ArrayList;
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

import com.cargo.dao.ReceiptDao;
import com.cargo.dao.TrackDao;
import com.cargo.dao.WaybillDao;
import com.cargo.model.DelayRecord;
import com.cargo.model.Indemnify;
import com.cargo.model.Receipt;
import com.cargo.model.Track;
import com.cargo.model.Waybill;

@Component
public class TrackService {
	private TrackDao trackDao;
	private WaybillDao waybillDao;
	private ReceiptDao receiptDao;
	
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
	
	public ReceiptDao getReceiptDao() {
		return receiptDao;
	}
	@Resource
	public void setReceiptDao(ReceiptDao receiptDao) {
		this.receiptDao = receiptDao;
	}
	public void save(Track track){
		this.trackDao.save(track);
		
	}
	public void update(Track track){
		trackDao.update(track);
				
	}
	public void delete(Track track){
		this.trackDao.delete(track);
	}
	public void deleteByIds(String ids) {
		String[] idss =ids.split(",");
		for(int i=0;i<idss.length;i++){
			Track track = findById(Integer.parseInt(idss[i]));   
			waybillDao.findByWaybill(track.getWaybill()).setStatusId(2);  //通过id取得运单 号，根据运单 号修改运单 状态为“已配仓” 2
		}
		this.trackDao.deleteByIds(ids);
			
	}
	public Boolean CheckPics(String waybill,Integer pics){
		return trackDao.CheckPics(waybill, pics);
	}
	public Track findById(java.lang.Integer id) {
		return this.trackDao.findById(id);
	}
	public List findAll() {
		return this.trackDao.findAll();
	}
	public void updateAppOn(String ids,Integer editable){
		this.trackDao.updateAppOn(ids, editable);
	}
	
	public Integer countPicsByWaybill(String waybill){
		return this.trackDao.countPicsByWaybill(waybill);
	}
	public Integer countDelayIndemByWaybill(String waybill){
		return this.trackDao.countDelayIndemByWaybill(waybill);
	}
	public Map find(String custId,String bitch,String waybill,String sender,String rater,String lineId,Date stdate,Date enddate){
		return this.trackDao.find(custId, bitch, waybill, sender, rater, lineId, stdate, enddate);
	}
	public Boolean isexsitTrackByWaybill(String waybill){
		return this.trackDao.isexsitTrackByWaybill(waybill);
	}
	//实际将waybills字段里的运单号waybill添加到-到货记录里
	public void updateWaybillsArr(String waybills,Date tdate){
		String[] waybillss = waybills.split(",");
		Waybill wayb = null;
		
		for(int i=0;i<waybillss.length;i++){
			System.out.println("Track Service: waybill.i :"+waybillss[i]);  //输出语句
			wayb = waybillDao.findByWaybill(waybillss[i]);
			int pic = wayb.getPics()-this.countPicsByWaybill(waybillss[i]);
			if(pic > 0){
				
				
				Track track = new Track();
				track.setArriveDate(tdate);   //到货日期
				track.setWaybill(wayb.getWaybill());
				track.setSddate(wayb.getSddate());
				track.setSender(wayb.getSender());				
				track.setBitch(wayb.getBitch());
				track.setCustId(wayb.getCustId());
				track.setCustName(wayb.getCustName());
				track.setLineId(wayb.getLineId());
				track.setRater(wayb.getRaterName());
				track.setDestName(wayb.getDestName());
				
				track.setPics(pic);
				
				track.setRemarks("");
				
				track.setWstatus(0);				
				
				//计算实际运期
				Long between = ((Long)track.getArriveDate().getTime()-wayb.getSddate().getTime())/1000;
				Long yq = between/(24*3600);
				track.setDays(yq.intValue());
				
				track.setModel(0);
				track.setDelayWeight(0.0);
				track.setInDate(0);
				track.setDelayRate(0.0);
				track.setDelayDate(0);
				track.setDelayIndemnity(0.0);
				track.setApproval(0);
				track.setAppDate(new Date());
				track.setOutIndemnity(0.0);
				
				track.setStatus(0);
				track.setMothed("无");
				track.setIndemDate(null);
				
				save(track);
				
				
				wayb.setStatusId(4);
				waybillDao.update(wayb);
			}
			
			
		}
	}
	
	
	//实现到货资料的excel导入
	public String checkExcel(File excelFile,String excelFileFileName) throws Exception{
		String returnMark="";
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);
		if(!ros.getCell(0).getStringCellValue().equals("到货日期")				
				||!ros.getCell(1).getStringCellValue().equals("票号")
				||!ros.getCell(2).getStringCellValue().equals("包数")				
				||!ros.getCell(3).getStringCellValue().equals("完好状态")				
				||!ros.getCell(4).getStringCellValue().equals("备注")
			
		){
			
			returnMark="格式不对，请下载模板表格";
			
		}else if(sheet.getLastRowNum()<=0){
			returnMark="这是一个空的模板";
		}else{
			Map mapWaybill = new HashMap();
			int j =0;
			for(int i=2;i<=sheet.getLastRowNum();i++){
				ros = sheet.getRow(i);
				Integer pices =Integer.parseInt(this.getValue(ros.getCell(2)));
				Integer oldPic = countPicsByWaybill(this.getValue(ros.getCell(1)));
				if(oldPic == null){
					oldPic =0;
				}
				int yPic = waybillDao.findByWaybill(this.getValue(ros.getCell(1))).getPics();
				System.out.print("到货包数："+pices+" 运单件数："+yPic +" 已到货件数："+oldPic);
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
					returnMark+=""+(i+1)+"行，<包数>不是数值类型！\n |";
					continue;
				}else if(ros.getCell(3).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<完整状态>不是数值类型！\n |";
					continue;
				}else if(pices+oldPic > yPic){
					returnMark+=""+(i+1)+"行，到货包数大于实际货物包装数，票号："+this.getValue(ros.getCell(1))+"\n  |";
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
		Date arrDate = new Date();
		
		
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);		
		for(int i=2;i<=sheet.getLastRowNum();i++){
			ros=sheet.getRow(i);
			Track track = new Track();			
		
			arrDate=HSSFDateUtil.getJavaDate(ros.getCell(0).getNumericCellValue());
			
			
				Waybill waybill = waybillDao.findByWaybill(this.getValue(ros.getCell(1)));
				track.setWaybill(waybill.getWaybill());				
				track.setBitch(waybill.getBitch());
				track.setCustId(waybill.getCustId());
				track.setCustName(waybill.getCustName());
				track.setLineId(waybill.getLineId());
				track.setRater(waybill.getRaterName());
				track.setSender(waybill.getSender());
				track.setSddate(waybill.getSddate());
				
				track.setArriveDate(arrDate);
				track.setPics(Integer.parseInt(this.getValue(ros.getCell(2))));
				track.setWstatus(Integer.parseInt(this.getValue(ros.getCell(3))));
				track.setRemarks(this.getValue(ros.getCell(4)));		
				
				//计算实际运期
				Long between = ((Long)track.getArriveDate().getTime()-waybill.getSddate().getTime())/1000;
				Long yq = between/(24*3600);
				track.setDays(yq.intValue());
				
				track.setModel(0);
				track.setDelayWeight(0.0);
				track.setInDate(0);
				track.setDelayRate(0.0);
				track.setDelayDate(0);
				track.setDelayIndemnity(0.0);
				track.setApproval(0);
				
				
				
				save(track);
				
				waybill.setStatusId(4);
				waybillDao.update(waybill);
			}
		return errorString;
	}
	
	//实现在到货页面直接生成晚到赔偿记录
	public void updateCreatRecord(String ids,Integer md, Double drate,Integer inDate,Date outSdDate,Integer outInDate,Double outDelayRate){
		String[] idss = ids.split(",");
		Waybill wayb = null;
		Track track = null;
		for(int i=0;i<idss.length;i++){
			System.out.println("Track Service: waybill.i :"+idss[i]);  //输出语句
			
			track = trackDao.findById(Integer.parseInt(idss[i]));
			wayb = waybillDao.findByWaybill(track.getWaybill());
			
			track.setModel(md);
			track.setDelayRate(drate);
			track.setInDate(inDate);
			track.setOutSdDate(outSdDate);
			track.setOutInDate(outInDate);
			track.setOutDelayRate(outDelayRate);
			System.out.print("外发货日期："+outSdDate);//测试输出
			Long between = ((Long)track.getArriveDate().getTime()-outSdDate.getTime())/1000;
			Long yq = between/(24*3600);
			Integer outDelayDays = yq.intValue()-outInDate;
			
			track.setOutDelayDate(outDelayDays);
			
			
			
			//平均得出到货重量
			Double dWeight = wayb.getWeight()*(track.getPics().doubleValue()/wayb.getPics().doubleValue());
			System.out.println("重量："+dWeight+"外系数："+outDelayRate);
			System.out.println("晚到："+outDelayDays);//测试输出
			System.out.println("外结："+dWeight*outDelayDays*outDelayRate);//测试输出
			
			
			//区分算法分别计算赔偿金额
			Double indemnity =0.0;
			int delayDate =0;
			java.text.DecimalFormat   df1   =new   java.text.DecimalFormat("#.0"); 
			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
			Double outIndem =dWeight*outDelayDays*outDelayRate;
			track.setOutIndemnity(Double.parseDouble(df1.format(outIndem)));
			if(md==1){
				indemnity = dWeight*(wayb.getPrice()-drate);
				delayDate = track.getDays()-inDate;
			}else if(md==0){
				delayDate = track.getDays()-inDate;
				indemnity = dWeight*drate*delayDate;
			}
			Double idy = Double.parseDouble(df.format(indemnity));
			Double weight = Double.parseDouble(df1.format(dWeight));
			
			
			track.setDelayDate(delayDate);
			track.setDelayWeight(weight);
			track.setDelayIndemnity(idy);
			track.setApproval(0);
			update(track);
		
		}
		
	}
	//选中的赔偿记录录出
	public InputStream getInputStream(String ids){
		InputStream is = null;
		Workbook book = new HSSFWorkbook();
		List<Track> list = new ArrayList<Track>();
		String[] idss =ids.split(",");
		for(int i=0;i<idss.length;i++){
			System.out.println("Track Service: track id :"+idss[i]);  //输出语句
			
			Track tk = this.findById(Integer.parseInt(idss[i]));
			
			list.add(tk);
			
		}
				
		String sheetName="晚到赔偿报表";
		String titleName="258 КАРГО 晚到赔偿报表";
		
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
		
		HSSFCellStyle dateCellStyle = (HSSFCellStyle) book.createCellStyle();// 创建<日期>单元格样式  
		dateCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
		dateCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中 
		dateCellStyle.setWrapText(true);		
		dateCellStyle.setFont(cellFont); 
		dateCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		short df=book.createDataFormat().getFormat("yyyy/mm/dd"); 
		dateCellStyle.setDataFormat(df);
		
		 //--列宽设置
		sheet.setColumnWidth(0, 2000); //货源地
        sheet.setColumnWidth(1, 5000); //批次
        sheet.setColumnWidth(2, 2900); //日期
        sheet.setColumnWidth(3, 7000);//票号
		sheet.setColumnWidth(4, 1800); //件数 
		sheet.setColumnWidth(5, 1800); //重量
		sheet.setColumnWidth(6, 1800); //体积
		sheet.setColumnWidth(7, 8000); //品名		
		sheet.setColumnWidth(8, 1800);//数量		
		sheet.setColumnWidth(9, 1800); //目的地
		sheet.setColumnWidth(10, 1800); //付款方式		
		sheet.setColumnWidth(11, 2000); //发货人		
		sheet.setColumnWidth(12, 2900); //到货日期
		sheet.setColumnWidth(13, 1800); //到货包数
		sheet.setColumnWidth(14, 1800); //到货重量		
		sheet.setColumnWidth(15, 2000); //晚到天数
		sheet.setColumnWidth(16, 1800); //晚到赔偿
		sheet.setColumnWidth(17, 1800); //赔偿情况
		sheet.setColumnWidth(18, 2000); //起飞日期
		sheet.setColumnWidth(19, 2000); //晚到天数		
		sheet.setColumnWidth(20, 2000); //外赔偿
		sheet.setColumnWidth(21, 2900); //外赔状态
		
	
		
				
		
		
		Row row = sheet.createRow(0);
		
		HSSFCell cell =(HSSFCell) row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 21));
		
		row.getCell(0).setCellValue(titleName);		
		row.getCell(0).setCellStyle(titleStyle);
		
		row = sheet.createRow(1);
		row.setHeight((short) 600);//目的是想把行高设置成400px
		cell = (HSSFCell) row.createCell(0);
		cell.setCellValue("货源地");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(1);
		cell.setCellValue("批次");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(2);
		cell.setCellValue("日期");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(3);
		cell.setCellValue("票号");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(4);
		cell.setCellValue("包数");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(5);
		cell.setCellValue("重量");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(6);
		cell.setCellValue("体积");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(7);
		cell.setCellValue("品名");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(8);
		cell.setCellValue("小件数");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(9);
		cell.setCellValue("目的地");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(10);
		cell.setCellValue("付款方式");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(11);
		cell.setCellValue("发货人");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(12);
		cell.setCellValue("到货日期");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(13);
		cell.setCellValue("到货包数");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(14);
		cell.setCellValue("到货重量");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(15);
		cell.setCellValue("晚到天数");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(16);
		cell.setCellValue("晚到赔偿");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(17);
		cell.setCellValue("赔偿情况");
		cell.setCellStyle(headerStyle);
				
		cell = (HSSFCell) row.createCell(18);
		cell.setCellValue("起飞日期");
		cell.setCellStyle(headerStyle);

		cell = (HSSFCell) row.createCell(19);
		cell.setCellValue("晚到天数");
		cell.setCellStyle(headerStyle);

		cell = (HSSFCell) row.createCell(20);
		cell.setCellValue("外赔金额");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(21);
		cell.setCellValue("赔偿状态");
		cell.setCellStyle(headerStyle);
		
		
		

		
			int i=2;
			
			for(int j=0;j<list.size();j++){
				Track tk = (Track) list.get(j);				
				Waybill waybill = waybillDao.findByWaybill(tk.getWaybill());
				row = sheet.createRow(i);
				
				String stauts = "";
				switch(tk.getStatus()){
        		case 0: stauts = "未赔付";
        		break;
        		case 1: stauts = "已经赔付";
        		break;
        		
			    }
				
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue(waybill.getOrderNo());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(tk.getBitch());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(tk.getSddate());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(tk.getWaybill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(waybill.getPics());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue(waybill.getWeight());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue(waybill.getVolumu());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue(waybill.getGoods());
				cell.setCellStyle(cellStyle);
								
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue(waybill.getQuantity());
				cell.setCellStyle(cellStyle);
			
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue(waybill.getDestName());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(10);
				cell.setCellValue(waybill.getPayMethod());
				cell.setCellStyle(cellStyle);					
				cell = (HSSFCell) row.createCell(11);
				cell.setCellValue(tk.getSender());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(12);
				cell.setCellValue(tk.getArriveDate());
				cell.setCellStyle(dateCellStyle);
				
				cell = (HSSFCell) row.createCell(13);
				cell.setCellValue(tk.getPics());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(14);
				cell.setCellValue(tk.getDelayWeight());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(15);
				cell.setCellValue(tk.getDelayDate());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(16);
				cell.setCellValue(tk.getDelayIndemnity());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(17);
				cell.setCellValue(stauts);
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(18);
				cell.setCellValue(tk.getOutSdDate());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(19);
				cell.setCellValue(tk.getOutDelayDate());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(20);
				cell.setCellValue(tk.getOutIndemnity());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(21);
				cell.setCellValue("");
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
