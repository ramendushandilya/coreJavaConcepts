package com.ramendu.levelNine;

public class MethodReferenceOne {

    public static void main(String[] args) {

        //() -> printMessage() can be replaced by MethodReferenceOne::printMessage
        //Equivalent to () -> method() when there is a no arg method
        Thread t = new Thread(MethodReferenceOne::printMessage);
        t.start();
    }

    static void printMessage() {
        System.out.println("Some message");
    }

}
