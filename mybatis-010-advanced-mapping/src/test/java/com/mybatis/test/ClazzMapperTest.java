package com.mybatis.test;

import com.mybatis.mapper.ClazzMapper;
import com.mybatis.pojo.Clazz;
import com.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/3 13:52
 * Description: null
 */
public class ClazzMapperTest {
    @Test
    public void selectByStep1(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByStep1(1001);

        // System.out.println("clazz = " + clazz);

        System.out.println(clazz.getCname());

        sqlSession.close();
    }
    @Test
    public void selectByCollection(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByCollection(1000);
        System.out.println("clazz = " + clazz);
        sqlSession.close();
    }
}
