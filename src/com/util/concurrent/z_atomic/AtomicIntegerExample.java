package com.util.concurrent.z_atomic;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author y15079
 * @create 2017-09-12 20:20
 * @desc
 *
 * AtomicInteger提供原子操作来进行Integer的使用，因此十分适合高并发情况下的使用。
 **/
public class AtomicIntegerExample {

	private static int threadCount=10;
	private static CountDownLatch countDown=new CountDownLatch(threadCount);
	private static AtomicInteger count=new AtomicInteger(0);//原子操作类
	private static class Counter implements Runnable{
		@Override
		public void run() {
			for (int i=0;i<1000;i++){
				count.addAndGet(1);
			}
			countDown.countDown();
		}
	}

	public static void main(String[] args) throws Exception{
		Thread[] threads=new Thread[threadCount];
		for (int i=0;i<threadCount;i++){
			threads[i]=new Thread(new Counter());
		}
		for (int i=0;i<threadCount;i++){
			threads[i].start();
		}
		countDown.await();
		System.out.println(count.get());
	}

}
