package com.example.biz.design.state;

import com.example.biz.data.Customer;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenzeng
 * @date 2024/11/29
 */
@Slf4j
public class OutSideQuery extends AbstractQueryState {

    public static OutSideQuery INSTANCE = new OutSideQuery();

    protected OutSideQuery() {
        super(null);
    }

    @Override
    public List<Customer> query(Object request, List<Customer> dataList) {
        log.info("out side query");
        // 业务查询结果
        List<Customer> queryResult = new ArrayList<>();
        return doFilter(dataList, queryResult);
    }

    @Override
    public void addNextQueryState() {

    }
}
