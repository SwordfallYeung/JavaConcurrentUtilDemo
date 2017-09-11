package com.util.concurrent.d_Forkjoin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author y15079
 * @create: 9/11/17 12:39 AM
 * @desc:
 *
 * Fork-join框架
 * 这是一个JDK7引入的并行框架，它把流程划分成fork（分解）+join（合并）两个步骤（怎么那么像MapReduce？），
 * 传统线程池来实现一个并行任务的时候，经常需要花费大量的时间去等待其他线程执行任务的完成，
 * 但是fork-join框架使用work stealing技术缓解了这个问题：
 *
 * 1.每个工作线程都有一个双端队列，当分给每个任务一个线程去执行的时候，这个任务会放到这个队列的头部；
 * 2.当这个任务执行完毕，需要和另外一个任务的结果执行合并操作，可是那个任务却没有执行的时候，
 *   不会干等，而是把另一个任务放到队列的头部去，让它尽快执行；
 * 3.当工作线程的队列为空，它会尝试从其他线程的队列尾部偷一个任务过来；
 * 4.取得的任务可以被进一步分解。
 *
 * Fork/Join框架主要有以下两个类组成.:
 * 1.ForkJoinPool 这个类实现了ExecutorService接口和工作窃取算法(Work-Stealing Algorithm).
 *                它管理工作者线程,并提供任务的状态信息,以及任务的执行信息.
 *                ForkJoin框架的任务池，ExecutorService的实现类
 * 2.ForkJoinTask 这个类是一个将在ForkJoinPool执行的任务的基类.
 *                Future的子类，框架任务的抽象
 *
 * ForkJoinWorkerThread 工作线程
 *
 * Fork/Join框架提供了在一个任务里执行fork()和join()操作的机制和控制任务状态的方法.
 * 通常,为了实现Fork/Join任务,需要实现一个以下两个类之一的子类:
 * 1.RecursiveAction 用于任务没有返回值的场景
 *                   ForkJoinTask的实现类，抽象类，compute方法无返回值
 * 2.RecursiveTask 用于任务有返回值的场景.
 *                  ForkJoinTask的实现类，抽象类，compute方法有返回值
 *
 *
 * 首先我们要定义个赚钱任务 MakeMoneyTask,如果要赚钱的目标小于最小目标,比如一万,
 * 那么就自己去完成,否则,就把任务分给小弟们去做.
 */
public class ForkJoinExample {

    public static void main(String[] args) throws Exception{
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTask<Integer> task=forkJoinPool.submit(new MakeMoneyTask(10000));
        do {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!task.isDone());
        forkJoinPool.shutdown();
        System.out.println(task.get());
    }

    public static class MakeMoneyTask extends RecursiveTask<Integer>{
        private static final int MIN_GOAL_MONEY=5000;
        private static final AtomicLong employeeNo=new AtomicLong();

        private int goalMoney;
        private String name;

        public MakeMoneyTask(int goalMoney) {
            this.goalMoney = goalMoney;
            this.name = "员工"+employeeNo.getAndIncrement()+"号";
        }

        @Override
        protected Integer compute() {
            if (this.goalMoney<MIN_GOAL_MONEY){
                System.out.println(name+":老板交代了，要赚"+goalMoney+ " 元,为了买车买房,加油吧....");
                return makeMoney();
            }else {
                int subThreadCount=ThreadLocalRandom.current().nextInt(5)+2;
                System.out.println(name+":上级要我攒"+goalMoney+",有点多，没事让我"+subThreadCount+"个手下去完成吧，" +
                        "每人攒个"+Math.ceil(goalMoney*1.0/subThreadCount)+"元应该没问题...");

                //fork，分散
                List<MakeMoneyTask> tasks=new ArrayList<>();
                for (int i=0;i<subThreadCount;i++){
                    tasks.add(new MakeMoneyTask(goalMoney/subThreadCount));
                }
                Collection<MakeMoneyTask> makeMoneyTasks=invokeAll(tasks);
                int sum =0;
                //join，集合
                for (MakeMoneyTask moneyTask:makeMoneyTasks){
                    try {
                        sum+=moneyTask.get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.println(name+":恩，不错，效率还可以，终于攒到"+sum+"元,赶紧邀功去....");
                return sum;
            }
        }

        private Integer makeMoney(){
            int sum=0;
            int day=1;
            try {
                while (true){
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500));
                    int money=ThreadLocalRandom.current().nextInt(MIN_GOAL_MONEY/3);
                    System.out.println(name+":在第"+(day++)+"天攒了"+money);
                    sum+=money;
                    if (sum>=goalMoney){
                        System.out.println(name+":终于攒到"+sum+"元，可以交差了...");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sum;
        }
    }
}


