package com.example.hogwartsmybatis.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * dto和实体类不同，dto只保留业务相关的数据即可。如系统相关的创建时间，修改时间就不需要了，主键也无需前端传到后端
 */
@ApiModel(value = "用户登录类", description = "请求类")
@Data
public class AddHogwartsTestUser {


        /**
         * 用户名
         */
        @ApiModelProperty(value = "用户名", example = "hogwarts", required = true)
        private String userName;

        /**
         * 密码
         */
        @ApiModelProperty(value = "密码", example = "hogwarts", required = true)
        private String password;

        /**
         * 邮箱
         */
        @ApiModelProperty(value = "邮箱", example = "8888@qq.com", required = true)
        private String email;

        /**
         * 自动生成用例job名称 不为空时表示已经创建job
         */
        @ApiModelProperty(value = "自动生成用例job名称 不为空时表示已经创建job", example = "hogwarts")
        private String autoCreateCaseJobName;

        /**
         * 执行测试job名称 不为空时表示已经创建job
         */
        @ApiModelProperty(value = "执行测试job名称 不为空时表示已经创建job", example = "hogwarts")
        private String startTestJobName;

        /**
         * 默认Jenkins服务器
         */
        @ApiModelProperty(value = "默认Jenkins服务器", example = "hogwarts服务器")
        private Integer defaultJenkinsId;

    }
