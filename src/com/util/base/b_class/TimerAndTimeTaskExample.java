package com.util.base.b_class;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author y15079
 * @create 2017-09-26 19:31
 * @desc
 *
 *   Timer是JDK中的定时调度类，主要用来定时触发任务：
 *   Timer是调度控制器，TimerTask是可调度的任务：
 *  其基本处理模型是单线程调度的任务队列模型，Timer不停地接受调度任务，所有任务接受Timer调度后加入TaskQueue,
    TimerThread不停地去TaskQueue中取任务来执行.
 *
 * Timer常见方法：
 * [1] public void schedule(TimerTask task, long delay)
      这个方法是调度一个task，经过delay(ms)后开始进行调度，仅仅调度一次。
   [2] public void schedule(TimerTask task, Date time)
      在指定的时间点time上调度一次
   [3] public void schedule(TimerTask task, long delay, long period)
      这个方法是调度一个task，在delay（ms）后开始调度，每次调度完后，最少等待period（ms）后才开始调度。
   [4] public void schedule(TimerTask task, Date firstTime, long period)
      和上一个方法类似，唯一的区别就是传入的第二个参数为第一次调度的时间。
 *
 *
 *http://www.cnblogs.com/jinspire/archive/2012/02/10/2345256.html
 * http://www.cnblogs.com/0201zcr/p/4703061.html
 **/
public class TimerAndTimeTaskExample {

	public static void testUsual(){
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("11232");
			}
		},2000,2000);
	}

	//通过继承TimerTask的方式实现，必须重写run方法.
	public static class MyTask extends TimerTask{
		@Override
		public void run() {
			SimpleDateFormat sdf=null;
			sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			System.out.println("当前时间："+sdf.format(new Date()));
		}
	}

	public static void testExtends(){
		Timer t=new Timer();//建立Timer对象
		MyTask task=new MyTask();//定义任务
		//设置任务的执行，1秒后开始，每3秒执行一次
//		t.schedule(task,1000,3000);

		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.MINUTE,30);
		System.out.println(calendar.getTime());
		t.schedule(task,calendar.getTime(),2000);
	}

	public static void main(String[] args) {
       testExtends();
	}
}
