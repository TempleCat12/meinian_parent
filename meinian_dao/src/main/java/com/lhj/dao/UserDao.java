package com.lhj.dao;

import com.lhj.pojo.User;

/**
 * @author lhj
 * @creat 2022-04-25-17:38
 */
public interface UserDao {

    User findUserByName(String name);
}
