package com.util.concurrentTest.a_block;

import java.util.concurrent.SynchronousQueue;

/**
 * @author y15079
 * @create 2017-09-05 20:20
 * @desc 一种无缓冲的等待队列，类似于无中介的直接交易，有点像原始社会中的生产者和消费者，
 * 生产者拿着产品去集市销售给产品的最终消费者，而消费者必须亲自去集市找到所要商品的直接生产者，
 *
 * 如果一方没有找到合适的目标，那么对不起，大家都在集市等待。
 *
 * 相对于有缓冲的BlockingQueue来说，少了一个中间经销商的环节（缓冲区），
 * 如果有经销商，生产者直接把产品批发给经销商，而无需在意经销商最终会将这些产品卖给那些消费者，
 * 由于经销商可以库存一部分商品，因此相对于直接交易模式，总体来说采用中间经销商的模式会吞吐量高一些（可以批量买卖）；
 * 但另一方面，又因为经销商的引入，使得产品从生产者到消费者中间增加了额外的交易环节，单个产品的及时响应性能可能会降低。
 * <p>
 * 声明一个SynchronousQueue有两种不同的方式，它们之间有着不太一样的行为。公平模式和非公平模式的区别:
 * 如果采用公平模式：SynchronousQueue会采用公平锁，并配合一个FIFO队列来阻塞多余的生产者和消费者，从而体系整体的公平策略；
 * 但如果是非公平模式（SynchronousQueue默认）：SynchronousQueue采用非公平锁，同时配合一个LIFO队列来管理多余的生产者和消费者，
 * 而后一种模式，如果生产者和消费者的处理速度有差距，则很容易出现饥渴的情况，即可能有某些生产者或者是消费者的数据永远都得不到处理。
 **/
public class SynchronousQueueExample {
	public static void main(String[] args) {
		SynchronousQueue<Object> queue = new SynchronousQueue<Object>();
		for (int i = 0; i < 5; i++) {
			Thread t = new SQThread(queue, 1);
			t.start();
		}

		for (int i = 0; i < 10; i++) {
			if (!queue.offer(new Object())) {
				System.out.println("Failure");
			}
		}
	}

	public static class SQThread extends Thread {
		private SynchronousQueue<Object> queue;
		private int mode;

		public SQThread(SynchronousQueue<Object> queue, int mode) {
			this.queue = queue;
			this.mode = mode;
		}

		@Override
		public void run() {
			Object item = null;
			try {
				System.out.println("线程ID:" + Thread.currentThread().getId());
				if (mode == 1) {
					while ((item = queue.take()) != null) {
						System.out.println("线程ID:" + Thread.currentThread().getId() + " " + item.toString());
					}
				} else {
					//
				}
			} catch (Exception e) {
				//
			}
			System.out.println("end");
		}
	}
}
