package com.util.concurrent.e_Forkjoin;

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

	public static void main(String[] args) {

	}

	public class Fibonacci extends RecursiveTask{
		final int n;

		public Fibonacci(int n) {
			this.n = n;
		}

		protected int compute(int small) {
			final int[] results={ 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
			return results[small];
		}

		public Integer compute(){
			if (n<=10){
				return compute(n);
			}
			Fibonacci f1=new Fibonacci(n-1);
			Fibonacci f2=new Fibonacci(n-2);
			f1.fork();
			f2.fork();
			return f1.join()+f2.join();
		}
	}

}
