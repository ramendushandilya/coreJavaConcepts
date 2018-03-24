package com.ramendu.forkJoin.levelZero;

import java.util.concurrent.ForkJoinPool;

public class App {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SimpleRecursiveAction recursiveAction = new SimpleRecursiveAction(400);

        pool.invoke(recursiveAction);
    }
}
