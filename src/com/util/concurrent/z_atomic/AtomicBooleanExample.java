package com.util.concurrent.z_atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author y15079
 * @create: 9/12/17 12:31 AM
 * @desc:
 *
 * AtomicBoolean是java.util.concurrent.atomic包下的原子变量，这个包里面提供了一组原子类。其基本的特性就是在多线程环境下，当有多个线程同时执行这些类的实例包含的方法时，具有排他性，即当某个线程进入方法，执行其中的指令时，不会被其他线程打断，而别的线程就像自旋锁一样，一直等到该方法执行完成，才由JVM从等待队列中选择一个另一个线程进入，这只是一种逻辑上的理解。
 *
 * AtomicBoolean，在这个Boolean值的变化的时候不允许在之间插入，保持操作的原子性。
 * 方法和举例：compareAndSet(boolean expect, boolean update)。这个方法主要两个作用
 * 1. 比较AtomicBoolean和expect的值，如果一致，执行方法内的语句。其实就是一个if语句
 * 2. 把AtomicBoolean的值设成update
 * 比较最要的是这两件事是一气呵成的，这连个动作之间不会被打断，任何内部或者外部的语句都不可能在两个动作之间运行。为多线程的控制提供了解决的方案。
 */
public class AtomicBooleanExample {

    public static class BarWorker implements Runnable{

        private AtomicBoolean atomicBoolean;

//        private static AtomicBoolean atomicBoolean=new AtomicBoolean(false);

        private String name;

        public BarWorker(String name,AtomicBoolean atomicBoolean) {
            this.name = name;
            this.atomicBoolean = atomicBoolean;
        }


//        public BarWorker(String name) {
//            this.name = name;
//        }

        @Override
        public void run() {
            if (atomicBoolean.compareAndSet(false,true)){
                try {
                    System.out.println(name+" enter");
                    System.out.println(name+ " working");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(name+" leave");
                atomicBoolean.set(false);
            }else {
                System.out.println(name+" give up");
            }
        }
    }

    public static void main(String[] args) {
        AtomicBoolean atomicB1=new AtomicBoolean(false);
        AtomicBoolean atomicB2=new AtomicBoolean(true);
        BarWorker barWorker1=new BarWorker("bar1",atomicB1);
        BarWorker barWorker2=new BarWorker("bar2",atomicB1);
        BarWorker barWorker3=new BarWorker("bar3",atomicB1);

//          BarWorker barWorker2=new BarWorker("bar2");
//          BarWorker barWorker3=new BarWorker("bar3");

        new Thread(barWorker1).start();
        new Thread(barWorker2).start();
        new Thread(barWorker3).start();

    }



}
