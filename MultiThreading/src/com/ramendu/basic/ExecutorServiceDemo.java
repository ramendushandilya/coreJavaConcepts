package com.ramendu.basic;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {

    /**
     *
     *  1). ExecutorService executor = Executors.newCachedThreadPool()
     *      - It returns an executor service that can dynamically reuse threads
     *      - Before starting a job it is going to check whether there are any threads that finished the job, reuse them
     *      - If there are no waiting threads it's going to create another one
     *      - It's good for the processor and is effective solution
     *
     *  2). ExecutorService executor = Executors.newFixedThreadPool(N)
     *      - Maximize the number of threads
     *      - When we want to start a job, if all the threads are busy then it shall create a new one
     *
     *  3). ExecutorService executor = Executors.newSingleThreadExecutor();
     *      - It uses a single thread for the job
     *
     *      * execute() => runnable + callable
     *      * submit()  => runnable
     */

    public static void main(String[] args) {

        //Best approach to get the executors is by using newCachedThreadPool
        ExecutorService executors = Executors.newCachedThreadPool();


        for(int i = 0 ; i < 5 ; i++) {

            executors.submit(new Runnable() {

                @Override
                public void run() {
                    for(int i = 0 ; i < 5; i++) {
                        System.out.println(i);
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

}
