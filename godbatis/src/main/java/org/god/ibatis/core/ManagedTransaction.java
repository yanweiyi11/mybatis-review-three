package org.god.ibatis.core;

import java.sql.Connection;

/**
 * Package: org.god.ibatis.core
 * Date: 2023/8/4 9:57
 * Description: null
 */
public class ManagedTransaction implements Transaction {
    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }

    @Override
    public void openConnection() {

    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
