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

		//以下例子先定义了Person类，然后定义了一个HashSet，并加入了5个Person到该集合，其中1个人加入了两次
		//Jack是同一个人，却在集合中出现了两次，这是什么原因呢？这是因为，Person是Object的子类，
		// 而Object类的equals()方法是根据对象的内存地址来判断两个对象是否相等的，
		// 由于两次插入的Jack的内存地址肯定不相同，所以判断的结果是不相等，所以两次都插入了。
		// 于是，我们需要覆写equals()方法来判断两个对象是否是同一个对象。

		@Override
		public boolean equals(Object obj) {
			// 地址相等，则肯定是同一个对象
			if(this==obj){
				return true;
			}

			// 类型不同，则肯定不是同一类对象
			if(!(obj instanceof Person)){
				return false;
			}

			// 类型相同，向下转型
			Person per=(Person) obj;
			// 如果两个对象的姓名和性别相同，则是同一个人
			if(this.name.equals(per.name)&&this.sex.equals(per.sex))
				return true;
			return false;
		}

		//Jack仍然被插入了两次，这是什么原因呢？这是因为Object的Hash码返回的是对象的Hash地址，而两个对象的Hash地址肯定是不相等的，
		// 所以6次插入的对象被存储在6个存储区域，equals()方法根本没有运行。于是，还需要覆写hashCode()方法，根据姓名来计算对象的Hash码

		@Override
		public int hashCode() {
			return this.name.hashCode();
		}

		//Jack只插入了一次，终于正确了。如果根据性别来计算对象的Hash码，结果也是正确的，Jack也只会被插入1次。但是，如果两个对象的性别不同，则会插入两次
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
