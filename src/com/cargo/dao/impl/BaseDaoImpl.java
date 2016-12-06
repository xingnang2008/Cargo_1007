package com.cargo.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cargo.dao.IBaseDao;
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements IBaseDao<T> {
	
	private Class clazz;
	
	public BaseDaoImpl(){
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		clazz = (Class)type.getActualTypeArguments()[0];
	}
	
	@Resource
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		Session s = sessionFactory.getCurrentSession();
		if(s==null){
			s = getNewSession();
		}
		return s;
	}
	private Session getNewSession(){
		
		Session s = sessionFactory.openSession();
		return s;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void delete(int  id) {
		try {
			String hql="DELETE FROM "+ clazz.getSimpleName() +" WHERE id:=id";
			getSession().createQuery(hql)
			.setInteger("id", id)
			.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public List<T> findAll() {
		try {
			String hql="FROM "+ clazz.getSimpleName();
			return getSession().createQuery(hql).list();
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public T findById(int  id) {
		return (T)getSession().get(clazz, id);
	}
	public void save(T t) {
		try {
			getSession().save(t);
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public void update(T t) {
		try {
			getSession().update(t);
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public void deleteByIds(String ids) {
		try {
			String hql="DELETE FROM "+ clazz.getSimpleName() +" WHERE id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	

}
