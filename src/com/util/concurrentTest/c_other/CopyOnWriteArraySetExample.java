package com.util.concurrentTest.c_other;

/**
 * @author y15079
 * @create 2017-09-09 20:16
 * @desc
 * 1、public class CopyOnWriteArraySet<E>extends AbstractSet<E>implements Serializable
 *      对于CopyOnWriteArraySet<E>类：
 *      1）它最适合于具有以下特征的应用程序：set 大小通常保持很小，只读操作远多于可变操作，需要在遍历期间防止线程间的冲突。
 *      2）它是线程安全的, 底层的实现是CopyOnWriteArrayList；
 *      3）因为通常需要复制整个基础数组，所以可变操作（add、set 和 remove 等等）的开销很大。
 *      4）迭代器不支持可变 remove 操作。
 *      5）使用迭代器进行遍历的速度很快，并且不会与其他线程发生冲突。在构造迭代器时，迭代器依赖于不变的数组快照。
 **/
public class CopyOnWriteArraySetExample {
}
