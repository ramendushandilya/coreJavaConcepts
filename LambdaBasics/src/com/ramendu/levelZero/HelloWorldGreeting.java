package com.ramendu.levelZero;

public class HelloWorldGreeting implements IGreeting {

    @Override
    public void perform() {
        System.out.println("Hello world!");
    }
}
