package com.util.base.b_class;

import java.util.Arrays;
import java.util.Collections;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author y15079
 * @create 2017-09-26 16:51
 * @desc
 *
 * 适用于循环拼接
 *
 * http://www.javacodegeeks.com/2014/03/java-can-finally-join-strings.html
 * http://www.codeweblog.com/java8%E4%B9%8Bstringjoiner-%E7%BB%88%E4%BA%8E%E6%9C%89%E5%83%8Fguava%E7%B1%BB%E5%BA%93%E9%87%8C%E7%9A%84%E5%8A%9F%E8%83%BD%E4%BA%86/
 **/
public class StringJoinerExample {

	public static void main(String[] args) {
		//1.0
		StringJoiner sj=new StringJoiner(",");
		System.out.println(sj.add("a").add("b").add("c"));
		//1.1
		StringJoiner stringJoiner=new StringJoiner(",");
		stringJoiner.add("name");
		stringJoiner.add("sex");
		System.out.println(stringJoiner.toString());
		//2
		stringJoiner=new StringJoiner(",","[","]");
		stringJoiner.add("name");
		stringJoiner.add("sex");
		System.out.println(stringJoiner.toString());
		//3
		stringJoiner = new StringJoiner(",").add("name").add("sex");
		System.out.println(stringJoiner.toString());
		//4
		String collect= Arrays.asList("name","sex").stream().collect(Collectors.joining(","));
		System.out.println(collect);

	}
}
