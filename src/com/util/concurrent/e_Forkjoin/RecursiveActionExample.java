package com.util.concurrent.e_Forkjoin;

import javax.xml.ws.spi.Invoker;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author y15079
 * @create 2017-09-11 19:18
 * @desc 1.RecursiveAction 用于任务没有返回值的场景
 * ForkJoinTask的实现类，抽象类，compute方法无返回值
 * <p>
 * SortTask 首先通过 partition() 方法将数组分成两个部分。随后，两个子任务将被生成并分别排序数组的两个部分。
 * 当子任务足够小时，再将其分割为更小的任务反而引起性能的降低。因此，这里我们使用一个 THRESHOLD，限定在子任务规模较小时，使用直接排序，
 * 而不是再将其分割成为更小的任务。RecursiveAction 提供的方法 invokeAll()。
 * 它表示：启动所有的任务，并在所有任务都正常结束后返回。如果其中一个任务出现异常，则其它所有的任务都取消。
 * invokeAll() 的参数还可以是任务的数组。
 * execute()：将 ForkJoinTask 类的对象提交给 ForkJoinPool，ForkJoinPool 将立刻开始执行 ForkJoinTask。
 * shutdown()：执行此方法之后，ForkJoinPool 不再接受新的任务，但是已经提交的任务可以继续执行。
 * 如果希望立刻停止所有的任务，可以尝试 shutdownNow() 方法。
 * awaitTermination()：阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束。
 **/
public class RecursiveActionExample {

	public static void main(String[] args) throws Exception {
		long[] array = new long[50];
		Random random = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextLong() % 100;//for demo only
		}
		ForkJoinTask sort = new SortTask(array);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.execute(sort);//将 ForkJoinTask 类的对象提交给 ForkJoinPool，ForkJoinPool 将立刻开始执行 ForkJoinTask。
		forkJoinPool.shutdown();//执行此方法之后，ForkJoinPool 不再接受新的任务，但是已经提交的任务可以继续执行。
		forkJoinPool.awaitTermination(30, TimeUnit.SECONDS);//阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束。
	}

	public static class SortTask extends RecursiveAction {
		final long[] array;
		final int low;
		final int high;
		private int THRESHOLD = 30;//临界值

		public SortTask(long[] array) {
			this.array = array;
			this.low = 0;
			this.high = array.length - 1;
		}

		public SortTask(long[] array, int low, int high) {
			this.array = array;
			this.low = low;
			this.high = high;
		}

		@Override
		protected void compute() {
			if (high - low < THRESHOLD) {
				Arrays.sort(array, low, high + 1);
				System.out.println("array,NO \n" + Arrays.toString(array));
			} else {
				int pivot = partition(array, low, high);
				System.out.println("\npivot=" + pivot + ",low=" + low + ",high=" + high);
				System.out.println("array" + Arrays.toString(array));
				invokeAll(new SortTask(array, low, pivot - 1), new SortTask(array, pivot + 1, high));
			}
		}

		//排序
		private int partition(long[] array, int low, int high) {
			int i = low - 1;
			for (int j = low; j < high; j++) {
				if (array[j] <= array[high]) {
					i++;
					swap(array, i, j);
				}
			}
			swap(array, i + 1, high);
			return i + 1;
		}

		private void swap(long[] array, int i, int j) {
			if (i != j) {
				long temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
	}
}
