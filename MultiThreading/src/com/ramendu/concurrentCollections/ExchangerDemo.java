package com.ramendu.concurrentCollections;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        First first = new First(exchanger);
        Second second = new Second(exchanger);

        new Thread(first).start();
        new Thread(second).start();
    }
    
}

class First implements Runnable {
    private int counter;
    private Exchanger<Integer> exchanger;

    public First(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            counter++;
            System.out.println("First thread incremented counter to = "+counter);
            try {
                counter = exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Second implements Runnable {
    
    private int counter;
    private Exchanger<Integer> exchanger;

    public Second(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            counter --;
            System.out.println("Second thread decremented counter to = "+counter);
            try {
                counter = exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}