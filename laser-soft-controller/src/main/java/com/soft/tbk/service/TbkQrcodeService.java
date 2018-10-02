package com.soft.tbk.service;

import java.util.List;
import java.util.Map;

import com.soft.tbk.base.BaseService;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkQrcode;

public interface TbkQrcodeService extends BaseService {

    /**
     * 
     * @param tbkQrcode
     * @return
     */
    public TbkQrcode saveTbkQrcode(TbkQrcode tbkQrcode) throws ApiException;

    /**
     * 
     * @param tbkQrcode
     */
    public boolean updateTbkQrcode(TbkQrcode tbkQrcode) throws ApiException;

    /**
     * 刪除
     * 
     * @param id
     * @return
     */
    public boolean deleteTbkQrcode(Integer id);

    /**
     * 根據用戶的二維碼
     * 
     * @param userId
     * @param qrType
     * @return
     */
    public String getTbkQrcode(Integer userId, String qrType);

    /**
     * 根據用戶id獲取二維碼
     * 
     * @param id
     * @return
     */
    public TbkQrcode getTbkQrcode(Integer id);

    /**
     * 查询
     * 
     * @param map
     * @return
     */
    QueryResult<TbkQrcode> queryTbkQrcode(Map<String, Object> map);

    public void insertBatch(List<TbkQrcode> tbkQrcodeList);

}
