package com.util.concurrent.z_atomic;

/**
 * @author y15079
 * @create: 9/12/17 12:01 AM
 * @desc:
 *
 * 原子对象
 *
 * 这些对象都的行为在不使用同步的情况下保证了原子性。值得一提的有两点：
 * weakCompareAndSet方法：compareAndSet方法很明确，但是这个是啥？根据JSR规范，调用weakCompareAndSet时并不能保证happen-before的一致性，
 *                       因此允许存在重排序指令等等虚拟机优化导致这个操作失败（较弱的原子更新操作），但是从Java源代码看，它的实现其实和compareAndSet是一模一样的；
 *                       lazySet方法：延时设置变量值，这个等价于set方法，但是由于字段是volatile类型的，
 *                       因此次字段的修改会比普通字段（非volatile字段）有稍微的性能损耗，所以如果不需要立即读取设置的新值，那么此方法就很有用。
 *
 * AtomicBoolean.class  原子更新布尔类型
 * AtomicInteger.class  原子更新整型
 * AtomicIntegerArray.class 原子更新长整型
 * AtomicIntegerFieldUpdater.class  原子更新整型的字段的更新器
 * AtomicLong.class  原子更新整型数组里的元素
 * AtomicLongArray.class  原子更新长整型数组里的元素
 * AtomicLongFieldUpdater.class    原子更新长整型字段的更新器
 * AtomicMarkableReference.class， 原子更新带有标记位的引用类型   它是用来高效表述Object-boolean这样的对象标志位数据结构的，一个对象引用+一个bit标志位
 * AtomicReference.class   原子更新引用类型
 * AtomicReferenceArray.class  原子更新引用类型数组里的元素
 * AtomicReferenceFieldUpdater.class  原子更新引用类型里的字段
 * AtomicStampedReference.class，  原子更新带有版本号的引用类型    它和前面的AtomicMarkableReference类似，但是它是用来高效表述Object-int这样的“对象+版本号”数据结构，
 * 特别用于解决ABA问题（ABA问题这篇文章里面也有介绍）
 *
 * 累加器DoubleAccumulator、DoubleAdder、LongAccumulator、LongAdder、Striped64
 *
 * 在Atomic包里一共提供了13个类，属于4种类型的原子更新方式，
 * 分别是原子更新基本类型、原子更新数组、原子更新引用和原子更新属性（字段）。
 * Atomic包里的类基本都是使用Unsafe实现的包装类。
 *
 */
public class atomicLearnAbout {
}
