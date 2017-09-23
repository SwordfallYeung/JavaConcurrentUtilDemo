package com.util.base.b_class;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;

/**
 * @author y15079
 * @create: 9/23/17 12:33 PM
 * @desc:
 *
 * EnumSet为抽象类，EnumSet的实现类为RegularEnumSet，RegularEnumSet
 *
 * EnumSet是Java枚举类型的泛型容器，Java既然有了SortedSet、TreeSet、HashSet等容器，为何还要多一个EnumSet<T>呢？
 * 答案肯定是EnumSet有一定的特性，举个例子，EnumSet的速度很快。
 *
 *首先以事实说话，存在这样一个EnumSet，它有50个枚举值T0～T49，将50个值插入到容器（HashSet、EnumSet）中，为一个操作，将50个枚举值移出做为第二个操作。
 * 把第一个和第二个操作执行的总时间设定为一个周期，
 * 拿HashSet操作的一个周期和EnumSet的一个周期做比较自然没什么意义，所以我们用50个周期的和做为比较，HashSet耗费9ms，EnumSet耗费4ms（这个结果只说明同样的操作EnumSet比HashSet更快，不做为其他参考依据，因为这个时间不是线程独占时间）。
 *
 *
 * EnumSet 是一个专为枚举设计的集合类，EnumSet中的所有元素都必须是指定枚举类型的枚举值，该枚举类型在创建EnumSet时显式或隐式地指定。

   EnumSet的集合元素也是有序的，EnumSet以枚举值在Enum类内的定义顺序来决定集合元素的顺序。
   EnumSet在内部以位向量的形式存储，这种存储形式非常紧凑、高效,因此EnumSet对象占用内存很小，而且运行效率很好。尤其是进行批量操作（如调用containsAll()和retainAll()方法）时，如果其参数也是EnumSet集合，则该批量操作的执行速度也非常快。
   EnumSet集合不允许加入null元素，如果试图插入null元素，EnumSet将抛出NullPointerException异常。
   EnumSet类没有暴露任何构造器来创建该类的实例，程序应该通过它提供的类方法来创建EnumSet对象。
   如果只是想判断EnumSet是否包含null元素或试图删除null元素都不会抛出异常，只是删除操作将返回false，因为没有任何null元素被删除。

   方法介绍：

    EnumSet allOf(Class elementType): 创建一个包含指定枚举类里所有枚举值的EnumSet集合。
    EnumSet complementOf(EnumSet e): 创建一个其元素类型与指定EnumSet里元素类型相同的EnumSet集合，新EnumSet集合包含原EnumSet集合所不包含的、此类枚举类剩下的枚举值（即新EnumSet集合和原EnumSet集合的集合元素加起来是该枚举类的所有枚举值）。
    EnumSet copyOf(Collection c): 使用一个普通集合来创建EnumSet集合。
    EnumSet copyOf(EnumSet e): 创建一个指定EnumSet具有相同元素类型、相同集合元素的EnumSet集合。
    EnumSet noneOf(Class elementType): 创建一个元素类型为指定枚举类型的空EnumSet。
    EnumSet of(E first,E…rest): 创建一个包含一个或多个枚举值的EnumSet集合，传入的多个枚举值必须属于同一个枚举类。
    EnumSet range(E from,E to): 创建一个包含从from枚举值到to枚举值范围内所有枚举值的EnumSet集合。
 *
 *
 *
 */
public class EnumSetExample {

    static enum Session{
        SPRING,
        SUMMER,
        FAIL,
        WINTER
    }

    public static void main(String[] args) {
        //1.创建一个包含Session(枚举类)里所有枚举值的EnumSet集合
        EnumSet e1=EnumSet.allOf(Session.class);//里面new RegularEnumSet
        System.out.println(e1);//[SPRING, SUMMER, FAIL, WINTER]

        //2.创建一个空EnumSet
        EnumSet e2=EnumSet.noneOf(Session.class);
        System.out.println(e2);//[]

        //3.add() 空EnumSet集合中添加枚举元素
        e2.add(Session.SPRING);
        e2.add(Session.SUMMER);
        System.out.println(e2);//[SPRING, SUMMER]

        //4.以指定枚举值创建EnumSet集合
        EnumSet e3=EnumSet.of(Session.SPRING,Session.FAIL);
        System.out.println(e3);//[SPRING, FAIL]

        //5.创建一个包含从from枚举值到to枚举值范围内所有枚举值的EnumSet集合
        EnumSet e4=EnumSet.range(Session.SPRING,Session.FAIL);
        System.out.println(e4);//[SPRING, SUMMER, FAIL]

        //6.创建一个其元素类型与指定EnumSet里元素类型相同的EnumSet集合，
        //新EnumSet集合包含原EnumSet集合所不包含的枚举值
        EnumSet e5=EnumSet.complementOf(e4);
        System.out.println(e5);//[WINTER]

        //7.除此之外还可以复制另一个EnumSet集合中的所有元素来创建新的EnumSet集合，
        // 或者复制另一个Collection集合中的所有元素来创建新的EnumSet集合。
        Collection c=new HashSet();
        c.clear();
        c.add(Session.SPRING);
        c.add(Session.FAIL);
        EnumSet e6=EnumSet.copyOf(c);
        System.out.println(e6);//[SPRING, FAIL]
        //当复制Collection集合中所有元素来创建新的EnumSet集合时，要求Collection集合中的所有元素必须是同一个枚举类的枚举值。



    }

}
