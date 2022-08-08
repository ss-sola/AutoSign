package com.sola;

import com.sola.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AutoSolaApplicationTests {

    @Autowired
    List<UserService> userServices;

    @Test
    void contextLoads() {
        System.out.println(userServices.size());
        userServices.get(0).test();
        userServices.get(1).test();
    }

}
