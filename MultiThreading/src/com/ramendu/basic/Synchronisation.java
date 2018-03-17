package com.ramendu.basic;

/**
 * Here count is a common variable shared across two threads one and two
 * Both the threads take turn to increment the count variable 1000 times
 * Since the increment operation is not an atomic operation the result
 * is inconsistent, hence we need to make use of synchronisation to fix
 * this issue
 */
public class Synchronisation {

    private static int count = 0;

    public static void process() {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 1000 ; i++) {
                    ++count;
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 1000 ; i++) {
                    ++count;
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
        System.out.println(count);
    }

    public static void main(String[] args) {
        process();
    }

}
