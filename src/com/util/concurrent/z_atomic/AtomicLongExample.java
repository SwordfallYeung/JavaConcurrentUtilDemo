package com.util.concurrent.z_atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author y15079
 * @create: 9/13/17 12:05 AM
 * @desc:
 * 线程安全的atomicLong的使用
 */
public class AtomicLongExample {

    public static class Counter{
        private static AtomicLong counter=new AtomicLong(0);
        public static long addOne(){
            return counter.incrementAndGet();
        }

        public static void main(String[] args) {
            for (int i=0;i<100;i++){
                Thread thread=new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(100);
                            if(Counter.addOne() == 100){
                                System.out.println("counter = 100");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        }
    }
}
