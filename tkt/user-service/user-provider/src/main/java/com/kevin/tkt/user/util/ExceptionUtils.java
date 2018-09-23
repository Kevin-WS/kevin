
package com.kevin.tkt.user.util;


import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.kevin.tkt.user.constant.ResponseCodeEnum;
import com.kevin.tkt.user.exception.ServiceException;
import com.kevin.tkt.user.exception.ValidateException;

/**
 * 异常工具类
 * @author: Wu Shuai
 * @create: 2018-09-16 20:27
 **/
public class ExceptionUtils {

    private static Logger logger = LoggerFactory.getLogger(ExceptionUtils.class);

    /**
     * 将下层抛出的异常转换为resp返回码
     *
     * @param e Exception
     * @return
     */
    public static Exception handlerException4biz(Exception e) {
        Exception ex = null;
        if (!(e instanceof Exception)) {
            return null;
        }
        if (e instanceof ValidateException) {
            ex = new ServiceException(((ValidateException) e).getErrorCode(), ((ValidateException) e).getErrorMessage());
        } else if (e instanceof Exception) {
            ex = new ServiceException(ResponseCodeEnum.SYSTEM_BUSY.getCode(),
                    ResponseCodeEnum.SYSTEM_BUSY.getMsg());
        }
        logger.error("ExceptionUtils.handlerException4biz,Exception=" + e.getMessage(), e);
        return ex;
    }
}
