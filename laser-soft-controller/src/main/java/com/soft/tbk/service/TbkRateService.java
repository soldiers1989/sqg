package com.soft.tbk.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkRate;

public interface TbkRateService extends BaseService {

    /**
     * 
     * @param tbkRate
     * @return
     */
    public TbkRate saveTbkRate(TbkRate tbkRate) throws ApiException;

    /**
     * 
     * @param tbkRate
     */
    public boolean updateTbkRate(TbkRate tbkRate) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkRate(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkRate getTbkRate(Integer id);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkRate> queryTbkRate(Map<String, Object> map);

    public void insertBatch(List<TbkRate> tbkRateList);

    public BigDecimal getRateByLevel(String userLevel, Integer rateLevel);

}
