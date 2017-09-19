package com.util.base.a_interface;

import java.util.*;

/**
 * @author y15079
 * @create 2017-09-19 17:12
 * @desc
 *
 * HashMap比SortedMap快，非排序的时候用HashMap ，
 * SortedMap是接口，它的实现类为TreeMap，故实际上是HashMap与TreeMap比较
 *
 *
 **/
public class SortedMapExample {

	public static void main(String[] args) {
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("3","333");
		map.put("2","222");
		map.put("1","111");

		for (Map.Entry<String,String> entry:map.entrySet()){
			System.out.println("排序之前:"+entry.getKey()+" 值"+entry.getValue());
		}

		System.out.println("======================================================");

		SortedMap<String,String> sort=new TreeMap<String,String>(map);
		Set<Map.Entry<String,String>> set=sort.entrySet();
		Iterator<Map.Entry<String,String>> it=set.iterator();
		while(it.hasNext())
		{
			Map.Entry<String,String> entry=it.next();
			System.out.println("排序之后:"+entry.getKey()+" 值"+entry.getValue());
		}
	}
}
