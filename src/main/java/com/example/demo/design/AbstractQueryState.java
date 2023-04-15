package com.example.demo.design;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/8
 */
public class AbstractQueryState implements QueryState{

    protected QueryState nextQuerySate;

    @Override
    public void query(Object request) {
        // do something after...
        nextQuerySate.query(request);
    }
}
