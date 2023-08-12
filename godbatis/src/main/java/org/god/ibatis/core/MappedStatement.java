package org.god.ibatis.core;

/**
 * Package: org.god.ibatis.core
 * Date: 2023/8/4 9:17
 * Description: null
 */
public class MappedStatement {
    private String sql;
    private String resultType;

    @Override
    public String toString() {
        return "MappedStatement{" +
                "sql='" + sql + '\'' +
                ", resultType='" + resultType + '\'' +
                '}';
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public MappedStatement(String sql, String resultType) {
        this.sql = sql;
        this.resultType = resultType;
    }

    public MappedStatement() {
    }
}
