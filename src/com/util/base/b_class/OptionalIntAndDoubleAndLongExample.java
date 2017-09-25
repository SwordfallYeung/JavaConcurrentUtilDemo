package com.util.base.b_class;

import java.util.OptionalInt;

/**
 * @author y15079
 * @create 2017-09-25 16:22
 * @desc
 *
 * OptionalInt,OptionalDouble,OptionalLong类的工作方式与Optional类十分类似，只不过他们是专门操作int，double，long类型的值而设计的。
 * 因此，他们分别定义了getAsInt(),getAsDouble(),getAsLong()方法，而不是get()方法
 *
 * 值得注意的一点是，上面这3个类并不支持ofNullable()，filter()，map()，flatMap()这几个方法。下面是OptionalInt类的实例代码：
 *
 *  OptionalInt,OptionalDouble,OptionalLong类示例代码
 *
 *  http://blog.csdn.net/u011794238/article/details/49532717
 **/
public class OptionalIntAndDoubleAndLongExample {
	public static void main(String[] args) {

		//静态工厂方法获取一个示例
		OptionalInt op = OptionalInt.of(1);
		if (op.isPresent()) //判断OptionalInt中是否有值
		{
			//获得OptionalInt对象里面的值，输出1
			System.out.println(op.getAsInt());
		}
		op.ifPresent((value) -> System.out.println("value：" + value));

		//创建一个空值对象
		OptionalInt opint = OptionalInt.empty();
		if (opint.isPresent())
		{
			//和Optional一样，输出No value present
			System.out.println(opint.getAsInt());
		}
		else
		{
			//如果没有值，赋初始值
			System.out.println(opint.orElse(222));
			//如果没有值，赋初始函数
			System.out.println(opint.orElseGet(() -> 333));
		}
		//如果没有值则抛出异常
		opint.orElseThrow(NullPointerException::new);




	}

}
