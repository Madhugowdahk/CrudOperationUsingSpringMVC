package com.unibrain.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Scheduler {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // Schedule a task to call a random method after 5 minutes
        scheduler.schedule(() -> callRandomMethod(), 5, TimeUnit.MINUTES);

        // Shutdown the scheduler after the task is completed
        scheduler.shutdown();
    }

    private static void callRandomMethod() {
        List<Runnable> methods = new ArrayList<>();
        methods.add(() -> method1());
        methods.add(() -> method2());
        methods.add(() -> method3());

        // Select a random method from the list
        Random random = new Random();
        int index = random.nextInt(methods.size());
        Runnable method = methods.get(index);

        // Call the selected method
        method.run();
    }

    private static void method1() {
        System.out.println("Method 1 called");
    }

    private static void method2() {
        System.out.println("Method 2 called");
    }

    private static void method3() {
        System.out.println("Method 3 called");
    }
}
