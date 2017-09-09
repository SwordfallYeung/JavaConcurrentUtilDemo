package com.util.concurrentTest.c_other;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author y15079
 * @create 2017-09-09 15:25
 * @desc
 * ConcurrentSkipListMap是线程安全的有序的哈希表，适用于高并发的场景
 * ConcurrentSkipListMap和TreeMap，它们虽然都是有序的哈希表。
 * 但是，第一，它们的线程安全机制不同，TreeMap都是非线程安全的，
 * 而ConcurrentSkipListMap是线程安全的。第二，ConcurrentSkipListMap是通过跳表实现的，
 * 而TreeMap是通过红黑树实现的。
 * 关于跳表（Skip List）,它是平衡树的一种替代的数据结构，但是和红黑树不相同的是，跳表
 * 对于树的平衡的实现是基于一种随机化的算法的，这样也就是说跳表的插入和删除的工作是比较简单的
 **/
public class ConcurrentSkipListMapExample {

	private static Map<String,String> map=new ConcurrentSkipListMap<String,String>();

	public static void main(String[] args) {

		//同时启动两个线程对map进行操作
		new MyThread("a").start();
		new MyThread("b").start();
	}

	private static void printAll(){

		String key,value;
		Iterator iter=map.entrySet().iterator();
		while (iter.hasNext()){
			Map.Entry entry=(Map.Entry) iter.next();
			key=(String)entry.getKey();
			value=(String)entry.getValue();
			System.out.print("("+key+", "+value+"), ");
		}
		System.out.println();
	}

	private static class MyThread extends Thread{
		public MyThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			int i=0;
			while (i++<6){
				//线程名+序号
				String val=Thread.currentThread().getName()+i;
				map.put(val,"0");
				//通过iterator遍历map
				printAll();
			}
		}
	}
}
