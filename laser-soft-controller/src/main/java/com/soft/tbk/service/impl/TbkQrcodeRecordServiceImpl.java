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
import com.soft.tbk.dao.TbkQrcodeRecordMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkQrcodeRecord;
import com.soft.tbk.service.TbkQrcodeRecordService;

@Service
public class TbkQrcodeRecordServiceImpl extends BaseServiceImpl implements TbkQrcodeRecordService{

    private static final String SYS_CODE = "TbkQrcodeRecordServiceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkQrcodeRecordMapper tbkQrcodeRecordMapper;

    @Override
    public TbkQrcodeRecord saveTbkQrcodeRecord(TbkQrcodeRecord tbkQrcodeRecord) throws ApiException {
        //1.check
        check(tbkQrcodeRecord);
        //3.set default
        setDefault(tbkQrcodeRecord);
        //4.保存
        saveTbkQrcodeRecordModel(tbkQrcodeRecord);
        //5.domain设值
        return tbkQrcodeRecord;
    }


    private void setDefault(TbkQrcodeRecord tbkQrcodeRecord) {
        if (tbkQrcodeRecord == null) {
            return;
        }
        tbkQrcodeRecord.setCreateTime(new Date());
        tbkQrcodeRecord.setUpdateTime(new Date());
    }

    private void check(TbkQrcodeRecord tbkQrcodeRecord) {

        if (null == tbkQrcodeRecord) {
            throw new ApiException(SYS_CODE + ".saveTbkQrcodeRecord", "数据不能为空");
        }
        
    }

    @Override
    public boolean updateTbkQrcodeRecord(TbkQrcodeRecord tbkQrcodeRecord) throws ApiException {

        check(tbkQrcodeRecord);

        return updataTbkQrcodeRecord(tbkQrcodeRecord);

    }

    @Override
    public boolean deleteTbkQrcodeRecord(Integer id) {

        return deleteTbkQrcodeRecordModel(id);
    }

    @Override
    public TbkQrcodeRecord getTbkQrcodeRecord(Integer id) {

        return getTbkQrcodeRecordById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkQrcodeRecord> queryTbkQrcodeRecord(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkQrcodeRecord> list = queryTbkQrcodeRecordsModel(map);

        PageInfo<TbkQrcodeRecord> pageInfo = new PageInfo<TbkQrcodeRecord>(list);

        QueryResult<TbkQrcodeRecord> queryResult = new QueryResult<TbkQrcodeRecord>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkQrcodeRecord> queryTbkQrcodeRecordsModel(Map<String, Object> map) {

        List<TbkQrcodeRecord> list = null;
        try {
            list = tbkQrcodeRecordMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }


    private void saveTbkQrcodeRecordModel(TbkQrcodeRecord tbkQrcodeRecord) {

        try {
            tbkQrcodeRecordMapper.insertUseGeneratedKeys(tbkQrcodeRecord);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkQrcodeRecordModel", "保存失败");
        }
    }

    private boolean updataTbkQrcodeRecord(TbkQrcodeRecord tbkQrcodeRecord) {

        try {
            int result = tbkQrcodeRecordMapper.updateByPrimaryKeySelective(tbkQrcodeRecord);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkQrcodeRecord", "更新失败");
        }
    }

    private boolean deleteTbkQrcodeRecordModel(Integer id) {

        try {
            int count = tbkQrcodeRecordMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkQrcodeRecordModel", "删除失败");
        }
    }

    private TbkQrcodeRecord getTbkQrcodeRecordById(Integer id) {

        try {
            return tbkQrcodeRecordMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkQrcodeRecord> list) {

        try {
            tbkQrcodeRecordMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }

}
