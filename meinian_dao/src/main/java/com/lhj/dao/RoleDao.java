package com.lhj.dao;

import com.lhj.pojo.Role;

import java.util.Set;

/**
 * @author lhj
 * @creat 2022-04-25-17:53
 */
public interface RoleDao {
    Set<Role> findRoleByUserId(Integer userId);
}
