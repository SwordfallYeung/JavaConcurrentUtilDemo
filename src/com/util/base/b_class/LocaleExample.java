package com.util.base.b_class;

import java.util.Locale;

/**
 * @author y15079
 * @create: 9/25/17 12:31 AM
 * @desc:
 *
 *  定义:
    public final class Locale extends Object
    Locale 对象表示了特定的地理、政治和文化地区
    字段摘要
    Locale.CHINA
    用于表示中国常量
    Locale.US
    用于表示美国常量
    Locale.JAPAN
    用于表示日本常量
    ...
    三个构造方法
    Locale(String language)
    根据语言代码构造一个语言环境
    Locale(String language, String country)
    根据语言和国家构造一个语言环境
    Locale(String language, String country, String variant)
    根据语言、国家和变量构造一个语言环境
    语言参数是一个有效的 ISO 语言代码,这些代码是由 ISO-639 定义的小写两字母代码
    国家参数是一个有效的 ISO 国家代码,这些代码是由 ISO-3166 定义的大写两字母代码
 *
 */
public class LocaleExample {
    public static void main(String[] args) {
        // 设置默认语言环境
        Locale.setDefault(Locale.CHINA);
        // 获取计算机默认语言环境
        Locale l = Locale.getDefault();
        System.out.println("默认语言代码: " + l.getLanguage());
        System.out.println("默认地区代码: " + l.getCountry());
        System.out.println("默认语言地区代码: " + l.toString());
        System.out.println("---------------------------------------");
        System.out.println("默认语言描述: " + l.getDisplayLanguage());
        System.out.println("默认地区描述: " + l.getDisplayCountry());
        System.out.println("默认语言,地区描述: " + l.getDisplayName());
        System.out.println("---------------------------------------");
        System.out.println("在美国默认语言叫: " + l.getDisplayLanguage(Locale.US));
        System.out.println("在美国默认地区叫: " + l.getDisplayCountry(Locale.US));
        System.out.println("在美国默认语言,地区叫: " + l.getDisplayName(Locale.US));

        System.out.println("在日本默认语言代码叫: " + l.getDisplayLanguage(Locale.JAPAN));
        System.out.println("在日本默认地区代码叫: " + l.getDisplayCountry(Locale.JAPAN));
        System.out.println("在日本默认语言,地区代码叫: " + l.getDisplayName(Locale.JAPAN));
        System.out.println("---------------------------------------");
        System.out.println("语言环境三字母缩写: " + l.getISO3Language());
        System.out.println("国家环境三字母缩写: " + l.getISO3Country());
        System.out.println("---------------------------------------");
        // 机器已经安装的语言环境数组
        Locale[] allLocale = Locale.getAvailableLocales();
        // 返回 ISO 3166 中所定义的所有两字母国家代码
        String[] str1 = Locale.getISOCountries();
        // 返回 ISO 639 中所定义的所有两字母语言代码
        String[] str2 = Locale.getISOLanguages();
    }
}
