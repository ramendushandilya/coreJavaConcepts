package com.ramendu.levelZero;

/**
 * Lambda is coded to an interface in Java
 * For an interface to be eligible to be
 * target interface it must contain only
 * one abstract method
 *
 * To maintain this contract of only one
 * abstract method we mark the interface
 * as FunctionalInterface so that when a
 * developer adds any other method,
 * compilation error occurs
 */

@FunctionalInterface
public interface IGreeting {

    public void perform();

}
