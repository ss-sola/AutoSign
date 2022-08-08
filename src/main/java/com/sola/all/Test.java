package com.sola.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Test {

    @Autowired
    private DefaultSchedulingConfigurer defaultSchedulingConfigurer;

    @Autowired
    BatchQueryService batchQueryService;

    @GetMapping("/zhy")
    public void zhy() throws InterruptedException {
        while (!defaultSchedulingConfigurer.inited())
        {
            Thread.sleep(100);
        }
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            defaultSchedulingConfigurer.addTriggerTask("task"+finalI,
                    new TriggerTask(
                            () -> System.out.println("=====>"+ finalI),
                            new CronTrigger("0 46 8 * * ? ")));
        }

    }
    @GetMapping("/uzhy")
    public void uzhy(){
        defaultSchedulingConfigurer.cancelTriggerTask("task");
    }
    @GetMapping("/test")
    public void test(){
        System.out.println(batchQueryService.queryItem("1").keySet());
    }
}
