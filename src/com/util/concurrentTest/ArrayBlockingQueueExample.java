package com.util.concurrentTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author y15079
 * @create 2017-08-30 10:17
 * @desc
 *
 * ArrayBlockingQueue 类实现了 BlockingQueue 接口。
 *
 * ArrayBlockingQueue 是一个有界的阻塞队列，其内部实现是将对象放到一个数组里。有界也就意味着，它不能够存储无限多数量的元素。
 * 它有一个同一时间能够存储元素数量的上限。
 * 你可以在对其初始化的时候设定这个上限，但之后就无法对这个上限进行修改了(译者注：因为它是基于数组实现的，也就具有数组的特性：一旦初始化，大小就无法修改)。
 * ArrayBlockingQueue 内部以 FIFO(先进先出)的顺序对元素进行存储。
 * 队列中的头元素在所有元素之中是放入时间最久的那个，而尾元素则是最短的那个。
 **/
public class ArrayBlockingQueueExample {

	public static void main(String[] args) throws Exception {
		BlockingQueue blockingQueue = new ArrayBlockingQueue(1024);

        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
	}
}

class Producer implements Runnable{
	protected BlockingQueue queue = null;

	public Producer(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			//put ,take 阻塞函数
			queue.put("1");
			Thread.sleep(1000);
			queue.put("2");
			Thread.sleep(1000);
			queue.put("3");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer implements Runnable{

	protected BlockingQueue queue = null;

	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
