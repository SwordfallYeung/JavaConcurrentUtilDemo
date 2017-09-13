package com.util.concurrent.y_lock;

/**
 * @author y15079
 * @create: 9/14/17 1:06 AM
 * @desc:
 *
 * ReadWriteLock为接口，
 * 实现类ReentrantReadWriteLock,ReentrantReadWriteLock.ReadLock，ReentrantReadWriteLock.WriteLock
 *
 * 读写锁，读写分开，读锁是共享锁，写锁是独占锁；对于读-写都要保证严格的实时性和同步性的情况，
 * 并且读频率远远大过写，使用读写锁会比普通互斥锁有更好的性能。
 */
public class ReadWriteLockExample {
}
