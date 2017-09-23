package com.util.base.b_class;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

/**
 * @author y15079
 * @create: 9/24/17 12:08 AM
 * @desc:
 *
 * 这个类主要是和stream类配合使用的，在java.util包中，
 * 主要用于统计整形数组中元素的最大值，最小值，平均值，个数，元素总和等等。
 * 下面是一个简单的例子：
 *
 * http://blog.csdn.net/huangwenyi1010/article/details/51489222
 */
public class IntSummaryStatisticsExample {
    public static void main(String[] args) {
        int[] intArray={12,3,34,67,100,99};
        /** 第一种构造intStream **/
        IntStream intStream=IntStream.of(intArray);
        /** 第二种构造intStream **/
        //IntStream intStream2 = IntStream.of(12,3,34,67,100,99);
        /** 这个是重点，获得当前int数组的统计信息，包括 **/
        IntSummaryStatistics statistics=intStream.summaryStatistics();
        System.out.println("the max:" + statistics.getMax());
        System.out.println("the min:" + statistics.getMin());
        System.out.println("the average:" + statistics.getAverage());
        System.out.println("the sum:" + statistics.getSum());
        System.out.println("the count:" + statistics.getCount());
    }
}
