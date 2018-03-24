package com.ramendu.concurrentCollections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * This is an unbounded BlockingQueue of objects that implement Delayed interface
 * DelayQueue keeps items internally until a certain delay has expired
 * An object can only be taken out when its delay has expired
 * We can not place null items in the queue
 * The queue is sorted such that the item at the head has an item whose delay expired for the longest amount of time
 * If no delay has expired then there is no head element and poll() will return null
 * size() returns the count of both expired and unexpired items
 */

public class DelayQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();

        try {
            queue.put(new DelayedWorker(1000, "This is the first message..."));
            queue.put(new DelayedWorker(10000, "This is the second message..."));
            queue.put(new DelayedWorker(4000, "This is the third message..."));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class DelayedWorker implements Delayed {

    private long duration;
    private String message;

    public DelayedWorker(long duration, String message) {
        this.duration = System.currentTimeMillis()+ duration;
        this.message = message;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed otherDelayed) {

        if(this.duration < ((DelayedWorker)otherDelayed).getDuration()) {
            return -1;
        }

        if(this.duration > ((DelayedWorker)otherDelayed).getDuration()) {
            return +1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return this.message;
    }
}