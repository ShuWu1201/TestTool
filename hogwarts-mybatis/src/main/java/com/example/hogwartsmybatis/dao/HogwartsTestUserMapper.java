package com.example.hogwartsmybatis.dao;

import com.example.hogwartsmybatis.entity.HogwartsTestUser;
import org.springframework.stereotype.Repository;


@Repository
public interface HogwartsTestUserMapper extends MysqlExtensionMapper<HogwartsTestUser> {

    //public HogwartsTestUser selectByPrimaryKey(Integer id);

}