package com.lhj.controller;

import com.lhj.constants.MessageConstant;
import com.lhj.constants.RedisMessageConstant;
import com.lhj.entity.Result;
import com.lhj.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * @author lhj
 * @creat 2022-04-15-21:52
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        try {
            Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
            jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_LOGIN,5*60,validateCode + "");
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS, validateCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        //先判断手机号是否存在
        //再生成验证码
        try {
            Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
            //验证码存进缓存，设置五分钟的过期时间
            jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER,5*60,validateCode.toString());
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS, validateCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }
}
