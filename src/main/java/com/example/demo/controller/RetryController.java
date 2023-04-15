package com.example.demo.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.example.demo.data.StatisticDTO;
import com.example.demo.service.RetryService;
import com.example.demo.strategy.ExcelRowMergeStrategy;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
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
}
