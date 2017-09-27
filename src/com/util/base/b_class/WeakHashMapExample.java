package com.util.base.b_class;

import java.util.*;

/**
 * @author y15079
 * @create 2017-09-27 9:15
 * @desc
 *
 *  WeakHashMap 继承于AbstractMap，实现了Map接口。
    和HashMap一样，WeakHashMap 也是一个散列表，它存储的内容也是键值对(key-value)映射，而且键和值都可以是null。
 *
 * 不过WeakHashMap的键是“弱键”。在 WeakHashMap 中，当某个键不再正常使用时，会被从WeakHashMap中被自动移除。
 *更精确地说，对于一个给定的键，其映射的存在并不阻止垃圾回收器对该键的丢弃，这就使该键成为可终止的，被终止，然后被回收。
 * 某个键被终止时，它对应的键值对也就从映射中有效地移除了。
 *
 * 这个“弱键”的原理呢？
 * 大致上就是，通过WeakReference和ReferenceQueue实现的。 WeakHashMap的key是“弱键”，即是WeakReference类型的；
   ReferenceQueue是一个队列，它会保存被GC回收的“弱键”。
 *实现步骤是：
  [1] 新建WeakHashMap，将“键值对”添加到WeakHashMap中。
      实际上，WeakHashMap是通过数组table保存Entry(键值对)；每一个Entry实际上是一个单向链表，即Entry是键值对链表。
  [2] 当某“弱键”不再被其它对象引用，并被GC回收时。在GC回收该“弱键”时，这个“弱键”也同时会被添加到ReferenceQueue(queue)队列中。
  [3] 当下一次我们需要操作WeakHashMap时，会先同步table和queue。table中保存了全部的键值对，而queue中保存被GC回收的键值对；
      同步它们，就是删除table中被GC回收的键值对。
   这就是“弱键”如何被自动从WeakHashMap中删除的步骤了。
 *
 * 和HashMap一样，WeakHashMap是不同步的。可以使用 Collections.synchronizedMap 方法来构造同步的 WeakHashMap。
 *
 * 说明：WeakHashMap和HashMap都是通过"拉链法"实现的散列表。
 *
 * “弱键”是一个“弱引用(WeakReference)”，在Java中，WeakReference和ReferenceQueue 是联合使用的。
   在WeakHashMap中亦是如此：如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
   接着，WeakHashMap会根据“引用队列”，来删除“WeakHashMap中已被GC回收的‘弱键’对应的键值对”。
 *
 *
 *http://blog.csdn.net/zheng0518/article/details/42200113
 **/
public class WeakHashMapExample {

	/**
	 *  遍历WeakHashMap的测试程序。
	 *   (01) 通过entrySet()去遍历key、value，参考实现函数：
	 *        iteratorHashMapByEntryset()
	 *   (02) 通过keySet()去遍历key、value，参考实现函数：
	 *        iteratorHashMapByKeyset()
	 *   (03) 通过values()去遍历value，参考实现函数：
	 *        iteratorHashMapJustValues()
	 */
	public static void main(String[] args) {
		int val=0;
		String key = null;
		Integer value = null;
		Random r = new Random();
		WeakHashMap map=new WeakHashMap();

		for (int i=0;i<12;i++){
			//随机获取一个[0,100)之间的数字
			val=r.nextInt(100);

			key=String.valueOf(val);
			value=r.nextInt(5);
			//添加到WeakHashMap中
			map.put(key,value);
			System.out.println(" key："+key+" value:"+value);
		}

		//通过entrySet()遍历WeakHashMap的key-value
		iteratorHashMapByEntryset(map) ;

		// 通过keySet()遍历WeakHashMap的key-value
		iteratorHashMapByKeyset(map) ;

		// 单单遍历WeakHashMap的value
		iteratorHashMapJustValues(map);
	}

	/**
	 *
	 */
	public static void iteratorHashMapByEntryset(WeakHashMap map){
		if (map==null)
			return;

		System.out.println("\niterator WeakHashMap By entryset");
		String key=null;
		Integer integer=null;
		Iterator itr=map.entrySet().iterator();
		while (itr.hasNext()){
			Map.Entry entry=(Map.Entry)itr.next();

			key=(String)entry.getKey();
			integer=(Integer)entry.getValue();
			System.out.println(key+" -- "+integer.intValue());
		}
	}

	/**
	 * 通过keyset来遍历WeakHashMap
	 * 效率低!
	 */
     private static void iteratorHashMapByKeyset(WeakHashMap map){
     	if (map==null)
     		return;
     	System.out.println("\niterator WeakHashMap By keyset");

		 String key = null;
		 Integer integ = null;
		 Iterator iter = map.keySet().iterator();
		 while (iter.hasNext()) {
			 key = (String)iter.next();
			 integ = (Integer)map.get(key);
			 System.out.println(key+" -- "+integ.intValue());
		 }
	 }

	/**
	 * 遍历WeakHashMap的values
	 */
	private static void iteratorHashMapJustValues(WeakHashMap map){
		if (map == null)
			return;
		Collection c = map.values();
		Iterator iter= c.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
