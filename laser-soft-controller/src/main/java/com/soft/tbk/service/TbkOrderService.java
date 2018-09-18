package com.soft.tbk.service;

import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkOrder;


public interface TbkOrderService extends BaseService{
    


    /**
     * 
     * @param tbkOrder
     * @return
     */
    public TbkOrder saveTbkOrder(TbkOrder tbkOrder) throws ApiException;

    /**
     * 
     * @param tbkOrder
     */
    public boolean updateTbkOrder(TbkOrder tbkOrder) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkOrder(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkOrder getTbkOrder(Integer id);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkOrder> queryTbkOrder(Map<String, Object> map);

    
    public void insertBatch(List<TbkOrder> tbkOrderList);
    
    
}
