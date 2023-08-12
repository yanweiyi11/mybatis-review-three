package com.mybatis.test;

import com.mybatis.pojo.Car;
import com.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: com.mybatis.test
 * Date: 2023/8/1 17:35
 * Description: null
 */
public class CarMapperTest {

    @Test
    public void testNameSpace() {
        /**
         * namespace命名空间的作用是防止id重复的
         * 本质上Mybatis的sqlId的完整写法：namespace.id
         */
        SqlSession sqlSession = SqlSessionUtil.openSession();

        // List<Car> selectAll = sqlSession.selectList("selectAll");
        // namespace.id
        List<Car> selectAll = sqlSession.selectList("a.selectAll");

        selectAll.forEach(System.out::println);

        sqlSession.close();
    }

    @Test
    public void selectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        List<Car> selectAll = sqlSession.selectList("selectAll");

        selectAll.forEach(System.out::println);

        sqlSession.close();
    }

    @Test
    public void selectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Object car = sqlSession.selectOne("selectById", 20);
        System.out.println("car = " + car);

        sqlSession.close();
    }

    @Test
    public void updateById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = new Car(21L, "1511", "北京现代", 30.0, "1999-1-20", "燃油车");
        int count = sqlSession.update("updateById", car);
        System.out.println("count = " + count);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        sqlSession.delete("deleteById", 22);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCarByPojo() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Car car = new Car(null, "1022", "上海大众", 30.0, "2020-11-30", "燃油车");

        int count = sqlSession.insert("insertCar", car);

        System.out.println("count = " + count);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCar() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Map<String, Object> map = new HashMap<>();
        map.put("carNum", "1111");
        map.put("brand", "比亚迪2");
        map.put("guidePrice", 10.0);
        map.put("produceTime", "2020-11-11");
        map.put("carType", "新能源");

        int count = sqlSession.insert("insertCar", map);

        System.out.println("count = " + count);

        sqlSession.commit();
        sqlSession.close();
    }

}
