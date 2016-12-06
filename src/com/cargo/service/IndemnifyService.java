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

import com.cargo.dao.IndemnifyDao;
import com.cargo.dao.TrackDao;
import com.cargo.dao.WaybillDao;
import com.cargo.model.Indemnify;
import com.cargo.model.Waybill;

@Component
public class IndemnifyService {
	private IndemnifyDao indemnifyDao;
	private WaybillDao waybillDao;
	private TrackDao trackDao;
	
	public IndemnifyDao getIndemnifyDao() {
		return indemnifyDao;
	}

	@Resource
	public void setIndemnifyDao(IndemnifyDao indemnifyDao) {
		this.indemnifyDao = indemnifyDao;
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

	public void save(Indemnify indemnify){
		indemnifyDao.save(indemnify);
		
	}
	public void delete(Indemnify indemnify){
		this.indemnifyDao.delete(indemnify);
	}
	public void update(Indemnify indemnify){
		indemnifyDao.update(indemnify);
		//Waybill waybill = waybillDao.findByWaybill(indemnify.getWaybill());
		//waybill.setIndemnity(countFeeByWaybill(indemnify.getWaybill()).intValue());
		//waybillDao.update(waybill);
		
	}
	public List<Indemnify> findAll(){
		return this.indemnifyDao.findAll();
	}
	public Indemnify findByWaybill(String waybill){
		return indemnifyDao.findByWaybill(waybill);
	}
	public void createIndemnify(String waybills){
		String[] waybillss = waybills.split(",");
		Waybill wayb = null;
		
		for(int i=0;i<waybillss.length;i++){
			
			wayb = waybillDao.findByWaybill(waybillss[i]);
			Integer allPic = wayb.getPics();
			Integer arrPic = trackDao.countPicsByWaybill(waybillss[i])!=null?trackDao.countPicsByWaybill(waybillss[i]):0;
			
			Integer noPic = allPic-arrPic;
			
			if(noPic>0 && !indemnifyDao.isExsit(waybillss[i])){
				
				Indemnify indem = new Indemnify();
				
				indem.setWaybill(waybillss[i]);
				indem.setBitch(wayb.getBitch());
				indem.setCustId(wayb.getCustId());
				indem.setCustName(wayb.getCustName());
				indem.setRater(wayb.getRaterName());
				indem.setProcurator(wayb.getProcurator());
				indem.setSender(wayb.getSender());
				indem.setLineId(wayb.getLineId());
				indem.setGoods(wayb.getGoods());
				indem.setMark(wayb.getMark());
				indem.setPrice(wayb.getPrice());
				Integer yiWuBao = 10;
				
				indem.setYiWuBao(yiWuBao);
				indem.setWorth(wayb.getWorth());
				indem.setWeight(wayb.getWeight());
				indem.setOutWorth(wayb.getOutWorth());
				
				
				
				indem.setReason("有"+noPic+"包货物丢失或未到！");
				//对客户赔偿
				Long yw =Math.round(wayb.getWeight()*yiWuBao);
				Integer yworth = yw.intValue();
				Integer worth = wayb.getWorth()>0?wayb.getWorth():yworth;
				Double rate =noPic.doubleValue()/allPic.doubleValue();
				Double indemWorth =allPic==noPic?worth:worth*rate;
				
				indem.setIndemWorth(indemWorth);
				
				Double weight = wayb.getWeight()/allPic*noPic.doubleValue();
				indem.setIndemWeight(weight);
				Double subTotal =wayb.getSumbill()-wayb.getTotal();
				
				Double returnBill =allPic==noPic?subTotal: weight*(wayb.getPrice()+wayb.getRaterRate());
				
				indem.setReturnBill(returnBill);
				//----------
				System.out.println("Indemnify Service: allPic: "+ allPic);
				System.out.println("Indemnify Service: noPic: "+ noPic);
				System.out.println("Indemnify Service: returnBill: "+ returnBill);
				
				
				
				//----------
				indem.setIndemDate(null);
				indem.setIndemnity(indemWorth+returnBill);				
				indem.setStatus(0);
				indem.setPayMethod(0);
				
				//向清关公司索赔
				Integer outYiWuBao = 10;
				Long oyw =Math.round(wayb.getWeight()*outYiWuBao);
				Double outYworth = oyw.doubleValue();
				Double outWorth = wayb.getOutWorth()>0?wayb.getOutWorth():outYworth;
				Double outIndemnify =outWorth/allPic*noPic.doubleValue();
				
				
				indem.setOutIndemnity(outIndemnify);				
				indem.setOutIndemDate(null);
				indem.setOutStatus(0);							
				indem.setOutPrice(wayb.getOutPrice());
				indem.setRemarks("");				
				
				//审核与赔付状态
				//--------------
				System.out.println("Indemnify Service: outIndemnify "+ outIndemnify);
				System.out.println("arrive rate "+ rate);
				System.out.println("Indemnify Service: indemnify "+ indemWorth+returnBill);
				System.out.println("Indemnify Service: NOPIC "+ noPic);
				//--------------
				
				indem.setAppDate(null);
				indem.setApproval(0);
				save(indem);				
				}
			
			
		}
	}
	
	public void updateApprovalIndemnify(String ids,Integer status){
		
		this.indemnifyDao.approvalIndemnify(ids, status);
		
		String[] idss = ids.split(",");
		for(int i=0;i<idss.length;i++){
			Indemnify ind = this.indemnifyDao.findById(Integer.parseInt(idss[i]));
			Waybill wayb  = waybillDao.findByWaybill(ind.getWaybill());
			if(status==1){
				ind.setAppDate(new Date());
				ind.setApproval(status);
			}else if(status==0){
				ind.setApproval(status);
				ind.setAppDate(null);
			}
		
		}
		
		
		
	}
	public void deleteByIds(String ids) {
		indemnifyDao.deleteByIds(ids);
		
		
	}
	public Map find(String custId,String bitch,String waybill,String sender,String rater,String lineId,Date stdate,Date enddate){
		return this.indemnifyDao.find(custId, bitch, waybill, sender, rater, lineId, stdate, enddate);
	}
	public Double countFeeByWaybill(String waybill){
		return this.indemnifyDao.countFeeByWaybill(waybill);
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
				else if(indemnifyDao.isExsit(this.getValue(ros.getCell(1)))){
					returnMark+=""+(i+1)+"行，票号："+this.getValue(ros.getCell(1))+"已经做过此“赔偿原由”的赔偿 。\n  |";
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
		Date inDate = new Date();		
		
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);		
		for(int i=2;i<=sheet.getLastRowNum();i++){
			ros=sheet.getRow(i);
			Indemnify indemnify = new Indemnify();
		
			inDate=HSSFDateUtil.getJavaDate(ros.getCell(0).getNumericCellValue());
			
				Waybill waybill = waybillDao.findByWaybill(this.getValue(ros.getCell(1)));
				indemnify.setWaybill(waybill.getWaybill());
				
				indemnify.setBitch(waybill.getBitch());
				indemnify.setCustId(waybill.getCustId());
				indemnify.setCustName(waybill.getCustName());
				indemnify.setLineId(waybill.getLineId());
				indemnify.setRater(waybill.getRaterName());
				indemnify.setProcurator(waybill.getProcurator());
				indemnify.setSender(waybill.getSender());
				
				indemnify.setIndemDate(inDate);
				//indemnify.setFee(Integer.parseInt(this.getValue(ros.getCell(2))));
				indemnify.setPayMethod(Integer.parseInt(this.getValue(ros.getCell(3))));
				//indemnify.setReason(Integer.parseInt(this.getValue(ros.getCell(4))));
				indemnify.setRemarks(this.getValue(ros.getCell(5)));		
				
				indemnify.setApproval(0); //默认未审核				
				save(indemnify);
			}
		return errorString;
	}
	//选中的赔偿记录录出
	public InputStream getInputStream(String waybills){
		InputStream is = null;
		Workbook book = new HSSFWorkbook();
		List<Indemnify> list = new ArrayList<Indemnify>();
		String[] waybs =waybills.split(",");
		for(int i=0;i<waybs.length;i++){
			System.out.println("Waybill Service: waybill :"+waybs[i]);  //输出语句
			
			Indemnify wb = this.findByWaybill(waybs[i]);
			
			list.add(wb);
			
		}
				
		String sheetName="赔偿报表";
		String titleName="258 КАРГО 赔偿报表";
		
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
		sheet.setColumnWidth(0, 2900); //日期
        sheet.setColumnWidth(1, 5000); //批次
        sheet.setColumnWidth(2, 7000); //票号
		sheet.setColumnWidth(3, 1800); //件数 
		sheet.setColumnWidth(4, 1800); //重量
		sheet.setColumnWidth(5, 1800); //体积
		sheet.setColumnWidth(6, 8000); //品名		
		sheet.setColumnWidth(7, 1800);//数量		
		sheet.setColumnWidth(8, 1800); //货值
		sheet.setColumnWidth(9, 1800); //单价		
		sheet.setColumnWidth(10, 2000); //应收		
		sheet.setColumnWidth(11, 4000); //原因
		sheet.setColumnWidth(12, 1800); //丢失重量
		sheet.setColumnWidth(13, 1800); //丢失数量		
		sheet.setColumnWidth(14, 2000); //发货人
		sheet.setColumnWidth(15, 1800); //丢失赔偿
		sheet.setColumnWidth(16, 1800); //退运费
		sheet.setColumnWidth(17, 2000); //赔客户
		sheet.setColumnWidth(18, 2000); //赔偿方式
		sheet.setColumnWidth(19, 2900); //赔偿日期
		sheet.setColumnWidth(20, 4000); //备注
		
		sheet.setColumnWidth(21, 1800); //外货值
		sheet.setColumnWidth(22, 1800); //外单价
		sheet.setColumnWidth(23, 1800); //外退运费
		sheet.setColumnWidth(24, 1800); //外丢失赔偿
		sheet.setColumnWidth(25, 2000); //外总额
		sheet.setColumnWidth(26, 2000); //赔偿状态
		sheet.setColumnWidth(27, 2900); //赔偿日期
		
				
		
		
		Row row = sheet.createRow(0);
		
		HSSFCell cell =(HSSFCell) row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 27));
		
