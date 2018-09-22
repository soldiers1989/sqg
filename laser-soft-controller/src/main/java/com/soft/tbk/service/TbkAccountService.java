package com.soft.tbk.service;

import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkAccount;


public interface TbkAccountService extends BaseService{
    


    /**
     * 
     * @param tbkAccount
     * @return
     */
    public TbkAccount saveTbkAccount(TbkAccount tbkAccount) throws ApiException;

    /**
     * 
     * @param tbkAccount
     */
    public boolean updateTbkAccount(TbkAccount tbkAccount) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkAccount(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkAccount getTbkAccount(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkAccount getTbkAccountByUserId(Integer userId);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkAccount> queryTbkAccount(Map<String, Object> map);

    
    public void insertBatch(List<TbkAccount> tbkAccountList);

}
