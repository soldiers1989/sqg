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
import com.soft.tbk.dao.TbkWithdrawMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkWithdraw;
import com.soft.tbk.service.TbkWithdrawService;

@Service
public class TbkWithdrawServiceImpl extends BaseServiceImpl implements TbkWithdrawService {

    private static final String SYS_CODE = "TbkWithdrawSerciceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkWithdrawMapper tbkWithdrawMapper;

    @Override
    public TbkWithdraw saveTbkWithdraw(TbkWithdraw tbkWithdraw) throws ApiException {

        //1.check
        check(tbkWithdraw);
        //3.set default
        setDefault(tbkWithdraw);
        //4.保存
        saveTbkWithdrawModel(tbkWithdraw);
        //5.domain设值
        return tbkWithdraw;
    }

    private void setDefault(TbkWithdraw tbkWithdraw) {

        if (tbkWithdraw == null) {
            return;
        }
        tbkWithdraw.setStatus(0);
        tbkWithdraw.setCreateTime(new Date());
        tbkWithdraw.setUpdateTime(new Date());
    }

    private void check(TbkWithdraw tbkWithdraw) {

        if (null == tbkWithdraw) {
            throw new ApiException(SYS_CODE + ".saveTbkWithdraw", "数据不能为空");
        }

    }

    @Override
    public boolean updateTbkWithdraw(TbkWithdraw tbkWithdraw) throws ApiException {

        check(tbkWithdraw);

        return updataTbkWithdraw(tbkWithdraw);

    }

    @Override
    public boolean deleteTbkWithdraw(Integer id) {

        return deleteTbkWithdrawModel(id);
    }

    @Override
    public TbkWithdraw getTbkWithdraw(Integer id) {

        return getTbkWithdrawById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkWithdraw> queryTbkWithdraw(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkWithdraw> list = queryTbkWithdrawsModel(map);

        PageInfo<TbkWithdraw> pageInfo = new PageInfo<TbkWithdraw>(list);

        QueryResult<TbkWithdraw> queryResult = new QueryResult<TbkWithdraw>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkWithdraw> queryTbkWithdrawsModel(Map<String, Object> map) {

        List<TbkWithdraw> list = null;
        try {
            list = tbkWithdrawMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }

    private void saveTbkWithdrawModel(TbkWithdraw tbkWithdraw) {

        try {
            tbkWithdrawMapper.insertUseGeneratedKeys(tbkWithdraw);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkWithdrawModel", "保存失败");
        }
    }

    private boolean updataTbkWithdraw(TbkWithdraw tbkWithdraw) {

        try {
            int result = tbkWithdrawMapper.updateByPrimaryKeySelective(tbkWithdraw);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkWithdraw", "更新失败");
        }
    }

    private boolean deleteTbkWithdrawModel(Integer id) {

        try {
            int count = tbkWithdrawMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkWithdrawModel", "删除失败");
        }
    }

    private TbkWithdraw getTbkWithdrawById(Integer id) {

        try {
            return tbkWithdrawMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkWithdraw> list) {

        try {
            tbkWithdrawMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }

}
