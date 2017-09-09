package com.util.concurrentTest.a_block;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author y15079
 * @create 2017-08-30 11:55
 * @desc
 *
 * 链阻塞队列 LinkedBlockingQueue 链表实现
 *
 * LinkedBlockingQueue 内部以一个链式结构(链接节点)对其元素进行存储。
 * 如果需要的话，这一链式结构可以选择一个上限。如果没有定义上限，将使用 Integer.MAX_VALUE 作为上限。
 * LinkedBlockingQueue 内部以 FIFO(先进先出)的顺序对元素进行存储。
 * 队列中的头元素在所有元素之中是放入时间最久的那个，而尾元素则是最短的那个。
 *
 * LinkedBlockingQueue是一个线程安全的阻塞队列，它实现了BlockingQueue接口，
 * BlockingQueue接口继承自java.util.Queue接口，并在这个接口的基础上增加了take和put方法，
 * 这两个方法正是队列操作的阻塞版本。
 **/
public class LinkedBlockingQequeExample {

	public static void main(String[] args) {

		// 队列
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);

		//ExecutorService是Java中对线程池定义的一个接口，
		// 它java.util.concurrent包中，在这个接口中定义了和后台任务执行相关的方法
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 6; i++) {
			service.submit(new Consumers(queue, "X二代" + i));
			service.submit(new Consumers(queue, "导演" + i));
		}
		for (int i = 0; i < 6; i++) {
			service.submit(new Producers(queue, "黄金酒," + i));
			service.submit(new Producers(queue, "美女演员" + i));
		}
		service.shutdown();
	}
}

//生产者
class Producers implements Runnable{
	private BlockingQueue<String> queue;
	private String produce;

	public Producers(BlockingQueue<String> queue, String produce) {
		this.queue = queue;
		if (null!=produce)
		   this.produce = produce;
		else this.produce="null";
	}

	@Override
	public void run() {
		String uuid= UUID.randomUUID().toString();
		try {
			Thread.sleep(200);//生产需要时间
			queue.put(produce+":"+uuid);
			System.out.println("Produce \""+produce+"\":"+uuid+" "+Thread.currentThread());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}

class Consumers implements Runnable{
	private BlockingQueue<String> queue;
	private String consumer;

	public Consumers(BlockingQueue<String> queue, String consumer) {
		this.queue = queue;
		if (null!=consumer)
		    this.consumer = consumer;
		else
			this.consumer="null";
	}

	@Override
	public void run() {
		try {
			String uuid=queue.take();
			System.out.println(consumer+"decayed"+uuid+" "+Thread.currentThread());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

	}
}
