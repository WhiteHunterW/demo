package com.example.biz.wordengineer;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

/**
 * 自定义渲染策略， 循环列表的使用
 * @author wenzeng
 * @date 2024/12/30
 */
public class DetailTablePolicy extends DynamicTableRenderPolicy {

    /**
     * 货品填充数据所在行
     */
    int goodsStartRow = 2;

    /**
     * 人工费数据填充所在行
     */
    int laborsStartRow = 5;

    @Override
    public void render(XWPFTable table, Object data) throws Exception {
        if(null == data) {
            return;
        }
        DetailTable detailTable = (DetailTable) data;
        List<RowRenderData> labors = detailTable.getLabors();
        // 人工费
        if(null != labors) {
            table.removeRow(laborsStartRow);
            for (int i = 0; i < labors.size(); i++) {
                XWPFTableRow insertRow = table.insertNewTableRow(laborsStartRow);
                for (int j = 0; j < 7; j++) {
                    insertRow.createCell();
                }
                // 合并单元格
                TableTools.mergeCellsHorizonal(table, laborsStartRow, 0, 3);
                TableTools.mergeCellsVertically(table, laborsStartRow, 2,3);
                TableRenderPolicy.Helper.renderRow(table.getRow(laborsStartRow), labors.get(i));
            }
        }
        // 货物明细
        List<RowRenderData> goods = detailTable.getGoods();
        if(null != goods) {
            table.removeRow(goodsStartRow);
            for (int i = 0; i < goods.size(); i++) {
                XWPFTableRow insertRow = table.insertNewTableRow(goodsStartRow);
                for (int j = 0; j < 7; j++) {
                    // 创建单元格
                    insertRow.createCell();
                }
                // 循环插入行
                TableRenderPolicy.Helper.renderRow(table.getRow(goodsStartRow), goods.get(i));
            }
        }
    }
}
