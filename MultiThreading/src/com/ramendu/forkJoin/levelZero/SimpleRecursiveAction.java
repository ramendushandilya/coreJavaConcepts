package com.ramendu.forkJoin.levelZero;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction{

    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }


    @Override
    protected void compute() {
        if(simulatedWork > 100) {
            System.out.println("Parallel execution and split the tasks ..."+simulatedWork);

            SimpleRecursiveAction recursiveActionOne = new SimpleRecursiveAction(simulatedWork/2);
            SimpleRecursiveAction recursiveActionTwo = new SimpleRecursiveAction(simulatedWork/2);

            recursiveActionOne.fork();
            recursiveActionTwo.fork();
        } else {
            System.out.println("No parallel processing needed..."+simulatedWork);
        }
    }
}
