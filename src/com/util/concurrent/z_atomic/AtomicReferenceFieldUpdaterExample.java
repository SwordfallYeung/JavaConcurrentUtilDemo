package com.util.concurrent.z_atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author y15079
 * @create 2017-09-13 15:22
 * @desc
 *
 * AtomicReferenceFieldUpdater一个基于反射的工具类，它能对指定类的指定的volatile引用字段进行原子更新。(注意这个字段不能是private的)
 * 通过调用AtomicReferenceFieldUpdater的静态方法newUpdater就能创建它的实例，该方法要接收三个参数：
 * 包含该字段的对象的类
 * 将被更新的对象的类
 * 将被更新的字段的名称
 **/
public class AtomicReferenceFieldUpdaterExample {
	public static void main(String[] args) throws Exception{
		AtomicReferenceFieldUpdater updater=AtomicReferenceFieldUpdater.newUpdater(Dog.class,String.class,"name");
		Dog dog=new Dog();
		updater.compareAndSet(dog,dog.name,"serviceloader");
		System.out.println(dog.name);
	}

	static class Dog{
		volatile String name="dog1";
	}
}
