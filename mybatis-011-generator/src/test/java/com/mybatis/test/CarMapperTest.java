package com.mybatis.test;

import com.mybatis.mapper.CarMapper;
import com.mybatis.pojo.Car;
import com.mybatis.pojo.CarExample;
import com.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/3 18:50
 * Description: null
 */
public class CarMapperTest {
    @Test
    public void test3() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        sqlSession.close();
    }
    @Test
    public void test2() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        sqlSession.close();
    }
    @Test
    public void test1() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        // Car car = mapper.selectByPrimaryKey(19L);
        // System.out.println("car = " + car);

        // List<Car> cars = mapper.selectByExample(null);
        // cars.forEach(System.out::println);

        // QBC风格:一种查询方式,比较面向对象,看不到sql语句
        CarExample carExample = new CarExample();
        carExample.createCriteria().andBrandLike("%北京现代%");
        carExample.or().andCarTypeEqualTo("电瓶车");
        List<Car> cars = mapper.selectByExample(carExample);
        cars.forEach(System.out::println);

        sqlSession.close();
    }
    /*@Test
    public void test2(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        cars.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void test1(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        mapper.deleteByPrimaryKey(56L);
        sqlSession.commit();
        sqlSession.close();
    }*/
}
