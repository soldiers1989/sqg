package com.soft.tbk.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkCommission;


public interface TbkCommissionService extends BaseService{
    


    /**
     * 
     * @param tbkCommission
     * @return
     */
    public TbkCommission saveTbkCommission(TbkCommission tbkCommission) throws ApiException;

    /**
     * 
     * @param tbkCommission
     */
    public boolean updateTbkCommission(TbkCommission tbkCommission) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkCommission(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkCommission getTbkCommission(Integer id);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkCommission> queryTbkCommission(Map<String, Object> map);

    
    public void insertBatch(List<TbkCommission> tbkCommissionList);
    
    
    /**
     * 
     * @param tbkCommission
     */
    public boolean updateCommissionStatus(Integer orderId, Integer commissionStatus, Date settleDate) throws ApiException;


    
    public Map<String, Object> sumCommission(Integer userId, Date sumDate);
}