		row.getCell(0).setCellValue(titleName);		
		row.getCell(0).setCellStyle(titleStyle);
		
		row = sheet.createRow(1);
		row.setHeight((short) 600);//目的是想把行高设置成400px
		cell = (HSSFCell) row.createCell(0);
		cell.setCellValue("日期");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(1);
		cell.setCellValue("批次");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(2);
		cell.setCellValue("票号");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(3);
		cell.setCellValue("包数");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(4);
		cell.setCellValue("重量");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(5);
		cell.setCellValue("体积");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(6);
		cell.setCellValue("品名");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(7);
		cell.setCellValue("小件数");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(8);
		cell.setCellValue("货值");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(9);
		cell.setCellValue("单价");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(10);
		cell.setCellValue("应收");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(11);
		cell.setCellValue("赔偿原因");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(12);
		cell.setCellValue("丢失重量");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(13);
		cell.setCellValue("丢失数量");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(14);
		cell.setCellValue("发货人");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(15);
		cell.setCellValue("丢失赔偿");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(16);
		cell.setCellValue("退运费");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(17);
		cell.setCellValue("赔付金额");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(18);
		cell.setCellValue("赔付方式");
		cell.setCellStyle(headerStyle);

		cell = (HSSFCell) row.createCell(19);
		cell.setCellValue("赔付日期");
		cell.setCellStyle(headerStyle);

