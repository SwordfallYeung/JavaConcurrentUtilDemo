package com.util.base.b_class;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author y15079
 * @create: 9/24/17 4:54 PM
 * @desc:
 *
 * LinkedHashSet是对LinkedHashMap的简单包装，对LinkedHashSet的函数调用都会转换成合适的LinkedHashMap方法
 *
 * 类HashSet和LinkedHashSet都是接口Set的实现，两者都不能保存重复的数据。
 * 主要区别是HashSet不保证集合中元素的顺序，即不能保证迭代的顺序与插入的顺序一致。
   而LinkedHashSet按照元素插入的顺序进行迭代，即迭代输出的顺序与插入的顺序保持一致。
 *
 * http://www.cnblogs.com/CarpenterLee/p/5541111.html
 * http://blog.csdn.net/ameyume/article/details/17291333
 *
 *以下是HastSet和LinkedHashSet的用法示例：
 */
public class LinkedHashSetExample {

    // HashSet不保证集合的迭代顺序；也许在某些时间迭代的顺序与插入顺序一致，但是不保证该顺序恒久不变。
    private static Set<Integer> integerSet=new HashSet<Integer>();
    private static Set<String> stringSet=new HashSet<String>();

    // LinkedHashSet按照元素插入的顺序进行迭代，LinkedHashSet不是线程安全的。
    private static Set<Integer> integerLinkedSet= Collections.synchronizedSet(new LinkedHashSet<Integer>());
    private static Set<String> stringLinkedSet= Collections.synchronizedSet(new LinkedHashSet<String>());

    public static void main(String[] args) {
        for (int i=0;i<50;i++){
            integerSet.add(i);
            stringSet.add(String.valueOf(i));
            integerLinkedSet.add(i);
            stringLinkedSet.add(String.valueOf(i));
        }

        Iterator<Integer> setIntIt = integerSet.iterator();
        System.out.println("The sequence of HashSet for Integer:");
        while(setIntIt.hasNext()) {
            System.out.print(setIntIt.next() + " ");
        }
        System.out.println();

        System.out.println("The sequence of HashSet for String:");
        Iterator<String> setStringIt = stringSet.iterator();
        while(setStringIt.hasNext()) {
            System.out.print(setStringIt.next() + " ");
        }
        System.out.println();

        System.out.println("The sequence of LinkedHashSet for Integer:");
        Iterator<Integer> linkedSetIntIt = integerLinkedSet.iterator();
        while(linkedSetIntIt.hasNext()) {
            System.out.print(linkedSetIntIt.next() + " ");
        }
        System.out.println();

        System.out.println("The sequence of LinkedHashSet for String:");
        Iterator<String> linkedSetStringIt = stringLinkedSet.iterator();
        while(linkedSetStringIt.hasNext()) {
            System.out.print(linkedSetStringIt.next() + " ");
        }
        System.out.println();

    }


}
