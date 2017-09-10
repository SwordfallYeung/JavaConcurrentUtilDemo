package com.util.concurrentTest.c_synchronizedUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author y15079
 * @create: 9/10/17 7:31 PM
 * @desc:
 * 一个计数信号量。
 * 从概念上讲，信号量维护了一个许可集。
 * 如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。
 * 每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。
 * 但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。
 * 拿到信号量的线程可以进入代码，否则就等待。
 * 通过acquire()和release()获取和释放访问许可。
 */
public class SemaphoreExample {

    public static void main(String[] args) {
        //线程池
        ExecutorService exec= Executors.newCachedThreadPool();
        //只能5个线程同时访问
        final Semaphore semp=new Semaphore(5);
        //模拟20个客户端访问
        for (int index=0;index<20;index++){
            final int No=index;
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取许可
                        semp.acquire();
                        System.out.println("Accessing: "+No);
                        Thread.sleep((long)(Math.random()*10000));
                        //访问完后，释放，如果屏蔽下面的语句，则在控制台只能5条记录，之后线程一直阻塞
                        semp.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(runnable);
        }
        //退出线程池
        exec.shutdown();
    }
}
