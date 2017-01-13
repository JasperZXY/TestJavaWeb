package test.jasper.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class HelloWord implements IHelloWord {

    public void sayHello(String message) {
        System.out.println(message);
    }
}