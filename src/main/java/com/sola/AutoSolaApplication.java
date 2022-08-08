package com.sola;

import com.sola.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@EnableScheduling
@SpringBootApplication
public class AutoSolaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoSolaApplication.class, args);

    }

}

//class Test{
//    @Autowired
//    List<UserService> userServices;
//}


