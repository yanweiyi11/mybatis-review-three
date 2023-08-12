package com.mybatis.test;

import com.mybatis.mapper.CarMapper;
import com.mybatis.pojo.Car;
import com.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/3 9:01
 * Description: null
 */
public class CarMapperTest {
    @Test
    public void selectById2() throws Exception {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession1 = sessionFactory.openSession();
        SqlSession sqlSession2 = sessionFactory.openSession();

        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);

        Car car1 = mapper1.selectById2(21L);
        System.out.println(car1);

        sqlSession1.close();

        Car car2 = mapper2.selectById2(21L);
        System.out.println(car2);

        // sqlSession1.close();
        sqlSession2.close();
    }

    @Test
    public void selectByIdB() throws Exception {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession1 = sessionFactory.openSession();
        SqlSession sqlSession2 = sessionFactory.openSession();

        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        Car car1 = mapper1.selectById(21L);
        System.out.println(car1);

        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);
        Car car2 = mapper2.selectById(21L);
        System.out.println(car2);

        sqlSession1.close();
        sqlSession2.close();
    }

    @Test
    public void selectByIdA() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        CarMapper mapper1 = sqlSession.getMapper(CarMapper.class);
        Car car1 = mapper1.selectById(21L);
        System.out.println(car1);

        // sqlSession.clearCache();

        mapper1.insertClazz(1003, "大数据");

        CarMapper mapper2 = sqlSession.getMapper(CarMapper.class);
        Car car2 = mapper2.selectById(21L);
        System.out.println(car2);

        sqlSession.commit();
        sqlSession.close();
    }

    /*@Test
    public void testSelectTotal(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long total = mapper.selectTotal();
        System.out.println(total);
        sqlSession.close();
    }

    @Test
    public void testSelectAllByTF() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByTF();
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectAllByRltMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByRltMap();
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectAllRetMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Map<Long, Map<String, Object>> maps = mapper.selectAllRetMap();
        System.out.println(maps);
        sqlSession.close();
    }

    @Test
    public void testSelectAllReturnListMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Map> maps = mapper.selectAllReturnListMap();
        maps.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByIdReturnMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Map map = mapper.selectByIdReturnMap(19L);
        System.out.println("map = " + map);
        sqlSession.close();
    }

    @Test
    public void testSelectById2() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectById2(19L);
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByBrand() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectByBrandLike("北京现代");
        System.out.println("car = " + car);
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(19L);
        System.out.println("car = " + car);
        sqlSession.close();
    }*/
}
