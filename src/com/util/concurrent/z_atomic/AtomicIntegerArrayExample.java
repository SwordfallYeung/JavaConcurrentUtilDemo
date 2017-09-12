package com.util.concurrent.z_atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author y15079
 * @create: 9/12/17 11:10 PM
 * @desc:
 */
public class AtomicIntegerArrayExample {
    private static int threadCount=1000;
    private static CountDownLatch countDown=new CountDownLatch(threadCount);
    static int[] values=new int[10];
    static AtomicIntegerArray ai=new AtomicIntegerArray(values);
    private static class Counter implements Runnable{
        @Override
        public void run() {
            for (int i=0;i<100;i++){
                for (int j=0;j<10;j++){
                    //所有元素+1
                    ai.getAndIncrement(j);
                }
            }
            countDown.countDown();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread[] threads=new Thread[threadCount];
        for (int i=0;i<threadCount;i++){
            threads[i]=new Thread(new Counter());
        }
        for (int i=0;i<threadCount;i++){
            threads[i].start();
        }
        countDown.await();
        for (int i=0;i<10;i++){
            System.out.println(ai.get(i)+" ");
        }
        System.out.println();
        for (int i=0;i<10;i++){
            System.out.println(values[i]+" ");
        }
    }



//    private static int threadCount=1000;
//    private static CountDownLatch countDown=new CountDownLatch(threadCount);
//    static int[] values=new int[10];
//    private static class Counter implements Runnable{
//        @Override
//        public void run() {
//            for(int i=0;i<100;i++){
//                for(int j=0;j<10;j++){//所有元素+1
//                    values[j]++;
//                }
//            }
//            countDown.countDown();
//        }
//    }
//    public static void main(String[] args) throws InterruptedException{
//        Thread[] threads=new Thread[threadCount];
//        for(int i=0;i<threadCount;i++){
//            threads[i]=new Thread(new Counter());
//        }
//        for(int i=0;i<threadCount;i++){
//            threads[i].start();;
//        }
//        countDown.await();
//        for(int i=0;i<10;i++){
//            System.out.print(values[i]+" ");
//        }
//    }
}
