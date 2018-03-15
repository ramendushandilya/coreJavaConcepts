package com.ramendu.levelSix;

import java.util.function.BiConsumer;

public class ExceptionHandling {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int key = 0;

        process(nums, key, wrapperLambda((v, k) -> System.out.println(v/k)));
    }

    private static void process(int[] nums, int key, BiConsumer<Integer, Integer> biConsumer) {
        for(Integer i : nums)
            biConsumer.accept(i, key);
    }

    private static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> biConsumer) {
        return (v,k) -> {
            try {
                biConsumer.accept(v, k);
            } catch (ArithmeticException e) {
                e.printStackTrace();
            }
        };
    }

}
