package com.example.springframe;

import com.example.springframe.beans.strategy.XmlBeanDefinitionReader;
import com.example.springframe.beans.support.DefaultListableBeanFactory;
import com.example.springframe.context.ClassPathXmlApplicationContext;
import com.example.springframe.core.io.DefaultResourceLoader;
import com.example.springframe.core.io.Resource;
import com.example.springframe.service.MyBeanFactoryPostProcessor;
import com.example.springframe.service.MyBeanPostProcessor;
import com.example.springframe.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/5
 */
@SpringBootTest
public class ResourceLoaderTest {

    private DefaultResourceLoader defaultResourceLoader;

    @PostConstruct
    public void init() {
        defaultResourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testClassPath() throws IOException {
        String location = "classpath:application.yml";
        Resource resource = defaultResourceLoader.getResource(location);
        InputStream inputStream = resource.getInputStream();
        Collection<String> contents = IOUtil.readLines(inputStream);
        contents.forEach(System.out::println);
    }

    @Test
    public void testResourceLoader() {
        String location = "classpath:bean.xml";
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(location);

        UserServiceImpl userService = (UserServiceImpl) beanFactory.getBean("userServiceImpl");
        userService.insertUser(null);
    }

    @Test
    public void testBeanFactoryContext() {
        // 创建一个beanfactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 加载配置文件
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:bean.xml");

        // 注册完BeanDefinition之后对BeanDefinition进行修改
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 添加bean对象的处理器 实例化bean对象的过程中执行
        MyBeanPostProcessor postProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(postProcessor);

        // 实例化bean
        UserServiceImpl userService = (UserServiceImpl) beanFactory.getBean("userServiceImpl");
        System.out.println(userService.getUserInfo());

    }


    @Test
    public void testInitDestroy() {
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:bean.xml");
        context.registerShutdownHook();

        // 2. 获取bean对象的调用方法
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userServiceImpl");
        String result = userService.getUserInfo();
        System.out.println(result);
    }
}
