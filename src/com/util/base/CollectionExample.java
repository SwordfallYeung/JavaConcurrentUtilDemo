package com.util.base;

/**
 * @author y15079
 * @create: 9/16/17 4:29 PM
 * @desc:
 */
public class CollectionExample {

/**


    在 Java2中，有一套设计优良的接口和类组成了Java集合框架Collection，使程序员操作成批的数据或对象元素极为方便。
 这些接口和类有很多对抽象数据类型操作的API，而这是我们常用的且在数据结构中熟知的。例如Map，Set，List等。
 并且Java用面向对象的设计对这些数据结构和算法进行了封装，这就极大的减化了程序员编程时的负担。
 程序员也可以以这个集合框架为基础，定义更高级别的数据抽象，比如栈、队列和线程安全的集合等，从而满足自己的需要。

    Java2的集合框架，抽其核心，主要有三种：List、Set和Map。如下图所示：

    需要注意的是，这里的 Collection、List、Set和Map都是接口（Interface），不是具体的类实现。 List lst = new ArrayList(); 这是我们平常经常使用的创建一个新的List的语句，在这里， List是接口，ArrayList才是具体的类。

    常用集合类的继承结构如下：
    Collection<--List<--Vector
    Collection<--List<--ArrayList
    Collection<--List<--LinkedList
    Collection<--Set<--HashSet
    Collection<--Set<--HashSet<--LinkedHashSet
    Collection<--Set<--SortedSet<--TreeSet
    Map<--SortedMap<--TreeMap
    Map<--HashMap

 */
}
