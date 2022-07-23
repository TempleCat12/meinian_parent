package com.lhj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.dao.OrderSettingDao;
import com.lhj.pojo.OrderSetting;
import com.lhj.service.OrderSettingService;
import com.lhj.utils.DateUtils;
import com.lhj.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @author lhj
 * @creat 2022-04-12-16:48
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService{
    @Autowired
    private OrderSettingDao orderSettingDao;


    @Override
    public void add(List<OrderSetting> orderSettings) throws Exception {
        for (OrderSetting orderSetting : orderSettings) {
            Date orderDate = orderSetting.getOrderDate();
            Long count = orderSettingDao.findCountByOrderDate(orderDate);
            if (count > 0) {
                orderSettingDao.editNumberByOrderDate(orderSetting);
            }else {
                orderSettingDao.add(orderSetting);
            }
        }
    }

    @Override
    public List<OrderSetting> getOrderSettingByMonth(String date) throws Exception {
        Date dateBegin = DateUtils.parseString2Date(date + "-1");
        Date dateEnd = DateUtils.parseString2Date(date + "-31");

        Map<String,Date> dateMap = new HashMap<>();
        dateMap.put("dateBegin",dateBegin);
        dateMap.put("dateEnd",dateEnd);

        return orderSettingDao.getOrderSettingByMonth(dateMap);
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        orderSettingDao.editNumberByOrderDate(orderSetting);
    }
}
