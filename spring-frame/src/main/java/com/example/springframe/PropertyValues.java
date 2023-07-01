package com.example.springframe;

import java.util.ArrayList;
import java.util.List;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/1
 */
public class PropertyValues {

    final List<PropertyValue> propertyValueList = new ArrayList<>();


    /**
     * 添加属性
     * @param propertyValue
     */
    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    /**
     * 获取属性值
     * @param propertyName
     * @return
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : propertyValueList) {
            if(pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }


    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
