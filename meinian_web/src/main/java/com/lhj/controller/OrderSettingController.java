package com.lhj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.constants.MessageConstant;
import com.lhj.entity.Result;
import com.lhj.pojo.OrderSetting;
import com.lhj.service.OrderSettingService;
import com.lhj.utils.DateUtils;
import com.lhj.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author lhj
 * @creat 2022-04-12-16:32
 */
@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        try {
            List<OrderSetting> orderSettings = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, orderSettings);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            POIUtils.checkFile(excelFile);
            List<String[]> rows = POIUtils.readExcel(excelFile);

            List<OrderSetting> orderSettings = new ArrayList<>();
            for (String[] row : rows) {
                Date date = DateUtils.parseString2Date(row[0],"yyyy/MM/dd");
                Integer number = Integer.valueOf(row[1]);
                OrderSetting orderSetting = new OrderSetting(date, number);
                orderSettings.add(orderSetting);
            }
            orderSettingService.add(orderSettings);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
