package com.example.mybatis.session;

import java.util.List;

/**
 * @author wenzeng
 * @date 2023/12/18
 */
public interface SqlSession {

    /**
     * Retrieve a single row mapped from the statement key.
     * @param <T> the returned object type
     * @param statement
     * @return Mapped object
     */
    <T> T selectOne(String statement);

    /**
     * Retrieve a single row mapped from the statement key and parameter.
     * @param <T> the returned object type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return Mapped object
     */
    <T> T selectOne(String statement, Object parameter);


    /**
     * 获取mapper实例
     * @param type
     * @return
     * @param <T>
     */
    <T> T getMapper(Class<T> type);
}
