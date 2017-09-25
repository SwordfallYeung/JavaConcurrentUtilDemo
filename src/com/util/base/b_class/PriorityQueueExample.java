package com.util.base.b_class;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author y15079
 * @create 2017-09-25 16:42
 * @desc
 * PriorityQueue是一个比较标准的队列实现类，之所以说它是比较标准的队列实现，而不是绝对标准的队列实现
 * 是因为：PriorityQueue保存队列元素的顺序并不是按加入队列的顺序，而是按队列元素的大小进行重新排序。
 * 因此当调用peek方法或着pull方法来取出队列中的元素时，并不是取出最先进入队列的元素，而是取出队列中最小的元素。
 * 从这个意义上看，PriorityQueue已经违反了队列的最基本原则:先进先出(FIFO)。
 *
 * PriorityQueue不允许插入null元素，它还需要对队列元素进行排序，队列元素有两种排序方式：自然排序、定制排序；
 * 关于使用自然排序和定制排序与前面讲到的TreeSet集合一样
 *
 * http://www.cnblogs.com/be-forward-to-help-others/p/6825738.html
 * http://blog.csdn.net/kingzma/article/details/44686347
 **/
public class PriorityQueueExample {

	public static void natureSort(){
		PriorityQueue<Integer> priorityQueue=new PriorityQueue<Integer>();
		priorityQueue.offer(3);
		priorityQueue.offer(-6);
		priorityQueue.offer(9);
		//打印结果为[-6, 3, 9]
		System.out.println(priorityQueue);
		//打印结果为-6
		System.out.println(priorityQueue.peek());
		//打印结果为-6
		System.out.println(priorityQueue.poll());
	}

	public static void DIYSort(){
        int[] A={1,3,5,7,9,2,4,6,8,10};
        //自定义 大到小排序
        PriorityQueue<Integer> queue=new PriorityQueue<Integer>(A.length, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});

        for (int i:A){
        	queue.add(i);
		}
		System.out.println("---"+queue.isEmpty());
		while (!queue.isEmpty()){
			System.out.println(queue.poll());
		}
	}

	public static void main(String[] args) {

	}
}
