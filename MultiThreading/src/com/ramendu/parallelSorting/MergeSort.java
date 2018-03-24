package com.ramendu.parallelSorting;

import java.util.Arrays;

public class MergeSort {

    /**
     * Original merge sort procedure
     * @param a
     */
    public void mergeSort(int[] a) {
        if(a.length <= 1) {
            return;
        }

        int mid = a.length/2;

        int[] left = Arrays.copyOfRange(a, 0, mid);
        int[] right = Arrays.copyOfRange(a, mid, a.length);

        mergeSort(left);
        mergeSort(right);

        merge(left, right, a);
    }

    /**
     * Merging procedure
     * @param left
     * @param right
     * @param a
     */
    private void merge(int[] left, int[] right, int[] a) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if(left[i] < right[j]) {
                a[k++] = left[i++];
            } else {
                a[k++] = right[j++];
            }
        }

        while (i < left.length) {
            a[k++] = left[i++];
        }

        while (j < right.length) {
            a[k++] = right[j++];
        }
    }

    /**
     * Parallel merge sort procedure
     * @param numbers
     * @param numberOfThreads
     */
    public void parallelMergeSort(int[] numbers, int numberOfThreads) {
        if(numberOfThreads <= 1) {
            mergeSort(numbers);
            return;
        }

        int mid = numbers.length/2;

        int[] left = Arrays.copyOfRange(numbers, 0, mid);
        int[] right = Arrays.copyOfRange(numbers, mid, numbers.length);

        Thread leftSorter = mergeSortThread(left, numberOfThreads);
        Thread rightSorter = mergeSortThread(right, numberOfThreads);

        leftSorter.start();
        rightSorter.start();
        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        merge(left, right, numbers);
    }

    private Thread mergeSortThread(int[] array, int numberOfThreads) {

        return new Thread() {
            public void run() {
                parallelMergeSort(array, numberOfThreads/2);
            }
        };
    }
}
