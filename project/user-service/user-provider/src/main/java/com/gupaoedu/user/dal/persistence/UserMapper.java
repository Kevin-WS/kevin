package com.gupaoedu.user.dal.persistence;

import com.gupaoedu.user.dal.entity.User;

/**
 * Author: Wu Shuai
 * Date: 2018/8/25
 */
public interface UserMapper {
    /**
     * 根据用户名获取用户信息
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 根据uid获取用户信息
     * @param uid
     * @return
     */
    User getUserByUid(Integer uid);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int insertSelective(User user);
}
