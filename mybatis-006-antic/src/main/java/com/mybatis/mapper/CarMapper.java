package com.mybatis.mapper;

import com.mybatis.pojo.Car;

import java.util.List;

/**
 * Package: com.mybatis.mapper
 * Date: 2023/8/2 16:09
 * Description: null
 */
public interface CarMapper {

    List<Car> selectByCarType(String carType);

    List<Car> selectAllByAscOrDesc(String ascOrDesc);

    int deleteBatch(String ids);

    List<Car> selectByBrandLike(String brand);

    int insertCarUserGeneratedKeys(Car car);

}
