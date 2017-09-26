package com.util.base.b_class;

import java.util.TreeMap;

/**
 * @author y15079
 * @create: 9/26/17 10:13 PM
 * @desc:
 *
 * TreeMap方法是红黑树结构，每一个key-value节点作为红黑树的一个节点，TreeMap存储是会进行排序的，会根据key来对key-value键值对进行排序，
   其中排序方式也是分为两种，一种是自然排序，一种是定制排序
  [1]自然排序：TreeMap中所有的key必须实现Comparable接口，并且所有的key都应该是同一个类的对象，否则会报ClassCastException异常
  [2]定制排序：定义TreeMap时，创建一个comparator对象，该对象对所有的treeMap中所有的key值进行排序，
     采用定制排序的时候不需要TreeMap中所有的key必须实现Comparable接口
 *
 * http://blog.csdn.net/chenssy/article/details/26668941
 *
 */
public class TreeMapExample {

    public static class TreeMapTest implements Comparable{
        int count;

        public TreeMapTest(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "count["+count+"]";
        }

        @Override
        public boolean equals(Object obj) {
            if (this==obj){
                return true;
            }
            if (null!=obj&&TreeMapTest.class==obj.getClass()){
                TreeMapTest tr=(TreeMapTest) obj;
                return tr.count==count;
            }
            return false;
        }

        @Override
        public int compareTo(Object o) {
            //根据count属性值来判断两个对象是否相等
            TreeMapTest trTest=(TreeMapTest) o;
            //记住这个方法，是很好的判断了3中情况的存在，1.count>r.count 返回1 ;2.count<r.count返回-1;3count=r.count返回0
            return count>trTest.count ? 1: count<trTest.count-1?-1:0;
        }
    }

    public static void main(String[] args) {
        TreeMap treemap= new TreeMap();
        treemap.put(new TreeMapTest(3),"疯狂java讲义");
        treemap.put(new TreeMapTest(-5), "andior教程");
        treemap.put(new TreeMapTest(9), "平凡的世界");
        System.out.println("完整的treemap:"+treemap);
        System.out.println("map排序中第一个key-value键值对:"+treemap.firstEntry());
        System.out.println("map中排序的第一个key值:"+treemap.firstKey());
        System.out.println("map中在比new（2）小的子map:"+treemap.headMap(new TreeMapTest(2)));
        System.out.println("map中比new（12）小的最大的键值对:"+treemap.lowerEntry(new TreeMapTest(12)));
    }
}
