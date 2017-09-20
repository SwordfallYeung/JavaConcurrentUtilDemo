package com.util.base.b_class;

import java.util.*;

/**
 * @author y15079
 * @create 2017-09-20 11:03
 * @desc
 *
 *  * Deque
 * 双端队列是一个限定插入和删除操作的数据结构，具有队列和栈的性质。
 * 双端队列是限定插入和删除操作在表的两端进行的线性表。
 *
 * 尽管双端队列看起来似乎比栈和队列更灵活，但实际上在应用程序中远不及栈和队列有用。
 *双端队列就是一个两端都是结尾的队列。队列的每一端都可以插入数据项和移除数据项。
 * 这些方法可以叫作insertLeft()和insertRight()，以及removeLeft()和removeRight()。
 * 如果严格禁止调用insertLeft()和removeLeft()方法（或禁用右段的操作），双端队列功能就和栈一样。
 * 禁止调用insertLeft()和removeRight()（或相反的另一对方法），它的功能就和队列一样了。
 * 双端队列与栈或队列相比，是一种多用途的数据结构，在容器类库中有时会用双端队列来提供栈和队列两种功能。
 *
 * 继承自AbastractCollection（该类实习了部分集合通用的方法，其实现了Collection接口）
 *
 * 就其实现而言，ArrayDeque采用了循环数组的方式来完成双端队列的功能。
 *1. 无限的扩展，自动扩展队列大小的。（当然在不会内存溢出的情况下。）
 *2. 非线程安全的，不支持并发访问和修改。
 *3. 支持fast-fail.
 *4. 作为栈使用的话比比栈要快.
 *5. 当队列使用比LinkedList要快。
 *6. null元素被禁止使用。
 *
 *
 * 题目要求为：
 * 卡拉兹(Callatz)猜想：
 * 对任何一个自然数n，如果它是偶数，那么把它砍掉一半；如果它是奇数，那么把(3n+1)砍掉一半。这样一直反复砍下去，最后一定在某一步得到n=1。
   当我们验证卡拉兹猜想的时候，为了避免重复计算，可以记录下递推过程中遇到的每一个数。例如对n=3进行验证的时候，我们需要计算3、5、8、4、2、1，
   则当我们对n=5、8、4、2进行验证的时候，就可以直接判定卡拉兹猜想的真伪，而不需要重复计算，因为这4个数已经在验证3的时候遇到过了，我们称5、8、4、2是被3“覆盖”的数。
   我们称一个数列中的某个数n为“关键数”，如果n不能被数列中的其他数字所覆盖。
 * 现在给定一系列待验证的数字，我们只需要验证其中的几个关键数，就可以不必再重复验证余下的数字。你的任务就是找出这些关键数字，并按从大到小的顺序输出它们。
 * 输入格式：每个测试输入包含1个测试用例，第1行给出一个正整数K(<100)，第2行给出K个互不相同的待验证的正整数n(1<n<=100)的值，数字间用空格隔开。
 * 输出格式：每个测试用例的输出占一行，按从大到小的顺序输出关键数字。数字间用1个空格隔开，但一行中最后一个数字后没有空格。
 * 输入样例：
   6
   3 5 6 7 8 11
   输出样例：
   7 6
 *
 * ArrayDeque： http://www.jb51.net/article/81653.htm
 *
 **/
public class ArrayDequeExample {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		int numSize=scanner.nextInt();
		ArrayDeque<Integer> newArrayDeque=new ArrayDeque<Integer>();
		ArrayDeque<Integer> closeArrayDeque=new ArrayDeque<Integer>();
		int i;
		while (scanner.hasNext()){
			//读取键盘输入值
			for (i=0;i<numSize;i++){
				newArrayDeque.add(scanner.nextInt());
			}
			if (i>=numSize){
				break;
			}
		}
		int temp;
		for (Integer integer:newArrayDeque){
			//将非关键数存入closeArrayDeque中
			temp=integer;
			while (temp!=1){
				if (temp%2==0){
					//偶数
					temp=temp/2;
					if (newArrayDeque.contains(temp)){
                       closeArrayDeque.add(temp);
					}
				}else {
					temp=(temp*3+1);
					if (newArrayDeque.contains(temp)){
						closeArrayDeque.add(temp);
					}
				}
			}
		}
		SortedSet<Integer> sortedSet=new TreeSet<Integer>(new Comparator<Integer>() {
			//o1为大，o2为小，小减大为负，负则交换位置
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});//sortedSet用于存放关键数, 默认从小到大
		for (Integer integer:newArrayDeque){
			if (!closeArrayDeque.contains(integer)){
				sortedSet.add(integer);
			}
		}

		int j=sortedSet.size()-1;
		for (Integer integer:sortedSet){
			if (j==0){
				System.out.print(integer);
			}else {
				System.out.print(integer+" ");
			}
			j--;
		}
	}

}
