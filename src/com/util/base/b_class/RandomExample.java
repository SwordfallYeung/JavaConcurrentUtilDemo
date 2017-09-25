package com.util.base.b_class;

/**
 * @author y15079
 * @create: 9/26/17 12:20 AM
 * @desc:
 *
 * stream流，针对Double，int,Long进行操作，来自于jdk1.8
 *
 * 下个随机类，针对byte,int,long,float,double,boolean共6个基本类型进行操作，除了short,char
 *
 * 1、Random对象的生成

   Random类包含两个构造方法，下面依次进行介绍：
   [1]a、public Random()
     该构造方法使用一个和当前系统时间对应的相对时间有关的数字作为种子数，然后使用这个种子数构造Random对象。
   [2]b、public Random(long seed)
      该构造方法可以通过制定一个种子数进行创建。

   [3]示例代码：
      Random r = new Random();
      Random r1 = new Random(10);
 *
 * 2.常用方法
 * [1] nextBoolean   该方法的作用是生成一个随机的boolean值，生成true和false的值几率相等，也就是都是50%的几率。
 * [2] nextDouble    该方法的作用是生成一个随机的double值，数值介于[0,1.0)之间
 * [3] nextInt       该方法的作用是生成一个随机的int值，该值介于int的区间，也就是-231到231-1之间
 * [4]nextInt(int n) 该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
 * [5] setSeed       该方法的作用是重新设置Random对象中的种子数。设置完种子数以后的Random对象和相同种子数使用new关键字创建出的Random对象相同。
 *
 * 3.区间实现
 *  [1,2.5）  double d3 = r.nextDouble() * 1.5 + 1;
 *  任意整数   int n1 = r.nextInt();
 *  [0,10)    int n2 = r.nextInt(10);
 *  [0,10]    int n3 = r.nextInt(11);
 *  [-3,15)   int n4 = r.nextInt(18) - 3;
 * 4. 几率问题
 *
 * http://www.cnblogs.com/Fskjb/archive/2009/08/29/1556417.html
 */
public class RandomExample {


}