		cell = (HSSFCell) row.createCell(20);
		cell.setCellValue("备注");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(21);
		cell.setCellValue("外保货值");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(22);
		cell.setCellValue("外单价");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(23);
		cell.setCellValue("外退运费");
		cell.setCellStyle(headerStyle);
		
		
		cell = (HSSFCell) row.createCell(24);
		cell.setCellValue("外赔货值");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(25);
		cell.setCellValue("外赔总计");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(26);
		cell.setCellValue("赔偿状态");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(27);
		cell.setCellValue("赔付日期");
		cell.setCellStyle(headerStyle);
		

		
			int i=2;
			
			for(int j=0;j<list.size();j++){
				Indemnify indem = (Indemnify) list.get(j);				
				Waybill waybill = waybillDao.findByWaybill(indem.getWaybill());
				row = sheet.createRow(i);
				String outD  = indem.getOutIndemDate() != null?indem.getOutIndemDate().toString():"-";
				String inD = indem.getIndemDate() != null?indem.getIndemDate().toString():"-";
				
				String payMethod = "";
				switch(indem.getPayMethod()){
	        		case 0:  payMethod="运费中减";
	        		break;
	        		case 1:  payMethod="现金";
	        		break;
	        		case 2:  payMethod="转账";
	        		break;
	        		case 3:  payMethod="其他";
	        		break;
				}
				String outPayMethod = "";
				switch(indem.getOutStatus()){
        		case 0: outPayMethod = "未赔付";
        		break;
        		case 1: outPayMethod = "已经赔付";
        		break;
        		
			    }
				
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue(waybill.getSddate());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(indem.getBitch());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(indem.getWaybill());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(waybill.getPics());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(waybill.getWeight());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue(waybill.getVolumu());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue(waybill.getGoods());
				cell.setCellStyle(cellStyle);
								
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue(waybill.getQuantity());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue(indem.getWorth());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue(indem.getPrice());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(10);
				cell.setCellValue(waybill.getTotal());
				cell.setCellStyle(cellStyle);					
				cell = (HSSFCell) row.createCell(11);
				cell.setCellValue(indem.getReason());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(12);
				cell.setCellValue(indem.getIndemWeight());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(13);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(14);
				cell.setCellValue(indem.getSender());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(15);
				cell.setCellValue(indem.getIndemWorth());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(16);
				cell.setCellValue(indem.getReturnBill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(17);
				cell.setCellValue(indem.getIndemnity());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(18);
				cell.setCellValue(payMethod);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(19);
				cell.setCellValue(inD);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(20);
				cell.setCellValue(indem.getRemarks());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(21);
				cell.setCellValue(indem.getOutWorth());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(22);
				cell.setCellValue(indem.getOutPrice());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(23);
				cell.setCellValue(indem.getOutReturnBill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(24);
				cell.setCellValue(indem.getOutIndemWorth());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(25);
				cell.setCellValue(indem.getOutIndemnity());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(26);
				cell.setCellValue(outPayMethod);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(27);
				cell.setCellValue(outD);
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
