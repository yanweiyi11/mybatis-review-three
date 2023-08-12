package com.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/1 11:37
 * Description: null
 */
public class MyBatisTest1 {
    public static void main(String[] args) throws Exception {
        /**
         * 当前只有mybatis，如果配置为MANAGED，那么事务是没人管的，表示事务没开启
         * 使用jdbc是事务管理器，事务管理器对象：jdbcTransaction
         * 在jdbc中，如果没有执行过conn.setAutoCommit(false)，默认的autoCommit是true
         * 只要自动提交是true，就表示没有事务
         * 没有人管理事务就表示没有事务
         */
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        // ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(); // conn.setAutoCommit(false)

        // 开启自动提交，表示没有开启事务，因为这种方式不会执行connection.setAutoCommit(false)
        // 在jdbc事务中，没有执行connection.setAutoCommit(false)，那么autoCommit就是true
        // 如果autoCommit是true，就表示没有开启事务，只要执行任意一条DML语句就提交一次
        // 这种方式是不建议的，因为没有开启事务
        // SqlSession sqlSession = sqlSessionFactory.openSession(true); // conn.setAutoCommit(false)

        // conn preparedStatement
        int count = sqlSession.insert("insertCar");
        System.out.println("count = " + count);

        sqlSession.commit(); // conn.commit()
    }
}
