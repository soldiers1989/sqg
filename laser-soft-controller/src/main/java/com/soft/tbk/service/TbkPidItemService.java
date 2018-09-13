package com.soft.tbk.service;

import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkPidItem;


public interface TbkPidItemService extends BaseService{
    


    /**
     * 
     * @param tbkPidItem
     * @return
     */
    public TbkPidItem saveTbkPidItem(TbkPidItem tbkPidItem) throws ApiException;

    /**
     * 
     * @param tbkPidItem
     */
    public boolean updateTbkPidItem(TbkPidItem tbkPidItem) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkPidItem(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkPidItem getTbkPidItem(Integer id);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkPidItem> queryTbkPidItem(Map<String, Object> map);

    
    public void insertBatch(List<TbkPidItem> tbkPidItemList);

}
