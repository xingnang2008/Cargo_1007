package com.cargo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.BitchDao;
import com.cargo.dao.WaybillDao;
import com.cargo.model.Bitch;
import com.cargo.model.Waybill;

@Component("bitchService")
public class BitchService {

	private BitchDao bitchDao;
	private WaybillDao waybillDao;

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

	@Resource
	public void setWaybillDao(WaybillDao waybillDao) {
		this.waybillDao = waybillDao;
	}

	public void save(Bitch bitch) {
		this.bitchDao.save(bitch);
	}

	public void delete(Bitch bitch) {
		this.bitchDao.delete(bitch);
	}

	public void update(Bitch bitch) {	
		
		this.bitchDao.update(bitch);		
	}

	public List<Bitch> findAll() {
		return this.bitchDao.findAll();

	}

	public List<Waybill> queryWaybillByBitch(String bitch) {
		List<Waybill> waybills = new ArrayList<Waybill>();
		List<Waybill> list = this.waybillDao.queryByBitch(bitch);
		for (Waybill w : list) {
			waybills.add(w);
		}
		return waybills;
	}

	public List<Bitch> queryBitchByLine(String lineId) {
		return this.bitchDao.queryBitchByLine(lineId);
	}

	public Long getCount(String line) {
		return this.bitchDao.getCount(line);
	}

	public void deleteByIds(String ids) {
		this.bitchDao.deleteByIds(ids);
	}

	public List listByIds(String ids) {
		return this.bitchDao.lsitByIds(ids);
	}

	public Map find(String lineId, Date stDate, Date edDate) {
		return this.bitchDao.find(lineId, stDate, edDate);
	}

	public List<Bitch> findBySdDate(Date stDate, Date edDate) {
		return bitchDao.findBySdDate(stDate, edDate);
	}

}
