package com.util.base.a_interface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author y15079
 * @create: 9/18/17 3:03 AM
 * @desc:
 *
 * |--List:元素是有序的(怎么存的就怎么取出来，顺序不会乱)，元素可以重复（角标1上有个3，角标2上也可以有个3）因为该集合体系有索引，
   |-- ArrayList：底层的数据结构使用的是数组结构（数组长度是可变的百分之五十延长）（特点是查询很快，但增删较慢）线程不同步
   |-- LinkedList：底层的数据结构是链表结构（特点是查询较慢，增删较快）
   |-- Vector：底层是数组数据结构 线程同步（数组长度是可变的百分之百延长）（无论查询还是增删都很慢，被ArrayList替代了）



    List：特有的方法，凡是可以操作角标的方法都是该体系特有的方法
 *
 */
public class ListAndListIteratorExample {


    public static void main(String[] args) {
//        List_add();
//        List_remove();
//        List_set();
//        List_get();
        listIterator();
    }

    //boolean add(int index, E element)
    //boolean addAll(index,Collection)
    public static void List_add(){
        ArrayList a1=new ArrayList();
        a1.add("java");
        a1.add("php");//list集合中的元素可以重复
        a1.add(".net");
        System.out.println("原集合："+a1);
        a1.add(1,"Flash");
        a1.add(0,"ps");
        System.out.println(a1);

        ArrayList a2=new ArrayList();
        a2.add("javascript");
        a2.add("3dMax");
        a2.add("IBM");

        a1.addAll(0,a2);
        System.out.println(a1);
    }


    //删除指定位置的元素  boolean remove(int index)
    public static void List_remove(){
        ArrayList a1=new ArrayList();
        a1.add("javascript");
        a1.add("php");
        a1.add("flash");
        System.out.println("原集合："+a1);

        a1.remove(0);
        System.out.println(a1);
    }

    //修改指定角标的元素  set(int index, E element)  返回的是修改的那个元素
    public static void List_set(){
        ArrayList a1=new ArrayList();
        a1.add("javascript");
        a1.add("php");
        a1.add(".net");
        System.out.println("原集合："+a1);

        a1.set(1,"flash");
        System.out.println(a1);
    }


    //get(int index)   返回列表中指定位置的元素
    //subList(int fromIndex, int toIndex)
    // 返回列表中指定的 fromIndex（包括 ）和 toIndex（不包括）之间的部分元素。
    public static void List_get(){
        ArrayList a1=new ArrayList();
        a1.add("javascript");
        a1.add("php");
        a1.add(".net");

        //获取指定角标的元素，有了该方法就可以遍历该集合中的所有元素
        System.out.println(a1.get(0));

        //获取集合中某一部分的元素,包含头不包含尾
        System.out.println(a1.subList(1,3));
    }

    /**
     * List集合特有的迭代器:ListIterator(是Iterator的子接口)

     注意：
     在迭代时，是不可以通过集合对象的方法操作集合中的元素
     因为会发生ConcurrentModificationException异常（并发异常）
     所以，在迭代器时，只能用迭代器的方法造作元素
     因为Iterator方法是有限的所以只能对元素进行判断，取出，删除的操作
     如果想要其他的操作如添加，修改等，就需要使用其子接口，ListIterator
     该接口只能通过List集合的listIterator方法获取
     */
    public static void listIterator(){
        ArrayList a1 = new ArrayList();
        a1.add("java01");
        a1.add("java02");
        a1.add("java03");
        a1.add("java04");

        System.out.println("原集合是："+a1);

        //在迭代过程中准备添加或删除元素
        Iterator it=a1.iterator();
        while (it.hasNext()){
            Object obj=it.next();

            if (obj.equals("java02")){
                //al.add("java008");//会出现并发异常，因为迭代器正在操作集合，不能再用集合的方法操作集合了
                it.remove();//将java02的引用从集合中删除
            }

            System.out.println("obj:"+obj);
        }

        //只有List的listIterator有增，删，改，查这些功能，因为只有List有索引
        ListIterator li=a1.listIterator();
        while (li.hasNext()){
            if (li.next().equals("java03")){
                //li.add("java009");
                li.set("java006");
            }
        }
        System.out.println(a1);
    }

}
