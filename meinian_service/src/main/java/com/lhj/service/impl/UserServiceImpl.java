package com.lhj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.dao.UserDao;
import com.lhj.pojo.User;
import com.lhj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lhj
 * @creat 2022-04-25-17:36
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }
}
