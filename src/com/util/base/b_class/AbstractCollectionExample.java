package com.util.base.b_class;

/**
 * @author y15079
 * @create: 9/19/17 11:03 PM
 * @desc:
 *
 * AbstractCollection 是 Java 集合框架中 Collection 接口 的一个直接实现类，
 * Collection 下的大多数子类都继承 AbstractCollection ，比如 List 的实现类, Set的实现类。
 *
 * 它实现了一些方法，也定义了几个抽象方法留给子类实现，因此它是一个抽象类
 *
 * 抽象方法:
 * public abstract Iterator<E> iterator();
 * public abstract int size();

 *子类必须以自己的方式实现这两个方法。除此外，AbstractCollection 中默认不支持添加单个元素，如果直接调用 add(E) 方法，会报错：因此，如果子类是可添加的数据结构，需要自己实现 add(E) 方法。
 *
 * 实现的方法:
 * 1.addAll() 添加一个集合内的全部元素:
 * 2.clear() 删除所有元素：
 * 3.contains() 是否包含某个元素：
 * 4.containsAll() 是否包含指定集合中的全部元素:
 * 5.isEmpty() 是否为空:
 * 6.remove() 删除某个元素:
 * 7.removeAll() 删除指定集合中包含在本集合的元素：
 * 8.retainAll() 保留共有的，删除指定集合中不共有的：
 * 9.toArray(), toArray(T[] contents) 转换成数组：
 * 10.toString() 把内容转换成一个 String 进行展示:
 *
 * AbstractCollection: http://blog.csdn.net/u011240877/article/details/52829912
 *
 */
public class AbstractCollectionExample {
}
