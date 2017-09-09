package com.util.concurrentTest.a_block;

import java.util.Date;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author y15079
 * @create 2017-09-05 17:09
 * @desc 阻塞双端队列，链表实现
 **/
public class LinkedBlockingDequeExample {
	public static void main(String[] args) throws Exception {
//创建一个并发链式双向队列
		LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>();

		Client client = new Client(list);
		Thread thread = new Thread(client);
		thread.start();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				String request = list.take();
				System.out.printf("Main: Request: %s at %s. Size: %d\n", request, new Date(), list.size());
			}
			TimeUnit.MILLISECONDS.sleep(300);
		}
		System.out.printf("Main: End of the program.\n");
	}

	/**
	 * 向阻塞队列中添加数据的类
	 */
	public static class Client implements Runnable {
		private LinkedBlockingDeque<String> requestList;

		public Client(LinkedBlockingDeque<String> requestList) {
			this.requestList = requestList;
		}

		/**
		 * 添加15个对象
		 */
		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {
					StringBuilder request = new StringBuilder();
					request.append(i);
					request.append(":");
					request.append(j);
					try {
						requestList.put(request.toString());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.printf("Client: %s at %s.\n", request, new Date());
				}
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.printf("Client: End.\n");
			}
		}
	}


}


class LinkedBlockingDequeDemo2 {
	private static Queue<String> queue = new LinkedBlockingDeque<String>();

	public static void main(String[] args) {
		//同时启动两个线程对queue进行操作
        new MyThread("ta").start();
        new MyThread("tb").start();
	}

	private static void printAll() {
		String value;
		Iterator iterator = queue.iterator();
		while (iterator.hasNext()) {
			value = (String) iterator.next();
			System.out.print(value + ", ");
		}
		System.out.println();
	}

	private static class MyThread extends Thread {
		public MyThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			int i = 0;
			while (i++ < 6) {
				// “线程名” + "-" + "序号"
				String val = Thread.currentThread().getName() + i;
				queue.add(val);
				// 通过“Iterator”遍历queue。
				printAll();
			}
		}
	}
}


