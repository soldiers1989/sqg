package com.soft.tbk.base;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.github.pagehelper.PageHelper;
import com.soft.tbk.utils.StringUtils;

/**
 * BaseService
 */

public abstract class BaseServiceImpl {

    public static final Integer DEFAULT_DATA_STATE = 0;

    public static final String UPDATE_ID = "id";

    public static final String OLD_DATA_STATE = "oldDataState";

    public static final String DATA_STATE = "dataState";

    public static final String PAGE_NUM = "pageNum";

    public static final String PAGE_SIZE = "pageSize";

    public static final int INITIAL_CAPACITY = 3;

    public static final String LOGIC_DELETE ="logicDelete";
    
    
    protected Map<String, Object> getQueryParamMap(String key, Object... values) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] keys = key.split(",");
            if (keys.length > 0) {
                for (int i = 0; i < keys.length; i++) {
                    map.put(keys[i], values[i]);
                }
            }
        } catch (Exception e) {
            map = null;
        }
        return map;
    }


    protected String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    protected HashMap<String, Object> getUpdateStateMap(long id, Integer dataState, Integer oldDataState) {
        HashMap<String, Object> updateMap = new HashMap<String, Object>(INITIAL_CAPACITY);
        updateMap.put(UPDATE_ID, id);
        updateMap.put(OLD_DATA_STATE, oldDataState);
        updateMap.put(DATA_STATE, dataState);
        return updateMap;
    }
    
    /**
     * 设置分页查询pageBounds参数
     * @param map
     * @return
     */
    protected void setPage(Map<String, Object> map) {
        Integer pageNum = (Integer) map.get(PAGE_NUM);
        Integer pageSize = (Integer) map.get(PAGE_SIZE);
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum.intValue(), pageSize.intValue());
        }

    }

}
