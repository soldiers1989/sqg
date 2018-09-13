package com.soft.tbk.base;

import java.util.Map;

import com.soft.tbk.utils.BeanMapUtil;

public abstract class BaseQueryRequest extends BaseRequest {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5604289318242452183L;

	private Integer pageNum;

    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    
    public Map<String, Object> request2Map() {
    	return BeanMapUtil.beanToMap(this);
    }
    
}
