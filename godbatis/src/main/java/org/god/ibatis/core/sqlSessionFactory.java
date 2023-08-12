package org.god.ibatis.core;

import java.sql.Connection;
import java.util.Map;

/**
 * Package: org.god.ibatis.core
 * Date: 2023/8/4 8:57
 * Description: null
 */
public class sqlSessionFactory {
    private Transaction transaction;

    private Map<String, MappedStatement> mappedStatements;

    public SqlSession openSession() {
        transaction.openConnection();
        SqlSession sqlSession = new SqlSession(this);
        return sqlSession;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }

    public sqlSessionFactory() {
    }

    public sqlSessionFactory(Transaction transaction, Map<String, MappedStatement> mappedStatements) {
        this.transaction = transaction;
        this.mappedStatements = mappedStatements;
    }
}
