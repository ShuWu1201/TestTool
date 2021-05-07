package com.example.hogwartsmybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hogwartsmybatis.common.ResultDto;
import com.example.hogwartsmybatis.dao.HogwartsTestUserMapper;
import com.example.hogwartsmybatis.dto.AddHogwartsTestUser;
import com.example.hogwartsmybatis.entity.HogwartsTestUser;
import com.example.hogwartsmybatis.service.HogwartsTestUserService;
import com.example.hogwartsmybatis.service.Impl.HogwartsTestUserServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

@RestController
@Slf4j
public class HogwartsTestUserController {

    @Autowired
    private HogwartsTestUserServiceImpl hogwartsTestUserServiceImpl;

    @Qualifier
    HogwartsTestUserMapper hogwartsTestUserMapper;

    @PostMapping("register")
    public ResultDto<HogwartsTestUser> register(@RequestBody AddHogwartsTestUser addHogwartsTestUser){

        HogwartsTestUser hogwartsTestUser = new HogwartsTestUser();
        /**
         * dto 类可以通过属性赋值的方式将属性信息传递给实体类，但比较繁琐，不推荐，推荐使用Spring中的工具类
         */
//        hogwartsTestUser.setUserName(addHogwartsTestUser.getUserName());
//        hogwartsTestUser.setPassword(addHogwartsTestUser.getPassword());

//        if (StringUtil.isEmpty(addHogwartsTestUser.getPassword())){
//            return ResultDto.fail("密码不能为空！");
//        }
//
//        /**
//         * 使用Spring提供的工具时dto和entity类中属性名需要一样，如果不一样则使用属性赋值，即set方法
//         */
//        BeanUtils.copyProperties(addHogwartsTestUser, hogwartsTestUser);
//
//        log.info(JSONObject.toJSONString(hogwartsTestUser));
//
//        hogwartsTestUserServiceImpl.save(hogwartsTestUser);
//
//        return ResultDto.success("成功", hogwartsTestUser);

        return null;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return hogwartsTestUserMapper.selectByPrimaryKey(1).toString();
    }
}
