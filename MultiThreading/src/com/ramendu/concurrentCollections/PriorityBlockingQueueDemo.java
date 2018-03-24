package com.ramendu.concurrentCollections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * It implements the BlockingQueue interface
 * If the priority is to be defined on the Object, then it should implement Comparable interface
 * and override the concerned method
 *
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new PriorityBlockingQueue<>();

        FirstWorker worker = new FirstWorker(queue);
        SecondWorker worker1 = new SecondWorker(queue);

        new Thread(worker).start();
        new Thread(worker1).start();
    }

}

class FirstWorker implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public FirstWorker(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put("F");
            blockingQueue.put("B");
            blockingQueue.put("Z");
            blockingQueue.put("D");
            blockingQueue.put("E");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorker implements Runnable {
    private BlockingQueue<String> blockingQueue;

    public SecondWorker(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}