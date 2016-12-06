package com.cargo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.LineDao;
import com.cargo.model.Line;

@Component("lineService")
public class LineService {
	@Resource
	private LineDao lineDao;
	
	public void save(Line line){
		this.lineDao.save(line);
	}
	public void delete(Line line){
		this.lineDao.delete(line);
	}
	public void update(Line line){
		this.lineDao.update(line);
	}
	public List findAll(){
		return this.lineDao.findAll();
	}
	public List listBylineId(String lineId,Integer page,Integer rows){
		return this.lineDao.listByline(lineId, page, rows);
	}
	public Long getCountByLineId(String lineId){
		return this.lineDao.getCountByLine(lineId);
	}
	public void deleteByIds(String ids){
		this.lineDao.deleteByIds(ids);
	}
	public Map find(String lineId,Integer page,Integer rows){
		return this.lineDao.find(lineId, page, rows);
	}
	public Line findByLineId(String lineId){
		return this.lineDao.findByLineId(lineId);
	}
	public Line findById(Integer id){
		return this.lineDao.findById(id);
	}
	
	
}
