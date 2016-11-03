package com.cargo.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDao {
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
	

}
