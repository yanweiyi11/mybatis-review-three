package org.god.ibatis.core;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Package: org.god.ibatis.core
 * Date: 2023/8/4 9:56
 * Description: null
 */
public class JdbcTransaction implements Transaction {

    private DataSource dataSource;
    private Boolean autoCommit;
    private Connection connection;

    public JdbcTransaction(DataSource dataSource, Boolean autoCommit) {
        this.dataSource = dataSource;
        this.autoCommit = autoCommit;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void openConnection() {
        try {
            if (this.connection == null) {
                this.connection = this.dataSource.getConnection();
                connection.setAutoCommit(autoCommit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
