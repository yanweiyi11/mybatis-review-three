package com.mybatis.test.junit.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * Package: com.mybatis.test.junit.service
 * Date: 2023/8/1 16:04
 * Description: 单元测试类
 */
public class MathServiceTest { // 名字规范，要测试的类名 + Test

    // 一个业务方法对应一个测试方法
    // 方法名规范：以test开始，假设测试的方法是sum，这个测试的方法名就叫testSum

    private MathService mathService = new MathService();


    @Test // 被注解标注的方法就是一个单元测试方法
    public void testSum() {
        // 单元测试中有2个重要概念：
        // 1.实际值（被测方法真正执行结果）
        // 2.期望值（执行了这个业务方法之后，你期望的执行结果）
        // 获取实际值
        int actual = mathService.sum(1, 2);
        // 期望值
        int expected = 3;
        // int expected = 30;
        // 加断言进行测试;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSub() {
        int actual = mathService.sub(10, 5);
        int expected = 5;
        Assert.assertEquals(expected, actual);
    }


}
