package com.jasper.service;

/**
 * @author Jasper.Zhong
 */
public class MathServiceImpl implements MathService {
    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }
}
