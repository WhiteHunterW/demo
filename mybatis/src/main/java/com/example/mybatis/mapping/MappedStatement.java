package com.example.mybatis.mapping;

import com.example.mybatis.session.Configuration;

import java.util.List;

/**
 * @author wenzeng
 * @date 2023/12/23
 */
public class MappedStatement {

    private String resource;
    private Configuration configuration;
    private String id;
    private SqlCommandType sqlCommandType;
    private ParameterMap parameterMap;
    private List<ResultMap> resultMaps;
    private String sql;

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public ParameterMap getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(ParameterMap parameterMap) {
        this.parameterMap = parameterMap;
    }

    public List<ResultMap> getResultMaps() {
        return resultMaps;
    }

    public void setResultMaps(List<ResultMap> resultMaps) {
        this.resultMaps = resultMaps;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResource() {
        return resource;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getId() {
        return id;
    }
}
