package com.lhj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.dao.MemberDao;
import com.lhj.pojo.Member;
import com.lhj.service.MemberService;
import com.lhj.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author lhj
 * @creat 2022-04-24-15:51
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.getMemberByTelephone(telephone);
    }

    @Override
    public void add(Member memberNew) {
        memberDao.add(memberNew);
    }

    @Override
    public Map<String, List> getMemberReport() throws Exception {
        //获取会员注注册时间的各个月份
        Map<String, List> map = new HashMap<>();
        Date minDate = memberDao.getMinRegDate();
        Date maxDate = memberDao.getMaxRegDate();
        List<String> months = DateUtils.getMonthBetween(DateUtils.parseDate2String(minDate),
                DateUtils.parseDate2String(maxDate), "yyyy-MM");

        map.put("months",months);

        //获取<=某月 的会员人数
        List<Long> memberCount = new ArrayList<>();
        for (String month : months) {
            Date endDate = DateUtils.parseString2Date(month + "-31");
            Long memberNum =  memberDao.getMemberCountByMonth(endDate);
            memberCount.add(memberNum);
        }

        map.put("memberCount",memberCount);
        return map;
    }
}
