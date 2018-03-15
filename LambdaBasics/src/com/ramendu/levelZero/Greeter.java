package com.ramendu.levelZero;

public class Greeter {

    public void greet(IGreeting greeting) {
        greeting.perform();
    }

    public static void main(String[] args) {
        IGreeting gr = new HelloWorldGreeting();
        Greeter greeter = new Greeter();
        greeter.greet(gr);
    }
}