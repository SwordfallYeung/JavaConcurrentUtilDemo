package com.util.concurrent.z_atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author y15079
 * @create 2017-09-13 14:28
 * @desc
 *
 * /**
 * 相关方法列表
 * AtomicReference#compareAndSet(Object, Object) 对比设置值，参数1：原始值，参数2：修改目标引用
 * AtomicReference#getAndSet(Object) 将引用的目标修改为设置的参数，直到修改成功为止，返回修改前的引用
 *
 **/
public class AtomicReferenceExample {

	public final static AtomicReference<String> atomicReference=new AtomicReference<String>("abc");

	public static void main(String[] args) {
		for (int i=0;i<100;i++){
			final int num=i;
			new Thread(){
				@Override
				public void run() {
					try {
						Thread.sleep(Math.abs((int)(Math.random()*100)));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (atomicReference.compareAndSet("abc",new String("abcd"))){
						System.out.println("我是线程"+num+",我获得类锁进行对象的修改"+atomicReference.get());
					}
				}
			}.start();
		}
	}
}
