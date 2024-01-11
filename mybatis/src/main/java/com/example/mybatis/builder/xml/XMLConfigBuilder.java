package com.example.mybatis.builder.xml;

import com.example.common.BizException;
import com.example.mybatis.builder.BaseBuilder;
import com.example.mybatis.session.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.Reader;
import java.util.List;

/**
 * @author wenzeng
 * @date 2023/12/23
 */
@Slf4j
public class XMLConfigBuilder extends BaseBuilder {

    private final Element root;

    public XMLConfigBuilder(Reader reader) {
        super(new Configuration());
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(reader);
            root = document.getRootElement();
        } catch (DocumentException e) {
            log.error("reade file error", e);
            throw new BizException("reade file error" + e.getMessage());
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
    private void mapperElement(Element mappers) {
        List<Element> elementList = mappers.elements("mapper");
        for (Element el: elementList) {
            if("package".equals(el.getName())) {
                configuration.addMappers(el.elementText("name"));
            }
        }
    }

}
