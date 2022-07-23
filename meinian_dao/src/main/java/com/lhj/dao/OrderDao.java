package com.lhj.dao;

import com.lhj.pojo.Order;

import java.util.Map;

/**
 * @author lhj
 * @creat 2022-04-16-15:21
 */
public interface OrderDao {
    void order(Order order);

    Order findOrderByMemberId(Integer memberId);

    Order findById(Integer id);

    Map<String, Object> findOrderSuccessById(Integer id);
}
