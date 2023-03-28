package com.nsu.test;

public class TestClass {
    public void a() {
        System.out.println("+++++ A starts");
        b();
        System.out.println("+++++ A ends");
    }

    public void b() {
        System.out.println("+++++ B start");
        c();
        System.out.println("+++++ B ends");
    }

    public void c() {
        System.out.println("+++++ C body");
    }
}