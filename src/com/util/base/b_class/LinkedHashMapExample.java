package com.util.base.b_class;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author y15079
 * @create: 9/24/17 1:01 PM
 * @desc:
 *
 * HashMap是一种非常常见、非常有用的集合，但在多线程情况下使用不当会有线程安全问题
 *
 * 大多数情况下，只要不涉及线程安全问题，Map基本都可以使用HashMap，不过HashMap有一个问题，
 * 就是迭代HashMap的顺序并不是HashMap放置的顺序，也就是无序。HashMap的这一缺点往往会带来困扰，因为有些场景，我们期待一个有序的Map。
 *
 * 这个时候，LinkedHashMap就闪亮登场了，它虽然增加了时间和空间上的开销，但是通过维护一个运行于所有条目的双向链表，LinkedHashMap保证了元素迭代的顺序。
 * 该迭代顺序可以是插入顺序或者是访问顺序。
 *
 * 二、四个关注点在LinkedHashMap上的答案
   关  注  点 	                结      论
   LinkedHashMap是否允许空 	    Key和Value都允许空
   LinkedHashMap是否允许重复数据 	Key重复会覆盖、Value允许重复
   LinkedHashMap是否有序 	        有序
   LinkedHashMap是否线程安全 	    非线程安全
 *
 * 三、LinkedHashMap基本结构
   关于LinkedHashMap，先提两点：
   1、LinkedHashMap可以认为是HashMap+LinkedList，即它既使用HashMap操作数据结构，又使用LinkedList维护插入元素的先后顺序。
   2、LinkedHashMap的基本实现思想就是----多态。可以说，理解多态，再去理解LinkedHashMap原理会事半功倍；
     反之也是，对于LinkedHashMap原理的学习，也可以促进和加深对于多态的理解。
 *
 * LinkedHashMap是HashMap的子类，自然LinkedHashMap也就继承了HashMap中所有非private的方法。
 *
 * LinkedHashMap中并没有什么操作数据结构的方法，也就是说LinkedHashMap操作数据结构（比如put一个数据），和HashMap操作数据的方法完全一样，无非就是细节上有一些的不同罢了。
 *
 * LinkedHashMap和HashMap的区别在于它们的基本数据结构上，看一下LinkedHashMap的基本数据结构，也就是Entry
 *
 * 列一下Entry里面有的一些属性吧：

   1、K key

   2、V value

   3、Entry<K, V> next

   4、int hash

   5、Entry<K, V> before

   6、Entry<K, V> after
   其中前面四个，也就是红色部分是从HashMap.Entry中继承过来的；
   后面两个，也就是蓝色部分是LinkedHashMap独有的。
   不要搞错了next和before、After，next是用于维护HashMap指定table位置上连接的Entry的顺序的，
   before、After是用于维护Entry插入的先后顺序的。

 *
 * 总得来看，再说明一遍，LinkedHashMap的实现就是HashMap+LinkedList的实现方式，以HashMap维护数据结构，以LinkList的方式维护数据插入顺序
 *
 * http://www.cnblogs.com/xiaoxi/p/6170590.html
 */
public class LinkedHashMapExample {

    public static void main(String[] args) {
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>(16,0.75f,true);
        linkedHashMap.put("111","111");
        linkedHashMap.put("222","222");
        linkedHashMap.put("333", "333");
        linkedHashMap.put("444", "444");
        loopLinkedHashMap(linkedHashMap);
        linkedHashMap.get("111");
        loopLinkedHashMap(linkedHashMap);
        linkedHashMap.put("222", "2222");
        loopLinkedHashMap(linkedHashMap);
    }

    public static void loopLinkedHashMap(LinkedHashMap<String, String> linkedHashMap)
    {
        //采用迭代器遍历
        Set<Map.Entry<String, String>> set = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();

        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();
    }

    //注意这里的构造方法要用三个参数那个且最后的要传入true，这样才表示按照访问顺序排序。看一下代码运行结果：
    /**
     * 111=111    222=222    333=333    444=444
     * 222=222    333=333    444=444    111=111
     * 333=333    444=444    111=111    222=2222
     *
     */
    //代码运行结果证明了两点：

    //1、LinkedList是有序的

    //2、每次访问一个元素（get或put），被访问的元素都被提到最后面去了

}
