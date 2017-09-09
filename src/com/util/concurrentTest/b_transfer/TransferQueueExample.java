package com.util.concurrentTest.b_transfer;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author y15079
 * @create 2017-09-09 12:42
 * @desc
 * 转移队列接口，生产者要等消费者消费的队列，生产者尝试把元素直接转移给消费者
 *
 * 如果接收线程处于等待状态，transfer（）马上把工作项传给它，否则就会阻塞直到取走工作项  [高效]
 * 即正在处理的工作项的线程在交付当前工作之前（被取走之前），不开始其他工作项的处理。
 **/
public class TransferQueueExample {

	public static void main(String[] args) {
          final TransferQueue<String> transferQueue = new LinkedTransferQueue<String>();
	      //生产者线程初始化并启动
          LinkedTransferQueueProducer queueProducer=new LinkedTransferQueueProducer(transferQueue);
	      new Thread(queueProducer).start();

	      LinkedTransferQueueConsumer queueConsumer1=new LinkedTransferQueueConsumer(transferQueue);
		  LinkedTransferQueueConsumer queueConsumer2=new LinkedTransferQueueConsumer(transferQueue);

		  new Thread(queueConsumer1).start();
		  new Thread(queueConsumer2).start();
	}

	public static class LinkedTransferQueueProducer implements Runnable{
		protected TransferQueue<String> transferQueue;

		final Random random = new Random();

		public LinkedTransferQueueProducer(TransferQueue<String> transferQueue) {
			this.transferQueue = transferQueue;
		}

		@Override
		public void run() {
			while (true){
				try {
					String data = UUID.randomUUID().toString();
					System.out.println(" Put: "+data);
					transferQueue.transfer(data);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class LinkedTransferQueueConsumer implements Runnable{
		protected TransferQueue<String> transferQueue;

		public LinkedTransferQueueConsumer(TransferQueue<String> transferQueue) {
			this.transferQueue = transferQueue;
		}

		@Override
		public void run() {
			while (true){
				try {
					String data=transferQueue.take();
					System.out.println(Thread.currentThread().getName()+" take(): "+data);
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
