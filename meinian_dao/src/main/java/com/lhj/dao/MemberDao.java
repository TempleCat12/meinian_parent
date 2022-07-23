package com.lhj.dao;

import com.lhj.pojo.Member;

import java.util.Date;

/**
 * @author lhj
 * @creat 2022-04-16-15:55
 */
public interface MemberDao {
    Member getMemberByTelephone(String telephone);

    void add(Member memberNew);

    Date getMinRegDate();

    Date getMaxRegDate();

    Long getMemberCountByMonth(Date endDate);
}
