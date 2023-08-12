package com.mybatis.test;

import com.mybatis.mapper.CarMapper;
import com.mybatis.pojo.Car;
import com.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/3 11:01
 * Description: null
 */
public class CarMapperTest {
    @Test
    public void insertBatch(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        List<Car> cars = new ArrayList<>();
        cars.add(new Car(null, "1001", "car1", 31.0, "2020-11-11", "燃油车"));
        cars.add(new Car(null, "1002", "car2", 32.0, "2020-11-12", "燃油车"));
        cars.add(new Car(null, "1002", "car3", 33.0, "2020-11-13", "燃油车"));

        mapper.insertBatch(cars);

        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void deleteByIds(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        Long[] ids = {20L, 23L};
        mapper.deleteByIds(ids);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectByChoose() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        // List<Car> cars = mapper.selectByChoose("台铃", 1.0, "电瓶车");
        // List<Car> cars = mapper.selectByChoose("", 1.0, "电瓶车");
        // List<Car> cars = mapper.selectByChoose("", null, "电瓶车");
        List<Car> cars = mapper.selectByChoose("", null, "");
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void updateBySet() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(19L, null, "台铃", null, null, null);

        mapper.updateBySet(car);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(19L, null, "台铃", null, null, "电瓶车");
        mapper.updateById(car);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectByMultiConditionWithTrim() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        List<Car> cars = mapper.selectByMultiConditionWithTrim("北京现代", null, "");
        // List<Car> cars = mapper.selectByMultiConditionWithTrim("", null, "");

        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void selectByMultiConditionWithWhere() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        // List<Car> cars = mapper.selectByMultiConditionWithWhere("北京现代", 25.0, "燃油车");
        // List<Car> cars = mapper.selectByMultiConditionWithWhere("", null, "");
        // List<Car> cars = mapper.selectByMultiConditionWithWhere("", 25.0, "燃油车");
        List<Car> cars = mapper.selectByMultiConditionWithWhere("北京现代", null, "");

        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void selectByMultiCondition() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        // List<Car> cars = mapper.selectByMultiCondition("北京现代", 25.0, "燃油车");
        // List<Car> cars = mapper.selectByMultiCondition("", null, "");
        // List<Car> cars = mapper.selectByMultiCondition("", 25.0, "燃油车");
        List<Car> cars = mapper.selectByMultiCondition("北京现代", null, "");

        cars.forEach(System.out::println);
        sqlSession.close();
    }
}
