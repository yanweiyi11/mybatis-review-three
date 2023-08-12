package com.mybatis.mapper;

import com.mybatis.pojo.Car;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Package: com.mybatis.mapper
 * Date: 2023/8/3 8:59
 * Description: null
 */
public interface CarMapper {
    Car selectById2(Long id);

    int insertClazz(@Param("cid") Integer cid, @Param("cname") String cname);

    Car selectById(Long id);

   /* Long selectTotal();

    List<Car> selectAllByTF();

    List<Car> selectAllByRltMap();

    @MapKey("id") // 将查询结果的id值作为整个大map的key
    Map<Long, Map<String, Object>> selectAllRetMap();

    List<Map> selectAllReturnListMap();

    Map selectByIdReturnMap(Long id);

    List<Car> selectById2(Long id);

    Car selectByBrandLike(String brand);

    List<Car> selectAll();

    Car selectById(Long id);*/

}
