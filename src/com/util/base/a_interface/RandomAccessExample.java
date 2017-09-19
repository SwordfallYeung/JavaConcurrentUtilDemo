package com.util.base.a_interface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author y15079
 * @create 2017-09-19 13:45
 * @desc
 *
 * List实现所使用的标记接口，用来表明其支持快速（通常是固定时间）随机访问。
 * 此接口的主要目的是允许一般的算法更改其行为，从而在将其应用到随机或连续访问列表时能提供良好的性能。
 *
 * 随机和连续访问之间的区别通常是模糊的。例如，如果列表很大时，某些list实现提供渐进的线性访问时间，
 * 但实际上是固定的访问时间。这样的list实现通常应该实现此接口。
 *
 * JDK中推荐的是对List集合尽量要实现RandomAccess接口
 * 如果集合类是RandomAccess的实现，则尽量用for(int i=0;i<size;i++）来遍历而不要用Iterator迭代器来遍历
 *
 * 反过来，如果List是Sequence List，则最好用迭代器来进行迭代
 *
 * JDK中说的很清楚，在对List特别是Huge size的List的遍历算法中，要尽量来判断是属于RandomAccess（如ArrayList）
 * 还是Sequence List(如LinkedList)，因为适合RandomAccess List的遍历算法，用在Sequence List上就
 * 差别很大。
 *
 *
 **/
public class RandomAccessExample {
	public static void main(String[] args) throws Exception{
		ArrayList<Integer> arrayList=new ArrayList<Integer>();
		LinkedList<Integer> linkedList=new LinkedList<Integer>();
        initList(arrayList,1000);
        initList(linkedList,1000);

        System.out.println("ArrayList实现了RandomAccess接口且用for遍历：");
		implRandomAccessTraverse(arrayList); //花了10ms时间
		System.out.println("\nArrayList实现了RandomAccess接口且用Iterator遍历：");
		noImplRandomAccessTraverse(arrayList); //花了39ms时间
		//证明实现RandomAccess接口的ArrayList应该用for遍历快

		System.out.println("LinkedList未实现了RandomAccess接口且用for遍历：");
		implRandomAccessTraverse(linkedList); //花了434ms时间
		System.out.println("LinkedList未实现了RandomAccess接口且用Iterator遍历：");
		noImplRandomAccessTraverse(linkedList); //花了27ms时间
		//证明没实现RandomAccess接口的LinkedList应该用Iterator遍历快
	}

	private static long startTime=0;
	private static long endTime=0;
	//初始化列表
	public static void initList(List<Integer> list,int n){
		for (int i=0;i<n;i++){
			list.add(i);
		}
	}

	//有实现RandomAccess接口的遍历全部数据
	public static void implRandomAccessTraverse(List list){
		startTime=System.currentTimeMillis();
		for (int count=0;count<=1000;count++){
			for (int i=0;i<list.size();i++){
				list.get(i);
			}
		}
		endTime=System.currentTimeMillis();
		System.out.println("使用for迭代一共花了"+(endTime-startTime));
	}

	//没有实现RandomAccess接口的遍历全部数据
	public static void noImplRandomAccessTraverse(List list){
		for (int count=0;count<1000;count++){
			for (Iterator itr=list.iterator();itr.hasNext();){
				itr.next();
			}
		}
		endTime=System.currentTimeMillis();
		System.out.println("使用Iterator迭代一共花了"+(endTime-startTime));
	}
}
