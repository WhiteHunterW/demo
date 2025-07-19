package com.example.biz.wordengineer;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.Tables;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.Preconditions;
import com.deepoove.poi.util.TableTools;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import java.util.List;

/**
 * @author wenzeng
 * @date 2025/6/24
 */
public class ContractTablePolicy extends DynamicTableRenderPolicy {

    /**
     * 数据填充行
     */
    int laborsStartRow = 1;


    @Override
    public void render(XWPFTable table, Object data) throws Exception {
        if (null == data) {
            return;
        }
        /*DetailTable detailTable = (DetailTable) data;
        List<RowRenderData> goods = detailTable.getGoods();
        if(null != goods) {
            table.removeRow(laborsStartRow);
            for (RowRenderData good : goods) {
                XWPFTableRow insertRow = table.insertNewTableRow(laborsStartRow);
                for (int j = 0; j < 7; j++) {
                    XWPFTableCell tableCell = insertRow.createCell();
                    setCellStyle(tableCell);
                }
                TableRenderPolicy.Helper.renderRow(table.getRow(laborsStartRow), good);
            }
            // 列
            for (int m = 0; m < 5; m++) {
                // 行
                for (int n = 1; n < table.getNumberOfRows(); ) {
                    int f = n;
                    XWPFTableRow thisRow = table.getRow(f);
                    String thisCellValue = thisRow.getCell(m).getText();
                    while (f < table.getRows().size() - 1) {
                        XWPFTableRow nextRow = table.getRow(f + 1);
                        String lastCellValue = nextRow.getCell(m).getText();
                        if(StringUtils.equals(lastCellValue, thisCellValue)) {
                            f++;
                        } else {
                            break;
                        }
                    }
                    if(f == n) {
                        // 没有找到要合并的
                        n++;
                    } else  {
                        TableTools.mergeCellsVertically(table, m, n, f);
                        // 合并后设置单元格段落居中方式
                        XWPFTableCell cell = table.getRow(n).getCell(m);
                        setCellStyle(cell);
                        n = f;
                    }
                }
            }
        }*/
        table.removeRow(laborsStartRow);
        TableRenderData renderData = (TableRenderData) data;
        for (RowRenderData rowRenderData : renderData.getRows()) {
            XWPFTableRow insertRow = table.insertNewTableRow(laborsStartRow);
            for (int j = 0; j < 7; j++) {
                XWPFTableCell tableCell = insertRow.createCell();
                setCellStyle(tableCell);
            }
            TableRenderPolicy.Helper.renderRow(table.getRow(laborsStartRow), rowRenderData);
        }
        // 合并单元格
        for (int rowIndex = 1; rowIndex < table.getNumberOfRows() - 1; ) {
            XWPFTableRow row = table.getRow(rowIndex);
            String contractCode = row.getCell(0).getText();
            // 默认每组只有一条有值 写的时候是第一条有值，生成表格是最后一条有值
            int f;
            if(StringUtils.isNotEmpty(contractCode)) {
                f = rowIndex + 1;
                while (f < table.getNumberOfRows() - 1) {
                    row = table.getRow(f);
                    contractCode = row.getCell(0).getText();
                    // 找到下一行不为空的数据
                    if(StringUtils.isNotEmpty(contractCode)) {
                        break;
                    }
                    f++;
                }
                // 有可能不需要合并
                if(f == rowIndex) {
                    rowIndex++;
                    continue;
                }
                mergeCellAndSetStyle(table,0,  rowIndex, f - 1);
                mergeCellAndSetStyle(table,1,  rowIndex, f - 1);
                mergeCellAndSetStyle(table,2,  rowIndex, f - 1);
                mergeCellAndSetStyle(table,3,  rowIndex, f - 1);
                mergeCellAndSetStyle(table,4,  rowIndex, f - 1);
                rowIndex = f;
            } else {
                rowIndex++;
            }
        }
    }

    /**
     * 垂直合并并设置段落居中方式
     * @param table
     * @param col
     * @param startRow
     * @param endRow
     */
    private void mergeCellAndSetStyle(XWPFTable table, int col, int startRow, int endRow) {
        TableTools.mergeCellsVertically(table, col, startRow, endRow);
        // 合并后设置单元格段落居中方式
        XWPFTableCell cell = table.getRow(startRow).getCell(col);
        setCellStyle(cell);
    }


    /**
     * 设置单元格垂直居中
     * @param tableCell
     */
    private void setCellStyle(XWPFTableCell tableCell) {
        // 水平居中
        XWPFParagraph p = tableCell.getParagraphs().get(0);
        p.setAlignment(ParagraphAlignment.CENTER);
        // 垂直居中
        CTTcPr tcPr = tableCell.getCTTc().addNewTcPr();
        if(tcPr == null) {
            tcPr = tableCell.getCTTc().addNewTcPr();
        }
        CTVerticalJc ctVerticalJc = tcPr.isSetVAlign() ? tcPr.getVAlign() : tcPr.addNewVAlign();
        ctVerticalJc.setVal(STVerticalJc.CENTER);
    }
}

