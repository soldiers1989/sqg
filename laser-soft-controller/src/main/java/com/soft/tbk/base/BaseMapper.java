package com.soft.tbk.base;

import java.util.Date;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {

    Date selectSysDate();
}
