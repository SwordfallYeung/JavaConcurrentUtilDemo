package com.util.base.a_interface;

import java.util.*;

/**
 * @author y15079
 * @create 2017-09-18 19:34
 * @desc
 *
 * 标准的Java类库中包含了几种不同的Map：HashMap, TreeMap, LinkedHashMap, WeakHashMap, IdentityHashMap。
 * [1]HashMap          HashMap就是使用对象的hashCode()进行快速查询的。此方法能够显着提高性能。Map基于散列表的实现。插入和查询“键值对”的开销是固定的。可以通过构造器设置容量capacity和负载因子load factor，以调整容器的性能。
 * [2]LinkedHashMap    类似于HashMap，但是迭代遍历它时，取得“键值对”的顺序是其插入次序，或者是最近最少使用(LRU)的次序。只比HashMap慢一点。而在迭代访问时发而更快，因为它使用链表维护内部次序。
 * [3]TreeMap          基于红黑树数据结构的实现。查看“键”或“键值对”时，它们会被排序(次序由Comparabel或Comparator决定)。TreeMap的特点在于，你得到的结果是经过排序的。TreeMap是唯一的带有subMap()方法的Map，它可以返回一个子树
 * [4]WeakHashMap      弱键(weak key)Map，Map中使用的对象也被允许释放: 这是为解决特殊问题设计的。如果没有map之外的引用指向某个“键”，则此“键”可以被垃圾收集器回收
 * [5]IdentifyHashMap  使用==代替equals()对“键”作比较的hash map。专为解决特殊问题而设计
 *
 *
 * Map是一个由键值对组成的数据结构，且在集合中每个键是唯一的。
 * 下面就以K和V来代表键和值，来说明一下java中关于Map的九大问题。
 **/
public class MapExample {
	public static void main(String[] args) {
		Map<String,String> map=new HashMap<>();
		map.put("one","1");
		map.put("two","2");
		map.put("three","3");

	}

	/**
	 * 将Map转换为List类型

	 在java中Map接口提供了三种集合获取方式：Key set,，value set， and key-value set.
	 */
	public static void MapToList(Map<String,String> map){
		//key list
		List keyList=new ArrayList(map.keySet());

		//value list
		List valueList=new ArrayList(map.values());

		//key-value list
		List entryList=new ArrayList(map.entrySet());
	}

	//通过Entry遍历Map
	//java中这种以键值对存在的方式被称为Map.Entry。Map.entrySet()返回的是一个key-value 集合，这是一种非常高效的遍历方式。
	public static void EntryList(Map<String,String> map) throws Exception{
		for (Map.Entry entry:map.entrySet()){
		    //get key
		    String key=(String) entry.getKey();
		    //get value
		    String value=(String) entry.getValue();
		}


		Iterator itr=map.entrySet().iterator();
		while (itr.hasNext()){
			Map.Entry entry=(Map.Entry) itr.next();
			//get key
			String key=(String) entry.getKey();
			//get value
			String value=(String)entry.getValue();
		}
	}

	//排序需要对Map的ke进行频繁的操作，一种方式就是通过比较器(comparator )来实现：
	public void sortList(Map<String,String> map){
		List list=new ArrayList(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String,String>>() {
			@Override
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});

		//另外一种方法就是通过SortedMap，但必须要实现Comparable接口。
		SortedMap<String,String> sortedMap=new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		sortedMap.putAll(sortedMap);

		//对value对Map进行排序
		List li=new ArrayList(map.entrySet());
		Collections.sort(li, new Comparator<Map.Entry<String,String>>() {
			@Override
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
	}

	//对Map的复制
	//java中提供了很多方法都可以实现对一个Map的复制，但是那些方法不见得会时时同步。简单说，就是一个Map发生的变化，而复制的那个依然保持原样。
	// 下面是一个比较高效的实现方法：
	public static void copyMap(Map<String,String> map){
		Map copiedMap=Collections.synchronizedMap(map);
	}

	//创建一个空的Map
	public static void useMap(Map<String,String> map){
		//如果这个map被置为不可用，可以通过以下实现
        map=Collections.emptyMap();
        //相反，我们会用到的时候，就可以直接
        map=new HashMap<>();
	}
}
