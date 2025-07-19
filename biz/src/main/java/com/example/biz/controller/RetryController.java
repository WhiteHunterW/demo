package com.example.biz.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSON;
import com.example.biz.data.DateDTO;
import com.example.biz.data.StatisticDTO;
import com.example.biz.service.RetryService;
import com.example.biz.strategy.ExcelRowMergeStrategy;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Function:
 *
 * @author wz
 * @date 2022/7/31
 */
@RequestMapping
@RestController
@Slf4j
public class RetryController {

    @Autowired
    private RetryService retryService;

    @GetMapping("/test/recovery")
    public void testRecovery(String number) throws TimeoutException {
        retryService.testException(number);
    }

    @GetMapping("/test/recovery/template")
    public void testRetryTemplate(String number) throws TimeoutException {
        retryService.turnNumber(number);
    }

    /**
     * excel导出合并指定列
     * @param response
     */
    @PostMapping("/test/export")
    public void testExport(HttpServletResponse response){
        List<StatisticDTO> dtoList = new ArrayList<>();
        StatisticDTO dto = new StatisticDTO();
        dto.setCity("汇总");
        dto.setTotalPointPositionCount(1);
        dto.setTotalWorkOrderCount(2);
        dto.setInApprovalPointPositionCount(3);
        dto.setInApprovalWorkOrderCount(4);
        StatisticDTO dto1 = new StatisticDTO();
        dto1.setCity("汇总");
        dto1.setTotalPointPositionCount(4);
        dto1.setTotalWorkOrderCount(1);
        dto1.setInApprovalPointPositionCount(5);
        dto1.setInApprovalWorkOrderCount(6);

        StatisticDTO dto2 = new StatisticDTO();
        dto2.setCity("测试");
        dto2.setTotalPointPositionCount(4);
        dto2.setTotalWorkOrderCount(1);
        dto2.setInApprovalPointPositionCount(5);
        dto2.setInApprovalWorkOrderCount(6);
        StatisticDTO dto3 = new StatisticDTO();
        dto3.setCity("测试1");
        dto3.setTotalPointPositionCount(4);
        dto3.setTotalWorkOrderCount(1);
        dto3.setInApprovalPointPositionCount(5);
        dto3.setInApprovalWorkOrderCount(6);

        StatisticDTO dto4 = new StatisticDTO();
        dto4.setCity("测试");
        dto4.setTotalPointPositionCount(4);
        dto4.setTotalWorkOrderCount(1);
        dto4.setInApprovalPointPositionCount(5);
        dto4.setInApprovalWorkOrderCount(6);

        dtoList.add(dto);
        dtoList.add(dto1);
        dtoList.add(dto3);
        dtoList.add(dto2);
        dtoList.add(dto4);

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            final String fileName = URLEncoder.encode("测试导出", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName +".xlsx");
            EasyExcelFactory.write(response.getOutputStream(), StatisticDTO.class)
                    .registerWriteHandler(new ExcelRowMergeStrategy(0, Lists.newArrayList(0)))
                    .sheet("故障工单").doWrite(dtoList);
        } catch (Exception ex) {
            log.error("导出拆除工单智慧屏异常：", ex);
        }

    }

    @PostMapping("/test/import")
    public List<DateDTO> readExcelDate(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        /*try {
            List<DateDTO> dateDTOS = EasyExcelFactory.read(file.getInputStream())
                    .head(DateDTO.class)
                    .sheet().doReadSync();
            return dateDTOS;
        } catch (IOException e) {
            log.error("导入失败");
        }
        return null;*/
        try {
            List<DateDTO> dtoList = getDateDTOS(file);
            return dtoList;
        } catch (IOException e) {
            log.error("处理文件失败", e);
        }
        return null;
    }

    private static List<DateDTO> getDateDTOS(MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterable = sheet.iterator();
        List<DateDTO> dtoList = new ArrayList<>();
        while (iterable.hasNext()) {
            Row row = iterable.next();
            Cell cell = row.getCell(0);
            String date = null;
            if(cell.getCellType() == CellType.NUMERIC) {
                Date numDate = DateUtil.getJavaDate(cell.getNumericCellValue());
                date = cn.hutool.core.date.DateUtil.format(numDate, "yyyy-MM-dd");
            } else if (cell.getCellType() == CellType.STRING) {
                date = cell.getStringCellValue();
            }
            String user = row.getCell(1).getStringCellValue();
            DateDTO dto = new DateDTO();
            dto.setDate(date);
            dto.setUserName(user);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
