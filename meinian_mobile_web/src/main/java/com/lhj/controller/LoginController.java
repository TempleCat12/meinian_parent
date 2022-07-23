package com.lhj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.constants.MessageConstant;
import com.lhj.constants.RedisMessageConstant;
import com.lhj.entity.Result;
import com.lhj.pojo.Member;
import com.lhj.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author lhj
 * @creat 2022-04-24-15:35
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private JedisPool jedisPool;

    @Reference
    private MemberService memberService;

    @RequestMapping("/check")
    public Result check(@RequestBody Map loginInfo, HttpServletResponse response){
        String telephone = (String) loginInfo.get("telephone");
        String validateCode = (String) loginInfo.get("validateCode");
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        //校验验证码
        if(codeInRedis == null || !codeInRedis.equals(validateCode)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        //判断是否为会员
        Member member = memberService.findByTelephone(telephone);
        if (member == null) {
            //注册会员
            Member memberNew = new Member();
            memberNew.setPhoneNumber(telephone);
            memberNew.setRegTime(new Date());
            memberService.add(memberNew);
        }
        //写入cookie
        Cookie cookie = new Cookie("login_member_telephone", telephone);
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new Result(true, MessageConstant.LOGIN_SUCCESS);

    }
}
