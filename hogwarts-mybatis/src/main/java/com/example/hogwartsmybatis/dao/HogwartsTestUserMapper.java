package com.example.hogwartsmybatis.dao;

import com.example.hogwartsmybatis.entity.HogwartsTestUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HogwartsTestUserMapper extends MysqlExtensionMapper<HogwartsTestUser> {

    int updateUserDemo(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("id") Integer id);

}