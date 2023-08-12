package org.god.ibatis.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.god.ibatis.utils.Resources;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.*;

/**
 * Package: org.god.ibatis.core
 * Date: 2023/8/4 8:54
 * Description: null
 */
public class SqlSessionFactoryBuilder {

    public sqlSessionFactory build(InputStream in) {
        sqlSessionFactory sqlSessionFactory = null;
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            Element environments = (Element) document.selectSingleNode("/configuration/environments");
            String defaultId = environments.attributeValue("default");
            Element environment = (Element) document.selectSingleNode("/configuration/environments/environment[@id='" + defaultId + "']");
            Element transactionElt = environment.element("transactionManager");
            Element dataSourceElt = environment.element("dataSource");
            DataSource dataSource = getDataSource(dataSourceElt);
            Transaction transaction = getTransaction(transactionElt, dataSource);
            List<String> sqlMapperXMLPathList = new ArrayList<>();
            List<Node> nodes = document.selectNodes("//mapper");
            nodes.forEach(node -> {
                Element mapper = (Element) node;
                String resource = mapper.attributeValue("resource");
                sqlMapperXMLPathList.add(resource);
            });
            Map<String, MappedStatement> mappedStatements = getMappedStatements(sqlMapperXMLPathList);
            sqlSessionFactory = new sqlSessionFactory(transaction, mappedStatements);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

    private Map<String, MappedStatement> getMappedStatements(List<String> sqlMapperXMLPathList) {
        Map<String, MappedStatement> mappedStatements = new HashMap<>();
        sqlMapperXMLPathList.forEach(sqlMapperXMLPath -> {
            try {
                SAXReader reader = new SAXReader();
                Document document = reader.read(Resources.getResourceAsStream(sqlMapperXMLPath));
                Element mapperElt = (Element) document.selectSingleNode("mapper");
                String namespace = mapperElt.attributeValue("namespace");
                List<Element> elements = mapperElt.elements();
                elements.forEach(element -> {
                    String id = element.attributeValue("id");
                    String sqlId = namespace + "." + id;
                    String resultType = element.attributeValue("resultType");
                    String sql = element.getTextTrim();
                    MappedStatement mappedStatement = new MappedStatement(sql, resultType);
                    mappedStatements.put(sqlId, mappedStatement);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return mappedStatements;
    }

    private Transaction getTransaction(Element transactionElt, DataSource dataSource) {
        Transaction transaction = null;
        String type = transactionElt.attributeValue("type").trim().toUpperCase();
        switch (type) {
            case Const.JDBC_TRANSACTION:
                transaction = new JdbcTransaction(dataSource, false); // 默认开启事务，需要手动提交
                break;
            case Const.MANAGED_TRANSACTION:
                transaction = new ManagedTransaction();
                break;
        }
        return transaction;
    }

    private DataSource getDataSource(Element dataSourceElt) {
        DataSource dataSource = null;
        Map<String, String> map = new HashMap<>();
        String type = dataSourceElt.attributeValue("type").trim().toUpperCase();
        List<Element> propertyElts = dataSourceElt.elements("property");
        propertyElts.forEach(propertyElt -> {
            String name = propertyElt.attributeValue("name");
            String value = propertyElt.attributeValue("value");
            map.put(name, value);
        });
        switch (type) {
            case Const.UN_POOLED_DATASOURCE:
                dataSource = new UnPooledDataSource(map.get("driver"), map.get("url"), map.get("username"), map.get("password"));
                break;
            case Const.POOLED_DATASOURCE:
                dataSource = new PooledDataSource();
                break;
            case Const.JNDI_DATASOURCE:
                dataSource = new JNDIDataSource();
                break;
        }
        return dataSource;
    }

}
