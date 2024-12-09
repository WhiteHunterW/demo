package com.example.biz.design;

import com.example.biz.data.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/8
 */
@Slf4j
public abstract class AbstractQueryState implements QueryState{

    protected QueryState nextQuerySate;

    protected AbstractQueryState(QueryState nextQuerySate) {
        this.nextQuerySate = nextQuerySate;
    }

    @Override
    public List<Customer> query(Object request, List<Customer> dataList) {
        // 做通用查询
        log.info("通用查询");
        List<Customer> queryResult = new ArrayList<>();
        // do filter 逐层查询之后数据取交集
        return nextQuerySate.query(request, queryResult);
    }

    @Override
    public void query(DataAccess access) {
        // 1. 执行通用查询逻辑... 得到查询结果
        List<String> queryResult = new ArrayList<>();
        // 2. 执行过滤逻辑 判断是否有交集数据
        if(CollectionUtils.isEmpty(queryResult)) {
            access.setHasData(false);
        } else {

        }
        // 3. 设置下一个功能链 并执行下一个功能链
        addNextQueryState();
        nextQuerySate.query(access);
    }

    /**
     * 数据去重
     * @param dataList
     * @param queryList
     * @return
     */
    protected List<Customer> doFilter(List<Customer> dataList, List<Customer> queryList) {
        // 第一次查询
        if(CollectionUtils.isEmpty(dataList) || CollectionUtils.isEmpty(queryList)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(CollectionUtils.retainAll(dataList, queryList));
    }

    /**
     * 添加下一个调用者节点
     */
    public abstract void addNextQueryState();

}
