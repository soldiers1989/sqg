package com.soft.tbk.service.impl;

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
import com.soft.tbk.dao.TbkOrderMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkCoupon;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.service.TbkCouponService;
import com.soft.tbk.service.TbkOrderService;
import com.soft.tbk.utils.ListUtil;

@Service
public class TbkOrderServiceImpl extends BaseServiceImpl implements TbkOrderService{

    private static final String SYS_CODE = "TbkOrderSerciceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkOrderMapper tbkOrderMapper;
    
    @Autowired
    private TbkCouponService tbkCouponService;

    @Override
    public TbkOrder saveTbkOrder(TbkOrder tbkOrder) throws ApiException {
        //1.check
        check(tbkOrder);
        //3.set default
        setDefault(tbkOrder);
        //4.保存
        saveTbkOrderModel(tbkOrder);
        //5.domain设值
        return tbkOrder;
    }


    private void setDefault(TbkOrder tbkOrder) {
        if (tbkOrder == null) {
            return;
        }
        tbkOrder.setCreateTime(new Date());
        tbkOrder.setUpdateTime(new Date());
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemId", tbkOrder.getItemId());
        map.put("pageNum", 1);
        map.put("pageSize", 1);
        QueryResult<TbkCoupon> queryResult = tbkCouponService.queryTbkCoupon(map);
        if (queryResult != null && ListUtil.isNotEmpty(queryResult.getList())) {
            tbkOrder.setItemImage(queryResult.getList().get(0).getItemImage());
        }
    }

    private void check(TbkOrder tbkOrder) {

        if (null == tbkOrder) {
            throw new ApiException(SYS_CODE + ".saveTbkOrder", "数据不能为空");
        }
        
    }

    @Override
    public boolean updateTbkOrder(TbkOrder tbkOrder) throws ApiException {

        check(tbkOrder);

        return updataTbkOrder(tbkOrder);

    }

    @Override
    public boolean deleteTbkOrder(Integer id) {

        return deleteTbkOrderModel(id);
    }

    @Override
    public TbkOrder getTbkOrder(Integer id) {

        return getTbkOrderById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkOrder> queryTbkOrder(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkOrder> list = queryTbkOrdersModel(map);

        PageInfo<TbkOrder> pageInfo = new PageInfo<TbkOrder>(list);

        QueryResult<TbkOrder> queryResult = new QueryResult<TbkOrder>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkOrder> queryTbkOrdersModel(Map<String, Object> map) {

        List<TbkOrder> list = null;
        try {
            list = tbkOrderMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }


    private void saveTbkOrderModel(TbkOrder tbkOrder) {

        try {
            tbkOrderMapper.insertUseGeneratedKeys(tbkOrder);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkOrderModel", "保存失败");
        }
    }

    private boolean updataTbkOrder(TbkOrder tbkOrder) {

        try {
            int result = tbkOrderMapper.updateByPrimaryKeySelective(tbkOrder);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkOrder", "更新失败");
        }
    }

    private boolean deleteTbkOrderModel(Integer id) {

        try {
            int count = tbkOrderMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkOrderModel", "删除失败");
        }
    }

    private TbkOrder getTbkOrderById(Integer id) {

        try {
            return tbkOrderMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkOrder> list) {

        try {
            tbkOrderMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }

}
