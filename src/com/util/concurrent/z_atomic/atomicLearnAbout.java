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
 * AtomicBoolean.class
 * AtomicInteger.class
 * AtomicIntegerArray.class
 * AtomicIntegerFieldUpdater.class
 * AtomicLong.class
 * AtomicLongArray.class
 * AtomicLongFieldUpdater.class
 * AtomicMarkableReference.class，它是用来高效表述Object-boolean这样的对象标志位数据结构的，一个对象引用+一个bit标志位
 * AtomicReference.class
 * AtomicReferenceArray.class
 * AtomicReferenceFieldUpdater.class
 * AtomicStampedReference.class，它和前面的AtomicMarkableReference类似，但是它是用来高效表述Object-int这样的“对象+版本号”数据结构，
 * 特别用于解决ABA问题（ABA问题这篇文章里面也有介绍）
 *
 *
 */
public class atomicLearnAbout {
}
