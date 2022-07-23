package com.lhj.dao;

import com.github.pagehelper.Page;
import com.lhj.entity.PageResult;
import com.lhj.entity.QueryPageBean;
import com.lhj.pojo.TravelItem;

import java.util.List;

/**
 * @author lhj
 * @creat 2022-03-15-10:09
 */
public interface TravelItemDao {

    void add(TravelItem travelItem);

    Page<TravelItem> findPage(String queryString);

    void edit(TravelItem travelItem);

    void deleteById(Integer id);

    Long findCountByTravelItemItemId(Integer id);

    List<TravelItem> findAll();

    List<TravelItem> findTravelItemListById(Integer id);
}
