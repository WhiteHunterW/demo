package com.example.common;

/**
 * Function:
 * 业务异常 com.example.spring frame.exception
 * @author wenzeng
 * @date 2023/6/13
 */
public class BizException extends RuntimeException{

    private static final long serialVersionUID = -8014707564235824006L;

    private final String message;

    private String code;


    public BizException(String msg) {
        this.message = msg;
    }


}
