package com.mybatis.mapper;

import com.mybatis.pojo.Clazz;

/**
 * Package: com.mybatis.mapper
 * Date: 2023/8/3 13:50
 * Description: null
 */
public interface ClazzMapper {
    Clazz selectByStep1(Integer cid);
    Clazz selectByCollection(Integer id);
    Clazz selectByIdStep2(Integer id);
}
