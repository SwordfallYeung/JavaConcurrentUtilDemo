package com.util.base.b_class;

import java.util.Objects;

/**
 * @author y15079
 * @create 2017-09-25 9:43
 * @desc
 *在Java7中新添了一个Objects工具类，它提供了一些方法来操作对象，这些工具方法大多是“空指针”安全的。
 *比如，如果不能明确地判断一个引用变量是否为null，如果调用toString()方法，则可能发生NullPointerException异常；
 * 如果使用Objects类提供的toString(Object o)方法，就不会引发空指针异常，当o为null 时，程序将返回一个“null”字符串。
 *
 **/
public class ObjectsExample {
	//定义一个obj变量，默认为null
	static ObjectsExample obj;

	public static void main(String[] args) {
        //输出一个null对象的hashCode值，输出0
		System.out.println(Objects.hashCode(obj));
		//输出一个null对象的toString，输出null
		System.out.println(Objects.toString(obj));
		//要求obj不能为null，如果为null则引发异常
		System.out.println(Objects.requireNonNull(obj));
	}
}
