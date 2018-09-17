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
import com.soft.tbk.dao.TbkPidItemMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkPidItem;
import com.soft.tbk.service.TbkPidItemService;

@Service
public class TbkPidItemServiceImpl extends BaseServiceImpl implements TbkPidItemService{

    private static final String SYS_CODE = "TbkPidItemSerciceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkPidItemMapper tbkPidItemMapper;

    @Override
    public TbkPidItem saveTbkPidItem(TbkPidItem tbkPidItem) throws ApiException {
        //1.check
        check(tbkPidItem);
        //3.set default
        setDefault(tbkPidItem);
        //4.保存
        saveTbkPidItemModel(tbkPidItem);
        //5.domain设值
        return tbkPidItem;
    }


    private void setDefault(TbkPidItem tbkPidItem) {
        if (tbkPidItem == null) {
            return;
        }
        tbkPidItem.setCreateTime(new Date());
        tbkPidItem.setUpdateTime(new Date());
    }

    private void check(TbkPidItem tbkPidItem) {

        if (null == tbkPidItem) {
            throw new ApiException(SYS_CODE + ".saveTbkPidItem", "数据不能为空");
        }
        
    }

    @Override
    public boolean updateTbkPidItem(TbkPidItem tbkPidItem) throws ApiException {

        check(tbkPidItem);

        tbkPidItem.setUpdateTime(tbkPidItemMapper.selectSysDate());
        
        return updataTbkPidItem(tbkPidItem);

    }

    @Override
    public boolean deleteTbkPidItem(Integer id) {

        return deleteTbkPidItemModel(id);
    }

    @Override
    public TbkPidItem getTbkPidItem(Integer id) {

        return getTbkPidItemById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkPidItem> queryTbkPidItem(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkPidItem> list = queryTbkPidItemsModel(map);

        PageInfo<TbkPidItem> pageInfo = new PageInfo<TbkPidItem>(list);

        QueryResult<TbkPidItem> queryResult = new QueryResult<TbkPidItem>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkPidItem> queryTbkPidItemsModel(Map<String, Object> map) {

        List<TbkPidItem> list = null;
        try {
            list = tbkPidItemMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }


    private void saveTbkPidItemModel(TbkPidItem tbkPidItem) {

        try {
            tbkPidItemMapper.insertUseGeneratedKeys(tbkPidItem);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkPidItemModel", "保存失败");
        }
    }

    private boolean updataTbkPidItem(TbkPidItem tbkPidItem) {

        try {
            int result = tbkPidItemMapper.updateByPrimaryKeySelective(tbkPidItem);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkPidItem", "更新失败");
        }
    }

    private boolean deleteTbkPidItemModel(Integer id) {

        try {
            int count = tbkPidItemMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkPidItemModel", "删除失败");
        }
    }

    private TbkPidItem getTbkPidItemById(Integer id) {

        try {
            return tbkPidItemMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkPidItem> list) {

        try {
            tbkPidItemMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }

}
