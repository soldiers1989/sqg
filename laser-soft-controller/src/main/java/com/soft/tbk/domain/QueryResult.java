package com.soft.tbk.domain;

import java.util.List;

public class QueryResult<T> {

	private List<T> list;
	
	private long total;
	
	public QueryResult(List<T> list, long total) {
		this.list = list;
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	
}
