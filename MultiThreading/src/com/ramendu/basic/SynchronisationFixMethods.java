package com.ramendu.basic;

public class SynchronisationFixMethods {

    private static int counter = 0;

    private static void process() {

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100000; i++) {
                    increment();
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100000 ; i++) {
                    increment();
                }
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //When we make the method synchronised only one thread could make use of it at one time
    private synchronized static void increment() {
        ++counter;
    }

    public static void main(String[] args) {
        process();
        System.out.println("Counter = "+counter);
    }

}
