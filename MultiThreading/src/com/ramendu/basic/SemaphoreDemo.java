package com.ramendu.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphores maintain a set of permits
 * acquire() - if a permit is available then take it
 * release() - adds a permit
 * Semaphore will just keep count of the number available
 * new Semaphore(int permits, boolean fairnessFactor)
 *
 * In the below example we have create a semaphore with 3 permits
 * If we have 12 threads trying to do a task, then at a time only 3 permits will be assigned by the semaphore
 */

enum Downloader {

    INSTANCE;

    //Creating a semaphore with three permits/slots with fairness factor as true
    private Semaphore semaphore = new Semaphore(3, true);

    public void downloadData() {
        try {
            //Acquire the semaphore
            semaphore.acquire();
            //Call download
            download();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            //Once done release semaphore
            semaphore.release();
        }
    }

    private void download() {
        System.out.println("Downloading from the web...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SemaphoreDemo {

    public static void main(String[] args) {
        //Made use of Executors to create a thread pool
        ExecutorService executors = Executors.newCachedThreadPool();

        //Creating a certain number threads to access the downloadData() method
        for(int i = 0 ; i < 2 ; i++) {
            executors.execute(new Runnable() {
                @Override
                public void run() {
                    Downloader.INSTANCE.downloadData();
                }
            });
        }
    }

}
