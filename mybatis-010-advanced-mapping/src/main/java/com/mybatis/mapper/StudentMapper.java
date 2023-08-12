package com.mybatis.mapper;

import com.mybatis.pojo.Student;

import java.util.List;

/**
 * Package: com.mybatis.mapper
 * Date: 2023/8/3 13:50
 * Description: null
 */
public interface StudentMapper {
    List<Student> selectByCidStep2(Integer cid);
    Student selectByIdStep1(Integer sid);
    Student selectByIdAss(Integer id);
    Student selectById(Integer id);
}
