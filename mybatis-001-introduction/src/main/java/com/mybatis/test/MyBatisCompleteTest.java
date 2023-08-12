package com.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/1 15:46
 * Description: 完整版的mybatis程序
 */
public class MyBatisCompleteTest {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            // 开启绘画（底层会开启事务）
            sqlSession = sqlSessionFactory.openSession();
            // 执行sql，处理业务
            int count = sqlSession.insert("insertCar");
            System.out.println("count = " + count);
            // 没有异常，提交事务，终至事务
            sqlSession.commit();
        } catch (Exception e) {
            // 回滚事务
            if (sqlSession != null){
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            // 关闭会话(释放资源)
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
