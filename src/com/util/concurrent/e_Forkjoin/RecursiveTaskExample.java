package com.util.concurrent.e_Forkjoin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author y15079
 * @create 2017-09-11 20:31
 * @desc
 *
 *  2.RecursiveTaskExample 用于任务有返回值的场景.
 *                        ForkJoinTask的实现类，抽象类，compute方法有返回值
 **/
public class RecursiveTaskExample {


	public static class CountTask extends RecursiveTask<Integer>{
		private static final long serialVersionUID=-3611254198265061729L;

		public static final int threshold=2;

		private int start;

		private int end;

		public CountTask(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		protected Integer compute() {

			int sum = 0;

			//如果任务足够小就计算任务
			boolean canCompute = (end -start) <=threshold;
			if (canCompute){
				for (int i=start;i<=end;i++){
					sum+=i;
				}
			}else {
				//如果任务大于阈值，就分裂成两个子任务计算
				int middle=(start+end)/2;
				CountTask leftTask=new CountTask(start,middle);
				CountTask rightTask=new CountTask(middle+1,end);

				//方式一：任务量小则采用fork()和join()函数
				//执行子任务
				leftTask.fork();
				rightTask.fork();

				System.out.println(Thread.currentThread().getName());

				//等待任务执行结束合并其结果
				int leftResult=leftTask.join();
				int rightResult=rightTask.join();

				//合并子任务
				sum=leftResult+rightResult;

				//方式二：任务量大则采用集合和invokeAll()一起执行, invokeAll()是对fork()和join()方法的封装
				/*List<CountTask> list=new ArrayList<>();
				list.add(leftTask);
				list.add(rightTask);
				Collection<CountTask> countTaskCollection= invokeAll(list);

				for (CountTask c:countTaskCollection){
					try {
						sum+=c.get();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}*/
			}
			return sum;
		}
	}

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool=new ForkJoinPool();

		//生成一个计算任务，计算1+2+3+4
		CountTask task=new CountTask(1,100);

		//执行一个任务
		Future<Integer> result=forkJoinPool.submit(task);

		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
