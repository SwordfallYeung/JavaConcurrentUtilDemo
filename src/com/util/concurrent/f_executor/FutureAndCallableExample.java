package com.util.concurrent.f_executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author y15079
 * @create: 9/15/17 1:16 AM
 * @desc:
 */
public class FutureAndCallableExample {
    public static class findMaxTask implements Callable<Integer>{
        private int start;
        private int end;
        private Integer[] data;

        public findMaxTask(int start, int end, Integer[] data) {
            this.start = start;
            this.end = end;
            this.data = data;
        }

        @Override
        public Integer call() throws Exception {
            int maxNum = Integer.MIN_VALUE;
            //System.out.println(Thread.currentThread());
            //Thread.sleep(3000);
            for (int i = start; i < end; i ++) {
                maxNum = Math.max(maxNum, data[i]);
            }
            return maxNum;
        }
    }

    public static void main(String[] args) throws Exception{

        Integer[] arr=new Integer[]{1,8,5,3,9,6,2,0,7,4};

        findMaxTask task1 = new findMaxTask(0, arr.length / 2, arr);
        findMaxTask task2 = new findMaxTask(arr.length / 2, arr.length, arr);
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> ans1 = service.submit(task1);
        Future<Integer> ans2 = service.submit(task2);
        int maxNum1 = ans1.get();
        int maxNum2 = ans2.get();
        System.out.println("maxNum1 = " + maxNum1);
        System.out.println("maxNum2 = " + maxNum2);
        service.shutdown();
        System.out.println(Math.max(maxNum1, maxNum2));
    }
}
