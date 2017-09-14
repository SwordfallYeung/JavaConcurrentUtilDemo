package com.util.concurrent.y_lock;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author y15079
 * @create: 9/14/17 11:39 PM
 * @desc:
 *
 * 使用lock/condition实现生产者消费者模式如下：
 *
 */
public class ReentrantLockExample2 {
    public static class Buffer{
        private final Lock lock;
        private final Condition notFull;
        private final Condition notEmpty;
        private int maxSize;
        private List<Date> storage;

        public Buffer(int size) {
            //使用锁lock，并且创建两个condition，相当于两个阻塞队列
            lock=new ReentrantLock();
            notFull=lock.newCondition();
            notEmpty=lock.newCondition();
            maxSize=size;
            storage=new LinkedList<>();
        }

        public void put(){
            lock.lock();
            try {
                while (storage.size()==maxSize){
                    //如果队列满了
                    System.out.println(Thread.currentThread().getName()+": wait\n");
                    notFull.await();
                }
                storage.add(new Date());
                System.out.println(Thread.currentThread().getName()+": put:"+storage.size()+"\n");
                Thread.sleep(1000);
                notEmpty.signalAll();//唤醒消费线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void take(){
            lock.lock();
            try {
                while (storage.size()==0){
                    //如果队列满了
                    System.out.println(Thread.currentThread().getName()+":wait\n");
                    notEmpty.await();//阻塞消费线程
                }
                Date d=((LinkedList<Date>)storage).poll();
                System.out.print(Thread.currentThread().getName()+": take:"+storage.size()+ "\n");
                Thread.sleep(1000);
                notFull.signalAll();//唤醒生产线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class Producer implements Runnable{
        private Buffer buffer;

        public Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true){
                buffer.put();
            }
        }
    }

    public static class Consumer implements Runnable{
        private Buffer buffer;

        public Consumer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true){
                buffer.take();
            }
        }
    }

    public static void main(String[] args) {
        Buffer buffer=new Buffer(10);
        Producer producer=new Producer(buffer);
        Consumer consumer=new Consumer(buffer);
        for(int i=0;i<3;i++){
            new Thread(producer,"producer-"+i).start();
        }
        for(int i=0;i<3;i++){
            new Thread(consumer,"consumer-"+i).start();
        }
    }
}
