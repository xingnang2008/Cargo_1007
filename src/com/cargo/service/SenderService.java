package com.cargo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.SenderDao;
import com.cargo.model.Sender;

@Component
public class SenderService {
		@Resource
		private SenderDao senderDao;
		

		public void save(Sender sender){
			this.senderDao.save(sender);
		}
		
		public void delete(Sender sender){
			this.senderDao.delete(sender);
		}
		public void update(Sender sender){
			this.senderDao.update(sender);
		}
		public List<Sender> findAll(){
			return this.senderDao.findAll();
			
		}
		public Sender findById(java.lang.Integer id) {
			return this.senderDao.findById(id);
		}
		public List listBySender(String sender,Integer page,Integer rows){
			return this.senderDao.listBySender(sender, page, rows);
		}
		public Long getCountBySender(String sdName){
			return this.senderDao.getCountBySender(sdName);
		}
		public void deleteByIds(String ids){
			this.senderDao.deleteByIds(ids);
		}
		public Sender findBySender(String sender){
			return this.senderDao.findBySender(sender);
		}
		
}
