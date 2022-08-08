package com.sola.pojo;

import lombok.Data;
import java.util.Map;

/**
 * ToDo
 *
 * @Author sola
 * @Date 2022/6/26 14:59
 */
@Data
public class Entity {

    private String url;
    private Map<String,Object> parameter;
    private Map<String,Object> header;
    private String qq;
}
