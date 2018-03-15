package com.ramendu.levelFour;

import com.ramendu.common.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Step 1: Sort a list of people by their first name
 * Step 2: Make a method to print all the people
 * Step 3: Print people with las name starting with C
 */

public class LambdaWithCollectionExample {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 39)
        );

        //Step 1
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });

        //Lambda implementation sorts by last name
        Collections.sort(people, (p1, p2) -> p2.getLastName().compareTo(p1.getLastName()));

        //Step 2
        System.out.println("###### STEP 2 ######");
        printAll(people);

        //Step 3
        System.out.println("###### STEP 2 ######");
        printConditionally(people, new Condition() {
            @Override
            public boolean test(Person person) {
                return person.getLastName().startsWith("C");
            }
        });

        //Lambda implementation last name doesn't start with C
        printConditionally(people, (p) -> !p.getLastName().startsWith("C"));


    }

    private static void printAll(List<Person> people) {
        for(Person p : people)
            System.out.println(p);
    }

    private static void printConditionally(List<Person> people, Condition condition) {
        for(Person p : people) {
            if(condition.test(p)) {
                System.out.println(p);
            }
        }
    }

}

interface Condition {
    boolean test(Person person);
}