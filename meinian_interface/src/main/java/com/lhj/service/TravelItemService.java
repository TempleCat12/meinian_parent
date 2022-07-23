package com.lhj.service;

import com.lhj.entity.PageResult;
import com.lhj.entity.QueryPageBean;
import com.lhj.pojo.TravelItem;

import java.util.List;

/**
 * @author lhj
 * @creat 2022-03-15-10:02
 */
public interface TravelItemService {

    void add(TravelItem travelItem);

    PageResult findPage(QueryPageBean queryPageBean);

    void edit(TravelItem travelItem);

    void deleteById(Integer id);

    List<TravelItem> findAll();
}
