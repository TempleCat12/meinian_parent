package com.lhj.service;

import com.lhj.entity.PageResult;
import com.lhj.entity.QueryPageBean;
import com.lhj.pojo.TravelGroup;

import java.util.List;

/**
 * @author lhj
 * @creat 2022-03-25-15:30
 */
public interface TravelGroupService {
    void add(Integer[] travelItemIds, TravelGroup travelGroup);

    PageResult findPage(QueryPageBean queryPageBean);

    Integer[] findTravelItemIdByTravelgroupId(Integer id);

    void edit(Integer[] travelItemIds, TravelGroup travelGroup);

    void delete(Integer id);

    List<TravelGroup> findAll();
}
