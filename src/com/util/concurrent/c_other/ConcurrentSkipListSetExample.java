package com.util.concurrent.c_other;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author y15079
 * @create 2017-09-09 15:25
 * @desc ConcurrentSkipListSet是线程安全的有序的哈希表，适用于高并发的场景
 * 他是一个基于ConcurrentSkipListMap的可缩放并发NavigableSet实现。
 * ConcurrentSkipListSet继承于AbstractSet。因此，它本质上是一个集合。
 * ConcurrentSkipListSet实现类NavigableSet接口。因此，ConcurrentSkipListSet是一个有序的集合
 * ConcurrentSkipListSet是通过ConcurrentSkipListMap实现的。
 * 它包含一个ConcurrentNavigableMap对象m，而m对象实际上是ConcurrentNavigableMap的实现类ConcurrentSkipListMap的实例。
 * ConcurrentSkipListMap中的元素是key-value键值对；而ConcurrentSkipListSet是集合，它只用到了ConcurrentSkipListMap中的key！
 * <p>
 * 由源码中我们能看到ConcurrentSkipListSet内部所有操作都是在内部由ConcurrentSkipListMap完成
 **/
public class ConcurrentSkipListSetExample {

	public static void main(String[] args) {
		//线程同步
		ConcurrentSkipListSet<Integer> csls1 = new ConcurrentSkipListSet<Integer>();
		PutThread1 p1 = new PutThread1(csls1);
		PutThread1 p2 = new PutThread1(csls1);
		p1.start();
		p2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Iterator<Integer> iterator1 = csls1.iterator();
		while (iterator1.hasNext()) {
			System.out.print(iterator1.next() + " ");
		}
        System.out.println();
		/**
		 * ————————————————————分割线——————————————————————
		 */
		//线程不同步
		ConcurrentSkipListSet<Integer> csls2 = new ConcurrentSkipListSet<Integer>();
		PutThread2 p3 = new PutThread2(csls2);
		PutThread2 p4 = new PutThread2(csls2);
		p3.start();
		p4.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Iterator<Integer> iterator2 = csls2.iterator();
		while (iterator2.hasNext()) {
			System.out.print(iterator2.next() + " ");
		}
	}

	public static class PutThread1 extends Thread {

		private ConcurrentSkipListSet<Integer> csls1;

		public PutThread1(ConcurrentSkipListSet<Integer> csls1) {
			this.csls1 = csls1;
		}

		@Override
		public void run() {
			//同步代码块，一次只能执行一次，不能被同时调用
			synchronized (PutThread1.class){
				for (int i=0;i<10;i++){
					Random random = new Random();
					int a = random.nextInt(10);
					System.out.print(a+"\t");
					if (!csls1.contains(a)){
						csls1.add(a);
					}
				}
				System.out.println();
			}
		}
	}

	public static class PutThread2 extends Thread{
		private ConcurrentSkipListSet<Integer> csls2;

		public PutThread2(ConcurrentSkipListSet<Integer> csls2) {
			this.csls2 = csls2;
		}

		@Override
		public void run() {
			//会被两个线程同时调用执行
			for (int i=0;i<10;i++){
				Random random = new Random();
				int a = random.nextInt(8);
				System.out.print(a+"\t");
				if (!csls2.contains(a)){
					csls2.add(a);
				}
			}
			System.out.println();
		}
	}

}
