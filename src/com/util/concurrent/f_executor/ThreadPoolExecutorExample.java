package com.util.concurrent.f_executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author y15079
 * @create: 9/15/17 1:16 AM
 * @desc:
 *
 * Future.class，异步计算的结果对象，get方法会阻塞线程直至真正的结果返回
Callable.class，用于异步执行的可执行对象，call方法有返回值，它和Runnable接口很像，都提供了在其他线程中执行的方法，二者的区别在于：

Runnable没有返回值，Callable有
Callable的call方法声明了异常抛出，而Runnable没有

RunnableFuture.class，实现自Runnable和Future的子接口，成功执行run方法可以完成它自身这个Future并允许访问其结果，它把任务执行和结果对象放到一起了
FutureTask.class，RunnableFuture的实现类，可取消的异步计算任务，仅在计算完成时才能获取结果，
一旦计算完成，就不能再重新开始或取消计算；它的取消任务方法cancel(boolean mayInterruptIfRunning)接收一个boolean参数表示在取消的过程中是否需要设置中断


Executor.class，执行提交任务的对象，只有一个execute方法
Executors.class，辅助类和工厂类，帮助生成下面这些ExecutorService
ExecutorService.class，Executor的子接口，管理执行异步任务的执行器，AbstractExecutorService提供了默认实现
AbstractExecutorService.class，ExecutorService的实现类，提供执行方法的默认实现，包括：

① submit的几个重载方法，返回Future对象，接收Runnable或者Callable参数
② invokeXXX方法，这类方法返回的时候，任务都已结束，即要么全部的入参task都执行完了，要么cancel了

ThreadPoolExecutor.class，线程池，AbstractExecutorService的子类，除了从AbstractExecutorService继承下来的①、②两类提交任务执行的方法以外，还有：

③ 实现自Executor接口的execute方法，接收一个Runnable参数，没有返回值

RejectedExecutionHandler.class，当任务无法被执行的时候，定义处理逻辑的地方，前面已经提到过了
ThreadFactory.class，线程工厂，用于创建线程


理解Executor，Executors，ExecutorService，AbstractExecutorService，ThreadPoolExecutor的关系，
可以参考：http://blog.csdn.net/suifeng3051/article/details/49444177

 */
public class ThreadPoolExecutorExample {
    public static class findMaxTask implements Callable<Integer>{
        private int start;
        private int end;
        private Integer[] data;

        public findMaxTask(int start, int end, Integer[] data) {
            this.start = start;
            this.end = end;
            this.data = data;
        }

        @Override
        public Integer call() throws Exception {
            int maxNum = Integer.MIN_VALUE;
            //System.out.println(Thread.currentThread());
            //Thread.sleep(3000);
            for (int i = start; i < end; i ++) {
                maxNum = Math.max(maxNum, data[i]);
            }
            return maxNum;
        }
    }

    public static void main(String[] args) throws Exception{

        Integer[] arr=new Integer[]{1,8,5,3,9,6,2,0,7,4};

        findMaxTask task1 = new findMaxTask(0, arr.length / 2, arr);
        findMaxTask task2 = new findMaxTask(arr.length / 2, arr.length, arr);
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> ans1 = service.submit(task1);
        Future<Integer> ans2 = service.submit(task2);
        int maxNum1 = ans1.get();
        int maxNum2 = ans2.get();
        System.out.println("maxNum1 = " + maxNum1);
        System.out.println("maxNum2 = " + maxNum2);
        service.shutdown();
        System.out.println(Math.max(maxNum1, maxNum2));
    }
}
