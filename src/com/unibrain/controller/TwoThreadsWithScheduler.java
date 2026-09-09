package com.unibrain.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.*;

public class TwoThreadsWithScheduler {
	/**
	 * @param args
	 * A thread pool is a collection of worker threads that are managed by an executor. The main purpose of a thread pool is to improve the performance of an application by reusing threads 
	 * rather than creating new ones every time a task is executed. When you submit a task to a thread pool, it is added to a queue. The worker threads in the thread pool take tasks from the queue
	 *  and execute them. Once a task is completed, the thread can be reused for another task.
	 *  Thread pools are useful because creating and destroying threads can be a costly operation in terms of performance and resources. By reusing threads from a pool, you can avoid this overhead and 
	 *  improve the efficiency of your application.
	 *  
	 *  Java provides the Executor framework for working with thread pools. You can create a thread pool using Executors class methods like newFixedThreadPool, newCachedThreadPool, or 
	 *  newScheduledThreadPool, depending on your requirements.Threads in a thread pool are kept alive and can be reused for future tasks. When a task is completed, the thread doesn't die but goes back to the pool,
	 *  where it waits for the next task to be assigned to it. This reusability is one of the key advantages of using a thread pool, as it avoids the overhead of creating and destroying threads for each task, 
	 *  improving the performance and efficiency of the application.
	 */
	public static void main(String[] args) {
		//ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
		//        Schedule two tasks to call the same method after 5 minutes
		//        scheduler.schedule(() -> callMethod(), 5, TimeUnit.MINUTES);
		//        System.out.println("above one");
		//        scheduler.schedule(() -> callMethod(), 5, TimeUnit.MINUTES);
		//        System.out.println("below one");
		//
		//        // Shutdown the scheduler after the tasks are completed
		//        scheduler.shutdown();
//		for(int i=0;i<5;i++)
//		{
//			try {
//				Thread.sleep(10000);
//				callMethod();
//
//			}catch (Exception e) {
//				
//			}
//		}
		callMethod();

	}
	public static void print()
	{
	    Thread currentThread = Thread.currentThread();
	    System.out.println("Thread name: " + currentThread.getName());
	    System.out.println("Thread ID: " + currentThread.getId());
	    System.out.println("Thread group: " + currentThread.getThreadGroup().getName());
		System.out.println("The name is going to print after 10 secs:Madhugowda");
	}
	private static void callMethod() {
		//each time you call the callMethod() method, a new thread pool is created, and the scheduled task is executed by a thread from that pool. 
		//This behavior is expected because Executors.newScheduledThreadPool(1) creates a new thread pool with a single thread each time it's called
		System.out.println("Method called");
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.schedule(()->print(), 1, TimeUnit.MINUTES);
		scheduler.shutdown();

	}
}
