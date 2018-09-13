package com.soft.tbk.service;

import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkCoupon;


public interface TbkCouponService extends BaseService{
    


    /**
     * 
     * @param tbkCoupon
     * @return
     */
    public TbkCoupon saveTbkCoupon(TbkCoupon tbkCoupon) throws ApiException;

    /**
     * 
     * @param tbkCoupon
     */
    public boolean updateTbkCoupon(TbkCoupon tbkCoupon) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkCoupon(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkCoupon getTbkCoupon(Integer id);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkCoupon> queryTbkCoupon(Map<String, Object> map);

    
    public void insertBatch(List<TbkCoupon> tbkCouponList);

}
