package org.god.ibatis.core;

import java.sql.Connection;

/**
 * Package: org.god.ibatis.core
 * Date: 2023/8/4 9:43
 * Description: 事务管理器接口
 */
public interface Transaction {
    void commit();

    void rollback();

    void close();

    void openConnection();

    Connection getConnection();
}
