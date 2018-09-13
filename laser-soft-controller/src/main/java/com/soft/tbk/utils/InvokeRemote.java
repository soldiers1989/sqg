package com.soft.tbk.utils;

import java.util.LinkedHashMap;

/**
 * 远程调用对象
 * 
 * @author zhiqiang.song
 *
 */
public class InvokeRemote {

	/**
	 * 请求地址
	 */
	private String url;

	/**
	 * 调用参数
	 */
	private LinkedHashMap<String, Object> params;
	
	/**
	 * 调用方式http,httpjson,spring
	 */
	private String type;

	/**
	 * 返回的属性key
	 */
	private String name;
	
	/**
	 * 返回类型0:直接返回,1:对象返回,2:json格式返回
	 */
	private String resultType;
	
	
	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LinkedHashMap<String, Object> getParams() {
		return params;
	}

	public void setParams(LinkedHashMap<String, Object> params) {
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
