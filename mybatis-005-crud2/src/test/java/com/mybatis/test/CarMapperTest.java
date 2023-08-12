package com.mybatis.test;

import com.mybatis.mapper.CarMapper;
import com.mybatis.pojo.Car;
import com.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/2 15:39
 * Description: null
 */
public class CarMapperTest {

    @Test
    public void testInsert(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null, "1234", "疯子兽医", 1.0, "2019-8-3", "新能源");
        int count = mapper.insert(car);
        System.out.println("count = " + count);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
    @Test
    public void testDel(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteById(54L);
        System.out.println("count = " + count);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
    @Test
    public void testUpd(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(1L, "1234", "疯子兽医", 1.0, "2019-8-3", "新能源");
        mapper.update(car);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
    @Test
    public void testSelById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(1L);
        System.out.println("car = " + car);
    }
    @Test
    public void testSelAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        System.out.println("cars = " + cars);
    }

}
