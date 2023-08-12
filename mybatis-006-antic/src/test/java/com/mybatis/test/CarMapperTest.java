package com.mybatis.test;

import com.mybatis.mapper.CarMapper;
import com.mybatis.pojo.Car;
import com.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/2 16:11
 * Description: null
 */
public class CarMapperTest {

    @Test
    public void testInsertCarUserGeneratedKeys(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        Car car = new Car(null, "9991", "凯美瑞", 30.0, "2020-11-11", "燃油车");

        mapper.insertCarUserGeneratedKeys(car);

        sqlSession.commit();
        sqlSession.close();

        System.out.println("car = " + car);
    }

    @Test
    public void testSelByBraLK(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByBrandLike("北京现代");
        cars.forEach(System.out::println);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDelBat(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteBatch("1, 2");
        System.out.println("count = " + count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelByCT(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars =  mapper.selectByCarType("新能源");
        cars.forEach(System.out::println);
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAllAsc(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars =  mapper.selectAllByAscOrDesc("asc");
        cars.forEach(System.out::println);
        SqlSessionUtil.close(sqlSession);
    }

}
