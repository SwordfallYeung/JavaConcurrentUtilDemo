package com.util.base.a_interface;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author y15079
 * @create 2017-09-19 18:36
 * @desc
 * //SortedSet提供的方法：
 *java.util.SortedSet.comparator()//自己定义比较器，对内部元素排序
 *java.util.SortedSet.first()//第一个元素
 *java.util.SortedSet.headSet(E e)//e之前的元素，不包括e
 *java.util.SortedSet.last()//最后一个元素
 *java.util.SortedSet.spliterator()//Java8新增，生成Spliterator接口，有点类似nio里的selector
 *java.util.SortedSet.subSet(E e1, E e2)//e1和e2之间的元素
 *java.util.SortedSet.tailSet(E e)//e之后的元素，包括e
 *
 * 所有已知实现类：
   ConcurrentSkipListSet, TreeSet
 **/
public class SortedSetExample {
	public static void main(String[] args) {
		SortedSet<String> sortedTreeSet=new TreeSet<String>();//sortedSet接收TreeSet的实例
		//增加元素
		sortedTreeSet.add("aa");
		sortedTreeSet.add("bb");
		sortedTreeSet.add("cc");
		sortedTreeSet.add("dd");

		//重复元素，不能加入
		sortedTreeSet.add("aa");
		sortedTreeSet.add("bb");
		//增加元素
		sortedTreeSet.add("ee");

		System.out.println("共有多少个元素："+sortedTreeSet.size());//添加7个元素，减去重复的剩下5个
		System.out.println("第一个元素："+sortedTreeSet.first());//第一个元素：aa
		System.out.println("最后一个元素："+sortedTreeSet.last());//最后一个元素：ee
		System.out.println("headSet元素："+sortedTreeSet.headSet("cc"));//cc之前的元素：[aa bb]
		System.out.println("tailSet元素："+sortedTreeSet.tailSet("cc"));//cc之后的元素：[cc dd ee]
		System.out.println("subSet元素："+sortedTreeSet.subSet("bb","dd"));//bb到dd之间的元素：[bb cc]
		System.out.println("spliterator元素："+sortedTreeSet.spliterator());//Java8中提供

		//在外部实现sortedSet.comparator()功能，此处定义一个Man类，包括name和age，然后加入new Comparator，后面的SortedMap也具有此接口
		SortedSet<Man> sortedManSet=new TreeSet<Man>(new Comparator<Man>() {
			@Override
			public int compare(Man o1, Man o2) {
				return o1.getAge()-o2.getAge();
			}
		});

		Man m1=new Man("li1",22);
		Man m2=new Man("li2",25);
		Man m3=new Man("li3",19);
		Man m4=new Man("li4",23);
		Man m5=new Man("li5",21);
		sortedManSet.add(m1);
		sortedManSet.add(m2);
		sortedManSet.add(m3);
		sortedManSet.add(m4);
		sortedManSet.add(m5);

		Iterator itr=sortedManSet.iterator();
		while (itr.hasNext()){
			Man man=(Man)itr.next();
			System.out.println("man name =="+man.getName()+"man age =="+man.getAge());
		}

		System.out.println("=====================================");
	}

	public static class Man{
		private String name;
		private int age;

		public Man(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
}
