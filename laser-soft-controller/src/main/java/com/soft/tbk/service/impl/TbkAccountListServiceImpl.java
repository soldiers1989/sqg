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
import com.soft.tbk.dao.TbkAccountListMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkAccountList;
import com.soft.tbk.service.TbkAccountListService;

@Service
public class TbkAccountListServiceImpl extends BaseServiceImpl implements TbkAccountListService{

    private static final String SYS_CODE = "TbkAccountListSerciceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkAccountListMapper tbkAccountListMapper;

    @Override
    public TbkAccountList saveTbkAccountList(TbkAccountList tbkAccountList) throws ApiException {
        //1.check
        check(tbkAccountList);
        //3.set default
        setDefault(tbkAccountList);
        //4.保存
        saveTbkAccountListModel(tbkAccountList);
        //5.domain设值
        return tbkAccountList;
    }


    private void setDefault(TbkAccountList tbkAccountList) {
        if (tbkAccountList == null) {
            return;
        }
        tbkAccountList.setCreateTime(new Date());
        tbkAccountList.setUpdateTime(new Date());
    }

    private void check(TbkAccountList tbkAccountList) {

        if (null == tbkAccountList) {
            throw new ApiException(SYS_CODE + ".saveTbkAccountList", "数据不能为空");
        }
        
    }

    @Override
    public boolean updateTbkAccountList(TbkAccountList tbkAccountList) throws ApiException {

        check(tbkAccountList);

        return updataTbkAccountList(tbkAccountList);

    }

    @Override
    public boolean deleteTbkAccountList(Integer id) {

        return deleteTbkAccountListModel(id);
    }

    @Override
    public TbkAccountList getTbkAccountList(Integer id) {

        return getTbkAccountListById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkAccountList> queryTbkAccountList(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkAccountList> list = queryTbkAccountListsModel(map);

        PageInfo<TbkAccountList> pageInfo = new PageInfo<TbkAccountList>(list);

        QueryResult<TbkAccountList> queryResult = new QueryResult<TbkAccountList>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkAccountList> queryTbkAccountListsModel(Map<String, Object> map) {

        List<TbkAccountList> list = null;
        try {
            list = tbkAccountListMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }


    private void saveTbkAccountListModel(TbkAccountList tbkAccountList) {

        try {
            tbkAccountListMapper.insertUseGeneratedKeys(tbkAccountList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkAccountListModel", "保存失败");
        }
    }

    private boolean updataTbkAccountList(TbkAccountList tbkAccountList) {

        try {
            int result = tbkAccountListMapper.updateByPrimaryKeySelective(tbkAccountList);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkAccountList", "更新失败");
        }
    }

    private boolean deleteTbkAccountListModel(Integer id) {

        try {
            int count = tbkAccountListMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkAccountListModel", "删除失败");
        }
    }

    private TbkAccountList getTbkAccountListById(Integer id) {

        try {
            return tbkAccountListMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkAccountList> list) {

        try {
            tbkAccountListMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }

}
