package com.ramendu.basic;

/**
 * In this example we have two methods produce and consume
 * The lock acquired is class based lock using this keyword
 * Two threads are initialised
 * One thread runs the produce method, another thread runs the consume method
 * Based on their wait and notify pattern two threads communicate
 */
public class WaitNotifyDemo {

    public void produce() throws InterruptedException {
        //this is used to acquire the class level lock
        synchronized (this) {
            System.out.println("In the producer method");
            System.out.println("Producer will wait and take rest by calling wait method");
            //After doing it's logic the produce thread waits for another thread
            wait();
            System.out.println("Producer method awakened");
        }
    }

    public void consume() throws InterruptedException {
        //Thread sleeps for 3 seconds to show some time gap between the calls
        Thread.sleep(3000);
        synchronized (this) {
            System.out.println("In the consumer method");
            //Once the consumer thread is done doing its work it calls notify to signal the waiting thread that it's done
            //On notify any of the waiting threads could be invoked, there is no guarantee of which one
            notify();

            //Once the notify is called the thread will execute the remaining logic if any in the scope then the control
            //will be transferred to another thread
            Thread.sleep(2000);
            System.out.println("Consumer slept for 2 seconds, now returning...");
        }
    }

    public static void main(String[] args) {

        WaitNotifyDemo demo = new WaitNotifyDemo();

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.consume();
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
