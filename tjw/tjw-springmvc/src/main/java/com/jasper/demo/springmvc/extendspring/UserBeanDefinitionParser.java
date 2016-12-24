package com.jasper.demo.springmvc.extendspring;

import com.jasper.demo.springmvc.bean.UserInfo;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected Class<?> getBeanClass(Element element) {
        return UserInfo.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        builder.addPropertyValue("id", element.getAttribute("id"));
        builder.addPropertyValue("name", element.getAttribute("name"));
        try {
            builder.addPropertyValue("birthday", DATE_FORMAT.parse(element.getAttribute("birthday")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
