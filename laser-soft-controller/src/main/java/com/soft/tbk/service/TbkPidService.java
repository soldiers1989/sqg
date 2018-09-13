package com.soft.tbk.service;

import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkPid;


public interface TbkPidService extends BaseService{
    


    /**
     * 
     * @param tbkPid
     * @return
     */
    public TbkPid saveTbkPid(TbkPid tbkPid) throws ApiException;

    /**
     * 
     * @param tbkPid
     */
    public boolean updateTbkPid(TbkPid tbkPid) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkPid(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkPid getTbkPid(Integer id);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkPid> queryTbkPid(Map<String, Object> map);

    
    public void insertBatch(List<TbkPid> tbkPidList);

}
