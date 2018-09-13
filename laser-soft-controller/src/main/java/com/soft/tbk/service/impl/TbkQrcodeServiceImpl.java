package com.soft.tbk.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.soft.tbk.base.BaseServiceImpl;
import com.soft.tbk.dao.TbkQrcodeMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkQrcode;
import com.soft.tbk.service.TbkQrcodeService;

@Service
public class TbkQrcodeServiceImpl extends BaseServiceImpl implements TbkQrcodeService{

    private static final String SYS_CODE = "TbkQrcodeSerciceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkQrcodeMapper tbkQrcodeMapper;

    @Override
    public TbkQrcode saveTbkQrcode(TbkQrcode tbkQrcode) throws ApiException {
        //1.check
        check(tbkQrcode);
        //3.set default
        setDefault(tbkQrcode);
        //4.保存
        saveTbkQrcodeModel(tbkQrcode);
        //5.domain设值
        return tbkQrcode;
    }


    private void setDefault(TbkQrcode tbkQrcode) {
        if (tbkQrcode == null) {
            return;
        }
        tbkQrcode.setCreateTime(new Date());
        tbkQrcode.setUpdateTime(new Date());
    }

    private void check(TbkQrcode tbkQrcode) {

        if (null == tbkQrcode) {
            throw new ApiException(SYS_CODE + ".saveTbkQrcode", "数据不能为空");
        }
        
    }

    @Override
    public boolean updateTbkQrcode(TbkQrcode tbkQrcode) throws ApiException {

        check(tbkQrcode);

        return updataTbkQrcode(tbkQrcode);

    }

    @Override
    public boolean deleteTbkQrcode(Integer id) {

        return deleteTbkQrcodeModel(id);
    }

    @Override
    public TbkQrcode getTbkQrcode(Integer id) {

        return getTbkQrcodeById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkQrcode> queryTbkQrcode(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkQrcode> list = queryTbkQrcodesModel(map);

        PageInfo<TbkQrcode> pageInfo = new PageInfo<TbkQrcode>(list);

        QueryResult<TbkQrcode> queryResult = new QueryResult<TbkQrcode>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkQrcode> queryTbkQrcodesModel(Map<String, Object> map) {

        List<TbkQrcode> list = null;
        try {
            list = tbkQrcodeMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }


    private void saveTbkQrcodeModel(TbkQrcode tbkQrcode) {

        try {
            tbkQrcodeMapper.insertUseGeneratedKeys(tbkQrcode);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkQrcodeModel", "保存失败");
        }
    }

    private boolean updataTbkQrcode(TbkQrcode tbkQrcode) {

        try {
            int result = tbkQrcodeMapper.updateByPrimaryKeySelective(tbkQrcode);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkQrcode", "更新失败");
        }
    }

    private boolean deleteTbkQrcodeModel(Integer id) {

        try {
            int count = tbkQrcodeMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkQrcodeModel", "删除失败");
        }
    }

    private TbkQrcode getTbkQrcodeById(Integer id) {

        try {
            return tbkQrcodeMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkQrcode> list) {

        try {
            tbkQrcodeMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }

}
