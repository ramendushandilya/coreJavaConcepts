package com.ramendu.forkJoin.levelTwo;

import java.util.concurrent.ForkJoinPool;

public class App {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SimpleRecursiveAction recursiveAction = new SimpleRecursiveAction(200);
        System.out.println(pool.invoke(recursiveAction));
    }
}
