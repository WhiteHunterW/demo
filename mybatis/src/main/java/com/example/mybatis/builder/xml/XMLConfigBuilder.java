package com.example.mybatis.builder.xml;

import com.example.common.BizException;
import com.example.mybatis.builder.BaseBuilder;
import com.example.mybatis.mapping.MappedStatement;
import com.example.mybatis.session.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

/**
 * @author wenzeng
 * @date 2023/12/23
 */
@Slf4j
public class XMLConfigBuilder extends BaseBuilder {

    private final Element root;

    public XMLConfigBuilder(Reader reader) throws IOException {
        super(new Configuration());
        SAXReader saxReader = new SAXReader(false);
        InputSource stream;
        try {
            saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            stream = new InputSource(reader);
            Document document = saxReader.read(stream);
            root = document.getRootElement();
        } catch (DocumentException | SAXException e) {
            log.error("reade file error", e);
            throw new BizException("reade file error" + e.getMessage());
        } finally {
            reader.close();
        }
    }


    /**
     * 获取xml文件中的mappers节点解析
     * @return
     */
    public Configuration parse() {
        try {
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            log.error("parse xml mappers error", e);
            throw new BizException("parse xml mappers error" + e.getMessage());
        }
        return configuration;
    }

    /**
     * 解析xml文件中的mappers节点
     * 存入configuration
     * @param mappers
     */
    private void mapperElement(Element mappers) throws ClassNotFoundException, DocumentException, SAXException {
        List<Element> elementList = mappers.elements("mapper");
        for (Element el: elementList) {
            if("package".equals(el.getName())) {
                configuration.addMappers(el.elementText("name"));
            } else {
                String mapperClass = el.attributeValue("class");
                if(null != mapperClass){
                    Class<?> mapperInterface = ClassLoader.getSystemClassLoader().loadClass(mapperClass);
                    configuration.addMapper(mapperInterface);
                    // 加载xml文件
                    String xmlResource = mapperInterface.getName().substring( mapperInterface.getName().lastIndexOf(".") + 1) + ".xml";
                    //InputStream inputStream = mapperInterface.getResourceAsStream("/" + xmlResource);
                    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mapper/" + xmlResource);
                    // 解析SQL 生成MappedStatement 并放入configuration
                    if (inputStream != null) {
                        SAXReader reader = new SAXReader(false);
                        reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
                        Document document = reader.read(inputStream);
                        Element rootElement = document.getRootElement();
                        List<Element> nodes = rootElement.selectNodes("//select|insert|update|delete");
                        for (Element child : nodes){
                            MappedStatement statement = new MappedStatement();
                            statement.setSql(child.getTextTrim());
                            statement.setId(child.attribute("id").getValue());
                            configuration.addMappedStatement(statement);
                        }
                    }
                }
            }
        }
    }

}
