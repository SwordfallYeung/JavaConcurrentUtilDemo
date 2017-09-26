package com.util.base.b_class;

import java.util.Random;
import java.util.StringTokenizer;

/**
 * @author y15079
 * @create 2017-09-26 17:22
 * @desc
 *
 * StringTokenizer是一个用来分隔String的应用类， 与StringJoiner相反，
 * 前者是把string根据分割符分割，后者是根据分隔符组建string
 *
 * StringTokenizer类：根据自定义字符为分界符进行拆分，并将结果进行封装提供对应方法进行遍历取值，
 * StringTokenizer 方法不区分标识符、数和带引号的字符串，它们也不识别并跳过注释；该方法用途类似于split方法，只是对结果进行了封装；
 * 建议所有寻求此功能的人使用 String 的 split 方法或 java.util.regex 包。
 *
 * 1.构造函数
   public StringTokenizer(String str)
   public StringTokenizer(String str, String delim)
   public StringTokenizer(String str, String delim, boolean returnDelims)
   第一个参数就是要分隔的String，第二个是分隔字符集合，第三个参数表示分隔符号是否作为标记返回，如果不指定分隔字符，默认的是：”\t\n\r\f”
 *
 * 2.核心方法
   public boolean hasMoreTokens()
   public String nextToken()
   public String nextToken(String delim)
   public int countTokens()
   其实就是三个方法，返回分隔字符块的时候也可以指定分割符，而且以后都是采用最后一次指定的分隔符号。
 *
 * 3.多余方法
   public boolean hasMoreElements()
   public boolean hasMoreElements()
   这个类实现了Enumeration接口，所以多了这么两个方法，其实根本没有必要实现这个接口
   它的名字就叫StringTokenizer，返回一个Object就没有什么意思了。
 *
 *
 * StringTokenizer和Spilt速度比较
 *
 * 一百万以空格区分的字符串，拆分后重新组成不带空格的串。
 *
 * https://my.oschina.net/jasonli0102/blog/272061
 * http://www.cnblogs.com/junneyang/p/5842937.html
 *
 **/
public class StringTokenizerExample {
	public static void main(String[] args) {
		String str=buildString(1_000_000);//1.7新特性，1_000_000
		long start;
		long end;

		System.out.println("-----------StringTokenizer start-----------");
		start=System.currentTimeMillis();
		StringTokenizer st=new StringTokenizer(str);
		StringBuilder sb=new StringBuilder();
		while (st.hasMoreTokens()){
			sb.append(st.nextToken());
		}
		end=System.currentTimeMillis();
		System.out.println("StringTokenizer time use:" + (end-start));

		System.out.println("-----------StringSpilt start-----------");
		start = System.currentTimeMillis();
		StringBuilder sb2=new StringBuilder();
		String[] strs=str.split("\\s");
		for (String s:strs){
			sb2.append(s);
		}
		end = System.currentTimeMillis();
		System.out.println("StringSpilt time use:" + (end-start));
	}

	//建立一个长字符串
	//其中有空格，以便拆分成length长度的n个字符串
	private static String buildString(int length){
		StringBuilder sb=new StringBuilder();
		Random r=new Random();
		for (int i=0;i<length;i++){
			for (int j=r.nextInt(10);j>0;j--){
				sb.append((char)('a'+r.nextInt(26)));
			}
			sb.append(" ");
		}
		return sb.toString();
	}
}
