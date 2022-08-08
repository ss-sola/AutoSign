package com.sola.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName User2
 * TODO
 * @Date 2022/8/8 11:19
 **/
@Component
public class User2 implements UserService{
    @Override
    public void test() {
        System.out.println("User2");
    }
}
