package com.example.biz.design.state;

import com.example.biz.data.Customer;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author wenzeng
 * @date 2024/12/3
 * 用于封装链式查询的参数，用到的bean? 以及查询到的数据
 */
@Data
public class DataAccess {

    /**
     * 查询到的客户集合
     */
    private List<String> customerIds;

    /**
     * 查询参数
     */
    private Customer queryParam;

    /**
     * 是否有数据
     */
    private boolean hasData;

    /**
     * 用于查库的bean
     * 外部业务的spring bean 可以通过application获取
     * mapper也可以通过application获取
     */
    private Map<String, String> beanMapper;

}
