package com.mybatis.mapper;

import com.mybatis.pojo.Car;
import com.mybatis.pojo.CarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarMapper {
    long countByExample(CarExample example);

    int deleteByExample(CarExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Car row);

    int insertSelective(Car row);

    List<Car> selectByExample(CarExample example);

    Car selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Car row, @Param("example") CarExample example);

    int updateByExample(@Param("row") Car row, @Param("example") CarExample example);

    int updateByPrimaryKeySelective(Car row);

    int updateByPrimaryKey(Car row);
}