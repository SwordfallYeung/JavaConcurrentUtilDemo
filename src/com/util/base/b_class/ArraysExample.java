package com.util.base.b_class;

import java.util.Arrays;

/**
 * @author y15079
 * @create 2017-09-20 14:30
 * @desc
 *
 * java.util.Arrays类能方便地操作数组，它提供的所有方法都是静态的。具有以下功能：
  [1] 给数组赋值：通过fill方法。
  [2] 对数组排序：通过sort方法,按升序。
  [3] 比较数组：通过equals方法比较数组中元素值是否相等。
  [4] 查找数组元素：通过binarySearch方法能对排序好的数组进行二分查找法操作。

  [5] copyOf, 数组复制
  [6] copyOfRange 数组范围复制
  [7] parallelPrefix 并行计算
  [8] parallelSort  并行排序
  [9] spliterator 可分割迭代器
 *
 * http://blog.csdn.net/object_allen/article/details/41695425
 *
 **/
public class ArraysExample {

	public static void output(int[] array){
		if (array!=null){
			for (int i=0;i<array.length;i++){
				System.out.print(array[i]+" ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
	    int[] array=new int[5];
	    //填充数组
		Arrays.fill(array,5);
		System.out.println("填充数组：Arrays.fill(array,5):");
		output(array);

		//将数组的第2和第3个元素赋值为8
	}
}
