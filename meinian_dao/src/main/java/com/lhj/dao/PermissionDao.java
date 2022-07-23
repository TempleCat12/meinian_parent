package com.lhj.dao;

import com.lhj.pojo.Permission;

import java.util.Set;

/**
 * @author lhj
 * @creat 2022-04-25-18:22
 */
public interface PermissionDao {
    Set<Permission> findPermissionByRoleId(Integer roleId);
}
