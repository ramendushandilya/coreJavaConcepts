package com.ramendu.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLocks {

    private static Lock reentrantLock = new ReentrantLock();

    //Condition interface provides various method similar to wait and notify
    private static Condition condition = reentrantLock.newCondition();

    private static void producer() throws InterruptedException {
        reentrantLock.lock();
        System.out.println("In the producer method");
        //await() method in Condition interface is similar to wait method and is used to make a thread wait
        condition.await();
        System.out.println("Back to the producer");
    }

    private static void consumer() throws InterruptedException {
        Thread.sleep(500);
        reentrantLock.lock();
        System.out.println("In the consumer method");
        //signal() method in Condition interface is similar to notify method and is used to make a thread notify other
        //waiting threads
        condition.signal();
        System.out.println("Leaving from the consumer");
        reentrantLock.unlock();
    }

    public static void main(String[] args) {

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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

}
