package com.ramendu.levelTwo;

public class RunnableExample {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside anon class thread");
            }
        });
        thread.start();

        /**
         * Thread constructor expects a class of Runnable interface
         * Runnable interface has just one abstract method, perfect candidate for target of a lambda expression
         * () since there is no args to the run method
         * -> as the lambda operator
         */
        Thread lambdaThread = new Thread(() -> System.out.println("Inside lambda thread"));
        lambdaThread.start();
    }

}
