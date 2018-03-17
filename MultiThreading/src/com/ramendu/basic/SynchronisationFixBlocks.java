package com.ramendu.basic;

/**
 * In this example, we have two different variables countOne and countTwo
 * Two threads want to access these
 * If we have a synchronised method or class level lock then two threads won't be able to access these simultaneously
 * Reason being both the variables are not dependent we want them to be used by threads as per their convenience
 * Hence, we use something called as synchronised blocks, now since the two variables are independent threads can
 * access them simultaneously and processing speed is improved
 */
public class SynchronisationFixBlocks {

    private static int countOne = 0;
    private static int countTwo = 0;

    //We are creating two objects which will be used as locks to make synchronised blocks
    private static Object lockOne = new Object();
    private static Object lockTwo = new Object();

    private static void incrementOne() {
        //If we use synchronized (SynchronisationFixBlocks.class) lock this is called class level lock, again it's
        //not good, since two threads working independently won't be able to access the count variables
        synchronized (lockOne) {
            countOne++;
        }
    }

    private static void incrementTwo() {
        synchronized (lockTwo) {
            countTwo++;
        }
    }

    private static void compute() {
        for(int i = 0 ; i < 100 ; i++) {
            incrementOne();
            incrementTwo();
        }
    }

    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
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
        System.out.println("count one = "+countOne+" count two = "+countTwo);
    }
}
