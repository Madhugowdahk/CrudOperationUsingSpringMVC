package com.unibrain.scheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerExample2 {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Define the future date and time (e.g., 1 minute from now)
        LocalDateTime futureDateTime = LocalDateTime.now().plusMinutes(1);

        // Calculate the delay in milliseconds
        long delay = Duration.between(LocalDateTime.now(), futureDateTime).toMillis();

        // Schedule the task
        scheduler.schedule(() -> SchedulerExample1.print("Task from SchedulerExample2"), delay, TimeUnit.MILLISECONDS);

        // Optionally, shut down the scheduler after some time if it's not needed further
        scheduler.shutdown();
    }

    public static void print(String taskName) {
        System.out.println(taskName + " executed at " + LocalDateTime.now());
    }
}
