package com.cargo.service;

import java.io.ByteArrayOutputStream;
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
import javax.annotation.security.RolesAllowed;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cargo.dao.BitchDao;
import com.cargo.dao.IndemnifyDao;
import com.cargo.dao.TrackDao;
import com.cargo.dao.WaybillDao;
import com.cargo.model.Bitch;
import com.cargo.model.Customer;
import com.cargo.model.Dest;
import com.cargo.model.Indemnify;
import com.cargo.model.Line;
import com.cargo.model.Procurator;
import com.cargo.model.Rater;
import com.cargo.model.Sender;
import com.cargo.model.Track;
import com.cargo.model.Waybill;
import com.cargo.model.dto.Arrearages;
import com.cargo.model.dto.ProcuratorGroup;
import com.cargo.model.dto.WaybillGroupSender;
@Component("waybillService")
public class WaybillService {
	private BitchDao bitchDao;
	private WaybillDao waybillDao;
	private SenderService senderService;
	private CustomerService customerService;
	private RaterService raterService;
	private DestService destService;
	private LineService lineService;
	private ProcuratorService procuratorService;
	private AdvanceRecordersService advanceRecordersService;
	private ProcuratorRecordersService procuratorRecordersService;
	private MarkService markService;
	private TrackDao trackDao;
	private IndemnifyDao indemnifyDao;
	
	
	public IndemnifyDao getIndemnifyDao() {
		return indemnifyDao;
	}
	@Resource
	public void setIndemnifyDao(IndemnifyDao indemnifyDao) {
		this.indemnifyDao = indemnifyDao;
	}
	public BitchDao getBitchDao() {
		return bitchDao;
	}
	@Resource
	public void setBitchDao(BitchDao bitchDao) {
		this.bitchDao = bitchDao;
	}

	public WaybillDao getWaybillDao() {
		return waybillDao;
	}
	
	public LineService getLineService() {
		return lineService;
	}
	@Resource
	public void setLineService(LineService lineService) {
		this.lineService = lineService;
	}
	@Resource
	public void setWaybillDao(WaybillDao waybillDao) {
		this.waybillDao = waybillDao;
	}

	public SenderService getSenderService() {
		return senderService;
	}
	@Resource
	public void setSenderService(SenderService senderService) {
		this.senderService = senderService;
	}
	public CustomerService getCustomerService() {
		return customerService;
	}
	@Resource
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public RaterService getRaterService() {
		return raterService;
	}
	@Resource
	public void setRaterService(RaterService raterService) {
		this.raterService = raterService;
	}
	public DestService getDestService() {
		return destService;
	}
	@Resource
	public void setDestService(DestService destService) {
		this.destService = destService;
	}
	
	public ProcuratorService getProcuratorService() {
		return procuratorService;
	}
	@Resource
	public void setProcuratorService(ProcuratorService procuratorService) {
		this.procuratorService = procuratorService;
	}
	
	public MarkService getMarkService() {
		return markService;
	}
	
	public ProcuratorRecordersService getProcuratorRecordersService() {
		return procuratorRecordersService;
	}
	@Resource
	public void setProcuratorRecordersService(
			ProcuratorRecordersService procuratorRecordersService) {
		this.procuratorRecordersService = procuratorRecordersService;
	}
	public AdvanceRecordersService getAdvanceRecordersService() {
		return advanceRecordersService;
	}
	@Resource
	public void setAdvanceRecordersService(
			AdvanceRecordersService advanceRecordersService) {
		this.advanceRecordersService = advanceRecordersService;
	}
	@Resource
	public void setMarkService(MarkService markService) {
		this.markService = markService;
	}
	
	public TrackDao getTrackDao() {
		return trackDao;
	}
	@Resource
	public void setTrackDao(TrackDao trackDao) {
		this.trackDao = trackDao;
	}
	public void save(Waybill waybill){
	 this.waybillDao.save(waybill);
	}
	@RolesAllowed({"ROLE_SUPER"})
	public void delete(Waybill waybill){
		this.waybillDao.delete(waybill);
	}
	public void update(Waybill waybill){
		this.waybillDao.update(waybill);
	}
	@SuppressWarnings("unchecked")
	public List<Waybill> findAll(){
		return this.waybillDao.findAll();
		
	}
	
	public Waybill findById(java.lang.Integer id) {
		return this.waybillDao.findById(id);
	}
	public Waybill findByWaybill(String waybill){
		return this.waybillDao.findByWaybill(waybill);
	}
	@RolesAllowed({"ROLE_SUPER"})
	public void deleteByIds(String ids) {
		this.waybillDao.deleteByIds(ids);
	}
	public void updateTrackOnStauts(String ids,Integer statusId){
		waybillDao.updateTrackOnStauts(ids,statusId);
	}
	@RolesAllowed({"ROLE_CAIWU"})
	public void updateRaterStatus(String ids,Integer status,Date rdate){
		List<Waybill> list = this.waybillDao.listByIds(ids);
		waybillDao.updateRaterStatus(ids,status);
		for(Waybill w: list){
			if(status ==1){
				procuratorRecordersService.deleteByWaybill(w.getWaybill());
			}else{
				procuratorRecordersService.save(w, rdate);
			}
		}
		
	}
	public List<Waybill> queryWaybillByBitchId(String bitch){
		List<Waybill> waybills = new ArrayList<Waybill>();
		List<Waybill> list = this.waybillDao.queryByBitch(bitch);
		for(Waybill w : list){
			waybills.add(w);
		}
		return waybills;
	}
	//用于查看垫付款
	public Map listAdvanceWaybill(String sender,String custId,Integer advanceStatus,Date stDate,Date edDate){
		return waybillDao.listAdvanceWaybill(sender,custId, advanceStatus, stDate, edDate);
	}
	//用于查看代理费
	public Map listRaterWaybill(String procurator,Integer raterStatus,Date stDate,Date edDate){
		return waybillDao.listRaterWaybill(procurator, raterStatus, stDate, edDate);
	}
	//根据选中的ids,将运单垫付款状态更新
	@RolesAllowed({"ROLE_CAIWU"})
	public void updateAdvanceStatus(String ids ,Integer status,Date advanceDate){
		List<Waybill> list = this.waybillDao.listByIds(ids);
		waybillDao.updateAdvanceStatus(ids, status);
		for(Waybill w: list){
			if(status ==1){
				advanceRecordersService.deleteByWaybill(w.getWaybill());
			}else{
				advanceRecordersService.save(w, advanceDate);
			}
		}
	}
	public List<Waybill> listByBitch(String bitch){
		return this.waybillDao.listByBitch(bitch);
	}
	public List<Waybill> listAdvancedByBitch(String bitch){
		return this.waybillDao.listAdvancedByBitch(bitch);
	}
	public Map findBySenderAndSddate(String bitch,String Sender,Date sddate,Integer statusId){
		return this.waybillDao.findBySenderAndSddate(bitch, Sender, sddate,statusId);
	}
	public List listByDestIds(String ids){
		return this.waybillDao.listByDestIds(ids);
	}
	public List queryBySender(String sdName){
		return this.waybillDao.queryBySender(sdName);
	}
	public List queryByStatus(Integer status){
		return this.waybillDao.queryByStatus(status);
		
	}
	public List<Bitch> listByBitchGroup(Date stDate,Date edDate){
		List<Bitch> bitchList = bitchDao.findBySdDate(stDate, edDate);
		String bitches ="";
		for(Bitch b :bitchList){
			bitches +="'"+ b.getBitch().trim()+"',";
		}
		bitches = bitches.substring(0, bitches.lastIndexOf(","));
		
		return this.waybillDao.listByBitchGroup(bitches);
	}
	public List<WaybillGroupSender> listByCustGroup(String bitch,Integer statusId){
		return this.waybillDao.listByCustGroup(bitch, statusId);
	}
	public Integer getCountByCustGroup(String bitch){
		return this.waybillDao.getCountByCustGroup(bitch);
	}
	public void updateLockOnWaybills(String ids,Integer editable){
		this.waybillDao.updateLockOnWaybills(ids, editable);
	}
	public void updateTrackOnSend(String ids,Integer statusId,String cangId){
		this.waybillDao.updateTrackOnSend(ids, statusId, cangId);
		
	}
	public Map listUnArrivalTrack(String lineId,String custId,String waybill,String raterName,String sdName,String bitch,String procurator,Integer status,Date stDate,Date edDate){
		return this.waybillDao.listUnArrivalTrack(lineId, custId, waybill, raterName, sdName, bitch, procurator,status ,stDate, edDate);
	}
	public Map listWaybillFee(String lineId,String custId,String waybill,String raterName,String sdName,String bitch,String procurator,Integer status,Date stDate,Date edDate){
		return this.waybillDao.listWaybillFee(lineId, custId, waybill, raterName, sdName, bitch, procurator,status ,stDate, edDate);
	}
	public void updateBitch(String ids,String bitch,String lineId){
		waybillDao.updateBitch(ids, bitch,lineId);
	}
	public List<Waybill> findByBitchAndStatusId(String bitch,Integer statusId){
		return this.waybillDao.findByBitchAndStatusId(bitch, statusId);
	}
	public List queryByDest(Integer destId){
		return this.waybillDao.queryByDest(destId);
	}
	public Map find(String lineId,String custId,String waybill,String raterName,String sdName,String bitch,String procurator,Date stDate,Date edDate){
		
		return this.waybillDao.find(lineId,custId, waybill, raterName, sdName, bitch, procurator, stDate, edDate);
	}
	public Map findTrack(String lineId,String custId,String waybill,String raterName,String sdName,String bitch,String procurator,Integer status,Date stDate,Date edDate){
		
		return this.waybillDao.findTrack(lineId, custId, waybill, raterName, sdName, bitch, procurator, status, stDate, edDate);
	}
	public Map findByStatusId(String waybill,String sdName,String bitch,Integer statusId){
		return this.waybillDao.findByStatusId( waybill,  sdName, bitch, statusId);
	}	
	public Map findByRebateId(Integer rebateId,Integer page,Integer rows){
		return this.waybillDao.findByRebateId(rebateId, page, rows);
	}
	public List findByRebateId(Integer rebateId){	
		return this.waybillDao.findByRebateId(rebateId);
	}
	public Boolean isBillExsit(String waybill){
		return this.waybillDao.isBillExsit(waybill);
	}
	public void updateWaybillStatus(String ids,Integer status){
		waybillDao.updateWaybillStatus(ids, status);
	}
	public void updateWaybillStatusByWaybills(String waybills,Integer status){
		waybillDao.updateWaybillStatusByWaybills(waybills, status);
	}
	//用于查看客户的欠款
	public Map listWaybillArrearages(String sender,String custName){
		return waybillDao.listWaybillArrearages(sender, custName);
	}
	//excel的导入
	//实现货物资料的excel导入
	public String checkExcel(File excelFile,String excelFileFileName) throws Exception{
		String returnMark="";
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);
		if(!ros.getCell(0).getStringCellValue().equals("日期")				
				||!ros.getCell(1).getStringCellValue().equals("票号")
				||!ros.getCell(2).getStringCellValue().equals("包数")
				||!ros.getCell(42).getStringCellValue().equals("未加运费")
				
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
					returnMark+=""+(i+1)+"行，第1列<日期>不能为空！ ";
					continue;
				}else if(null ==ros.getCell(1)){
					returnMark+=""+(i+1)+"行，第2列<票号>不能为空！";
					continue;
				}
				else if(this.isBillExsit(this.getValue(ros.getCell(1)))){					
					returnMark+=""+(i+1)+"行，第2列<票号>在数据库已经存在 ！\n";
					continue;
				}
				else if(ros.getCell(2).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<包数>不是数值类型！";
					continue;
				}else if(ros.getCell(3).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<重量>不是数值类型！";
					continue;
				}else if(ros.getCell(4).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<体积>不是数值类型！";
					continue;
				}else if(ros.getCell(7).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<小件数>不是数值类型！";
					continue;
				}else if(ros.getCell(8).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<垫付款￥>不是数值类型！";
					continue;
				}else if(ros.getCell(9).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<垫付款$>不是数值类型！";
					continue;
				}else if(ros.getCell(10).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<包费￥>不是数值类型！";
					continue;
				}else if(ros.getCell(11).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<包费$>不是数值类型！";
					continue;
				}else if(ros.getCell(13).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<单价>不是数值类型！";
					continue;
				}else if(ros.getCell(14).getCellType()!= Cell.CELL_TYPE_NUMERIC &&ros.getCell(14).getCellType() !=Cell.CELL_TYPE_FORMULA){
					returnMark+=""+(i+1)+"行，<运费>不是数值类型！";
					continue;
				}
				else if(ros.getCell(15).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<货值>不是数值类型！";
					continue;
				}
				else if(ros.getCell(16).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，<保率>不是数值类型！";
					continue;
				}
				else if(ros.getCell(17).getCellType()!= Cell.CELL_TYPE_NUMERIC &&ros.getCell(17).getCellType() !=Cell.CELL_TYPE_FORMULA){
					returnMark+=""+(i+1)+"行，<保险费>不是数值类型！";
					continue;
				}else if(ros.getCell(18).getCellType()!= Cell.CELL_TYPE_NUMERIC &&ros.getCell(18).getCellType() !=Cell.CELL_TYPE_FORMULA){
					returnMark+=""+(i+1)+"行，第19列<应收款>不是数值类型！";
					continue;
				}else if(ros.getCell(32).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第33列<拆扣率>不是数值类型！";
					continue;
				}else if(ros.getCell(33).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第34列<代理费>不是数值类型！";
					continue;
				}else if(ros.getCell(31).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第32列<代收>不是数值类型！";
					continue;
				}else if(ros.getCell(34).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第35列<索赔>不是数值类型！";
					continue;
				}
				
				
				if(senderService.findBySender(this.getValue(ros.getCell(22)))==null){
					returnMark+=""+(i+1)+"行，第23列<发货人>数据库中不存在，请重新确认。\n";
				}
				if( customerService.findByCustId(this.getValue(ros.getCell(23)))==null){
					returnMark+=""+(i+1)+"行，第24列<客户号>数据库中不存在，请重新确认。\n";
				}
				if( destService.findByDestName(this.getValue(ros.getCell(21)))==null){
					returnMark+=""+(i+1)+"行，第22列<市场>数据库中不存在，请重新确认。\n";
				}
				if( lineService.findByLineId(this.getValue(ros.getCell(25)))==null){
					returnMark+=""+(i+1)+"行，第26列<线路>数据库中不存在，请重新确认。\n";
				}
				if(bitchDao.findByBitch(this.getValue(ros.getCell(24))) == null){
					returnMark+=""+(i+1)+"行，第25列<批次>数据库中不存在，请重新确认。\n";
				}
				if(procuratorService.findByProcurator(this.getValue(ros.getCell(29))) == null){
					returnMark+=""+(i+1)+"行，第30列<代理人>数据库中不存在，请重新确认。\n";
				}
				if(raterService.findByRaterName(this.getValue(ros.getCell(30))) == null){
					returnMark+=""+(i+1)+"行，第31列<经办人>数据库中不存在，请重新确认。\n";
				}
				
