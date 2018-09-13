package com.soft.tbk.dao;

import com.soft.tbk.base.BaseMapper;
import com.soft.tbk.model.MmMbuser;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MmMbuserMapper extends BaseMapper<MmMbuser> {
    List<MmMbuser> query(Map<String, Object> parameters);

    int count(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    MmMbuser getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<MmMbuser> list);
}