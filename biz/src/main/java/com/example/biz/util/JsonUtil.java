package com.example.biz.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.biz.data.DataAliPageDTO;
import com.example.biz.data.PublicationRateIndustryResponseDTO;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * 序列化工具类
 * 使用TypeReference，参考https://github.com/alibaba/fastjson/wiki/TypeReference
 * @author wenzeng
 * @date 2024/10/10
 */
public class JsonUtil {

    final static String dataJsonStr = "{\n" +
            "    \"totalNum\": 153,\n" +
            "    \"pageSize\": 300,\n" +
            "    \"rows\": [\n" +
            "        {\n" +
            "            \"jWeekId\": \"2022014\",\n" +
            "            \"productLine\": \"A0\",\n" +
            "            \"productLineDesc\": \"智能屏\",\n" +
            "            \"productLineSub\": \"0000\",\n" +
            "            \"productLineSubDesc\": \"全部\",\n" +
            "            \"industryCode\": \"HY001\",\n" +
            "            \"industryName\": \"大健康\",\n" +
            "            \"industryLevel\": \"1\",\n" +
            "            \"xjRateFz\": \"8640985500.0\",\n" +
            "            \"allRateFz\": \"14741484000.0\",\n" +
            "            \"skFenmu\": \"298693755000.0\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"jWeekId\": \"2022015\",\n" +
            "            \"productLine\": \"A0\",\n" +
            "            \"productLineDesc\": \"智能屏\",\n" +
            "            \"productLineSub\": \"0000\",\n" +
            "            \"productLineSubDesc\": \"全部\",\n" +
            "            \"industryCode\": \"HY001\",\n" +
            "            \"industryName\": \"大健康\",\n" +
            "            \"industryLevel\": \"1\",\n" +
            "            \"xjRateFz\": \"7163353500.0\",\n" +
            "            \"allRateFz\": \"13750071000.0\",\n" +
            "            \"skFenmu\": \"293361606000.0\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"jWeekId\": \"2022021\",\n" +
            "            \"productLine\": \"A0\",\n" +
            "            \"productLineDesc\": \"智能屏\",\n" +
            "            \"productLineSub\": \"0000\",\n" +
            "            \"productLineSubDesc\": \"全部\",\n" +
            "            \"industryCode\": \"HY001\",\n" +
            "            \"industryName\": \"大健康\",\n" +
            "            \"industryLevel\": \"1\",\n" +
            "            \"xjRateFz\": \"4792059000.0\",\n" +
            "            \"allRateFz\": \"21256693500.0\",\n" +
            "            \"skFenmu\": \"297559053000.0\"\n" +
            "        }],\n" +
            "    \"pageNum\": 1\n" +
            "}";

    /**
     * 返回具体的类型
     * @param jsonStr
     * @return
     */
    public static DataAliPageDTO<PublicationRateIndustryResponseDTO> jsonToObject(String jsonStr) {
        return JSONObject.parseObject(jsonStr, new TypeReference<DataAliPageDTO<PublicationRateIndustryResponseDTO>>(){});
    }

    /**
     * 反序列化嵌套对象，嵌套对象使用了泛型
     * 反序列化后T是JSONObject类型
     * @param jsonStr
     * @return
     * @param <T>
     */
    public static  <T> DataAliPageDTO<T> jsonToT(String jsonStr) {
        return JSONObject.parseObject(jsonStr, new TypeReference<DataAliPageDTO<T>>(){});
    }


    /**
     * 对jsonToT(String jsonStr) 的优化，带着类类型一起反序列化
     * 传入的T是什么类型 返回就是什么类型
     * @param jsonStr
     * @param cl
     * @return
     * @param <T>
     */
    public static  <T> DataAliPageDTO<T> jsonToTV2(String jsonStr, Class<T> cl) {
        return JSONObject.parseObject(jsonStr, new TypeReference<DataAliPageDTO<T>>(cl){});
    }

    /**
     * 性能优于 JSONObject.parseObject(jsonStr, new TypeReference<DataAliPageDTO<T>>(){})
     * 最后的泛型类型仍然是JSONObject
     * @param jsonStr
     * @return
     * @param <T>
     */
    public static  <T> DataAliPageDTO<T> jsonToV3(String jsonStr) {
        Type type = new TypeReference<DataAliPageDTO<T>>(){}.getType();
        return JSONObject.parseObject(jsonStr, type);
    }


    /**
     * key value都是泛型
     * 如果有嵌套对象 嵌套对象的类型仍然是JSONObject
     * @param jsonStr
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K,V> Map<K, V> jsonToMap(String jsonStr) {
        return JSONObject.parseObject(jsonStr, new TypeReference<Map<K,V>>(){});
    }

    /**
     * 对jsonToMap(String jsonStr)的优化
     * key-value都是泛型 传入具体的类类型
     * @param jsonStr
     * @param kClass
     * @param vClass
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K,V> Map<K, V> jsonToMapV2(String jsonStr, Class<K> kClass, Class<V> vClass) {
        return JSONObject.parseObject(jsonStr, new TypeReference<Map<K,V>>(kClass, vClass){});
    }

    public static void main(String[] args) {
        //DataAliPageDTO<PublicationRateIndustryResponseDTO> response = jsonToV3(dataJsonStr)
        Map<String, String> map = jsonToMapV2(dataJsonStr, String.class, String.class);
        System.out.println(map);
    }

}
