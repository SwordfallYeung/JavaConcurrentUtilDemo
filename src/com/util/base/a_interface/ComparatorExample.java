package com.util.base.a_interface;

import java.util.*;

/**
 * @author y15079
 * @create: 9/16/17 4:47 PM
 * @desc:
 *
 * Comparable 为接口
 *
 * Comparable和Comparator都是用来实现集合中的排序的，Comparator位于包java.util下，而Comparable位于包java.lang下
 *
 * Comparable是一个对象本身就已经支持自比较所需要实现的接口（如 String、Integer 自己就可以完成比较大小操作），是内部定义的排序；
 * 而后者在一个独立的类中实现比较，是外部实现的排序。
 * 如果一个类没有实现Comparable接口，或是这个对象不支持自比较或者自比较函数不能满足你的要求时，可以通过Comparator来实现比较算法进行排序，并且为了使用不同的排序标准做准备，比如：升序、降序。所以，
 * 如想实现排序，就需要让类对象自身实现Comparable接口，重写其中的compareTo(T o)方法；或在外部定义比较器实现Comparator接口，重写其compare(T o1,T o2)方法。
 * 前者只有一个参数，后者有两个参数。排序时可以调用java.util.Arrays.sort()来排序对象数组，或是调用集合中的sort()方法就可以按照相应的排序方法进行排序。
 * 方法返回一个基本类型的整型，返回负数表示o1小于o2，返回0表示o1和o2相等，返回正数表示o1大于o2。
 *
 * Comparable接口将比较代码嵌入自身类中，而Comparator在一个独立的类中实现比较
 *
 */
public class ComparatorExample {

    /**
     * Comparable 类实现Comparable接口
     */
    public static class Person implements Comparable<Person>{
        private String name;
        private int age;

        public Person(String name, int age) {
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

        @Override
        public String toString() {
            return ""+this.name+" "+this.age;
        }

        @Override
        public int compareTo(Person o) {
            if (this.getName().compareTo(o.getName())!=0){
                return this.getName().compareTo(o.getName());
            }else {
                if (this.getAge()<o.getAge()){
                    return -1;
                }else if (this.getAge()>o.getAge()){
                    return 1;
                }else {
                    return 0;
                }
            }
        }
    }

    /**
     * Comparator  外部定义比较器实现Comparator接口
     */
    public static class Cmp implements Comparator<Person>{
        @Override
        public int compare(Person o1, Person o2) {
            if (o1.getName().compareTo(o2.getName())!=0)
                return o1.getName().compareTo(o2.getName());
            else {
                if (o1.getAge()<o2.getAge())
                    return -1;
                else if (o1.getAge()>o2.getAge())
                    return 1;
                else return 0;
            }
        }
    }

    public static void main(String[] args) {
        Person[] p = new Person[4];
        p[0] = new Person("ZZZ",19);
        p[1] = new Person("AAA", 109);
        p[2] = new Person("AAA", 19);
        p[3] = new Person("YYY",100);

        List<Person> list=new ArrayList<>();
        list.add(p[0]);
        list.add(p[1]);
        list.add(p[2]);
        list.add(p[3]);
        Collections.sort(list);
        System.out.println("Comparable: "+list);//调用自由的排序

        Arrays.sort(p,new Cmp());//调用Comparator定义的排序
        System.out.println("Comparator: "+Arrays.toString(p));
    }
}
