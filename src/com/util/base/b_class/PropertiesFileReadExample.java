package com.util.base.b_class;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author y15079
 * @create 2017-09-25 20:05
 * @desc
 *
 * java加载properties文件的六种基本方式的代码
 *
 * 1,2,3常用，4，5，6不怎么用
 *
 * http://blog.csdn.net/u011063151/article/details/51888640
 **/
public class PropertiesFileReadExample {

	private static String basePath="D:\\IDEA\\JavaConcurrentUtilExample\\resources\\prop.properties";
	private static String path="";

	/**
	 * 一、使用java.util.Properties类的load(InputStream in)方法加载properties文件
	 * @return
	 */
	public static String getPath1() {

		try {
			InputStream in=new BufferedInputStream(new FileInputStream(new File(basePath)));
			Properties properties=new Properties();
			properties.load(in);
			path=properties.getProperty("path");
			System.out.println(path);
		} catch (IOException e) {
			System.out.println("properties文件路径书写有误，请检查！");
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 二、 使用java.util.ResourceBundle类的getBundle()方法
	 * 注意：这个getBundle()方法的参数只能写成包路径+properties文件名，不加后缀名,否则将抛异常
	 * @return
	 */
	public static String getPath2(){
		Locale locale=new Locale("zh","CN");
		locale=new Locale("en","US");
		ResourceBundle resourceBundle=ResourceBundle.getBundle("myres",locale);
		path=resourceBundle.getString("aaa");

		/*//这个目前只在windows下可以运行，linux下不行，不知道为什么
		ResourceBundle resourceBundle=ResourceBundle.getBundle("prop");
		path=resourceBundle.getString("path");*/
		return path;
	}

	public static String getPath3(){
		InputStream in;

		try {
			in=new BufferedInputStream(new FileInputStream(basePath));
			ResourceBundle resourceBundle=new PropertyResourceBundle(in);
			path=resourceBundle.getString("path");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/***
	 *  四、 使用class变量的getResourceAsStream()方法
	 * 注意：getResourceAsStream()方法的参数按格式写到包路径+properties文件名+.后缀
	 *
	 * 报错，不知道为什么
	 * @return
	 */
	public static String getPath4(){
		try {
			//以本代码文件为目录起点来获取prop.properties
			InputStream in=PropertiesFileReadExample.class.getResourceAsStream("prop.properties");
			Properties properties=new Properties();
			properties.load(in);
			path=properties.getProperty("path");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 五、
	 * 使用class.getClassLoader()所得到的java.lang.ClassLoader的getResourceAsStream()方法
	 * getResourceAsStream(name)方法的参数必须是包路径+文件名+.后缀
	 * 否则会报空指针异常
	 * @return
	 */
	public static String getPath5(){
		InputStream in=PropertiesFileReadExample.class.getClassLoader().getResourceAsStream("D:\\IDEA\\JavaConcurrentUtilExample\\resources\\prop.properties");
		try {
			Properties properties=new Properties();
			properties.load(in);
			path=properties.getProperty("path");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 六、 使用java.lang.ClassLoader类的getSystemResourceAsStream()静态方法
	 * getSystemResourceAsStream()方法的参数格式也是有固定要求的
	 *
	 * @return
	 */
	public static String getPath6() {
		InputStream in = ClassLoader
				.getSystemResourceAsStream("prop.properties");
		Properties p = new Properties();
		try {
			p.load(in);
			path = p.getProperty("path");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}


	public static void main(String[] args) {
//		System.out.println(getPath1());
		System.out.println(getPath2());
//		System.out.println(getPath3());
//		System.out.println(getPath4());
//		System.out.println(getPath5());
//		System.out.println(getPath6());
	}
}
