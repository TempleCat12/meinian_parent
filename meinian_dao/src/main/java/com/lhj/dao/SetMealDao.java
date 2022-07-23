package com.lhj.dao;

import com.github.pagehelper.Page;
import com.lhj.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @author lhj
 * @creat 2022-03-28-15:50
 */
public interface SetMealDao {
    void add(Setmeal setmeal);

    void setSetmealAndTravelGroup(Map<String, Integer> map);

    Page<Setmeal> findPage(String queryString);

    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);
}
