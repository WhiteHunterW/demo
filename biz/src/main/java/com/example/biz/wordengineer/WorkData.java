package com.example.biz.wordengineer;

import com.deepoove.poi.data.NumberingRenderData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wenzeng
 * @date 2024/12/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkData {

    /**
     * 公司
     */
    private String company;

    /**
     * 部门
     */
    private String department;

    /**
     * 工作岗位
     */
    private String job;

    /**
     * 工作时间
     */
    private String time;

    /**
     * 主要职责
     * 循环列表
     */
    private NumberingRenderData responsibility;
}
