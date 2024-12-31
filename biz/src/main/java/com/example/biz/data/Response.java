package com.example.biz.data;

import lombok.Data;

/**
 * @author wenzeng
 * @date 2024/12/19
 */
@Data
public class Response<T> {

    /**
     * 状态码
     */
    private String code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public Response() {
        // 空构造
    }

    public Response(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 操作成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Response<T> success(T data) {
        return new Response<>("200", "操作成功", data);
    }

    /**
     * 操作失败
     * @param msg
     * @return
     */
    public static Response<Void> error(String msg) {
        return new Response<>("500", msg, null);
    }
}
