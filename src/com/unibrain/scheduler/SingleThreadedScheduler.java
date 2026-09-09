package com.unibrain.scheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SingleThreadedScheduler {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Define the future date and time (e.g., 20 minutes from now)
        LocalDateTime futureDateTime = LocalDateTime.now().plusMinutes(2);

        // Calculate the delay in milliseconds
        long delay = Duration.between(LocalDateTime.now(), futureDateTime).toMillis();

        // Schedule two tasks with the same delay
        scheduler.schedule(() ->print("Task 1"), delay, TimeUnit.MILLISECONDS);
        System.out.println("we will do");
        scheduler.schedule(() -> print("Task 2"), delay, TimeUnit.MILLISECONDS);
        System.out.println("we will complete");

        // Optionally, shut down the scheduler after some time if it's not needed further
        scheduler.shutdown();
        System.out.println("we will shutdown");
    }

    public static void print(String taskName) {
        System.out.println(taskName + " executed at " + LocalDateTime.now());
    }
}
