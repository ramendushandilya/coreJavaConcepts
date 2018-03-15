package com.ramendu.levelZero;


public class Greeter {

    public void greet(IGreeting greeting) {
        greeting.perform();
    }

    public static void main(String[] args) {

        // Normal Java 7 way
        IGreeting gr = new HelloWorldGreeting();
        Greeter greeter = new Greeter();
        greeter.greet(gr);

        //Using inner class
        IGreeting innerClass = new IGreeting() {
            @Override
            public void perform() {
                System.out.println("Hello world from inner class");
            }
        };
        greeter.greet(innerClass);

        /**
         * Interpretation of lambda expression for self understanding
         * IGreeting interface is the type of the lambda, all lambdas
         * in Java are coded to interfaces
         *
         * () because IGreeting interface only abstract method doesn't
         * use any parameter, hence to match signature
         *
         * -> is lambda sign
         *
         * No return types are used since compiler automatically knows the return type
         */
        IGreeting lambdaGreeting = () -> System.out.println("Hello world from lambda");
        greeter.greet(lambdaGreeting);
    }
}