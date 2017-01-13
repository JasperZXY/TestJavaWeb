package test.jasper.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircleB {
    @Autowired
    private CircleA circleA;

    public CircleA getCircleA() {
        return circleA;
    }

    public void setCircleA(CircleA circleA) {
        this.circleA = circleA;
    }
}
