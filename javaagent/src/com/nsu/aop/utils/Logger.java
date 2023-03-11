package com.nsu.aop.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Logger {
    private static Logger logger;
    private Queue<String> queue = new LinkedList<>();
    private Logger(){}
    public static Logger instance(){
        if(logger == null) logger = new Logger();
        return logger;
    }
    private String agentStartMessage(){
        return "\n" +
                "▄▀█ █▀▀ █▀▀ █▄░█ ▀█▀   █▀ ▀█▀ ▄▀█ █▀█ ▀█▀ █▀\n" +
                "█▀█ █▄█ ██▄ █░▀█ ░█░   ▄█ ░█░ █▀█ █▀▄ ░█░ ▄█";
    }

    private String agentEndMessage(){
        return "\n" +
                "▄▀█ █▀▀ █▀▀ █▄░█ ▀█▀   █▀▀ █ █▄░█ █ █▀ █░█ █▀▀ █▀▄\n" +
                "█▀█ █▄█ ██▄ █░▀█ ░█░   █▀░ █ █░▀█ █ ▄█ █▀█ ██▄ █▄▀";
    }


    public void agentStartNotification(){
        queue.add(agentStartMessage());
    }

    public void agentEndNotification(){
        queue.add(agentEndMessage());
    }

    public Queue<String> getLogs(){
        return new LinkedList<>(queue);
    }
}
