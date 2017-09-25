package com.util.base.b_class;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author y15079
 * @create: 9/24/17 9:27 PM
 * @desc:
 *
 * ResourceBundle为抽象类
 *
 * java.util.ResourceBundle类用来存储文本以及本地化相关数据。
 *
 * ResourceBundle类有两个子类，PropertyResourceBundle及ListResourceBundle。
 *
 *PropertyResourceBundle将本地化的文本存储于Java property文件中。
 *
 *
 *
 *
 * http://blog.csdn.net/revivedsun/article/details/51330396
 *
 */
public class ListResourceBundleExample {

    //默认的bundle class文件实现
    public class MyClassBundel extends ListResourceBundle{

        @Override
        protected Object[][] getContents() {
            return contents;
        }

        private Object[][] contents={
                {"price",new Double(10.00)},
                {"currency","EUR"},
        };
    }

    //danish resource bundle的实现
    public class MyClassBundle_da extends ListResourceBundle{
        @Override
        protected Object[][] getContents() {
            return new Object[0][];
        }

        private Object[][] contents={
                {"price",new Double(75.00)},
                {"currency","DKK"},
        };
    }

    //注意contents数组，是一个包含key,value的二维数组。price，currency是key，而key右侧为本地化的值。
    //获取ListResourceBundle 实例的方式与获取PropertyResourceBundle实例的方式一样，
    // 下面是获取默认ResourceBundle 及丹麦语ResourceBundle 的例子:
    public static void main(String[] args) {
        //no bundle for German -> default
        Locale locale=new Locale("de","DE");
        ResourceBundle bundle=ResourceBundle.getBundle("i18n.MyClassBundle", locale);
        System.out.println("price:"+bundle.getObject("price"));
        System.out.println("currency:"+bundle.getObject("currency"));

        locale = new Locale("da", "DK");
        bundle = ResourceBundle.getBundle("i18n.MyClassBundle", locale);

        System.out.println("price   : " + bundle.getObject("price"));
        System.out.println("currency: " + bundle.getObject("currency"));

    }
}
