package com.soft.tbk.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.soft.tbk.base.BaseServiceImpl;
import com.soft.tbk.constants.TbkConstants;
import com.soft.tbk.dao.TbkUserMapper;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.exception.ApiException;
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.service.TbkUserService;
import com.soft.tbk.utils.BeanUtils;

@Service
public class TbkUserServiceImpl extends BaseServiceImpl implements TbkUserService {

    private static final String SYS_CODE = "TbkUserSerciceImpl";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkUserMapper tbkUserMapper;

    @Override
    public TbkUser saveTbkUserWithOpenId(TbkUser tbkUser) throws ApiException {

        if (tbkUser == null || StringUtils.isBlank(tbkUser.getUserOpenid())) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userOpenid", tbkUser.getUserOpenid());
        List<TbkUser> list = queryTbkUsersModel(map);
        if (list != null && !list.isEmpty()) {
            TbkUser oldtbkUser = list.get(0);
            tbkUser.setParentId(oldtbkUser.getParentId());
            tbkUser.setParentIdPath(oldtbkUser.getParentIdPath());
            try {
                BeanUtils.copyAllPropertysNotNull(oldtbkUser, tbkUser);
            } catch (Exception e) {}
            updateTbkUser(oldtbkUser);
            return oldtbkUser;
        } else {
            Integer parentId = tbkUser.getParentId();
            if (parentId != null) {
                TbkUser parentUser = getTbkUser(parentId);
                String parentIdPath = parentId + "";
                if (parentUser != null && parentUser.getParentId() != null) {
                    // 2级
                    parentIdPath = parentUser.getParentId() + "," + parentId;
                }
                tbkUser.setParentIdPath(parentIdPath);
            }
        }
        return saveTbkUser(tbkUser);
    }

    @Override
    public TbkUser saveTbkUser(TbkUser tbkUser) throws ApiException {

        //1.check
        check(tbkUser);
        //3.set default
        setDefault(tbkUser);
        //4.保存
        saveTbkUserModel(tbkUser);
        //5.domain设值
        return tbkUser;
    }

    private void setDefault(TbkUser tbkUser) {

        if (tbkUser == null) {
            return;
        }
        tbkUser.setCreateTime(new Date());
        tbkUser.setUpdateTime(new Date());
        tbkUser.setUserLevel(TbkConstants.USER_LEVEL_0);
    }

    private void check(TbkUser tbkUser) {

        if (null == tbkUser) {
            throw new ApiException(SYS_CODE + ".saveTbkUser", "数据不能为空");
        }

    }

    @Override
    public boolean updateTbkUser(TbkUser tbkUser) throws ApiException {

        check(tbkUser);

        return updataTbkUser(tbkUser);

    }

    @Override
    public boolean deleteTbkUser(Integer id) {

        return deleteTbkUserModel(id);
    }

    @Override
    public TbkUser getTbkUser(Integer id) {

        return getTbkUserById(id);
    }

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    @Override
    public QueryResult<TbkUser> queryTbkUser(Map<String, Object> map) {

        //分页查询
        setPage(map);

        List<TbkUser> list = queryTbkUsersModel(map);

        PageInfo<TbkUser> pageInfo = new PageInfo<TbkUser>(list);

        QueryResult<TbkUser> queryResult = new QueryResult<TbkUser>(list, pageInfo.getTotal());

        return queryResult;
    }

    private List<TbkUser> queryTbkUsersModel(Map<String, Object> map) {

        List<TbkUser> list = null;
        try {
            list = tbkUserMapper.query(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return list;

    }

    private void saveTbkUserModel(TbkUser tbkUser) {

        try {
            tbkUserMapper.insertUseGeneratedKeys(tbkUser);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".saveTbkUserModel", "保存失败");
        }
    }

    private boolean updataTbkUser(TbkUser tbkUser) {

        try {
            int result = tbkUserMapper.updateByPrimaryKeySelective(tbkUser);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".updataTbkUser", "更新失败");
        }
    }

    private boolean deleteTbkUserModel(Integer id) {

        try {
            int count = tbkUserMapper.deleteByPrimaryKey(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".deleteTbkUserModel", "删除失败");
        }
    }

    private TbkUser getTbkUserById(Integer id) {

        try {
            return tbkUserMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void insertBatch(List<TbkUser> list) {

        try {
            tbkUserMapper.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(SYS_CODE + ".insertBatch", "插入失败");
        }
    }

    @Override
    public TbkUser getTbkUserByOpenid(String openId) {

        Map<String, Object> map = new HashMap<>();
        map.put("userOpenid", openId);
        List<TbkUser> list = queryTbkUsersModel(map);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
