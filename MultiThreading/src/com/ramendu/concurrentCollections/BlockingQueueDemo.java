package com.ramendu.concurrentCollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue is an interface that represents a Queue that is thread safe
 * put() puts the item to the BQ
 * take() takes away the item from the BQ
 */

public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();
    }

}

// Producer class adds items to the queue
class Producer implements Runnable{
    int count;
    private BlockingQueue<Integer> producer;

    public Producer(BlockingQueue<Integer> producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                producer.put(count);
                System.out.println("Putting item to the queue = "+count);
                count++;
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Consumer class takes items from the queue
class Consumer implements Runnable {
    private BlockingQueue<Integer> consumer;

    public Consumer(BlockingQueue<Integer> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int removed = consumer.take();
                System.out.println("Taking out from the queue = "+removed);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}