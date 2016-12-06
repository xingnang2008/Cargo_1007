package com.cargo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.DestDao;
import com.cargo.model.Dest;

@Component("destService")
public class DestService {
		@Resource
		private DestDao destDao;
		
		public void save(Dest dest){
			this.destDao.save(dest);
		}
		public void delete(Dest dest){
			this.destDao.delete(dest);
		}
		public void update(Dest dest){
			this.destDao.update(dest);
		}
		public List findAll(){
			return this.destDao.findAll();
		}
		public List listByDestName(String destName,Integer page,Integer rows){
			return this.destDao.listByDestName(destName, page, rows);
		}
		public Long getCountByDest(String destName){
			return this.destDao.getCountByDest(destName);
		}
		public List listByIds(String ids){
			return this.destDao.listByIds(ids);
		}
		public void deleteByIds(String ids){
			this.destDao.deleteByIds(ids);
		}
		public Dest findByDestName(String destName) {
			return this.destDao.findByDestName(destName);
		}
}
