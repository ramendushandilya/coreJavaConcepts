package com.ramendu.parallelSorting;

import java.util.Random;

public class App {

    public static Random random = new Random();
    static MergeSort mergeSort = new MergeSort();

    public static void main(String[] args) throws Throwable{

        int numberOfThreads = Runtime.getRuntime().availableProcessors();

        System.out.println("Number of threads/cores = "+ numberOfThreads);

        long startSeq = System.currentTimeMillis();
        mergeSort.mergeSort(createRandomArray());
        long endSeq = System.currentTimeMillis();
        long diffSeq = endSeq - startSeq;

        long startPar = System.currentTimeMillis();
        mergeSort.parallelMergeSort(createRandomArray(), numberOfThreads);
        long endPar = System.currentTimeMillis();
        long diffPar = endPar - startPar;

        System.out.println("Sequential sort = "+diffSeq);
        System.out.println("Parallel sort = "+diffPar);
    }

    private static void showList(int[] numbers) {
        for(int i = 0 ; i < numbers.length ; i++) {
            System.out.println(numbers[i]);
        }
    }

    public static int[] createRandomArray() {
        int[] a = new int[1000000];
        for(int i = 0 ; i < 1000000 ; i++) {
            a[i] = random.nextInt(100000);
        }
        return a;
    }
}
