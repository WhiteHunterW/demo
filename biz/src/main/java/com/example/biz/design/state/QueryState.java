package com.example.biz.design.state;

import com.example.biz.data.Customer;

import java.util.List;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/8
 */
public interface QueryState {

    List<Customer> query(Object request, List<Customer> dataList);

    void query(DataAccess access);

}
