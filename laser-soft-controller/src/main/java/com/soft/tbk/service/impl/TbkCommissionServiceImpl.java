package com.soft.tbk.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.soft.tbk.base.BaseServiceImpl;
import com.soft.tbk.dao.TbkCommissionMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkCommission;
import com.soft.tbk.service.TbkCommissionService;
import com.soft.tbk.utils.DateUtil;

@Service
public class TbkCommissionServiceImpl extends BaseServiceImpl implements TbkCommissionService{

    private static final String SYS_CODE = "TbkCommissionSerciceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkCommissionMapper tbkCommissionMapper;

    @Override
    public TbkCommission saveTbkCommission(TbkCommission tbkCommission) throws ApiException {
        //1.check
        check(tbkCommission);
        //3.set default
        setDefault(tbkCommission);
        //4.保存
        saveTbkCommissionModel(tbkCommission);
        //5.domain设值
        return tbkCommission;
    }


    private void setDefault(TbkCommission tbkCommission) {
        if (tbkCommission == null) {
            return;
        }
        tbkCommission.setCreateTime(new Date());
        tbkCommission.setUpdateTime(new Date());
    }

    private void check(TbkCommission tbkCommission) {

        if (null == tbkCommission) {
            throw new ApiException(SYS_CODE + ".saveTbkCommission", "数据不能为空");
        }
        
    }

    @Override
    public boolean updateTbkCommission(TbkCommission tbkCommission) throws ApiException {

        check(tbkCommission);

        return updataTbkCommission(tbkCommission);

    }

    @Override
    public boolean deleteTbkCommission(Integer id) {

        return deleteTbkCommissionModel(id);
    }

    @Override
    public TbkCommission getTbkCommission(Integer id) {

        return getTbkCommissionById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkCommission> queryTbkCommission(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkCommission> list = queryTbkCommissionsModel(map);

        PageInfo<TbkCommission> pageInfo = new PageInfo<TbkCommission>(list);

        QueryResult<TbkCommission> queryResult = new QueryResult<TbkCommission>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkCommission> queryTbkCommissionsModel(Map<String, Object> map) {

        List<TbkCommission> list = null;
        try {
            list = tbkCommissionMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }


    private void saveTbkCommissionModel(TbkCommission tbkCommission) {

        try {
            tbkCommissionMapper.insertUseGeneratedKeys(tbkCommission);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkCommissionModel", "保存失败");
        }
    }

    private boolean updataTbkCommission(TbkCommission tbkCommission) {

        try {
            int result = tbkCommissionMapper.updateByPrimaryKeySelective(tbkCommission);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkCommission", "更新失败");
        }
    }

    private boolean deleteTbkCommissionModel(Integer id) {

        try {
            int count = tbkCommissionMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkCommissionModel", "删除失败");
        }
    }

    private TbkCommission getTbkCommissionById(Integer id) {

        try {
            return tbkCommissionMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkCommission> list) {

        try {
            tbkCommissionMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }


    @Override
    public boolean updateCommissionStatus(Integer orderId, Integer commissionStatus, Date settleDate) throws ApiException {
        try {
            int rows = tbkCommissionMapper.updateStatusByOrderId(getQueryParamMap("orderId,commissionStatus,settleDate", orderId, commissionStatus, settleDate));
            if (rows > 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }


    @Override
    public List<Map<String, Object>> sumCommission(Integer userId, Date sumDate) {
        
        try {
            return tbkCommissionMapper.sumCommission(getQueryParamMap("userId,sumDate", userId, DateUtil.getDateString(sumDate, "yyyyMM")));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        return new ArrayList<Map<String,Object>>();
    }


    @Override
    public List<Map<String, Object>> sellteCommission(Date sellteDate) {
        try {
            return tbkCommissionMapper.sellteCommission(getQueryParamMap("sumDate", DateUtil.getDateString(sellteDate, "yyyyMM")));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        return new ArrayList<Map<String,Object>>();

    }


    @Override
    public boolean updateCommissionStatusByUser(Integer userId, Integer commissionStatus, Date settleDate) throws ApiException {
        try {
            int rows = tbkCommissionMapper.updateStatusByUserId(getQueryParamMap("userId,commissionStatus,settleDate", userId, commissionStatus, DateUtil.getDateString(settleDate, "yyyyMM")));
            if (rows > 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

}
