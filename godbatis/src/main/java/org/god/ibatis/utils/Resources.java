package org.god.ibatis.utils;

import java.io.InputStream;

/**
 * Package: org.god.ibatis.utils
 * Date: 2023/8/4 8:49
 * Description: 资源工具类 - 完成类路径中资源的加载
 */
public class Resources {
    /**
     * 工具类的构造方法都是建议私有化的，因为工具类中的方法都是静态的，不需要创建对象就能调用
     * 为了避免new对象，所以构造方法私有化，这是一种编程习惯
     */
    private Resources(){}

    /**
     * 从类路径当中，加载资源
     *
     * @param resource 类路径的文件
     * @return 指向资源文件的输入流
     */
    public static InputStream getResourceAsStream(String resource){
        return ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
    }
}
