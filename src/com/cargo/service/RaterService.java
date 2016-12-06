package com.cargo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.RaterDao;
import com.cargo.model.Rater;

@Component
public class RaterService {
	@Resource
	private RaterDao raterDao;
	
	public void save(Rater rater){
		this.raterDao.save(rater);
	}
	public void delete(Rater rater){
		this.raterDao.delete(rater);
	}
	public void update(Rater rater){
		this.raterDao.update(rater);
	}
	public List findAll(){
		return this.raterDao.findAll();
	}
	public Rater findById(java.lang.Integer id) {
		return this.raterDao.findById(id);
	}
	public void deleteByIds(String ids) {
		this.raterDao.deleteByIds(ids);
	}
	public List listByRater(String raterName,Integer page,Integer rows){
		return this.raterDao.listByRater(raterName, page, rows);
	}
	public Long getCountByRater(String raterName){
		return this.raterDao.getCountByRater(raterName);
		
	}
	public Map find(String raterName,Integer page,Integer rows){
		return this.raterDao.find(raterName, page, rows);
	}
	public Rater findByRaterName(String raterName){
		return this.raterDao.findByRaterName(raterName);
	}
}
