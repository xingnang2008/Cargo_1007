package com.cargo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.DisburseDao;
import com.cargo.model.Disburse;

@Component("disburseService")
public class DisburseService {
	@Resource
	private DisburseDao dao;
	
	public void save(Disburse line){
		this.dao.save(line);
	}
	public void delete(Disburse line){
		this.dao.delete(line);
	}
	public void update(Disburse line){
		this.dao.update(line);
	}
	public List findAll(){
		return this.dao.findAll();
	}

	public void deleteByIds(String ids){
		this.dao.deleteByIds(ids);
	}
	public Map find(String lineId,Integer sort,Integer payMethod, Date stDate,Date edDate){
			return this.dao.find(lineId, sort, payMethod, stDate, edDate);
	}

	public Disburse findById(Integer id){
		return this.dao.findById(id);
	}
	
	
}
