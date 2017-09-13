package com.util.concurrent.y_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * @author y15079
 * @create: 9/14/17 12:38 AM
 * @desc:
 *
 * 为实现依赖于先进先出队列的阻塞锁和相关同步器（信号量、事件等等）提供的一个框架，它依靠int值来表示状态
 *
 * 提供了一个基于FIFO队列，可以用于构建锁或者其他相关同步装置的基础框架。
 * 该同步器（以下简称同步器）利用了一个int来表示状态，期望它能够成为实现大部分同步需求的基础。
 * 使用的方法是继承，子类通过继承同步器并需要实现它的方法来管理其状态，管理的方式就是通过类似acquire和release的方式来操纵状态。
 * 然而多线程环境中对状态的操纵必须确保原子性，因此子类对于状态的把握，需要使用这个同步器提供的以下三个方法对状态进行操作：

   java.util.concurrent.locks.AbstractQueuedSynchronizer.getState()
   java.util.concurrent.locks.AbstractQueuedSynchronizer.setState(int)
   java.util.concurrent.locks.AbstractQueuedSynchronizer.compareAndSetState(int, int)

   子类推荐被定义为自定义同步装置的内部类，同步器自身没有实现任何同步接口，它仅仅是定义了若干acquire之类的方法来供使用。
   该同步器即可以作为排他模式也可以作为共享模式，当它被定义为一个排他模式时，其他线程对其的获取就被阻止，而共享模式对于多个线程获取都可以成功。

 *下面通过一个排它锁的例子来深入理解一下同步器的工作原理，而只有掌握同步器的工作原理才能够更加深入了解其他的并发组件。
 *排他锁的实现，一次只能一个线程获取到锁。
 */
public class AbstractQueuedSynchronizerExample {

    //内容类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer{
        //是否处于占用状态
        protected boolean isHeldExclusively(){
            return getState()==1;
        }
        //当状态为0的时候获取锁
        public boolean tryAcquire(int acquires){
            assert acquires==1;
            if (compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //释放锁，将状态设置为0
        protected boolean tryRelease(int releases){
            assert releases==1;
            if (getState() == 0) throw new IllegalMonitorStateException();

            setExclusiveOwnerThread(null);

            setState(0);

            return true;
        }

        // 返回一个Condition，每个condition都包含了一个condition队列
        Condition newCondition() { return new ConditionObject(); }
    }
   // 仅需要将操作代理到Sync上即可

    private final Sync sync = new Sync();

    public void lock()                { sync.acquire(1); }

    public boolean tryLock()          { return sync.tryAcquire(1); }

    public void unlock()              { sync.release(1); }

    public Condition newCondition()   { return sync.newCondition(); }

    public boolean isLocked()         { return sync.isHeldExclusively(); }
    public boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit)
        throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));

    }


    public static void main(String[] args) {

    }
}
