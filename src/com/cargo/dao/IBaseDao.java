package com.cargo.dao;

import java.util.List;

import com.cargo.model.Role;

public interface IBaseDao<T> {
	
	public void save(T t);
	
	public void update(T t);

	public void delete(int id);
	
	public void deleteByIds(String ids);
	
	public T findById(int id);
	
	public List<T> findAll();

}
