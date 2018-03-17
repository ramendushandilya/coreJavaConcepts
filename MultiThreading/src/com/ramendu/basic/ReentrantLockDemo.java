package com.ramendu.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrant locks have the same behaviour as synchronised with certain differences
 * ReentrantLock is a class implementation of the Lock interface with added features
 * It can be declared as new ReentrantLock(boolean fairnessParameter)
 * When fairnessParameter is set to true then the longest waiting thread will get the lock
 * When fairnessParameter is set to false then there is not access order guaranteed
 * We have to use the try catch block when doing critical sections that may throw exceptions
 * unlock() should be called in the finally block
 * Indifferent from the synchronised block approach a lock could be unlocked anywhere in the program
 *
 */
public class ReentrantLockDemo {

    private static int counter = 0;
    private static Lock reentrantlock = new ReentrantLock();

    public static void increment() {

        //By calling the lock method of the reentrant lock, the lock is acquired by this critical section
        reentrantlock.lock();

        //Since the critical section may throw exceptions we surround under try cath
        try {
            for(int i = 0 ; i < 10000; i++) {
                counter++;
            }
        } finally {
            //In the finally block finally the lock is released
            reentrantlock.unlock();
        }
    }

    public static void main(String[] args) {

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
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
        System.out.println("Counter value = "+counter);
    }

}
