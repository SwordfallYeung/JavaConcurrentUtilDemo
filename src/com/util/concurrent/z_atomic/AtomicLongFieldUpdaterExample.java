package com.util.concurrent.z_atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * @author y15079
 * @create 2017-09-13 13:59
 * @desc
 *
 *
 * 可以直接访问对应的变量，进行修改和处理
 * 条件：要在可访问的区域内，如果是private或挎包访问default类型以及非父亲类的protected均无法访问到
 * 其次访问对象不能是static类型的变量（因为在计算属性的偏移量的时候无法计算），也不能是final类型的变量（因为根本无法修改），必须是普通的成员变量
 *
 * 方法（说明上和AtomicInteger几乎一致，唯一的区别是第一个参数需要传入对象的引用）
 *  AtomicIntegerFieldUpdater#addAndGet(Object, int)
 *  AtomicIntegerFieldUpdater#compareAndSet(Object, int, int)
 *  AtomicIntegerFieldUpdater#decrementAndGet(Object)
 *  AtomicIntegerFieldUpdater#incrementAndGet(Object)
 *
 *  AtomicIntegerFieldUpdater#getAndAdd(Object, int)
 *  AtomicIntegerFieldUpdater#getAndDecrement(Object)
 *  AtomicIntegerFieldUpdater#getAndIncrement(Object)
 *  AtomicIntegerFieldUpdater#getAndSet(Object, int)
 *
 **/
public class AtomicLongFieldUpdaterExample {
	static class A{
		volatile long intValue=100;
	}

	public final static AtomicLongFieldUpdater<A> atomicLongUpdater=AtomicLongFieldUpdater.newUpdater(A.class,"intValue");

	public static void main(String[] args) {
		final A a=new A();
		for (int i=0;i<100;i++){
			final int num=i;
			new Thread(){
				@Override
				public void run() {
					if (atomicLongUpdater.compareAndSet(a,100,200)){
						System.out.println("我是线程：" + num + " 我对对应的值做了修改！");
					}
				}
			}.start();
		}
	}



}
