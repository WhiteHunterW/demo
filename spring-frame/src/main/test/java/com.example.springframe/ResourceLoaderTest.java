package com.example.springframe;

import com.example.springframe.beans.strategy.XmlBeanDefinitionReader;
import com.example.springframe.beans.support.DefaultListableBeanFactory;
import com.example.springframe.core.io.DefaultResourceLoader;
import com.example.springframe.core.io.Resource;
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
}
