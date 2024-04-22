package com.example.mybatis;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.example.mybatis.binding.MapperProxyFactory;
import com.example.mybatis.binding.MapperRegistry;
import com.example.mybatis.mapper.RoleMapper;
import com.example.mybatis.mapper.UserTestMapper;
import com.example.mybatis.session.SqlSession;
import com.example.mybatis.session.SqlSessionFactory;
import com.example.mybatis.session.SqlSessionFactoryBuilder;
import com.example.mybatis.session.defaults.DefaultSqlSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * @author wenzeng
 * @date 2023/12/17
 */
@Slf4j
public class TestMybatis {

    @Test
    public void testProxy() {
        MapperProxyFactory<UserTestMapper> factory = new MapperProxyFactory<>(UserTestMapper.class);
        HashMap<String, String> sqlSession = new HashMap<>(2);
        sqlSession.put("com.example.mybatis.mapper.UserTestMapper.queryUserName", "select name from user where uid = #{uid}");
        UserTestMapper userTestMapper = factory.newInstance(sqlSession);
        String nameTest = userTestMapper.queryUserName("2");
        log.info("名称查询测试 {}", nameTest);
    }

    /**
     * 测试 mapper映射器注册和使用
     */
    @Test
    public void test() {
        // 注册
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.example.mybatis.mapper");
        // 实例化SqlSession
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(null);
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        // 获取接口的代理类对象 执行接口方法
        UserTestMapper testMapper = registry.getMapper(UserTestMapper.class, sqlSession);
        RoleMapper roleMapper = registry.getMapper(RoleMapper.class, sqlSession);
        // 还不能执行SQL语句
        log.info("testMapper {}", testMapper.queryUserName("22"));
        log.info("roleMapper {}", roleMapper.getUserRole("22"));
    }

    @Test
    public void proxy() throws IOException, DocumentException, SAXException {
        /*
         * 加载配置文件做的事：
         * 通过SqlSessionFactoryBuilder构建SqlSessionFactory
         * 初始化Configuration
         * 通过XMLConfigBuilder解析xml文件：将解析到的SQL语句生成MappedStatement并放入Configuration
         * 注册Mapper：将mapper添加进MapperRegistry并放入Configuration
         *
         */
        String url = "/Users/wenzeng/Desktop/wz/workspace/project/xhiteam/demo/mybatis/src/main/resources/mybatis-test-config.xml";
        BufferedReader reader = new BufferedReader(new FileReader(url));
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        String string = roleMapper.getUserRole("1");
        System.out.printf(string);

        /*InputStream inputStream = ClassLoader.getSystemResourceAsStream(url);
        assert inputStream != null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.printf(line);
        }
        inputStream.close();
        bufferedReader.close();
        InputStream is = ClassLoader.getSystemResourceAsStream(url);
        SAXReader reader1 = new SAXReader(false);
        //reader1.setEntityResolver((publicId, systemId) -> new InputSource(new ByteArrayInputStream("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes())));
        reader1.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        Document document = reader1.read(is);
        Element element = document.getRootElement();
        List list = element.elements();
        System.out.println();
        System.out.printf(JSONUtil.toJsonStr(list));*/


    }

    @Test
    public void testDom() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    }
}
