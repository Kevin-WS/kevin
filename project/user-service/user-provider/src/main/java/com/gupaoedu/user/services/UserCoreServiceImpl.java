package com.gupaoedu.user.services;

import com.google.common.collect.Maps;
import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.constants.Constants;
import com.gupaoedu.user.constants.ResponseCodeEnum;
import com.gupaoedu.user.dal.entity.User;
import com.gupaoedu.user.dal.persistence.UserMapper;
import com.gupaoedu.user.dto.*;
import com.gupaoedu.user.exception.ExceptionUtil;
import com.gupaoedu.user.exception.ServiceException;
import com.gupaoedu.user.exception.ValidateException;
import com.gupaoedu.user.utils.JwtTokenUtils;
import com.gupaoedu.user.utils.ResponseUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * Author: Wu Shuai
 * Date: 2018/8/25
 */
@Service("userCoreService")
public class UserCoreServiceImpl implements IUserCoreService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userMapper;

    @Override
    public UserLoginRespose login(UserLoginRequest request) {
        UserLoginRespose response = new UserLoginRespose();
        log.info("login request: " + request);
        try {
            beforeValidate(request);
            User user = userMapper.getUserByUserName(request.getUserName());
            if (user == null || !user.getPassword().equals(request.getPassword())) {
                ResponseUtils.setResponseByCodeEnum(response, ResponseCodeEnum.USERORPASSWORD_ERRROR);
                return response;
            }
            // 生成token
            Map<String, Object> map = Maps.newHashMap();
            map.put("uid", user.getId());
            map.put("exp", DateTime.now().plusSeconds(40).toDate().getTime() / 1000);

            response.setToken(JwtTokenUtils.generatorToken(map));
            response.setUid(user.getId());
            response.setAvatar(user.getAvatar());
            response.setMobile(user.getMobile());
            ResponseUtils.setResponseByCodeEnum(response, ResponseCodeEnum.SUCCESS);
        } catch (Exception e) {
            ServiceException serviceException = (ServiceException) ExceptionUtil.handlerException4biz(e);
            ResponseUtils.setResponseByException(response, serviceException);
        } finally {
            log.info("login response: " + response);
        }

        return response;
    }


    @Override
    public CheckAuthResponse validToken(CheckAuthRequest request) {
        CheckAuthResponse response = new CheckAuthResponse();
        try {
            beforeValidateAuth(request);

            Claims claims = JwtTokenUtils.parseToken(request.getToken());
            response.setUid(claims.get("uid").toString());
            ResponseUtils.setResponseByCodeEnum(response, ResponseCodeEnum.SUCCESS);

        } catch (ExpiredJwtException e) {
            log.error("Expire :" + e);
            ResponseUtils.setResponseByCodeEnum(response, ResponseCodeEnum.TOKEN_EXPIRE);
        } catch (SignatureException e1) {
            log.error("SignatureException :" + e1);
            ResponseUtils.setResponseByCodeEnum(response, ResponseCodeEnum.SIGNATURE_ERROR);
        } catch (Exception e) {
            log.error("login occur exception :" + e);
            ServiceException serviceException = (ServiceException) ExceptionUtil.handlerException4biz(e);
            response.setCode(serviceException.getErrorCode());
            response.setMsg(serviceException.getErrorMessage());
        } finally {
            log.info("response:" + response);
        }

        return response;
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        log.info("begin UserCoreService.register,request:【" + userRegisterRequest + "】");

        UserRegisterResponse response = new UserRegisterResponse();
        try {
            beforeRegisterValidate(userRegisterRequest);

            User user = new User();
            user.setUsername(userRegisterRequest.getUsername());
            user.setPassword(userRegisterRequest.getPassword());
            user.setMobile(userRegisterRequest.getMobile());
            user.setSex(userRegisterRequest.getSex());
            user.setStatus(Constants.NORMAL_USER_STATUS);
            user.setCreateTime(new Date());

            int effectRow = userMapper.insertSelective(user);
            if (effectRow > 0) {
                ResponseUtils.setResponseByCodeEnum(response, ResponseCodeEnum.SUCCESS);
                return response;
            }
            ResponseUtils.setResponseByCodeEnum(response, ResponseCodeEnum.SYSTEM_BUSY);
            return response;
        } catch (DuplicateKeyException e) {
            //TODO 用户名重复
        } catch (Exception e) {
            ServiceException serviceException = (ServiceException) ExceptionUtil.handlerException4biz(e);
            ResponseUtils.setResponseByException(response, serviceException);
        } finally {
            log.info("register response:【" + response + "】");
        }

        return response;
    }

    private void beforeRegisterValidate(UserRegisterRequest request) {
        if (null == request) {
            throw new ValidateException("请求对象为空");
        }
        if (StringUtils.isEmpty(request.getUsername())) {
            throw new ValidateException("用户名为空");
        }
        if (StringUtils.isEmpty(request.getPassword())) {
            throw new ValidateException("密码为空");
        }
        if (StringUtils.isEmpty(request.getMobile())) {
            throw new ValidateException("手机号为空");
        }
    }

    private void beforeValidateAuth(CheckAuthRequest request) {
        if (request == null) {
            throw new ValidateException("请求对象为空");
        }
        if (StringUtils.isEmpty(request.getToken())) {
            throw new ValidateException("token信息为空");
        }
    }

    private void beforeValidate(UserLoginRequest request) {
        if (request == null) {
            throw new ValidateException("请求对象为空");
        }
        if (StringUtils.isEmpty(request.getUserName())) {
            throw new ValidateException("用户名为空");
        }
        if (StringUtils.isEmpty(request.getPassword())) {
            throw new ValidateException("密码为空");
        }
    }
}
