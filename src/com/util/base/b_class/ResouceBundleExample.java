package com.util.base.b_class;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author y15079
 * @create: 9/25/17 11:51 PM
 * @desc:
 *
 * ResourceBundle与Properties的区别是，前者提供软件国际化的捷径，后着貌似不提供
 *
 * 过此类，可以使您所编写的程序可以：

   轻松地本地化或翻译成不同的语言
   一次处理多个语言环境
   以后可以轻松地进行修改，支持更多的语言环境

  说的简单点，这个类的作用就是读取资源属性文件（properties），然后根据.properties文件的名称信息（本地化信息），
  匹配当前系统的国别语言信息（也可以程序指定），然后获取相应的properties文件的内容。
 *
 * 注意：

   properties文件的名字是有规范的：一般的命名规范是： 自定义名_语言代码_国别代码.properties
   如果是默认的，直接写为：自定义名.properties
 *
 * 比如：
   myres_en_US.properties
   myres_zh_CN.properties
   myres.properties
 *
 * [1]当在中文操作系统下，如果myres_zh_CN.properties、myres.properties两个文件都存在，则优先会使用myres_zh_CN.properties，
 *    当myres_zh_CN.properties不存在时候，会使用默认的myres.properties。
   [2]没有提供语言和地区的资源文件是系统默认的资源文件。
   [3]资源文件都必须是ISO-8859-1编码，因此，对于所有非西方语系的处理，都必须先将之转换为Java Unicode Escape格式。
      转换方法是通过JDK自带的工具native2ascii.
 *
 *http://blog.csdn.net/fanxiaobin577328725/article/details/52071310
 */
public class ResouceBundleExample {

    /**
     * 国际化资源绑定测试
     * @param args
     */
    public static void main(String[] args) {
        Locale locale=new Locale("zh","CN");
        ResourceBundle rb1=ResourceBundle.getBundle("myres",locale);
        System.out.println(rb1.getString("aaa"));

        ResourceBundle rb2 = ResourceBundle.getBundle("myres", Locale.getDefault());
        System.out.println(rb2.getString("aaa"));

        locale=new Locale("en","US");
        ResourceBundle rb3 = ResourceBundle.getBundle("myres", locale);
        System.out.println(rb3.getString("aaa"));
    }
}
