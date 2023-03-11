package com.nsu.aop.utils;

import java.util.Queue;

public class LogsPrinter {
    private Queue<String> logs;

    public LogsPrinter(Queue<String> logs){
        this.logs = logs;
        print();
    }
    private void print(){
        logs.forEach(System.out::println);
    }
}
