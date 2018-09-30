package com.soft.tbk.dao;

import com.soft.tbk.base.BaseMapper;
import com.soft.tbk.model.TbkAccountList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbkAccountListMapper extends BaseMapper<TbkAccountList> {
    List<TbkAccountList> query(Map<String, Object> parameters);

    int count(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    void insertBatch(List<TbkAccountList> list);
}