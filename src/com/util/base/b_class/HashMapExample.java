package com.util.base.b_class;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author y15079
 * @create 2017-09-23 19:19
 * @desc
 *
 * HashMap 和 HashSet 是 Java Collection Framework 的两个重要成员，其中 HashMap 是 Map 接口的常用实现类，HashSet 是 Set 接口的常用实现类。
 * 虽然 HashMap 和 HashSet 实现的接口规范不同，但它们底层的 Hash 存储机制完全一样，甚至 HashSet 本身就采用 HashMap 来实现的。
 *
 **/
public class HashMapExample {

	public static void main(String[] args) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("cn", "中国");
		hashMap.put("jp", "日本");
		hashMap.put("fr", "法国");

		System.out.println(hashMap);
		System.out.println("cn:" + hashMap.get("cn"));
		System.out.println(hashMap.containsKey("cn"));
		System.out.println(hashMap.keySet());
		System.out.println(hashMap.isEmpty());

		hashMap.remove("cn");
		System.out.println(hashMap.containsKey("cn"));

		//采用Iterator遍历HashMap
		Iterator it = hashMap.keySet().iterator();
		while(it.hasNext()) {
			String key = (String)it.next();
			System.out.println("key:" + key);
			System.out.println("value:" + hashMap.get(key));
		}

		//遍历HashMap的另一个方法
		Set<Map.Entry<String, String>> sets = hashMap.entrySet();
		for(Map.Entry<String, String> entry : sets) {
			System.out.print(entry.getKey() + ", ");
			System.out.println(entry.getValue());
		}
	}
}