				if(mapWaybill.get(this.getValue(ros.getCell(1)))==null){
					mapWaybill.put(this.getValue(ros.getCell(1)), this.getValue(ros.getCell(1)));
				}else{
					returnMark+="\n 第"+(i+1)+"行运单号有重复，运单号是： "+this.getValue(ros.getCell(1));
				}
			}
		}
		System.out.println(returnMark);
		return returnMark;
	}
	//货物资料的导入
	public String saveInputExcel(File excelFile,String excelFileName) throws FileNotFoundException {	
		String errorString="";
		Date date = new Date();
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);		
		for(int i=2;i<=sheet.getLastRowNum();i++){
			ros=sheet.getRow(i);
			Waybill waybill = new Waybill();
		
			date=HSSFDateUtil.getJavaDate(ros.getCell(0).getNumericCellValue());
			
				Sender	sender=senderService.findBySender(this.getValue(ros.getCell(22)));
				Customer	customer = customerService.findByCustId(this.getValue(ros.getCell(23)));
				Dest	dest = destService.findByDestName(this.getValue(ros.getCell(21)));
				Line	line = lineService.findByLineId(this.getValue(ros.getCell(25)));
				Bitch	bitch = bitchDao.findByBitch(this.getValue(ros.getCell(24)));
				Procurator	procurator=procuratorService.findByProcurator(this.getValue(ros.getCell(29))) ;
				Rater rater = raterService.findByRaterName(this.getValue(ros.getCell(30)));
				
				waybill.setSddate(date);	
				waybill.setSdId(sender.getId());
				waybill.setSender(sender.getSdName());
				waybill.setSdTel(sender.getPhone());
				waybill.setCustId(customer.getCustId());
				waybill.setCustName(customer.getName());
				waybill.setCustTel(customer.getTelphone());
				waybill.setCustEmail(customer.getEmail());
				waybill.setCustAdd(customer.getAddress());
				waybill.setDestName(dest.getDestName());
				waybill.setDestTel(dest.getPhone());
				waybill.setLineId(line.getLineId());
				waybill.setTransType(line.getTransType());
				waybill.setProcurator(procurator.getName());
				waybill.setRaterName(rater.getRaterName());
				waybill.setEditable(0);
				
				waybill.setDescrip(" ");
				waybill.setCangId(" ");
				
				waybill.setBitch(bitch.getBitch());
				waybill.setWaybill(this.getValue(ros.getCell(1)));
				waybill.setPics(Integer.parseInt(this.getValue(ros.getCell(2))));
				waybill.setWeight(Double.parseDouble(this.getValue(ros.getCell(3))));
				waybill.setVolumu(Double.parseDouble(this.getValue(ros.getCell(4))));
				waybill.setGoods(this.getValue(ros.getCell(5)));
				waybill.setMark(this.getValue(ros.getCell(6)));
				waybill.setQuantity(Integer.parseInt(this.getValue(ros.getCell(7))));
				waybill.setAdvancedZ(Integer.parseInt(this.getValue(ros.getCell(8))));
				waybill.setPackfeeZ(Integer.parseInt(this.getValue(ros.getCell(9))));
				waybill.setAdvancedU(Integer.parseInt(this.getValue(ros.getCell(10))));
				waybill.setPackfeeU(Integer.parseInt(this.getValue(ros.getCell(11))));
				waybill.setExchangeRate(Double.parseDouble(this.getValue(ros.getCell(12))));
				
				waybill.setPrice(Double.parseDouble(this.getValue(ros.getCell(13))));
				waybill.setSumbill(Double.parseDouble(this.getValue(ros.getCell(14))));
				waybill.setWorth(Integer.parseInt(this.getValue(ros.getCell(15))));
				waybill.setWorthRate(Double.parseDouble(this.getValue(ros.getCell(16))));
				waybill.setInsurance(Integer.parseInt(this.getValue(ros.getCell(17))));
				
				Double tt = Double.parseDouble(this.getValue(ros.getCell(18)));
				Integer tot = Integer.parseInt(new java.text.DecimalFormat("0").format(tt));
				
				waybill.setTotal(tot);
				waybill.setActualSum(Integer.parseInt(this.getValue(ros.getCell(19))));
				waybill.setPayMethod(this.getValue(ros.getCell(20)));
				
				
				waybill.setIsRebate(Integer.parseInt(this.getValue(ros.getCell(26))));
				waybill.setRebateId(Integer.parseInt(this.getValue(ros.getCell(27))));
				waybill.setRemarks(this.getValue(ros.getCell(28)));
				
				waybill.setCod(Double.parseDouble(this.getValue(ros.getCell(31))));				
				waybill.setRaterRate(Double.parseDouble(this.getValue(ros.getCell(32))));
				waybill.setDiscount(Double.parseDouble(this.getValue(ros.getCell(33))));				
				waybill.setIndemnity(Double.parseDouble(this.getValue(ros.getCell(34))));
				
				waybill.setOutPrice(Double.parseDouble(this.getValue(ros.getCell(35))));
				waybill.setOutWorth(Double.parseDouble(this.getValue(ros.getCell(36))));
				waybill.setOutWorthrate(Double.parseDouble(this.getValue(ros.getCell(37))));
				waybill.setOutInsurance(Double.parseDouble(this.getValue(ros.getCell(38))));
				waybill.setOutSum(Double.parseDouble(this.getValue(ros.getCell(39))));
				
				waybill.setStatusId(Integer.parseInt(this.getValue(ros.getCell(40))));
				
				waybill.setNoaccAdvance(Integer.parseInt(this.getValue(ros.getCell(41))));
				waybill.setNoaccPackfee(Integer.parseInt(this.getValue(ros.getCell(42))));
				waybill.setOrderNo(this.getValue(ros.getCell(43)));
				if(waybill.getAdvancedZ()+waybill.getNoaccAdvance() >0){
					waybill.setAdvanceStauts(1);
				}else waybill.setAdvanceStauts(0);
				if(waybill.getCod()+waybill.getDiscount()>0){
					waybill.setRaterStauts(1);
				}else	waybill.setRaterStauts(0);
				
				save(waybill);
			}
		return errorString;
	}
	//实现做价资料excel导入 的检查
	public String checkMakePriceExcel(File excelFile,String excelFileFileName) throws Exception{
		String returnMark="";
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);
		if(!ros.getCell(0).getStringCellValue().equals("日期")
				||!ros.getCell(1).getStringCellValue().equals("发货人")
				||!ros.getCell(2).getStringCellValue().equals("票号")
				||!ros.getCell(3).getStringCellValue().equals("包数")
				||!ros.getCell(4).getStringCellValue().equals("重量")
				||!ros.getCell(20).getStringCellValue().equals("应收")
				||!ros.getCell(23).getStringCellValue().equals("备注")
				
				
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
					returnMark+=""+(i+1)+"行，第1列<发货日期>不能为空！ ";
					continue;
				}else if(null ==ros.getCell(2)){
					returnMark+=""+(i+1)+"行，第3列<票号>不能为空！";
					continue;
				}
				else if(!this.isBillExsit(this.getValue(ros.getCell(2)))){					
					returnMark+=""+(i+1)+"行，第3列<票号>在数据库不存在 ！\n";
					continue;
				}
				else if(ros.getCell(3).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第4列<包数>不是数值类型！";
					continue;
				}else if(ros.getCell(4).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第5列<重量>不是数值类型！";
					continue;
				}else if(ros.getCell(5).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第6列<垫付$>不是数值类型！";
					continue;
				}else if(ros.getCell(6).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第7列<垫付￥>不是数值类型！";
					continue;
				}else if(ros.getCell(8).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第9列<包费￥>不是数值类型！";
					continue;
				}else if(ros.getCell(7).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第7列<包费$>不是数值类型！";
					continue;
				}else if(ros.getCell(10).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第11列<返率>不是数值类型！";
					continue;
				}
				else if(ros.getCell(11).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第12列<返点>不是数值类型！";
					continue;
				}else if(ros.getCell(12).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第13列<代收>不是数值类型！";
					continue;
				}else if(ros.getCell(13).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第14列<单价>不是数值类型！";
					continue;
				}
				else if(ros.getCell(14).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第15列<货值>不是数值类型！";
					continue;
				}
				else if(ros.getCell(15).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第16列<保率>不是数值类型！";
					continue;
				}
				else if(ros.getCell(16).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第17列<保险费>不是数值类型！";
					continue;
				}else if(ros.getCell(17).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第18列<索赔>不是数值类型！";
					continue;
				}else if(ros.getCell(18).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第19列<汇率>不是数值类型！";
					continue;
				}
				else if(ros.getCell(19).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第20列<运费>不是数值类型！";
					continue;
				}else if(ros.getCell(20).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第21列<应收>不是数值类型！";
					continue;
				}else if(null ==ros.getCell(21)){
				returnMark+=""+(i+1)+"行，第22列<品类>不能为空！";
				continue;
				}
				if(procuratorService.findByProcurator(this.getValue(ros.getCell(9))) == null){
					returnMark+=""+(i+1)+"行，第3列<代理人>数据库中不存在，请重新确认。\n";
				}
				if(markService.findByType(this.getValue(ros.getCell(21))) == null){
					returnMark+=""+(i+1)+"行，第22列<品类>数据库中不存在，请重新确认。\n";
				}
				
				
				if(mapWaybill.get(this.getValue(ros.getCell(2)))==null){
					mapWaybill.put(this.getValue(ros.getCell(2)), this.getValue(ros.getCell(2)));
				}else{
					returnMark+="\n 第"+(i+1)+"行运单号有重复，运单号是： "+this.getValue(ros.getCell(2));
				}
			}
		}
		System.out.println(returnMark);
		return returnMark;
	}
	//做价单的导入
	public String saveInputMakePriceExcel(File excelFile,String excelFileName) throws FileNotFoundException {	
		String errorString="";
		
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(2);		
		for(int i=2;i<=sheet.getLastRowNum();i++){
			ros=sheet.getRow(i);
			Waybill waybill = findByWaybill(this.getValue(ros.getCell(2)));
			Procurator procurator = procuratorService.findByProcurator(this.getValue(ros.getCell(9)));

							
				waybill.setAdvancedZ(Integer.parseInt(this.getValue(ros.getCell(6))));
				waybill.setAdvancedU(Integer.parseInt(this.getValue(ros.getCell(5))));
				waybill.setPackfeeU(Integer.parseInt(this.getValue(ros.getCell(7))));
				waybill.setPackfeeZ(Integer.parseInt(this.getValue(ros.getCell(8))));
				waybill.setProcurator(procurator.getName());
				waybill.setRaterRate(Double.parseDouble(this.getValue(ros.getCell(10))));
				waybill.setDiscount(Double.parseDouble(this.getValue(ros.getCell(11))));
				waybill.setCod(Double.parseDouble(this.getValue(ros.getCell(12))));
				waybill.setPrice(Double.parseDouble(this.getValue(ros.getCell(13))));
				waybill.setWorth(Integer.parseInt(this.getValue(ros.getCell(14))));
				waybill.setWorthRate(Double.parseDouble(this.getValue(ros.getCell(15))));
				waybill.setInsurance(Integer.parseInt(this.getValue(ros.getCell(16))));
				waybill.setIndemnity(Double.parseDouble(this.getValue(ros.getCell(17))));
				waybill.setExchangeRate(Double.parseDouble(this.getValue(ros.getCell(18))));
				waybill.setSumbill(Double.parseDouble(this.getValue(ros.getCell(19))));
				
				waybill.setTotal(Integer.parseInt(this.getValue(ros.getCell(20))));
				waybill.setMark(this.getValue(ros.getCell(21)));
				waybill.setRemarks(this.getValue(ros.getCell(23)));
				
				
				update(waybill);
			}
		return errorString;
	}
	//结算单的检查
	public String checkOutchargeExcel(File excelFile,String excelFileFileName) throws Exception{
		String returnMark="";
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(1);
		if(!ros.getCell(0).getStringCellValue().equals("票号")
				||!ros.getCell(1).getStringCellValue().equals("包数")
				||!ros.getCell(2).getStringCellValue().equals("重量")
				||!ros.getCell(7).getStringCellValue().equals("结算价")
				||!ros.getCell(8).getStringCellValue().equals("外费值")
				||!ros.getCell(9).getStringCellValue().equals("外保率")
				||!ros.getCell(10).getStringCellValue().equals("外保费")
				||!ros.getCell(11).getStringCellValue().equals("总结算")
				
		){
			returnMark="格式不对，请下载模板表格";
			
		}else if(sheet.getLastRowNum()<=0){
			returnMark="这是一个空的模板";
		}else{
			Map mapWaybill = new HashMap();			
			for(int i=2;i<=sheet.getLastRowNum();i++){
				ros = sheet.getRow(i);

				if(null==ros.getCell(0)){
					returnMark+=""+(i+1)+"行，第1列<票号>不能为空！\n ";
					continue;
				}
				else if(!this.isBillExsit(this.getValue(ros.getCell(0)))){					
					returnMark+=""+(i+1)+"行，第1列<运单号>数据库不存在 ！\n";
					continue;
				}
				else if(ros.getCell(2).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第2列<重量>不是数值类型！\n";
					continue;
				}else if(ros.getCell(7).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第3列<结算价>不是数值类型！\n";
					continue;
				}else if(ros.getCell(8).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第4列<货值>不是数值类型！\n";
					continue;
				}else if(ros.getCell(9).getCellType()!= Cell.CELL_TYPE_NUMERIC){
					returnMark+=""+(i+1)+"行，第5列<保率>不是数值类型！\n";
					continue;
				}
				else if(ros.getCell(10).getCellType()!= Cell.CELL_TYPE_NUMERIC &&ros.getCell(10).getCellType() !=Cell.CELL_TYPE_FORMULA){
					returnMark+=""+(i+1)+"行，第6列<保险费$>不是数值类型！\n";
					continue;
				}
				else if(ros.getCell(11).getCellType()!= Cell.CELL_TYPE_NUMERIC &&ros.getCell(11).getCellType() !=Cell.CELL_TYPE_FORMULA){
					returnMark+=""+(i+1)+"行，第7列<结算金额>不是数值类型！\n";
					continue;
				}
				
				
				if(mapWaybill.get(this.getValue(ros.getCell(0)))==null){
					mapWaybill.put(this.getValue(ros.getCell(0)), this.getValue(ros.getCell(0)));
				}else{
					returnMark+="\n 第"+(i+1)+"行运单号有重复，运单号是： "+this.getValue(ros.getCell(2));
				}
				
			
			}
		}
		System.out.println(returnMark);
		return returnMark;
		
		
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
	//应用于结算单的引入
	public String saveInputOutchargeExcel(File excelFile,String excelFileName) throws FileNotFoundException {	
		String errorString="";
		Date date = new Date();
		Workbook book = createWorkbook(new FileInputStream(excelFile),excelFileName);
		Sheet sheet = book.getSheetAt(0);
		Row ros = sheet.getRow(2);
		
		for(int i=2;i<=sheet.getLastRowNum();i++){
			ros=sheet.getRow(i);
			Waybill waybill = findByWaybill(this.getValue(ros.getCell(0)));
			
				
				waybill.setOutPrice(Double.parseDouble(this.getValue(ros.getCell(7))));
				waybill.setOutWorth(Double.parseDouble(this.getValue(ros.getCell(8))));
				waybill.setOutWorthrate(Double.parseDouble(this.getValue(ros.getCell(9))));
				waybill.setOutInsurance(Double.parseDouble(this.getValue(ros.getCell(10))));
				waybill.setOutSum(Double.parseDouble(this.getValue(ros.getCell(11))));
				
				update(waybill);
			}
			
		
		return errorString;
	}
	
	
	
  //导出Excel,做仓单导出
	
	public InputStream getInputStream(String bitch,Integer statusId){
		List<Waybill> list = this.findByBitchAndStatusId(bitch, statusId);
		InputStream is = null;
		Workbook book = new HSSFWorkbook();
		String sheetName="";
		String titleName="";
		if(statusId==1){
			sheetName="未配仓明细";
			titleName="258库房 未配仓明细 "+bitch;
			Sheet sheet = book.createSheet(sheetName);
			//--页边距设置
			HSSFPrintSetup ps = (HSSFPrintSetup) sheet.getPrintSetup();
	        ps.setLandscape(false); //打印方向，true:横向，false:纵向
	        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
	        sheet.setMargin(HSSFSheet.BottomMargin, (double)0.5); //页边距（下）
	        sheet.setMargin(HSSFSheet.LeftMargin, (double)0.1); //页边距（左）
	        sheet.setMargin(HSSFSheet.RightMargin, (double)0.1); //页边距（右）
	        sheet.setMargin(HSSFSheet.TopMargin, (double)0.5); //页边距（上）
	        sheet.setHorizontallyCenter(true); //设置打印页面为水平居中
	        //sheet.setVerticallyCenter(true); //设置打印页面为垂直居中
	      //--列宽设置
			sheet.setColumnWidth(0, 2900);
			sheet.setColumnWidth(1, 7000);
			sheet.setColumnWidth(2, 1800);
			sheet.setColumnWidth(3, 2000);
			sheet.setColumnWidth(4, 2000);
			sheet.setColumnWidth(5, 5000);
			sheet.setColumnWidth(6, 1800);
			sheet.setColumnWidth(7, 1500);
			sheet.setColumnWidth(8, 2000);
			//--样式设置
			HSSFCellStyle headerStyle = (HSSFCellStyle) book.createCellStyle();// 创建标题样式  
			headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
			headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中  
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
			
			short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
			dateCellStyle.setDataFormat(df);			
			
			
			Row row = sheet.createRow(0);
			
			
			HSSFCell cell =(HSSFCell) row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
			
			row.getCell(0).setCellValue(titleName);		
			row.getCell(0).setCellStyle(titleStyle);
			
			row = sheet.createRow(1);
			row.setHeight((short) 400);//目的是想把行高设置成400px
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("日期");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("票号");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("包数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue("重量");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue("体积");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("品名");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue("数量");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue("目的地");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue("发货人");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue("次序");
			cell.setCellStyle(headerStyle);
			
			
			CellStyle styDate = book.createCellStyle();	
			
			CellStyle styWidth= book.createCellStyle();
			
				int i=2;
				int pics= 0;
				Double weight =0.0;
				Double volum=0.00;
				
				for(int j=0;j<list.size();j++){
					Waybill waybill = list.get(j);				
					
					row = sheet.createRow(i);
					cell = (HSSFCell) row.createCell(0);
					cell.setCellValue(waybill.getSddate());
					cell.setCellStyle(dateCellStyle);
					cell = (HSSFCell) row.createCell(1);
					cell.setCellValue(waybill.getWaybill());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(2);
					cell.setCellValue(waybill.getPics());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(3);
					cell.setCellValue(waybill.getWeight());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(4);
					cell.setCellValue(waybill.getVolumu());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(5);
					cell.setCellValue(waybill.getGoods());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(6);
					cell.setCellValue(waybill.getQuantity());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(7);
					cell.setCellValue(waybill.getDestName());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(8);
					cell.setCellValue(waybill.getSender());
					cell.setCellStyle(cellStyle);
					
					pics +=waybill.getPics();
					weight+=waybill.getWeight();
					volum +=waybill.getVolumu();
					
					i++;
				}
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(pics);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(weight);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(volum);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
			
			File file = new File("仓单.xls");
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
		}else if(statusId==2){
			sheetName="配仓明细";
			titleName="258库房 配仓单  "+bitch;
			Sheet sheet = book.createSheet(sheetName);
			//--页边距设置
			HSSFPrintSetup ps = (HSSFPrintSetup) sheet.getPrintSetup();
	        ps.setLandscape(false); //打印方向，true:横向，false:纵向
	        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
	        sheet.setMargin(HSSFSheet.BottomMargin, (double)0.5); //页边距（下）
	        sheet.setMargin(HSSFSheet.LeftMargin, (double)0.1); //页边距（左）
	        sheet.setMargin(HSSFSheet.RightMargin, (double)0.1); //页边距（右）
	        sheet.setMargin(HSSFSheet.TopMargin, (double)0.5); //页边距（上）
	        sheet.setHorizontallyCenter(true); //设置打印页面为水平居中
	        //sheet.setVerticallyCenter(true); //设置打印页面为垂直居中
	      //--列宽设置
			sheet.setColumnWidth(0, 2900);
			sheet.setColumnWidth(1, 6500);
			sheet.setColumnWidth(2, 1800);
			sheet.setColumnWidth(3, 2000);
			sheet.setColumnWidth(4, 1800);
			sheet.setColumnWidth(5, 4200);
			sheet.setColumnWidth(6, 1700);
			sheet.setColumnWidth(7, 2100);
			sheet.setColumnWidth(8, 1900);
			sheet.setColumnWidth(9, 1600);
			//--样式设置
			HSSFCellStyle headerStyle = (HSSFCellStyle) book.createCellStyle();// 创建标题样式  
			headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
			headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中  
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
			short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
			dateCellStyle.setDataFormat(df);
			
			Row row = sheet.createRow(0);
			
			
			HSSFCell cell =(HSSFCell) row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
			
			row.getCell(0).setCellValue(titleName);		
			row.getCell(0).setCellStyle(titleStyle);
			
			row = sheet.createRow(1);
			row.setHeight((short) 400);//目的是想把行高设置成400px
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("日期");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("票号");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("包数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue("重量");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue("体积");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("品名");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue("数量");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue("目的地");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue("发货人");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue("次序");
			cell.setCellStyle(headerStyle);
			
			
			CellStyle styDate = book.createCellStyle();	
			
			CellStyle styWidth= book.createCellStyle();
			
				int i=2;
				int pics= 0;
				Double weight =0.0;
				Double volum=0.00;
				
				for(int j=0;j<list.size();j++){
					Waybill waybill = list.get(j);				
					
					row = sheet.createRow(i);
					cell = (HSSFCell) row.createCell(0);
					cell.setCellValue(waybill.getSddate());
					cell.setCellStyle(dateCellStyle);
					cell = (HSSFCell) row.createCell(1);
					cell.setCellValue(waybill.getWaybill());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(2);
					cell.setCellValue(waybill.getPics());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(3);
					cell.setCellValue(waybill.getWeight());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(4);
					cell.setCellValue(waybill.getVolumu());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(5);
					cell.setCellValue(waybill.getGoods());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(6);
					cell.setCellValue(waybill.getQuantity());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(7);
					cell.setCellValue(waybill.getDestName());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(8);
					cell.setCellValue(waybill.getSender());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(9);
					cell.setCellValue(waybill.getCangId());
					cell.setCellStyle(cellStyle);
					pics +=waybill.getPics();
					weight+=waybill.getWeight();
					volum +=waybill.getVolumu();
					
					i++;
				}
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(pics);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(weight);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(volum);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
			
			File file = new File("仓单.xls");
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
		}	
		
	
		return is;
	}
 //做运单Word 格式
	public void getOneReportStream(Integer id){
		Waybill wb =this.findById(id);
		
		 String templatePath = "D:\\word\\template.doc";  
	      InputStream is=null;
	      HWPFDocument doc =null;
		try {
			is = new FileInputStream(templatePath);
			doc = new HWPFDocument(is);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		 Range range = doc.getRange();  
	      //把range范围内的${reportDate}替换为当前的日期  
	      range.replaceText("sdId",wb.getSdId().toString()); 
	      /*
	      range.replaceText("sender",wb.getSender());
	      range.replaceText("sdTel",wb.getSdTel());
	      range.replaceText("sddate",new SimpleDateFormat("yyyy-MM-dd").format(wb.getSddate()));
	     
	      range.replaceText("transType",wb.getTransType());
	      range.replaceText("waybill",wb.getWaybill());
	      range.replaceText("custId",wb.getCustId());
	      range.replaceText("custName",wb.getCustName());
	      range.replaceText("custTel",wb.getCustTel());
	      range.replaceText("custEmail",wb.getCustEmail());
	      range.replaceText("goods",wb.getGoods());
	      range.replaceText("quantity",wb.getQuantity().toString());
	      range.replaceText("pics",wb.getPics().toString());
	      range.replaceText("volumu",wb.getVolumu().toString());
	      range.replaceText("weight",wb.getWeight().toString());
	      range.replaceText("price",wb.getPrice().toString());
	      Double adv = wb.getAdvancedZ()/wb.getExchangeRate();
	      Double packfee = wb.getPackfeeZ()/wb.getExchangeRate();
	      DecimalFormat dfw = new DecimalFormat("0");
	      
	      String ad = dfw.format(adv+wb.getAdvancedU());
	      String pf = dfw.format(packfee+wb.getPackfeeU());
	      
	      range.replaceText("advanced",ad);
	      range.replaceText("packfee",pf);
	      range.replaceText("worth",wb.getWorth().toString());
	      range.replaceText("insurance",wb.getInsurance().toString());
	      range.replaceText("indemnity",wb.getIndemnity().toString());
	      range.replaceText("total",wb.getTotal().toString());
	      range.replaceText("remarks",wb.getRemarks());
	      range.replaceText("exchangeRate",wb.getExchangeRate().toString());
		 */
		 
		File file = new File("D:\\word\\仓单.doc");
		 ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		OutputStream os=null;
		try {
				os = new FileOutputStream(file);
				doc.write(ostream);
				os.write(ostream.toByteArray());
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ostream.close();
			
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
	}
	//导出Excel,做运单导出
	public InputStream getReportStream(String ids){
		List<Waybill> list = this.waybillDao.listByIds(ids);
		String sheetName="对账单";
		String titleName="258 КАРГО 货物运输单   北京Пекин — 莫斯科Москва";
		
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
		sheet.setColumnWidth(1, 7000);
		sheet.setColumnWidth(2, 1800);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 2000);
		sheet.setColumnWidth(5, 4500);
		sheet.setColumnWidth(6, 1800);
		sheet.setColumnWidth(7, 2200);
		sheet.setColumnWidth(8, 2100);
		sheet.setColumnWidth(9, 2200);
		sheet.setColumnWidth(10, 2100);
		sheet.setColumnWidth(11, 2000);
		sheet.setColumnWidth(12, 2000);
		sheet.setColumnWidth(13, 2000);
		sheet.setColumnWidth(14, 2000);
		sheet.setColumnWidth(15, 2000);
		sheet.setColumnWidth(16, 2100);
		sheet.setColumnWidth(17, 1500);
		sheet.setColumnWidth(18, 2000);
		sheet.setColumnWidth(19, 2000);
		sheet.setColumnWidth(20, 5000);
		sheet.setColumnWidth(21, 7000);
		

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
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 21));
		
		row.getCell(0).setCellValue(titleName);		
		row.getCell(0).setCellStyle(titleStyle);
		
		row = sheet.createRow(1);
		row.setHeight((short) 600);//目的是想把行高设置成400px
		cell = (HSSFCell) row.createCell(0);
		cell.setCellValue("日期");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(1);
		cell.setCellValue("票号");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(2);
		cell.setCellValue("包数");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(3);
		cell.setCellValue("重量");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(4);
		cell.setCellValue("体积");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(5);
		cell.setCellValue("品名");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(6);
		cell.setCellValue("小件数");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(7);
		cell.setCellValue("垫付￥");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(8);
		cell.setCellValue("垫付$");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(9);
		cell.setCellValue("包费￥");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(10);
		cell.setCellValue("包费$");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(11);
		cell.setCellValue("单价$");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(12);
		cell.setCellValue("保价$");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(13);
		cell.setCellValue("保率");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(14);
		cell.setCellValue("保险费$");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(15);
		cell.setCellValue("应收$");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(16);
		cell.setCellValue("目的地");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(17);
		cell.setCellValue("付款方式");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(18);
		cell.setCellValue("发货人");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(19);
		cell.setCellValue("客户号");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(20);
		cell.setCellValue("收货人");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(21);
		cell.setCellValue("收货电话");
		cell.setCellStyle(headerStyle);
		
		
										
		
		
			int i=2;
			int pics= 0;
			Double weight =0.0;
			Double volum=0.00;
			Integer advancedZ =0;
			Integer advancedU=0;
			Integer packfeeU=0;
			Integer packfeeZ=0;
			Integer worth=0;
			Integer insurance =0;
			Integer total=0; //
			for(int j=0;j<list.size();j++){
				Waybill waybill = list.get(j);				
				
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue(waybill.getSddate());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(waybill.getWaybill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(waybill.getPics());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(waybill.getWeight());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(waybill.getVolumu());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue(waybill.getGoods());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue(waybill.getQuantity());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue(waybill.getAdvancedZ());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue(waybill.getAdvancedU());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue(waybill.getPackfeeZ());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(10);
				cell.setCellValue(waybill.getPackfeeU());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(11);
				Double price = waybill.getPrice()+waybill.getRaterRate();
				cell.setCellValue(price);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(12);
				cell.setCellValue(waybill.getWorth());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(13);
				cell.setCellValue(waybill.getWorthRate());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(14);
				cell.setCellValue(waybill.getInsurance());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(15);
				cell.setCellValue(waybill.getTotal());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(16);
				cell.setCellValue(waybill.getDestName());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(17);
				cell.setCellValue(waybill.getPayMethod());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(18);
				cell.setCellValue(waybill.getSender());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(19);
				cell.setCellValue(waybill.getCustId());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(20);
				cell.setCellValue(waybill.getCustName());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(21);
				cell.setCellValue(waybill.getCustTel());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(21);
				cell.setCellValue(waybill.getCustTel());
				cell.setCellStyle(cellStyle);
				
				
				
				pics +=waybill.getPics();
				weight+=waybill.getWeight();
				volum +=waybill.getVolumu();
				advancedZ +=waybill.getAdvancedZ();
				advancedU +=waybill.getAdvancedU();
				packfeeU += waybill.getPackfeeU();
				packfeeZ+= waybill.getPackfeeZ();
				
				
				worth +=waybill.getWorth();
				insurance +=waybill.getInsurance();
				total+=waybill.getTotal(); //
				
				i++;
			}
			row = sheet.createRow(i);
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue(pics);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue(weight);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue(volum);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue(advancedZ);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue(advancedU);
			cell.setCellStyle(cellStyle);			
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue(packfeeZ);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(10);
			cell.setCellValue(packfeeU);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(11);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(12);
			cell.setCellValue(worth);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(13);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(14);
			cell.setCellValue(insurance);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(15);
			cell.setCellValue(total);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(16);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(17);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(18);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(19);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(20);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(21);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			
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
	//生成到货报表
	public InputStream getTrackReportSream(String waybills){
		InputStream is = null;
		Workbook book;
		book = new HSSFWorkbook();
		String titleName ="到货信息";
		Sheet sheet = book.createSheet("到货信息表");
		
		//数据获得
		List<Waybill> list =new ArrayList<Waybill>();
		String[] waybs =waybills.split(",");
		for(int i=0;i<waybs.length;i++){
			System.out.println("Waybill Service: waybill :"+waybs[i]);  //输出语句
			
			Waybill wb = this.findByWaybill(waybs[i]);
			
			list.add(wb);
			
		}
		
		
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
        sheet.setDisplayGridlines(true); //设置显示网格线	

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
		
		
		HSSFCellStyle dateCellStyle=(HSSFCellStyle) book.createCellStyle();// 日期样式  
		short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
		dateCellStyle.setDataFormat(df);
		dateCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
		dateCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中 
		
			 //--列宽设置
	        sheet.setColumnWidth(0, 2900); //发货日期
	        sheet.setColumnWidth(1, 6000); //票号
			sheet.setColumnWidth(2, 1800); //件数 
			sheet.setColumnWidth(3, 1800); //重量
			sheet.setColumnWidth(4, 1800);//体积
			sheet.setColumnWidth(5, 2700); //到货日期
			sheet.setColumnWidth(6, 2500); //到货包数
			sheet.setColumnWidth(7, 2500);	//完好状态
			sheet.setColumnWidth(8, 10000); //备注	
			sheet.setColumnWidth(9, 2000); //运期	
			
			
			
			Row row = sheet.createRow(0);
						
			HSSFCell cell =(HSSFCell) row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
			
			row.getCell(0).setCellValue(titleName);		
			row.getCell(0).setCellStyle(titleStyle);
			
			row = sheet.createRow(1);
			row.setHeight((short) 600);//目的是想把行高设置成400px
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("发货日期");
			cell.setCellStyle(headerStyle);
		
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("票号");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("包数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue("重量");
			cell.setCellStyle(headerStyle);
		
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue("体积");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("到货日期");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue("到货包数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue("完好状态");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue("备注");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue("运期");
			cell.setCellStyle(headerStyle);
			
			int i=2;
			int pics= 0;
			int arrPics =0;
			Double weight =0.0;
			Double volum=0.00;
		
			System.out.println("waybillListSize:"+list.size()); //输出检查
			for(int j=0;j<list.size();j++){
				Waybill waybill = list.get(j);				
				List<Track> trackList =this.trackDao.findByWaybill(waybill.getWaybill());
				row = sheet.createRow(i);
				row.setHeight((short) 500);
				
				
				
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue(waybill.getSddate());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(waybill.getWaybill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(waybill.getPics());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(waybill.getWeight());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(waybill.getVolumu());
				cell.setCellStyle(cellStyle);
				
				
				
				if(trackList.size()>=1){
						for(int k=0;k<trackList.size();k++){
						
							Track track = trackList.get(k);					
							String w = track.getWstatus()==0?"完好":"有破损或丢失";
							
							cell = (HSSFCell) row.createCell(5);
							cell.setCellValue(track.getArriveDate());
							cell.setCellStyle(dateCellStyle);
							cell = (HSSFCell) row.createCell(6);
							cell.setCellValue(track.getPics());
							cell.setCellStyle(cellStyle);
							cell = (HSSFCell) row.createCell(7);
							cell.setCellValue(w);
							cell.setCellStyle(cellStyle);
							cell = (HSSFCell) row.createCell(8);
							cell.setCellValue(track.getRemarks());
							cell.setCellStyle(cellStyle);
							
							//计算实际运期
							Long between = ((Long)track.getArriveDate().getTime()-waybill.getSddate().getTime())/1000;
							Long yq = between/(24*3600);
							
							cell = (HSSFCell) row.createCell(9);
							cell.setCellValue(yq);
							cell.setCellStyle(cellStyle);
							
							i++;
							arrPics += track.getPics();
							
							row = sheet.createRow(i);
							row.setHeight((short) 500);
							cell = (HSSFCell) row.createCell(0);
							cell.setCellValue(waybill.getSddate());
							cell.setCellStyle(dateCellStyle);
							
						}
						
						sheet.addMergedRegion(new CellRangeAddress(i-trackList.size(), i-1, 1, 1));
						sheet.addMergedRegion(new CellRangeAddress(i-trackList.size(), i-1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(i-trackList.size(), i-1, 3, 3));
						sheet.addMergedRegion(new CellRangeAddress(i-trackList.size(), i-1, 4, 4));
						
					}else{
						cell = (HSSFCell) row.createCell(5);
						cell.setCellValue("-");
						cell.setCellStyle(cellStyle);
						cell = (HSSFCell) row.createCell(6);
						cell.setCellValue(0);
						cell.setCellStyle(cellStyle);
						cell = (HSSFCell) row.createCell(7);
						cell.setCellValue("");
						cell.setCellStyle(cellStyle);
						cell = (HSSFCell) row.createCell(8);
						cell.setCellValue("");
						cell.setCellStyle(cellStyle);
					
					
					
					
					
					i++;
				}
								
				pics +=waybill.getPics();
				weight += waybill.getWeight();
				volum += waybill.getVolumu();
				
				
			}
			
			row = sheet.createRow(i);
			row.setHeight((short) 500);
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue(pics);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue(weight);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue(volum);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue(arrPics);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			
		
		File file = new File("TrackInfo.xls");
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
	
	//生成批次报表
	public InputStream getMocReportStream(String bitch ,Integer typeId){
		InputStream is = null;
		Workbook book;
		List<Waybill> list = this.waybillDao.listByBitch(bitch);
		String lineId = this.bitchDao.findLineIdByBitch(bitch);
		System.out.println("List:"+list.size());
		if(typeId==1){
		book = new HSSFWorkbook();
		System.out.println("Type:"+typeId);
		String sheetName="莫办报表";
		String titleName="258 КАРГО 莫办报表 【"+lineId+"】"+bitch;
		
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
		short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
		dateCellStyle.setDataFormat(df);
			 //--列宽设置
	        sheet.setColumnWidth(0, 2850); //日期
	        sheet.setColumnWidth(1, 6000); //票号
			sheet.setColumnWidth(2, 1800); //件数 
			sheet.setColumnWidth(3, 1800); 
			sheet.setColumnWidth(4, 1800);
			sheet.setColumnWidth(5, 6000); //品名
			sheet.setColumnWidth(6, 1800);//数量
			sheet.setColumnWidth(7, 2200);	//应收
			sheet.setColumnWidth(8, 2000); //实收款
			sheet.setColumnWidth(9, 2200);	//地址
			sheet.setColumnWidth(10, 1800); //付款方式
			sheet.setColumnWidth(11, 2000); //发货人
			sheet.setColumnWidth(12, 2000); //客户号
			sheet.setColumnWidth(13, 6500); //收货人
			sheet.setColumnWidth(14, 7000); //电话
			sheet.setColumnWidth(15, 2200); //外付款
			sheet.setColumnWidth(16, 2200); //批次
			sheet.setColumnWidth(17, 2500); //备注
			sheet.setColumnWidth(18, 2500); //到货日期
			sheet.setColumnWidth(19, 2500); //到货包数
			sheet.setColumnWidth(20, 2500); //存货情况
			
			
			Row row = sheet.createRow(0);
						
			HSSFCell cell =(HSSFCell) row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 20));
			
			row.getCell(0).setCellValue(titleName);		
			row.getCell(0).setCellStyle(titleStyle);
			
			row = sheet.createRow(1);
			row.setHeight((short) 600);//目的是想把行高设置成400px
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("日期");
			cell.setCellStyle(headerStyle);
		
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("票号");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("包数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue("重量");
			cell.setCellStyle(headerStyle);
		
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue("体积");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("品名");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue("小件数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue("应收款");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue("实收款");
			cell.setCellStyle(headerStyle);
			
			
			
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue("市场");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(10);
			cell.setCellValue("付款方式");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(11);
			cell.setCellValue("发货人");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(12);
			cell.setCellValue("客户号");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(13);
			cell.setCellValue("收货人");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(14);
			cell.setCellValue("电话");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(15);
			cell.setCellValue("外付款");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(16);
			cell.setCellValue("批次");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(17);
			cell.setCellValue("备注");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(18);
			cell.setCellValue("到货日期");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(19);
			cell.setCellValue("到货包数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(20);
			cell.setCellValue("存货情况");
			cell.setCellStyle(headerStyle);
					
				int i=2;
				int pics= 0;
				
				Double weight =0.0;
				Double volum=0.00;
			
				Integer total=0; //
				Integer actualSum =0;
				Double outSum =0.0;
				for(int j=0;j<list.size();j++){
					Waybill waybill = list.get(j);				
					
					row = sheet.createRow(i);
					cell = (HSSFCell) row.createCell(0);
					cell.setCellValue(waybill.getSddate());
					cell.setCellStyle(dateCellStyle);
					cell = (HSSFCell) row.createCell(1);
					cell.setCellValue(waybill.getWaybill());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(2);
					cell.setCellValue(waybill.getPics());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(3);
					cell.setCellValue(waybill.getWeight());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(4);
					cell.setCellValue(waybill.getVolumu());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(5);
					cell.setCellValue(waybill.getGoods());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(6);
					cell.setCellValue(waybill.getQuantity());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(7);
					cell.setCellValue(waybill.getTotal());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(8);
					cell.setCellValue(waybill.getActualSum());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(9);
					cell.setCellValue(waybill.getDestName());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(10);
					cell.setCellValue(waybill.getPayMethod());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(11);
					cell.setCellValue(waybill.getSender());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(12);
					cell.setCellValue(waybill.getCustId());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(13);
					cell.setCellValue(waybill.getCustName());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(14);
					cell.setCellValue(waybill.getCustTel());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(15);
					cell.setCellValue(waybill.getOutSum());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(16);
					cell.setCellValue(waybill.getBitch());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(17);
					cell.setCellValue(waybill.getRemarks());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(18);
					cell.setCellValue("");
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(19);
					cell.setCellValue("");
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(20);
					cell.setCellValue("");
					cell.setCellStyle(cellStyle);
					
					pics +=waybill.getPics();
					weight += waybill.getWeight();
					volum += waybill.getVolumu();
					total +=waybill.getTotal();
					outSum +=waybill.getOutSum();
					actualSum += waybill.getActualSum();
					i++;
				}
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(pics);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(weight);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(volum);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue(total);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue(actualSum);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);			
				cell = (HSSFCell) row.createCell(10);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(11);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(12);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(13);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(14);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(15);
				cell.setCellValue(outSum);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(16);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(17);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(18);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(19);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(20);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				
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
		}else if(typeId==2){
			
			book = new HSSFWorkbook();
			String sheetName="财务报表";
			String titleName="258 КАРГО 财务报表    "+lineId+"-"+bitch;
			System.out.println(sheetName);
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
			 //--列宽设置
		       
			
	        sheet.setColumnWidth(0, 8000); //票号
			sheet.setColumnWidth(1, 2000); //件数 
			sheet.setColumnWidth(2, 2200); //重量
			sheet.setColumnWidth(3, 2200);		//体积	
			sheet.setColumnWidth(4, 2500); //应收款
			sheet.setColumnWidth(5, 2600); //付款方式
			sheet.setColumnWidth(6, 2000); //经办人
			sheet.setColumnWidth(7, 2500); //发货人
			sheet.setColumnWidth(8, 2000); //客户号
			sheet.setColumnWidth(9, 7500); //客户名
			sheet.setColumnWidth(10, 2000); //外结算
			
			
			
			Row row = sheet.createRow(0);
			
			
			HSSFCell cell =(HSSFCell) row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
			
			row.getCell(0).setCellValue(titleName);		
			row.getCell(0).setCellStyle(titleStyle);
			
			row = sheet.createRow(1);
			row.setHeight((short) 600);//目的是想把行高设置成400px
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("票号");
			cell.setCellStyle(headerStyle);		
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("包数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("重量");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue("体积");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(4);		
			cell.setCellValue("应收合计");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(5);		
			cell.setCellValue("付款方式");
			cell.setCellStyle(headerStyle);			
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue("经办人");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue("发货人");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue("客户号");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue("客户名");
			cell.setCellStyle(headerStyle);
			
			cell = (HSSFCell) row.createCell(10);			
			cell.setCellValue("清关费");
			cell.setCellStyle(headerStyle);
			
			
			
			
			
					
				int i=2;
				int pics= 0;
				Double weight =0.0;
				Double volum=0.00;
				
				Integer total=0; //
				
				Double outSum =0.0;
				
				
				
				for(int j=0;j<list.size();j++){
					Waybill waybill = list.get(j);				
					
					row = sheet.createRow(i);
					
					cell = (HSSFCell) row.createCell(0);
					cell.setCellValue(waybill.getWaybill());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(1);
					cell.setCellValue(waybill.getPics());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(2);
					cell.setCellValue(waybill.getWeight());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(3);
					cell.setCellValue(waybill.getVolumu());
					cell.setCellStyle(cellStyle);

					cell = (HSSFCell) row.createCell(4);
					cell.setCellValue(waybill.getTotal());
					cell.setCellStyle(cellStyle);

					cell = (HSSFCell) row.createCell(5);
					cell.setCellValue(waybill.getPayMethod());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(6);
					cell.setCellValue(waybill.getRaterName());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(7);
					cell.setCellValue(waybill.getSender());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(8);
					cell.setCellValue(waybill.getCustId());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(9);
					cell.setCellValue(waybill.getCustName());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(10);
					cell.setCellValue(waybill.getOutSum());
					cell.setCellStyle(cellStyle);
					
					
					
					
					pics +=waybill.getPics();
					weight += waybill.getWeight();					
					volum += waybill.getVolumu();
					
					total +=waybill.getTotal();
					
					outSum +=waybill.getOutSum();
					
					i++;
				}
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(pics);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(weight);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(volum);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(total);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(10);
				cell.setCellValue(outSum);
				cell.setCellStyle(cellStyle);
				
				
				
				
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
		
		}else if(typeId==3){
			
			book = new HSSFWorkbook();
			String sheetName="总报表";
			String titleName="258 КАРГО 总报表"+lineId+"-"+bitch;
			System.out.println(sheetName);
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
			short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
			dateCellStyle.setDataFormat(df);
			
			 //--列宽设置
			sheet.setColumnWidth(0, 2900); //日期
	        sheet.setColumnWidth(1, 7000); //票号
			sheet.setColumnWidth(2, 1800); //件数 
			sheet.setColumnWidth(3, 1800); //重量
			sheet.setColumnWidth(4, 1800); //体积
			sheet.setColumnWidth(5, 9000); //品名
			sheet.setColumnWidth(6, 2000); //品类
			sheet.setColumnWidth(7, 1800);//数量
			sheet.setColumnWidth(8, 1800); //垫付款￥
			sheet.setColumnWidth(9, 1800); //包费￥
			sheet.setColumnWidth(10, 1800);  //垫付款$
			sheet.setColumnWidth(11, 1800);//包费$
			sheet.setColumnWidth(12, 2000); //汇率
			sheet.setColumnWidth(13, 1800); //单价
			sheet.setColumnWidth(14, 2000); //运费
			sheet.setColumnWidth(15, 1800); //货值
			sheet.setColumnWidth(16, 1800);//保率
			sheet.setColumnWidth(17, 1800); //保险费
			sheet.setColumnWidth(18, 2000); //应收
			sheet.setColumnWidth(19, 2000); //实收款
			sheet.setColumnWidth(20, 2200); //付款方式
			sheet.setColumnWidth(21, 2200); //市场
			sheet.setColumnWidth(22, 1800); //发货人
			sheet.setColumnWidth(23, 1800); //客户号
			sheet.setColumnWidth(24, 4000); //批次
			sheet.setColumnWidth(25, 2000); //线路
			sheet.setColumnWidth(26, 2000); //是否核销
			sheet.setColumnWidth(27, 2000); //核销编号
			sheet.setColumnWidth(28, 2500); //备注
			sheet.setColumnWidth(29, 2000); //代理人
			sheet.setColumnWidth(30, 2000); //经办人
			sheet.setColumnWidth(31, 1800); //代收款
			sheet.setColumnWidth(32, 1800); //拆扣率
			sheet.setColumnWidth(33, 1800); //代理费	
			
			sheet.setColumnWidth(34, 1800); //索赔
			sheet.setColumnWidth(35, 1800); //外配价
			sheet.setColumnWidth(36, 1800); //外货值
			sheet.setColumnWidth(37, 1800); //外保率
			sheet.setColumnWidth(38, 1800); //外保费
			sheet.setColumnWidth(39, 2000); //外合计		
			sheet.setColumnWidth(40, 1800); //状态
			sheet.setColumnWidth(41, 2000); //未加包费
			sheet.setColumnWidth(42, 2000); //未加运费
			
			sheet.setColumnWidth(43, 2000); //货源地
			sheet.setColumnWidth(44, 7000); //收货人
			sheet.setColumnWidth(45, 7000); //电话
			sheet.setColumnWidth(46, 2200);	//地址
			
			
			Row row = sheet.createRow(0);
			
			HSSFCell cell =(HSSFCell) row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 46));
			
			row.getCell(0).setCellValue(titleName);		
			row.getCell(0).setCellStyle(titleStyle);
			
			row = sheet.createRow(1);
			row.setHeight((short) 600);//目的是想把行高设置成400px
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("日期");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("票号");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("包数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue("重量");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue("体积");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("品名");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue("品类");
			cell.setCellStyle(headerStyle);

			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue("小件数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue("垫付款￥");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue("包费￥");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(10);
			cell.setCellValue("垫付款$");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(11);
			cell.setCellValue("包费$");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(12);
			cell.setCellValue("汇率");
			cell.setCellStyle(headerStyle);
			
			cell = (HSSFCell) row.createCell(13);
			cell.setCellValue("单价");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(14);
			cell.setCellValue("运费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(15);
			cell.setCellValue("货值");
			cell.setCellStyle(headerStyle);
			
			cell = (HSSFCell) row.createCell(16);
			cell.setCellValue("保率");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(17);
			cell.setCellValue("保险费");
			cell.setCellStyle(headerStyle);
			
			cell = (HSSFCell) row.createCell(18);
			cell.setCellValue("应收款");
			cell.setCellStyle(headerStyle);

			cell = (HSSFCell) row.createCell(19);
			cell.setCellValue("实收");
			cell.setCellStyle(headerStyle);

			cell = (HSSFCell) row.createCell(20);
			cell.setCellValue("付款方式");
			cell.setCellStyle(headerStyle);
			
			cell = (HSSFCell) row.createCell(21);
			cell.setCellValue("市场");
			cell.setCellStyle(headerStyle);
			
			cell = (HSSFCell) row.createCell(22);
			cell.setCellValue("发货人");
			cell.setCellStyle(headerStyle);
			
			cell = (HSSFCell) row.createCell(23);
			cell.setCellValue("客户号");
			cell.setCellStyle(headerStyle);
			
			cell = (HSSFCell) row.createCell(24);
			cell.setCellValue("批次");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(25);
			cell.setCellValue("线路");
			cell.setCellStyle(headerStyle);
			
			cell = (HSSFCell) row.createCell(26);
			cell.setCellValue("是否核销");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(27);
			cell.setCellValue("核销编号");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(28);
			cell.setCellValue("备注");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(29);
			cell.setCellValue("代理人");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(30);
			cell.setCellValue("经办人");
			cell.setCellStyle(headerStyle);

			cell = (HSSFCell) row.createCell(31);
			cell.setCellValue("代收款");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(32);
			cell.setCellValue("折扣率");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(33);
			cell.setCellValue("代理费");
			cell.setCellStyle(headerStyle);
			
			cell = (HSSFCell) row.createCell(34);
			cell.setCellValue("索赔");
			cell.setCellStyle(headerStyle);
			
			cell = (HSSFCell) row.createCell(35);
			cell.setCellValue("结算价");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(36);
			cell.setCellValue("外费值");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(37);
			cell.setCellValue("外保率");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(38);
			cell.setCellValue("外保费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(39);
			cell.setCellValue("总结算");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(40);
			cell.setCellValue("状态");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(41);
			cell.setCellValue("未加包费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(42);
			cell.setCellValue("未加运费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(43);
			cell.setCellValue("货源地");
			cell.setCellStyle(headerStyle);
			
			
			cell = (HSSFCell) row.createCell(44);
			cell.setCellValue("收货人");
			cell.setCellStyle(headerStyle);

			cell = (HSSFCell) row.createCell(45);
			cell.setCellValue("电话");
			cell.setCellStyle(headerStyle);

			cell = (HSSFCell) row.createCell(46);
			cell.setCellValue("地址");
			cell.setCellStyle(headerStyle);

						
					
				int i=2;
				int pics= 0;
				Double weight =0.0;
				Double volum=0.00;
				Integer advanceU =0;
				Integer advanceZ =0;
				Integer packfeeU =0;
				Integer packfeeZ =0;
				Integer worth =0;
				Integer insurance =0;
				Double sumbill=0.0;
				Double indemnity=0.0;
				Double cod =0.0;
				Double discount=0.0;
				Integer total=0; //
				Integer actualSum =0;
				Double outworth =0.0;
				Double outInsurance=0.0;
				Double outSum =0.0;
				Integer noadv =0;
				Integer nopack =0;
				
				
				for(int j=0;j<list.size();j++){
					Waybill waybill = list.get(j);				
					
					row = sheet.createRow(i);
					
					cell = (HSSFCell) row.createCell(0);
					cell.setCellValue(waybill.getSddate());
					cell.setCellStyle(dateCellStyle);
					cell = (HSSFCell) row.createCell(1);
					cell.setCellValue(waybill.getWaybill());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(2);
					cell.setCellValue(waybill.getPics());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(3);
					cell.setCellValue(waybill.getWeight());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(4);
					cell.setCellValue(waybill.getVolumu());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(5);
					cell.setCellValue(waybill.getGoods());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(6);
					cell.setCellValue(waybill.getMark());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(7);
					cell.setCellValue(waybill.getQuantity());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(8);
					cell.setCellValue(waybill.getAdvancedZ());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(9);
					cell.setCellValue(waybill.getPackfeeZ());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(10);
					cell.setCellValue(waybill.getAdvancedU());
					cell.setCellStyle(cellStyle);					
					cell = (HSSFCell) row.createCell(11);
					cell.setCellValue(waybill.getPackfeeU());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(12);
					cell.setCellValue(waybill.getExchangeRate());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(13);
					cell.setCellValue(waybill.getPrice());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(14);
					cell.setCellValue(waybill.getSumbill());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(15);
					cell.setCellValue(waybill.getWorth());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(16);
					cell.setCellValue(waybill.getWorthRate());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(17);
					cell.setCellValue(waybill.getInsurance());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(18);
					cell.setCellValue(waybill.getTotal());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(19);
					cell.setCellValue(waybill.getActualSum());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(20);
					cell.setCellValue(waybill.getPayMethod());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(21);
					cell.setCellValue(waybill.getDestName());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(22);
					cell.setCellValue(waybill.getSender());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(23);
					cell.setCellValue(waybill.getCustId());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(24);
					cell.setCellValue(waybill.getBitch());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(25);
					cell.setCellValue(waybill.getLineId());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(26);
					cell.setCellValue(waybill.getIsRebate());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(27);
					cell.setCellValue(waybill.getRebateId());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(28);
					cell.setCellValue(waybill.getRemarks());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(29);
					cell.setCellValue(waybill.getProcurator());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(30);
					cell.setCellValue(waybill.getRaterName());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(31);
					cell.setCellValue(waybill.getCod());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(32);
					cell.setCellValue(waybill.getRaterRate());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(33);
					cell.setCellValue(waybill.getDiscount());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(34);
					cell.setCellValue(waybill.getIndemnity());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(35);
					cell.setCellValue(waybill.getOutPrice());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(36);
					cell.setCellValue(waybill.getOutWorth());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(37);
					cell.setCellValue(waybill.getOutWorthrate());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(38);
					cell.setCellValue(waybill.getOutInsurance());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(39);
					cell.setCellValue(waybill.getOutSum());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(40);
					cell.setCellValue(waybill.getStatusId());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(41);
					cell.setCellValue(waybill.getNoaccPackfee());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(42);
					cell.setCellValue(waybill.getNoaccAdvance());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(43);
					cell.setCellValue(waybill.getOrderNo());
					cell.setCellStyle(cellStyle);					
					
					cell = (HSSFCell) row.createCell(44);
					cell.setCellValue(waybill.getCustName());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(45);
					cell.setCellValue(waybill.getCustTel());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(46);
					cell.setCellValue(waybill.getCustAdd());
					cell.setCellStyle(cellStyle);
					
					pics +=waybill.getPics();
					weight += waybill.getWeight();					
					volum += waybill.getVolumu();
					advanceU +=waybill.getAdvancedU();
					advanceZ +=waybill.getAdvancedZ();
					packfeeU +=waybill.getPackfeeU();
					packfeeZ +=waybill.getPackfeeZ();
					worth += waybill.getWorth();
					insurance +=waybill.getInsurance();
					indemnity +=waybill.getIndemnity();
					sumbill +=waybill.getSumbill();
					total +=waybill.getTotal();
					cod += waybill.getCod();
					discount += waybill.getDiscount();
					actualSum +=waybill.getActualSum();
					outSum +=waybill.getOutSum();
					outworth +=waybill.getOutWorth();
					outInsurance +=waybill.getOutInsurance();
					noadv += waybill.getNoaccAdvance();
					nopack +=waybill.getNoaccPackfee();
					
					i++;
				}
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(pics);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(weight);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(volum);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				
				
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue(advanceZ);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue(packfeeZ);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(10);
				cell.setCellValue(advanceU);
				cell.setCellStyle(cellStyle);			
				cell = (HSSFCell) row.createCell(11);
				cell.setCellValue(packfeeU);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(12);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(13);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(14);
				cell.setCellValue(sumbill);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(15);
				cell.setCellValue(worth);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(16);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(17);
				cell.setCellValue(insurance);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(18);
				cell.setCellValue(total);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(19);
				cell.setCellValue(actualSum);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(20);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(21);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(22);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(23);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(24);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(25);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(26);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(27);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(28);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(29);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(30);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(31);
				cell.setCellValue(cod);
				cell.setCellStyle(cellStyle);
				

				cell = (HSSFCell) row.createCell(32);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(33);
				cell.setCellValue(discount);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(34);
				cell.setCellValue(indemnity);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(35);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(36);
				cell.setCellValue(outworth);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(37);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(38);
				cell.setCellValue(outInsurance);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(39);
				cell.setCellValue(outSum);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(40);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(41);
				cell.setCellValue(nopack);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(42);
				cell.setCellValue(noadv);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(43);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(44);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(45);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(46);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				
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
		
		}else if(typeId==4){
			
			book = new HSSFWorkbook();
			String sheetName="财务总报表";
			String titleName="258 КАРГО 财务总报表"+lineId+"-"+bitch;
			System.out.println(sheetName);
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
			 //--列宽设置
		       
	        sheet.setColumnWidth(0, 7000); //票号
			sheet.setColumnWidth(1, 1800); //件数 
			sheet.setColumnWidth(2, 1800); 
			sheet.setColumnWidth(3, 1800);
			sheet.setColumnWidth(4, 9000); //品名
			sheet.setColumnWidth(5, 1800);//数量
			sheet.setColumnWidth(6, 1800); //垫付款
			sheet.setColumnWidth(7, 1800);
			sheet.setColumnWidth(8, 1800); //包费
			sheet.setColumnWidth(9, 1800);
			sheet.setColumnWidth(10, 1800); //货值
			sheet.setColumnWidth(11, 1800);//保率
			sheet.setColumnWidth(12, 1800); //保险费
			sheet.setColumnWidth(13, 1800); //索赔
			sheet.setColumnWidth(14, 1800); //单价
			sheet.setColumnWidth(15, 2000); //运费
			sheet.setColumnWidth(16, 1800); //代收款
			sheet.setColumnWidth(17, 1800); //拆扣率
			sheet.setColumnWidth(18, 1800); //代理费			
			sheet.setColumnWidth(19, 2000); //代理人
			sheet.setColumnWidth(20, 2000); //经办人
			sheet.setColumnWidth(21, 1800); //发货人
			sheet.setColumnWidth(22, 1800); //客户号
			sheet.setColumnWidth(23, 1800); //客户名
			sheet.setColumnWidth(24, 1800); //外配价
			sheet.setColumnWidth(25, 1800); //外货值
			sheet.setColumnWidth(26, 1800); //外保率
			sheet.setColumnWidth(27, 1800); //外保费
			sheet.setColumnWidth(28, 2000); //外合计
			sheet.setColumnWidth(29, 2000); //外合计
			sheet.setColumnWidth(30, 5000); //批次
			sheet.setColumnWidth(31, 2000); //线路
			sheet.setColumnWidth(32, 8500); //备注
			sheet.setColumnWidth(33, 2000); //未加包费
			sheet.setColumnWidth(34, 2000); //未加运费
			
			Row row = sheet.createRow(0);
			
			
			HSSFCell cell =(HSSFCell) row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 34));
			
			row.getCell(0).setCellValue(titleName);		
			row.getCell(0).setCellStyle(titleStyle);
			
			row = sheet.createRow(1);
			row.setHeight((short) 600);//目的是想把行高设置成400px
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("票号");
			cell.setCellStyle(headerStyle);
		
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("包数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("重量");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue("体积");
			cell.setCellStyle(headerStyle);
		
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue("品名");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("小件数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue("垫付款￥");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue("垫付款$");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue("包费￥");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue("包费$");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(10);
			cell.setCellValue("货值");
			cell.setCellStyle(headerStyle);
			
			
			cell = (HSSFCell) row.createCell(11);
			cell.setCellValue("保率");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(12);
			cell.setCellValue("保险费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(13);
			cell.setCellValue("索赔");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(14);
			cell.setCellValue("单价");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(15);
			cell.setCellValue("运费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(16);
			cell.setCellValue("代收款");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(17);
			cell.setCellValue("折扣率");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(18);
			cell.setCellValue("代理费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(19);
			cell.setCellValue("代理人");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(20);
			cell.setCellValue("经办人");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(21);
			cell.setCellValue("发货人");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(22);
			cell.setCellValue("客户号");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(23);
			cell.setCellValue("客户名");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(24);
			cell.setCellValue("外配价");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(25);
			cell.setCellValue("外费值");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(26);
			cell.setCellValue("外保率");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(27);
			cell.setCellValue("外保费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(28);
			cell.setCellValue("应收合计");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(29);
			cell.setCellValue("清关费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(30);
			cell.setCellValue("批次");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(31);
			cell.setCellValue("线路");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(32);
			cell.setCellValue("备注");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(33);
			cell.setCellValue("未加包费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(34);
			cell.setCellValue("未加运费");
			cell.setCellStyle(headerStyle);
			
			
					
				int i=2;
				int pics= 0;
				Double weight =0.0;
				Double volum=0.00;
				Integer advanceU =0;
				Integer advanceZ =0;
				Integer packfeeU =0;
				Integer packfeeZ =0;
				Integer worth =0;
				Integer insurance =0;
				Double sumbill=0.0;
				Double indemnity=0.0;
				Double cod =0.0;
				Double discount=0.0;
				Integer total=0; //
				
				Double outworth =0.0;
				Double outInsurance=0.0;
				Double outSum =0.0;
				Integer nopack =0;
				Integer noadv =0;
				
				
				for(int j=0;j<list.size();j++){
					Waybill waybill = list.get(j);				
					
					row = sheet.createRow(i);
					
					cell = (HSSFCell) row.createCell(0);
					cell.setCellValue(waybill.getWaybill());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(1);
					cell.setCellValue(waybill.getPics());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(2);
					cell.setCellValue(waybill.getWeight());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(3);
					cell.setCellValue(waybill.getVolumu());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(4);
					cell.setCellValue(waybill.getGoods());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(5);
					cell.setCellValue(waybill.getQuantity());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(6);
					cell.setCellValue(waybill.getAdvancedZ());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(7);
					cell.setCellValue(waybill.getAdvancedU());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(8);
					cell.setCellValue(waybill.getPackfeeZ());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(9);
					cell.setCellValue(waybill.getPackfeeU());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(10);
					cell.setCellValue(waybill.getWorth());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(11);
					cell.setCellValue(waybill.getWorthRate());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(12);
					cell.setCellValue(waybill.getInsurance());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(13);
					cell.setCellValue(waybill.getIndemnity());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(14);
					cell.setCellValue(waybill.getPrice());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(15);
					cell.setCellValue(waybill.getSumbill());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(16);
					cell.setCellValue(waybill.getCod());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(17);
					cell.setCellValue(waybill.getRaterRate());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(18);
					cell.setCellValue(waybill.getDiscount());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(19);
					cell.setCellValue(waybill.getProcurator());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(20);
					cell.setCellValue(waybill.getRaterName());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(21);
					cell.setCellValue(waybill.getSender());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(22);
					cell.setCellValue(waybill.getCustId());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(23);
					cell.setCellValue(waybill.getCustName());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(24);
					cell.setCellValue(waybill.getOutPrice());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(25);
					cell.setCellValue(waybill.getOutWorth());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(26);
					cell.setCellValue(waybill.getOutWorthrate());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(27);
					cell.setCellValue(waybill.getOutInsurance());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(28);
					cell.setCellValue(waybill.getTotal());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(29);
					cell.setCellValue(waybill.getOutSum());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(30);
					cell.setCellValue(waybill.getBitch());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(31);
					cell.setCellValue(waybill.getLineId());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(32);
					cell.setCellValue(waybill.getRemarks());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(33);
					cell.setCellValue(waybill.getNoaccPackfee());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(34);
					cell.setCellValue(waybill.getNoaccAdvance());
					cell.setCellStyle(cellStyle);
					
					
					pics +=waybill.getPics();
					weight += waybill.getWeight();					
					volum += waybill.getVolumu();
					advanceU +=waybill.getAdvancedU();
					advanceZ +=waybill.getAdvancedZ();
					packfeeU +=waybill.getPackfeeU();
					packfeeZ +=waybill.getPackfeeZ();
					worth += waybill.getWorth();
					insurance +=waybill.getInsurance();
					indemnity +=waybill.getIndemnity();
					sumbill +=waybill.getSumbill();
					total +=waybill.getTotal();
					cod += waybill.getCod();
					discount += waybill.getDiscount();
					
					outSum +=waybill.getOutSum();
					outworth +=waybill.getOutWorth();
					outInsurance +=waybill.getOutInsurance();
					nopack += waybill.getNoaccPackfee();
					noadv += waybill.getNoaccAdvance();
					
					i++;
				}
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(pics);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(weight);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(volum);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue(advanceZ);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue(advanceU);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue(packfeeZ);
				cell.setCellStyle(cellStyle);			
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue(packfeeU);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(10);
				cell.setCellValue(worth);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(11);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(12);
				cell.setCellValue(insurance);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(13);
				cell.setCellValue(indemnity);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(14);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(15);
				cell.setCellValue(sumbill);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(16);
				cell.setCellValue(cod);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(17);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(18);
				cell.setCellValue(discount);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(19);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(20);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(21);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(22);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(23);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(24);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(25);
				cell.setCellValue(outworth);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(26);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(27);
				cell.setCellValue(outInsurance);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(28);
				cell.setCellValue(total);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(29);
				cell.setCellValue(outSum);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(30);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(31);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(32);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(33);
				cell.setCellValue(nopack);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(34);
				cell.setCellValue(noadv);
				cell.setCellStyle(cellStyle);
				
				
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
		
		}else if(typeId==5){
			
			book = new HSSFWorkbook();
			String sheetName="结算报表";
			String titleName="258 КАРГО 货物结算报表   "+lineId+"-"+bitch;
			System.out.println(sheetName);
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
			 //--列宽设置
			
	        sheet.setColumnWidth(0, 7000); //票号
			sheet.setColumnWidth(1, 1800); //件数 
			sheet.setColumnWidth(2, 1800); //重量
			sheet.setColumnWidth(3, 1800); //体积
			sheet.setColumnWidth(4, 9000); //品名
			sheet.setColumnWidth(5, 2000); //品类
			sheet.setColumnWidth(6, 1800);//数量
			
			sheet.setColumnWidth(7, 1800); //外配价
			sheet.setColumnWidth(8, 1800); //外货值
			sheet.setColumnWidth(9, 1800); //外保率
			sheet.setColumnWidth(10, 1800); //外保费
			sheet.setColumnWidth(11, 2000); //外合计		
			sheet.setColumnWidth(12, 2000); //市场
			sheet.setColumnWidth(13, 2000); //发货人
			
			Row row = sheet.createRow(0);
			
			HSSFCell cell =(HSSFCell) row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
			
			row.getCell(0).setCellValue(titleName);		
			row.getCell(0).setCellStyle(titleStyle);
			
			row = sheet.createRow(1);
			row.setHeight((short) 600);//目的是想把行高设置成400px
			
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("票号");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("包数");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("重量");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue("体积");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue("品名");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("品类");
			cell.setCellStyle(headerStyle);

			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue("小件数");
			cell.setCellStyle(headerStyle);
			
			
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue("结算价");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue("外费值");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue("外保率");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(10);
			cell.setCellValue("外保费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(11);
			cell.setCellValue("总结算");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(12);
			cell.setCellValue("市场");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(13);
			cell.setCellValue("发货人");
			cell.setCellStyle(headerStyle);
				int i=2;
			
				for(int j=0;j<list.size();j++){
					Waybill waybill = list.get(j);				
					
					row = sheet.createRow(i);
					
					cell = (HSSFCell) row.createCell(0);					
					cell.setCellValue(waybill.getWaybill());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(1);
					cell.setCellValue(waybill.getPics());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(2);
					cell.setCellValue(waybill.getWeight());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(3);
					cell.setCellValue(waybill.getVolumu());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(4);
					cell.setCellValue(waybill.getGoods());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(5);
					cell.setCellValue(waybill.getMark());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(6);
					cell.setCellValue(waybill.getQuantity());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(7);
					cell.setCellValue(waybill.getOutPrice());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(8);
					cell.setCellValue(waybill.getOutWorth());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(9);
					cell.setCellValue(waybill.getOutWorthrate());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(10);
					cell.setCellValue(waybill.getOutInsurance());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(11);
					cell.setCellValue(waybill.getOutSum());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(12);
					cell.setCellValue(waybill.getDestName());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(13);
					cell.setCellValue(waybill.getSender());
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
		
		}
		
		return is;
	}
	//生成统计报表
	public InputStream getAllBitchReportStream(Date stDate,Date edDate){
		InputStream is = null;
		Workbook book = new HSSFWorkbook();
			List<Bitch> bitchList = bitchDao.findBySdDate(stDate, edDate);
			String bitches ="";
			for(Bitch b :bitchList){
				bitches +="'"+ b.getBitch().trim()+"',";
			}
			bitches = bitches.substring(0, bitches.lastIndexOf(","));
			List<Waybill> list = this.waybillDao.listAllByBitches(bitches);
		
		
		String sheetName="统计报表";
		String titleName="258 КАРГО 统计报表"+stDate+"--"+edDate;
		
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
		short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
		dateCellStyle.setDataFormat(df);
		
		 //--列宽设置
		sheet.setColumnWidth(0, 2900); //日期
        sheet.setColumnWidth(1, 7000); //票号
		sheet.setColumnWidth(2, 1800); //件数 
		sheet.setColumnWidth(3, 1800); //重量
		sheet.setColumnWidth(4, 1800); //体积
		sheet.setColumnWidth(5, 9000); //品名
		sheet.setColumnWidth(6, 2000); //品类
		sheet.setColumnWidth(7, 1800);//数量
		sheet.setColumnWidth(8, 1800); //垫付款￥
		sheet.setColumnWidth(9, 1800); //包费￥
		sheet.setColumnWidth(10, 1800);  //垫付款$
		sheet.setColumnWidth(11, 1800);//包费$
		sheet.setColumnWidth(12, 2000); //汇率
		sheet.setColumnWidth(13, 1800); //单价
		sheet.setColumnWidth(14, 2000); //运费
		sheet.setColumnWidth(15, 1800); //货值
		sheet.setColumnWidth(16, 1800);//保率
		sheet.setColumnWidth(17, 1800); //保险费
		sheet.setColumnWidth(18, 2000); //应收
		sheet.setColumnWidth(19, 2000); //实收款
		sheet.setColumnWidth(20, 2200); //付款方式
		sheet.setColumnWidth(21, 2200); //市场
		sheet.setColumnWidth(22, 1800); //发货人
		sheet.setColumnWidth(23, 1800); //客户号
		sheet.setColumnWidth(24, 4000); //批次
		sheet.setColumnWidth(25, 2000); //线路
		sheet.setColumnWidth(26, 2000); //是否核销
		sheet.setColumnWidth(27, 2000); //核销编号
		sheet.setColumnWidth(28, 2500); //备注
		sheet.setColumnWidth(29, 2000); //代理人
		sheet.setColumnWidth(30, 2000); //经办人
		sheet.setColumnWidth(31, 1800); //代收款
		sheet.setColumnWidth(32, 1800); //拆扣率
		sheet.setColumnWidth(33, 1800); //代理费	
		
		sheet.setColumnWidth(34, 1800); //索赔
		sheet.setColumnWidth(35, 1800); //外配价
		sheet.setColumnWidth(36, 1800); //外货值
		sheet.setColumnWidth(37, 1800); //外保率
		sheet.setColumnWidth(38, 1800); //外保费
		sheet.setColumnWidth(39, 2000); //外合计		
		sheet.setColumnWidth(40, 1800); //状态
		sheet.setColumnWidth(41, 2000); //未加包费
		sheet.setColumnWidth(42, 2000); //未加运费
		
		sheet.setColumnWidth(43, 2000); //货源地
		sheet.setColumnWidth(44, 7000); //收货人
		sheet.setColumnWidth(45, 7000); //电话
		sheet.setColumnWidth(46, 2200);	//地址
		
		
		Row row = sheet.createRow(0);
		
		HSSFCell cell =(HSSFCell) row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 46));
		
		row.getCell(0).setCellValue(titleName);		
		row.getCell(0).setCellStyle(titleStyle);
		
		row = sheet.createRow(1);
		row.setHeight((short) 600);//目的是想把行高设置成400px
		cell = (HSSFCell) row.createCell(0);
		cell.setCellValue("日期");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(1);
		cell.setCellValue("票号");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(2);
		cell.setCellValue("包数");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(3);
		cell.setCellValue("重量");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(4);
		cell.setCellValue("体积");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(5);
		cell.setCellValue("品名");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(6);
		cell.setCellValue("品类");
		cell.setCellStyle(headerStyle);

		cell = (HSSFCell) row.createCell(7);
		cell.setCellValue("小件数");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(8);
		cell.setCellValue("垫付款￥");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(9);
		cell.setCellValue("包费￥");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(10);
		cell.setCellValue("垫付款$");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(11);
		cell.setCellValue("包费$");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(12);
		cell.setCellValue("汇率");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(13);
		cell.setCellValue("单价");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(14);
		cell.setCellValue("运费");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(15);
		cell.setCellValue("货值");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(16);
		cell.setCellValue("保率");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(17);
		cell.setCellValue("保险费");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(18);
		cell.setCellValue("应收款");
		cell.setCellStyle(headerStyle);

		cell = (HSSFCell) row.createCell(19);
		cell.setCellValue("实收");
		cell.setCellStyle(headerStyle);

		cell = (HSSFCell) row.createCell(20);
		cell.setCellValue("付款方式");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(21);
		cell.setCellValue("市场");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(22);
		cell.setCellValue("发货人");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(23);
		cell.setCellValue("客户号");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(24);
		cell.setCellValue("批次");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(25);
		cell.setCellValue("线路");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(26);
		cell.setCellValue("是否核销");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(27);
		cell.setCellValue("核销编号");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(28);
		cell.setCellValue("备注");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(29);
		cell.setCellValue("代理人");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(30);
		cell.setCellValue("经办人");
		cell.setCellStyle(headerStyle);

		cell = (HSSFCell) row.createCell(31);
		cell.setCellValue("代收款");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(32);
		cell.setCellValue("折扣率");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(33);
		cell.setCellValue("代理费");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(34);
		cell.setCellValue("索赔");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(35);
		cell.setCellValue("结算价");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(36);
		cell.setCellValue("外费值");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(37);
		cell.setCellValue("外保率");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(38);
		cell.setCellValue("外保费");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(39);
		cell.setCellValue("总结算");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(40);
		cell.setCellValue("状态");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(41);
		cell.setCellValue("未加包费");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(42);
		cell.setCellValue("未加运费");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(43);
		cell.setCellValue("货源地");
		cell.setCellStyle(headerStyle);
		
		
		cell = (HSSFCell) row.createCell(44);
		cell.setCellValue("收货人");
		cell.setCellStyle(headerStyle);

		cell = (HSSFCell) row.createCell(45);
		cell.setCellValue("电话");
		cell.setCellStyle(headerStyle);

		cell = (HSSFCell) row.createCell(46);
		cell.setCellValue("地址");
		cell.setCellStyle(headerStyle);

					
				
			int i=2;
			int pics= 0;
			Double weight =0.0;
			Double volum=0.00;
			Integer advanceU =0;
			Integer advanceZ =0;
			Integer packfeeU =0;
			Integer packfeeZ =0;
			Integer worth =0;
			Integer insurance =0;
			Double sumbill=0.0;
			Double indemnity=0.0;
			Double cod =0.0;
			Double discount=0.0;
			Integer total=0; //
			Integer actualSum =0;
			Double outworth =0.0;
			Double outInsurance=0.0;
			Double outSum =0.0;
			Integer noadv =0;
			Integer nopack =0;
			
			
			for(int j=0;j<list.size();j++){
				Waybill waybill = list.get(j);				
				
				row = sheet.createRow(i);
				
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue(waybill.getSddate());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(waybill.getWaybill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(waybill.getPics());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(waybill.getWeight());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(waybill.getVolumu());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue(waybill.getGoods());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue(waybill.getMark());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue(waybill.getQuantity());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue(waybill.getAdvancedZ());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue(waybill.getPackfeeZ());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(10);
				cell.setCellValue(waybill.getAdvancedU());
				cell.setCellStyle(cellStyle);					
				cell = (HSSFCell) row.createCell(11);
				cell.setCellValue(waybill.getPackfeeU());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(12);
				cell.setCellValue(waybill.getExchangeRate());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(13);
				cell.setCellValue(waybill.getPrice());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(14);
				cell.setCellValue(waybill.getSumbill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(15);
				cell.setCellValue(waybill.getWorth());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(16);
				cell.setCellValue(waybill.getWorthRate());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(17);
				cell.setCellValue(waybill.getInsurance());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(18);
				cell.setCellValue(waybill.getTotal());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(19);
				cell.setCellValue(waybill.getActualSum());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(20);
				cell.setCellValue(waybill.getPayMethod());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(21);
				cell.setCellValue(waybill.getDestName());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(22);
				cell.setCellValue(waybill.getSender());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(23);
				cell.setCellValue(waybill.getCustId());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(24);
				cell.setCellValue(waybill.getBitch());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(25);
				cell.setCellValue(waybill.getLineId());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(26);
				cell.setCellValue(waybill.getIsRebate());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(27);
				cell.setCellValue(waybill.getRebateId());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(28);
				cell.setCellValue(waybill.getRemarks());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(29);
				cell.setCellValue(waybill.getProcurator());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(30);
				cell.setCellValue(waybill.getRaterName());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(31);
				cell.setCellValue(waybill.getCod());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(32);
				cell.setCellValue(waybill.getRaterRate());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(33);
				cell.setCellValue(waybill.getDiscount());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(34);
				cell.setCellValue(waybill.getIndemnity());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(35);
				cell.setCellValue(waybill.getOutPrice());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(36);
				cell.setCellValue(waybill.getOutWorth());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(37);
				cell.setCellValue(waybill.getOutWorthrate());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(38);
				cell.setCellValue(waybill.getOutInsurance());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(39);
				cell.setCellValue(waybill.getOutSum());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(40);
				cell.setCellValue(waybill.getStatusId());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(41);
				cell.setCellValue(waybill.getNoaccPackfee());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(42);
				cell.setCellValue(waybill.getNoaccAdvance());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(43);
				cell.setCellValue(waybill.getOrderNo());
				cell.setCellStyle(cellStyle);					
				
				cell = (HSSFCell) row.createCell(44);
				cell.setCellValue(waybill.getCustName());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(45);
				cell.setCellValue(waybill.getCustTel());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(46);
				cell.setCellValue(waybill.getCustAdd());
				cell.setCellStyle(cellStyle);
				
				pics +=waybill.getPics();
				weight += waybill.getWeight();					
				volum += waybill.getVolumu();
				advanceU +=waybill.getAdvancedU();
				advanceZ +=waybill.getAdvancedZ();
				packfeeU +=waybill.getPackfeeU();
				packfeeZ +=waybill.getPackfeeZ();
				worth += waybill.getWorth();
				insurance +=waybill.getInsurance();
				indemnity +=waybill.getIndemnity();
				sumbill +=waybill.getSumbill();
				total +=waybill.getTotal();
				cod += waybill.getCod();
				discount += waybill.getDiscount();
				actualSum +=waybill.getActualSum();
				outSum +=waybill.getOutSum();
				outworth +=waybill.getOutWorth();
				outInsurance +=waybill.getOutInsurance();
				noadv += waybill.getNoaccAdvance();
				nopack +=waybill.getNoaccPackfee();
				
				i++;
			}
			row = sheet.createRow(i);
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue(pics);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue(weight);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue(volum);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			
			
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue(advanceZ);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue(packfeeZ);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(10);
			cell.setCellValue(advanceU);
			cell.setCellStyle(cellStyle);			
			cell = (HSSFCell) row.createCell(11);
			cell.setCellValue(packfeeU);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(12);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			
			cell = (HSSFCell) row.createCell(13);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(14);
			cell.setCellValue(sumbill);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(15);
			cell.setCellValue(worth);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(16);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(17);
			cell.setCellValue(insurance);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(18);
			cell.setCellValue(total);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(19);
			cell.setCellValue(actualSum);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(20);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(21);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(22);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(23);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(24);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(25);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(26);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(27);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(28);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(29);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(30);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(31);
			cell.setCellValue(cod);
			cell.setCellStyle(cellStyle);
			

			cell = (HSSFCell) row.createCell(32);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(33);
			cell.setCellValue(discount);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(34);
			cell.setCellValue(indemnity);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(35);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			
			cell = (HSSFCell) row.createCell(36);
			cell.setCellValue(outworth);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(37);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(38);
			cell.setCellValue(outInsurance);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(39);
			cell.setCellValue(outSum);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(40);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(41);
			cell.setCellValue(nopack);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(42);
			cell.setCellValue(noadv);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(43);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(44);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(45);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(46);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			
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
	//生成代理费报表
	public InputStream getRaterReportStream(Date stDate,Date edDate){
		InputStream is = null;
		Workbook book = new HSSFWorkbook();
			List<Bitch> bitchList = bitchDao.findBySdDate(stDate, edDate);
			String bitches ="";
			for(Bitch b :bitchList){
				bitches +="'"+ b.getBitch().trim()+"',";
			}
			bitches = bitches.substring(0, bitches.lastIndexOf(","));
			List<ProcuratorGroup> listGroup = this.waybillDao.listGroupByBitches(bitches);
		
		
		String sheetNameG="代理费报表";
		String titleNameG="258 КАРГО 代理费报表";
		
		Sheet sheet = book.createSheet(sheetNameG);
		
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
		short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
		dateCellStyle.setDataFormat(df);
		
			 //--列宽设置
	        sheet.setColumnWidth(0, 2200); //代理人
			sheet.setColumnWidth(1, 2200);	//返点
			sheet.setColumnWidth(2, 2200); //代收款
			sheet.setColumnWidth(3, 2200); //丢失减代理费
			sheet.setColumnWidth(4, 2200); //合计

			Row row = sheet.createRow(0);
						
			HSSFCell cell =(HSSFCell) row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			
			row.getCell(0).setCellValue(titleNameG);		
			row.getCell(0).setCellStyle(titleStyle);
			
			row = sheet.createRow(1);
			row.setHeight((short) 600);//目的是想把行高设置成400px
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("代理人");
			cell.setCellStyle(headerStyle);		
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("返点");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("代收款");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue("减代理费");
			cell.setCellStyle(headerStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue("合计");
			cell.setCellStyle(headerStyle);		
				int i=2;
				
				
				Double discount =0.0;
				Double cod =0.0;
				Double inde =0.0;
				for(int j=0;j<listGroup.size();j++){
					
					ProcuratorGroup procurator = listGroup.get(j);				
					
					List waybills = indemnifyDao.listWaybillByProcurator(procurator.getProcurator());
					
					Double indemn =0.0;
					for(int m=0;m<waybills.size();m++){
						Waybill wb = this.findByWaybill((String)waybills.get(m));
						Double rate = wb.getRaterRate();
						Indemnify indemnify = indemnifyDao.findByWaybill((String)waybills.get(m));
						indemn += indemnify.getIndemWeight()*rate;
						
						
					}
					
					row = sheet.createRow(i);
					cell = (HSSFCell) row.createCell(0);
					cell.setCellValue(procurator.getProcurator());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(1);
					cell.setCellValue(procurator.getDiscount());
					cell.setCellStyle(cellStyle);
					cell = (HSSFCell) row.createCell(2);
					cell.setCellValue(procurator.getCod());
					cell.setCellStyle(cellStyle);
					
					cell = (HSSFCell) row.createCell(3);
					cell.setCellValue(indemn);
					cell.setCellStyle(cellStyle);
					
					
					cell = (HSSFCell) row.createCell(4);
					cell.setCellValue(procurator.getDiscount()+procurator.getCod()-indemn);
					cell.setCellStyle(cellStyle);
					
					discount += procurator.getDiscount();
					cod +=procurator.getCod();
					inde += indemn;
					i++;
				}
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(discount);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(cod);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(inde);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(discount+cod-inde);
				cell.setCellStyle(cellStyle);
		
			List<Waybill> list = this.waybillDao.listByBitches(bitches);
			
			String sheetName="代理费明细表";
			String titleName="258 КАРГО 代理费明细表    ";
			
			Sheet sheet2 = book.createSheet(sheetName);
			
			
			 //--列宽设置
			sheet2.setColumnWidth(0, 2600); //日期
			sheet2.setColumnWidth(1, 2000); //客户号
	        sheet2.setColumnWidth(2, 8000); //票号
			sheet2.setColumnWidth(3, 2000); //件数 
			sheet2.setColumnWidth(4, 2200); //重量
			sheet2.setColumnWidth(5, 2200);		//体积	
			sheet2.setColumnWidth(6, 2200); //返率
			sheet2.setColumnWidth(7, 2200); //返点
			sheet2.setColumnWidth(8, 2200); //代收款
			sheet2.setColumnWidth(9, 2500); // 丢失重量
			sheet2.setColumnWidth(10, 2500); //减代理费
			sheet2.setColumnWidth(11, 3000); //总金额
			sheet2.setColumnWidth(12, 2500); //代理人
			sheet2.setColumnWidth(13, 3500); //批次
			
			Row row2 = sheet2.createRow(0);
			
			
			Cell cell2 =(HSSFCell) row2.createCell(0);
			sheet2.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
			
			row2.getCell(0).setCellValue(titleName);		
			row2.getCell(0).setCellStyle(titleStyle);
			
			row2 = sheet2.createRow(1);
			row2.setHeight((short) 600);//目的是想把行高设置成400px
			cell2 = (HSSFCell) row2.createCell(0);
			cell2.setCellValue("日期");
			cell2.setCellStyle(headerStyle);
			cell2 = (HSSFCell) row2.createCell(1);
			cell2.setCellValue("客户号");
			cell2.setCellStyle(headerStyle);	
			cell2 = (HSSFCell) row2.createCell(2);
			cell2.setCellValue("票号");
			cell2.setCellStyle(headerStyle);		
			cell2 = (HSSFCell) row2.createCell(3);
			cell2.setCellValue("包数");
			cell2.setCellStyle(headerStyle);
			cell2 = (HSSFCell) row2.createCell(4);
			cell2.setCellValue("重量");
			cell2.setCellStyle(headerStyle);
			cell2 = (HSSFCell) row2.createCell(5);
			cell2.setCellValue("体积");
			cell2.setCellStyle(headerStyle);
			cell2 = (HSSFCell) row2.createCell(6);		
			cell2.setCellValue("返率");
			cell2.setCellStyle(headerStyle);
			cell2 = (HSSFCell) row2.createCell(7);		
			cell2.setCellValue("返点");
			cell2.setCellStyle(headerStyle);			
			cell2 = (HSSFCell) row2.createCell(8);
			cell2.setCellValue("代收款");
			cell2.setCellStyle(headerStyle);
			cell2 = (HSSFCell) row2.createCell(9);
			cell2.setCellValue("丢失重量");
			cell2.setCellStyle(headerStyle);
			cell2 = (HSSFCell) row2.createCell(10);
			cell2.setCellValue("减代理费");
			cell2.setCellStyle(headerStyle);
			
			
			cell2 = (HSSFCell) row2.createCell(11);
			cell2.setCellValue("总金额");
			cell2.setCellStyle(headerStyle);
			cell2 = (HSSFCell) row2.createCell(12);
			cell2.setCellValue("代理人");
			cell2.setCellStyle(headerStyle);
			cell2 = (HSSFCell) row2.createCell(13);
			cell2.setCellValue("批次");
			cell2.setCellStyle(headerStyle);		
				int ii=2;
				
				int pics= 0;
				Double weight2 =0.0;
				Double volum2=0.00;
				
				Double cod2 =0.0;
				Double discount2=0.00;		
				
				Double lost =0.0;
				
				
				for(int j=0;j<list.size();j++){
					Waybill waybill = list.get(j);				
					
					row2 = sheet2.createRow(ii);
					
					cell2 = (HSSFCell) row2.createCell(0);
					cell2.setCellValue(waybill.getSddate());
					cell2.setCellStyle(dateCellStyle);
					
					cell2 = (HSSFCell) row2.createCell(1);
					cell2.setCellValue(waybill.getCustId());
					cell2.setCellStyle(cellStyle);
					cell2 = (HSSFCell) row2.createCell(2);
					cell2.setCellValue(waybill.getWaybill());
					cell2.setCellStyle(cellStyle);
					
					cell2 = (HSSFCell) row2.createCell(3);
					cell2.setCellValue(waybill.getPics());
					cell2.setCellStyle(cellStyle);
					cell2 = (HSSFCell) row2.createCell(4);
					cell2.setCellValue(waybill.getWeight());
					cell2.setCellStyle(cellStyle);
					cell2 = (HSSFCell) row2.createCell(5);
					cell2.setCellValue(waybill.getVolumu());
					cell2.setCellStyle(cellStyle);

					cell2 = (HSSFCell) row2.createCell(6);
					cell2.setCellValue(waybill.getRaterRate());
					cell2.setCellStyle(cellStyle);

					cell2 = (HSSFCell) row2.createCell(7);
					cell2.setCellValue(waybill.getDiscount());
					cell2.setCellStyle(cellStyle);
					
					cell2 = (HSSFCell) row2.createCell(8);
					cell2.setCellValue(waybill.getCod());
					cell2.setCellStyle(cellStyle);
					//丢失的重量
					Double wt =indemnifyDao.isExsit(waybill.getWaybill())?indemnifyDao.getWeightByWaybill(waybill.getWaybill()):0.0;
					cell2 = (HSSFCell) row2.createCell(9);
					cell2.setCellValue(wt);
					cell2.setCellStyle(cellStyle);
					//减代理费
					Double indfee = wt*waybill.getRaterRate();
					cell2 = (HSSFCell) row2.createCell(10);
					cell2.setCellValue(indfee);
					cell2.setCellStyle(cellStyle);
					
					
					
					cell2 = (HSSFCell) row2.createCell(11);
					cell2.setCellValue(waybill.getCod()+waybill.getDiscount()-indfee);
					cell2.setCellStyle(cellStyle);
					cell2 = (HSSFCell) row2.createCell(12);
					cell2.setCellValue(waybill.getProcurator());
					cell2.setCellStyle(cellStyle);
					cell2 = (HSSFCell) row2.createCell(13);
					cell2.setCellValue(waybill.getBitch());
					cell2.setCellStyle(cellStyle);
					
					pics += waybill.getPics();
					weight2 += waybill.getWeight();
					volum2 += waybill.getVolumu();
					
					cod2 +=waybill.getCod();
					discount2 +=waybill.getDiscount();		
					lost += indfee;
					ii++;
				}
				row2 = sheet2.createRow(ii);
				cell2 = (HSSFCell) row2.createCell(0);
				cell2.setCellValue("");
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(1);
				cell2.setCellValue("");
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(2);
				cell2.setCellValue("");
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(3);
				cell2.setCellValue(pics);
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(4);
				cell2.setCellValue(weight2);
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(5);
				cell2.setCellValue(volum2);
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(6);
				cell2.setCellValue("");
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(7);
				cell2.setCellValue(discount2);
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(8);
				cell2.setCellValue(cod2);
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(9);
				cell2.setCellValue("");
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(10);
				cell2.setCellValue(lost);
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(11);
				cell2.setCellValue(cod2+discount2-lost);
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(12);
				cell2.setCellValue("");
				cell2.setCellStyle(cellStyle);
				cell2 = (HSSFCell) row2.createCell(13);
				cell2.setCellValue("");
				cell2.setCellStyle(cellStyle);
				
				
				
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
	//生成每批的垫付款明细
	public InputStream getAdvanceReportStream(String bitch){
		List<Waybill> list = this.listAdvancedByBitch(bitch);
		String sheetName="垫付款明细单";
		String titleName="258 КАРГО 垫付款明细单-"+bitch;
		
		InputStream is = null;
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet(sheetName);
		//--页边距设置
		HSSFPrintSetup ps = (HSSFPrintSetup) sheet.getPrintSetup();
        ps.setLandscape(false); //打印方向，true:横向，false:纵向
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
        sheet.setMargin(HSSFSheet.BottomMargin, (double)0.5); //页边距（下）
        sheet.setMargin(HSSFSheet.LeftMargin, (double)0.1); //页边距（左）
        sheet.setMargin(HSSFSheet.RightMargin, (double)0.1); //页边距（右）
        sheet.setMargin(HSSFSheet.TopMargin, (double)0.5); //页边距（上）
        sheet.setHorizontallyCenter(true); //设置打印页面为水平居中
        //sheet.setVerticallyCenter(true); //设置打印页面为垂直居中
      //--列宽设置
        sheet.setColumnWidth(0, 2900);
        sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 1800);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 1800);
		sheet.setColumnWidth(5, 5500);
		sheet.setColumnWidth(6, 2800);	//垫付款U
		sheet.setColumnWidth(7, 2800);  //垫付款Z
		sheet.setColumnWidth(8, 2800); //未记垫付款
		sheet.setColumnWidth(9, 2800); //未记打包款
		sheet.setColumnWidth(10, 2600); //发货人
		
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
		short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
		dateCellStyle.setDataFormat(df);
		
		Row row = sheet.createRow(0);
		
		
		HSSFCell cell =(HSSFCell) row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
		
		row.getCell(0).setCellValue(titleName);		
		row.getCell(0).setCellStyle(titleStyle);
		
		row = sheet.createRow(1);
		row.setHeight((short) 600);//目的是想把行高设置成400px
		cell = (HSSFCell) row.createCell(0);
		cell.setCellValue("日期");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(1);
		cell.setCellValue("票号");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(2);
		cell.setCellValue("包数");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(3);
		cell.setCellValue("重量");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(4);
		cell.setCellValue("体积");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(5);
		cell.setCellValue("品名");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(6);
		cell.setCellValue("垫付款$");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(7);
		cell.setCellValue("垫付款￥");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(8);
		cell.setCellValue("未加垫付￥");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(9);
		cell.setCellValue("未加包费￥");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(10);
		cell.setCellValue("发货人");
		cell.setCellStyle(headerStyle);
			int pics= 0;
			Double weight2 =0.0;
			Double volum2=0.00;
			
			int adv =0;
			int noadv =0;	
			int advU =0;
			int nopac =0;
			int i=2;
			
			for(int j=0;j<list.size();j++){
				Waybill waybill = list.get(j);				
				
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue(waybill.getSddate());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(waybill.getWaybill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(waybill.getPics());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(waybill.getWeight());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(waybill.getVolumu());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue(waybill.getGoods());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue(waybill.getAdvancedU());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue(waybill.getAdvancedZ());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue(waybill.getNoaccAdvance());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue(waybill.getNoaccPackfee());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(10);
				cell.setCellValue(waybill.getSender());
				cell.setCellStyle(cellStyle);
				
				pics += waybill.getPics();
				weight2 += waybill.getWeight();
				volum2 += waybill.getVolumu();
				
				adv +=waybill.getAdvancedZ();
				advU +=waybill.getAdvancedU();
				nopac +=waybill.getNoaccPackfee();
				noadv +=waybill.getNoaccAdvance();	
				i++;
			}
			row = sheet.createRow(i);
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue(pics);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue(weight2);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue(volum2);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue(advU);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue(adv);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue(noadv);
			cell.setCellStyle(cellStyle);
			
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue(nopac);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(10);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
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
	//生成代理费 按ids的报表
	public InputStream getRaterStream(String ids){
		List<Waybill> list = this.waybillDao.listByIds(ids);
		String sheetName="代理费";
		String titleName="258 КАРГО 代理费明细";
		
		InputStream is = null;
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet(sheetName);
		//--页边距设置
		HSSFPrintSetup ps = (HSSFPrintSetup) sheet.getPrintSetup();
        ps.setLandscape(false); //打印方向，true:横向，false:纵向
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
        sheet.setMargin(HSSFSheet.BottomMargin, (double)0.5); //页边距（下）
        sheet.setMargin(HSSFSheet.LeftMargin, (double)0.1); //页边距（左）
        sheet.setMargin(HSSFSheet.RightMargin, (double)0.1); //页边距（右）
        sheet.setMargin(HSSFSheet.TopMargin, (double)0.5); //页边距（上）
        sheet.setHorizontallyCenter(true); //设置打印页面为水平居中
        //sheet.setVerticallyCenter(true); //设置打印页面为垂直居中
      //--列宽设置
        sheet.setColumnWidth(0, 2900);
        sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 2000);
		sheet.setColumnWidth(5, 2000);
		sheet.setColumnWidth(6, 2000);
		sheet.setColumnWidth(7, 2000);
		sheet.setColumnWidth(8, 2000);
		sheet.setColumnWidth(9, 2500);
	
		
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
		short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
		dateCellStyle.setDataFormat(df);
		
		Row row = sheet.createRow(0);
		
		
		HSSFCell cell =(HSSFCell) row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
		
		row.getCell(0).setCellValue(titleName);		
		row.getCell(0).setCellStyle(titleStyle);
		
		row = sheet.createRow(1);
		row.setHeight((short) 600);//目的是想把行高设置成400px
		cell = (HSSFCell) row.createCell(0);
		cell.setCellValue("日期");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(1);
		cell.setCellValue("代理人");
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
		cell.setCellValue("返率");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(6);
		cell.setCellValue("返点");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(7);
		cell.setCellValue("代收");
		cell.setCellStyle(headerStyle);
		
		
		cell = (HSSFCell) row.createCell(8);
		cell.setCellValue("合计");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(9);
		cell.setCellValue("运输状态");
		cell.setCellStyle(headerStyle);
		
			int pics= 0;
			Double weight2 =0.0;
			Double volum2=0.00;
			
			Double disc =0.0;
			int cod =0;	
		
			int i=2;
			
			for(int j=0;j<list.size();j++){
				Waybill waybill = list.get(j);				
				
				row = sheet.createRow(i);
				row.setHeight((short) 400);//目的是想把行高
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue(waybill.getSddate());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(waybill.getProcurator());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(waybill.getWaybill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(waybill.getPics());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(waybill.getWeight());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue(waybill.getRaterRate());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue(waybill.getDiscount());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue(waybill.getCod());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue(waybill.getCod()+waybill.getDiscount());
				cell.setCellStyle(cellStyle);
				String status ="";
				switch(waybill.getStatusId()){
        		case 1: status = "新建";
        		break;
        		case 2: status = "已配仓";
        		break;
        		case 3: status = "已分货，欠款";
        		break;	
        		case 4: status = "已到货";
        		break;					        		
        		case 5: status = "已到达，存货";
        		break;
        		case 6: status = "已结束";
        		break;
        		case 7: status = "灭失";
        		break;
        		case 8: status = "问题处理中";
        		break;
        		
        	}
				
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue(status);
				cell.setCellStyle(cellStyle);
				
				
				pics += waybill.getPics();
				weight2 += waybill.getWeight();
				volum2 += waybill.getVolumu();
				
				disc += waybill.getDiscount();
				cod +=waybill.getCod();	
				
				
				i++;
			}
			row = sheet.createRow(i);
			row.setHeight((short) 400);//目的是想把行高
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue(pics);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue(weight2);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue(disc);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue(cod);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue(disc+cod);
			cell.setCellStyle(cellStyle);
			
			cell = (HSSFCell) row.createCell(9);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			
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
	//生成垫付款 按ids的报表
	public InputStream getAdvanceStream(String ids){
		List<Waybill> list = this.waybillDao.listByIds(ids);
		String sheetName="垫付款";
		String titleName="258 КАРГО 垫付款明细";
		
		InputStream is = null;
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet(sheetName);
		//--页边距设置
		HSSFPrintSetup ps = (HSSFPrintSetup) sheet.getPrintSetup();
        ps.setLandscape(false); //打印方向，true:横向，false:纵向
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
        sheet.setMargin(HSSFSheet.BottomMargin, (double)0.5); //页边距（下）
        sheet.setMargin(HSSFSheet.LeftMargin, (double)0.1); //页边距（左）
        sheet.setMargin(HSSFSheet.RightMargin, (double)0.1); //页边距（右）
        sheet.setMargin(HSSFSheet.TopMargin, (double)0.5); //页边距（上）
        sheet.setHorizontallyCenter(true); //设置打印页面为水平居中
        //sheet.setVerticallyCenter(true); //设置打印页面为垂直居中
      //--列宽设置
        sheet.setColumnWidth(0, 2900);
        sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 2000);
		sheet.setColumnWidth(5, 2000);
		sheet.setColumnWidth(6, 2000);
		sheet.setColumnWidth(7, 2500);
		sheet.setColumnWidth(8, 2000);
		
	
		
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
		short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
		dateCellStyle.setDataFormat(df);
		
		Row row = sheet.createRow(0);
		
		
		HSSFCell cell =(HSSFCell) row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
		
		row.getCell(0).setCellValue(titleName);		
		row.getCell(0).setCellStyle(titleStyle);
		
		row = sheet.createRow(1);
		row.setHeight((short) 600);//目的是想把行高设置成400px
		cell = (HSSFCell) row.createCell(0);
		cell.setCellValue("日期");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(1);
		cell.setCellValue("发货人");
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
		cell.setCellValue("品名");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(6);
		cell.setCellValue("垫付款");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(7);
		cell.setCellValue("未加垫付");
		cell.setCellStyle(headerStyle);
		
		
		cell = (HSSFCell) row.createCell(8);
		cell.setCellValue("合计");
		cell.setCellStyle(headerStyle);
	
		
			int pics= 0;
			Double weight2 =0.0;
			Double volum2=0.00;
			
			Double adv =0.0;
			Double noadv =0.0;
			
		
			int i=2;
			
			for(int j=0;j<list.size();j++){
				Waybill waybill = list.get(j);				
				
				row = sheet.createRow(i);
				row.setHeight((short) 400);//目的是想把行高
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue(waybill.getSddate());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(waybill.getSender());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(waybill.getWaybill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(waybill.getPics());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(waybill.getWeight());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue(waybill.getGoods());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue(waybill.getAdvancedZ());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue(waybill.getNoaccAdvance());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue(waybill.getAdvancedZ()+waybill.getNoaccAdvance());
				cell.setCellStyle(cellStyle);
				
				
				
				pics += waybill.getPics();
				weight2 += waybill.getWeight();
				volum2 += waybill.getVolumu();
				
				adv += waybill.getAdvancedZ();
				noadv +=waybill.getNoaccAdvance();
				
				
				i++;
			}
			row = sheet.createRow(i);
			row.setHeight((short) 400);//目的是想把行高
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue(pics);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue(weight2);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			
			cell = (HSSFCell) row.createCell(6);
			cell.setCellValue(adv);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(7);
			cell.setCellValue(noadv);
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(8);
			cell.setCellValue(adv+noadv);
			cell.setCellStyle(cellStyle);
			
			
			
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
	//生成做价单
	public InputStream getMakePriceStream(String ids){
		List<Waybill> list = this.waybillDao.listByIds(ids);
		String sheetName="做价单";
		String titleName="258 КАРГО 货物做价单";
		
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
        sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 7000);
		sheet.setColumnWidth(3, 1800);
		sheet.setColumnWidth(4, 1800);
		sheet.setColumnWidth(5, 1800);
		sheet.setColumnWidth(6, 1800);
		sheet.setColumnWidth(7, 1800);
		sheet.setColumnWidth(8, 1800);
		sheet.setColumnWidth(9, 1800);
		sheet.setColumnWidth(10, 1800);
		sheet.setColumnWidth(11, 1800);
		sheet.setColumnWidth(12, 1800);
		sheet.setColumnWidth(13, 1800);
		sheet.setColumnWidth(14, 1800);
		sheet.setColumnWidth(15, 1800);
		sheet.setColumnWidth(16, 1800);
		sheet.setColumnWidth(17, 1800);
		sheet.setColumnWidth(18, 1800);
		sheet.setColumnWidth(19, 1800);
		sheet.setColumnWidth(20, 1800);
		sheet.setColumnWidth(21, 1800);
		sheet.setColumnWidth(22, 7000);
		sheet.setColumnWidth(23, 2500);
		sheet.setColumnWidth(24, 2500);
		
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
		short df=book.createDataFormat().getFormat("yyyy-mm-dd"); 
		dateCellStyle.setDataFormat(df);
		
		Row row = sheet.createRow(0);
		
		
		HSSFCell cell =(HSSFCell) row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 22));
		
		row.getCell(0).setCellValue(titleName);		
		row.getCell(0).setCellStyle(titleStyle);
		
		row = sheet.createRow(1);
		row.setHeight((short) 600);//目的是想把行高设置成400px
		cell = (HSSFCell) row.createCell(0);
		cell.setCellValue("日期");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(1);
		cell.setCellValue("发货人");
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
		cell.setCellValue("垫付$");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(6);
		cell.setCellValue("垫付￥");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(7);
		cell.setCellValue("包费$");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(8);
		cell.setCellValue("包费￥");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(9);
		cell.setCellValue("代理人");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(10);
		cell.setCellValue("返率");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(11);
		cell.setCellValue("返点");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(12);
		cell.setCellValue("代收");
		cell.setCellStyle(headerStyle);
		
		
		cell = (HSSFCell) row.createCell(13);
		cell.setCellValue("单价");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(14);
		cell.setCellValue("货值");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(15);
		cell.setCellValue("保率");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(16);
		cell.setCellValue("保险费");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(17);
		cell.setCellValue("索赔");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(18);
		cell.setCellValue("汇率");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(19);
		cell.setCellValue("运费");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(20);
		cell.setCellValue("应收");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(21);
		cell.setCellValue("品类");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(22);
		cell.setCellValue("品名");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(23);
		cell.setCellValue("备注");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(24);
		cell.setCellValue("目的地");
		cell.setCellStyle(headerStyle);
		
			int i=2;
			
			for(int j=0;j<list.size();j++){
				Waybill waybill = list.get(j);				
				
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue(waybill.getSddate());
				cell.setCellStyle(dateCellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(waybill.getSender());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(waybill.getWaybill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(waybill.getPics());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(waybill.getWeight());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(5);
				cell.setCellValue(waybill.getAdvancedU());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(6);
				cell.setCellValue(waybill.getAdvancedZ());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(7);
				cell.setCellValue(waybill.getPackfeeU());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(8);
				cell.setCellValue(waybill.getPackfeeZ());
				cell.setCellStyle(cellStyle);
				
				cell = (HSSFCell) row.createCell(9);
				cell.setCellValue(waybill.getProcurator());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(10);
				cell.setCellValue(waybill.getRaterRate());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(11);
				cell.setCellValue(waybill.getDiscount());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(12);
				cell.setCellValue(waybill.getCod());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(13);
				cell.setCellValue(waybill.getPrice());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(14);
				cell.setCellValue(waybill.getWorth());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(15);
				cell.setCellValue(waybill.getWorthRate());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(16);
				cell.setCellValue(waybill.getInsurance());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(17);
				cell.setCellValue(waybill.getIndemnity());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(18);
				cell.setCellValue(waybill.getExchangeRate());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(19);
				cell.setCellValue(waybill.getSumbill());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(20);
				cell.setCellValue(waybill.getTotal());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(21);
				cell.setCellValue(waybill.getMark());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(22);
				cell.setCellValue(waybill.getGoods());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(23);
				cell.setCellValue(waybill.getRemarks());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(24);
				cell.setCellValue(waybill.getDestName());
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
	//生成欠款总表
	public InputStream getArrearagesStream(String sender,String custName){
		Map getMap = this.listWaybillArrearages(sender, custName);
		List<Arrearages> list = (List<Arrearages>) getMap.get("rows");
		
		String sheetName="欠款统计表";
		String titleName="258 КАРГО 欠款统计表";
		
		InputStream is = null;
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet(sheetName);
		//--页边距设置
		HSSFPrintSetup ps = (HSSFPrintSetup) sheet.getPrintSetup();
        ps.setLandscape(false); //打印方向，true:横向，false:纵向
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
        sheet.setMargin(HSSFSheet.BottomMargin, (double)0.5); //页边距（下）
        sheet.setMargin(HSSFSheet.LeftMargin, (double)0.1); //页边距（左）
        sheet.setMargin(HSSFSheet.RightMargin, (double)0.1); //页边距（右）
        sheet.setMargin(HSSFSheet.TopMargin, (double)0.5); //页边距（上）
        sheet.setHorizontallyCenter(true); //设置打印页面为水平居中
        //sheet.setVerticallyCenter(true); //设置打印页面为垂直居中
      //--列宽设置
        sheet.setColumnWidth(0, 1800); //No序号
        sheet.setColumnWidth(1, 5000); //发货人
		sheet.setColumnWidth(2, 3000);	//收货人
		sheet.setColumnWidth(3, 3000);	//收货人编号
		sheet.setColumnWidth(4, 2000);   //欠款金额
		
		
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
		
		
		Row row = sheet.createRow(0);
		
		
		HSSFCell cell =(HSSFCell) row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		
		row.getCell(0).setCellValue(titleName);		
		row.getCell(0).setCellStyle(titleStyle);
		
		row = sheet.createRow(1);
		row.setHeight((short) 600);//目的是想把行高设置成400px
		cell = (HSSFCell) row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(headerStyle);
		
		cell = (HSSFCell) row.createCell(1);
		cell.setCellValue("发货人");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(2);
		cell.setCellValue("收货人");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(3);
		cell.setCellValue("收货人编号");
		cell.setCellStyle(headerStyle);
		cell = (HSSFCell) row.createCell(4);
		cell.setCellValue("欠款");
		cell.setCellStyle(headerStyle);
		
			int arr= 0;
			int k=1;
			int i=2;
			
			for(int j=0;j<list.size();j++){
				Arrearages arrgs = list.get(j);				
				
				row = sheet.createRow(i);
				cell = (HSSFCell) row.createCell(0);
				cell.setCellValue(k);
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(1);
				cell.setCellValue(arrgs.getSender());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(2);
				cell.setCellValue(arrgs.getCustName());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(3);
				cell.setCellValue(arrgs.getCustId());
				cell.setCellStyle(cellStyle);
				cell = (HSSFCell) row.createCell(4);
				cell.setCellValue(arrgs.getFee());
				cell.setCellStyle(cellStyle);
				
				
				arr += arrgs.getFee();
				
				i++;
				k++;
			}
			row = sheet.createRow(i);
			cell = (HSSFCell) row.createCell(0);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(1);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(2);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(3);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = (HSSFCell) row.createCell(4);
			cell.setCellValue(arr);
			cell.setCellStyle(cellStyle);
			
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
}
