package com.mybatis.test;

import com.mybatis.mapper.StudentMapper;
import com.mybatis.pojo.Student;
import com.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/3 13:52
 * Description: null
 */
public class StudentMapperTest {
    @Test
    public void selectByIdStep1() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdStep1(4);
        System.out.println("student = " + student);
        sqlSession.close();
    }

    @Test
    public void selectByIdAss() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdAss(4);
        System.out.println("student = " + student);
        sqlSession.close();
    }

    @Test
    public void selectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectById(1);
        System.out.println("student = " + student);
        sqlSession.close();
    }
}
