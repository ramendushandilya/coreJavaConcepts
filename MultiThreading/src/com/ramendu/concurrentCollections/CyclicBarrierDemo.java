package com.ramendu.concurrentCollections;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

    /**
     * A CyclicBarrier is used in situations when you want to create a group of tasks to perform work in parallel plus
     * wait until they are all finished before moving onto the next step
     * Where CountDownLatch is a one shot event, CyclicBarrier could be reused again ang again
     * CyclicBarrier has a barrier action, a runnable that will run automatically when the count reaches zero
     */

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(5);

        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All the tasks finished");
            }
        });

        for(int i = 0 ; i < 5; i++) {
            service.execute(new WorkerBarrier(i+1, barrier));
        }
        service.shutdown();
    }
}

class WorkerBarrier implements Runnable {

    private int id;
    private CyclicBarrier barrier;

    public WorkerBarrier(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        doSomeWork();
    }

    private void doSomeWork() {
        System.out.println("Thread with id = "+this.id+" starting the task...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread with id = "+this.id+" finished the task...");
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return ""+this.id;
    }
}
