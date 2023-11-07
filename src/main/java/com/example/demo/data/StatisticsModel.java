package com.example.demo.data;

import com.example.demo.annotation.FieldProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/4/2
 */
@Data
public class StatisticsModel {

    @FieldProperty(sort = 4)
    private Integer workOrderCount;

    @FieldProperty(sort = 2)
    private Integer pointPositionCount;

    @FieldProperty(sort = 3)
    private BigDecimal decimal;

    @FieldProperty(sort = 1)
    private String str;
}
