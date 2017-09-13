package com.util.concurrent.z_atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author y15079
 * @create 2017-09-13 15:38
 * @desc
 *
 * AtomicMarkableReference  相当于一个[引用,boolean]的二元组,可用于表示如：已删除的节点
 **/
public class AtomicMarkableReferenceExample {
	static AtomicMarkableReference<String> amr=new AtomicMarkableReference<String>("ref",false);

	static class AddThread implements Runnable{
		@Override
		public void run() {
			//update the value of mark as true if "ref" matches to current reference
			amr.attemptMark("ref",true);

			System.out.println("1."+amr.getReference()+" "+amr.isMarked());

			//if current reference is "ref" and current mark is "true" then it change as newref and false
			amr.compareAndSet("ref","newref",true,false);
			System.out.println("2."+amr.getReference()+" "+amr.isMarked());

			//sets the new reference and new mark without any check
			amr.set("reference",true);
			System.out.println("3."+amr.getReference()+" "+amr.isMarked());
		}
	}

	public static void main(String[] args) {
		new Thread(new AddThread()).start();
	}
}
