package com.example.hogwartsmybatis.service;

import com.example.hogwartsmybatis.common.ResultDto;
import com.example.hogwartsmybatis.entity.HogwartsTestUser;
import org.springframework.stereotype.Service;


@Service
public interface HogwartsTestUserService {

    public ResultDto<HogwartsTestUser> login(HogwartsTestUser hogwartsTestUser);

    /**
     * 保存数据
     * @param hogwartsTestUser
     * @return
     */
    public ResultDto<HogwartsTestUser> save(HogwartsTestUser hogwartsTestUser);

    /**
     * 更新
     * @param hogwartsTestUser
     * @return
     */
    public ResultDto<HogwartsTestUser> update(HogwartsTestUser hogwartsTestUser);
}
