package com.util.concurrent.c_other;

/**
 * @author y15079
 * @create 2017-09-09 13:35
 * @desc
 *
 * 并发Map的接口，
 * 定义了putIfAbsent(k,v)、remove(k,v)、replace(k,oldV,newV)、replace(k,v)这四个并发场景下特定的方法
 *
 * ConcurrentNavigableMap.class，concurrentMap和NavigableMap的实现类，返回最接近的一个元素
 *
 * ConcurrentMap,HashMap及HashTable
 * ①HashMap默认不是线程安全的，允许null key和null value,由于不是线程安全的，所以HashMap效率比HashTable的要高
 * ②HashTable是线程安全的，内部的方法基本都是synchronized，不允许有null值的存在,
 * ③ConcurrentMap不是加synchronized关键字，而是基于lock操作的，这样的目的是保证同步的时候，锁住的不是整个对象。
 *   不可以有null键。遍历元素时，需要获取所有的segment的锁，使用遍历慢。size()或isEmpty()的实现比较困难，因为
 *    要求一次获得许多的锁。
 **/
public class ConcurrentMapExample {

}
