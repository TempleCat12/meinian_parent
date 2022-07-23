package com.lhj.service;

import com.lhj.pojo.Member;

import java.util.List;
import java.util.Map;

/**
 * @author lhj
 * @creat 2022-04-24-15:50
 */
public interface MemberService {
   

    Member findByTelephone(String telephone);

    void add(Member memberNew);

    Map<String, List> getMemberReport() throws Exception;
}
