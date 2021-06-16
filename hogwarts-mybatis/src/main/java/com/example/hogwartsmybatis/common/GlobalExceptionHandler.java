package com.example.hogwartsmybatis.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理类
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ServiceException.class})
    private ResultDto serviceExceptionHandler(ServiceException serviceException){
        log.error(serviceException.getMessage());
        return resultFormat(serviceException);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({Exception.class})
    public ResultDto exceptionHandler(Exception e){
        log.error(e.getMessage());
        return resultFormat(e);
    }

    @ExceptionHandler({Throwable.class})
    public ResultDto torowableHandler(Throwable throwable){
        log.error(throwable.getMessage());
        return ResultDto.fail("系统错误，请稍后重试！");
    }

    public ResultDto resultFormat(Throwable th){
        String tips = "系统繁忙，请稍后重试！";

        if (th instanceof ServiceException){
            return ResultDto.fail("业务异常" + tips);
        }

        if (th instanceof Exception){
            return ResultDto.fail("业务异常" + tips);
        }
        return ResultDto.fail(tips);
    }
}
