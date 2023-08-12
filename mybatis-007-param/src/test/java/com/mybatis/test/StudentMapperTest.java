package com.mybatis.test;

import com.mybatis.mapper.StudentMapper;
import com.mybatis.pojo.Student;
import com.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.javassist.expr.NewExpr;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/2 19:41
 * Description: null
 */
public class StudentMapperTest {
    @Test
    public void testSelectByNameAndSex() throws Exception {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByNameAndSex2("张三", '男');
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testInsertStudentByPojo() throws Exception {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        Student student = new Student(null, "王五", 10, 1.75, new Date(), '女');

        mapper.insertStudentByPojo(student);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertStudentByMap() throws Exception {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Map map = new HashMap();
        map.put("姓名", "赵六");
        map.put("年龄", 20);
        map.put("身高", 1.81);
        map.put("性别", '男');
        map.put("生日", new Date());
        mapper.insertStudentByMap(map);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectBySex() throws Exception {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectBySex('男');
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByBirth() throws Exception {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1988-05-27");
        List<Student> students = mapper.selectByBirth(date);
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByName(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByName("李四");
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectById(1L);
        students.forEach(System.out::println);
        sqlSession.close();
    }

}
