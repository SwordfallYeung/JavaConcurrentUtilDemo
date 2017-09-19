package com.util.base.b_class;

/**
 * @author y15079
 * @create: 9/20/17 12:42 AM
 * @desc:
 *
 * 由于Set接口相对于Collection接口并没有增加特别的接口方法；
   AbstractSet仅重写了AbstractCollection的三个方法：equals、hasCode()、removeAll()方法；
   因此，如果要实现一个不可修改的Set，只需要继承AbstractSet并且实现size()和iterator()方法即可；
   如果想要实现一个可以修改的Set，还需要实现add()和迭代器的remove方法

 *
 *
 */
public class AbstractSetExample {
}
