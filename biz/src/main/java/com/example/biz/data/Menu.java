package com.example.biz.data;

import lombok.Data;

import java.util.List;

/**
 * @author wenzeng
 * @date 2024/12/31
 */
@Data
public class Menu {


    /**
     * id
     */
    private Integer id;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 是否有权限 是否展示
     */
    private Boolean hasPermission;

    /**
     * 子节点
     */
    private List<Menu> children;



}
