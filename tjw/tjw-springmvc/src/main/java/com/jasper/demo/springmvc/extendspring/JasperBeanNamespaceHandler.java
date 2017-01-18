package com.jasper.demo.springmvc.extendspring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * spring自定义标签的实现
 */
public class JasperBeanNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
        registerBeanDefinitionParser("animal", new AnimalBeanDefinitionParser());
    }
}
