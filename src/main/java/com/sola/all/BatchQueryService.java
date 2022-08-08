package com.sola.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class BatchQueryService {
    //队列用来存放请求
    private LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    private List<Map<String,Object>> mapList=new ArrayList<>();

    @PostConstruct
    public void initMapList(){
        Map<String,Object> map1 = new HashMap();
        map1.put("1",1);
        Map<String,Object> map2 = new HashMap();
        map2.put("2",2);
        mapList.add(map2);
        mapList.add(map1);
    }

    //封装请求
    class Request {
        String code;
        CompletableFuture<Map<String, Object>> future;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public CompletableFuture<Map<String, Object>> getFuture() {
            return future;
        }

        public void setFuture(CompletableFuture<Map<String, Object>> future) {
            this.future = future;
        }
    }

    @PostConstruct
    public void init() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            int size = queue.size();
            if (size == 0)
                return;

            List<Request> requests = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Request request = queue.poll();
                requests.add(request);
            }
            System.out.println("批量处理了" + size + "条请求");

            List<String> codes = new ArrayList<>();
            for (Request request : requests) {
                codes.add(request.getCode());
            }

           // List<Map<String, Object>> responses = queryItemService.queryByCodes(codes);
            List<Map<String, Object>> responses = mapList;

            //结果集完成--> 把请求分发给每一个具体的Request
            Map<String, Map<String, Object>> responseMap = new HashMap<>();
            for (Map<String, Object> response : responses) {
                String code = response.get("code").toString();
                responseMap.put(code, response);
            }

            //返回请求
            for (Request request : requests) {
                Map<String, Object> result = responseMap.get(request.getCode());
                request.future.complete(result);
            }

        }, 0, 200, TimeUnit.MILLISECONDS);
    }

    //根据code进行单个查询
    public Map<String, Object> queryItem(String code) {
        Request request = new Request();
        request.setCode(code);

        CompletableFuture<Map<String, Object>> future = new CompletableFuture<>();
        request.setFuture(future);

        queue.add(request);

        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
