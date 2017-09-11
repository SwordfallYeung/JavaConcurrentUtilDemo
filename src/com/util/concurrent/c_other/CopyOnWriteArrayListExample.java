package com.util.concurrent.c_other;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author y15079
 * @create 2017-09-09 19:38
 * @desc
 *
 * Copy-On-Write简称COW，是一种用于程序设计中的优化策略。
 * 其基本思路是，从一开始大家都在共享同一个内容，当某个人想要修改这个内容的时候，才会真正把内容Copy出去形成一个新的内容然后再改，这是一种延时懒惰策略。从JDK1.5开始Java并发包里提供了两个使用CopyOnWrite机制实现的并发容器,它们是CopyOnWriteArrayList和CopyOnWriteArraySet。
 * CopyOnWrite容器非常有用，可以在非常多的并发场景中使用到。
 *
 * CopyOnWrite容器即写时复制的容器。通俗的理解是当我们往一个容器添加元素的时候，不直接往当前容器添加，
 * 而是先将当前容器进行Copy，复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。
 * 这样做的好处是我们可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
 * 所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
 *
 * CopyOnWrite并发容器用于读多写少的并发场景。比如白名单，黑名单，商品类目的访问和更新场景
 *
 * CopyOnWrite容器缺点：
 *     ①内存占用问题，内存过大，会造成频繁的Yong GC和Full GC
 *     ②数据一致性问题，只能保证数据的最终一致性，不能保证数据的实时一致性。
 **/
public class CopyOnWriteArrayListExample {

	public static void main(String[] args) throws Exception {
        //testErrorWhenReadOnWrite();
		testCopyOnWriteArrayList();
	}

	//CopyOnWriteArrayList类最大的特点就是，在对其实例进行修改操作（add/remove等）会新建一个数据并修改，修改完毕之后，再将原来的引用指向新的数组。
	// 这样，修改过程没有修改原来的数组。也就没有了ConcurrentModificationException错误。
	public static void testCopyOnWriteArrayList() throws Exception{
		List<String> a=new ArrayList<String>();
		a.add("a");a.add("b");a.add("c");
		final CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<String>();
		Thread t =new Thread(new Runnable() {
			int count = -1;
			@Override
			public void run() {
				while (true){
					list.add(count++ + "");
				}
			}
		});
		t.setDaemon(true);
		t.start();
		Thread.currentThread().sleep(3);
		for (String s:list){
//			System.out.println(list.hashCode());
			System.out.println(s);
		}
	}

	//当主线程在遍历list的时候，子线程在向list中添加元素。会抛出java.util.ConcurrentModificationException错误
	public static void testErrorWhenReadOnWrite() throws Exception{
		List<String> a=new ArrayList<>();
		a.add("a");
		a.add("b");
		a.add("c");
		final ArrayList<String> list=new ArrayList<String>(a);

		Thread t=new Thread(new Runnable() {
			int count=-1;

			@Override
			public void run() {
				while (true){
					list.add(count++ +"");
				}
			}
		});
		t.setDaemon(true);
		t.start();
		Thread.currentThread().sleep(3);
		for (String s: list){
			System.out.println(s);
		}
	}
}
