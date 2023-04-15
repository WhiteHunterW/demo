package com.example.demo.data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/4/2
 */
@Data
public class StatisticDTO implements Serializable {


    private static final long serialVersionUID = -4957437892803365359L;

    @ExcelProperty(value = "城市")
    private String city;

    @ExcelProperty({"总计", "工单数"})
    private Integer totalWorkOrderCount;

    @ExcelProperty({"总计", "点位数"})
    private Integer totalPointPositionCount;

    @ExcelProperty({"审批中","工单数"})
    private Integer inApprovalWorkOrderCount;

    @ExcelProperty({"审批中", "点位数"})
    private Integer inApprovalPointPositionCount;
}
