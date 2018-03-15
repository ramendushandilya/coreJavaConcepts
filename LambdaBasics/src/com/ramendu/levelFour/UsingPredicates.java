package com.ramendu.levelFour;

import com.ramendu.common.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class UsingPredicates {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 39)
        );

        printConditionally(people , p -> p.getFirstName().startsWith("C"), p -> System.out.println(p));

        printConditionally(people, p -> true, p -> System.out.println(p.getAge()));
    }

    /**
     * For common scenarios Java 8 provides some marker interface in java.util.function package
     * Here we are using Predicate interface to achieve our task
     * @param people
     * @param predicate
     */
    private static void printConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for(Person p : people) {
            if(predicate.test(p)) {
                consumer.accept(p);
            }
        }
    }

}
