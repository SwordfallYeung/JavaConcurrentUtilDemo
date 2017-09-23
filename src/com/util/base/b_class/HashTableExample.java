package com.util.base.b_class;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author y15079
 * @create: 9/23/17 11:28 PM
 * @desc:
 *
 * 哈希表是一种重要的存储方式，也是一种常见的检索方法。其基本思想是将关系码的值作为自变量，通过一定的函数关系计算出对应的函数值，把这个数值解释为结点的存储地址，将结点存入计算得到存储地址所对应的存储单元。检索时采用检索关键码的方法。现在哈希表有一套完整的算法来进行插入、删除和解决冲突。在Java中哈希表用于存储对象，实现快速检索。
 *
 * Java.util.Hashtable提供了种方法让用户使用哈希表，而不需要考虑其哈希表真正如何工作。
　　哈希表类中提供了三种构造方法，分别是：
　　public Hashtable()
　　public Hashtable(int initialcapacity)
　　public Hashtable(int initialCapacity,float loadFactor)

   参数initialCapacity是Hashtable的初始容量，它的值应大于0。loadFactor又称装载因子，是一个0.0到0.1之间的float型的浮点数。它是一个百分比，表明了哈希表何时需要扩充，例如，有一哈希表，容量为100，而装载因子为0.9，那么当哈希表90%的容量已被使用时，此哈希表会自动扩充成一个更大的哈希表。如果用户不赋这些参数，系统会自动进行处理，而不需要用户操心。
 *
 * Hashtable提供了基本的插入、检索等方法。
 *
 *■插入
　　public synchronized void put(Object key,Object value)
   给对象value设定一关键字key,并将其加到Hashtable中。若此关键字已经存在，则将此关键字对应的旧对象更新为新的对象Value。这表明在哈希表中相同的关键字不可能对应不同的对象(从哈希表的基本思想来看，这也是显而易见的)。
 *
 *■检索
　　public synchronized Object get(Object key)
　　根据给定关键字key获取相对应的对象。
　　public synchronized boolean containsKey(Object key)
　　判断哈希表中是否包含关键字key。
　　public synchronized boolean contains(Object value)
　　判断value是否是哈希表中的一个元素。
 *
 * ■删除
　　public synchronized object remove(object key)
　　从哈希表中删除关键字key所对应的对象。
　　public synchronized void clear()
　　清除哈希表
 *
 * 另外，Hashtalbe还提供方法获取相对应的枚举集合：
　　public synchronized Enumeration keys()
　　返回关键字对应的枚举对象。
　　public synchronized Enumeration elements()
　　返回元素对应的枚举对象。
 *
 * http://blog.csdn.net/zhna123_2011/article/details/6741479
 */
public class HashTableExample {
    public static void main(String[] args) {
        Hashtable hashtable=new Hashtable(2,(float)0.8);
        //创建了一个哈希表的对象hash，初始容量为2，装载因子为0.8

        hashtable.put("Jiangsu","Nanjing");
        //将字符串对象“Jiangsu”给定一关键字“Nanjing”,并将它加入Hash
        hashtable.put("Beijing","Beijing");

        System.out.println("The hashtable hash1 is: "+hashtable);
        System.out.println("The size of this hash table is "+hashtable.size());
        //打印hash的内容和大小hashtable.put("Zhejiang","Hangzhou");

        Enumeration enumeration=hashtable.elements();
        System.out.print("The element of hashtable is: ");
        while (enumeration.hasMoreElements()){
            System.out.print(enumeration.nextElement()+" ");
        }
        System.out.println();
        //依次打印hash中的内容
        if(hashtable.containsKey("Jiangsu"))
            System.out.println("The capatial of Jiangsu is "+hashtable.get("Jiangsu"));

        hashtable.remove("Beijing");
        //删除关键字Beijing对应对象
        System.out.println("The hashtable hash2 is: "+hashtable);
        System.out.println("The size of this hash table is "+hashtable.size());
    }
}
