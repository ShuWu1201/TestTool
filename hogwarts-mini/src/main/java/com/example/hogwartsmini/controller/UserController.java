package com.example.hogwartsmini.controller;

import com.example.hogwartsmini.common.ResultDto;
import com.example.hogwartsmini.common.ServiceException;
import com.example.hogwartsmini.dto.UserDto;
import com.example.hogwartsmini.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Api(tags = "UserController测试类")
@RestController
@Slf4j
//@RequestMapping("user") 在类上添加 @RequestMapping 相当于在请求中增加一个 @RequestMapping 中指定 user 的路径，且必须增加该路径
public class  UserController {

    @Autowired
    private UserService userService;

    // 注意使用${}进行引用
    @Value("${zidingyipropertyKey1}")
    private String zidingyipropertyKey1;

    @Value("${zidingyipropertyKey2}")
    private String zidingyipropertyKey2;

    @ApiOperation(value = "login登陆方法测试")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultDto<UserDto> login(@RequestBody UserDto userDto){

        String result = userService.login(userDto);

        if (userDto.getName().contains("error2")){
            throw new NullPointerException();
        }

        if(userDto.getName().contains("error")){
            ServiceException.throwEx("用户名中含有error！");
        }

//        try {
//            if (userDto.getName().contains("error2")){
//                throw new ServiceException("error2", new NullPointerException());
//            }
//
//            if(userDto.getName().contains("error")){
//                ServiceException.throwEx("用户名中含有error！");
//            }
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return "系统繁忙，请稍后重拾！";
//        }
        return ResultDto.success("login success!" + result, userDto);
    }


    //@RequestMapping(value = "login", method = RequestMethod.POST) 等效与 @PostMapping(value = "login")
    /**
     * @RequestBody 注解作用是将请求参数和实体类进行绑定
     */
    @ApiOperation(value = "loginRequestBodyTest方法测试")
    @PostMapping(value = "loginRequestBodyTest")
    public String loginRequestBodyTest(@RequestBody UserDto userDto){

        System.out.println("userDto:" + userDto.getName());
        System.out.println("userDto:" + userDto.getPwd());

        return "login success!";
    }

    /**
     * PathVariable：处理动态的 URL，URI 的值可以作为控制器中处理方法的参数
     * @param userId
     * @param id
     * @return
     */
    @ApiOperation(value = "getById方法测试")
    @RequestMapping(value = "byId/{userId}/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("userId") Long userId, @PathVariable("id") Long id){

        System.out.println("userId:" + userId);
        System.out.println("id:" + id);

        return "login success! userId = " + userId + ", id = " + id;
    }

    /**
     * @RequestParam 用于处理 get 请求的参数，需要注意的是请求路径不要写错了
     * 其中@RequestParam中required默认为true，表示请求的参数是必须要有的，为false时表示参数可有可无
     * @param userId
     * @param id
     * @return
     */
    @ApiOperation(value = "testRequestPara方法测试")
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String testRequestParam(@RequestParam("userId") Long userId, @RequestParam(value = "id", required = false) Long id){

        System.out.println("userId:" + userId);
        System.out.println("id:" + id);

        return "login success! userId = " + userId + ", id = " + id;
    }

}
