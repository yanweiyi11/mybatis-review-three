package org.god.ibatis.core;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.Map;

/**
 * Package: org.god.ibatis.core
 * Date: 2023/8/4 13:07
 * Description: 专门负责执行SQL语句的会话对象
 */
public class SqlSession {
    private sqlSessionFactory sqlSessionFactory;

    public SqlSession(sqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insert(String sqlId, Object pojo) {
        int count = 0;
        try {
            Connection connection = sqlSessionFactory.getTransaction().getConnection();
            Map<String, MappedStatement> mappedStatements = sqlSessionFactory.getMappedStatements();
            String godBatisSql = mappedStatements.get(sqlId).getSql();
            String sql = godBatisSql.replaceAll("#\\{(\\w+)}", "?");
            PreparedStatement ps = connection.prepareStatement(sql);

            int fromIndex = 0;
            int index = 1; // 问号的下标
            while (true) {
                int jhIndex = godBatisSql.indexOf("#", fromIndex);
                if (jhIndex < 0) break;
                int ykhIndex = godBatisSql.indexOf("}", fromIndex);
                String pName = godBatisSql.substring(jhIndex + 2, ykhIndex).trim();
                fromIndex = ykhIndex + 1;
                String getMethodName = "get" + pName.toUpperCase().charAt(0) + pName.substring(1);
                Method getMethod = pojo.getClass().getDeclaredMethod(getMethodName);
                String retValue = (String) getMethod.invoke(pojo);
                ps.setString(index, retValue);
                index++;
            }

            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public Object selectOne(String sqlId, Object param) {
        Object ret = null;
        try {
            Connection connection = sqlSessionFactory.getTransaction().getConnection();
            MappedStatement mappedStatement = sqlSessionFactory.getMappedStatements().get(sqlId);

            String godBatisSql = mappedStatement.getSql();
            String resultType = mappedStatement.getResultType();

            String sql = godBatisSql.replaceAll("#\\{(\\w+)}", "?");
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, param.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Class<?> clazz = Class.forName(resultType);
                ret = clazz.newInstance();
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String pName = rsmd.getColumnName(i);
                    String setMethodName = "set" + pName.toUpperCase().charAt(0) + pName.substring(1);
                    Method setMethod = clazz.getDeclaredMethod(setMethodName, String.class);
                    setMethod.invoke(ret, rs.getString(pName));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    public static void main(String[] args) {
        String sql = "insert into t_user values (#{id}, #{name}, #{age});";
        int fromIndex = 0;
        int index = 1; // 问号的下标
        while (true) {
            int jhIndex = sql.indexOf("#", fromIndex);
            if (jhIndex < 0) break;
            System.out.println(index);
            index++;
            int ykhIndex = sql.indexOf("}", fromIndex);
            String pName = sql.substring(jhIndex + 2, ykhIndex).trim();
            System.out.println(pName);
            fromIndex = ykhIndex + 1;
        }
    }

    public void commit() {
        sqlSessionFactory.getTransaction().commit();
    }

    public void rollback() {
        sqlSessionFactory.getTransaction().rollback();
    }

    public void close() {
        sqlSessionFactory.getTransaction().close();
    }
}
