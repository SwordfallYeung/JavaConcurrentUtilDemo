package com.util.concurrent.c_synchronizedUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author y15079
 * @create: 9/10/17 6:56 PM
 * @desc:
 *
 * CyclicBarrier和CountDownLatch一样，都是关于线程的计数器。用法略有不同
 *
 * 1.CyclicBarrier初始化时规定一个数目，然后计算调用了CyclicBarrier.await()进入等待的线程数。
 *   当线程数达到了这个数目时，所有进入等待状态的线程被唤醒并继续。
 * 2.CyclicBarrier就象它名字的意思一样，可看成是个障碍， 所有的线程必须到齐后才能一起通过这个障碍。
 * 3.CyclicBarrier初始时还可带一个Runnable的参数， 此Runnable任务在CyclicBarrier的数目达到后，所有其它线程被唤醒前被执行。
 */
public class CyclicBarrierExample {
    private static final int THEARD_NUM=5;

    public static void main(String[] args) {
        CyclicBarrier cb =new CyclicBarrier(THEARD_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside Barrier");
            }
        });

        for (int i=0;i<THEARD_NUM;i++){
            new Thread(new WorkerThread(cb)).start();
        }
    }

    public static class WorkerThread implements Runnable{
        CyclicBarrier barrier;

        public WorkerThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("Worker's waiting");
                //线程在这里等待，直到所有线程都到达barrier
                barrier.await();
                System.out.println("ID:"+Thread.currentThread().getId()+" working");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
