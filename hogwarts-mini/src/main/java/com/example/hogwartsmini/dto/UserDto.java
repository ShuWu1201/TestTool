package com.example.hogwartsmini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * UserDto类，用于前端数据和服务端进行交互的类
 */
@ApiModel(value = "用户登陆类", description = "userDto请求参数类")
@Data
@Component
public class UserDto {

    @ApiModelProperty(value = "登陆名", example = "hhh", required = true)
    private String name;

    @ApiModelProperty(value = "登陆密码", example = "123456", required = true)
    private String pwd;

}
