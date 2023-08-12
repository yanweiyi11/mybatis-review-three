package com.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Package: com.mybatis.utils
 * Date: 2023/8/1 17:12
 * Description: Mybatis工具类
 */
public class SqlSessionUtil {
    /**
     * 工具类的构造方法一般都是私有化的
     * 工具类中所有的方法都是静态的，直接采用类名调用，不需要new对象
     * 为了防止new对象，构造方法私有化
     */

    private static SqlSessionFactory sqlSessionFactory;

    private SqlSessionUtil() {}

    // 类加载时执行
    // 工具类在进行第一次加载时，解析mybatis-config.xml文件，创建SQL Session Factory对象
    static {
        try {
            // 一个sqlSessionFactory对应一个environment
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取会话对象
     *
     * @return 会话对象
     */
    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }

}
