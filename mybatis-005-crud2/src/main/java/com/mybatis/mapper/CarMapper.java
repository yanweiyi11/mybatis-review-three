package com.mybatis.mapper;

import com.mybatis.pojo.Car;

import java.util.List;

/**
 * Package: com.mybatis.mapper
 * Date: 2023/8/2 15:30
 * Description: null
 */
public interface CarMapper {

    int insert(Car car);

    int deleteById(Long id);

    int update(Car car);

    Car selectById(Long id);

    List<Car> selectAll();

}
