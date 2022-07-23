package com.lhj.test;

import com.lhj.utils.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * @author lhj
 * @creat 2022-04-14-22:54
 */
public class TestDateUtils {
    @Test
    public void testDate31() throws Exception {
        Date date = DateUtils.parseString2Date("2022-04-32");
        System.out.println(date);
    }
}
