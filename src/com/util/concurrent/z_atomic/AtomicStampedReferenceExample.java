package com.util.concurrent.z_atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author y15079
 * @create 2017-09-13 15:36
 * @desc
 *
 * AtomicStampedReference  相当于一个[引用,integer]的二元组 ,可用来作为带版本号的原子引用
 *
 * 运用AtomicStampedReference解决ABA问题
 **/
public class AtomicStampedReferenceExample {
     static AtomicStampedReference<Integer> money=new AtomicStampedReference<Integer>(19,0);

	public static void main(String[] args) {
		for (int i=0;i<3;i++){
			final int timestamp=money.getStamp();
			new Thread(){
				@Override
				public void run() {
					while (true){
						Integer m=money.getReference();
						if (m<20){
							if (money.compareAndSet(m,m+20,timestamp,timestamp+1)){
								System.out.println("充值成功，余额:"+money.getReference()+"，时间戳："+money.getStamp());
								break;
							}
						}else {
							break;
						}
					}
				}
			}.start();
		}

		new Thread(){
			@Override
			public void run() {
				for (int i=0;i<100;i++){
					while (true){
						int timestamp=money.getStamp();
						Integer m=money.getReference();
						if (m>10){
							if (money.compareAndSet(m,m-10,timestamp,timestamp+1)){
								System.out.println("消费10元，余额："+money.getReference()+"，时间戳："+money.getStamp());
								break;
							}else {
								break;
							}
						}
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}.start();
	}
}
