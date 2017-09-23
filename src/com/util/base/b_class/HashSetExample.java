package com.util.base.b_class;

import com.util.base.a_interface.SetExample;

import java.util.HashSet;
import java.util.Set;

/**
 * @author y15079
 * @create 2017-09-23 19:55
 * @desc
 *
 * 如果要查找一个集合中是否包含了某个对象，那么就需要把这个对象和这个集合中的每个对象依次进行比较和判断，直到找到这个对象为止，
 * 或者把所有对象都比较一次为止（如果最后一个对象才是要查找的对象，或者集合中没有包含要查找的对象）。
 * 当集合中的对象数量较多时，效率就很低。为了提高效率，提出了Hash算法。
 * Hash算法对每一个对象都计算出一个Hash码，根据Hash码把对象分配到某个存储区域中，比如一个集合包含了很多人，根据国籍，
 * 中国人是一个存储区域，美国人是一个存储区域，英国人是一个存储区域，......。
 * 这样如果要查找该集合是否包含了某个中国人，就到中国人的存储区域去比较就行了，这样大大提高了效率。
 *
 *Java中实现了Hash的集合是HashSet。HashSet查找某个对象时，首先用hashCode()方法计算出这个对象的Hash码，
 * 然后再根据Hash码到相应的存储区域用equals()方法查找，从而提高了效率。由于是集合，所以同一个对象只能有一个。
 *
 **/
public class HashSetExample {
	public static class Person{
		private String sex;
		private String name;
		private Double hei;
		private Double wei;

		public Person(String sex, String name, Double hei, Double wei) {
			this.sex = sex;
			this.name = name;
			this.hei = hei;
			this.wei = wei;
		}

		@Override
		public String toString() {
			return "\n姓名："+this.name+"  性别："+this.sex+"  身高："+this.hei+"  体重："+this.wei;
		}
	}

	public static void main(String[] args) {
		Set<Person> mySet=new HashSet<Person>();
		mySet.add(new Person("Tom","Male",170.0,70.0));
		mySet.add(new Person("Peter","Male",175.0,70.0));
		mySet.add(new Person("Kate","Female",168.0,60.0));
		mySet.add(new Person("Alice","Female",161.0,55.0));
		mySet.add(new Person("Jack","Male",190.0,95.0));
		mySet.add(new Person("Jack","Male",190.0,95.0));
		System.out.println(mySet);
	}
}
