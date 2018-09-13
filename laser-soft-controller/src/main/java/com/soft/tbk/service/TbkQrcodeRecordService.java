package com.soft.tbk.service;

import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkQrcodeRecord;


public interface TbkQrcodeRecordService extends BaseService{
    


    /**
     * 
     * @param tbkQrcodeRecord
     * @return
     */
    public TbkQrcodeRecord saveTbkQrcodeRecord(TbkQrcodeRecord tbkQrcodeRecord) throws ApiException;

    /**
     * 
     * @param tbkQrcodeRecord
     */
    public boolean updateTbkQrcodeRecord(TbkQrcodeRecord tbkQrcodeRecord) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkQrcodeRecord(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkQrcodeRecord getTbkQrcodeRecord(Integer id);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkQrcodeRecord> queryTbkQrcodeRecord(Map<String, Object> map);

    
    public void insertBatch(List<TbkQrcodeRecord> tbkQrcodeRecordList);

}
