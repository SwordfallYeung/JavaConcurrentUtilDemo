package com.util.base.b_class;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author y15079
 * @create 2017-09-26 13:38
 * @desc
 *
 * TimeZone为抽象类，SimpleTimeZone为其子类
 *
 **/
public class TimeZoneExample {

	public static void main(String[] args) {
		TimeZone tz=TimeZone.getDefault();
		System.out.println("tz: "+tz);

		int offset=tz.getRawOffset();
		System.out.println("raw offset: "+offset);

		int dstSavings = tz.getDSTSavings();
		System.out.println("dstSavings: "+dstSavings);

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while (true){
			Calendar calendar=Calendar.getInstance();
			String msg="["+sdf.format(calendar.getTime())+"]"+calendar.getTime();
			msg+=",offset:"+TimeZone.getDefault().getOffset(calendar.getTimeInMillis());
			System.out.println(msg);

			try {
				Thread.sleep(60*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
