package com.util.base.b_class;

import java.util.*;

/**
 * @author y15079
 * @create: 9/24/17 5:17 PM
 * @desc:
 *
 *  LinkedList与ArrayList一样实现List接口，只是ArrayList是List接口的大小可变数组的实现，LinkedList是List接口链表的实现。
 *基于链表实现的方式使得LinkedList在插入和删除时更优于ArrayList，而随机访问则比ArrayList逊色些
 *
 * LinkedList实现所有可选的列表操作，并允许所有的元素包括null。
 *
 * 除了实现 List 接口外，LinkedList 类还为在列表的开头及结尾 get、remove 和 insert 元素提供了统一的命名方法。这些操作允许将链接列表用作堆栈、队列或双端队列。
 *
 *  此类实现 Deque 接口，为 add、poll 提供先进先出队列操作，以及其他堆栈和双端队列操作。
 *
 *  所有操作都是按照双重链接列表的需要执行的。在列表中编索引的操作将从开头或结尾遍历列表（从靠近指定索引的一端）。
 *
 *  同时，与ArrayList一样此实现不是同步的。
 *
 * LinkedList继承AbstractSequentialList，实现List、Deque、Cloneable、Serializable。
 * 其中AbstractSequentialList提供了 List 接口的骨干实现，从而最大限度地减少了实现受“连续访问”数据存储（如链接列表）支持的此接口所需的工作,从而以减少实现List接口的复杂度。
 *
 * Deque一个线性 collection，支持在两端插入和移除元素，定义了双端队列的操作。
 *
 *  get(int index)：返回此列表中指定位置处的元素。

    getFirst()：返回此列表的第一个元素。

    getLast()：返回此列表的最后一个元素。

    indexOf(Object o)：返回此列表中首次出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1。

    lastIndexOf(Object o)：返回此列表中最后出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1。
 *
 *
 * List 是一个接口，它继承于Collection的接口。它代表着有序的队列。
   AbstractList 是一个抽象类，它继承于AbstractCollection。AbstractList实现List接口中除size()、get(int location)之外的函数。
   AbstractSequentialList 是一个抽象类，它继承于AbstractList。AbstractSequentialList 实现了“链表中，根据index索引值操作链表的全部函数”。
   ArrayList, LinkedList, Vector, Stack是List的4个实现类。
   ArrayList 是一个数组队列，相当于动态数组。它由数组实现，随机访问效率高，随机插入、随机删除效率低。
   LinkedList 是一个双向链表。它也可以被当作堆栈、队列或双端队列进行操作。LinkedList随机访问效率低，但随机插入、随机删除效率低。
   Vector 是矢量队列，和ArrayList一样，它也是一个动态数组，由数组实现。但是ArrayList是非线程安全的，而Vector是线程安全的。
   Stack 是栈，它继承于Vector。它的特性是：先进后出(FILO, First In Last Out)。
 *
 *List使用场景
  如果涉及到“栈”、“队列”、“链表”等操作，应该考虑用List，具体的选择哪个List，根据下面的标准来取舍。
  (01) 对于需要快速插入，删除元素，应该使用LinkedList。
  (02) 对于需要快速随机访问元素，应该使用ArrayList。
  (03)
  对于“单线程环境” 或者 “多线程环境，但List仅仅只会被单个线程操作”，此时应该使用非同步的类(如ArrayList)。
  对于“多线程环境，且List可能同时被多个线程操作”，此时，应该使用同步的类(如Vector)。
 *
 * 对比ArrayList和LinkedList的插入、随机读取效率、删除的效率
 *
 * http://www.cnblogs.com/chenssy/p/3514524.html
 * http://www.jb51.net/article/42767.htm
 *
 */
public class LinkedListExample {

    private static final int COUNT=100000;
    private static LinkedList linkedList=new LinkedList();
    private static ArrayList arrayList=new ArrayList();
    private static Vector vector=new Vector();
    private static Stack stack=new Stack();

    public static void main(String[] args) {
        // 换行符
        System.out.println();
        // 插入
        insertByPosition(stack) ;
        insertByPosition(vector) ;
        insertByPosition(linkedList) ;
        insertByPosition(arrayList) ;
        // 换行符
        System.out.println();
        // 随机读取
        readByPosition(stack);
        readByPosition(vector);
        readByPosition(linkedList);
        readByPosition(arrayList);
        // 换行符
        System.out.println();
        // 删除
        deleteByPosition(stack);
        deleteByPosition(vector);
        deleteByPosition(linkedList);
        deleteByPosition(arrayList);
    }

    //获取list的名称
    private static String getListName(List list){
        if (list instanceof LinkedList){
            return "LinkedList";
        }else if (list instanceof ArrayList){
            return "ArrayList";
        }else if (list instanceof Stack){
            return "Stack";
        }else if (list instanceof Vector){
            return "Vector";
        }else {
            return "List";
        }
    }

    //向list的指定位置加入COUNT个元素，并统计时间
    private static void insertByPosition(List list){
        long startTime = System.currentTimeMillis();
        // 向list的位置0插入COUNT个数
        for (int i=0; i<COUNT; i++)
            list.add(0, i);
        long endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println(getListName(list) + " : insert "+COUNT+" elements into the 1st position use time：" + interval+" ms");
    }

    // 从list的指定位置删除COUNT个元素，并统计时间
    private static void deleteByPosition(List list) {
        long startTime = System.currentTimeMillis();
        // 删除list第一个位置元素
        for (int i=0; i<COUNT; i++)
            list.remove(0);
        long endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println(getListName(list) + " : delete "+COUNT+" elements from the 1st position use time：" + interval+" ms");
    }

    // 根据position，不断从list中读取元素，并统计时间
    private static void readByPosition(List list) {
        long startTime = System.currentTimeMillis();
        // 读取list元素
        for (int i=0; i<COUNT; i++)
            list.get(i);
        long endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println(getListName(list) + " : read "+COUNT+" elements by position use time：" + interval+" ms");
    }
}

