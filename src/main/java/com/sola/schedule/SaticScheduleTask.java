package com.sola.schedule;


import com.sola.util.EmilUtil;
import com.sola.util.GetUtil;
import com.sola.util.JsonUtil;
import com.sola.util.PostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

    @Autowired
    EmilUtil emilUtil;
    @Autowired
    JsonUtil jsonUtil;
    @Autowired
    PostUtil postUtil;
    @Autowired
    GetUtil getUtil;

}
