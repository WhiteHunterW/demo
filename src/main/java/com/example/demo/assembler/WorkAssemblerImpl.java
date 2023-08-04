package com.example.demo.assembler;

import org.mapstruct.Named;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/8/4
 */
public class WorkAssemblerImpl {


    @Named(value = "nameToString")
    public String nameToString(String name) {
        return name + "test";
    }
}
