package com.example.hogwartsmybatis.service.Impl;

import com.example.hogwartsmybatis.common.ResultDto;
import com.example.hogwartsmybatis.dao.HogwartsTestUserMapper;
import com.example.hogwartsmybatis.entity.HogwartsTestUser;
import com.example.hogwartsmybatis.service.HogwartsTestUserService;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
public class HogwartsTestUserServiceImpl implements HogwartsTestUserService, InitializingBean {

    @Autowired
    private HogwartsTestUserMapper hogwartsTestUserMapper;


    @Override
    public ResultDto<HogwartsTestUser> login(HogwartsTestUser hogwartsTestUser) {
        return null;
    }

    @Override
    public ResultDto<HogwartsTestUser> save(HogwartsTestUser hogwartsTestUser) {

        hogwartsTestUser.setCreateTime(new Date());
        hogwartsTestUser.setUpdateTime(new Date());
        hogwartsTestUserMapper.insertUseGeneratedKeys(hogwartsTestUser);

        return ResultDto.success("插入成功！", hogwartsTestUser);
    }

    @Override
    public ResultDto<HogwartsTestUser> update(HogwartsTestUser hogwartsTestUser) {
        hogwartsTestUserMapper.updateUserDemo(hogwartsTestUser.getUserName(), hogwartsTestUser.getPassword(), hogwartsTestUser.getEmail(), hogwartsTestUser.getId());
        return ResultDto.success("成功", hogwartsTestUser);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }
}
