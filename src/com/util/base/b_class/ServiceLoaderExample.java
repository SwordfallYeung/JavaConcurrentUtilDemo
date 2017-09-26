package com.util.base.b_class;

import com.util.base.serviceloader.IService;

import java.util.ServiceLoader;

/**
 * @author y15079
 * @create 2017-09-26 10:27
 * @desc
 *
 * ServiceLoader与ClassLoader是Java中2个即相互区别又相互联系的加载器.
 *[1] JVM利用ClassLoader将类载入内存，这是一个类声明周期的第一步（一个java类的完整的生命周期会经历加载、
 *    连接、初始化、使用、和卸载五个阶段，当然也有在加载或者连接之后没有被初始化就直接被使用的情况）。
 *[2] ServiceLoader：一个简单的服务提供者加载设施。服务 是一个熟知的接口和类（通常为抽象类）集合。
 *    服务提供者 是服务的特定实现。提供者中的类通常实现接口，并子类化在服务本身中定义的子类。
 *    服务提供者可以以扩展的形式安装在 Java 平台的实现中，也就是将 jar 文件放入任意常用的扩展目录中。
 *    也可通过将提供者加入应用程序类路径，或者通过其他某些特定于平台的方式使其可用。
 *
 * 简单总结：
   ServiceLoader也像ClassLoader一样，能装载类文件，但是使用时有区别，具体区别如下：
      （1） ServiceLoader装载的是一系列有某种共同特征的实现类，而ClassLoader是个万能加载器；
      （2）ServiceLoader装载时需要特殊的配置，使用时也与ClassLoader有所区别；
      （3）ServiceLoader还实现了Iterator接口。
 *
 *
 * 在FileSystem中用到了java.util.ServiceLoader这个类来从配置文件中加载子类或者接口的实现类。
 *
 * 主要是从META-INF/services这个目录下的配置文件加载给定接口或者基类的实现，ServiceLoader会根据给定的类的full name来
 * 在META-INF/services下面找对应的文件，在这个文件中定义了所有这个类的子类或者接口的实现类，返回一个实例。
 *
 * 下面以一个具体的例子来说明一下ServiceLoader的具体使用，类似Hadoop FileSystem中的实现。
 *
 **/
public class ServiceLoaderExample {



	public static void main(String[] args) {
		//need to define related class full name in /META-INF/services/....
		ServiceLoader<IService> serviceLoader=ServiceLoader.load(IService.class);
		for (IService service:serviceLoader){
			System.out.println(service.getScheme()+":"+service.sayHello());
		}
	}
}
