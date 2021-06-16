package com.example.hogwartsmybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hogwartsmybatis.common.ResultDto;
import com.example.hogwartsmybatis.dao.HogwartsTestUserMapper;
import com.example.hogwartsmybatis.dto.AddHogwartsTestUserDto;
import com.example.hogwartsmybatis.dto.UpdateHogwartsTestUser;
import com.example.hogwartsmybatis.entity.HogwartsTestUser;
import com.example.hogwartsmybatis.service.HogwartsTestUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
public class HogwartsTestUserController {

    @Autowired
    private HogwartsTestUserService hogwartsTestUserService;

    @Autowired
    HogwartsTestUserMapper hogwartsTestUserMapper;

    @ApiOperation("用户注册")
    @PostMapping("register")
    public ResultDto<HogwartsTestUser> register(@RequestBody AddHogwartsTestUserDto addHogwartsTestUserDto){

        HogwartsTestUser hogwartsTestUser = new HogwartsTestUser();
//        /**
//         * Dto 类赋值给实体类，一种方式是一个一个赋值
//         */
//        hogwartsTestUser.setUserName(addHogwartsTestUserDto.getUserName());
//        hogwartsTestUser.setPassword(addHogwartsTestUserDto.getPassword());
//        hogwartsTestUser.setEmail(addHogwartsTestUserDto.getEmail());

        /**
         * Dto 类赋值给实体类，另一种方式是通过Spring提供的工具类
         * 如果 Dto 类和实体类中字段名不一样时，可以通过上述赋值的方法完成赋值
         */
        BeanUtils.copyProperties(addHogwartsTestUserDto, hogwartsTestUser);
        log.info("用户注册 请求入参：" + JSONObject.toJSONString(hogwartsTestUser));

        if (addHogwartsTestUserDto.getPassword().isEmpty()){
            return ResultDto.fail("密码不能为空！");
        }
        if (addHogwartsTestUserDto.getUserName().isEmpty()){
            return ResultDto.fail("用户名不能为空！");
        }

        return hogwartsTestUserService.save(hogwartsTestUser);

    }


    @ApiOperation("用户登录")
    @GetMapping("login")
    public String login(){
        System.out.println("========== 用户登录 ==========");
        return JSONObject.toJSONString(hogwartsTestUserMapper.selectByPrimaryKey(1));
    }


    @ApiOperation("用户信息修改接口")
    @PutMapping()
    public ResultDto<HogwartsTestUser> updateUserInfo(@RequestBody UpdateHogwartsTestUser updateHogwartsTestUser){
        HogwartsTestUser hogwartsTestUser = new HogwartsTestUser();
        BeanUtils.copyProperties(updateHogwartsTestUser, hogwartsTestUser);
        log.info("用户注册 请求入参：" + JSONObject.toJSONString(hogwartsTestUser));

        if (updateHogwartsTestUser.getPassword().isEmpty()){
            return ResultDto.fail("密码不能为空！");
        }
        if (updateHogwartsTestUser.getUserName().isEmpty()){
            return ResultDto.fail("用户名不能为空！");
        }

        /**
         * 这种方式除了将给定字段进行修改外，没有将更新时间进行修改
         */
//        hogwartsTestUserMapper.updateUserDemo(hogwartsTestUser.getUserName(), hogwartsTestUser.getPassword(), hogwartsTestUser.getEmail(), hogwartsTestUser.getId());

        /**
         * 该方法会将传入的字段中，不为空的字段进行修改
         */
//        hogwartsTestUserMapper.updateByPrimaryKeySelective(hogwartsTestUser);

        hogwartsTestUser.setCreateTime(new Date());
        hogwartsTestUser.setUpdateTime(new Date());
        hogwartsTestUserMapper.updateByPrimaryKey(hogwartsTestUser);
        return ResultDto.success("succeed!", hogwartsTestUserMapper.selectByPrimaryKey(hogwartsTestUser.getId()));
    }
}
