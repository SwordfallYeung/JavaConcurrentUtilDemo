package com.util.concurrentTest;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author y15079
 * @create 2017-09-05 20:00
 * @desc 优先级阻塞队列
 **/
public class PriorityBlockingQueueExample {

	public static void main(String[] args) {

		// 存储事件的优先级队列
		PriorityBlockingQueue<Event>  queue=new PriorityBlockingQueue<Event>();

		// 存储5个线程对象的数组
		Thread taskThreads[] = new Thread[5];

		// 创建5个线程运行5个任务，每个任务创建1000事件对象
		for (int i = 0; i < taskThreads.length; i++) {
			Task task = new Task(i, queue);
			taskThreads[i] = new Thread(task);
		}

		//  启动5个线程
		for (Thread taskThread : taskThreads) {
			taskThread.start();
		}

		// 等待5个线程完成
		for (Thread taskThread : taskThreads) {
			try {
				taskThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 输出事件信息
		System.out.printf("Main: Queue Size: %d\n", queue.size());
		for (int i = 0; i < taskThreads.length * 1000; i++) {
			Event event = queue.poll();
			System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
		}
		System.out.printf("Main: Queue Size: %d\n", queue.size());
		System.out.printf("Main: End of the program\n");

	}

	public static class Event implements Comparable<Event> {

		/**
		 * 线程编号
		 */
		private int thread;
		/**
		 * 线程优先级
		 */
		private int priority;

		public Event(int thread, int priority) {
			this.thread = thread;
			this.priority = priority;
		}

		public int getThread() {
			return thread;
		}

		public void setThread(int thread) {
			this.thread = thread;
		}

		public int getPriority() {
			return priority;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		@Override
		public int compareTo(Event o) {
			if (this.priority > o.getPriority()) {
				return -1;
			} else if (this.priority < o.getPriority()) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public static class Task implements Runnable{

		/**
		 * 任务编号
		 */
		private int id;

		/**
		 * 存储事件的优先队列
		 */
		private PriorityBlockingQueue<Event> queue;

		public Task(int id, PriorityBlockingQueue<Event> queue) {
			this.id = id;
			this.queue = queue;
		}

		/**
		 * 核心方法，生成1000个事件，并且将其存放在一个优先队列中
		 */
		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				Event event = new Event(id, i);
				queue.add(event);
			}
		}
	}
}

