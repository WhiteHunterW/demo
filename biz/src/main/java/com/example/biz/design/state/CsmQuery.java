package com.example.biz.design.state;

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
public class CsmQuery extends AbstractQueryState {

    public static CsmQuery INSTANCE = new CsmQuery();

    private CsmQuery(){
        super(OutSideQuery.INSTANCE);
    }

    @Override
    public List<Customer> query(Object request,List<Customer> dataList) {
        log.info("csm query ");
        // 业务查询结果
        List<Customer> queryResult = new ArrayList<>();
        return nextQuerySate.query(request, doFilter(dataList, queryResult));
    }

    @Override
    public void addNextQueryState() {
        nextQuerySate = OutSideQuery.INSTANCE;
    }

}
