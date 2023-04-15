package com.example.demo.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wz
 * @date 2022/1/16
 */
@Data
public class User implements Serializable {


    private static final long serialVersionUID = 4852082541975399915L;

    private String name;

    private Integer gender;

    private Date created;

    private int count;

    public User(){

    }
    public User(String name, Integer gender){
        this.name = name;
        this.gender = gender;
    }
}
