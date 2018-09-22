package com.kevin.tkt.user.dal;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author: Kevin
 * @create: 2018-09-22 17:53
 **/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}