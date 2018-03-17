package com.ramendu.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Callable interface is similar to Runnable but it also returns the result of the thread
 * call method throws Exception
 */
class Processor implements Callable<String> {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Id"+id;
    }
}

public class CallableFutureDemo {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(2);

        //Return of Callable is stored in Future<String>
        List<Future<String>> futures = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++) {
            Future<String> future = executors.submit(new Processor(i+1));
            futures.add(future);
        }

        for(Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        //Previously initiated tasks are shutdown orderly
        //No new tasks are accepted
        executors.shutdown();
    }
}
