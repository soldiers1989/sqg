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
import com.soft.tbk.dao.TbkCouponMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkCoupon;
import com.soft.tbk.service.TbkCouponService;

@Service
public class TbkCouponServiceImpl extends BaseServiceImpl implements TbkCouponService {

    private static final String SYS_CODE = "TbkCouponSerciceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkCouponMapper tbkCouponMapper;

    @Override
    public TbkCoupon saveTbkCoupon(TbkCoupon tbkCoupon) throws ApiException {

        //1.check
        check(tbkCoupon);
        //3.set default
        setDefault(tbkCoupon);
        //4.保存
        saveTbkCouponModel(tbkCoupon);
        //5.domain设值
        return tbkCoupon;
    }

    private void setDefault(TbkCoupon tbkCoupon) {

        if (tbkCoupon == null) {
            return;
        }
        tbkCoupon.setStatus(1);// 默认有效
        tbkCoupon.setCreateTime(new Date());
        tbkCoupon.setUpdateTime(new Date());
    }

    private void check(TbkCoupon tbkCoupon) {

        if (null == tbkCoupon) {
            throw new ApiException(SYS_CODE + ".saveTbkCoupon", "数据不能为空");
        }

    }

    @Override
    public boolean updateTbkCoupon(TbkCoupon tbkCoupon) throws ApiException {

        check(tbkCoupon);

        return updataTbkCoupon(tbkCoupon);

    }

    @Override
    public boolean deleteTbkCoupon(Integer id) {

        return deleteTbkCouponModel(id);
    }

    @Override
    public TbkCoupon getTbkCoupon(Integer id) {

        return getTbkCouponById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkCoupon> queryTbkCoupon(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkCoupon> list = queryTbkCouponsModel(map);

        PageInfo<TbkCoupon> pageInfo = new PageInfo<TbkCoupon>(list);

        QueryResult<TbkCoupon> queryResult = new QueryResult<TbkCoupon>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkCoupon> queryTbkCouponsModel(Map<String, Object> map) {

        List<TbkCoupon> list = null;
        try {
            list = tbkCouponMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }

    private void saveTbkCouponModel(TbkCoupon tbkCoupon) {

        try {
            tbkCouponMapper.insertUseGeneratedKeys(tbkCoupon);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkCouponModel", "保存失败");
        }
    }

    private boolean updataTbkCoupon(TbkCoupon tbkCoupon) {

        try {
            int result = tbkCouponMapper.updateByPrimaryKeySelective(tbkCoupon);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkCoupon", "更新失败");
        }
    }

    private boolean deleteTbkCouponModel(Integer id) {

        try {
            int count = tbkCouponMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkCouponModel", "删除失败");
        }
    }

    private TbkCoupon getTbkCouponById(Integer id) {

        try {
            return tbkCouponMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkCoupon> list) {

        try {
            tbkCouponMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }

}
