package com.cargo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.CompanyDao;
import com.cargo.model.Company;

@Component
public class CompanyService {
	@Resource
	private CompanyDao companyDao;
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public List isCompanyExist(String company){
		return this.companyDao.isCompanyExist(company);
	}
	public void save(Company company){
		this.companyDao.save(company);
	}
	
	public void delete(Company company){
		this.companyDao.delete(company);
	}
	public void update(Company company){
		this.companyDao.update(company);
	}
	public List<Company> findAll(){
		return this.companyDao.findAll();
		
	}
	public void deleteByIds(String ids){
		this.companyDao.deleteByIds(ids);
	}
	public Company findByName(String name){
		return this.companyDao.findByName(name);
	}
}
