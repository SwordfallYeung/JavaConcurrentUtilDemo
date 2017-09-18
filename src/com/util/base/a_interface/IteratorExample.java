package com.util.base.a_interface;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author y15079
 * @create: 9/18/17 2:51 AM
 * @desc:
 *
 * Iterator接口
　　Iterator接口也是Java集合框架的成员，但它与Collection系列、Map系列的集合不一样：Collection系列集合、Map系列集合主要用于盛装其他对象，而Iterator则主要用于遍历（即迭代访问）Collection集合中的元素，Iterator对象也被称为迭代器。
　　Iterator接口里定义了如下4个方法：
　　　　–boolean hasNext()：如果被迭代的集合还元素没有被遍历，则返回true。
　　　　–Object next()：返回集合里下一个元素。
　　　　–void remove() ：删除集合里上一次next方法返回的元素
　　　　–void forEachRemaining(Consumer action)，这是Java 8为Iterator新增的默认方法，该方法可使用Lambda表达式来遍历集合元素。
 *
 */
public class IteratorExample {

    public static void main(String[] args) {
        //创建集合，添加元素
        Collection books=new HashSet();
        books.add("轻量级Java EE企业应用实践");
        books.add("疯狂Java讲义");
        books.add("疯狂Android讲义");

        //获取books集合对应的迭代器
        Iterator it=books.iterator();
        while (it.hasNext()){
            //it.next()方法返回的数据类型是Object类型，因此需要强制类型转换
            String book=(String)it.next();
            System.out.println(book);
            if (book.equals("疯狂Java讲义"))
            {
                //从集合中删除上一次next方法返回的元素
                it.remove();
            }
            //对book变量赋值，不会改变集合元素本身
            book="测试字符串";
        }
        System.out.println(books);
    }
}
