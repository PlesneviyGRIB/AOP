package com.nsu.test;

public class TestClass {
    public void A() {
        System.out.println("+++++++++++++A BODY+++++++++++++");
        B();
    }

    public void B() {
        System.out.println("+++++++++++++B BODY+++++++++++++");
        C();
    }

    public void C() {
        System.out.println("+++++++++++++C BODY+++++++++++++");
    }
}