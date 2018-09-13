package com.soft.tbk.service;

import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkWithdraw;


public interface TbkWithdrawService extends BaseService{
    


    /**
     * 
     * @param tbkWithdraw
     * @return
     */
    public TbkWithdraw saveTbkWithdraw(TbkWithdraw tbkWithdraw) throws ApiException;

    /**
     * 
     * @param tbkWithdraw
     */
    public boolean updateTbkWithdraw(TbkWithdraw tbkWithdraw) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkWithdraw(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkWithdraw getTbkWithdraw(Integer id);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkWithdraw> queryTbkWithdraw(Map<String, Object> map);

    
    public void insertBatch(List<TbkWithdraw> tbkWithdrawList);

}
