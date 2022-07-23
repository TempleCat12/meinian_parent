package com.lhj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.constants.MessageConstant;
import com.lhj.constants.RedisMessageConstant;
import com.lhj.entity.Result;
import com.lhj.pojo.Order;
import com.lhj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @author lhj
 * @creat 2022-04-16-14:39
 */
@RestController
@RequestMapping("/order")
public class OrderMobileController {
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private OrderService orderService;

    @RequestMapping("/findOrderSuccessById")
    public Result findOrderSuccessById(Integer id){
        try {
            Map<String, Object>orderMap = orderService.findOrderSuccessById(id);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, orderMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Order order = orderService.findById(id);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, order);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    @RequestMapping("/submitOrder")
    public Result submitOrder(@RequestBody Map orderInfo){
        String telephone = (String) orderInfo.get("telephone");
        String validateCode = (String) orderInfo.get("validateCode");
        String code = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);

        if (code ==null || !validateCode.equals(code)) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

        Result result = null;
        try {
            orderInfo.put("orderType", Order.ORDERTYPE_WEIXIN);
            result = orderService.order(orderInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }
}
