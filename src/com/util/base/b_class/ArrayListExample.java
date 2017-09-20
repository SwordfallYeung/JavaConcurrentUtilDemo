package com.util.base.b_class;

/**
 * @author y15079
 * @create 2017-09-20 14:20
 * @desc
 *  ArrayList是实现List接口的动态数组，所谓动态就是它的大小是可变的。实现了所有可选列表操作，并允许包括 null 在内的所有元素。
 *  除了实现 List 接口外，此类还提供一些方法来操作内部用来存储列表的数组的大小。
 *  每个ArrayList实例都有一个容量，该容量是指用来存储列表元素的数组的大小。默认初始容量为10。
 *
 *  注意，ArrayList实现不是同步的。如果多个线程同时访问一个ArrayList实例，而其中至少一个线程从结构上修改了列表，那么它必须保持外部同步。
     所以为了保证同步，最好的办法是在创建时完成，以防止意外对列表进行不同步的访问：
     List list = Collections.synchronizedList(new ArrayList(...));
 *
 **/
public class ArrayListExample {
}
