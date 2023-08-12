package com.mybatis.bank.utils;

import org.apache.ibatis.javassist.*;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Package: com.mybatis.bank.utils
 * Date: 2023/8/2 13:50
 * Description: 可以动态生成DAO的实现类
 */
public class GenerateDaoProxy {

    /**
     * 生成dao接口实现类，并且将实现类的对象创建出来，并返回
     * @param daoInterface dao接口
     * @return dao接口实现类的实例化对象
     */
    public static Object generate(SqlSession sqlSession, Class daoInterface) {
        // 类池
        ClassPool pool = ClassPool.getDefault();
        // 制造类
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Proxy"); // 本质上是在内存中生成一个代理类
        // 制造接口
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());
        // 实现接口
        ctClass.addInterface(ctInterface);
        // 实现接口中所有的方法
        Method[] methods = daoInterface.getDeclaredMethods();
        Arrays.stream(methods).forEach(method ->{
            // 将method抽象方法实现
            try {
                StringBuilder methodCode = new StringBuilder();
                methodCode.append("public ");
                methodCode.append(method.getReturnType().getName());
                methodCode.append(" ");
                methodCode.append(method.getName());
                methodCode.append("(");
                // 需要方法的形参列表
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> parameterType = parameterTypes[i];
                    methodCode.append(parameterType.getTypeName());
                    methodCode.append(" ");
                    methodCode.append("arg").append(i);
                    if (i != parameterTypes.length - 1){
                        methodCode.append(",");
                    }
                }
                methodCode.append(")");
                methodCode.append("{");
                // 方法体
                methodCode.append("org.apache.ibatis.session.SqlSession sqlSession = com.mybatis.bank.utils.SqlSessionUtil.openSession();");
                // 需要知道是什么类型的sql语句
                String sqlId = daoInterface.getName() + "." + method.getName();
                SqlCommandType sqlCommandType = sqlSession.getConfiguration().getMappedStatement(sqlId).getSqlCommandType();
                if (sqlCommandType == SqlCommandType.INSERT) {

                }
                else if (sqlCommandType == SqlCommandType.DELETE) {

                }
                else if (sqlCommandType == SqlCommandType.UPDATE) {
                    methodCode.append("return sqlSession.update(\"").append(sqlId).append("\", arg0);");
                }
                else if (sqlCommandType == SqlCommandType.SELECT) {
                    methodCode.append("return (");
                    methodCode.append(method.getReturnType().getName());
                    methodCode.append(")");
                    methodCode.append("sqlSession.selectOne(\"").append(sqlId).append("\", arg0);");
                }
                methodCode.append("}");
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 创建对象
        Object obj = null;
        try {
            Class<?> clazz = ctClass.toClass();
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
