package com.example.springframe.beans.strategy;

import cn.hutool.core.text.CharSequenceUtil;
import com.example.springframe.beans.BeanDefinition;
import com.example.springframe.beans.BeanReReference;
import com.example.springframe.beans.PropertyValue;
import com.example.springframe.beans.PropertyValues;
import com.example.springframe.beans.config.BeanDefinitionRegistry;
import com.example.springframe.core.io.Resource;
import com.example.springframe.core.io.ResourceLoader;
import com.example.springframe.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/2
 */
@Slf4j
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ParserConfigurationException | SAXException | ClassNotFoundException e) {
            log.error("loadBeanDefinitions -- load error ", e);
            throw new BizException("IOException parsing XML document from " + resource);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) {
        for(String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    /**
     * 解析资源
     *
     * @param inputStream
     */
    public void doLoadBeanDefinitions(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputStream);

        Element root = doc.getDocumentElement();
        NodeList childrenNodes = root.getChildNodes();

        for (int i = 0; i < childrenNodes.getLength(); i++) {
            // 判断元素
            if (!(childrenNodes.item(i) instanceof Element) || !"bean".equals(childrenNodes.item(i).getNodeName())) {
                continue;
            }
            // 解析标签
            Element bean = (Element) childrenNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            // 增加对initMethodName,destroyMethodName的获取
            String initMethodName = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");

            // 获取Class
            Class<?> clazz = Class.forName(className);
            String beanName = CharSequenceUtil.isNotEmpty(id) ? id : name;
            if (CharSequenceUtil.isEmpty(beanName)) {
                beanName = CharSequenceUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz, new PropertyValues());
            beanDefinition.setDestroyMethodName(destroyMethodName);
            beanDefinition.setInitMethodName(initMethodName);
            // 读取配置的bean属性
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                // 判断元素
                if (!(bean.getChildNodes().item(j) instanceof Element)
                        || !"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                    continue;
                }
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                Object value = CharSequenceUtil.isNotEmpty(attrRef) ? new BeanReReference(attrRef) : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            BeanDefinitionRegistry beanDefinitionRegistry = getRegistry();
            if (beanDefinitionRegistry.containsBeanDefinition(beanName)) {
                throw new BizException("Duplicate beanName[" + beanName + "] is not allowed");
            }

            // 注册beanDefinition
            beanDefinitionRegistry.registryBeanDefinition(beanName, beanDefinition);
        }
    }
}
