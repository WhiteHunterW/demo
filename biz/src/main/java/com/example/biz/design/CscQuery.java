package com.example.biz.design;

import com.example.biz.data.Customer;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/8
 */
@Slf4j
public class CscQuery extends AbstractQueryState {

    // 单例模式？
    public static CscQuery INSTANCE = new CscQuery();

    private CscQuery() {
        super(CsmQuery.INSTANCE);
    }

    @Override
    public List<Customer> query(Object request,List<Customer> dataList) {
        log.info("csc query");
        // 业务查询结果
        List<Customer> queryResult = new ArrayList<>();
        return nextQuerySate.query(request, doFilter(dataList, queryResult));
    }

    @Override
    public void addNextQueryState() {
        nextQuerySate = CsmQuery.INSTANCE;
    }
}
