package com.soft.tbk.dao;

import com.soft.tbk.base.BaseMapper;
import com.soft.tbk.model.TbkPid;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbkPidMapper extends BaseMapper<TbkPid> {
    List<TbkPid> query(Map<String, Object> parameters);

    TbkPid getPidByIndex(Map<String, Object> parameters);

    int count(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    void insertBatch(List<TbkPid> list);
}