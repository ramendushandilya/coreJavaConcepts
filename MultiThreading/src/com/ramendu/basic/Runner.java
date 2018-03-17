package com.ramendu.basic;


/**
 * Shows two ways to starting a thread in Java
 * Use of start method on the thread object
 * If run method is called on the thread object then the new thread is not created rather it's run in the same stack
 * not in the new stack as how a new thread is supposed to be
 */
public class Runner {

    public static void main(String[] args) {

        //Thread making using implementing runnable interface
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 10 ; i++) {
                    System.out.println("Thread one "+i);
                }
            }
        });


        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 10 ; i++) {
                    System.out.println("Thread two "+i);
                }
            }
        });

        //Thread making using extending the Thread class
        Thread three = new Thread() {
          public void run() {
              for(int i = 0 ; i < 10 ; i++) {
                  System.out.println("Thread three "+i);
              }
          }
        };

        //Execution of threads is not guaranteed, it may happen in any ways of random order
        one.start();
        two.start();
        three.start();

        //.join() method call on the thread makes the parent thread to wait for the child thread to complete
        try {
            one.join();
            two.join();
            three.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Threads done working...");
    }

}
