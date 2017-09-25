package com.util.base.b_class;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author y15079
 * @create 2017-09-25 17:19
 * @desc
 *
 * Java中有个比较重要的类Properties（Java.util.Properties），主要用于读取Java的配置文件，各种语言都有自己所支持的配置文件
 * 在Java中，其配置文件常为.properties文件，格式为文本文件，文件的内容的格式是“键=值”的格式，文本注释信息可以用"#"来注释。
 *
 * Properties 继承 HastTable类
 *
 * 主要方法：
   1． getProperty ( String key)，用指定的键在此属性列表中搜索属性。也就是通过参数 key ，得到 key 所对应的 value。
   2． load ( InputStream inStream)，从输入流中读取属性列表（键和元素对）。通过对指定的文件（比如说上面的 test.properties 文件）进行装载来获取该文件中的所有键 - 值对。
       以供 getProperty ( String key) 来搜索。
   3． setProperty ( String key, String value) ，调用 Hashtable 的方法 put 。他通过调用基类的put方法来设置 键 - 值对。
   4． store ( OutputStream out, String comments)，以适合使用 load 方法加载到 Properties 表中的格式，将此 Properties 表中的属性列表（键和元素对）写入输出流。与 load 方法相反，该方法将键 - 值对写入到指定的文件中去。
   5． clear ()，清除所有装载的 键 - 值对。该方法在基类中提供。
   6． list ()，一个是打印到PrintWriter, 一个是打印到指定文件中
 *
 *
 * Java读取Properties文件的方法有很多，详见： Java读取Properties文件的六种方法
   但是最常用的还是通过java.lang.Class类的getResourceAsStream(String name)方法来实现，如下可以这样调用：
   [1]InputStream in = getClass().getResourceAsStream("资源Name");作为我们写程序的，用此一种足够。
   [2]InputStream in = new BufferedInputStream(new FileInputStream(filepath));
 *
 *
 *
 *
 * http://www.cnblogs.com/bakari/p/3562244.html
 **/
public class PropertiesExample {

	public static void ReadJVM(){
		Properties properties=System.getProperties();
		properties.list(System.out);
	}

	public static void listReadProperty() throws Exception{
		Properties properties=new Properties();
		properties.load(new FileInputStream("D:\\IDEA\\JavaConcurrentUtilExample\\resources\\Test.properties"));
		Enumeration enumeration=properties.propertyNames();//得到配置文件的名字
		while (enumeration.hasMoreElements()){
			String strKey=(String)enumeration.nextElement();
			String strValue=properties.getProperty(strKey);
			System.out.println(strKey + "=" + strValue);
		}
	}

	//根据Key读取Value
	public static String GetValueByKey(String filePath,String key) throws Exception{
		try {
			Properties properties=new Properties();
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			properties.load(in);
			String value=properties.getProperty(key);
			System.out.println(key+"="+value);
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	//读取Properties的全部信息
	public static void GetAllProperties(String filePath) throws Exception{
		Properties properties=new Properties();
		InputStream in=new BufferedInputStream(new FileInputStream(filePath));
		properties.load(in);
		Enumeration en=properties.propertyNames();//得到配置文件的所有配置名字key
		while (en.hasMoreElements()){
			String strKey=(String)en.nextElement();
			String strValue=properties.getProperty(strKey);
			System.out.println(strKey+"="+strValue);
		}
	}

	//写入Properties信息
	public static void WriteProperties(String filePath,String pKey,String pValue) throws Exception{
		Properties properties=new Properties();

		InputStream in=new FileInputStream(filePath);
		//从输入流中读取属性列表（键和元素对）
		properties.load(in);
		//调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
		//强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		OutputStream out=new FileOutputStream(filePath);
		properties.setProperty(pKey,pValue);
		//以适合使用 load 方法加载到 Properties 表中的格式，
		//将此 Properties 表中的属性列表（键和元素对）写入输出流
		properties.store(out,"Update "+pKey+" name");

	}

	public static void main(String[] args) throws Exception {
//		ReadJVM();
//		listReadProperty();
		WriteProperties("D:\\IDEA\\JavaConcurrentUtilExample\\resources\\Test.properties","long", "212");
	}
}
