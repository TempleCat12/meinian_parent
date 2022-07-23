package com.lhj.service;

import com.lhj.pojo.OrderSetting;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author lhj
 * @creat 2022-04-12-16:34
 */
public interface OrderSettingService {
    void add(List<OrderSetting> orderSettings) throws Exception;

    List<OrderSetting> getOrderSettingByMonth(String date) throws Exception;

    void editNumberByDate(OrderSetting orderSetting);
}
