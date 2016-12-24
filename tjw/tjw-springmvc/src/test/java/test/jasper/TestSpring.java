package test.jasper;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.parsing.*;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.*;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class TestSpring {
    private static final String RESOURCE_PATH = "applicationContext-test.xml";

    @Test
    public void parseXML() {
        ClassPathResource resource = new ClassPathResource(RESOURCE_PATH);

        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);

        System.out.println("user1: " + JsonUtils.toString(xmlBeanFactory.getBean("user1")));
        System.out.println("bean_user: " + JsonUtils.toString(xmlBeanFactory.getBean("bean_user")));
        System.out.println("user_alias1:" + test.jasper.JsonUtils.toString(xmlBeanFactory.getBean("user_alias1")));
        System.out.println("user_alias2:" + test.jasper.JsonUtils.toString(xmlBeanFactory.getBean("user_alias2")));
        System.out.println("user_alias3:" + test.jasper.JsonUtils.toString(xmlBeanFactory.getBean("user_alias3")));

        /**
         * 自定义标签的使用。
         * 有点小问题，bean里面的id与spring里的id只能使用同一个，name属性也有这个问题。
         * 具体查看org.springframework.beans.factory.xml.AbstractBeanDefinitionParser中的ID_ATTRIBUTE与NAME_ATTRIBUTE
         * 注意需要在META-INF文件夹下定义配置文件。
         */
        System.out.println("User:" + JsonUtils.toString(xmlBeanFactory.getBean("2")));
        System.out.println("Animal:" + JsonUtils.toString(xmlBeanFactory.getBean("3")));

    }

    @Test
    public void testReaderEventListener() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.setEventListener(new ReaderEventListener() {
            @Override
            public void defaultsRegistered(DefaultsDefinition defaultsDefinition) {
                System.out.println("defaultsRegistered:" + JsonUtils.toString(defaultsDefinition));
            }

            @Override
            public void componentRegistered(ComponentDefinition componentDefinition) {
                System.out.println("componentRegistered:" + componentDefinition);
            }

            @Override
            public void aliasRegistered(AliasDefinition aliasDefinition) {
                System.out.println("aliasRegistered:" + JsonUtils.toString(aliasDefinition));
            }

            @Override
            public void importProcessed(ImportDefinition importDefinition) {
                System.out.println("importProcessed:" + JsonUtils.toString(importDefinition));
            }
        });
        xmlBeanDefinitionReader.setEnvironment(new StandardEnvironment());
        xmlBeanDefinitionReader.loadBeanDefinitions(RESOURCE_PATH);

        System.out.println(JsonUtils.toString(beanFactory.getBean("user1")));
    }

    @Test
    public void parseXMLImport() {
        ClassPathResource resource = new ClassPathResource(RESOURCE_PATH);
        BeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
        System.out.println("PropertyPlaceholderConfigurer:" + JsonUtils.toString(xmlBeanFactory.getBean("PropertyPlaceholderConfigurer")));
    }

    @Test
    public void getInputStream() throws Exception {
        ClassPathResource resource = new ClassPathResource(RESOURCE_PATH);
        InputStream is = resource.getInputStream();
        System.out.println(IOUtils.readLines(is));
        IOUtils.closeQuietly(is);

        is = TestSpring.class.getClassLoader().getResourceAsStream(RESOURCE_PATH);
        System.out.println(IOUtils.readLines(is));
        IOUtils.closeQuietly(is);
    }

    @Test
    public void document() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
//        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(this.getClass().getClassLoader().getResourceAsStream(RESOURCE_PATH));
        Document document = docBuilder.parse(inputSource);
        Element root = document.getDocumentElement();

        System.out.println("document:" + document);
        System.out.println("root:" + root);
        System.out.println("getNamespaceURI:" + root.getNamespaceURI());
        System.out.println("isDefaultNamespace:" + BeanDefinitionParserDelegate.BEANS_NAMESPACE_URI.equals(root.getNamespaceURI()));

        NodeList nodeList = root.getChildNodes();
        System.out.println("nodeList.getLength:" + nodeList.getLength());
        for (int i=0; i<nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.printf("nodeList %d\t NodeName:%s\t localName:%s\t NamespaceURI:%s\n", i, node.getNodeName(), node.getLocalName(), node.getNamespaceURI());
        }
    }

}
