package com.util.base.b_class;

/**
 * @author y15079
 * @create: 9/19/17 11:42 PM
 * @desc:
 *
 * AbstractMap 提供了 Map 的基本实现，使得我们以后要实现一个 Map 不用从头开始，只需要继承 AbstractMap, 然后按需求实现/重写对应方法即可
 *
 * 有两个成员变量：
 *   keySet, 保存 map 中所有键的 Set
 *   values, 保存 map 中所有值的集合

     他们都是 transient, volatile, 分别表示不可序列化、并发环境下变量的修改能够保证线程可见性
 *
 *需要注意的是 volatile 只能保证可见性，不能保证原子性，需要保证操作是原子性操作，才能保证使用 volatile 关键字的程序在并发时能够正确执行。
 *
 *成员方法：
 * 1.add 可以看到默认是不支持添加操作的，实现类需要重写 put() 方法
 * 2.remove
 * 3.get
 * 4.containsKey containsValue
 * 5.用于比较的 equals(), hashCode()
 *
 * AbstractMap 中的内部类:
 * [1] SimpleImmutableEntry, 表示一个不可变的键值对
 * [2] SimpleEntry, 表示可变的键值对
 * SimpleEntry 与 SimpleImmutableEntry 唯一的区别就是支持 setValue() 操作
 *
 * 和 AbstractCollection 接口，AbstractList 接口 作用相似， AbstractMap 是一个基础实现类，实现了 Map 的主要方法，默认不支持修改。
 * 常用的几种 Map, 比如 HashMap, TreeMap, LinkedHashMap 都继承自它。
 *
 * AbstractMap: http://blog.csdn.net/u011240877/article/details/52949046
 *
 */
public class AbstractMapExample {
}
