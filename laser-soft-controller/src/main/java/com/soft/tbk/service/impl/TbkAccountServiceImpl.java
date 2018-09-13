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
import com.soft.tbk.dao.TbkAccountMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkAccount;
import com.soft.tbk.service.TbkAccountService;

@Service
public class TbkAccountServiceImpl extends BaseServiceImpl implements TbkAccountService{

    private static final String SYS_CODE = "TbkAccountSerciceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkAccountMapper tbkAccountMapper;

    @Override
    public TbkAccount saveTbkAccount(TbkAccount tbkAccount) throws ApiException {
        //1.check
        check(tbkAccount);
        //3.set default
        setDefault(tbkAccount);
        //4.保存
        saveTbkAccountModel(tbkAccount);
        //5.domain设值
        return tbkAccount;
    }


    private void setDefault(TbkAccount tbkAccount) {
        if (tbkAccount == null) {
            return;
        }
        tbkAccount.setCreateTime(new Date());
        tbkAccount.setUpdateTime(new Date());
    }

    private void check(TbkAccount tbkAccount) {

        if (null == tbkAccount) {
            throw new ApiException(SYS_CODE + ".saveTbkAccount", "数据不能为空");
        }
        
    }

    @Override
    public boolean updateTbkAccount(TbkAccount tbkAccount) throws ApiException {

        check(tbkAccount);

        return updataTbkAccount(tbkAccount);

    }

    @Override
    public boolean deleteTbkAccount(Integer id) {

        return deleteTbkAccountModel(id);
    }

    @Override
    public TbkAccount getTbkAccount(Integer id) {

        return getTbkAccountById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkAccount> queryTbkAccount(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkAccount> list = queryTbkAccountsModel(map);

        PageInfo<TbkAccount> pageInfo = new PageInfo<TbkAccount>(list);

        QueryResult<TbkAccount> queryResult = new QueryResult<TbkAccount>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkAccount> queryTbkAccountsModel(Map<String, Object> map) {

        List<TbkAccount> list = null;
        try {
            list = tbkAccountMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }


    private void saveTbkAccountModel(TbkAccount tbkAccount) {

        try {
            tbkAccountMapper.insertUseGeneratedKeys(tbkAccount);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkAccountModel", "保存失败");
        }
    }

    private boolean updataTbkAccount(TbkAccount tbkAccount) {

        try {
            int result = tbkAccountMapper.updateByPrimaryKeySelective(tbkAccount);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkAccount", "更新失败");
        }
    }

    private boolean deleteTbkAccountModel(Integer id) {

        try {
            int count = tbkAccountMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkAccountModel", "删除失败");
        }
    }

    private TbkAccount getTbkAccountById(Integer id) {

        try {
            return tbkAccountMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkAccount> list) {

        try {
            tbkAccountMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }

}
