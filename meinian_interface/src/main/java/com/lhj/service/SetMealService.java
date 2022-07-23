package com.lhj.service;

import com.lhj.entity.PageResult;
import com.lhj.entity.QueryPageBean;
import com.lhj.pojo.Setmeal;

import java.util.List;

/**
 * @author lhj
 * @creat 2022-03-28-15:48
 */
public interface SetMealService {
    void add(Integer[] travelgroupIds, Setmeal setmeal);

    PageResult findPage(QueryPageBean queryPageBean);

    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);
}
