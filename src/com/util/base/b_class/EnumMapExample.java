package com.util.base.b_class;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author y15079
 * @create: 9/23/17 12:17 PM
 * @desc:
 *
 * Map的实现类有很多种，EnumMap从名字我们可以看出这个Map是给枚举类用的。
 * 它的key为枚举元素，value自定义。
 *
 *
 * http://blog.csdn.net/u013256816/article/details/50916581
 */
public class EnumMapExample {
    public enum Color{
        RED,BLUE,BLACK,YELLOW,GREEN;
    }

    public static void main(String[] args) {
        EnumMap<Color,String> map=new EnumMap<Color, String>(Color.class);
        map.put(Color.YELLOW,"黄色");
        map.put(Color.RED,"红色");
        map.put(Color.BLUE,null);
//        map.put(null, "无");   //会报NullPonitException的错误
        map.put(Color.BLACK,"黑色");
        map.put(Color.GREEN,"绿色");
        for (Map.Entry<Color,String> entry:map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        System.out.println(map);
    }
}
