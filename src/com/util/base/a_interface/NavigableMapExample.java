package com.util.base.a_interface;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author y15079
 * @create: 9/18/17 10:14 PM
 * @desc: Java集合框架（Java Collections Framework）加入了一个新的NavigableMap和NavigableSet接口。
 * 分别的扩展了SortedMap和SortedSet接口，
 * <p>
 * 对于SortedMap，你有firstKey()和lastKey()来获取某个Map的边界键值。
 * <p>
 * NavigableMap有一些获取键的其他方法:
 * ceilingKey(key):用来获取大于或者等于给定的key的第一个键，如果没有的话就返回null。
 * floorKey(key):用来获取小于或者等于给定的key的第一个键，如果没有的话就返回null。
 * higherKey(key):用来获取大于给定的key的第一个键，如果没有的话就返回null。
 * lowerKey(key):用来获取小于给定的key的第一个键，如果没有的话就返回null。
 * <p>
 * ceilingEntry(key):用于获取大于或等于给定key的第一个实体，如果没有则返回null。
 * firstEntry():用于获取Map的第一个实体，如果没有则返回null。
 * floorEntry(key):用于获取小于或等于给定的第一个实体key，如果没有则返回null。
 * higherEntry():用于获取大于给定的key的第一个实体，如果没有则返回null。
 * lastEntry():用于获取Map最后一个实体，如果没有则返回null。
 * lowerEntry(key):用于获取小于给定key的第一个实体，如果没有则返回null。
 * <p>
 * <p>
 * 这里有两个单步从Map获取和删除实体的方法。提供了一个简单的不用使用迭代器而遍历所有Map元素的方法。下面是具体的介绍：
 * Map.Entry<K,V> pollFirstEntry():获取Map第一个键的实体并且从Map中移除该实体，如果Map为空则返回null。
 * Map.Entry<K,V> pollLastEntry():获取Map最后一个键的实体并且从Map移除该实体，如果Map为空则返回null。
 */
public class NavigableMapExample {
    public static void main(String[] args) {
        NavigableMap<String, String> navigableMap = new TreeMap<String, String>();

        navigableMap.put("C++", "Good programming language");
        navigableMap.put("Java", "Another good programming language");
        navigableMap.put("Scala", "Another JVM language");
        navigableMap.put("Python", "Language which Google use");

        System.out.println("SortedMap:" + navigableMap);

        //lowerKey returns key which is less than specified key
        System.out.println("lowerKey from Java: " + navigableMap.lowerKey("Java"));

        //floorKey returns key which is less than or equal to specified key
        System.out.println("floorKey from Java: " + navigableMap.floorKey("Java"));

        //ceilingKey returns which is greater than or equal to specified key
        System.out.println("ceilingKey from Java: " + navigableMap.ceilingKey("Java"));

        //higherKey returns key which is greater specified key
        System.out.println("higherKey from Java: " + navigableMap.higherKey("Java"));

        //an example of headMap - returns NavigableMap whose key is less than specified
        NavigableMap<String, String> headMap = navigableMap.headMap("Python", false);
        System.out.println("headMap created from navigableMap: " + headMap);

        //an example of tailMap - returns NavigableMap whose key is greater than specified
        NavigableMap<String, String> tailMap = navigableMap.tailMap("Scala", false);
        System.out.println("tailMap created from navigableMap: " + tailMap);

        //an example of subMap - return NavigableMap from toKey to fromKey
        NavigableMap<String, String> subMap = navigableMap.subMap("C++", false, "Python", false);

        System.out.println("subMap created from navigableMap: " + subMap);

    }
}
