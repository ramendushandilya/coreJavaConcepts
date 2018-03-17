package com.ramendu.concurrentCollections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LatchDemo {

    /**
     * This is used to synchronise or more tasks by forcing them to wait for the completion of a set of operations being
     * performed by other tasks
     *
     * - You give an initial count to the CountDownLatch object, and any task that calls wait() on that object will
     *   block until the count reaches zero
     *
     * - Other tasks may call countDown() on the object to reduce the count, presumably when a task finishes its job
     * - CountDownLatch value can't be reset, if you need something that could be reset use cyclic barrier
     *
     * - The tasks that call CountDown() are not blocked when they make that call, only the call to await() is blocked
     *   is blocked until the count reaches zero
     *
     * - A typical use is to divide a problem to N independently solvable tasks and create CountDownLatch with value of
     *   N. When each task is finished it calls countDown() on the latch. Tasks waiting for the problem to be solved
     *   call await() on the latch to hold themselves back until it is completed
     *
     * - Example use case : You want to trigger an action after 10000 users download an image
     */


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //5 is passed to the CountDownLatch constructor indicating that 5 times countdown can be done before threads
        //are passed through
        CountDownLatch latch = new CountDownLatch(5);

        for(int i = 0 ; i < 5 ; i++) {
            executorService.execute(new Worker(i+1, latch));
        }
        try {
            //Causes the current thread to wait until the count has gone to zero
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All the pre processing done");
        executorService.shutdown();
    }
}

class Worker implements Runnable {

    private int id;
    private CountDownLatch latch;

    public Worker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }


    @Override
    public void run() {
        doWork();
        latch.countDown();
        System.out.println("Current count = "+latch.getCount());
    }

    private void doWork() {

        System.out.println("Thread with id "+ this.id +" started working...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}