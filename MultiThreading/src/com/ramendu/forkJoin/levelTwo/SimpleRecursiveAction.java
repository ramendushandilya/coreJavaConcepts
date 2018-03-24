package com.ramendu.forkJoin.levelTwo;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveAction extends RecursiveTask<Integer> {

    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected Integer compute() {
        if(simulatedWork > 100) {
            System.out.println("Parallel execution needed ..."+simulatedWork);

            SimpleRecursiveAction recursiveActionOne = new SimpleRecursiveAction(simulatedWork/2);
            SimpleRecursiveAction recursiveActionTwo = new SimpleRecursiveAction(simulatedWork/2);

            recursiveActionOne.fork();
            recursiveActionTwo.fork();

            int solution = 0;

            solution += recursiveActionOne.join();
            solution += recursiveActionTwo.join();

            return solution;
        } else {
            System.out.println("No parallel execution needed ..."+simulatedWork);
            return 2*simulatedWork;
        }
    }
}
