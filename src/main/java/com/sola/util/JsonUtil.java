package com.sola.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sola.pojo.Entity;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ToDo
 *
 * @Author sola
 * @Date 2022/6/26 15:03
 */
@Component
public class JsonUtil {

    public String getJsonString() {
        BufferedReader br = null;
        String path = "source.json";
        try {
            br = new BufferedReader(new InputStreamReader(JsonUtil.class.getClassLoader().getResourceAsStream(path)));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line + " ");
                        line = br.readLine();
            }
           return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<JSONObject> getJsonObjects(String json){
        List<JSONObject> list=new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(json);
        for (int i = 0; i < jsonArray.size() ; i++) {
            JSONObject o = (JSONObject) jsonArray.get(i);
            list.add(o);
        }
        return list;
    }

    public Entity jsonToEntity(String json){
        Entity entity = new Entity();

        JSONObject jsonObject = JSONObject.parseObject(json);

        String url = (String) jsonObject.get("url");
        String qq= (String) jsonObject.get("qq");

        JSONObject parameter = jsonObject.getJSONObject("parameter");

        JSONObject header = jsonObject.getJSONObject("header");


        Map<String, Object> parameterMap = parameter.getInnerMap();
        Map<String, Object> headerMap = header.getInnerMap();

        entity.setQq(qq);
        entity.setUrl(url);
        entity.setParameter(parameterMap);
        entity.setHeader(headerMap);

        return  entity;
    }

    public List<Entity> getEntityList(){
        List<Entity> list=new ArrayList<>();

        String jsonString = getJsonString();
        List<JSONObject> jsonObjects = getJsonObjects(jsonString);
        for (JSONObject jsonObject : jsonObjects) {
            Entity entity = jsonToEntity(jsonObject.toJSONString());
            list.add(entity);
        }
        return list;
    }


}
