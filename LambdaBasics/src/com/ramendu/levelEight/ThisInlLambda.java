package com.ramendu.levelEight;

//this reference won't work in static context in lambda
public class ThisInlLambda {

    public static void main(String[] args) {
        ThisInlLambda lamb = new ThisInlLambda();

        lamb.execute();

    }

    private void execute() {

        doProcess(10, i -> {
            System.out.println(i);
            System.out.println(this);
        });

    }

    private void doProcess(int i , Process p) {
        p.process(i);
    }

    @Override
    public String toString() {
        return "This reference";
    }
}

interface Process {
    void process(int i);
}