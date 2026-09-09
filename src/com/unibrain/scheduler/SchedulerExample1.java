package com.unibrain.scheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author UCS-BLR-05
 *Two Separate Executors: Each ScheduledExecutorService is independent of the other. Scheduling the same task
 * with the same delay in two separate executors means:
 * Task Execution: Each scheduler will handle its own set of tasks. If both are scheduled to execute at the same time, they will run independently of each other.
 * Threads: If you use single-threaded executors (Executors.newScheduledThreadPool(1)), each task from each scheduler will be executed by its own thread in its respective executor.
 * 
 * Execution Timing:
 * Synchronization: The tasks from different schedulers are not synchronized with each other. 
 * They are scheduled to run at the same time, but each executor runs its own tasks independently.
 * Concurrency: The tasks will be executed as scheduled but by different threads if using different executors. 
 * If both executors have only one thread, the tasks will be executed one after another according to each executor's scheduling.
 * 
 * Independent Execution:Separate Executors: Each ScheduledExecutorService instance operates independently.
 * Scheduling the same method in two separate schedulers means that each scheduler will run its own instance of
 * the method at the scheduled time.
 * No Conflict: There will be no conflict between the two schedulers because they manage their own threads
 * and task queues
 * 
 * 

 */
public class SchedulerExample1 {

	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		// Define the future date and time (e.g., 1 minute from now)
		LocalDateTime futureDateTime = LocalDateTime.now().plusMinutes(1);

		// Calculate the delay in milliseconds
		long delay = Duration.between(LocalDateTime.now(), futureDateTime).toMillis();

		// Schedule the task
		scheduler.schedule(() -> print("Task from SchedulerExample1"), delay, TimeUnit.MILLISECONDS);

		// Optionally, shut down the scheduler after some time if it's not needed further
		scheduler.shutdown();
	}

	/**
	 * @param taskName
	 */
	public static void print(String taskName) {
		System.out.println(taskName + " executed at " + LocalDateTime.now());
	}
}
