package com.util.base.a_interface;

import java.util.*;

/**
 * @author y15079
 * @create 2017-09-18 19:34
 * @desc
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
