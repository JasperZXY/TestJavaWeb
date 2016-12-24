package com.jasper.demo.springmvc.extendspring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class JasperBeanNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
        registerBeanDefinitionParser("animal", new AnimalBeanDefinitionParser());
    }
}
