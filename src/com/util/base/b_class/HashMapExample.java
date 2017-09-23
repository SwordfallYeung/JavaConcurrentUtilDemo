package com.util.base.b_class;

import java.util.*;

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

	/**
	 * 一个结合List和HashMap实现的例子
	 *
	 * 在不创建学生类的情况下，从键盘输入n个学生信息（学号，姓名，年龄），
	 * 输入完成后，打印出各个学生信息
	 */
	public static class Assignment{
		public static void main(String[] args) {
			//定义保存学生信息的List，元素类型为HashMap
			List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
			Scanner input=new Scanner(System.in);

			System.out.println("请输入学生的信息，y表示继续，n表示退出");
			while ("y".equals(input.next())){
				HashMap<String,Object> map=new HashMap<String,Object>();
				System.out.println("请输入学号");
				map.put("studentno",input.next());
				System.out.println("请输入姓名");
				map.put("name",input.next());
				System.out.println("请输入年龄");
				map.put("age",input.next());
				list.add(map);
				System.out.println("请继续输入学生的信息,y表示继续，n表示退出");
			}

			System.out.println("输入的学生信息为:");
			System.out.println("学生数量为:" + list.size());

			Iterator<HashMap<String,Object>> it=list.iterator();
			int i=1;
			while (it.hasNext()){
				HashMap<String,Object> stuMap=it.next();
				System.out.print("第"+i+"个学生的信息为 ");
				System.out.println("学号:" + stuMap.get("studentno") + " ,姓名:" + stuMap.get("name") + " ,年龄:" + stuMap.get("age"));
			}
		}
	}
}
