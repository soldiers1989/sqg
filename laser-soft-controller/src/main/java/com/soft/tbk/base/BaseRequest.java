package com.soft.tbk.base;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

public abstract class BaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2443829967944882901L;
	
	public <T> T makeDomain(T domain) {
		
		try {
			BeanUtils.copyProperties(this, domain);
		} catch (Exception e) {
			return null;
		}
		return domain;
	}
}
