package com.cargo.action;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Component;


import com.cargo.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@SuppressWarnings("restriction")
@Component
public abstract class BaseAction<T> extends ActionSupport implements ApplicationAware,
		SessionAware, RequestAware, ModelDriven<T> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	protected T model;
	protected Integer page;
	protected Integer rows;
	protected String ids;
	protected Map<String,Object> pageMap = null;
	protected List<T> jsonList =null;
	protected InputStream inputStream = null;
	protected Map<String,Object> application;
	protected Map<String,Object> session;
	protected Map<String,Object> request;
	protected Date stdate;
	protected Date enddate;

	protected File excelFile;
	protected String excelFileFileName;  //这个名字必须是 File  这个的名字+FileName（即excelFile+FileName)
	protected InputStream downloadFile;	//下载用的
	//service 
	

	@Resource
	protected UserService userService;
	
	
	
	
	public BaseAction(){
		ParameterizedType type= (ParameterizedType)this.getClass()
										.getGenericSuperclass();
		Class clazz =(Class)type.getActualTypeArguments()[0];
		try{
			model=(T)clazz.newInstance();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public T getModel(){
		return model;
	}
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public void setPageMap(Map<String, Object> pageMap) {
		this.pageMap = pageMap;
	}
	
	public Map<String, Object> getPageMap() {
		return pageMap;
	}
	public List<T> getJsonList() {
		return jsonList;
	}
	public void setJsonList(List<T> jsonList) {
		this.jsonList = jsonList;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public void setApplication(Map<String, Object> application) {
		this.application = application;

	}

	public void setSession(Map<String, Object> session) {
		this.session=session;

	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;

	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public File getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	public String getExcelFileFileName() {
		return excelFileFileName;
	}
	public void setExcelFileFileName(String excelFileFileName) {
		this.excelFileFileName = excelFileFileName;
	}
	public InputStream getDownloadFile() {
		return downloadFile;
	}
	public void setDownloadFile(InputStream downloadFile) {
		this.downloadFile = downloadFile;
	}
	public Date getStdate() {
		return stdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setStdate(Date stdate) {
		this.stdate = stdate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	
	
	
}
