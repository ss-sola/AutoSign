package com.sola.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName User1
 * TODO
 * @Date 2022/8/8 11:18
 **/
@Component
public class User1 implements UserService{
    @Override
    public void test() {
        System.out.println("user1");
    }
}
