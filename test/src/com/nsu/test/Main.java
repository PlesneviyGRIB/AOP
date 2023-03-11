package com.nsu.test;

public class Main {
    public static void main(String[] args) {
        new TestClass();
        new MyPointcuts();
        System.out.println("Main app return");
    }

    public static void methodForTest(){
        System.out.println("methodForTest");
    }
}