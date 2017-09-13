package com.util.concurrent.y_lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author y15079
 * @create: 9/14/17 12:56 AM
 * @desc:
 *
 * LockSupport是JDK中比较底层的类，用来创建锁和其他同步工具类的基本线程阻塞原语。
 * java锁和同步器框架的核心 AQS: AbstractQueuedSynchronizer，
 * 就是通过调用 LockSupport .park()和 LockSupport .unpark()实现线程的阻塞和唤醒 的。
 */
public class LockSupportExample {

    public static void main(String[] args) {

    }

    //运行该代码，可以发现主线程一直处于阻塞状态。因为 许可默认是被占用的 ，
    // 调用park()时获取不到许可，所以进入阻塞状态。
    public void testThree(){
        LockSupport.park();
        System.out.println("block.");
    }

    //先释放许可，再获取许可，主线程能够正常终止。
    // LockSupport许可的获取和释放，一般来说是对应的，
    // 如果多次unpark，只有一次park也不会出现什么问题，结果是许可处于可用状态。
    public void testTwo(){
        Thread thread = Thread.currentThread();
        LockSupport.unpark(thread);//释放许可
        LockSupport.park();// 获取许可
        System.out.println("b");
    }

    //这段代码打印出a和b，不会打印c，因为第二次调用park的时候，线程无法获取许可出现死锁。
    public void testOne(){
        Thread thread = Thread.currentThread();

        LockSupport.unpark(thread);

        System.out.println("a");
        LockSupport.park();
        System.out.println("b");
        LockSupport.park();
        System.out.println("c");
    }

    //最终线程会打印出thread over.true。这说明 线程如果因为调用park而阻塞的话，能够响应中断请求(中断状态被设置成true)，
    // 但是不会抛出InterruptedException 。
    public void testFour() throws Exception{

        Thread t = new Thread(new Runnable()
        {
            private int count = 0;

            @Override
            public void run()
            {
                long start = System.currentTimeMillis();
                long end = 0;

                while ((end - start) <= 1000)
                {
                    count++;
                    end = System.currentTimeMillis();
                }

                System.out.println("after 1 second.count=" + count);

                //等待或许许可
                LockSupport.park();
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

            }
        });

        t.start();

        Thread.sleep(2000);

        // 中断线程
        t.interrupt();


        System.out.println("main over");
    }
}
