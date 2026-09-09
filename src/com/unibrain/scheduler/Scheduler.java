package com.unibrain.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author UCS-BLR-05
 *If you use Executors.newScheduledThreadPool(1), it creates a ScheduledExecutorService with a single thread. This single thread is responsible for executing all scheduled tasks.
 * Here’s how it works:Single-Threaded Scheduled Executor Single Thread for Execution:
 * Single Thread: When you use Executors.newScheduledThreadPool(1), the thread pool will have only one thread available to execute tasks.
 * Sequential Execution: If multiple tasks are scheduled, they will be executed sequentially by the single thread. If one task is running, others will wait until the thread is free.

 * Scheduling Multiple Tasks:
 * Delay Handling: If you schedule multiple tasks with the same delay, each task will be queued and executed one after another, in the order they were scheduled. The single thread will handle each task sequentially.
 * Concurrency: Because there is only one thread, no two tasks will run concurrently. Tasks scheduled at the same time will execute one after the other, not in parallel.

 */
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
