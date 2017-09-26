package com.util.base.b_class;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author y15079
 * @create 2017-09-26 9:56
 * @desc
 *
 * http://blog.csdn.net/scythe666/article/details/51980596
 *
 * Scanner主要是对java基本类型的操作
 *
 * java.util.Scanner是Java5的新特征，主要功能是简化文本扫描。这个类最实用的地方表现在获取控制台输入，其他的功能都很鸡肋，
   尽管Java API文档中列举了大量的API方法，但是都不怎么地。
 *
 * Scanner 构造器：
 *可以从字符串（Readable）、输入流、文件等等来直接构建Scanner对象，有了Scanner了，就可以逐段（根据正则分隔式）来扫描整个文本，并对扫描后的结果做想要的处理。
 *
 *下面这几个相对实用：
     delimiter()
     返回此 Scanner 当前正在用于匹配分隔符的 Pattern。
     hasNext()
     判断扫描器中当前扫描位置后是否还存在下一段。（原APIDoc的注释很扯淡）
     hasNextLine()
     如果在此扫描器的输入中存在另一行，则返回 true。
     next()
     查找并返回来自此扫描器的下一个完整标记。
     nextLine()
     此扫描器执行当前行，并返回跳过的输入信息。
 *
 **/
public class ScannerExample {

	/**
	 * 扫描控制台输入
	 */

	/**
	 * 逐行扫描文件，并逐行输出
	 * @throws Exception
	 */
	public static void getFileStream() throws Exception{
		InputStream in = new FileInputStream(new File("D:\\IDEA\\JavaConcurrentUtilExample\\resources\\Test.properties"));
		Scanner s = new Scanner(in);
		while(s.hasNextLine()){
			System.out.println(s.nextLine());
		}
	}

    public static void consoleOutput(){
		Scanner s = new Scanner(System.in);
		System.out.println("请输入字符串：");
		while (true) {
			String line = s.nextLine();
			if (line.equals("exit")) break;
			System.out.println(">>>" + line);
		}
	}

	public static void main(String[] args) throws Exception {
//		consoleOutput();
		getFileStream();
	}
}
