package com.sola.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class PostUtil {


    /**
     * @param url     请求路径
     * @param json    请求参数
     * @param header  请求头
     * @return {@link String}
     * @throws
     * @Author sola
     * @Date 2022/6/26 14:54
     */
    public JSONObject doPost(String url, String json, Map<String, Object> header) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String res = "";

        Object code = null;
        JSONObject jsonObject = null;
        try {
            HttpPost post = new HttpPost(url);

            for (String str : header.keySet()) {
                post.setHeader(str, (String) header.get(str));
            }

            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
            response = httpClient.execute(post);

            res = EntityUtils.toString(response.getEntity(), "utf-8");


            jsonObject = JSONObject.parseObject(res);
           
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }


}

