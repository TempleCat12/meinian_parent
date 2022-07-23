package com.lhj.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author lhj
 * @creat 2022-04-26-8:02
 */
public class TestEncode {

    @Test
    public void testEncode(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //admin password
        String encode = bCryptPasswordEncoder.encode("12345678");
        System.out.println(encode);
        if (bCryptPasswordEncoder.matches("12345678","$2a$10$Cu5aRuq9rhCzITaty1ldfOYBI/TwZa1sVTmcqtwYdgwzHF5kVe/1a")) {
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
    }
}
