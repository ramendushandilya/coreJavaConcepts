package com.ramendu.levelFive;

public class TypeReferenceExample {

    public static void main(String[] args) {

        printLambda(s -> s.length());
    }

    private static void printLambda(StringLengthLambda lengthLambda) {
        System.out.println(lengthLambda.getLength("Hello lambda"));
    }

    interface StringLengthLambda {
        int getLength(String string);
    }
}