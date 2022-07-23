package com.lhj.dao;

import com.lhj.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lhj
 * @creat 2022-04-12-16:49
 */
public interface OrderSettingDao {
    void add(OrderSetting orderSetting);

    Long findCountByOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMonth(Map<String, Date> dateMap);

    OrderSetting getOrderSettingByDate(Date orderDate);

    void updateReservationsByDate(OrderSetting orderSetting);
}
