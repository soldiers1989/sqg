package com.soft.tbk.dao;

import com.soft.tbk.base.BaseMapper;
import com.soft.tbk.model.TbkOrder;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbkOrderMapper extends BaseMapper<TbkOrder> {
    List<TbkOrder> query(Map<String, Object> parameters);

    int count(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    void insertBatch(List<TbkOrder> list);
    
    Map<String, Object> sumOrder(Map<String, Object> parameters);
}