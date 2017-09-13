package com.util.concurrent.y_lock;

/**
 * @author y15079
 * @create: 9/14/17 12:53 AM
 * @desc:
 *
 * Lock为接口，实现类为ReentrantLock
 *
 * Lock比synchronized关键字更灵活，而且在吞吐量大的时候效率更高，根据JSR-133的定义，
 * 它happens-before的语义和synchronized关键字效果是一模一样的，
 * 它唯一的缺点似乎是缺乏了从lock到finally块中unlock这样容易遗漏的固定使用搭配的约束，除了lock和unlock方法以外，
 * 还有这样两个值得注意的方法：
 * lockInterruptibly：如果当前线程没有被中断，就获取锁；否则抛出InterruptedException，并且清除中断
 * tryLock，只在锁空闲的时候才获取这个锁，否则返回false，所以它不会block代码的执行
 */
public class LockExample {
}
