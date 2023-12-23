package com.example.mybatis.session;

/**
 * 管理SqlSession的工厂接口
 * @author wenzeng
 * @date 2023/12/18
 */
public interface SqlSessionFactory {

    SqlSession openSqlSession();
}
