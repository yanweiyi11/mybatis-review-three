package com.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/1 20:12
 * Description: null
 */
public class ConfigTest {

    @Test
    public void testDataSource() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // sqlSessionFactory对象，一个数据库一个
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        // SqlSession sqlSession1 = sqlSessionFactory.openSession();
        // sqlSession1.insert("car.insertCar");
        // sqlSession1.commit();
        // sqlSession1.close(); // 要测试连接池效果，关闭是需要的
        //
        // SqlSession sqlSession2 = sqlSessionFactory.openSession();
        // sqlSession2.insert("car.insertCar");
        // sqlSession2.commit();
        // sqlSession2.close(); // 要测试连接池效果，关闭是需要的

        for (int i = 0; i < 4; i++) {
            SqlSession sqlSession1 = sqlSessionFactory.openSession();
            sqlSession1.insert("car.insertCar");
        }

    }

    @Test
    public void testEnv() throws Exception {
        // 获取sqlSessionFactory
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 获取默认环境
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("car.insertCar");
        sqlSession.commit();
        sqlSession.close();

        // 获取指定环境
        SqlSessionFactory sqlSessionFactory1 = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"), "testDB");
        SqlSession sqlSession1 = sqlSessionFactory1.openSession();
        sqlSession1.insert("car.insertCar");
        sqlSession1.commit();
        sqlSession1.close();

    }

}
