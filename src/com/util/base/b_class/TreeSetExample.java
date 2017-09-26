package com.util.base.b_class;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author y15079
 * @create: 9/26/17 10:46 PM
 * @desc:
 *
 * 虽然在元素的存取方面TreeMap并不占优，但是它内部的元素都是排序的，当需要查找某些元素以及顺序输出元素的时候它能够带来比较理想的结果。
 * 可以说，TreeMap是一个内部元素排序版的HashMap。同样，TreeSet是一个封装了一个HashSet的成员变量来实现的，底层运用了红黑树的数据结构。
 *
 * 这里主要展现TreeSet的两种常用方法：
 *
 */
public class TreeSetExample {

    //有序输出，不按加入的顺序，自然排序
    public static void natureSort(){
        Set<String> set = new TreeSet<String>();
        set.add("abc");
        set.add("xyz");
        set.add("rst");

        System.out.println(set);//可以直接输出

        Iterator itSet = set.iterator();//也可以遍历输出
        while(itSet.hasNext())
            System.out.print(itSet.next() + "\t");
        System.out.println();
    }

    //自定义排序，自定义数据类型，并在自定义的数据类型中实现CompareTo方法
   public static class Teacher implements Comparable {
        int num;
        String name;

        Teacher(String name, int num) {
            this.num = num;
            this.name = name;
        }

        public String toString() {
            return "学号:" + num + " 姓名:" + name;
        }

        public int compareTo(Object o) {
            Teacher ss = (Teacher) o;
            int result = num > ss.num ? 1 : (num == ss.num ? 0 : -1);
            if (result == 0) {
                result = name.compareTo(ss.name);
            }
            return result;
        }
    }

    public static void DIYSort(){
        Set<Teacher> treeSet = new TreeSet<Teacher>();
        treeSet.add(new Teacher("zhangsan", 2));
        treeSet.add(new Teacher("lisi", 1));
        treeSet.add(new Teacher("wangwu", 3));
        treeSet.add(new Teacher("mazi", 3));

        System.out.println(treeSet);//直接输出

        Iterator itTSet = treeSet.iterator();//遍历输出
        while(itTSet.hasNext())
            System.out.print(itTSet.next() + "\t");
        System.out.println();
    }

    public static void main(String[] args) {
//        natureSort();
        DIYSort();
    }

}
