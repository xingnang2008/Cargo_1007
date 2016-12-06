package com.cargo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.ProcuratorDao;
import com.cargo.model.Procurator;

@Component
public class ProcuratorService {
		@Resource
		private ProcuratorDao procuratorDao;
		

		public void save(Procurator procurator){
			this.procuratorDao.save(procurator);
		}
		
		public void delete(Procurator procurator){
			this.procuratorDao.delete(procurator);
		}
		public void update(Procurator procurator){
			this.procuratorDao.update(procurator);
		}
		public List<Procurator> findAll(){
			return this.procuratorDao.findAll();
			
		}
		public Procurator findById(java.lang.Integer id) {
			return this.procuratorDao.findById(id);
		}
		public List listByProcurator(String procurator,Integer page,Integer rows){
			return this.procuratorDao.listByProcurator(procurator, page, rows);
		}
		public Long getCountByProcurator(String sdName){
			return this.procuratorDao.getCountByProcurator(sdName);
		}
		public void deleteByIds(String ids){
			this.procuratorDao.deleteByIds(ids);
		}
		public Procurator findByProcurator(String procurator){
			return this.procuratorDao.findByProcurator(procurator);
		}
		
}
