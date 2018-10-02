package com.soft.tbk.dao;

import com.soft.tbk.base.BaseMapper;
import com.soft.tbk.model.TbkCommission;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbkCommissionMapper extends BaseMapper<TbkCommission> {
    List<TbkCommission> query(Map<String, Object> parameters);

    int count(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    int updateStatusByOrderId(Map<String, Object> map);
    
    int updateStatusByUserId(Map<String, Object> map);

    void insertBatch(List<TbkCommission> list);
    
    List<Map<String, Object>> sumCommission(Map<String, Object> map);

    List<Map<String, Object>> sellteCommission(Map<String, Object> map);

}

