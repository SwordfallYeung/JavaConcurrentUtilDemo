package com.util.base.b_class;

/**
 * @author y15079
 * @create: 9/20/17 12:32 AM
 * @desc:
 *
 * （ Sequential 相继的，按次序的）
 *
 * AbstractSequentialList 继承自 AbstractList，是 LinkedList 的父类，是 List 接口 的简化版实现.
 * 简化在哪儿呢？简化在 AbstractSequentialList 只支持按次序访问，而不像 AbstractList 那样支持随机访问。
 * 想要实现一个支持按次序访问的 List的话，只需要继承这个抽象类，然后把指定的抽象方法实现就好了。需要实现的方法：

   size()
   listIterator()，返回一个 ListIterator
   你需要实现一个 ListIterator, 实现它的 hasNext(), hasPrevious(), next(), previous(), 还有那几个 获取位置 的方法，这样你就得到一个不可变的 ListIterator 了。
   如果你想让它可修改，还需要实现 add(), remove(), set() 方法。

 *
 * 成员函数：
 * 1.获取迭代器：
 * 2.add(int, E) 添加元素到指定位置，将当前处于该位置（如果有的话）和任何后续元素的元素移到右边（添加一个到它们的索引）：
 * 3.addAll(int index, Collection）
 * 4.get(int index) 获取指定位置的元素：
 * 5.set(int index, E element) 修改指定位置的元素为新的：
 * 6.remove(int index) 删除指定位置的元素：
 *
 * 可以看到， AbstractSequentialList 把父类 AbstractList 中没有实现或者没有支持的操作都实现了，而且都是调用的 ListIterator 相关方法进行操作。
   在 Java 集合深入理解：AbstractList 中我们介绍了 RandomAccess，里面提到，支持 RandomAccess 的对象，遍历时使用 get 比 迭代器更快。
   而 AbstractSequentialList 只支持迭代器按顺序 访问，不支持 RandomAccess，所以遍历 AbstractSequentialList 的子类，使用 for 循环 get() 的效率要 <= 迭代器遍历：
 *  get()太慢，还不如用迭代器：
 *
 *
 */
public class AbstractSequentialListExample {
}
