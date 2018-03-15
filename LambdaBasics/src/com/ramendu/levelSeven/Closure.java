package com.ramendu.levelSeven;

public class Closure {

    public static void main(String[] args) {
        int a = 10;
        //Here b is effectively final
        int b = 20;

        //Here lambda is executed in the doProcess method but there we don't have b variable, still we get it to be add
        //Internally in Java8 compiler keeps track of this variable, known as closure
        doProcess(a, i -> System.out.println(i+b));
    }

    private static void doProcess(int a, Process process) {
        process.process(a);
    }

}

interface Process {
    void process(int i);
}
