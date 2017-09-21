package com.util.base.b_class;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author y15079
 * @create: 9/22/17 2:38 AM
 * @desc:
 *
 * 在Java中，有一种key值可以重复的map，就是IdentityHashMap。在IdentityHashMap中，判断两个键值k1和 k2相等的条件是 k1 == k2 。在正常的Map 实现（如 HashMap）中，
 * 当且仅当满足下列条件时才认为两个键 k1 和 k2 相等：(k1==null ? k2==null : e1.equals(e2))
 *
 * IdentityHashMap类利用哈希表实现 Map 接口，比较键（和值）时使用引用相等性代替对象相等性。该类不是 通用 Map 实现！此类实现 Map 接口时，它有意违反 Map 的常规协定，该协定在比较对象时强制使用 equals 方法。
 * 此类设计仅用于其中需要引用相等性语义的罕见情况。
 *
 * 可以识别两个对象是否来自同一对象
 *
 * http://www.cnblogs.com/it-deepinmind/p/7309522.html
 */
public class IdentityHashMapExample {
    public static void main(String[] args) {

    }

    //key可以重复
    public void testEquals(){
        IdentityHashMap<String,Object> map =new IdentityHashMap<String,Object>();
        map.put(new String("xx"),"first");
        map.put(new String("xx"),"second");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.print(entry.getKey() +"    ");
            System.out.println(entry.getValue());
        }
        System.out.println("idenMap="+map.containsKey("xx"));
        System.out.println("idenMap="+map.get("xx"));
    }
    /**
     *
     xx    first
     xx    second
     idenMap=false
     idenMap=null
     */

    public void testEquals2(){
        IdentityHashMap<String,Object> map =new IdentityHashMap<String,Object>();
        String fsString =new String("xx");
        map.put(fsString,"first");
        map.put(new String("xx"),"second");
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.print(entry.getKey() +"    ");
            System.out.println(entry.getValue());
        }
        System.out.println("idenMap="+map.containsKey(fsString));
        System.out.println("idenMap="+map.get(fsString));
    }
    /**
     *
     xx    second
     xx    first
     idenMap=true
     idenMap=first
     */

    public void testEquals3(){
        IdentityHashMap<String,Object> map =new IdentityHashMap<String,Object>();
        String fsString =new String("xx");
        map.put(fsString,"first");
        map.put(fsString,"second");
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.print(entry.getKey() +"    ");
            System.out.println(entry.getValue());
        }
        System.out.println("idenMap="+map.containsKey(fsString));
        System.out.println("idenMap="+map.get(fsString));
    }
    /**
     xx    second
     idenMap=true
     idenMap=second
     */

    public void testEquals4(){
        IdentityHashMap<String,Object> map =new IdentityHashMap<String,Object>();
        String fsString =new String("xx");
        String secString =new String("xx");
        map.put(fsString,"first");
        map.put(secString,"second");
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.print(entry.getKey() +"    ");
            System.out.println(entry.getValue());
        }
        System.out.println("idenMap="+map.containsKey(fsString));
        System.out.println("idenMap="+map.get(fsString));

        System.out.println("idenMap="+map.containsKey(secString));
        System.out.println("idenMap="+map.get(secString));
    }

    /**
     xx    first
     xx    second
     idenMap=true
     idenMap=first
     idenMap=true
     idenMap=second
     */

    public void testEquals5(){
        IdentityHashMap<String,Object> map =new IdentityHashMap<String,Object>();
        map.put("xx","first");
        map.put("xx","second");
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.print(entry.getKey() +"    ");
            System.out.println(entry.getValue());
        }
    }
    /**
     xx    second
     */

    //可以看到，在IdentityHashMap中，是判断key是否为同一个对象，而不是普通HashMap的equals方式判断。
}
