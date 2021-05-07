package com.example.hogwartsmybatis.dao;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Tk.mybatis 使用 mapper 统一父类
 * @param <T>
 */
@Repository
interface MysqlExtensionMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper {

}
