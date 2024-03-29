package com.example.springframe.beans;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/1
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
