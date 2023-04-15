package com.example.demo.strategy;

import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/4/14
 */
public class ExcelRowMergeStrategy implements RowWriteHandler {

    /**
     * 主键下标
     */
    private Integer pkIndex;
    /**
     * 需要合并的列的下标集合
     */
    private List<Integer> needMergeColumnIndex;

    public ExcelRowMergeStrategy(Integer pkIndex, List<Integer> needMergeColumnIndex) {
        this.pkIndex = pkIndex;
        this.needMergeColumnIndex = needMergeColumnIndex;
    }


    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer relativeRowIndex, Boolean isHead) {
        // 如果是标题,则直接返回
        if (Boolean.TRUE.equals(isHead)) {
            return;
        }
        // 获取当前sheet
        Sheet sheet = writeSheetHolder.getSheet();
        // 不能和标题合并，只能数据行之间合并
        if (row.getRowNum() <= 1) {
            return;
        }
        // 获取上一行数据
        Row lastRow = sheet.getRow(row.getRowNum() - 1);
        // 将本行和上一行是同一类型的数据(通过主键字段进行判断)，则需要合并
        if (lastRow.getCell(pkIndex).getStringCellValue().equalsIgnoreCase(row.getCell(pkIndex).getStringCellValue())) {
            for (Integer needMerIndex : needMergeColumnIndex) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(row.getRowNum() - 1, row.getRowNum(),
                        needMerIndex, needMerIndex);
                sheet.addMergedRegionUnsafe(cellRangeAddress);
            }
        }
    }

}
