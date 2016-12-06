package com.cargo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.MarkDao;
import com.cargo.model.Mark;

@Component
public class MarkService {

	@Resource
	private MarkDao markDao;

	public void save(Mark mark){
		this.markDao.save(mark);
	}
	public void delete(Mark mark){
		this.markDao.delete(mark);
	}
	public void update(Mark mark){
		this.markDao.update(mark);
	}
	public List findAll(){
		return this.markDao.findAll();
	}
	public Map find(String type,Integer page,Integer rows){
		return this.markDao.find(type, page, rows);
	}
	public void deleteByIds(String ids) {
		this.markDao.deleteByIds(ids);
	}
	public Mark findByType(String type){
		return this.markDao.findByType(type);
	}
	
	
	
}
