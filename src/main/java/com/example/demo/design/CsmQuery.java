package com.example.demo.design;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/8
 */
public class CsmQuery extends AbstractQueryState{

    private CscQuery instance = new CscQuery();

    public CsmQuery(){
        nextQuerySate = instance;
    }
}
