package com.util.concurrent.z_atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author y15079
 * @create: 9/12/17 11:40 PM
 * @desc:
 *
 * volatile 实现了多线程的可见性，用于多线程对某个变量的修改 比如bool 值的变化，别的线程立即看到，可以退出循环之类的后续操作
 *
 * 但是volatile 不是线程安全，对其修饰的变量++ 加法减法等操作 保证不了线程安全
 *
 * 而AtomicIntegerfieldupdater  实现了普通变量的原子操作 ，加法减都可以
 */
public class AtomicIntegerfieldupdaterExample {

    public static class Candidate{
        int id;
        volatile int score;//共享变量
    }

    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater=AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");

    //检查Updater是否工作正确
    public static AtomicInteger allScore=new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        final Candidate stu=new Candidate();
        Thread[] t=new Thread[100];
        for (int i=0;i<100;i++){
            t[i]=new Thread(){
                public void run(){
                    scoreUpdater.incrementAndGet(stu);
                    allScore.incrementAndGet();
                }
            };
            t[i].start();
        }
        for (int i=0;i<100;i++){
            t[i].join();
            System.out.println("score="+stu.score);
            System.out.println("allScore="+allScore);
        }
    }
}
