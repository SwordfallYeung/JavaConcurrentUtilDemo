package com.util.concurrentTest.c_other;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author y15079
 * @create 2017-09-09 14:46
 * @desc
 **/
public class ConcurrentHashMapExample {

	public static void main(String[] args) {

		final Map<String,AtomicInteger> count = new ConcurrentHashMap<>();
		final CountDownLatch endLatch=new CountDownLatch(2);

		Runnable task = new Runnable() {
			@Override
			public void run() {
				AtomicInteger oldValue;
				for (int i=0;i<5;i++){
					oldValue=count.get("a");
					if (null==oldValue){
						AtomicInteger zeroValue=new AtomicInteger(0);
						oldValue = count.putIfAbsent("a",zeroValue);
						if (null == oldValue){
							oldValue=zeroValue;
						}
					}
					oldValue.incrementAndGet();
				}
			}
		};

		new Thread(task).start();
		new Thread(task).start();

		try {
			endLatch.await();
			System.out.println(count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
