package com.jasper.demo.springmvc.extendspring;

import com.jasper.demo.springmvc.bean.Animal;
import com.jasper.demo.springmvc.bean.UserInfo;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class AnimalBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Animal.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        builder.addPropertyValue("id", element.getAttribute("id"));
        builder.addPropertyValue("type", element.getAttribute("type"));
        builder.addPropertyValue("name", element.getAttribute("name"));
    }
}
