package com.util.base.b_class;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author y15079
 * @create 2017-09-26 14:44
 * @desc
 *
 * Stack类为线程安全类 , 继承Vector类
 *
 **/
public class StackExample {
	//Stack并不要求其中保存数据的唯一性，当Stack中有多个相同的item时，调用search方法，
	// 只返回与查找对象equal并且离栈顶最近的item与栈顶间距离
	public static void main(String[] args) {
		Stack<String> s=new Stack<String>();
		System.out.println("------isEmpty");
		System.out.println(s.isEmpty());
		System.out.println("------push");
		s.push("1");
		s.push("2");
		s.push("3");
        list(s);
		System.out.println("------pop");
		String str = s.pop();
		System.out.println(str);
		list(s);
		System.out.println("------peek");
		str=s.peek();
		System.out.println(str);
		list(s);
		System.out.println("------search");
		int i=s.search("2");
		System.out.println(i);
		i = s.search("1");
		System.out.println(i);
		i = s.search("none");
		System.out.println(i);
	}

	public static void list(Stack<String> s){
		System.out.print("iterator:");
		Iterator<String> it = s.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+";");
		}
		System.out.print("\n");
	}
}
