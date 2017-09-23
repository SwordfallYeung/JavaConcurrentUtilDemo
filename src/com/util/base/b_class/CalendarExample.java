package com.util.base.b_class;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author y15079
 * @create: 9/21/17 12:52 AM
 * @desc:
 * Calendar 为抽象类 ，子类为GregorianCalendar
 *
 * Java Calendar 类时间操作，这也许是创建和管理日历最简单的一个方案，示范代码很简单。
   演示了获取时间，日期时间的累加和累减，以及比较。

   注意事项：
   Calendar 的 month 从 0 开始，也就是全年 12 个月由 0 ~ 11 进行表示。
   而 Calendar.DAY_OF_WEEK 定义和值如下：
   Calendar.SUNDAY = 1
   Calendar.MONDAY = 2
   Calendar.TUESDAY = 3
   Calendar.WEDNESDAY = 4
   Calendar.THURSDAY = 5
   Calendar.FRIDAY = 6
   Calendar.SATURDAY = 7
 *
 * 日历类 主要涉及两个类，一个就是抽象类 Calendar类，另一个是他的子类GregorianCalendar。
 *
 * 1，主要字段：
   YEAR（年份）
   MONTH（月份从0开始）
   DATE(一月的某天)
   HOUR（指示上午或下午的小时）
   HOUR_F_DAY(指示一天中的小时。)
   DAY_OF_WEEK （一个星期中的某天）
   DAY_OF_MONTH（一个月中的某天）
   DAY_OF_YEAR（一年中的某天）
   DAY_OF_WEEK _IN_MONTH（一个月中的第几个星期）
   WEEK_OF_MONTH  (指示当前月中的星期数)
   WEEK_OF_YEAR(指示当前年中的星期数)
 * 2，得Calendar类对象。
 //通过 Calendar类的静态方法getInstance获得。
   Calendar  ca = Calendar.getInstance();
 *
 * 3，主要方法
   void set(int field,int value)//field日历类的参数，比如YEAR   MONTH   DATE 等...
   void set(int year,int month,int date)//设置年月日。
   void set(int year, int month, int date, int hourOfDay, int minute)//设置年月日时分
   void set(int year, int month, int date, int hourOfDay, int minute, int second)//设置年月日时分秒
   void setTime(Date date);//使用给定的 Date 设置此 Calendar 的时间。

    int  get(int field)//返回给定日历字段的值。如：int month = acobj.get(Calendar.MONTH);
    Date getTime()//返回一个表示此 Calendar 时间值的 Date 对象。
    long getTimeInMillis()//返回从1970.1.1   00：00：00到该日历的毫秒数。

    void add(int field,amont);//根据日历的规则，为给定的日历字段添加或减去指定的时间量。可加减。如：caobj.add(Calendar.MONTH,1)下一个月。
 *
 *   原文地址：blog.csdn.net/joyous/article/details/9630893
 *
 *   http://blog.csdn.net/liuhenghui5201/article/details/8273259
 */
public class CalendarExample {

	public static void main(String[] args) throws Exception {
		//字符串转换日期格式
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//接收传入参数
		String strDate="2017-01-01 01:01:01";
		//得到日期格式对象
		Date date=format.parse(strDate);

		//完整显示今天日期时间
		String str=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")).format(new Date());
		System.out.println(str);

		//创建Calendar对象
		Calendar calendar=Calendar.getInstance();

		//对calendar设置时间的方法
		//设置传入的时间格式
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-M-d H:m:s");;
		//指定一个日期
		Date date1=dateFormat.parse("2013-6-1 13:24:16");
		//对calendar设置为date所定的日期
		calendar.setTime(date1);

		//按特定格式显示刚设置的时间
		str=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")).format(calendar.getTime());
		System.out.println(str);

		//或者另一种设置calendar方式
		//分别为year,month,date,hourOfDay,minute,second
		calendar=Calendar.getInstance();
		calendar.set(2013,1,2,17,35,44);
		str=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")).format(calendar.getTime());
		System.out.println(str);

		//Calendar取得当前时间的方法
		//初始化Calendar对象
		calendar=Calendar.getInstance();
		//或者用Date来初始化Calendar对象
		calendar.setTime(new Date());

		//显示年份
		int year=calendar.get(Calendar.YEAR);
		System.out.println("year is = "+String.valueOf(year));

		//显示月份(从0开始，实际显示要加一)
		int month=calendar.get(Calendar.MONTH);
		System.out.println("month is ="+(month+1));

		//本周几
		int week=calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println("week is = " + week);

		//今年的第N天
		int DAY_OF_YEAR=calendar.get(Calendar.DAY_OF_YEAR);
		System.out.println("DAY_OF_YEAR is = " + DAY_OF_YEAR);

		//本月第N天
		int DAY_OF_MONTH=calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("DAY_OF_MONTH = " + String.valueOf(DAY_OF_MONTH));

		//3小时以后
		calendar.add(Calendar.HOUR_OF_DAY,3);
		int HOUR_OF_DAY=calendar.get(Calendar.HOUR_OF_DAY);
		System.out.println("HOUR_OF_DAY + 3 = " + HOUR_OF_DAY);

		//当前分钟数
		int MINUTE=calendar.get(Calendar.MINUTE);
		System.out.println("MINUTE = "+MINUTE);

		//15分钟以后
		calendar.add(Calendar.MINUTE,15);
		MINUTE=calendar.get(Calendar.MINUTE);
		System.out.println("MINUTE + 15 = " + MINUTE);

		//30分钟前
		calendar.add(Calendar.MINUTE,-30);
		MINUTE=calendar.get(Calendar.MINUTE);
		System.out.println("MINUTE - 30 = " + MINUTE);

		//格式化显示
		str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar.getTime());
		System.out.println(str);

		// 重置 Calendar 显示当前时间
		calendar.setTime(new Date());
		str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar.getTime());
		System.out.println(str);

		//创建一个Calendar用于比较时间
		Calendar calendarNew=Calendar.getInstance();

		//设定为5小时以前，后者大，显示-1
		calendarNew.add(Calendar.HOUR,-5);
		System.out.println("时间比较："+calendarNew.compareTo(calendar));

		//设定7小时以后，前者大，显示1
		calendarNew.add(Calendar.HOUR,+7);
		System.out.println("时间比较：" + calendarNew.compareTo(calendar));

		// 退回 2 小时，时间相同，显示 0
		calendarNew.add(Calendar.HOUR, -2);
		System.out.println("时间比较：" + calendarNew.compareTo(calendar));

		//得微秒级时间差
		long val=calendarNew.getTimeInMillis() - calendar.getTimeInMillis();
		//换算后得到天数
		long day=val/(1000*60*60*24);
		System.out.println("天数为："+day);
	}
}
