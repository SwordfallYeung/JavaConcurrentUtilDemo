package com.util.concurrentTest.a_block;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author y15079
 * @create 2017-08-30 10:43
 * @desc
 *延迟队列 DelayQueue 并且元素是Delay的子类，保证元素在达到一定时间后才可以取得到
 * DelayQueue 实现了 BlockingQueue 接口。
 * DelayQueue 对元素进行持有直到一个特定的延迟到期。
 *
 * DelayQueue 将会在每个元素的 getDelay() 方法返回的值的时间段之后才释放掉该元素。
 * 如果返回的是 0 或者负值，延迟将被认为过期，该元素将会在 DelayQueue 的下一次 take  被调用的时候被释放掉。
 **/
public class DelayQueueExample {
	public static void main(String[] args) throws Exception {
		//存储事件的延迟队列
		DelayQueue<Event> queue = new DelayQueue<>();

		//线程数组
		Thread threads[] = new Thread[5];

		//创建5个任务对象，并且放在不同的线程中去执行
		for (int i = 0; i<threads.length; i++ ){
			Task task = new Task(i+1, queue);
			threads[i] = new Thread(task);
		}

		// 启动线程
		for (Thread thread : threads) {
			thread.start();
		}

		// 等待5个任务的完成
		for (Thread thread : threads) {
			thread.join();
		}

		System.out.printf("queue.size %d events\n", queue.size());

		//输出结果
		do{
			int counter = 0;
			Event event;
			do {
				// 取队列中的所有数据，如果此队列没有已到期延迟的元素，则返回 null。
				//程序开始前，没有延期的事件，返回null。
				// 每隔1秒，就有100个事件同时延期
				event = queue.poll();
				if (event!=null){
					counter++;
				}
			}while (event!=null);
			System.out.printf("At %s you have read %d events\n", new Date(), counter);
			System.out.printf("queue.size %d events\n", queue.size());
			TimeUnit.MILLISECONDS.sleep(500);
		}while (queue.size() > 0);

	}
}



/**
 * 任务类，其事件存储在一个延迟队列中
 */
class Task implements Runnable{

	/**
	 * 任务编号
	 */
	private int id;

	/**
	 * 存储事件的任务队列
	 */
	private DelayQueue<Event> queue;

	public Task(int id, DelayQueue<Event> queue) {
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		Date now = new Date();
		Date delay = new Date();

		delay.setTime(now.getTime() + (id * 1000));

		System.out.printf("Thread %s: %s\n", id, delay);

		//给100事件定义相同的时间延迟
		for (int i = 0; i < 100; i++) {
			Event event = new Event(delay);
			queue.add(event);
		}
	}
}

/**
 * 事件类，激活了延迟队列接口
 */
class Event implements Delayed{
	/**
	 * 激活事件的时间
	 */
	private Date startDate;

	/**
	 * 构造函数
	 * @param startDate
	 */
	public Event(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 比较两个事件
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Delayed o) {
		long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		if (result < 0){
			return -1;
		}else if (result > 0){
			return 1;
		}
		return 0;
	}

	/**
	 * 返回离激活事件还剩余的毫秒数
	 * @param unit
	 * @return
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		Date now = new Date();
		long diff = startDate.getTime() - now.getTime();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}
}