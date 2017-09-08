package com.util.concurrentTest.unlock;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author y15079
 * @create 2017-09-08 19:15
 * @desc Java 7中引入了ConcurrentLinkedDeque类,它实现了一个非阻塞并发列表
 *
 *  现在,让我们创建100个线程将数据添加到列表和100个线程从列表删除数据。
 *  如果真的是线程安全的和非阻塞,它会几乎立即给你最终结果。此外,列表大小最终将是零。
 **/
public class ConcurrentLinkedDequeExample {
	public static void main(String[] args) {
         ConcurrentLinkedDeque<String> list=new ConcurrentLinkedDeque<String>();
         Thread[] threads=new Thread[100];

         for (int i=0;i<threads.length;i++){
         	AddTask task=new AddTask(list);
         	threads[i]=new Thread(task);
         	threads[i].start();
		 }
		 System.out.printf("Main: %d AddTask threads have been launched\n",threads.length);

		for (int i=0;i<threads.length;i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Main: Size of the List: %d \n",list.size());

		for (int i=0;i<threads.length;i++){
			RemoveTask task=new RemoveTask(list);
			threads[i]=new Thread(task);
			threads[i].start();
		}
		System.out.printf("Main: %d RemoveTask threads have been launched\n",threads.length);

		for (int i=0;i<threads.length;i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Main: Size of the List: %d\n", list.size());
	}

	//往ConcurrentLinkedDeque塞1万条数据
	public static class AddTask implements Runnable {
		private ConcurrentLinkedDeque<String> list;

		public AddTask(ConcurrentLinkedDeque<String> list) {
			this.list = list;
		}

		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			for (int i = 0; i < 10000; i++) {
				list.add(name + ": Element" + i);
			}
		}
	}

	//从concurrentLinkedDeque队列不断删除元素
	public static class RemoveTask implements Runnable{
		private ConcurrentLinkedDeque<String> list;

		public RemoveTask(ConcurrentLinkedDeque<String> list) {
			this.list = list;
		}

		@Override
		public void run() {
			for (int i=0;i<5000;i++){
				list.pollFirst();
				list.pollLast();
			}
		}
	}
}
