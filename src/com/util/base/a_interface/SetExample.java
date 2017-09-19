package com.util.base.a_interface;

import java.util.*;

/**
 * @author y15079
 * @create 2017-09-19 15:12
 * @desc
 *
 *Set具有与Collection完全一样的接口，因此没有任何额外的功能，不像前面有两个不同的List。实际上Set就是Collection,只是行为不同。(这是继承与多态思想的典型应用：表现不同的行为。)Set不保存重复的元素(至于如何判断元素相同则较为负责)
 * Set : 存入Set的每个元素都必须是唯一的，因为Set不保存重复元素。加入Set的元素必须定义equals()方法以确保对象的唯一性。Set与Collection有完全一样的接口。Set接口不保证维护元素的次序。
 * HashSet : 为快速查找设计的Set。存入HashSet的对象必须定义hashCode()。
 * TreeSet : 保存次序的Set, 底层为树结构。使用它可以从Set中提取有序的序列。
 *  LinkedHashSet : 具有HashSet的查询速度，且内部使用链表维护元素的顺序(插入的次序)。于是在使用迭代器遍历Set时，结果会按元素插入的次序显示
 *
 *
 *
 **/
public class SetExample {

	public static void main(String[] args) {
//		NoDuplicate();
//		SortInTreeSet();
//		SortFromBackToFront();
		SortByPeople();
	}

	//java中Set集合是一个不包含重复元素的Collection，首先我们先看看遍历方法
	public static void NoDuplicate(){
		Set<String> set=new HashSet<String>();

		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
		set.add("e");
		set.add("e");//不能放入重复数据

		/**
		 * 遍历方法一,迭代遍历
		 */
		for (Iterator<String> iterator=set.iterator();iterator.hasNext();){
			System.out.print(iterator.next()+" ");
		}

		System.out.println();
		System.out.println("******************");

		/**
		 * for 增强循环遍历
		 */
		for (String value:set){
			System.out.print(value+" ");
		}
	}

	/**
	 * 下面分析一下Set集合的另外一个重要实现类TreeSet,
	 TreeSet使用元素的自然顺序对元素进行排序，或者根据创建 set 时提供的 Comparator 进行排序，具体取决于使用的构造方法。
	 通俗一点讲，就是可以按照排序后的列表显示，也可以按照指定的规则排序
	 */
	public static void SortInTreeSet(){
		Set<String> set=new TreeSet<String>();

		set.add("f");
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
		set.add("e");

		System.out.println(set);
	}

	//那么如果我们想让他倒序输出呢？当然方法很多。这里我采用指定一个规则让他倒序输出
	public static void SortFromBackToFront(){
		Set<String> set=new TreeSet<String>(new MyComparator());
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
		set.add("e");
		set.add("A");

		for(Iterator<String> iterator = set.iterator();iterator.hasNext();){
			System.out.print(iterator.next()+" ");
		}
	}

	public static class MyComparator implements Comparator<String>{
		@Override
		public int compare(String o1, String o2) {
			return o2.compareTo(o1);//降序排列
		}
	}

	//如果Set集合中放入的是我们自己定义的一个类类型呢？
	//注意：一定要定义一个排序规则类实现Comparator接口，与上面的方法类似
	public static void SortByPeople(){
       Set<Person> set=new TreeSet<Person>(new PersonComparator());

		Person p1 =  new Person(10);
		Person p2 =  new Person(20);
		Person p3 =  new Person(30);
		Person p4 =  new Person(40);

		set.add(p1);
		set.add(p2);
		set.add(p3);
		set.add(p4);

		for (Iterator<Person> iterator=set.iterator();iterator.hasNext();){
			System.out.print(iterator.next().score+" ");
		}
	}

	public static class Person {
		private int score;

		public Person() {
		}

		public Person(int score) {
			this.score = score;
		}

		@Override
		public String toString() {
			return String.valueOf(this.score);
		}

	}

	public static class PersonComparator implements Comparator<Person>{
		//默认o1为大，o2为小，正则不变，负则交换
		@Override
		public int compare(Person o1, Person o2) {
//			return o1.score-o2.score;
//			return 10-20;
			System.out.println("o1.score:"+o1.score+" "+"o2.score:"+o2.score);
			if (o1.score>o2.score){
				return -1;
			}else if (o1.score==o2.score){
				return 0;
			}else
				return 1;
		}
	}
}
