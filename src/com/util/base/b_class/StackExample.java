package com.util.base.b_class;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author y15079
 * @create 2017-09-26 14:44
 * @desc
 *
 * Stack是一个后进先出（last in first out，LIFO）的堆栈，在Vector类的基础上扩展5个方法而来
 *
 * Stack类为线程安全类 , 继承Vector类
 *
 * Stack方法：
   E push(E item)
   把项压入堆栈顶部。
   E pop()
   移除堆栈顶部的对象，并作为此函数的值返回该对象。
   E peek()
   查看堆栈顶部的对象，但不从堆栈中移除它。
   boolean empty()
   测试堆栈是否为空。
   int search(Object o)
   返回对象在堆栈中的位置，以 1 为基数。
 *
 *
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
