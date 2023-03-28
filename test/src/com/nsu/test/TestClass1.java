package com.nsu.test;

public class TestClass1 {
    public TestClass1() {}
    public static Integer increment(Integer val){
        return val + 1;
    }
    public void exception() throws Exception{
        throw new Exception("Really important exception");
    }
}
