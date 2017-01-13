package test.jasper.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HelloWordAspect {
    @Pointcut(value = "execution(public void test.jasper.spring.aop.HelloWord.sayHello(..)) && args(message)")
    public void pointcutSayHello(String message) {
        System.out.println("Pointcut : " + message);
    }

    @Before("pointcutSayHello(message)")
    public void beforePointcut(String message) {
        System.out.println("beforePointcut : " + message);
    }

    // =======================================
    // --------------分割线-------------------
    // =======================================

    @Before(value = "execution(* test.jasper.spring.aop.HelloWord.*(..))")
    public void beforeSayHello(JoinPoint joinPoint) {

        System.out.println("Before :" + joinPoint.getArgs()[0]);
    }

    @After(value = "execution(public void test.jasper.spring.aop.HelloWord.sayHello(..)) && args(message)")
    public void afterSayHello(String message) {
        System.out.println("After : " + message);
    }

    @Around(value = "execution(public void test.jasper.spring.aop.HelloWord.sayHello(..))")
    public void aroundSayHello(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around Before !! ");
        joinPoint.proceed();
        System.out.println("Around After !! ");
    }

    @AfterThrowing(value = "execution(public void test.jasper.spring.aop.HelloWord.sayHello(..))", throwing = "ex")
    public void afterThrowingSayHello(Exception ex) {
        System.out.println("After Throwing : " + ex.getMessage());
    }

    @AfterReturning(value = "execution(public void test.jasper.spring.aop.HelloWord.sayHello(..))", returning = "reval")
    public void afterReturningSayHello(String reval) {
        System.out.println("After Returning : " + reval);
    }
}