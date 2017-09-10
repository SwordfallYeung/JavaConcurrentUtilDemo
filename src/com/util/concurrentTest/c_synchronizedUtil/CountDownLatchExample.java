package com.util.concurrentTest.c_synchronizedUtil;

import java.util.concurrent.CountDownLatch;

/**
 * @author y15079
 * @create: 9/10/17 6:19 PM
 * @desc:
 * 有时候会有这样的需求，多个线程同时工作，然后其中几个可以随意并发执行，
 * 但有一个线程需要等其他线程工作结束后，才能开始。
 * 举个例子，开启多个线程分块下载一个大文件，每个线程只下载固定的一截，最后由另外一个线程来拼接所有的分段，
 * 那么这时候我们可以考虑使用CountDownLatch来控制并发。
 *
 * CountDownLatch是JAVA提供在java.util.concurrent包下的一个辅助类，可以把它看成是一个计数器，
 * 其内部维护着一个count计数，只不过对这个计数器的操作都是原子操作，同时只能有一个线程去操作这个计数器，
 * CountDownLatch通过构造函数传入一个初始计数值，调用者可以通过调用CounDownLatch对象的cutDown()方法，来使计数减1；
 * 如果调用对象上的await()方法，那么调用者就会一直阻塞在这里，直到别人通过cutDown方法，将计数减到0，才可以继续执行。
 */
public class CountDownLatchExample {

    /**
     * 计数器，用来控制线程
     * 传入参数2,表示计数器计数为2
     */
    private final static CountDownLatch countDownLatch =new CountDownLatch(2);

    public static void main(String[] args) {
        //最先run SampleThread
        new SampleThread().start();
        //运行两个工作线程
        //工作线程1运行5秒
        new WorkingThread("WorkingThread1",5000).start();;
        //工作线程2运行2秒
        new WorkingThread("WorkingThread2",2000).start();
    }

    /**
     * 示例工作线程类
     */
    private static class WorkingThread extends Thread{
        private final String mThreadName;
        private final int mSleepTime;

        public WorkingThread(String mThreadName, int mSleepTime) {
            this.mThreadName = mThreadName;
            this.mSleepTime = mSleepTime;
        }

        @Override
        public void run() {
            System.out.println("["+mThreadName+"] started!");
            try {
                Thread.sleep(mSleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("["+mThreadName+"] end!");
        }
    }

    /**
     * 示例线程类
     */
    private static class SampleThread extends Thread{
        @Override
        public void run() {
            System.out.println("[SampleThread] started!");
            try {
                // 会阻塞在这里等待 mCountDownLatch 里的count变为0；
                // 也就是等待另外的WorkingThread调用countDown()
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[SampleThread] end!");
        }
    }
}
