package com.util.base.b_class;

import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author y15079
 * @create 2017-09-26 15:05
 * @desc
 *
 * Vector 可实现自动增长的对象数组。
 * java.util.vector提供了向量类(vector)以实现类似动态数组的功能。
 * 在Java语言中没有指针的概念，但如果正确灵活地使用指针又确实可以大大提高程序的质量。比如在c,c++中所谓的“动态数组”一般都由指针来实现。
 * 灵活使用数组也可以完成向量类的功能，但向量类中提供大量的方法大大方便了用户的使用。
 *
 * 创建了一个向量类的对象后，可以往其中随意插入不同类的对象，即不需顾及类型也不需预先选定向量的容量，并可以方便地进行查找。
 * 对于预先不知或者不愿预先定义数组大小，并且需要频繁地进行查找，插入，删除工作的情况。可以考虑使用向量类。
 *
 * Vector方法：
 * 插入功能
   [1] public final synchronized void adddElement(Object obj)
     将obj插入向量的尾部。obj可以是任何类型的对象。对同一个向量对象，亦可以在其中插入不同类的对象。
     但插入的应是对象而不是数值，所以插入数值时要注意将数组转换成相应的对象。
   [2] public final synchronized void setElementAt(Object obj,int index)
     将index处的对象设置成obj，原来的对象将被覆盖。
   [3] public final synchronized void insertElementAt(Object obj,int index)
     在index指定的位置插入obj，原来对象以及此后的对象依次往后顺延。
 *
 * 删除功能：
   [1] public final synchronized void removeElement(Object obj)
       从向量中删除obj,若有多个存在，则从向量头开始试，删除找到的第一个与obj相同的向量成员。
   [2] public final synchronized void removeAllElement();
      删除向量所有的对象
   [3] public final synchronized void removeElementAt(int index)
      删除index所指的地方的对象
 *
 * 查询搜索功能：
   [1] public final int indexOf(Object obj)
      从向量头开始搜索obj,返回所遇到的第一个obj对应的下标，若不存在此obj,返回-1.
   [2] public final synchronized int indexOf(Object obj,int index)
      从index所表示的下标处开始搜索obj.
   [3] public final int lastIndexOf(Object obj)
      从向量尾部开始逆向搜索obj.
   [4] public final synchornized int lastIndex(Object obj,int index)
      从index所表示的下标处由尾至头逆向搜索obj.
   [5] public final synchornized firstElement()
     获取向量对象中的首个obj
   [6] public final synchornized Object lastElement()
     获取向量对象的最后一个obj
 *
 *http://www.cnblogs.com/zhaoyan001/p/6077492.html
 **/
public class VectorExample {

	public static void addElement(){
		//例如：要插入整数1时，不要直接调用v1.addElement(1),正确的方法为：
		Vector v1 = new Vector();
		Integer integer1 = new Integer(1);
		v1.addElement(integer1);
	}

	public static void testBasicOperator(){
		Vector v1=new Vector();
		Integer integer1=new Integer(1);
		//加入为integer的对象
		v1.addElement(integer1);
		v1.addElement(integer1);
		//加入为字符串对象
		v1.addElement("one");
		v1.addElement("two");
		v1.addElement(new Integer(2));
		v1.addElement(integer1);
		v1.addElement(integer1);
		//转为字符串并打印
		System.out.println("The Vector v1 is:\n\t"+v1);

		//向指定位置插入新对象
		v1.insertElementAt("three",2);
		v1.insertElementAt(new Float(3.9),3);
		System.out.println("The Vector v1(used method insertElementAt()is:\n\t)"+v1);

		//将指定位置的对象设置为新的对象
		//指定位置后的对象依次往后顺延
		v1.setElementAt("four",2);
		System.out.println("The vector v1 cused method setElmentAt()is:\n\t"+v1);

		//从向量对象v1中删除对象integer1
		//由于存在多个integer1,所以从头开始。
		//找删除找到的第一个integer1.
		v1.removeElement(integer1);

		//按不同的方向查找对象integer1所处的位置
		System.out.println("The position of Object1(top-to-botton):"+v1.indexOf(integer1));
		System.out.println("The position of Object1(tottom-to-top):"+v1.lastIndexOf(integer1));

		//重新设置v1的大小，多余的元素被抛弃
		v1.setSize(4);
		System.out.println("The new Vector(resized the vector)is:"+v1);
	}

	public static void testLoop(){

		Vector v1=new Vector();

		for (int i=0;i<100000;i++){
			v1.addElement("test");
		}

		//使用枚举类(Enumeration)的方法取得向量对象的每个元素。 Vector内部实现
		System.out.println("-------------------枚举循环-----------------------------------------------");
		long startTime = System.currentTimeMillis();
		Enumeration enum1 = v1.elements();
		System.out.println("The vector v1 (used method removeElememt()is");
		while(enum1.hasMoreElements()){
			enum1.nextElement();
//			System.out.print(enum1.nextElement()+" ");
		}
		System.out.println();
		long endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		System.out.println("枚举循环花费时间："+interval);
		System.out.println();

		System.out.println("-------------------迭代器循环-----------------------------------------------");
		//迭代器，AbstractList实现
		startTime = System.currentTimeMillis();
		Iterator itr=v1.iterator();
		while(itr.hasNext()){
			itr.next();
//			System.out.print(itr.next()+" ");
		}
		System.out.println();
		endTime = System.currentTimeMillis();
		interval = endTime - startTime;
		System.out.println("迭代器循环花费时间："+interval);
		System.out.println();

		System.out.println("-------------------for循环-----------------------------------------------");
		startTime = System.currentTimeMillis();
		for (int i=0;i<v1.size();i++){
			v1.get(i);
//			System.out.print(v1.get(i)+" ");
		}
		System.out.println();
		endTime = System.currentTimeMillis();
		interval = endTime - startTime;
		System.out.println("for循环花费时间："+interval);
		System.out.println();
	}


	public static void main(String[] args) {
         testLoop();
	}
}
