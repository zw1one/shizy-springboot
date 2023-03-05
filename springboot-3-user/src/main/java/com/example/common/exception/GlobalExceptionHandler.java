package com.example.common.exception;

import com.example.common.json.JsonResult;
import com.example.common.json.JsonResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;

/**
 * 全局异常处理类
 * <p>
 * http://www.mydlq.club/article/49/
 */
@Configuration
@Slf4j
//@RestControllerAdvice("com.example.*.controller")//这个路径怎么配都不生效
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResult parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        log.error("", e);
        return new JsonResult(JsonResultEnum.PARAMETER_ERROR.getCode(), "请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResult parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        log.error("", e);
        return new JsonResult(JsonResultEnum.PARAMETER_ERROR.getCode(), "参数体不能为空");
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult parameterExceptionHandler(MethodArgumentNotValidException e) {
        log.error("", e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return new JsonResult(JsonResultEnum.PARAMETER_ERROR.getCode(), fieldError.getDefaultMessage());
            }
        }
        return new JsonResult(JsonResultEnum.PARAMETER_ERROR.getCode(), "参数验证异常");
    }

    /**
     * 自定义参数错误异常处理器
     *
     * @param e 自定义参数
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({MyException.class})
    public JsonResult myExceptionHandler(MyException e) {
        log.error("", e);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        String message = StringUtils.isEmpty(e.getMessage()) ? JsonResultEnum.FAIL.getMessage() : e.getMessage();
        Integer code = Objects.isNull(e.getCode()) ? JsonResultEnum.FAIL.getCode() : e.getCode();
        return new JsonResult(code, message);
    }

    /**
     * 用Exception来处理预期内的业务错误，不如直接返回jsonResult错误码效率高
     * 因为try catch语句与生成异常堆栈时会有一定损耗
     * 但通常该损害可以忽略不计，有大量异常抛出的情况再考虑优化
     * 如此，在service中的返回值，便可以不关注jsonResult，仅考虑自身业务逻辑
     */

}









