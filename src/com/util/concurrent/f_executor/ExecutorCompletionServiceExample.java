package com.util.concurrent.f_executor;

import java.util.concurrent.*;

/**
 * @author y15079
 * @create: 9/15/17 2:42 AM
 * @desc:
 *
 * CompletionService.class，它是对ExecutorService的改进，
 * 因为ExecutorService只是负责处理任务并把每个任务的结果对象（Future）给你，
 * 却并没有说要帮你“管理”这些结果对象，这就意味着你得自己建立一个对象容器存放这些结果对象，很麻烦；
 * CompletionService像是集成了一个Queue的功能，你可以调用Queue一样的方法——poll来获取结果对象，
 * 还有一个方法是take，它和poll差不多，区别在于take方法在没有结果对象的时候会返回空，而poll方法会block住线程直到有结果对象返回
ExecutorCompletionService.class，是CompletionService的实现类
 */
public class ExecutorCompletionServiceExample {

   public static class Task implements Callable<String>{
        private int i;

        public Task(int i){
            this.i = i;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(3000);
            return Thread.currentThread().getName() + "执行完任务：" + i;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int numThread = 5;
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
        for(int i = 0;i<numThread;i++ ){
            completionService.submit(new Task(i));
        }
    }
}
