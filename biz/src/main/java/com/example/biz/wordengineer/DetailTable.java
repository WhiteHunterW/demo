package com.example.biz.wordengineer;

import com.deepoove.poi.data.RowRenderData;
import lombok.Data;

import java.util.List;

/**
 * @author wenzeng
 * @date 2024/12/30
 */
@Data
public class DetailTable {

    /**
     * 货物
     */
    private List<RowRenderData> goods;

    /**
     * 人工费
     */
    private List<RowRenderData> labors;
}
