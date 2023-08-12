package com.mybatis.mapper;

import com.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Package: com.mybatis.mapper
 * Date: 2023/8/2 19:40
 * Description: null
 */
public interface StudentMapper {

    /**
     * Param注解
     */
    List<Student> selectByNameAndSex2(@Param("name") String name, @Param("sex") Character sex);

    /**
     * mybatis底层会把参数封装为一个Map集合
     * 集合的key为参数的下标：arg0,arg1|param1,param2...
     * 集合的value为参数的值
     */
    List<Student> selectByNameAndSex(String name, Character sex);

    int insertStudentByPojo(Student student);

    int insertStudentByMap(Map map);

    List<Student> selectById(Long id);

    List<Student> selectByName(String name);

    List<Student> selectByBirth(Date birth);

    List<Student> selectBySex(Character sex);

}
