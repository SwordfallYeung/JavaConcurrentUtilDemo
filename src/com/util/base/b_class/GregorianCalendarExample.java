package com.util.base.b_class;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author y15079
 * @create 2017-09-23 17:36
 * @desc
 *
 * 1,获得该类对象
   Calendar ca = new GregorianCalendar()//默认当前的时刻。
   Calendar ca = new GregorianCanlendar(int year,int month,int dayOfMonth)//初始具有指定年月日的公历类对象。
   Calendar ca = new GregorianCanlendar(int year,int month,int dayOfMonth,int hourOfDay,int minute)初始具有指定年月日的公历类对象。
   Calendar ca = new GregorianCanlendar(int year,int month,int dayOfMonth,int hourOfDay,int minute,int second)//初始具有指定年月日的公历类对象。
   上边的都是获得默认的语言环境，和默认的时区 对象。
2，用法
   用法主要继承去父类Calendar。
 *
 **/
public class GregorianCalendarExample {
	public static void main(String[] args) {
		//获得一个日历对象
		Calendar ca = Calendar.getInstance();
        display(ca);

        //创建日历对象时间为2012.11.12  12:12:12
		Calendar c2=new GregorianCalendar(2012,11,12,12,12,12);
        display(c2);

        //根据在年中的天数，求相隔天数
		int days= c2.get(Calendar.DAY_OF_YEAR)-ca.get(Calendar.DAY_OF_YEAR);
		System.out.println("相差"+days+"天");

		//重新设定c2对象的时间
		System.out.println("+++++++++++++++++重新设定后时间++++++++++++：");
		c2.set(2012,11,23);//月份和日期都是0开头。月份中0表示一月。月中的日期，0表示一日。
		c2.set(Calendar.HOUR, 22);
		c2.set(Calendar.MINUTE,12);
		c2.set(Calendar.SECOND, 12);
		display(c2);

		System.out.println("+++++++++++++++++修改后时间++++++++++++：");
		//向后延长29天
		ca.add(Calendar.DAY_OF_YEAR,29);
		display(ca);
		//再延长10小时
		ca.add(Calendar.HOUR, 4);
		display(ca);
	}

	public static void display(Calendar ca){
		String[] mon={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
		String[] week={"","星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		System.out.print(ca.get(Calendar.YEAR)+"年");
		System.out.print(mon[ca.get(Calendar.MONTH)]);
		System.out.print(ca.get(Calendar.DAY_OF_MONTH)+"日");
		System.out.println(week[ca.get(Calendar.DAY_OF_WEEK)]);
		System.out.print("时间：");
		System.out.print(ca.get(Calendar.HOUR_OF_DAY)+":");
		System.out.print(ca.get(Calendar.MINUTE)+":");
		System.out.println(ca.get(Calendar.SECOND));
	}
}
