package com.util.base.a_interface;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author y15079
 * @create: 9/17/17 11:01 PM
 * @desc:
 *
 * Enumeration（枚举）接口的作用和Iterator类似，
 * 只提供了遍历Vector和HashTable类型集合元素的功能，
 * 不支持元素的移除操作。
 */
public class EnumerationExample {

    public static void main(String[] args) {
        Vector v=new Vector();
        v.addElement("Lisa");
        v.addElement("Billy");
        v.addElement("Mr Brown");

        Enumeration e=v.elements();//返回Enumeration对象
        while (e.hasMoreElements()){
            String value=(String)e.nextElement();//调用nextElement方法获得元素
            System.out.println(value);
        }
    }
}
