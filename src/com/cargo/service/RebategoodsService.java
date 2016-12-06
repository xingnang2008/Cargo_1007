package com.cargo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.RebategoodsDao;
import com.cargo.model.Rebategoods;

@Component
public class RebategoodsService {
	@Resource
	private RebategoodsDao rebategoodsDao;
	
	public void save(Rebategoods rebategoods){
		this.rebategoodsDao.save(rebategoods);
	}
	
	public void delete(Rebategoods rebategoods){
		this.rebategoodsDao.delete(rebategoods);
	}
	public void update(Rebategoods rebategoods){
		this.rebategoodsDao.update(rebategoods);
	}
	public List<Rebategoods> findAll(){
		return this.rebategoodsDao.findAll();
		
	}
	public List listByIds(String ids){
		return this.rebategoodsDao.listByIds(ids);
	}
	public List listByRebateId(Integer rebateId){
		return this.rebategoodsDao.listByRebateId(rebateId);
	}
	public List listByRebateId(Integer rebateId,Integer page,Integer rows){
		return this.rebategoodsDao.listByRebateId(rebateId, page, rows);
	}
	public Long getCountByRebateId(Integer rebateId){
		return this.rebategoodsDao.getCountByRebateId(rebateId);
	}
	public void deleteByIds(String ids){
		this.rebategoodsDao.deleteByIds(ids);
	}
}
