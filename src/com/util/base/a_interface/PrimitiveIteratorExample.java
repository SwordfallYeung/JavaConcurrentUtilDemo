package com.util.base.a_interface;

/**
 * @author y15079
 * @create 2017-09-19 11:24
 * @desc
 *
 * PrimitiveIterator：
 *                   forEachRemaining
 *                   //为每个剩余元素执行给定的操作,直到所有的元素都已经被处理或行动将抛出一个异常
 *                   //Performs the given action for each remaining element, in the order elements occur when iterating, until all elements have been processed or the action throws an exception.
 *
 *  PrimitiveIterator.OfDouble:
 *                     forEachRemaining(Consumer<? super Double> action)
                             Performs the given action for each remaining element until all elements have been processed or the action throws an exception.
                       forEachRemaining(DoubleConsumer action)
                             Performs the given action for each remaining element until all elements have been processed or the action throws an exception.
                       next()
                             Returns the next element in the iteration.
        	           nextDouble()
                             Returns the next double element in the iteration.

 *  PrimitiveIterator.OfInt 与上述雷同
 *
 *  PrimitiveIterator.OfLong 与上述雷同
 **/
public class PrimitiveIteratorExample {
	//暂无
}
