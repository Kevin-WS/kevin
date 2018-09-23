package com.kevin.tkt.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kevin.tkt.user.dal.domain.User;
import com.kevin.tkt.user.dal.mapper.UserMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: Kevin
 * @create: 2018-09-22 18:53
 **/
public class UserTest extends AbstractTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectAllPage1() {
        PageHelper.startPage(2, 5);
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void selectAllPage2() {
        Page<User> page = PageHelper.startPage(2, 5).doSelectPage(()-> userMapper.selectAll());
        System.out.println(page);
    }

    @Test
    @Ignore
    //@Repeat(value = 10)
    public void insert() {
        User user = new User();
        user.setName("Kevin");
        user.setPassword("test");
        userMapper.insert(user);
    }
}
