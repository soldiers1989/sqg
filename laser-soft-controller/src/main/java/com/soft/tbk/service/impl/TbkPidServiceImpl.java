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
import com.soft.tbk.dao.TbkPidMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkPid;
import com.soft.tbk.service.TbkPidService;

@Service
public class TbkPidServiceImpl extends BaseServiceImpl implements TbkPidService{

    private static final String SYS_CODE = "TbkPidSerciceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkPidMapper tbkPidMapper;

    @Override
    public TbkPid saveTbkPid(TbkPid tbkPid) throws ApiException {
        //1.check
        check(tbkPid);
        //3.set default
        setDefault(tbkPid);
        //4.保存
        saveTbkPidModel(tbkPid);
        //5.domain设值
        return tbkPid;
    }


    private void setDefault(TbkPid tbkPid) {
        if (tbkPid == null) {
            return;
        }
        tbkPid.setCreateTime(new Date());
        tbkPid.setUpdateTime(new Date());
    }

    private void check(TbkPid tbkPid) {

        if (null == tbkPid) {
            throw new ApiException(SYS_CODE + ".saveTbkPid", "数据不能为空");
        }
        
    }

    @Override
    public boolean updateTbkPid(TbkPid tbkPid) throws ApiException {

        check(tbkPid);

        return updataTbkPid(tbkPid);

    }

    @Override
    public boolean deleteTbkPid(Integer id) {

        return deleteTbkPidModel(id);
    }

    @Override
    public TbkPid getTbkPid(Integer id) {

        return getTbkPidById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkPid> queryTbkPid(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkPid> list = queryTbkPidsModel(map);

        PageInfo<TbkPid> pageInfo = new PageInfo<TbkPid>(list);

        QueryResult<TbkPid> queryResult = new QueryResult<TbkPid>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkPid> queryTbkPidsModel(Map<String, Object> map) {

        List<TbkPid> list = null;
        try {
            list = tbkPidMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }


    private void saveTbkPidModel(TbkPid tbkPid) {

        try {
            tbkPidMapper.insertUseGeneratedKeys(tbkPid);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkPidModel", "保存失败");
        }
    }

    private boolean updataTbkPid(TbkPid tbkPid) {

        try {
            int result = tbkPidMapper.updateByPrimaryKeySelective(tbkPid);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkPid", "更新失败");
        }
    }

    private boolean deleteTbkPidModel(Integer id) {

        try {
            int count = tbkPidMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkPidModel", "删除失败");
        }
    }

    private TbkPid getTbkPidById(Integer id) {

        try {
            return tbkPidMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkPid> list) {

        try {
            tbkPidMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }


    @Override
    public TbkPid getTbkPidByIndex(Integer index) {
        return tbkPidMapper.getPidByIndex(getQueryParamMap("orderIndex", index));
    }

}
