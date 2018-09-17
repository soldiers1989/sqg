package com.soft.tbk.service;

import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkUser;

public interface TbkUserService extends BaseService {

    /**
     * 
     * @param tbkUser
     * @return
     */
    public TbkUser saveTbkUserWithOpenId(TbkUser tbkUser) throws ApiException;

    /**
     * 
     * @param tbkUser
     * @return
     */
    public TbkUser saveTbkUser(TbkUser tbkUser) throws ApiException;

    /**
     * 
     * @param tbkUser
     */
    public boolean updateTbkUser(TbkUser tbkUser) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkUser(Integer id);

    /**
     * 根據ID獲取
     * 
     * @param id
     * @return
     */
    public TbkUser getTbkUser(Integer id);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkUser> queryTbkUser(Map<String, Object> map);

    public void insertBatch(List<TbkUser> tbkUserList);

}
