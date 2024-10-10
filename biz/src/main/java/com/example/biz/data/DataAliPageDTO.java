package com.example.biz.data;

import lombok.Data;

import java.util.List;

/**
 * @author wenzeng
 * @date 2024/10/9
 */
@Data
public class DataAliPageDTO<T>{

    /**
     * 总条数
     */
    private Integer totalNum;

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 数据
     */
    private List<T> rows;
}
