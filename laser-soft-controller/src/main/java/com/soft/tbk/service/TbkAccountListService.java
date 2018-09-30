package com.soft.tbk.service;

import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkAccountList;


public interface TbkAccountListService extends BaseService{

    /**
     * 
     * @param tbkAccountList
     * @return
     */
    public TbkAccountList saveTbkAccountList(TbkAccountList tbkAccountList) throws ApiException;

    /**
     * 
     * @param tbkAccountList
     */
    public boolean updateTbkAccountList(TbkAccountList tbkAccountList) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkAccountList(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkAccountList getTbkAccountList(Integer id);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkAccountList> queryTbkAccountList(Map<String, Object> map);

    
    public void insertBatch(List<TbkAccountList> tbkAccountListList);

}
