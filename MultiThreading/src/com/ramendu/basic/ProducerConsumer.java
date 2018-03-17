package com.ramendu.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates a basic use case of producer consumer problem
 * It has two methods one producer other consumer which operate on a common list
 * Producer adds elements to the list, consumer eats it up
 * Producer adds elements till a particular threshold FULL
 * Consumer eats up till a particular threshold EMPTY
 */
public class ProducerConsumer {

    private List<Integer> list = new ArrayList<>();
    private final int FULL = 5;
    private final int EMPTY = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {

        //It's important to synchronise of a same lock for both producer and consumer
        synchronized (lock) {
            while (true) {
                //If the list is filled up, goto waiting state
                if(list.size() == FULL) {
                    System.out.println("Waiting for consumer to consume items from the list");
                    //Before going to the waiting state, the notify has been called already, so wait will be called
                    //and so will be the notify, signaling the waiting consumer thread that it can now start eating
                    lock.wait();
                } else {
                    //Otherwise keep on adding to the list
                    System.out.println("Adding to the pool = "+value);
                    list.add(value);
                    value++;
                    //Notify is called and since it happens to be a while loop other statements will keep on executing
                    //till a wait is not called or something happens to kill the loop
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {
        //It's also synchronised on the same lock
        synchronized (lock) {
            while (true) {
                //If the list is empty ask the producer to produce and enter the waiting state
                if(list.size() == EMPTY) {
                    System.out.println("Waiting for the producer to produce items to the list");
                    lock.wait();
                } else {
                    System.out.println("Consuming from the pool = "+--value);
                    //value--;
                    list.remove(value);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public static void main(String[] args) {

        ProducerConsumer demo = new ProducerConsumer();

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.consumer();
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