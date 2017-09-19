package com.util.base.b_class;

/**
 * @author y15079
 * @create: 9/19/17 11:20 PM
 * @desc:
 *
 * AbstractList 继承自 AbstractCollection 抽象类，实现了 List 接口 ，
 * 是 ArrayList 和 AbstractSequentiaList 的父类。
 *
 * 实现的方法:
 * 1.默认不支持的 add(), set(),remove():
 * 2.indexOf(Object) 获取指定对象 首次出现 的索引：
 * 3.lastIndexOf(Object) 获取指定对象最后一次出现的位置:
 * 4.clear(), removeRange(int, int), 全部/范围 删除元素：
 * 5.addAll(int,Collection)
 *
 * 与其他集合实现类不同，AbstractList 内部已经提供了 Iterator, ListIterator 迭代器的实现类，分别为 Itr, ListItr,
 * Itr 只是简单实现了 Iterator 的 next, remove 方法;
 * ListItr 在 Itr 基础上多了 向前 和 set 操作
 *
 *
 * RandomAccess 是一个空的接口，它用来标识某个类是否支持 随机访问（随机访问，相对比“按顺序访问”）。
 * 一个支持随机访问的类明显可以使用更加高效的算法。
 *List 中支持随机访问最佳的例子就是 ArrayList, 它的数据结构使得 get(), set(), add()等方法的时间复杂度都是 O(1);
 *反例就是 LinkedList, 链表结构使得它不支持随机访问，只能按序访问，因此在一些操作上性能略逊一筹。
 *通常在操作一个 List 对象时，通常会判断是否支持 随机访问，也就是* 是否为 RandomAccess 的实例*，从而使用不同的算法。
 *比如遍历，实现了 RandomAccess 的集合使用 get(), 比用迭代器更快
 *
 * 现了 RandomAccess 接口的类有：
 * ArrayList, AttributeList, CopyOnWriteArrayList, Vector, Stack 等
 *
 * AbstractList 作为 List 家族的中坚力量

 *既实现了 List 的期望
 *也继承了 AbstractCollection 的传统
 *还创建了内部的迭代器 Itr, ListItr
 *还有两个内部子类 SubList 和 RandomAccessSublist；

 *百废俱兴，AbstractList 博采众长，制定了 List 家族的家规，List 家族基础已经搭建的差不多了
 *
 * AbstractList: http://blog.csdn.net/u011240877/article/details/52834074
 *
 */
public class AbstractListExample {
}
