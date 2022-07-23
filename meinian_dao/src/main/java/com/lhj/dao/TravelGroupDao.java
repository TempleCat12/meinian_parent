package com.lhj.dao;

import com.github.pagehelper.Page;
import com.lhj.pojo.TravelGroup;

import java.util.List;
import java.util.Map;

/**
 * @author lhj
 * @creat 2022-03-25-15:32
 */
public interface TravelGroupDao {
    void add(TravelGroup travelGroup);

    void setTravelGroupAndTravelItem(Map<String, Integer> map);

    Page<TravelGroup> findPage(String queryString);

    Integer[] findTravelItemIdByTravelgroupId(Integer id);

    void edit(TravelGroup travelGroup);

    void deleteTravelGroupAndTravelItemByTravelGroupId(Integer id);

    void delete(Integer id);

    List<TravelGroup> findAll();

    List<TravelGroup> findTravelGroupListById(Integer id);
}
