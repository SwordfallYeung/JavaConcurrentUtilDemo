package com.util.base.b_class;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author y15079
 * @create: 9/25/17 11:47 PM
 * @desc:
 *
 * PropertyResourceBundle是ResourceBundle的具体子类，是通过对属性文件的静态字符串管理来语言环境资源。
 * 与其他资源包类型不同，不能为 PropertyResourceBundle 创建子类。相反，要提供含有资源数据的属性文件。
 * esourceBundle.getBundle 将自动查找合适的属性文件并创建引用该文件的 PropertyResourceBundle
 *
 */
public class PropertyResourceBundleExample {
    public static void main(String[] args) throws Exception {
        String name="";
        InputStream in = new BufferedInputStream(new FileInputStream(name));
        ResourceBundle rb = new PropertyResourceBundle(in);
    }
}
