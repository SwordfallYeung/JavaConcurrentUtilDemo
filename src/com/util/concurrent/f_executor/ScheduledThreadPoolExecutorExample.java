package com.util.concurrent.f_executor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author y15079
 * @create: 9/15/17 2:35 AM
 * @desc:
 *
 * Delayed.class，延迟执行的接口，只有long getDelay(TimeUnit unit)这样一个接口方法
ScheduledFuture.class，Delayed和Future的共同子接口
RunnableScheduledFuture.class，ScheduledFuture和RunnableFuture的共同子接口，增加了一个方法boolean isPeriodic()，返回它是否是一个周期性任务，一个周期性任务的特点在于它可以反复执行
ScheduledExecutorService.class，ExecutorService的子接口，它允许任务延迟执行，相应地，它返回ScheduledFuture
ScheduledThreadPoolExecutor.class，可以延迟执行任务的线程池
 *
 */
public class ScheduledThreadPoolExecutorExample {

    public static class WorkerThread implements Runnable{
        private String command;

        public WorkerThread(String s){
            this.command=s;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" Start. Time = "+new Date());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" End. Time = "+new Date());
        }

        @Override
        public String toString(){
            return this.command;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        //schedule to run after sometime
        System.out.println("Current Time = "+new Date());
        for(int i=0; i<3; i++){
            Thread.sleep(1000);
            WorkerThread worker = new WorkerThread("do heavy processing");

            scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);
        }

        //add some delay to let some threads spawn by scheduler
        Thread.sleep(30000);

        scheduledThreadPool.shutdown();
        while(!scheduledThreadPool.isTerminated()){
            //wait for all tasks to finish
        }
        System.out.println("Finished all threads");
    }

}
