package com.lhj.service;

import com.lhj.entity.Result;
import com.lhj.pojo.Order;

import java.util.Map;

/**
 * @author lhj
 * @creat 2022-04-16-15:09
 */
public interface OrderService {
    Result order(Map orderInfo) throws Exception;

    Order findById(Integer id);

    Map<String, Object> findOrderSuccessById(Integer id);
}
