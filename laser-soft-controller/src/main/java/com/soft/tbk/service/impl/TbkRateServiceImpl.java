package com.soft.tbk.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.soft.tbk.base.BaseServiceImpl;
import com.soft.tbk.dao.TbkRateMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkRate;
import com.soft.tbk.service.TbkRateService;

@Service
public class TbkRateServiceImpl extends BaseServiceImpl implements TbkRateService {

    private static final String SYS_CODE = "TbkRateSerciceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkRateMapper tbkRateMapper;

    @Override
    public TbkRate saveTbkRate(TbkRate tbkRate) throws ApiException {

        //1.check
        check(tbkRate);
        //3.set default
        setDefault(tbkRate);
        //4.保存
        saveTbkRateModel(tbkRate);
        //5.domain设值
        return tbkRate;
    }

    private void setDefault(TbkRate tbkRate) {

        if (tbkRate == null) {
            return;
        }
        tbkRate.setCreateTime(new Date());
        tbkRate.setUpdateTime(new Date());
    }

    private void check(TbkRate tbkRate) {

        if (null == tbkRate) {
            throw new ApiException(SYS_CODE + ".saveTbkRate", "数据不能为空");
        }

    }

    @Override
    public boolean updateTbkRate(TbkRate tbkRate) throws ApiException {

        check(tbkRate);

        return updataTbkRate(tbkRate);

    }

    @Override
    public boolean deleteTbkRate(Integer id) {

        return deleteTbkRateModel(id);
    }

    @Override
    public TbkRate getTbkRate(Integer id) {

        return getTbkRateById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkRate> queryTbkRate(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkRate> list = queryTbkRatesModel(map);

        PageInfo<TbkRate> pageInfo = new PageInfo<TbkRate>(list);

        QueryResult<TbkRate> queryResult = new QueryResult<TbkRate>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkRate> queryTbkRatesModel(Map<String, Object> map) {

        List<TbkRate> list = null;
        try {
            list = tbkRateMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }

    private void saveTbkRateModel(TbkRate tbkRate) {

        try {
            tbkRateMapper.insertUseGeneratedKeys(tbkRate);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkRateModel", "保存失败");
        }
    }

    private boolean updataTbkRate(TbkRate tbkRate) {

        try {
            int result = tbkRateMapper.updateByPrimaryKeySelective(tbkRate);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkRate", "更新失败");
        }
    }

    private boolean deleteTbkRateModel(Integer id) {

        try {
            int count = tbkRateMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkRateModel", "删除失败");
        }
    }

    private TbkRate getTbkRateById(Integer id) {

        try {
            return tbkRateMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkRate> list) {

        try {
            tbkRateMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }

    @Override
    public BigDecimal getRateByLevel(String userLevel, Integer rateLevel) {

        Map<String, Object> map = new HashMap<>();
        map.put("userLevel", userLevel);
        map.put("rateLevel", rateLevel);
        List<TbkRate> list = queryTbkRatesModel(map);
        if (list != null && !list.isEmpty()) {
            return list.get(0).getRateValue();
        }
        return null;
    }

}
