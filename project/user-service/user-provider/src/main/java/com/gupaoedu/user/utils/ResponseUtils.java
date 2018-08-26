package com.gupaoedu.user.utils;

import com.gupaoedu.user.abs.AbstractResponse;
import com.gupaoedu.user.constants.ResponseCodeEnum;
import com.gupaoedu.user.exception.ServiceException;

/**
 * Author: Wu Shuai
 * Date: 2018/8/26
 */
public class ResponseUtils {

    /**
     * 根据枚举值设置响应信息
     * @param response
     * @param responseCodeEnum
     */
    public static void setResponseByCodeEnum(AbstractResponse response, ResponseCodeEnum responseCodeEnum) {
        response.setCode(responseCodeEnum.getCode());
        response.setMsg(responseCodeEnum.getMsg());
    }

    /**
     * 根据异常设置响应信息
     * @param response
     * @param exception
     */
    public static void setResponseByException(AbstractResponse response, ServiceException exception) {
        response.setCode(exception.getErrorCode());
        response.setMsg(exception.getErrorMessage());
    }
}
