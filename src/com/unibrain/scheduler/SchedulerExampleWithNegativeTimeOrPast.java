package com.unibrain.scheduler;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author UCS-BLR-05
 *When a negative delay is passed to ScheduledExecutorService.schedule():
 *Immediate Execution: The task will be executed as soon as possible, essentially right
 * after the scheduler is ready to handle it. This is because a negative delay implies the 
 * scheduled time is in the past relative to the current time, so the scheduler treats it as 
 * a request to execute the task immediately.
 */
public class SchedulerExampleWithNegativeTimeOrPast {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Define the target past date and time (e.g., yesterday at the current time)
        LocalDateTime pastDateTime = LocalDateTime.now().minusDays(1);

        // Calculate the delay in milliseconds (which will be negative in this case)
        long delay = Duration.between(LocalDateTime.now(), pastDateTime).toMillis();

        // Print delay to understand the value
        System.out.println("Delay (milliseconds): " + delay);

        // Schedule the task
        scheduler.schedule(() -> print(), delay, TimeUnit.MILLISECONDS);

        // Optionally, shut down the scheduler after some time if it's not needed further
        scheduler.shutdown();
    }

    public static void print() {
        System.out.println("Task executed");
    }
}
